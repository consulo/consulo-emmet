/*
 * Copyright 2013-2014 must-be.org
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

package com.intellij.application.options.emmet;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.template.emmet.EmmetBundle;
import com.intellij.openapi.options.ConfigurationException;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
public class XmlEmmetConfigurable implements EmmetOptionsProvider
{
	private JPanel myRoot;
	private JCheckBox myEnableBemFilter;

	public XmlEmmetConfigurable()
	{
		myRoot = new JPanel(new BorderLayout());

		myEnableBemFilter = new JCheckBox();
		myEnableBemFilter.setText(EmmetBundle.message("emmet.enable.bem.filter"));

		myRoot.add(myEnableBemFilter, BorderLayout.NORTH);
	}

	@Nullable
	@Override
	public JComponent createComponent()
	{
		return myRoot;
	}

	@Override
	public boolean isModified()
	{
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();
		return myEnableBemFilter.isSelected() != instance.isBemFilterEnabledByDefault();
	}

	@Override
	public void apply() throws ConfigurationException
	{
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();

		instance.setBemFilterEnabledByDefault(myEnableBemFilter.isSelected());
	}

	@Override
	public void reset()
	{
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();

		myEnableBemFilter.setSelected(instance.isBemFilterEnabledByDefault());
	}

	@Override
	public void disposeUIResources()
	{

	}

	@NotNull
	@Override
	public String getId()
	{
		return "xml.emmet.application.settings";
	}

	@Nullable
	@Override
	public Runnable enableSearch(String s)
	{
		return null;
	}

	@Nls
	@Override
	public String getDisplayName()
	{
		return "XML(HTML, etc)";
	}

	@Nullable
	@Override
	public String getHelpTopic()
	{
		return null;
	}
}
