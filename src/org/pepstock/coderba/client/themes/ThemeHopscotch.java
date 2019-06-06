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
package org.pepstock.coderba.client.themes;

import org.pepstock.coderba.client.Theme;
import org.pepstock.coderba.client.resources.StyleResource;
import org.pepstock.coderba.client.resources.ThemeResources;

/**
 * Is the "hopscotch" theme to enable the editor to apply the theme.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ThemeHopscotch extends Theme {
	// name of editor theme
	private static final String NAME = "hopscotch";

	/**
	 * Instance of editor theme.
	 */
	public static final ThemeHopscotch THEME = new ThemeHopscotch();

	/**
	 * To avoid any instantiation
	 */
	private ThemeHopscotch() {
		// passes the name of theme
		super(NAME);
		getResources().add(new StyleResource(ThemeResources.INSTANCE.hopscotch()));
	}

}
