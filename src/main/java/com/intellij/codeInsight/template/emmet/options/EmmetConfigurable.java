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
package com.intellij.codeInsight.template.emmet.options;

import consulo.annotation.component.ExtensionImpl;
import consulo.configurable.ApplicationConfigurable;
import consulo.configurable.Configurable;
import consulo.configurable.SimpleConfigurable;
import consulo.configurable.StandardConfigurableIds;
import consulo.disposer.Disposable;
import consulo.emmet.localize.EmmetLocalize;
import consulo.language.editor.CodeInsightBundle;
import consulo.ui.CheckBox;
import consulo.ui.ComboBox;
import consulo.ui.Component;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.border.BorderPosition;
import consulo.ui.border.BorderStyle;
import consulo.ui.layout.Layout;
import consulo.ui.layout.VerticalLayout;
import consulo.ui.util.LabeledBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.function.Supplier;

/**
 * User: zolotov
 * Date: 2/20/13
 */
@ExtensionImpl
public class EmmetConfigurable extends SimpleConfigurable<EmmetConfigurable.Panel> implements Configurable, ApplicationConfigurable
{
	public static class Panel implements Supplier<Layout>
	{
		private final CheckBox myEnableEmmetCheckBox;
		private final ComboBox<Character> myEmmetExpandShortcutCombo;
		private final VerticalLayout myLayout;

		@RequiredUIAccess
		public Panel()
		{
			myLayout = VerticalLayout.create();
			myEnableEmmetCheckBox = CheckBox.create(EmmetLocalize.zenCodingEnableLabel());
			myLayout.add(myEnableEmmetCheckBox);

			ComboBox.Builder<Character> emmetExpandBox = ComboBox.builder();
			emmetExpandBox.add('\t', CodeInsightBundle.message("template.shortcut.tab"));
			emmetExpandBox.add('\n', CodeInsightBundle.message("template.shortcut.enter"));
			emmetExpandBox.add(' ', CodeInsightBundle.message("template.shortcut.space"));

			myEmmetExpandShortcutCombo = emmetExpandBox.build();
			myEmmetExpandShortcutCombo.selectFirst();

			Component labelExpand = LabeledBuilder.sided(EmmetLocalize.emmetExpandAbbreviationWith(), myEmmetExpandShortcutCombo);
			labelExpand.addBorder(BorderPosition.LEFT, BorderStyle.EMPTY, 16);
			myLayout.add(labelExpand);

			myEnableEmmetCheckBox.addValueListener(valueEvent -> myEmmetExpandShortcutCombo.setEnabled(myEnableEmmetCheckBox.getValueOrError()));
		}

		@Nonnull
		@Override
		public Layout get()
		{
			return myLayout;
		}
	}

	private final Provider<EmmetOptions> myEmmetOptionsProvider;

	@Inject
	public EmmetConfigurable(Provider<EmmetOptions> emmetOptionsProvider)
	{
		myEmmetOptionsProvider = emmetOptionsProvider;
	}

	@RequiredUIAccess
	@Nonnull
	@Override
	protected Panel createPanel(@Nonnull Disposable disposable)
	{
		return new Panel();
	}

	@RequiredUIAccess
	@Override
	public boolean isModified(@Nonnull Panel panel)
	{
		EmmetOptions emmetOptions = myEmmetOptionsProvider.get();
		return emmetOptions.isEmmetEnabled() != panel.myEnableEmmetCheckBox.getValueOrError() ||
				emmetOptions.getEmmetExpandShortcut() != panel.myEmmetExpandShortcutCombo.getValueOrError();
	}

	@RequiredUIAccess
	@Override
	public void apply(@Nonnull Panel panel)
	{
		EmmetOptions emmetOptions = myEmmetOptionsProvider.get();
		emmetOptions.setEmmetEnabled(panel.myEnableEmmetCheckBox.getValueOrError());
		emmetOptions.setEmmetExpandShortcut(panel.myEmmetExpandShortcutCombo.getValueOrError());
	}

	@RequiredUIAccess
	@Override
	public void reset(@Nonnull Panel panel)
	{
		EmmetOptions emmetOptions = myEmmetOptionsProvider.get();
		panel.myEnableEmmetCheckBox.setValue(emmetOptions.isEmmetEnabled());
		panel.myEmmetExpandShortcutCombo.setEnabled(emmetOptions.isEmmetEnabled());

		char shortcut = (char) emmetOptions.getEmmetExpandShortcut();
		panel.myEmmetExpandShortcutCombo.setValue(shortcut);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "editor.emmet";
	}

	@Nullable
	@Override
	public String getParentId()
	{
		return StandardConfigurableIds.EDITOR_GROUP;
	}

	@Nonnull
	@Override
	public String getDisplayName()
	{
		return "Emmet";
	}
}
