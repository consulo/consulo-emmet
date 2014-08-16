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

import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.template.emmet.EmmetBundle;
import com.intellij.openapi.options.BeanConfigurable;
import com.intellij.openapi.options.Configurable;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
public class XmlEmmetConfigurable extends BeanConfigurable<XmlEmmetOptions> implements Configurable
{
	public XmlEmmetConfigurable()
	{
		super(XmlEmmetOptions.getInstance());
		checkBox("bemFilterEnabledByDefault", EmmetBundle.message("emmet.enable.bem.filter"));
	}

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
}
