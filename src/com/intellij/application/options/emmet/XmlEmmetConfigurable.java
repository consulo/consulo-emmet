package com.intellij.application.options.emmet;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.xml.XmlBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @author VISTALL
 * @since 23.08.13.
 */
public class XmlEmmetConfigurable implements EmmetOptionsProvider {
	private JPanel myRoot;
	private JCheckBox myEnableBemFilter;

	public XmlEmmetConfigurable() {
		myRoot = new JPanel(new BorderLayout());

		myEnableBemFilter = new JCheckBox();
		myEnableBemFilter.setText(XmlBundle.message("emmet.enable.bem.filter"));

		myRoot.add(myEnableBemFilter, BorderLayout.NORTH);
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		return myRoot;
	}

	@Override
	public boolean isModified() {
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();
		return myEnableBemFilter.isSelected() != instance.isBemFilterEnabledByDefault();
	}

	@Override
	public void apply() throws ConfigurationException {
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();

		instance.setBemFilterEnabledByDefault(myEnableBemFilter.isSelected());
	}

	@Override
	public void reset() {
		XmlEmmetOptions instance = XmlEmmetOptions.getInstance();

		myEnableBemFilter.setSelected(instance.isBemFilterEnabledByDefault());
	}

	@Override
	public void disposeUIResources() {

	}

	@NotNull
	@Override
	public String getId() {
		return "xml.emmet.application.settings";
	}

	@Nullable
	@Override
	public Runnable enableSearch(String s) {
		return null;
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "XML(HTML, etc)";
	}

	@Nullable
	@Override
	public String getHelpTopic() {
		return null;
	}
}
