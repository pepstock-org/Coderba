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
package org.pepstock.coderba.client;

/**
 * The theme to style the editor.<br>
 * A theme is related to CSS file defining the corresponding styles.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Theme extends InjectableItem {

	/**
	 * Creates the object with the mandatory name of theme.
	 * 
	 * @param name the mandatory name of theme
	 */
	protected Theme(String name) {
		super(name);
	}

}
