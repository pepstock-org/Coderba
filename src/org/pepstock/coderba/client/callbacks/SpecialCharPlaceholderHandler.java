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
package org.pepstock.coderba.client.callbacks;

import com.google.gwt.dom.client.Element;

/**
 * A function that, given a special character identified by the specialChars option, produces a DOM node that is used to
 * represent the character.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface SpecialCharPlaceholderHandler {

	/**
	 * A function that, given a special character identified by the specialChars option, produces a DOM node that is used to
	 * represent the character.
	 * 
	 * @param character a special character identified by the specialChars option
	 * @return a DOM node that is used to represent the character.
	 */
	Element handle(char character);

}
