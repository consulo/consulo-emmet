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

import com.intellij.codeInsight.template.emmet.EmmetParser;
import com.intellij.codeInsight.template.emmet.XmlEmmetParser;
import com.intellij.codeInsight.template.emmet.ZenCodingTemplate;
import com.intellij.codeInsight.template.emmet.tokens.TemplateToken;
import com.intellij.codeInsight.template.emmet.tokens.ZenCodingToken;
import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ExtensionAPI;
import consulo.codeEditor.Editor;
import consulo.component.extension.ExtensionPointName;
import consulo.language.ast.TokenSet;
import consulo.language.editor.template.CustomTemplateCallback;
import consulo.language.editor.template.Template;
import consulo.language.impl.psi.LeafPsiElement;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiErrorElement;
import consulo.language.psi.PsiWhiteSpace;
import consulo.xml.psi.xml.XmlTokenType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Eugene.Kudelevsky
 */
@ExtensionAPI(ComponentScope.APPLICATION)
public abstract class ZenCodingGenerator
{
	private static final ExtensionPointName<ZenCodingGenerator> EP_NAME = ExtensionPointName.create(ZenCodingGenerator.class);

	private static final TokenSet VALID_LEAF_TYPES = TokenSet.create(XmlTokenType.XML_DATA_CHARACTERS, XmlTokenType.XML_CHAR_ENTITY_REF);

	public abstract Template generateTemplate(@NotNull TemplateToken token, boolean hasChildren, @NotNull PsiElement context);

	@Nullable
	public Template createTemplateByKey(@NotNull String key)
	{
		return null;
	}

	public abstract boolean isMyContext(@NotNull PsiElement context, boolean wrapping);

	@Nullable
	public String getSuffix()
	{
		return null;
	}

	public abstract boolean isAppliedByDefault(@NotNull PsiElement context);

	public abstract boolean isEnabled();

	public static List<ZenCodingGenerator> getInstances()
	{
		List<ZenCodingGenerator> generators = new ArrayList<ZenCodingGenerator>();
		Collections.addAll(generators, XmlZenCodingGeneratorImpl.INSTANCE);
		Collections.addAll(generators, EP_NAME.getExtensions());
		return generators;
	}

	@Nullable
	public String computeTemplateKey(@NotNull CustomTemplateCallback callback)
	{
		Editor editor = callback.getEditor();
		final int currentOffset = editor.getCaretModel().getOffset();
		final CharSequence documentText = editor.getDocument().getCharsSequence();
		PsiElement element = callback.getContext();
		int line = editor.getCaretModel().getLogicalPosition().line;
		int lineStart = editor.getDocument().getLineStartOffset(line);
		int elementStart = -1;
		do
		{
			PsiElement e = element;
			while((e instanceof LeafPsiElement && VALID_LEAF_TYPES.contains(((LeafPsiElement) e).getElementType())) ||
					e instanceof PsiWhiteSpace || e instanceof PsiErrorElement)
			{
				elementStart = e.getTextRange().getStartOffset();
				e = e.getPrevSibling();
			}
			if(elementStart >= 0)
			{
				int startOffset = Math.max(elementStart, lineStart);
				String key = computeKey(startOffset, currentOffset, documentText);
				if(key != null)
				{
					while(key.length() > 0 && !ZenCodingTemplate.checkTemplateKey(key, callback, this))
					{
						key = key.substring(1);
					}
					if(key.length() > 0)
					{
						return key;
					}
				}
			}
			element = element.getParent();
		}
		while(element != null && elementStart > lineStart);
		return null;
	}

	@Nullable
	protected static String computeKey(int startOffset, int currentOffset, CharSequence documentText)
	{
		if(currentOffset < startOffset || startOffset > documentText.length() || currentOffset > documentText.length())
		{
			return null;
		}
		String s = documentText.subSequence(startOffset, currentOffset).toString();
		int index = 0;
		while(index < s.length() && Character.isWhitespace(s.charAt(index)))
		{
			index++;
		}
		String key = s.substring(index);
		int lastWhitespaceIndex = -1;
		int lastQuoteIndex = -1;
		int lastApostropheIndex = -1;
		boolean inBrackets = false;
		int bracesStack = 0;

		for(int i = 0; i < key.length(); i++)
		{
			char c = key.charAt(i);
			if(lastQuoteIndex >= 0 || lastApostropheIndex >= 0)
			{
				if(c == '"')
				{
					lastQuoteIndex = -1;
				}
				else if(c == '\'')
				{
					lastApostropheIndex = -1;
				}
			}
			else if(Character.isWhitespace(c))
			{
				lastWhitespaceIndex = i;
			}
			else if(c == '"')
			{
				lastQuoteIndex = i;
			}
			else if(c == '\'')
			{
				lastApostropheIndex = i;
			}
			else if(c == '[')
			{
				inBrackets = true;
			}
			else if(c == ']' && inBrackets)
			{
				lastWhitespaceIndex = -1;
				inBrackets = false;
			}
			else if(c == '{')
			{
				bracesStack++;
			}
			else if(c == '}' && bracesStack > 0)
			{
				bracesStack--;
				if(bracesStack == 0)
				{
					lastWhitespaceIndex = -1;
				}
			}
		}
		if(lastQuoteIndex >= 0 || lastApostropheIndex >= 0)
		{
			int max = Math.max(lastQuoteIndex, lastApostropheIndex);
			return max < key.length() - 1 ? key.substring(max) : null;
		}
		if(lastWhitespaceIndex >= 0 && lastWhitespaceIndex < key.length() - 1)
		{
			return key.substring(lastWhitespaceIndex + 1);
		}
		return key;
	}

	@NotNull
	public EmmetParser createParser(List<ZenCodingToken> tokens, CustomTemplateCallback callback, ZenCodingGenerator generator,
			boolean surroundWithTemplate)
	{
		return new XmlEmmetParser(tokens, callback, generator, surroundWithTemplate);
	}

	@javax.annotation.Nullable
	public String getConfigurableId()
	{
		return null;
	}

	public boolean hasCompletionItem()
	{
		return false;
	}
}
