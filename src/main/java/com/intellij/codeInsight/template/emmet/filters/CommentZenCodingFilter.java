/*
 * Copyright 2000-2010 JetBrains s.r.o.
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
package com.intellij.codeInsight.template.emmet.filters;

import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.PsiElement;
import consulo.util.lang.StringUtil;
import consulo.xml.lang.xml.XMLLanguage;
import consulo.xml.psi.xml.XmlDocument;
import consulo.xml.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Eugene.Kudelevsky
 */
@ExtensionImpl
public class CommentZenCodingFilter extends ZenCodingFilter
{
	private static String buildCommentString(@Nullable String classAttr, @Nullable String idAttr)
	{
		StringBuilder builder = new StringBuilder();
		if(!StringUtil.isEmpty(idAttr))
		{
			builder.append('#').append(idAttr);
		}
		if(!StringUtil.isEmpty(classAttr))
		{
			builder.append('.').append(classAttr);
		}
		return builder.toString();
	}

	@NotNull
	@Override
	public String filterText(@NotNull String text, @NotNull TemplateToken token)
	{
		XmlDocument document = token.getFile().getDocument();
		if(document != null)
		{
			XmlTag tag = document.getRootTag();
			if(tag != null)
			{
				String classAttr = tag.getAttributeValue("class");
				String idAttr = tag.getAttributeValue("id");
				if(!StringUtil.isEmpty(classAttr) || !StringUtil.isEmpty(idAttr))
				{
					String commentString = buildCommentString(classAttr, idAttr);
					return text + "\n<!-- /" + commentString + " -->";
				}
			}
		}
		return text;
	}

	@NotNull
	@Override
	public String getSuffix()
	{
		return "c";
	}

	@Override
	public boolean isMyContext(@NotNull PsiElement context)
	{
		return context.getLanguage() instanceof XMLLanguage;
	}
}
