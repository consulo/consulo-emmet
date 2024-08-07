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
package com.intellij.codeInsight.template.emmet.nodes;

import com.intellij.codeInsight.template.emmet.ZenCodingUtil;
import com.intellij.codeInsight.template.emmet.filters.SingleLineEmmetFilter;
import com.intellij.codeInsight.template.emmet.filters.ZenCodingFilter;
import com.intellij.codeInsight.template.emmet.generators.XmlZenCodingGenerator;
import com.intellij.codeInsight.template.emmet.generators.XmlZenCodingGeneratorImpl;
import com.intellij.codeInsight.template.emmet.generators.ZenCodingGenerator;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import com.intellij.xml.util.HtmlUtil;
import consulo.codeEditor.Editor;
import consulo.document.Document;
import consulo.document.DocumentWindow;
import consulo.language.codeStyle.CodeStyleSettings;
import consulo.language.codeStyle.CodeStyleSettingsManager;
import consulo.language.editor.template.CustomTemplateCallback;
import consulo.language.editor.template.LiveTemplateBuilder;
import consulo.language.editor.template.Template;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.project.Project;
import consulo.undoRedo.util.UndoConstants;
import consulo.util.dataholder.UserDataHolderBase;
import consulo.util.lang.Couple;
import consulo.util.lang.LocalTimeCounter;
import consulo.util.lang.StringUtil;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.ide.highlighter.XmlFileType;
import consulo.xml.lang.xml.XMLLanguage;
import consulo.xml.psi.XmlElementFactory;
import consulo.xml.psi.xml.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author Eugene.Kudelevsky
 */
public class GenerationNode extends UserDataHolderBase
{
	private final TemplateToken myTemplateToken;
	private final List<GenerationNode> myChildren = new ArrayList<>();
	private final int myNumberInIteration;
	private final int myTotalIterations;
	private String mySurroundedText;
	private final boolean myInsertSurroundedTextAtTheEnd;

	private final boolean myInsertNewLineBetweenNodes;

	private GenerationNode myParent;
	private boolean myContainsSurroundedTextMarker = false;

	public GenerationNode(
			TemplateToken templateToken,
			int numberInIteration,
			int totalIterations,
			String surroundedText,
			boolean insertSurroundedTextAtTheEnd,
			GenerationNode parent)
	{
		this(templateToken, numberInIteration, totalIterations, surroundedText, insertSurroundedTextAtTheEnd, parent, false);
	}


	public GenerationNode(
			TemplateToken templateToken,
			int numberInIteration,
			int totalIterations,
			String surroundedText,
			boolean insertSurroundedTextAtTheEnd,
			GenerationNode parent,
			boolean insertNewLineBetweenNodes)
	{
		myTemplateToken = templateToken;
		myNumberInIteration = numberInIteration;
		myTotalIterations = totalIterations;
		mySurroundedText = surroundedText;
		myInsertSurroundedTextAtTheEnd = insertSurroundedTextAtTheEnd;
		myInsertNewLineBetweenNodes = insertNewLineBetweenNodes;
		if(parent != null)
		{
			parent.addChild(this);
		}
	}

	public boolean isInsertNewLineBetweenNodes()
	{
		return myInsertNewLineBetweenNodes;
	}

	public List<GenerationNode> getChildren()
	{
		return myChildren;
	}

	public void addChild(GenerationNode child)
	{
		child.setParent(this);
		myChildren.add(child);
	}

	public void addChildren(Collection<GenerationNode> children)
	{
		for(GenerationNode child : children)
		{
			addChild(child);
		}
	}

	public boolean isLeaf()
	{
		return myChildren.size() == 0;
	}

	private boolean isBlockTag()
	{
		if(myTemplateToken != null)
		{
			XmlFile xmlFile = myTemplateToken.getFile();
			XmlDocument document = xmlFile.getDocument();
			if(document != null)
			{
				XmlTag tag = document.getRootTag();
				if(tag != null)
				{
					return HtmlUtil.isHtmlBlockTagL(tag.getName());
				}
			}
		}
		return false;
	}

