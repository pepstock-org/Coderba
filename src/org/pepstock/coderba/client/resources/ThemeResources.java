/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.coderba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CodeMirror code editor and other java script codes, always needed to Acerba.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ThemeResources extends ClientBundle {

	// static reference of this resource
	public static final ThemeResources INSTANCE = GWT.create(ThemeResources.class);

	/**
	 * Returns the CSS source code for ThemeC3024Day.
	 * 
	 * @return <code>3024-day.css</code> CSS code for ThemeC3024Day
	 */
	@Source("/js/theme/3024-day.css")
	TextResource m3024Day();

	/**
	 * Returns the CSS source code for ThemeC3024Night.
	 * 
	 * @return <code>3024-night.css</code> CSS code for ThemeC3024Night
	 */
	@Source("/js/theme/3024-night.css")
	TextResource m3024Night();

	/**
	 * Returns the CSS source code for ThemeAbcdef.
	 * 
	 * @return <code>abcdef.css</code> CSS code for ThemeAbcdef
	 */
	@Source("/js/theme/abcdef.css")
	TextResource abcdef();

	/**
	 * Returns the CSS source code for ThemeAmbianceMobile.
	 * 
	 * @return <code>ambiance-mobile.css</code> CSS code for ThemeAmbianceMobile
	 */
	@Source("/js/theme/ambiance-mobile.css")
	TextResource ambianceMobile();

	/**
	 * Returns the CSS source code for ThemeAmbiance.
	 * 
	 * @return <code>ambiance.css</code> CSS code for ThemeAmbiance
	 */
	@Source("/js/theme/ambiance.css")
	TextResource ambiance();

	/**
	 * Returns the CSS source code for ThemeBase16Dark.
	 * 
	 * @return <code>base16-dark.css</code> CSS code for ThemeBase16Dark
	 */
	@Source("/js/theme/base16-dark.css")
	TextResource base16Dark();

	/**
	 * Returns the CSS source code for ThemeBase16Light.
	 * 
	 * @return <code>base16-light.css</code> CSS code for ThemeBase16Light
	 */
	@Source("/js/theme/base16-light.css")
	TextResource base16Light();

	/**
	 * Returns the CSS source code for ThemeBespin.
	 * 
	 * @return <code>bespin.css</code> CSS code for ThemeBespin
	 */
	@Source("/js/theme/bespin.css")
	TextResource bespin();

	/**
	 * Returns the CSS source code for ThemeBlackboard.
	 * 
	 * @return <code>blackboard.css</code> CSS code for ThemeBlackboard
	 */
	@Source("/js/theme/blackboard.css")
	TextResource blackboard();

	/**
	 * Returns the CSS source code for ThemeCobalt.
	 * 
	 * @return <code>cobalt.css</code> CSS code for ThemeCobalt
	 */
	@Source("/js/theme/cobalt.css")
	TextResource cobalt();

	/**
	 * Returns the CSS source code for ThemeColorforth.
	 * 
	 * @return <code>colorforth.css</code> CSS code for ThemeColorforth
	 */
	@Source("/js/theme/colorforth.css")
	TextResource colorforth();

	/**
	 * Returns the CSS source code for ThemeDarcula.
	 * 
	 * @return <code>darcula.css</code> CSS code for ThemeDarcula
	 */
	@Source("/js/theme/darcula.css")
	TextResource darcula();

	/**
	 * Returns the CSS source code for ThemeDracula.
	 * 
	 * @return <code>dracula.css</code> CSS code for ThemeDracula
	 */
	@Source("/js/theme/dracula.css")
	TextResource dracula();

	/**
	 * Returns the CSS source code for ThemeDuotoneDark.
	 * 
	 * @return <code>duotone-dark.css</code> CSS code for ThemeDuotoneDark
	 */
	@Source("/js/theme/duotone-dark.css")
	TextResource duotoneDark();

	/**
	 * Returns the CSS source code for ThemeDuotoneLight.
	 * 
	 * @return <code>duotone-light.css</code> CSS code for ThemeDuotoneLight
	 */
	@Source("/js/theme/duotone-light.css")
	TextResource duotoneLight();

	/**
	 * Returns the CSS source code for ThemeEclipse.
	 * 
	 * @return <code>eclipse.css</code> CSS code for ThemeEclipse
	 */
	@Source("/js/theme/eclipse.css")
	TextResource eclipse();

	/**
	 * Returns the CSS source code for ThemeElegant.
	 * 
	 * @return <code>elegant.css</code> CSS code for ThemeElegant
	 */
	@Source("/js/theme/elegant.css")
	TextResource elegant();

	/**
	 * Returns the CSS source code for ThemeErlangDark.
	 * 
	 * @return <code>erlang-dark.css</code> CSS code for ThemeErlangDark
	 */
	@Source("/js/theme/erlang-dark.css")
	TextResource erlangDark();

	/**
	 * Returns the CSS source code for ThemeGruvboxDark.
	 * 
	 * @return <code>gruvbox-dark.css</code> CSS code for ThemeGruvboxDark
	 */
	@Source("/js/theme/gruvbox-dark.css")
	TextResource gruvboxDark();

	/**
	 * Returns the CSS source code for ThemeHopscotch.
	 * 
	 * @return <code>hopscotch.css</code> CSS code for ThemeHopscotch
	 */
	@Source("/js/theme/hopscotch.css")
	TextResource hopscotch();

	/**
	 * Returns the CSS source code for ThemeIcecoder.
	 * 
	 * @return <code>icecoder.css</code> CSS code for ThemeIcecoder
	 */
	@Source("/js/theme/icecoder.css")
	TextResource icecoder();

	/**
	 * Returns the CSS source code for ThemeIdea.
	 * 
	 * @return <code>idea.css</code> CSS code for ThemeIdea
	 */
	@Source("/js/theme/idea.css")
	TextResource idea();

	/**
	 * Returns the CSS source code for ThemeIsotope.
	 * 
	 * @return <code>isotope.css</code> CSS code for ThemeIsotope
	 */
	@Source("/js/theme/isotope.css")
	TextResource isotope();

	/**
	 * Returns the CSS source code for ThemeLesserDark.
	 * 
	 * @return <code>lesser-dark.css</code> CSS code for ThemeLesserDark
	 */
	@Source("/js/theme/lesser-dark.css")
	TextResource lesserDark();

	/**
	 * Returns the CSS source code for ThemeLiquibyte.
	 * 
	 * @return <code>liquibyte.css</code> CSS code for ThemeLiquibyte
	 */
	@Source("/js/theme/liquibyte.css")
	TextResource liquibyte();

	/**
	 * Returns the CSS source code for ThemeLucario.
	 * 
	 * @return <code>lucario.css</code> CSS code for ThemeLucario
	 */
	@Source("/js/theme/lucario.css")
	TextResource lucario();

	/**
	 * Returns the CSS source code for ThemeMaterial.
	 * 
	 * @return <code>material.css</code> CSS code for ThemeMaterial
	 */
	@Source("/js/theme/material.css")
	TextResource material();

	/**
	 * Returns the CSS source code for ThemeMbo.
	 * 
	 * @return <code>mbo.css</code> CSS code for ThemeMbo
	 */
	@Source("/js/theme/mbo.css")
	TextResource mbo();

	/**
	 * Returns the CSS source code for ThemeMdnLike.
	 * 
	 * @return <code>mdn-like.css</code> CSS code for ThemeMdnLike
	 */
	@Source("/js/theme/mdn-like.css")
	TextResource mdnLike();

	/**
	 * Returns the CSS source code for ThemeMidnight.
	 * 
	 * @return <code>midnight.css</code> CSS code for ThemeMidnight
	 */
	@Source("/js/theme/midnight.css")
	TextResource midnight();

	/**
	 * Returns the CSS source code for ThemeMonokai.
	 * 
	 * @return <code>monokai.css</code> CSS code for ThemeMonokai
	 */
	@Source("/js/theme/monokai.css")
	TextResource monokai();

	/**
	 * Returns the CSS source code for ThemeNeat.
	 * 
	 * @return <code>neat.css</code> CSS code for ThemeNeat
	 */
	@Source("/js/theme/neat.css")
	TextResource neat();

	/**
	 * Returns the CSS source code for ThemeNeo.
	 * 
	 * @return <code>neo.css</code> CSS code for ThemeNeo
	 */
	@Source("/js/theme/neo.css")
	TextResource neo();

	/**
	 * Returns the CSS source code for ThemeNight.
	 * 
	 * @return <code>night.css</code> CSS code for ThemeNight
	 */
	@Source("/js/theme/night.css")
	TextResource night();

	/**
	 * Returns the CSS source code for ThemeNord.
	 * 
	 * @return <code>nord.css</code> CSS code for ThemeNord
	 */
	@Source("/js/theme/nord.css")
	TextResource nord();

	/**
	 * Returns the CSS source code for ThemeOceanicNext.
	 * 
	 * @return <code>oceanic-next.css</code> CSS code for ThemeOceanicNext
	 */
	@Source("/js/theme/oceanic-next.css")
	TextResource oceanicNext();

	/**
	 * Returns the CSS source code for ThemePandaSyntax.
	 * 
	 * @return <code>panda-syntax.css</code> CSS code for ThemePandaSyntax
	 */
	@Source("/js/theme/panda-syntax.css")
	TextResource pandaSyntax();

	/**
	 * Returns the CSS source code for ThemeParaisoDark.
	 * 
	 * @return <code>paraiso-dark.css</code> CSS code for ThemeParaisoDark
	 */
	@Source("/js/theme/paraiso-dark.css")
	TextResource paraisoDark();

	/**
	 * Returns the CSS source code for ThemeParaisoLight.
	 * 
	 * @return <code>paraiso-light.css</code> CSS code for ThemeParaisoLight
	 */
	@Source("/js/theme/paraiso-light.css")
	TextResource paraisoLight();

	/**
	 * Returns the CSS source code for ThemePastelOnDark.
	 * 
	 * @return <code>pastel-on-dark.css</code> CSS code for ThemePastelOnDark
	 */
	@Source("/js/theme/pastel-on-dark.css")
	TextResource pastelOnDark();

	/**
	 * Returns the CSS source code for ThemeRailscasts.
	 * 
	 * @return <code>railscasts.css</code> CSS code for ThemeRailscasts
	 */
	@Source("/js/theme/railscasts.css")
	TextResource railscasts();

	/**
	 * Returns the CSS source code for ThemeRubyblue.
	 * 
	 * @return <code>rubyblue.css</code> CSS code for ThemeRubyblue
	 */
	@Source("/js/theme/rubyblue.css")
	TextResource rubyblue();

	/**
	 * Returns the CSS source code for ThemeSeti.
	 * 
	 * @return <code>seti.css</code> CSS code for ThemeSeti
	 */
	@Source("/js/theme/seti.css")
	TextResource seti();

	/**
	 * Returns the CSS source code for ThemeShadowfox.
	 * 
	 * @return <code>shadowfox.css</code> CSS code for ThemeShadowfox
	 */
	@Source("/js/theme/shadowfox.css")
	TextResource shadowfox();

	/**
	 * Returns the CSS source code for ThemeSolarized.
	 * 
	 * @return <code>solarized.css</code> CSS code for ThemeSolarized
	 */
	@Source("/js/theme/solarized.css")
	TextResource solarized();

	/**
	 * Returns the CSS source code for ThemeSsms.
	 * 
	 * @return <code>ssms.css</code> CSS code for ThemeSsms
	 */
	@Source("/js/theme/ssms.css")
	TextResource ssms();

	/**
	 * Returns the CSS source code for ThemeTheMatrix.
	 * 
	 * @return <code>the-matrix.css</code> CSS code for ThemeTheMatrix
	 */
	@Source("/js/theme/the-matrix.css")
	TextResource theMatrix();

	/**
	 * Returns the CSS source code for ThemeTomorrowNightBright.
	 * 
	 * @return <code>tomorrow-night-bright.css</code> CSS code for ThemeTomorrowNightBright
	 */
	@Source("/js/theme/tomorrow-night-bright.css")
	TextResource tomorrowNightBright();

	/**
	 * Returns the CSS source code for ThemeTomorrowNightEighties.
	 * 
	 * @return <code>tomorrow-night-eighties.css</code> CSS code for ThemeTomorrowNightEighties
	 */
	@Source("/js/theme/tomorrow-night-eighties.css")
	TextResource tomorrowNightEighties();

	/**
	 * Returns the CSS source code for ThemeTtcn.
	 * 
	 * @return <code>ttcn.css</code> CSS code for ThemeTtcn
	 */
	@Source("/js/theme/ttcn.css")
	TextResource ttcn();

	/**
	 * Returns the CSS source code for ThemeTwilight.
	 * 
	 * @return <code>twilight.css</code> CSS code for ThemeTwilight
	 */
	@Source("/js/theme/twilight.css")
	TextResource twilight();

	/**
	 * Returns the CSS source code for ThemeVibrantInk.
	 * 
	 * @return <code>vibrant-ink.css</code> CSS code for ThemeVibrantInk
	 */
	@Source("/js/theme/vibrant-ink.css")
	TextResource vibrantInk();

	/**
	 * Returns the CSS source code for ThemeXqDark.
	 * 
	 * @return <code>xq-dark.css</code> CSS code for ThemeXqDark
	 */
	@Source("/js/theme/xq-dark.css")
	TextResource xqDark();

	/**
	 * Returns the CSS source code for ThemeXqLight.
	 * 
	 * @return <code>xq-light.css</code> CSS code for ThemeXqLight
	 */
	@Source("/js/theme/xq-light.css")
	TextResource xqLight();

	/**
	 * Returns the CSS source code for ThemeYeti.
	 * 
	 * @return <code>yeti.css</code> CSS code for ThemeYeti
	 */
	@Source("/js/theme/yeti.css")
	TextResource yeti();

	/**
	 * Returns the CSS source code for ThemeYonce.
	 * 
	 * @return <code>yonce.css</code> CSS code for ThemeYonce
	 */
	@Source("/js/theme/yonce.css")
	TextResource yonce();

	/**
	 * Returns the CSS source code for ThemeZenburn.
	 * 
	 * @return <code>zenburn.css</code> CSS code for ThemeZenburn
	 */
	@Source("/js/theme/zenburn.css")
	TextResource zenburn();

}