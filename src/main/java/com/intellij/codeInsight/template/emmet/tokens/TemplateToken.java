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
package com.intellij.codeInsight.template.emmet.tokens;

import consulo.language.editor.template.CustomTemplateCallback;
import consulo.language.editor.template.Template;
import consulo.language.psi.PsiFileFactory;
import consulo.undoRedo.util.UndoConstants;
import consulo.util.collection.ArrayUtil;
import consulo.util.lang.Couple;
import consulo.util.lang.LocalTimeCounter;
import consulo.virtualFileSystem.VirtualFile;
import consulo.xml.ide.highlighter.XmlFileType;
import consulo.xml.psi.XmlElementFactory;
import consulo.xml.psi.xml.XmlAttribute;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.psi.xml.XmlTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Eugene.Kudelevsky
 */
public class TemplateToken extends ZenCodingToken
{
	public static final String ATTRS = "ATTRS";
	public final static TemplateToken EMPTY_TEMPLATE_TOKEN = new TemplateToken("", new ArrayList<Couple<String>>());

	private final String myKey;
	private Template myTemplate;
	private final List<Couple<String>> myAttribute2Value;
	private XmlFile myFile;

	public TemplateToken(String key)
	{
		this(key, new ArrayList<Couple<String>>());
	}

	public TemplateToken(String key, List<Couple<String>> attribute2value)
	{
		myKey = key;
		myAttribute2Value = attribute2value;
	}

	public List<Couple<String>> getAttribute2Value()
	{
		return myAttribute2Value;
	}

	public XmlFile getFile()
	{
		return myFile;
	}

	public void setFile(XmlFile file)
	{
		myFile = file;
	}

	public String getKey()
	{
		return myKey;
	}

	public boolean setTemplate(Template template, CustomTemplateCallback callback)
	{
		myTemplate = template;
		final XmlFile xmlFile = parseXmlFileInTemplate(template, callback, getAttribute2Value());
		setFile(xmlFile);
		final XmlTag tag = xmlFile.getRootTag();
		if(getAttribute2Value().size() > 0 && tag == null)
		{
			return false;
		}
		return true;
	}


	private static boolean containsAttrsVar(Template template)
	{
		for(int i = 0; i < template.getVariableCount(); i++)
		{
			String varName = template.getVariableNameAt(i);
			if(ATTRS.equals(varName))
			{
				return true;
			}
		}
		return false;
	}

	@NotNull
	private static XmlFile parseXmlFileInTemplate( Template template, CustomTemplateCallback callback, List<Couple<String>> attributes)
	{
		XmlTag dummyRootTag = null;
		String templateString = template.getString();
		final PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(callback.getProject());
		if(!containsAttrsVar(template))
		{
			XmlFile dummyFile = (XmlFile) psiFileFactory.createFileFromText("dummy.xml", XmlFileType.INSTANCE, templateString);
			dummyRootTag = dummyFile.getRootTag();
			if(dummyRootTag != null)
			{
				addMissingAttributes(dummyRootTag, attributes);
			}
		}

		templateString = dummyRootTag != null ? dummyRootTag.getContainingFile().getText() : templateString;
		XmlFile xmlFile = (XmlFile) psiFileFactory.createFileFromText("dummy.xml", XmlFileType.INSTANCE, templateString, LocalTimeCounter.currentTime(),
				true);
		VirtualFile vFile = xmlFile.getVirtualFile();
		if(vFile != null)
		{
			vFile.putUserData(UndoConstants.DONT_RECORD_UNDO, Boolean.TRUE);
		}
		return xmlFile;
	}


	private static void addMissingAttributes(XmlTag tag, List<Couple<String>> value)
	{
		List<Couple<String>> attr2value = new ArrayList<Couple<String>>(value);
		for(Iterator<Couple<String>> iterator = attr2value.iterator(); iterator.hasNext(); )
		{
			Couple<String> pair = iterator.next();
			if(tag.getAttribute(pair.first) != null)
			{
				iterator.remove();
			}
		}
		addAttributesBefore(tag, attr2value);
	}

	private static void addAttributesBefore(XmlTag tag, List<Couple<String>> attr2value)
	{
		XmlAttribute firstAttribute = ArrayUtil.getFirstElement(tag.getAttributes());
		XmlElementFactory factory = XmlElementFactory.getInstance(tag.getProject());
		for(Couple<String> pair : attr2value)
		{
			XmlAttribute xmlAttribute = factory.createXmlAttribute(pair.first, "");
			if(firstAttribute != null)
			{
				tag.addBefore(xmlAttribute, firstAttribute);
			}
			else
			{
				tag.add(xmlAttribute);
			}
		}
	}

	@Nullable
	public Template getTemplate()
	{
		return myTemplate;
	}

	@Override
	public String toString()
	{
		return "TEMPLATE";
	}
}
