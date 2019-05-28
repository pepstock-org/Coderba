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
 * Defines the enumeration of configure mouse behaviors.<br>
 * The unit by which to select.<br>
 * May be one of the built-in units for a custom unit. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for alt-clicks (or, on Chrome OS,
 * meta-shift-clicks), and "char" otherwise.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MouseUnit implements Key
{
	CHAR("char"),
	WORD("word"),
	LINE("line"),
	RECTANGLE("rectangle");

	private final String value;

	/**
	 * @param value
	 */
	private MouseUnit(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

}
