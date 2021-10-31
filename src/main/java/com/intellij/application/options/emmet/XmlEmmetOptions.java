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

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import jakarta.inject.Singleton;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
@Singleton
@State(name = "XmlEmmetOptions", storages = @Storage("emmet.xml"))
public class XmlEmmetOptions implements PersistentStateComponent<XmlEmmetOptions>
{
	@Nonnull
	public static XmlEmmetOptions getInstance()
	{
		return ServiceManager.getService(XmlEmmetOptions.class);
	}

	private boolean myBemFilterEnabledByDefault = false;

	public boolean isBemFilterEnabledByDefault()
	{
		return myBemFilterEnabledByDefault;
	}

	public void setBemFilterEnabledByDefault(boolean enableBemFilterByDefault)
	{
		myBemFilterEnabledByDefault = enableBemFilterByDefault;
	}

	@Nullable
	@Override
	public XmlEmmetOptions getState()
	{
		return this;
	}

	@Override
	public void loadState(XmlEmmetOptions state)
	{
		XmlSerializerUtil.copyBean(state, this);
	}
}
