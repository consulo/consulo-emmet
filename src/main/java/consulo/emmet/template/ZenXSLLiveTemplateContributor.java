package consulo.emmet.template;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.LiveTemplateContributor;
import consulo.localize.LocalizeValue;
import consulo.xml.codeInsight.template.XslTextContextType;
import jakarta.annotation.Nonnull;

@ExtensionImpl
public class ZenXSLLiveTemplateContributor implements LiveTemplateContributor {
    @Override
    @Nonnull
    public String groupId() {
        return "zenxsl";
    }

    @Override
    @Nonnull
    public LocalizeValue groupName() {
        return LocalizeValue.localizeTODO("Zen XSL");
    }

    @Override
    public void contribute(@Nonnull LiveTemplateContributor.Factory factory) {
        try (Builder builder = factory.newBuilder("zenxslTmatch", "tmatch", "<xsl:template match=\"$VAR0$\" mode=\"$VAR1$\">$END$</xsl:template>", LocalizeValue.localizeTODO("<xsl:template match=\"...\" mode=\"...\">...</xsl:template>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslPar", "par", "<xsl:param name=\"$VAR0$\" >$END$</xsl:param>", LocalizeValue.localizeTODO("<xsl:param name=\"...\">...</xsl:param>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslPare", "pare", "<xsl:param name=\"$VAR0$\" select=\"$VAR1$\" />", LocalizeValue.localizeTODO("<xsl:param name=\"...\" select=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslCo", "co", "<xsl:copy-of select=\"$VAR0$\" />", LocalizeValue.localizeTODO("<xsl:copy-of select=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslAttr", "attr", "<xsl:attribute name=\"$VAR0$\">$END$</xsl:attribute>", LocalizeValue.localizeTODO("<xsl:attribute name=\"...\">...</xsl:attribute>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslVal", "val", "<xsl:value-of select=\"$VAR0$\" />", LocalizeValue.localizeTODO("<xsl:value-of select=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslXsl:when", "xsl:when", "<xsl:when test=\"$VAR0$\">$END$</xsl:when>", LocalizeValue.localizeTODO("<xsl:when test=\"...\">...</xsl:when>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslEach", "each", "<xsl:for-each select=\"$VAR0$\">$END$</xsl:for-each>", LocalizeValue.localizeTODO("<xsl:for-each select=\"...\">...</xsl:for-each>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslVar", "var", "<xsl:variable name=\"$VAR0$\">$END$</xsl:variable>", LocalizeValue.localizeTODO("<xsl:variable name=\"...\">...</xsl:variable>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslWh", "wh", "<xsl:when test=\"$VAR0$\">$END$</xsl:when>", LocalizeValue.localizeTODO("<xsl:when test=\"...\">...</xsl:when>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslTn", "tn", "<xsl:template name=\"$VAR0$\">$END$</xsl:template>", LocalizeValue.localizeTODO("<xsl:template name=\"...\">...</xsl:template>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslAp", "ap", "<xsl:apply-templates select=\"$VAR0$\" mode=\"$VAR1$\" />", LocalizeValue.localizeTODO("<xsl:apply-templates select=\"...\" mode=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslTm", "tm", "<xsl:template match=\"$VAR0$\" mode=\"$VAR1$\">$END$</xsl:template>", LocalizeValue.localizeTODO("<xsl:template match=\"...\" mode=\"...\">...</xsl:template>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslCall", "call", "<xsl:call-template name=\"$VAR0$\" />", LocalizeValue.localizeTODO("<xsl:call-template name=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslCh", "ch", "<xsl:choose>$END$</xsl:choose>", LocalizeValue.localizeTODO("<xsl:choose>...</xsl:choose>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslChoose+", "choose+", "<xsl:choose>\n"
            + "  <xsl:when test=\"$VAR0$\">$VAR1$</xsl:when>\n"
            + "  <xsl:otherwise>$END$</xsl:otherwise>\n"
            + "</xsl:choose>", LocalizeValue.localizeTODO("choose+"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslVare", "vare", "<xsl:variable name=\"$VAR0$\" select=\"$VAR1$\" />", LocalizeValue.localizeTODO("<xsl:variable name=\"...\" select=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslWp", "wp", "<xsl:with-param name=\"$VAR0$\" select=\"$VAR1$\" />", LocalizeValue.localizeTODO("<xsl:with-param name=\"...\" select=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslTname", "tname", "<xsl:template name=\"$VAR0$\">$END$</xsl:template>", LocalizeValue.localizeTODO("<xsl:template name=\"...\">...</xsl:template>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslIf", "if", "<xsl:if test=\"$VAR0$\">$END$</xsl:if>", LocalizeValue.localizeTODO("<xsl:if test=\"...\">...</xsl:if>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslSort", "sort", "<xsl:sort select=\"$VAR0$\" order=\"$VAR1$\" />", LocalizeValue.localizeTODO("<xsl:sort select=\"...\" order=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslProc", "proc", "<xsl:processing-instruction name=\"$VAR0$\">$END$</xsl:processing-instruction>", LocalizeValue.localizeTODO("<xsl:processing-instruction name=\"...\">...</xsl:processing-instruction>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslFor", "for", "<xsl:for-each select=\"$VAR0$\">$END$</xsl:for-each>", LocalizeValue.localizeTODO("<xsl:for-each select=\"...\">...</xsl:for-each>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslStrip", "strip", "<xsl:strip-space elements=\"$END$\" />", LocalizeValue.localizeTODO("<xsl:strip-space elements=\"...\" />>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslPres", "pres", "<xsl:preserve-space elements=\"$END$\" />", LocalizeValue.localizeTODO("<xsl:preserve-space elements=\"...\" />"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslNam", "nam", "<namespace-alias stylesheet-prefix=\"$VAR0$\" result-prefix=\"$END$\" />", LocalizeValue.localizeTODO("<namespace-alias stylesheet-prefix=\"...\" result-prefix=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslNum", "num", "<xsl:number value=\"$END$\" />", LocalizeValue.localizeTODO("<xsl:number value=\"...\" />"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslFall", "fall", "<xsl:fallback>$END$</xsl:fallback>", LocalizeValue.localizeTODO("<xsl:fallback>...</xsl:fallback>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslMsg", "msg", "<xsl:message terminate=\"no\">$END$</xsl:message>", LocalizeValue.localizeTODO("<xsl:message terminate=\"no\">...</xsl:message>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslCom", "com", "<xsl:comment>$END$</xsl:comment>", LocalizeValue.localizeTODO("<xsl:comment>...</xsl:comment>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslOt", "ot", "<xsl:otherwise>$END$</xsl:otherwise>", LocalizeValue.localizeTODO("<xsl:otherwise></xsl:otherwise>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslTex", "tex", "<xsl:text>$END$</xsl:text>", LocalizeValue.localizeTODO("<xsl:text></xsl:text>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslApi", "api", "<xsl:apply-imports/>", LocalizeValue.localizeTODO("<xsl:apply-imports/>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslCp", "cp", "<xsl:copy select=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<xsl:copy select=\"...\"/>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslAttrs", "attrs", "<xsl:attribute-set name=\"$VAR0$\">$END$</xsl:attribute-set>", LocalizeValue.localizeTODO("<xsl:attribute-set name=\"...\">...</xsl:attribute-set>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslElem", "elem", "<xsl:element name=\"$VAR0$\">$END$</xsl:element>", LocalizeValue.localizeTODO("<xsl:element name=\"...\">...</xsl:element>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslInc", "inc", "<xsl:include href=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<xsl:include href=\"...\"/>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslImp", "imp", "<xsl:import href=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<xsl:import href=\"...\"/>"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslXsl", "xsl", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>   <xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
            + "$VAR0$\n"
            + "</xsl:stylesheet>", LocalizeValue.localizeTODO("xsl"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxslKey", "key", "<xsl:key name=\"$VAR0$\" match=\"$VAR1$\" use=\"$VAR2$\" />", LocalizeValue.localizeTODO("<xsl:key name=\"...\" match=\"...\" use=\"...\" />"))) {
            builder.withReformat();

            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);

            builder.withContext(XslTextContextType.class, true);
        }

        try (Builder builder = factory.newBuilder("zenxsl!!!", "!!!", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>", LocalizeValue.localizeTODO("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"))) {
            builder.withReformat();


            builder.withContext(XslTextContextType.class, true);
        }

    }
}
