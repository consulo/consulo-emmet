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

import com.intellij.codeInsight.template.emmet.generators.LoremGenerator;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import consulo.language.editor.template.CustomTemplateCallback;
import consulo.language.editor.template.Template;
import consulo.language.editor.template.TemplateBuilderFactory;
import consulo.util.lang.Couple;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * @author Eugene.Kudelevsky
 */
public class LoremNode extends ZenCodingNode
{
	private final int myWordsCount;
	private final LoremGenerator myLoremGenerator;

	public LoremNode(int wordsCount)
	{
		myLoremGenerator = new LoremGenerator();
		myWordsCount = wordsCount;
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
		final TemplateToken templateToken = new TemplateToken("", Collections.<Couple<String>>emptyList());
		final Template template = TemplateBuilderFactory.getInstance().createRawTemplate("", myLoremGenerator.generate(myWordsCount, numberInIteration <= 0), "");
		templateToken.setTemplate(template, callback);
		final GenerationNode node = new GenerationNode(templateToken, numberInIteration, totalIterations, surroundedText,
				insertSurroundedTextAtTheEnd, parent);
		return Collections.singletonList(node);
	}

	@Override
	public String toString()
	{
		return "Lorem(" + myWordsCount + ")";
	}
}
