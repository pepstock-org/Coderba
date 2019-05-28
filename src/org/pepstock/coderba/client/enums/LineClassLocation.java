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
package org.pepstock.coderba.client.enums;

import org.pepstock.coderba.client.commons.Key;

/**
 * Determines to which element this class should be applied, can can be one of:<br>
 * <br>
 * "text" (the text element, which lies in front of the selection),<br>
 * "background" (a background element that will be behind the selection),<br>
 * "gutter" (the line's gutter space), <br>
 * "wrap" (the wrapper node that wraps all of the line's elements, including gutter elements).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum LineClassLocation implements Key
{
	/**
	 * the text element, which lies in front of the selection
	 */
	TEXT("text"),
	/**
	 * a background element that will be behind the selection
	 */
	BACKGROUND("background"),
	/**
	 * the line's gutter space
	 */
	GUTTER("gutter"),
	/**
	 * the wrapper node that wraps all of the line's elements, including gutter elements
	 */
	WRAP("wrap");

	private final String value;

	/**
	 * @param value
	 */
	private LineClassLocation(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

}
