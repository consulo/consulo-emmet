package consulo.emmet;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.template.DefaultLiveTemplatesProvider;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 16-Aug-22
 */
@ExtensionImpl
public class EmmetDefaultLiveTemplatesProvider implements DefaultLiveTemplatesProvider
{
	@Nonnull
	@Override
	public String[] getDefaultLiveTemplateFiles()
	{
		return new String[]{
				"/liveTemplates/zen_html.xml",
				"/liveTemplates/zen_xsl.xml"
		};
	}
}
