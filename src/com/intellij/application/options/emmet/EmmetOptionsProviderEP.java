package com.intellij.application.options.emmet;

import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.options.ConfigurableEP;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
public class EmmetOptionsProviderEP extends ConfigurableEP<EmmetOptionsProvider> {
	public static final ExtensionPointName<EmmetOptionsProviderEP> EP_NAME = ExtensionPointName.create("org.consulo.emmet.optionsProvider");
}
