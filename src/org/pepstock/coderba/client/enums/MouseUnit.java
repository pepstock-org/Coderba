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
 * May be one of the built-in units for a custom unit.<br>
 * The default is to return:<br>
 * <ul>
 * <li>"word" for double clicks
 * <li>"line" for triple clicks
 * <li>"rectangle" for alt-clicks (or, on Chrome OS, meta-shift-clicks)
 * <li>"char" for the rest of cases
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MouseUnit implements Key
{
	/**
	 * Single char will be selected.
	 */
	CHAR("char"),
	/**
	 * Single word will be selected.
	 */
	WORD("word"),
	/**
	 * Single line will be selected.
	 */
	LINE("line"),
	/**
	 * A specific area will be selected.
	 */
	RECTANGLE("rectangle");

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into CodeMirror configuration
	 */
	private MouseUnit(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
