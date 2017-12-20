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

package com.intellij.codeInsight.template.emmet;

import org.jetbrains.annotations.PropertyKey;
import com.intellij.AbstractBundle;

/**
 * @author VISTALL
 * @since 21.02.14
 */
public class EmmetBundle extends AbstractBundle
{
	private static final EmmetBundle ourInstance = new EmmetBundle();

	private EmmetBundle()
	{
		super("messages.EmmetBundle");
	}

	public static String message(@PropertyKey(resourceBundle = "messages.EmmetBundle") String key)
	{
		return ourInstance.getMessage(key);
	}

	public static String message(@PropertyKey(resourceBundle = "messages.EmmetBundle") String key, Object... params)
	{
		return ourInstance.getMessage(key, params);
	}
}
