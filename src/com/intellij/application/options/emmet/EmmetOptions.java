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

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import consulo.lombok.annotations.ApplicationService;

/**
 * User: zolotov
 * Date: 2/20/13
 */
@ApplicationService
@State(
		name = "EmmetOptions",
		storages = {
				@Storage(
						file = StoragePathMacros.APP_CONFIG + "/editor.xml"
				)
		}
)
public class EmmetOptions implements PersistentStateComponent<EmmetOptions>
{
	private boolean myEmmetEnabled = true;
	private int myEmmetExpandShortcut = '\t';

	public void setEmmetExpandShortcut(int emmetExpandShortcut)
	{
		myEmmetExpandShortcut = emmetExpandShortcut;
	}

	public int getEmmetExpandShortcut()
	{
		return myEmmetExpandShortcut;
	}

	public boolean isEmmetEnabled()
	{
		return myEmmetEnabled;
	}

	public void setEmmetEnabled(boolean emmetEnabled)
	{
		myEmmetEnabled = emmetEnabled;
	}

	@Nullable
	@Override
	public EmmetOptions getState()
	{
		return this;
	}

	public void loadState(final EmmetOptions state)
	{
		XmlSerializerUtil.copyBean(state, this);
	}
}