	@NotNull
	public Template generate(
			@NotNull CustomTemplateCallback callback,
			@Nullable ZenCodingGenerator generator,
			@NotNull Collection<ZenCodingFilter> filters,
			boolean insertSurroundedText,
			int segmentsLimit)
	{
		myContainsSurroundedTextMarker = !(insertSurroundedText && myInsertSurroundedTextAtTheEnd);

		GenerationNode generationNode = this;
		if(generationNode != this)
		{
			return generationNode.generate(callback, generator, Collections.<ZenCodingFilter>emptyList(), insertSurroundedText, segmentsLimit);
		}

		boolean shouldNotReformatTemplate = false;
		boolean oneLineTemplateExpanding = false;
		for(ZenCodingFilter filter : filters)
		{
			generationNode = filter.filterNode(generationNode);
			if(filter instanceof SingleLineEmmetFilter)
			{
				shouldNotReformatTemplate = true;
				oneLineTemplateExpanding = true;
			}
		}

		CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(callback.getProject());
		String indentStr;
		if(callback.isInInjectedFragment())
		{
			Editor editor = callback.getEditor();
			Document document = editor.getDocument();
			if(document instanceof DocumentWindow && ((DocumentWindow) document).isOneLine())
			{
				/*
				 * If document is one-line that in the moment of inserting text,
				 * new line chars will be filtered (see DocumentWindowImpl#insertString).
				 * So in this case we should filter text by SingleLineAvoid in order to avoid
				 * inconsistency of template segments.
				 */
				oneLineTemplateExpanding = true;
				filters.add(new SingleLineEmmetFilter());
			}
			indentStr = "";
		}
		else if(settings.useTabCharacter(callback.getFileType()))
		{
			indentStr = "\t";
		}
		else
		{
			int tabSize = settings.getTabSize(callback.getFileType());
			indentStr = StringUtil.repeatSymbol(' ', tabSize);
		}

		LiveTemplateBuilder builder = new LiveTemplateBuilder(segmentsLimit);
		int end = -1;
		boolean hasChildren = myChildren.size() > 0;

		Template parentTemplate;
		Map<String, String> predefinedValues;
		if(myTemplateToken instanceof TemplateToken && generator instanceof XmlZenCodingGenerator)
		{
			TemplateToken xmlTemplateToken = myTemplateToken;
			List<Couple<String>> attr2value = new ArrayList<Couple<String>>(xmlTemplateToken.getAttribute2Value());
			parentTemplate = invokeXmlTemplate(xmlTemplateToken, callback, generator, hasChildren, attr2value);
			predefinedValues = buildPredefinedValues(attr2value, (XmlZenCodingGenerator) generator, hasChildren);
		}
		else
		{
			parentTemplate = invokeTemplate(myTemplateToken, hasChildren, callback, generator);
			predefinedValues = null;
		}

		String s = parentTemplate.getString();
		for(ZenCodingFilter filter : filters)
		{
			s = filter.filterText(s, myTemplateToken);
		}
		parentTemplate = parentTemplate.copy();
		parentTemplate.setString(s);

		final String txt = hasChildren || myContainsSurroundedTextMarker ? null : mySurroundedText;
		parentTemplate = expandTemplate(parentTemplate, predefinedValues, txt, segmentsLimit);

		int offset = builder.insertTemplate(0, parentTemplate, null);
		int newOffset = gotoChild(callback.getProject(), builder.getText(), offset, 0, builder.length());
		if(offset < builder.length() && newOffset != offset)
		{
			end = offset;
		}
		offset = newOffset;
		if(end == -1 && offset < builder.length() && myChildren.size() == 0)
		{
			end = offset;
		}
		LiveTemplateBuilder.Marker marker = offset < builder.length() ? builder.createMarker(offset) : null;

		//noinspection ForLoopReplaceableByForEach
		for(int i = 0, myChildrenSize = myChildren.size(); i < myChildrenSize; i++)
		{
			GenerationNode child = myChildren.get(i);
			Template childTemplate = child.generate(callback, generator, filters, !myContainsSurroundedTextMarker, segmentsLimit);

			boolean blockTag = child.isBlockTag();

			if(!oneLineTemplateExpanding && blockTag && !isNewLineBefore(builder.getText(), offset))
			{
				builder.insertText(offset, "\n" + indentStr, false);
				offset += indentStr.length() + 1;
			}

			int e = builder.insertTemplate(offset, childTemplate, null);
			offset = marker != null ? marker.getEndOffset() : builder.length();

			if(!oneLineTemplateExpanding && ((blockTag && !isNewLineAfter(builder.getText(), offset)) || myInsertNewLineBetweenNodes))
			{
				builder.insertText(offset, "\n" + indentStr, false);
				offset += indentStr.length() + 1;
			}

			if(end == -1 && e < offset)
			{
				end = e;
			}
		}
		if(shouldNotReformatTemplate)
		{
			builder.setIsToReformat(false);
		}
		return builder.buildTemplate();
	}

