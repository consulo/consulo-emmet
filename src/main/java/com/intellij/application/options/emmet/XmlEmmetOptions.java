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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
@State(
		name = "XmlEmmetOptions",
		storages = {
				@Storage(
						file = StoragePathMacros.APP_CONFIG + "/editor.xml"
				)
		}
)
public class XmlEmmetOptions implements PersistentStateComponent<XmlEmmetOptions>
{
	@NotNull
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
