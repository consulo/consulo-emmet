package com.intellij.application.options.emmet;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.consulo.lombok.annotations.ApplicationService;
import org.jetbrains.annotations.Nullable;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
@ApplicationService
@State(
		name = "XmlEmmetOptions",
		storages = {
				@Storage(
						file = StoragePathMacros.APP_CONFIG + "/editor.xml"
				)}
)
public class XmlEmmetOptions implements PersistentStateComponent<XmlEmmetOptions> {
	private boolean myBemFilterEnabledByDefault = false;

	public boolean isBemFilterEnabledByDefault() {
		return myBemFilterEnabledByDefault;
	}

	public void setBemFilterEnabledByDefault(boolean enableBemFilterByDefault) {
		myBemFilterEnabledByDefault = enableBemFilterByDefault;
	}

	@Nullable
	@Override
	public XmlEmmetOptions getState() {
		return this;
	}

	@Override
	public void loadState(XmlEmmetOptions state) {
		XmlSerializerUtil.copyBean(state, this);
	}
}
