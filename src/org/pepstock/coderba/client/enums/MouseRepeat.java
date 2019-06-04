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
 * They could be: "single", "double" or "triple";
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum MouseRepeat implements Key
{
	SINGLE("single", MouseUnit.CHAR),
	DOUBLE("double", MouseUnit.WORD),
	TRIPLE("triple", MouseUnit.LINE);

	private final String value;

	private final MouseUnit defaultUnit;

	/**
	 * @param value
	 */
	private MouseRepeat(String value, MouseUnit defaultUnit) {
		this.value = value;
		this.defaultUnit = defaultUnit;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

	/**
	 * @return the defaultUnit
	 */
	public final MouseUnit getDefaultUnit() {
		return defaultUnit;
	}

}
