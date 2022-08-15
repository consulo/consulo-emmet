/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.codeInsight.template.emmet;

import com.intellij.codeInsight.template.emmet.options.EmmetOptions;
import com.intellij.codeInsight.template.emmet.filters.SingleLineEmmetFilter;
import com.intellij.codeInsight.template.emmet.filters.ZenCodingFilter;
import com.intellij.codeInsight.template.emmet.generators.XmlZenCodingGenerator;
import com.intellij.codeInsight.template.emmet.generators.ZenCodingGenerator;
import com.intellij.codeInsight.template.emmet.nodes.*;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import com.intellij.codeInsight.template.emmet.tokens.TextToken;
import com.intellij.codeInsight.template.emmet.tokens.ZenCodingToken;
import consulo.annotation.component.ExtensionImpl;
import consulo.application.ApplicationManager;
import consulo.application.ApplicationPropertiesComponent;
import consulo.application.ui.wm.ApplicationIdeFocusManager;
import consulo.application.ui.wm.IdeFocusManager;
import consulo.codeEditor.*;
import consulo.codeEditor.util.EditorModificationUtil;
import consulo.document.util.TextRange;
import consulo.emmet.localize.EmmetLocalize;
import consulo.language.editor.CodeInsightBundle;
import consulo.language.editor.completion.CompletionParameters;
import consulo.language.editor.completion.CompletionResultSet;
import consulo.language.editor.template.*;
import consulo.language.editor.template.context.TemplateActionContext;
import consulo.language.editor.template.event.TemplateEditingAdapter;
import consulo.language.editor.template.event.TemplateEditingListener;
import consulo.language.pattern.StandardPatterns;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.util.AttachmentFactoryUtil;
import consulo.logging.Logger;
import consulo.ui.ex.awt.LightColors;
import consulo.ui.ex.awt.TextFieldWithHistory;
import consulo.ui.ex.awt.TextFieldWithStoredHistory;
import consulo.ui.ex.awt.event.DocumentAdapter;
import consulo.ui.ex.popup.Balloon;
import consulo.ui.ex.popup.JBPopupFactory;
import consulo.ui.ex.popup.event.JBPopupListener;
import consulo.ui.ex.popup.event.LightweightWindowEvent;
import consulo.undoRedo.CommandProcessor;
import consulo.util.collection.ContainerUtil;
import consulo.util.lang.Couple;
import consulo.util.lang.StringUtil;
import consulo.util.lang.ref.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.*;

/**
 * @author Eugene.Kudelevsky
 */
@ExtensionImpl
public class ZenCodingTemplate extends CustomLiveTemplateBase
{
	public static final char MARKER = '\0';
	private static final String EMMET_RECENT_WRAP_ABBREVIATIONS_KEY = "emmet.recent.wrap.abbreviations";
	private static final String EMMET_LAST_WRAP_ABBREVIATIONS_KEY = "emmet.last.wrap.abbreviations";
	private static final Logger LOG = Logger.getInstance(ZenCodingTemplate.class);

	@Nullable
	public static ZenCodingGenerator findApplicableDefaultGenerator(@NotNull PsiElement context, boolean wrapping)
	{
		for(ZenCodingGenerator generator : ZenCodingGenerator.getInstances())
		{
			if(generator.isMyContext(context, wrapping) && generator.isAppliedByDefault(context))
			{
				return generator;
			}
		}
		return null;
	}

	@Nullable
	public static ZenCodingNode parse(
			@NotNull String text, @NotNull CustomTemplateCallback callback, @NotNull ZenCodingGenerator generator, @Nullable String surroundedText)
	{
		List<ZenCodingToken> tokens = new EmmetLexer().lex(text);
		if(tokens == null)
		{
			return null;
		}
		if(!validate(tokens, generator))
		{
			return null;
		}
		EmmetParser parser = generator.createParser(tokens, callback, generator, surroundedText != null);
		ZenCodingNode node = parser.parse();
		if(parser.getIndex() != tokens.size() || node instanceof TextNode)
		{
			return null;
		}
		return node;
	}