	private static Template invokeTemplate(
			TemplateToken token, boolean hasChildren, final CustomTemplateCallback callback, @Nullable ZenCodingGenerator generator)
	{
		Template template = token.getTemplate();
		if(generator != null)
		{
			assert template != null;
			template = generator.generateTemplate(token, hasChildren, callback.getContext());
			removeVariablesWhichHasNoSegment(template);
		}

		return template;
	}

	private Template invokeXmlTemplate(
			final TemplateToken token,
			CustomTemplateCallback callback,
			@Nullable ZenCodingGenerator generator,
			final boolean hasChildren,
			final List<Couple<String>> attr2value)
	{
	/*assert generator == null || generator instanceof XmlZenCodingGenerator :
	  "The generator cannot process TemplateToken because it doesn't inherit XmlZenCodingGenerator";*/

		Template template = token.getTemplate();
		assert template != null;

		final XmlFile xmlFile = token.getFile();
		PsiFileFactory fileFactory = PsiFileFactory.getInstance(xmlFile.getProject());
		XmlFile dummyFile = (XmlFile) fileFactory.createFileFromText("dummy.xml", XmlFileType.INSTANCE, xmlFile.getText());
		final XmlTag tag = dummyFile.getRootTag();
		if(tag != null)
		{
			for(Couple<String> pair : attr2value)
			{
				if(StringUtil.isEmpty(pair.second))
				{
					template.addVariable(prepareVariableName(pair.first), "", "", true);
				}
			}
			XmlTag tag1 = hasChildren ? expandEmptyTagIfNecessary(tag) : tag;
			setAttributeValues(tag1, attr2value);
			XmlFile physicalFile = (XmlFile) fileFactory.createFileFromText("dummy.xml", XmlFileType.INSTANCE, tag1.getContainingFile().getText(),
					LocalTimeCounter.currentTime(), true);
			VirtualFile vFile = physicalFile.getVirtualFile();
			if(vFile != null)
			{
				vFile.putUserData(UndoConstants.DONT_RECORD_UNDO, Boolean.TRUE);
			}
			token.setFile(physicalFile);
		}
		ZenCodingGenerator zenCodingGenerator = generator != null ? generator : XmlZenCodingGeneratorImpl.INSTANCE;
		template = zenCodingGenerator.generateTemplate(token, hasChildren, callback.getContext());
		removeVariablesWhichHasNoSegment(template);
		return template;
	}

	private static String prepareVariableName(@NotNull String attributeName)
	{
		return StringUtil.replaceChar(attributeName, '-', '_');
	}

	@NotNull
	private static Template expandTemplate(
			@NotNull Template template, Map<String, String> predefinedVarValues, String surroundedText, int segmentsLimit)
	{
		LiveTemplateBuilder builder = new LiveTemplateBuilder(segmentsLimit);
		if(predefinedVarValues == null && surroundedText == null)
		{
			return template;
		}
		int offset = builder.insertTemplate(0, template, predefinedVarValues);
		if(surroundedText != null)
		{
			builder.insertText(offset, surroundedText, true);
			builder.setIsToReformat(true);
		}
		return builder.buildTemplate();
	}

	@NotNull
	private static XmlTag expandEmptyTagIfNecessary(@NotNull XmlTag tag)
	{
		StringBuilder builder = new StringBuilder();
		boolean flag = false;

		for(PsiElement child : tag.getChildren())
		{
			if(child instanceof XmlToken && XmlTokenType.XML_EMPTY_ELEMENT_END.equals(((XmlToken) child).getTokenType()))
			{
				flag = true;
				break;
			}
			builder.append(child.getText());
		}

		if(flag)
		{
			builder.append("></").append(tag.getName()).append('>');
			return XmlElementFactory.getInstance(tag.getProject()).createTagFromText(builder.toString(), XMLLanguage.INSTANCE);
		}
		return tag;
	}

