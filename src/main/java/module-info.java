/**
 * @author VISTALL
 * @since 16-Aug-22
 */
module consulo.emmet {
    // TODO remove in future
    requires java.desktop;

    requires consulo.language.editor.api;
    requires consulo.configurable.api;
    requires consulo.ui.ex.api;
    requires consulo.ui.ex.awt.api;
    requires consulo.language.impl;

    requires com.google.common;

    requires com.intellij.xml.html.api;
    requires com.intellij.xml;

    exports com.intellij.codeInsight.template.emmet;
    exports com.intellij.codeInsight.template.emmet.filters;
    exports com.intellij.codeInsight.template.emmet.generators;
    exports com.intellij.codeInsight.template.emmet.nodes;
    exports com.intellij.codeInsight.template.emmet.tokens;
    exports com.intellij.codeInsight.template.emmet.options;
    exports consulo.emmet.localize;
}