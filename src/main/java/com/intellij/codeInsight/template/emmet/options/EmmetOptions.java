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

import consulo.annotation.component.ComponentScope;
import consulo.annotation.component.ServiceAPI;
import consulo.annotation.component.ServiceImpl;
import consulo.component.persist.PersistentStateComponent;
import consulo.component.persist.State;
import consulo.component.persist.Storage;
import consulo.ide.ServiceManager;
import consulo.util.xml.serializer.XmlSerializerUtil;
import jakarta.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * User: zolotov
 * Date: 2/20/13
 */
@Singleton
@State(name = "EmmetOptions", storages = @Storage("emmet.xml"))
@ServiceAPI(ComponentScope.APPLICATION)
@ServiceImpl
public class EmmetOptions implements PersistentStateComponent<EmmetOptions>
{
	@NotNull
	public static EmmetOptions getInstance()
	{
		return ServiceManager.getService(EmmetOptions.class);
	}

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
