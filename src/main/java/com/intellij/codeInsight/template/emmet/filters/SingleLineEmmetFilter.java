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
package com.intellij.codeInsight.template.emmet.filters;

import com.intellij.codeInsight.template.emmet.nodes.GenerationNode;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.Template;
import consulo.language.psi.PsiElement;
import consulo.util.lang.StringUtil;
import consulo.xml.lang.xml.XMLLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * User: zolotov
 * Date: 2/4/13
 */
@ExtensionImpl
public class SingleLineEmmetFilter extends ZenCodingFilter
{
	@NotNull
	@Override
	public String getSuffix()
	{
		return "s";
	}

	@NotNull
	@Override
	public String filterText(@NotNull String text, @NotNull TemplateToken token)
	{
		return StringUtil.replace(text, "\n", "");
	}

	@NotNull
	@Override
	public GenerationNode filterNode(@NotNull GenerationNode node)
	{
		Template template = node.getTemplateToken().getTemplate();
		if(template != null)
		{
			template.setToReformat(false);
		}
		for(GenerationNode generationNode : node.getChildren())
		{
			filterNode(generationNode);
		}
		return node;
	}

	@Override
	public boolean isMyContext(@NotNull PsiElement context)
	{
		return context.getLanguage() instanceof XMLLanguage;
	}
}
