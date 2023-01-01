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
package com.intellij.codeInsight.template.emmet.generators;

import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import com.intellij.xml.util.HtmlUtil;
import consulo.language.editor.template.Template;
import consulo.language.editor.template.TemplateBuilderFactory;
import consulo.language.psi.PsiElement;
import consulo.util.lang.Couple;
import consulo.xml.psi.xml.XmlDocument;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


/**
 * @author Eugene.Kudelevsky
 */
public abstract class XmlZenCodingGenerator extends ZenCodingGenerator
{
	@Override
	public Template generateTemplate(@NotNull TemplateToken token, boolean hasChildren, @NotNull PsiElement context)
	{
		String s = toString(token, hasChildren, context);
		Template template = token.getTemplate().copy();
		template.setString(s);
		return template;
	}

	@Override
	public Template createTemplateByKey(@NotNull String key)
	{
		StringBuilder builder = new StringBuilder("<");
		builder.append(key).append('>');
		if(!HtmlUtil.isSingleHtmlTag(key))
		{
			builder.append("$END$</").append(key).append('>');
		}
		return TemplateBuilderFactory.getInstance().createRawTemplate("", builder.toString(), "");
	}

	@NotNull
	private String toString(@NotNull TemplateToken token, boolean hasChildren, @NotNull PsiElement context)
	{
		XmlFile file = token.getFile();
		XmlDocument document = file.getDocument();
		if(document != null)
		{
			XmlTag tag = document.getRootTag();
			if(tag != null)
			{
				return toString(tag, token.getAttribute2Value(), hasChildren, context);
			}
		}
		return file.getText();
	}

	public abstract String toString(
			@NotNull XmlTag tag, @NotNull List<Couple<String>> attribute2Value, boolean hasChildren, @NotNull PsiElement context);

	@NotNull
	public abstract String buildAttributesString(
			@NotNull List<Couple<String>> attribute2value,
			boolean hasChildren,
			int numberInIteration,
			int totalIterations,
			@Nullable String surroundedText);

	@Override
	public abstract boolean isMyContext(@NotNull PsiElement context, boolean wrapping);
}
