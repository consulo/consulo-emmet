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
package com.intellij.codeInsight.template.emmet.nodes;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.CustomTemplateCallback;

/**
 * @author Eugene.Kudelevsky
 */
public class FilterNode extends ZenCodingNode
{
	private final ZenCodingNode myNode;
	private final String myFilter;

	public FilterNode(ZenCodingNode node, String filter)
	{
		myNode = node;
		myFilter = filter;
	}

	public ZenCodingNode getNode()
	{
		return myNode;
	}

	public String getFilter()
	{
		return myFilter;
	}

	@NotNull
	@Override
	public List<GenerationNode> expand(int numberInIteration, int totalIterations, String surroundedText, CustomTemplateCallback callback,
			boolean insertSurroundedTextAtTheEnd, GenerationNode parent)
	{
		return myNode.expand(numberInIteration, totalIterations, surroundedText, callback, insertSurroundedTextAtTheEnd, parent);
	}

	@Override
	public String toString()
	{
		return "Filter(" + myFilter + ")";
	}
}