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

import com.intellij.openapi.options.Configurable;
import consulo.disposer.Disposable;
import consulo.emmet.localize.EmmetLocalize;
import consulo.options.SimpleConfigurableByProperties;
import consulo.ui.CheckBox;
import consulo.ui.Component;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.layout.VerticalLayout;
import jakarta.inject.Provider;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
public class XmlEmmetConfigurable extends SimpleConfigurableByProperties implements Configurable
{
	private final Provider<XmlEmmetOptions> myXmlEmmetOptionsProvider;

	public XmlEmmetConfigurable(Provider<XmlEmmetOptions> xmlEmmetOptionsProvider)
	{
		myXmlEmmetOptionsProvider = xmlEmmetOptionsProvider;
	}

	@RequiredUIAccess
	@Nonnull
	@Override
	protected Component createLayout(@Nonnull PropertyBuilder propertyBuilder, @Nonnull Disposable disposable)
	{
		XmlEmmetOptions xmlEmmetOptions = myXmlEmmetOptionsProvider.get();

		VerticalLayout rootLayout = VerticalLayout.create();

		CheckBox enableBemFilter = CheckBox.create(EmmetLocalize.emmetEnableBemFilter());
		rootLayout.add(enableBemFilter);
		propertyBuilder.add(enableBemFilter, xmlEmmetOptions::isBemFilterEnabledByDefault, xmlEmmetOptions::setBemFilterEnabledByDefault);

		return rootLayout;
	}
}
