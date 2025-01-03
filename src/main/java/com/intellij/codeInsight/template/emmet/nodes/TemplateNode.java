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
package com.intellij.codeInsight.template.emmet.nodes;

import com.intellij.codeInsight.template.emmet.ZenCodingUtil;
import com.intellij.codeInsight.template.emmet.generators.ZenCodingGenerator;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import consulo.language.editor.template.CustomTemplateCallback;
import consulo.language.editor.template.Template;
import consulo.util.lang.Couple;
import consulo.util.lang.StringUtil;
import org.jetbrains.annotations.NotNull;

import jakarta.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eugene.Kudelevsky
 */
public class TemplateNode extends ZenCodingNode
{
	private final TemplateToken myTemplateToken;
	@Nullable
	private final ZenCodingGenerator myGenerator;

	public TemplateNode(TemplateToken templateToken)
	{
		this(templateToken, null);
	}

	public TemplateNode(TemplateToken token, @Nullable ZenCodingGenerator generator)
	{
		myTemplateToken = token;
		myGenerator = generator;
	}

	public TemplateToken getTemplateToken()
	{
		return myTemplateToken;
	}

	@NotNull
	@Override
	public List<GenerationNode> expand(
			int numberInIteration,
			int totalIterations,
			String surroundedText,
			CustomTemplateCallback callback,
			boolean insertSurroundedTextAtTheEnd,
			GenerationNode parent)
	{
		TemplateToken templateToken = myTemplateToken;
		String templateKey = templateToken.getKey();
		if(myGenerator != null && StringUtil.containsChar(templateKey, '$') && callback.findApplicableTemplate(templateKey) == null)
		{
			String newTemplateKey = ZenCodingUtil.replaceMarkers(templateKey, numberInIteration, totalIterations, surroundedText);
			TemplateToken newTemplateToken = new TemplateToken(newTemplateKey, templateToken.getAttribute2Value());

			Template template = myGenerator.createTemplateByKey(newTemplateKey);
			if(template != null)
			{
				template.setDeactivated(true);
				newTemplateToken.setTemplate(template, callback);
				templateToken = newTemplateToken;
			}
		}

		GenerationNode node = new GenerationNode(templateToken, numberInIteration, totalIterations, surroundedText, insertSurroundedTextAtTheEnd,
				parent);
		return Arrays.asList(node);
	}

	@Override
	public String toString()
	{
		String result = myTemplateToken.getKey();
		List<Couple<String>> attributes = myTemplateToken.getAttribute2Value();
		if(!attributes.isEmpty())
		{
			result += "[" + StringUtil.join(myTemplateToken.getAttribute2Value(), ",") + "]";
		}
		return "Template(" + result + ")";
	}
}
