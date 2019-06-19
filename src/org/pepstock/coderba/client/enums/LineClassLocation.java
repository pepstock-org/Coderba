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
 * <ul>
 * <li>"text" (the text element, which lies in front of the selection)
 * <li>"background" (a background element that will be behind the selection)
 * <li>"gutter" (the line's gutter space)
 * <li>"wrap" (the wrapper node that wraps all of the line's elements, including gutter elements)
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum LineClassLocation implements Key
{
	/**
	 * The text element, which lies in front of the selection
	 */
	TEXT("text"),
	/**
	 * A background element that will be behind the selection
	 */
	BACKGROUND("background"),
	/**
	 * The line's gutter space
	 */
	GUTTER("gutter"),
	/**
	 * The wrapper node that wraps all of the line's elements, including gutter elements
	 */
	WRAP("wrap");

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into CodeMirror configuration
	 */
	private LineClassLocation(String value) {
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
