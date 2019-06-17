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
import org.pepstock.coderba.client.entities.Position;

/**
 * Enumeration that is used as property of Position object, holding "before" or "after", whether the position is associated with
 * the character before or after it.<br>
 * This influences, for example, where the cursor is drawn on a line-break or bidi-direction boundary.
 * 
 * @author Andrea "Stock" Stocchero
 * @see Position
 */
public enum StickyPosition implements Key
{
	/**
	 * Not set, uses default.
	 */
	NULL("null"),
	/**
	 * The position is associated with the character after it.
	 */
	AFTER("after"),
	/**
	 * The position is associated with the character before it.
	 */
	BEFORE("before");

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into Code Mirror configuration
	 */
	private StickyPosition(String value) {
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