	private static int gotoChild(Project project, CharSequence text, int offset, int start, int end)
	{
		PsiFile file = PsiFileFactory.getInstance(project).createFileFromText("dummy.xml", XmlFileType.INSTANCE, text, LocalTimeCounter.currentTime(),
				false);

		PsiElement element = file.findElementAt(offset);
		if(offset < end && element instanceof XmlToken && ((XmlToken) element).getTokenType() == XmlTokenType.XML_END_TAG_START)
		{
			return offset;
		}

		int newOffset = -1;
		XmlTag tag = PsiTreeUtil.findElementOfClassAtRange(file, start, end, XmlTag.class);
		if(tag != null)
		{
			for(PsiElement child : tag.getChildren())
			{
				if(child instanceof XmlToken && ((XmlToken) child).getTokenType() == XmlTokenType.XML_END_TAG_START)
				{
					newOffset = child.getTextOffset();
				}
			}
		}

		if(newOffset >= 0)
		{
			return newOffset;
		}

		return offset;
	}

	private static void removeVariablesWhichHasNoSegment(Template template)
	{
		Set<String> segments = new HashSet<String>();
		for(int i = 0; i < template.getSegmentsCount(); i++)
		{
			segments.add(template.getSegmentName(i));
		}
		for(int i = template.getVariableCount() - 1; i >= 0; i--)
		{
			String varName = template.getVariableNameAt(i);
			if(!segments.contains(varName))
			{
				template.removeVariable(i);
			}
			else
			{
				segments.remove(varName);
			}
		}
	}

	@Nullable
	private Map<String, String> buildPredefinedValues(
			List<Couple<String>> attribute2value, @Nullable XmlZenCodingGenerator generator, boolean hasChildren)
	{
		if(generator == null)
		{
			return Collections.emptyMap();
		}

		for(Couple<String> pair : attribute2value)
		{
			if(ZenCodingUtil.containsSurroundedTextMarker(pair.second))
			{
				myContainsSurroundedTextMarker = true;
				break;
			}
		}

		String attributes = generator.buildAttributesString(attribute2value, hasChildren, myNumberInIteration, myTotalIterations, mySurroundedText);
		attributes = attributes.length() > 0 ? ' ' + attributes : null;
		Map<String, String> predefinedValues = null;
		if(attributes != null)
		{
			predefinedValues = new HashMap<String, String>();
			predefinedValues.put(TemplateToken.ATTRS, attributes);
		}
		return predefinedValues;
	}

	private void setAttributeValues(XmlTag tag, List<Couple<String>> attr2value)
	{
		for(Iterator<Couple<String>> iterator = attr2value.iterator(); iterator.hasNext(); )
		{
			Couple<String> pair = iterator.next();
			if(tag.getAttribute(pair.first) != null)
			{
				if(ZenCodingUtil.containsSurroundedTextMarker(pair.second))
				{
					myContainsSurroundedTextMarker = true;
				}
				tag.setAttribute(pair.first, StringUtil.isEmpty(pair.second) ? "$" + prepareVariableName(pair.first) + "$" : ZenCodingUtil
						.getValue(pair.second, myNumberInIteration, myTotalIterations, mySurroundedText));
				iterator.remove();
			}
		}
	}

	private static boolean isNewLineBefore(CharSequence text, int offset)
	{
		int i = offset - 1;
		while(i >= 0 && Character.isWhitespace(text.charAt(i)))
		{
			if(text.charAt(i) == '\n')
			{
				return true;
			}
			i--;
		}
		return i < 0;
	}

	private static boolean isNewLineAfter(CharSequence text, int offset)
	{
		int i = offset;
		while(i < text.length() && Character.isWhitespace(text.charAt(i)))
		{
			if(text.charAt(i) == '\n')
			{
				return true;
			}
			i++;
		}
		return i == text.length();
	}

	public TemplateToken getTemplateToken()
	{
		return myTemplateToken;
	}

	public String getSurroundedText()
	{
		return mySurroundedText;
	}

	public void setSurroundedText(String surroundedText)
	{
		mySurroundedText = surroundedText;
	}

	public GenerationNode getParent()
	{
		return myParent;
	}

	public void setParent(GenerationNode parent)
	{
		myParent = parent;
	}
}
