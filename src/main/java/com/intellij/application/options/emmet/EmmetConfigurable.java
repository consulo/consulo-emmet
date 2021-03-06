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
package com.intellij.application.options.emmet;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.codeInsight.template.impl.TemplateSettings;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.components.JBCheckBox;

/**
 * User: zolotov
 * Date: 2/20/13
 */
public class EmmetConfigurable implements Configurable
{
	private JBCheckBox myEnableEmmetJBCheckBox;
	private JComboBox myEmmetExpandShortcutCombo;

	private JPanel myPanel;

	private static final String SPACE = CodeInsightBundle.message("template.shortcut.space");
	private static final String TAB = CodeInsightBundle.message("template.shortcut.tab");
	private static final String ENTER = CodeInsightBundle.message("template.shortcut.enter");

	public EmmetConfigurable()
	{
		myEmmetExpandShortcutCombo.addItem(SPACE);
		myEmmetExpandShortcutCombo.addItem(TAB);
		myEmmetExpandShortcutCombo.addItem(ENTER);
	}

	private char getSelectedEmmetExpandShortcut()
	{
		Object selectedItem = myEmmetExpandShortcutCombo.getSelectedItem();
		if(TAB.equals(selectedItem))
		{
			return TemplateSettings.TAB_CHAR;
		}
		else if(ENTER.equals(selectedItem))
		{
			return TemplateSettings.ENTER_CHAR;
		}
		return TemplateSettings.SPACE_CHAR;
	}

	@Nls
	@Override
	public String getDisplayName()
	{
		return null;
	}

	@Nullable
	@Override
	public String getHelpTopic()
	{
		return null;
	}

	@Nullable
	@Override
	public JComponent createComponent()
	{
		return myPanel;
	}

	@Override
	public boolean isModified()
	{
		EmmetOptions emmetOptions = EmmetOptions.getInstance();
		return emmetOptions.isEmmetEnabled() != myEnableEmmetJBCheckBox.isSelected() || emmetOptions.getEmmetExpandShortcut() !=
				getSelectedEmmetExpandShortcut();
	}


	@Override
	public void apply() throws ConfigurationException
	{
		EmmetOptions emmetOptions = EmmetOptions.getInstance();
		emmetOptions.setEmmetEnabled(myEnableEmmetJBCheckBox.isSelected());
		emmetOptions.setEmmetExpandShortcut(getSelectedEmmetExpandShortcut());
	}

	@Override
	public void reset()
	{
		EmmetOptions emmetOptions = EmmetOptions.getInstance();
		myEnableEmmetJBCheckBox.setSelected(emmetOptions.isEmmetEnabled());
		myEmmetExpandShortcutCombo.setEnabled(emmetOptions.isEmmetEnabled());

		char shortcut = (char) emmetOptions.getEmmetExpandShortcut();
		if(shortcut == TemplateSettings.TAB_CHAR)
		{
			myEmmetExpandShortcutCombo.setSelectedItem(TAB);
		}
		else if(shortcut == TemplateSettings.ENTER_CHAR)
		{
			myEmmetExpandShortcutCombo.setSelectedItem(ENTER);
		}
		else
		{
			myEmmetExpandShortcutCombo.setSelectedItem(SPACE);
		}
	}

	@Override
	public void disposeUIResources()
	{

	}
}