	private static boolean validate(@NotNull List<ZenCodingToken> tokens, @NotNull ZenCodingGenerator generator)
	{
		for(ZenCodingToken token : tokens)
		{
			if(token instanceof TextToken && !(generator instanceof XmlZenCodingGenerator))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean checkTemplateKey(@NotNull String key, CustomTemplateCallback callback, @NotNull ZenCodingGenerator generator)
	{
		return parse(key, callback, generator, null) != null;
	}

	@Override
	public void expand(@NotNull String key, @NotNull CustomTemplateCallback callback)
	{
		ZenCodingGenerator defaultGenerator = findApplicableDefaultGenerator(callback.getContext(), false);
		assert defaultGenerator != null;
		expand(key, callback, null, defaultGenerator, Collections.<ZenCodingFilter>emptyList(), true, 50);
	}

	@Nullable
	private static ZenCodingGenerator findApplicableGenerator(ZenCodingNode node, PsiElement context, boolean wrapping)
	{
		ZenCodingGenerator defaultGenerator = null;
		List<ZenCodingGenerator> generators = ZenCodingGenerator.getInstances();
		for(ZenCodingGenerator generator : generators)
		{
			if(defaultGenerator == null && generator.isMyContext(context, wrapping) && generator.isAppliedByDefault(context))
			{
				defaultGenerator = generator;
			}
		}
		while(node instanceof FilterNode)
		{
			FilterNode filterNode = (FilterNode) node;
			String suffix = filterNode.getFilter();
			for(ZenCodingGenerator generator : generators)
			{
				if(generator.isMyContext(context, wrapping))
				{
					if(suffix != null && suffix.equals(generator.getSuffix()))
					{
						return generator;
					}
				}
			}
			node = filterNode.getNode();
		}
		return defaultGenerator;
	}

	private static List<ZenCodingFilter> getFilters(ZenCodingNode node, PsiElement context)
	{
		List<ZenCodingFilter> result = new ArrayList<ZenCodingFilter>();

		while(node instanceof FilterNode)
		{
			FilterNode filterNode = (FilterNode) node;
			String filterSuffix = filterNode.getFilter();
			boolean filterFound = false;
			for(ZenCodingFilter filter : ZenCodingFilter.getInstances())
			{
				if(filter.isMyContext(context) && filter.getSuffix().equals(filterSuffix))
				{
					filterFound = true;
					result.add(filter);
				}
			}
			assert filterFound;
			node = filterNode.getNode();
		}

		for(ZenCodingFilter filter : ZenCodingFilter.getInstances())
		{
			if(filter.isMyContext(context) && filter.isAppliedByDefault(context))
			{
				result.add(filter);
			}
		}

		Collections.reverse(result);
		return result;
	}


	public static void expand(
			@NotNull String key,
			@NotNull CustomTemplateCallback callback,
			@Nullable String surroundedText,
			@NotNull ZenCodingGenerator defaultGenerator,
			@NotNull Collection<? extends ZenCodingFilter> extraFilters,
			boolean expandPrimitiveAbbreviations,
			int segmentsLimit)
	{
		final ZenCodingNode node = parse(key, callback, defaultGenerator, surroundedText);
		if(node == null)
		{
			return;
		}
		if(surroundedText == null && node instanceof TemplateNode)
		{
			if(key.equals(((TemplateNode) node).getTemplateToken().getKey()) && callback.findApplicableTemplates(key).size() > 1)
			{
				TemplateManager templateManager = callback.getTemplateManager();
				Map<Template, String> template2Argument = templateManager.findMatchingTemplates(callback.getFile(), callback.getEditor(), null,
						TemplateSettings.getInstance());
				Runnable runnable = templateManager.startNonCustomTemplates(template2Argument, callback.getEditor(), null);
				if(runnable != null)
				{
					runnable.run();
				}
				return;
			}
		}

		PsiElement context = callback.getContext();
		ZenCodingGenerator generator = findApplicableGenerator(node, context, false);
		List<ZenCodingFilter> filters = getFilters(node, context);
		filters.addAll(extraFilters);


		if(surroundedText == null)
		{
			callback.deleteTemplateKey(key);
			// commit is required. otherwise injections placed after caret will be broken
			PsiDocumentManager.getInstance(callback.getProject()).commitDocument(callback.getEditor().getDocument());
		}
		expand(node, generator, filters, surroundedText, callback, expandPrimitiveAbbreviations, segmentsLimit);
	}

	private static void expand(
			ZenCodingNode node,
			ZenCodingGenerator generator,
			List<ZenCodingFilter> filters,
			String surroundedText,
			CustomTemplateCallback callback,
			boolean expandPrimitiveAbbreviations,
			int segmentsLimit)
	{
		if(surroundedText != null)
		{
			surroundedText = surroundedText.trim();
		}

		GenerationNode fakeParentNode = new GenerationNode(TemplateToken.EMPTY_TEMPLATE_TOKEN, -1, 1, surroundedText, true, null);
		node.expand(-1, 1, surroundedText, callback, true, fakeParentNode);

		if(!expandPrimitiveAbbreviations)
		{
			if(isPrimitiveNode(node))
			{
				return;
			}
		}

		List<GenerationNode> genNodes = fakeParentNode.getChildren();
		LiveTemplateBuilder builder = new LiveTemplateBuilder(segmentsLimit);
		int end = -1;
		for(int i = 0, genNodesSize = genNodes.size(); i < genNodesSize; i++)
		{
			GenerationNode genNode = genNodes.get(i);
			Template template = genNode.generate(callback, generator, filters, true, segmentsLimit);
			int e = builder.insertTemplate(builder.length(), template, null);
			if(i < genNodesSize - 1 && genNode.isInsertNewLineBetweenNodes())
			{
				builder.insertText(e, "\n", false);
				e++;
			}
			if(end == -1 && end < builder.length())
			{
				end = e;
			}
		}
		for(ZenCodingFilter filter : filters)
		{
			if(filter instanceof SingleLineEmmetFilter)
			{
				builder.setIsToReformat(false);
				break;
			}
		}

		callback.startTemplate(builder.buildTemplate(), null, new TemplateEditingAdapter()
		{
			private TextRange myEndVarRange;
			private Editor myEditor;

			@Override
			public void beforeTemplateFinished(TemplateState state, Template template)
			{
				int variableNumber = state.getCurrentVariableNumber();
				if(variableNumber >= 0)
				{
					Template t = (Template) template;
					while(variableNumber < t.getVariableCount())
					{
						String varName = t.getVariableNameAt(variableNumber);
						if(LiveTemplateBuilder.isEndVariable(varName))
						{
							myEndVarRange = state.getVariableRange(varName);
							myEditor = state.getEditor();
							break;
						}
						variableNumber++;
					}
				}
			}

			@Override
			public void templateFinished(Template template, boolean brokenOff)
			{
				if(brokenOff && myEndVarRange != null && myEditor != null)
				{
					int offset = myEndVarRange.getStartOffset();
					if(offset >= 0 && offset != myEditor.getCaretModel().getOffset())
					{
						myEditor.getCaretModel().moveToOffset(offset);
						myEditor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
					}
				}
			}
		});
	}

	private static boolean isPrimitiveNode(@NotNull ZenCodingNode node)
	{
		if(node instanceof TemplateNode)
		{
			final TemplateToken token = ((TemplateNode) node).getTemplateToken();
			if(token != null)
			{
				final List<Couple<String>> attributes = token.getAttribute2Value();
				final Couple<String> singleAttribute = ContainerUtil.getFirstItem(attributes);
				if(singleAttribute == null || "class".equalsIgnoreCase(singleAttribute.first) && StringUtil.isEmpty(singleAttribute.second))
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void wrap(@NotNull final String selection, @NotNull final CustomTemplateCallback callback)
	{
		final TextFieldWithStoredHistory field = new TextFieldWithStoredHistory(EMMET_RECENT_WRAP_ABBREVIATIONS_KEY);
		final Dimension fieldPreferredSize = field.getPreferredSize();
		field.setPreferredSize(new Dimension(Math.max(220, fieldPreferredSize.width), fieldPreferredSize.height));
		field.setHistorySize(10);
		final JBPopupFactory popupFactory = JBPopupFactory.getInstance();
		final Balloon balloon = popupFactory.createDialogBalloonBuilder(field, EmmetLocalize.emmetTitle().getValue())
				.setCloseButtonEnabled(false)
				.setBlockClicksThroughBalloon(true)
				.setHideOnClickOutside(true)
				.setAnimationCycle(0)
				.setHideOnKeyOutside(true)
				.createBalloon();

		field.addDocumentListener(new DocumentAdapter()
		{
			@Override
			protected void textChanged(DocumentEvent e)
			{
				validateTemplateKey(field, balloon, field.getText(), callback);
			}
		});
		field.addKeyboardListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(!field.isPopupVisible())
				{
					switch(e.getKeyCode())
					{
						case KeyEvent.VK_ENTER:
							final String abbreviation = field.getText();
							if(validateTemplateKey(field, balloon, abbreviation, callback))
							{
								doWrap(abbreviation, callback);
								ApplicationPropertiesComponent.getInstance().setValue(EMMET_LAST_WRAP_ABBREVIATIONS_KEY, abbreviation);
								field.addCurrentTextToHistory();
								balloon.hide(true);
							}
							break;
						case KeyEvent.VK_ESCAPE:
							balloon.hide(false);
							break;
					}
				}
			}
		});

		balloon.addListener(new JBPopupListener.Adapter()
		{
			@Override
			public void beforeShown(LightweightWindowEvent event)
			{
				field.setText(ApplicationPropertiesComponent.getInstance().getValue(EMMET_LAST_WRAP_ABBREVIATIONS_KEY, ""));
			}
		});
		balloon.show(EditorPopupHelper.getInstance().guessBestPopupLocation(callback.getEditor()), Balloon.Position.below);

		final IdeFocusManager focusManager = ApplicationIdeFocusManager.getInstance().getInstanceForProject(callback.getProject());
		focusManager.doWhenFocusSettlesDown(new Runnable()
		{
			@Override
			public void run()
			{
				focusManager.requestFocus(field, true);
				field.selectText();
			}
		});
	}

	private static boolean validateTemplateKey(
			@NotNull TextFieldWithHistory field, @Nullable Balloon balloon, @NotNull String abbreviation, @NotNull CustomTemplateCallback callback)
	{
		final boolean correct = checkTemplateKey(abbreviation, callback);
		field.getTextEditor().setBackground(correct ? LightColors.SLIGHTLY_GREEN : LightColors.RED);
		if(balloon != null && !balloon.isDisposed())
		{
			balloon.revalidate();
		}
		return correct;
	}

	static boolean checkTemplateKey(String inputString, CustomTemplateCallback callback)
	{
		ZenCodingGenerator generator = findApplicableDefaultGenerator(callback.getContext(), true);
		if(generator == null)
		{
			int offset = callback.getEditor().getCaretModel().getOffset();
			LOG.error("Emmet is disabled for context for file " + callback.getFileType().getName() + " in offset: " + offset,
					AttachmentFactoryUtil.createAttachment(callback.getEditor().getDocument()));
			return false;
		}
		return checkTemplateKey(inputString, callback, generator);
	}

	@Override
	public boolean isApplicable(@Nonnull CustomTemplateCallback callback, int offset, boolean wrapping)
	{
		PsiElement context = callback.getContext();
		if(!context.isValid())
		{
			return false;
		}
		PsiElement element = CustomTemplateCallback.getContext(callback.getFile(), offset);
		final ZenCodingGenerator applicableGenerator = findApplicableDefaultGenerator(element, wrapping);
		return applicableGenerator != null && applicableGenerator.isEnabled();
	}

	@Override
	public boolean hasCompletionItem(@NotNull CustomTemplateCallback callback, int offset)
	{
		PsiElement element = callback.getContext();
		if(!element.isValid())
		{
			return false;
		}
		final ZenCodingGenerator applicableGenerator = findApplicableDefaultGenerator(element, false);
		return applicableGenerator != null && applicableGenerator.isEnabled() && applicableGenerator.hasCompletionItem();
	}

	public static void doWrap(@NotNull final String abbreviation, @NotNull final CustomTemplateCallback callback)
	{
		final ZenCodingGenerator defaultGenerator = findApplicableDefaultGenerator(callback.getContext(), true);
		assert defaultGenerator != null;
		ApplicationManager.getApplication().runWriteAction(new Runnable()
		{
			@Override
			public void run()
			{
				CommandProcessor.getInstance().executeCommand(callback.getProject(), new Runnable()
				{
					@Override
					public void run()
					{
						callback.getEditor().getCaretModel().runForEachCaret(new CaretAction()
						{
							@Override
							public void perform(Caret caret)
							{
								String selectedText = callback.getEditor().getSelectionModel().getSelectedText();
								if(selectedText != null)
								{
									String selection = selectedText.trim();
									ZenCodingNode node = parse(abbreviation, callback, defaultGenerator, selection);
									assert node != null;
									PsiElement context = callback.getContext();
									ZenCodingGenerator generator = findApplicableGenerator(node, context, true);
									List<ZenCodingFilter> filters = getFilters(node, context);

									EditorModificationUtil.deleteSelectedText(callback.getEditor());
									PsiDocumentManager.getInstance(callback.getProject()).commitAllDocuments();

									expand(node, generator, filters, selection, callback, true, 50);
								}
							}
						});
					}
				}, CodeInsightBundle.message("insert.code.template.command"), null);
			}
		});
	}

	@Override
	@NotNull
	public String getTitle()
	{
		return EmmetLocalize.emmetTitle().getValue();
	}

	@Override
	public char getShortcut()
	{
		return (char) EmmetOptions.getInstance().getEmmetExpandShortcut();
	}

	@Override
	public String computeTemplateKey(@NotNull CustomTemplateCallback callback)
	{
		ZenCodingGenerator generator = findApplicableDefaultGenerator(callback.getContext(), false);
		if(generator == null)
		{
			return null;
		}
		return generator.computeTemplateKey(callback);
	}

	@Override
	public boolean supportsWrapping()
	{
		return true;
	}

	@Override
	public void addCompletions(CompletionParameters parameters, CompletionResultSet result)
	{
		if(!parameters.isAutoPopup())
		{
			return;
		}

		PsiFile file = parameters.getPosition().getContainingFile();
		int offset = parameters.getOffset();
		Editor editor = parameters.getEditor();

		ZenCodingGenerator generator = findApplicableDefaultGenerator(CustomTemplateCallback.getContext(file, offset), false);
		if(generator != null && generator.hasCompletionItem())
		{
			final Ref<Template> generatedTemplate = new Ref<Template>();
			final CustomTemplateCallback callback = new CustomTemplateCallback(editor, file)
			{
				@Override
				public void deleteTemplateKey(@NotNull String key)
				{
				}

				@Override
				public void startTemplate(@NotNull Template template, Map<String, String> predefinedValues, TemplateEditingListener listener)
				{
					if(!template.isDeactivated())
					{
						generatedTemplate.set(template);
					}
				}
			};

			final String templatePrefix = computeTemplateKeyWithoutContextChecking(callback);

			if(templatePrefix != null)
			{
				List<? extends Template> regularTemplates = TemplateManager.getInstance(callback.getProject()).listApplicableTemplates(TemplateActionContext.expanding(file, offset));
				boolean regularTemplateWithSamePrefixExists = !ContainerUtil.filter(regularTemplates, template -> templatePrefix.equals(template.getKey())).isEmpty();

				if(!regularTemplateWithSamePrefixExists)
				{
					// exclude perfect matches with existing templates because LiveTemplateCompletionContributor handles it
					final Collection<SingleLineEmmetFilter> extraFilters = new LinkedList<>(List.of(new SingleLineEmmetFilter()));
					expand(templatePrefix, callback, null, generator, extraFilters, false, 0);
					if(!generatedTemplate.isNull())
					{
						final Template template = generatedTemplate.get();
						template.setKey(templatePrefix);
						template.setDescription(template.getTemplateText());

						CompletionResultSet resultSet = result.withPrefixMatcher(result.getPrefixMatcher().cloneWithPrefix(templatePrefix));
						resultSet.restartCompletionOnPrefixChange(StandardPatterns.string().startsWith(templatePrefix));
						resultSet.addElement(new CustomLiveTemplateLookupElement(this, template.getKey(), template.getKey(), template.getDescription(), true, true));
					}
				}
			}
		}
	}
}
