/**
 * @author VISTALL
 * @since 16-Aug-22
 */
module consulo.emmet
{
	// TODO remove in future
	requires java.desktop;

	requires consulo.ide.api;

	requires com.google.common;

	requires com.intellij.xml;

	exports com.intellij.codeInsight.template.emmet;
	exports com.intellij.codeInsight.template.emmet.filters;
	exports com.intellij.codeInsight.template.emmet.generators;
	exports com.intellij.codeInsight.template.emmet.nodes;
	exports com.intellij.codeInsight.template.emmet.tokens;
	exports com.intellij.codeInsight.template.emmet.options;
	exports consulo.emmet.localize;
}