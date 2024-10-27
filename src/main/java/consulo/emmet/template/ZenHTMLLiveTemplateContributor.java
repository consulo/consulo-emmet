package consulo.emmet.template;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.LiveTemplateContributor;
import consulo.localize.LocalizeValue;
import consulo.xml.codeInsight.template.HtmlTextContextType;
import jakarta.annotation.Nonnull;

@ExtensionImpl
public class ZenHTMLLiveTemplateContributor implements LiveTemplateContributor {
    @Override
    @Nonnull
    public String groupId() {
        return "zenhtml";
    }

    @Override
    @Nonnull
    public LocalizeValue groupName() {
        return LocalizeValue.localizeTODO("Zen HTML");
    }

    @Override
    public void contribute(@Nonnull LiveTemplateContributor.Factory factory) {
        try (Builder builder = factory.newBuilder("zenhtml!!!", "!!!", "<!doctype html>", LocalizeValue.localizeTODO("<!doctype html>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!!!4t", "!!!4t", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">", LocalizeValue.localizeTODO("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!!!4s", "!!!4s", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">", LocalizeValue.localizeTODO("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!!!xt", "!!!xt", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">", LocalizeValue.localizeTODO("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!!!xs", "!!!xs", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">", LocalizeValue.localizeTODO("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!!!xxs", "!!!xxs", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">", LocalizeValue.localizeTODO("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlC", "c", "<!-- $END$ -->", LocalizeValue.localizeTODO("<!-- -->"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCc:ie6", "cc:ie6", "<!--[if lte IE 6]>\n  $END$\n<![endif]-->", LocalizeValue.localizeTODO("cc:ie6"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCc:ie", "cc:ie", "<!--[if IE]>\n  $END$\n<![endif]-->", LocalizeValue.localizeTODO("cc:ie"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCc:noie", "cc:noie", "<!--[if !IE]><!-->\n  $END$\n<!--<![endif]-->", LocalizeValue.localizeTODO("cc:noie"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtml!", "!", "<!doctype html>\n<html lang=\"$ENV_LOCALE$\">\n<head>\n<meta charset=\"UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("!"))) {
            builder.withReformat();
            builder.withVariable("ENV_LOCALE", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlA", "a", "<a href=\"$VAR0$\">$END$</a>", LocalizeValue.localizeTODO("<a href=\"...\">...</a>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlA:link", "a:link", "<a href=\"http://$VAR0$\">$END$</a>", LocalizeValue.localizeTODO("<a href=\"http://...\">...</a>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlA:mail", "a:mail", "<a href=\"mailto:$VAR0$\">$END$</a>", LocalizeValue.localizeTODO("<a href=\"mailto:...\">...</a>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlAbbr", "abbr", "<abbr title=\"$VAR0$\">$END$</abbr>", LocalizeValue.localizeTODO("<abbr title=\"...\">...</abbr>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlAcronym", "acronym", "<acronym title=\"$VAR0$\">$END$</acronym>", LocalizeValue.localizeTODO("<acronym title=\"...\">...</acronym>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBase", "base", "<base href=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<base href=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBasefont", "basefont", "<basefont/>", LocalizeValue.localizeTODO("<basefont/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBr", "br", "<br/>", LocalizeValue.localizeTODO("<br/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFrame", "frame", "<frame/>", LocalizeValue.localizeTODO("<frame/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHr", "hr", "<hr/>", LocalizeValue.localizeTODO("<hr/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBdo", "bdo", "<bdo dir=\"$VAR0$\">$END$</bdo>", LocalizeValue.localizeTODO("<bdo dir=\"...\">...</bdo>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBdo:r", "bdo:r", "<bdo dir=\"rtl\">$END$</bdo>", LocalizeValue.localizeTODO("<bdo dir=\"rtl\">...</bdo>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBdo:l", "bdo:l", "<bdo dir=\"ltr\">$END$</bdo>", LocalizeValue.localizeTODO("<bdo dir=\"ltr\">...</bdo>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCol", "col", "<col/>", LocalizeValue.localizeTODO("<col/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink", "link", "<link rel=\"stylesheet\" href=\"$END$\"/>", LocalizeValue.localizeTODO("<link rel=\"stylesheet\" href=\"...\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:css", "link:css", "<link rel=\"stylesheet\" href=\"$STYLE$.css\" />", LocalizeValue.localizeTODO("<link rel=\"stylesheet\" href=\"style.css\" />"))) {
            builder.withReformat();
            builder.withVariable("STYLE", "", "\"style\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:print", "link:print", "<link rel=\"stylesheet\" href=\"$STYLE$.css\" media=\"print\"/>", LocalizeValue.localizeTODO("<link rel=\"stylesheet\" href=\"print.css\" media=\"print\">"))) {
            builder.withReformat();
            builder.withVariable("STYLE", "", "\"print\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:favicon", "link:favicon", "<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"$FAVICON$\"/>", LocalizeValue.localizeTODO("<link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"favicon.ico\">"))) {
            builder.withReformat();
            builder.withVariable("FAVICON", "", "\"favicon.ico\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:touch", "link:touch", "<link rel=\"apple-touch-icon\" href=\"$FAVICON$\"/>", LocalizeValue.localizeTODO("<link rel=\"apple-touch-icon\" href=\"favicon.png\">"))) {
            builder.withReformat();
            builder.withVariable("FAVICON", "", "\"favicon.png\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:rss", "link:rss", "<link rel=\"alternate\" type=\"application/rss+xml\" title=\"RSS\" href=\"$RSS$\"/>", LocalizeValue.localizeTODO("<link rel=\"alternate\" type=\"application/rss+xml\" title=\"RSS\" href=\"rss.xml\">"))) {
            builder.withReformat();
            builder.withVariable("RSS", "", "\"rss.xml\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLink:atom", "link:atom", "<link rel=\"alternate\" type=\"application/atom+xml\" title=\"Atom\" href=\"$ATOM$\"/>", LocalizeValue.localizeTODO("<link rel=\"alternate\" type=\"application/atom+xml\" title=\"Atom\" href=\"atom.xml\">"))) {
            builder.withReformat();
            builder.withVariable("ATOM", "", "\"atom.xml\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMeta", "meta", "<meta/>", LocalizeValue.localizeTODO("<meta/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMeta:utf", "meta:utf", "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"/>", LocalizeValue.localizeTODO("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMeta:win", "meta:win", "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=windows-1251\"/>", LocalizeValue.localizeTODO("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=Win-1251\">"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMeta:vp", "meta:vp", "<meta name=\"viewport\" content=\"width=$WIDTH$, user-scalable=$SCALABLE$, initial-scale=$INITIAL_SCALE$, maximum-scale=$MAX_SCALE$, minimum-scale=$MIN_SCALE$\"/>", LocalizeValue.localizeTODO("<meta name=\"viewport\" content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\"/>"))) {
            builder.withReformat();
            builder.withVariable("WIDTH", "", "\"device-width\"", true);
            builder.withVariable("SCALABLE", "", "\"no\"", true);
            builder.withVariable("INITIAL_SCALE", "", "\"1.0\"", true);
            builder.withVariable("MAX_SCALE", "", "\"1.0\"", true);
            builder.withVariable("MIN_SCALE", "", "\"1.0\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMeta:compat", "meta:compat", "<meta http-equiv=\"X-UA-Compatible\" content=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"IE=7\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlStyle", "style", "<style>$END$</style>", LocalizeValue.localizeTODO("<style>...</style>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlScript", "script", "<script>$END$</script>", LocalizeValue.localizeTODO("<script>...</script>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlScript:src", "script:src", "<script src=\"$END$\"></script>", LocalizeValue.localizeTODO("<script src=\"...\">...</script>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlImg", "img", "<img src=\"$VAR0$\" alt=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<img src=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlIframe", "iframe", "<iframe src=\"$VAR0$\" frameborder=\"0\">$END$</iframe>", LocalizeValue.localizeTODO("<iframe src=\"...\" frameborder=\"0\">...</iframe>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlEmbed", "embed", "<embed src=\"$VAR0$\" type=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<embed src=\"...\" type=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlObject", "object", "<object data=\"$VAR0$\" type=\"$VAR1$\">$END$</object>", LocalizeValue.localizeTODO("<object data=\"...\" type=\"...\">...</object>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlParam", "param", "<param name=\"$VAR0$\" value=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<param name=\"...\" value=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMap", "map", "<map name=\"$VAR0$\">$END$</map>", LocalizeValue.localizeTODO("<map name=\"...\">...</map>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArea", "area", "<area shape=\"$VAR0$\" coords=\"$VAR1$\" href=\"$VAR2$\" alt=\"$VAR3$\"/>", LocalizeValue.localizeTODO("<area shape=\"...\" coords=\"...\" href=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withVariable("VAR3", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArea:d", "area:d", "<area shape=\"default\" href=\"$VAR0$\" alt=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<area shape=\"default\" href=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArea:c", "area:c", "<area shape=\"circle\" coords=\"$VAR0$\" href=\"$VAR1$\" alt=\"$VAR2$\"/>", LocalizeValue.localizeTODO("<area shape=\"circle\" coords=\"...\" href=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArea:r", "area:r", "<area shape=\"rect\" coords=\"$VAR0$\" href=\"$VAR1$\" alt=\"$VAR2$\"/>", LocalizeValue.localizeTODO("<area shape=\"rect\" coords=\"...\" href=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArea:p", "area:p", "<area shape=\"poly\" coords=\"$VAR0$\" href=\"$VAR1$\" alt=\"$VAR2$\"/>", LocalizeValue.localizeTODO("<area shape=\"poly\" coords=\"...\" href=\"...\" alt=\"...\">"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlForm", "form", "<form action=\"$VAR0$\">$END$</form>", LocalizeValue.localizeTODO("<form action=\"...\">...</form>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlForm:get", "form:get", "<form action=\"$VAR0$\" method=\"get\">$END$</form>", LocalizeValue.localizeTODO("<form action=\"...\" method=\"get\">...</form>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlForm:post", "form:post", "<form action=\"$VAR0$\" method=\"post\">$END$</form>", LocalizeValue.localizeTODO("<form action=\"...\" method=\"post\">...</form>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLabel", "label", "<label for=\"$VAR0$\">$END$</label>", LocalizeValue.localizeTODO("<label for=\"...\">...</label>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput", "input", "<input type=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"text\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInp", "inp", "<input type=\"$VAR0$\" name=\"$NAME$\" id=\"$ID$\"/>", LocalizeValue.localizeTODO("<input type=\"...\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"text\"", true);
            builder.withVariable("NAME", "", "\"\"", true);
            builder.withVariable("ID", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:hidden", "input:hidden", "<input name=\"$VAR0$\" type=\"hidden\"/>", LocalizeValue.localizeTODO("<input name=\"...\" type=\"hidden\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:h", "input:h", "<input name=\"$VAR0$\" type=\"hidden\"/>", LocalizeValue.localizeTODO("<input name=\"...\" type=\"hidden\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:text", "input:text", "<input type=\"text\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"text\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:t", "input:t", "<input type=\"text\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"text\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:search", "input:search", "<input type=\"search\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"search\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:email", "input:email", "<input type=\"email\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"email\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:url", "input:url", "<input type=\"url\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"url\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:password", "input:password", "<input type=\"password\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"password\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:p", "input:p", "<input type=\"password\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"password\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:datetime", "input:datetime", "<input type=\"datetime\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"datetime\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:date", "input:date", "<input type=\"date\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"date\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:datetime-local", "input:datetime-local", "<input type=\"datetime-local\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"datetime-local\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:month", "input:month", "<input type=\"month\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"month\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:week", "input:week", "<input type=\"week\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"week\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:time", "input:time", "<input type=\"time\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"time\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:number", "input:number", "<input type=\"number\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"number\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:color", "input:color", "<input type=\"color\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"color\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:checkbox", "input:checkbox", "<input type=\"checkbox\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"checkbox\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:c", "input:c", "<input type=\"checkbox\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"checkbox\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:radio", "input:radio", "<input type=\"radio\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"radio\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:r", "input:r", "<input type=\"radio\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"radio\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:range", "input:range", "<input type=\"range\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"range\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:file", "input:file", "<input type=\"file\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"file\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:f", "input:f", "<input type=\"file\" name=\"$VAR0$\" id=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"file\" name=\"...\" id=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:submit", "input:submit", "<input type=\"submit\" value=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"submit\" value=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:s", "input:s", "<input type=\"submit\" value=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"submit\" value=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:image", "input:image", "<input type=\"image\" src=\"$VAR0$\" alt=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"image\" src=\"...\" alt=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:i", "input:i", "<input type=\"image\" src=\"$VAR0$\" alt=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<input type=\"image\" src=\"...\" alt=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:button", "input:button", "<input type=\"button\" value=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"button\" value=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:b", "input:b", "<input type=\"button\" value=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"button\" value=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlIsindex", "isindex", "<isindex/>", LocalizeValue.localizeTODO("<inindex/>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlInput:reset", "input:reset", "<input type=\"reset\" value=\"$VAR0$\"/>", LocalizeValue.localizeTODO("<input type=\"reset\" value=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSelect", "select", "<select name=\"$VAR0$\" id=\"$VAR1$\">$END$</select>", LocalizeValue.localizeTODO("<select name=\"...\" id=\"...\">...</select>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSelect:disabled", "select:disabled", "<select name=\"$VAR0$\" id=\"$VAR1$\" disabled=\"$VAR2$\">$END$</select>", LocalizeValue.localizeTODO("<select name=\"...\" id=\"...\" disabled=\"...\">...</select>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSelect:d", "select:d", "<select name=\"$VAR0$\" id=\"$VAR1$\" disabled=\"$VAR2$\">$END$</select>", LocalizeValue.localizeTODO("<select name=\"...\" id=\"...\" disabled=\"...\">...</select>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOption", "option", "<option value=\"$VAR0$\">$END$</option>", LocalizeValue.localizeTODO("<option value=\"...\">...</option>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlTextarea", "textarea", "<textarea name=\"$VAR0$\" id=\"$VAR1$\" cols=\"$COL$\" rows=\"$ROWS$\">$END$</textarea>", LocalizeValue.localizeTODO("<textarea name=\"...\" id=\"...\" cols=\"30\" rows=\"10\">...</textarea>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("COL", "", "\"30\"", true);
            builder.withVariable("ROWS", "", "\"10\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMarquee", "marquee", "<marquee behavior=\"$BEHAVIOR$\" direction=\"$DIRECTION$\">$END$</marquee>", LocalizeValue.localizeTODO("<marquee behavior=\"...\" direction=\"...\">...</marquee>"))) {
            builder.withReformat();
            builder.withVariable("BEHAVIOR", "", "\"\"", true);
            builder.withVariable("DIRECTION", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMenu:context", "menu:context", "<menu type=\"context\">$END$</menu>", LocalizeValue.localizeTODO("<menu type=\"context\">...</menu>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMenu:c", "menu:c", "<menu type=\"context\">$END$</menu>", LocalizeValue.localizeTODO("<menu type=\"context\">...</menu>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMenu:toolbar", "menu:toolbar", "<menu type=\"toolbar\">$END$</menu>", LocalizeValue.localizeTODO("<menu type=\"toolbar\">...</menu>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMenu:t", "menu:t", "<menu type=\"toolbar\">$END$</menu>", LocalizeValue.localizeTODO("<menu type=\"toolbar\">...</menu>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlVideo", "video", "<video src=\"$VAR0$\">$END$</video>", LocalizeValue.localizeTODO("<video src=\"...\">...</video>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlAudio", "audio", "<audio src=\"$VAR0$\">$END$</audio>", LocalizeValue.localizeTODO("<audio src=\"...\">...</audio>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:xml", "html:xml", "<html xmlns=\"http://www.w3.org/1999/xhtml\">$END$</html>", LocalizeValue.localizeTODO("<html xmlns=\"http://www.w3.org/1999/xhtml\">...</html>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlKeygen", "keygen", "<keygen>", LocalizeValue.localizeTODO("<keygen>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCommand", "command", "<command>", LocalizeValue.localizeTODO("<command>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:submit", "button:submit", "<button type=\"submit\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"submit\"></button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:s", "button:s", "<button type=\"submit\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"submit\"></button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:reset", "button:reset", "<button type=\"reset\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"reset\"></button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:r", "button:r", "<button type=\"reset\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"reset\"></button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:disabled", "button:disabled", "<button disabled=\"$VAR0$\">$END$</button>", LocalizeValue.localizeTODO("<button disabled=\"\">...</button>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlButton:d", "button:d", "<button disabled=\"$VAR0$\">$END$</button>", LocalizeValue.localizeTODO("<button disabled=\"\">...</button>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFieldset:disabled", "fieldset:disabled", "<fieldset disabled=\"$VAR0$\">$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset disabled=\"\">...</fieldset>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFieldset:d", "fieldset:d", "<fieldset disabled=\"$VAR0$\">$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset disabled=\"\">...</fieldset>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBq", "bq", "<blockquote>$END$</blockquote>", LocalizeValue.localizeTODO("<blockquote>...</blockquote>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlAcr", "acr", "<acronym title=\"$VAR0$\">$END$</acronym>", LocalizeValue.localizeTODO("<acronym title=\"...\">...</acronym>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFig", "fig", "<figure>$END$</figure>", LocalizeValue.localizeTODO("<figure>...</figure>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFigc", "figc", "<figcaption>$END$</figcaption>", LocalizeValue.localizeTODO("<figcaption>...</figcaption>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlIfr", "ifr", "<iframe src=\"$VAR0$\" frameborder=\"0\">$END$</iframe>", LocalizeValue.localizeTODO("<iframe src=\"...\" frameborder=\"0\">...</iframe>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlEmb", "emb", "<embed src=\"$VAR0$\" type=\"$VAR1$\"/>", LocalizeValue.localizeTODO("<embed src=\"...\" type=\"...\"/>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlObj", "obj", "<object data=\"$VAR0$\" type=\"$VAR1$\">$END$</object>", LocalizeValue.localizeTODO("<object data=\"...\" type=\"...\">...</object>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSrc", "src", "<source>$END$</source>", LocalizeValue.localizeTODO("<source>...</source>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCap", "cap", "<caption>$END$</caption>", LocalizeValue.localizeTODO("<caption>...</caption>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlColg", "colg", "<colgroup>$END$</colgroup>", LocalizeValue.localizeTODO("<colgroup>...</colgroup>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFst", "fst", "<fieldset>$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset>...</fieldset>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFst:d", "fst:d", "<fieldset disabled=\"$VAR0$\">$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset disabled=\"\">...</fieldset>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBtn", "btn", "<button>$END$</button>", LocalizeValue.localizeTODO("<button>...</button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBtn:b", "btn:b", "<button type=\"button\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"button\">...</button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBtn:r", "btn:r", "<button type=\"reset\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"reset\">...</button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBtn:s", "btn:s", "<button type=\"submit\">$END$</button>", LocalizeValue.localizeTODO("<button type=\"submit\">...</button>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlBtn:d", "btn:d", "<button disabled=\"$VAR0$\">$END$</button>", LocalizeValue.localizeTODO("<button disabled=\"\">...</button>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOptg", "optg", "<optgroup>$END$</optgroup>", LocalizeValue.localizeTODO("<optgroup>...</optgroup>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOpt", "opt", "<option value=\"$VAR0$\">$END$</option>", LocalizeValue.localizeTODO("<option value=\"...\">...</option>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlTarea", "tarea", "<textarea name=\"$VAR0$\" id=\"$VAR1$\" cols=\"$COL$\" rows=\"$ROWS$\">$END$</textarea>", LocalizeValue.localizeTODO("<textarea name=\"...\" id=\"...\" cols=\"30\" rows=\"10\">...</textarea>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("COL", "", "\"30\"", true);
            builder.withVariable("ROWS", "", "\"10\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlLeg", "leg", "<legend>$END$</legend>", LocalizeValue.localizeTODO("<legend>...</legend>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSect", "sect", "<section>$END$</section>", LocalizeValue.localizeTODO("<section>...</section>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlArt", "art", "<article>$END$</article>", LocalizeValue.localizeTODO("<article>...</article>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHdr", "hdr", "<header>$END$</header>", LocalizeValue.localizeTODO("<header>...</header>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFtr", "ftr", "<footer>$END$</footer>", LocalizeValue.localizeTODO("<footer>...</footer>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlAdr", "adr", "<address>$END$</address>", LocalizeValue.localizeTODO("<address>...</address>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDlg", "dlg", "<dialog>$END$</dialog>", LocalizeValue.localizeTODO("<dialog>...</dialog>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlStr", "str", "<strong>$END$</strong>", LocalizeValue.localizeTODO("<strong>...</strong>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlProg", "prog", "<progress>$END$</progress>", LocalizeValue.localizeTODO("<progress>...</progress>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFset", "fset", "<fieldset>$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset>...</fieldset>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlFset:d", "fset:d", "<fieldset disabled=\"$VAR0$\">$END$</fieldset>", LocalizeValue.localizeTODO("<fieldset disabled=\"\">...</fieldset>"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDatag", "datag", "<datagrid>$END$</datagrid>", LocalizeValue.localizeTODO("<datagrid>...</datagrid>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDatal", "datal", "<datalist>$END$</datalist>", LocalizeValue.localizeTODO("<datalist>...</datalist>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlKg", "kg", "<keygen>", LocalizeValue.localizeTODO("<keygen>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOut", "out", "<output>$END$</output>", LocalizeValue.localizeTODO("<output>...</output>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDet", "det", "<details>$END$</details>", LocalizeValue.localizeTODO("<details>...</details>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlCmd", "cmd", "<command>", LocalizeValue.localizeTODO("<command>"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDoc", "doc", "<html>\n<head>\n<meta charset=\"UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("doc"))) {
            builder.withReformat();
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDoc4", "doc4", "<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("doc4"))) {
            builder.withReformat();
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:4t", "html:4t", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html lang=\"$ENV_LANG$\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:4t"))) {
            builder.withReformat();
            builder.withVariable("ENV_LANG", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:4s", "html:4s", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n<html lang=\"$ENV_LANG$\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:4s"))) {
            builder.withReformat();
            builder.withVariable("ENV_LANG", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:xt", "html:xt", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"$ENV_LANG$\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:xt"))) {
            builder.withReformat();
            builder.withVariable("ENV_LANG", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:xs", "html:xs", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"$ENV_LANG$\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:xs"))) {
            builder.withReformat();
            builder.withVariable("ENV_LANG", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:xxs", "html:xxs", "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"$ENV_LANG$\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:xxs"))) {
            builder.withReformat();
            builder.withVariable("ENV_LANG", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlHtml:5", "html:5", "<!doctype html>\n<html lang=\"$ENV_LOCALE$\">\n<head>\n<meta charset=\"UTF-8\">\n<title>$TITLE$</title>\n</head>\n<body>\n  $END$\n</body>\n</html>", LocalizeValue.localizeTODO("html:5"))) {
            builder.withReformat();
            builder.withVariable("ENV_LOCALE", "", "\"en\"", true);
            builder.withVariable("TITLE", "", "\"Document\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOl+", "ol+", "<ol>\n<li>$END$</li>\n</ol>", LocalizeValue.localizeTODO("ol+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlUl+", "ul+", "<ul>\n<li>$END$</li>\n</ul>", LocalizeValue.localizeTODO("ul+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlDl+", "dl+", "<dl>\n<dt>$VAR0$</dt>\n<dd>$END$</dd>\n</dl>", LocalizeValue.localizeTODO("dl+"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlMap+", "map+", "<map name=\"$VAR0$\">\n<area shape=\"$VAR1$\" coords=\"$VAR2$\" href=\"$VAR3$\" alt=\"$VAR4$\"/>$END$\n</map>", LocalizeValue.localizeTODO("map+"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withVariable("VAR3", "", "\"\"", true);
            builder.withVariable("VAR4", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlTable+", "table+", "<table>\n<tr>\n<td>$END$</td>\n</tr>\n</table>", LocalizeValue.localizeTODO("table+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlColgroup+", "colgroup+", "<colgroup>\n<col/>\n</colgroup>", LocalizeValue.localizeTODO("colgroup+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlColg+", "colg+", "<colgroup>\n<col/>\n</colgroup>", LocalizeValue.localizeTODO("colg+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlTr+", "tr+", "<tr>\n<td>$END$</td>\n</tr>", LocalizeValue.localizeTODO("tr+"))) {
            builder.withReformat();
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlSelect+", "select+", "<select name=\"$VAR0$\" id=\"$VAR1$\">\n<option value=\"$VAR2$\">$END$</option>\n</select>", LocalizeValue.localizeTODO("select+"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withVariable("VAR1", "", "\"\"", true);
            builder.withVariable("VAR2", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOptgroup+", "optgroup+", "<optgroup>\n<option value=\"$VAR0$\">$END$</option>\n</optgroup>", LocalizeValue.localizeTODO("optgroup+"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
        try (Builder builder = factory.newBuilder("zenhtmlOptg+", "optg+", "<optgroup>\n<option value=\"$VAR0$\">$END$</option>\n</optgroup>", LocalizeValue.localizeTODO("optg+"))) {
            builder.withReformat();
            builder.withVariable("VAR0", "", "\"\"", true);
            builder.withContext(HtmlTextContextType.class, true);
        }
    }
}