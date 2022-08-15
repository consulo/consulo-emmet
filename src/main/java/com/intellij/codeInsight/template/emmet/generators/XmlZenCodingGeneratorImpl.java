/*
 * Copyright 2000-2013 JetBrains s.r.o.
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
package com.intellij.codeInsight.template.emmet.generators;

import com.intellij.codeInsight.template.emmet.options.EmmetOptions;
import com.intellij.codeInsight.template.emmet.ZenCodingUtil;
import consulo.application.ApplicationManager;
import consulo.document.Document;
import consulo.document.FileDocumentManager;
import consulo.language.Language;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiDocumentManager;
import consulo.language.psi.PsiElement;
import consulo.language.psi.SmartPointerManager;
import consulo.language.psi.SmartPsiElementPointer;
import consulo.project.Project;
import consulo.util.lang.Couple;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;
import consulo.xml.codeInsight.template.HtmlTextContextType;
import consulo.xml.ide.highlighter.HtmlFileType;
import consulo.xml.ide.highlighter.XHtmlFileType;
import consulo.xml.lang.xml.XMLLanguage;
import consulo.xml.psi.XmlRecursiveElementVisitor;
import consulo.xml.psi.xml.XmlChildRole;
import consulo.xml.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eugene.Kudelevsky
 */
public class XmlZenCodingGeneratorImpl extends XmlZenCodingGenerator
{
	public static final XmlZenCodingGeneratorImpl INSTANCE = new XmlZenCodingGeneratorImpl();

	private static boolean isTrueXml(FileType type)
	{
		return type == HtmlFileType.INSTANCE || type == XHtmlFileType.INSTANCE;
	}

	@Override
	@NotNull
	public String toString(
			@NotNull XmlTag tag, @NotNull List<Couple<String>> attribute2Value, boolean hasChildren, @NotNull PsiElement context)
	{
		FileType fileType = context.getContainingFile().getFileType();
		if(isTrueXml(fileType))
		{
			closeUnclosingTags(tag);
		}
		return tag.getContainingFile().getText();
	}

	@Override
	@NotNull
	public String buildAttributesString(
			@NotNull List<Couple<String>> attribute2value,
			boolean hasChildren,
			int numberInIteration,
			int totalIterations,
			@Nullable String surroundedText)
	{
		StringBuilder result = new StringBuilder();
		for(Iterator<Couple<String>> it = attribute2value.iterator(); it.hasNext(); )
		{
			Couple<String> pair = it.next();
			String name = pair.first;
			String value = ZenCodingUtil.getValue(pair.second, numberInIteration, totalIterations, surroundedText);
			result.append(getAttributeString(name, value));
			if(it.hasNext())
			{
				result.append(' ');
			}
		}
		return result.toString();
	}

	@Override
	public boolean isMyContext(@NotNull PsiElement context, boolean wrapping)
	{
		return isMyLanguage(context.getLanguage()) && (wrapping || HtmlTextContextType.isInContext(context));
	}

	protected boolean isMyLanguage(Language language)
	{
		return language instanceof XMLLanguage;
	}

	@Override
	public String getSuffix()
	{
		return "html";
	}

	@Override
	public boolean isEnabled()
	{
		return EmmetOptions.getInstance().isEmmetEnabled();
	}

	@Override
	public boolean isAppliedByDefault(@NotNull PsiElement context)
	{
		return true;
	}

	private static String getAttributeString(String name, String value)
	{
		return name + "=\"" + value + '"';
	}

	@SuppressWarnings({"ConstantConditions"})
	private static void closeUnclosingTags(@NotNull XmlTag root)
	{
		final List<SmartPsiElementPointer<XmlTag>> tagToClose = new ArrayList<SmartPsiElementPointer<XmlTag>>();
		Project project = root.getProject();
		final SmartPointerManager pointerManager = SmartPointerManager.getInstance(project);
		root.accept(new XmlRecursiveElementVisitor()
		{
			@Override
			public void visitXmlTag(final XmlTag tag)
			{
				if(!isTagClosed(tag))
				{
					tagToClose.add(pointerManager.createSmartPsiElementPointer(tag));
				}
			}
		});
		final PsiDocumentManager documentManager = PsiDocumentManager.getInstance(project);
		for(final SmartPsiElementPointer<XmlTag> pointer : tagToClose)
		{
			final XmlTag tag = pointer.getElement();
			if(tag != null)
			{
				final ASTNode child = XmlChildRole.START_TAG_END_FINDER.findChild(tag.getNode());
				if(child != null)
				{
					final int offset = child.getTextRange().getStartOffset();
					VirtualFile file = tag.getContainingFile().getVirtualFile();
					if(file != null)
					{
						final Document document = FileDocumentManager.getInstance().getDocument(file);
						documentManager.doPostponedOperationsAndUnblockDocument(document);
						ApplicationManager.getApplication().runWriteAction(new Runnable()
						{
							@Override
							public void run()
							{
								document.replaceString(offset, tag.getTextRange().getEndOffset(), "/>");
								documentManager.commitDocument(document);
							}
						});
					}
				}
			}
		}
	}

	private static boolean isTagClosed(@NotNull XmlTag tag)
	{
		ASTNode node = tag.getNode();
		assert node != null;
		final ASTNode emptyTagEnd = XmlChildRole.EMPTY_TAG_END_FINDER.findChild(node);
		final ASTNode endTagEnd = XmlChildRole.CLOSING_TAG_START_FINDER.findChild(node);
		return emptyTagEnd != null || endTagEnd != null;
	}

	@javax.annotation.Nullable
	@Override
	public String getConfigurableId()
	{
		return "editor.emmet.xml";
	}
}
