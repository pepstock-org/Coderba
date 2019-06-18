/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.commons.Key;

/**
 * Defines a stroke and its methods.<br>
 * A stroke is a set of keys used to invoke commands on editor.
 * 
 * @author Andrea "Stock" Stocchero
 * @see KeyStroke
 * @see KeyMultiStroke
 */
public interface Stroke extends Key {

	/**
	 * Returns <code>true</code> if the stroke is a container of other strokes. By default, returns <code>false</code>.
	 * 
	 * @return <code>true</code> if the stroke is a container of other strokes.  By default, returns <code>false</code>
	 */
	default boolean isMulti() {
		return false;
	}

}
