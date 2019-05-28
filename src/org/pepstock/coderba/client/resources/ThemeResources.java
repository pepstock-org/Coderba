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
 * Client bundle to reference CodeMirror code editor and other java script codes, always needed to CODERBA.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ThemeResources extends ClientBundle {

	// static reference of this resource
	public static final ThemeResources INSTANCE = GWT.create(ThemeResources.class);

	/**
 * Returns the CSS source code for C3024Day.
 * @return <code>3024-day.css</code> CSS code for C3024Day
 */
 @Source("/js/theme/3024-day.css")
 TextResource m3024Day();

/**
 * Returns the CSS source code for C3024Night.
 * @return <code>3024-night.css</code> CSS code for C3024Night
 */
 @Source("/js/theme/3024-night.css")
 TextResource m3024Night();

/**
 * Returns the CSS source code for Abcdef.
 * @return <code>abcdef.css</code> CSS code for Abcdef
 */
 @Source("/js/theme/abcdef.css")
 TextResource abcdef();

/**
 * Returns the CSS source code for AmbianceMobile.
 * @return <code>ambiance-mobile.css</code> CSS code for AmbianceMobile
 */
 @Source("/js/theme/ambiance-mobile.css")
 TextResource ambianceMobile();

/**
 * Returns the CSS source code for Ambiance.
 * @return <code>ambiance.css</code> CSS code for Ambiance
 */
 @Source("/js/theme/ambiance.css")
 TextResource ambiance();

/**
 * Returns the CSS source code for Base16Dark.
 * @return <code>base16-dark.css</code> CSS code for Base16Dark
 */
 @Source("/js/theme/base16-dark.css")
 TextResource base16Dark();

/**
 * Returns the CSS source code for Base16Light.
 * @return <code>base16-light.css</code> CSS code for Base16Light
 */
 @Source("/js/theme/base16-light.css")
 TextResource base16Light();

/**
 * Returns the CSS source code for Bespin.
 * @return <code>bespin.css</code> CSS code for Bespin
 */
 @Source("/js/theme/bespin.css")
 TextResource bespin();

/**
 * Returns the CSS source code for Blackboard.
 * @return <code>blackboard.css</code> CSS code for Blackboard
 */
 @Source("/js/theme/blackboard.css")
 TextResource blackboard();

/**
 * Returns the CSS source code for Cobalt.
 * @return <code>cobalt.css</code> CSS code for Cobalt
 */
 @Source("/js/theme/cobalt.css")
 TextResource cobalt();

/**
 * Returns the CSS source code for Colorforth.
 * @return <code>colorforth.css</code> CSS code for Colorforth
 */
 @Source("/js/theme/colorforth.css")
 TextResource colorforth();

/**
 * Returns the CSS source code for Darcula.
 * @return <code>darcula.css</code> CSS code for Darcula
 */
 @Source("/js/theme/darcula.css")
 TextResource darcula();

/**
 * Returns the CSS source code for Dracula.
 * @return <code>dracula.css</code> CSS code for Dracula
 */
 @Source("/js/theme/dracula.css")
 TextResource dracula();

/**
 * Returns the CSS source code for DuotoneDark.
 * @return <code>duotone-dark.css</code> CSS code for DuotoneDark
 */
 @Source("/js/theme/duotone-dark.css")
 TextResource duotoneDark();

/**
 * Returns the CSS source code for DuotoneLight.
 * @return <code>duotone-light.css</code> CSS code for DuotoneLight
 */
 @Source("/js/theme/duotone-light.css")
 TextResource duotoneLight();

/**
 * Returns the CSS source code for Eclipse.
 * @return <code>eclipse.css</code> CSS code for Eclipse
 */
 @Source("/js/theme/eclipse.css")
 TextResource eclipse();

/**
 * Returns the CSS source code for Elegant.
 * @return <code>elegant.css</code> CSS code for Elegant
 */
 @Source("/js/theme/elegant.css")
 TextResource elegant();

/**
 * Returns the CSS source code for ErlangDark.
 * @return <code>erlang-dark.css</code> CSS code for ErlangDark
 */
 @Source("/js/theme/erlang-dark.css")
 TextResource erlangDark();

/**
 * Returns the CSS source code for GruvboxDark.
 * @return <code>gruvbox-dark.css</code> CSS code for GruvboxDark
 */
 @Source("/js/theme/gruvbox-dark.css")
 TextResource gruvboxDark();

/**
 * Returns the CSS source code for Hopscotch.
 * @return <code>hopscotch.css</code> CSS code for Hopscotch
 */
 @Source("/js/theme/hopscotch.css")
 TextResource hopscotch();

/**
 * Returns the CSS source code for Icecoder.
 * @return <code>icecoder.css</code> CSS code for Icecoder
 */
 @Source("/js/theme/icecoder.css")
 TextResource icecoder();

/**
 * Returns the CSS source code for Idea.
 * @return <code>idea.css</code> CSS code for Idea
 */
 @Source("/js/theme/idea.css")
 TextResource idea();

/**
 * Returns the CSS source code for Isotope.
 * @return <code>isotope.css</code> CSS code for Isotope
 */
 @Source("/js/theme/isotope.css")
 TextResource isotope();

/**
 * Returns the CSS source code for LesserDark.
 * @return <code>lesser-dark.css</code> CSS code for LesserDark
 */
 @Source("/js/theme/lesser-dark.css")
 TextResource lesserDark();

/**
 * Returns the CSS source code for Liquibyte.
 * @return <code>liquibyte.css</code> CSS code for Liquibyte
 */
 @Source("/js/theme/liquibyte.css")
 TextResource liquibyte();

/**
 * Returns the CSS source code for Lucario.
 * @return <code>lucario.css</code> CSS code for Lucario
 */
 @Source("/js/theme/lucario.css")
 TextResource lucario();

/**
 * Returns the CSS source code for Material.
 * @return <code>material.css</code> CSS code for Material
 */
 @Source("/js/theme/material.css")
 TextResource material();

/**
 * Returns the CSS source code for Mbo.
 * @return <code>mbo.css</code> CSS code for Mbo
 */
 @Source("/js/theme/mbo.css")
 TextResource mbo();

/**
 * Returns the CSS source code for MdnLike.
 * @return <code>mdn-like.css</code> CSS code for MdnLike
 */
 @Source("/js/theme/mdn-like.css")
 TextResource mdnLike();

/**
 * Returns the CSS source code for Midnight.
 * @return <code>midnight.css</code> CSS code for Midnight
 */
 @Source("/js/theme/midnight.css")
 TextResource midnight();

/**
 * Returns the CSS source code for Monokai.
 * @return <code>monokai.css</code> CSS code for Monokai
 */
 @Source("/js/theme/monokai.css")
 TextResource monokai();

/**
 * Returns the CSS source code for Neat.
 * @return <code>neat.css</code> CSS code for Neat
 */
 @Source("/js/theme/neat.css")
 TextResource neat();

/**
 * Returns the CSS source code for Neo.
 * @return <code>neo.css</code> CSS code for Neo
 */
 @Source("/js/theme/neo.css")
 TextResource neo();

/**
 * Returns the CSS source code for Night.
 * @return <code>night.css</code> CSS code for Night
 */
 @Source("/js/theme/night.css")
 TextResource night();

/**
 * Returns the CSS source code for Nord.
 * @return <code>nord.css</code> CSS code for Nord
 */
 @Source("/js/theme/nord.css")
 TextResource nord();

/**
 * Returns the CSS source code for OceanicNext.
 * @return <code>oceanic-next.css</code> CSS code for OceanicNext
 */
 @Source("/js/theme/oceanic-next.css")
 TextResource oceanicNext();

/**
 * Returns the CSS source code for PandaSyntax.
 * @return <code>panda-syntax.css</code> CSS code for PandaSyntax
 */
 @Source("/js/theme/panda-syntax.css")
 TextResource pandaSyntax();

/**
 * Returns the CSS source code for ParaisoDark.
 * @return <code>paraiso-dark.css</code> CSS code for ParaisoDark
 */
 @Source("/js/theme/paraiso-dark.css")
 TextResource paraisoDark();

/**
 * Returns the CSS source code for ParaisoLight.
 * @return <code>paraiso-light.css</code> CSS code for ParaisoLight
 */
 @Source("/js/theme/paraiso-light.css")
 TextResource paraisoLight();

/**
 * Returns the CSS source code for PastelOnDark.
 * @return <code>pastel-on-dark.css</code> CSS code for PastelOnDark
 */
 @Source("/js/theme/pastel-on-dark.css")
 TextResource pastelOnDark();

/**
 * Returns the CSS source code for Railscasts.
 * @return <code>railscasts.css</code> CSS code for Railscasts
 */
 @Source("/js/theme/railscasts.css")
 TextResource railscasts();

/**
 * Returns the CSS source code for Rubyblue.
 * @return <code>rubyblue.css</code> CSS code for Rubyblue
 */
 @Source("/js/theme/rubyblue.css")
 TextResource rubyblue();

/**
 * Returns the CSS source code for Seti.
 * @return <code>seti.css</code> CSS code for Seti
 */
 @Source("/js/theme/seti.css")
 TextResource seti();

/**
 * Returns the CSS source code for Shadowfox.
 * @return <code>shadowfox.css</code> CSS code for Shadowfox
 */
 @Source("/js/theme/shadowfox.css")
 TextResource shadowfox();

/**
 * Returns the CSS source code for Solarized.
 * @return <code>solarized.css</code> CSS code for Solarized
 */
 @Source("/js/theme/solarized.css")
 TextResource solarized();

/**
 * Returns the CSS source code for Ssms.
 * @return <code>ssms.css</code> CSS code for Ssms
 */
 @Source("/js/theme/ssms.css")
 TextResource ssms();

/**
 * Returns the CSS source code for TheMatrix.
 * @return <code>the-matrix.css</code> CSS code for TheMatrix
 */
 @Source("/js/theme/the-matrix.css")
 TextResource theMatrix();

/**
 * Returns the CSS source code for TomorrowNightBright.
 * @return <code>tomorrow-night-bright.css</code> CSS code for TomorrowNightBright
 */
 @Source("/js/theme/tomorrow-night-bright.css")
 TextResource tomorrowNightBright();

/**
 * Returns the CSS source code for TomorrowNightEighties.
 * @return <code>tomorrow-night-eighties.css</code> CSS code for TomorrowNightEighties
 */
 @Source("/js/theme/tomorrow-night-eighties.css")
 TextResource tomorrowNightEighties();

/**
 * Returns the CSS source code for Ttcn.
 * @return <code>ttcn.css</code> CSS code for Ttcn
 */
 @Source("/js/theme/ttcn.css")
 TextResource ttcn();

/**
 * Returns the CSS source code for Twilight.
 * @return <code>twilight.css</code> CSS code for Twilight
 */
 @Source("/js/theme/twilight.css")
 TextResource twilight();

/**
 * Returns the CSS source code for VibrantInk.
 * @return <code>vibrant-ink.css</code> CSS code for VibrantInk
 */
 @Source("/js/theme/vibrant-ink.css")
 TextResource vibrantInk();

/**
 * Returns the CSS source code for XqDark.
 * @return <code>xq-dark.css</code> CSS code for XqDark
 */
 @Source("/js/theme/xq-dark.css")
 TextResource xqDark();

/**
 * Returns the CSS source code for XqLight.
 * @return <code>xq-light.css</code> CSS code for XqLight
 */
 @Source("/js/theme/xq-light.css")
 TextResource xqLight();

/**
 * Returns the CSS source code for Yeti.
 * @return <code>yeti.css</code> CSS code for Yeti
 */
 @Source("/js/theme/yeti.css")
 TextResource yeti();

/**
 * Returns the CSS source code for Yonce.
 * @return <code>yonce.css</code> CSS code for Yonce
 */
 @Source("/js/theme/yonce.css")
 TextResource yonce();

/**
 * Returns the CSS source code for Zenburn.
 * @return <code>zenburn.css</code> CSS code for Zenburn
 */
 @Source("/js/theme/zenburn.css")
 TextResource zenburn();



}
