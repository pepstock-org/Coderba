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
 * Defines the enumeration of configure mouse repeat values.<br>
 * They could be: "single", "double" or "triple".<br>
 * For every kind of mouse repeat item, it has been defined also the default mouse unit.
 * 
 * @see MouseUnit
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MouseRepeat implements Key
{
	/**
	 * Single mouse event.
	 */
	SINGLE("single", MouseUnit.CHAR),
	/**
	 * Double mouse event.
	 */
	DOUBLE("double", MouseUnit.WORD),
	/**
	 * Triple mouse event.
	 */
	TRIPLE("triple", MouseUnit.LINE);

	// internal value to use into CodeMirror configuration
	private final String value;
	// mouse unit to use into mouse configuration
	private final MouseUnit defaultUnit;

	/**
	 * Creates the enumeration by its value and mouse unit.
	 * 
	 * @param value string value to use into Code Mirror configuration
	 * @param defaultUnit mouse unit which is used by CodeMirror as default, related to mouse repeat
	 */
	private MouseRepeat(String value, MouseUnit defaultUnit) {
		this.value = value;
		this.defaultUnit = defaultUnit;
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

	/**
	 * Returns the mouse unit which is used by CodeMirror as default, related to mouse repeat.
	 * 
	 * @return the mouse unit which is used by CodeMirror as default, related to mouse repeat
	 */
	public final MouseUnit getDefaultUnit() {
		return defaultUnit;
	}

}
