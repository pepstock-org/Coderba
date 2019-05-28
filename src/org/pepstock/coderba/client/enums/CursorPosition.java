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
 * Enumeration of values to indicate which end of the selection to return.<br>
 * It may be "start" , "end" , "head"(the side of the selection that moves when you press shift + arrow), or "anchor"(the fixed
 * side of the selection). Default is "head".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum CursorPosition implements Key
{
	/**
	 * End of the selection to return is the side of the selection that moves when you press shift + arrow. Default.
	 */
	HEAD("head"),
	/**
	 *  End of the selection to return is the fixed side of the selection.
	 */
	ANCHOR("anchor"),
	/**
	 * End of the selection to return is end of line
	 */
	END("end"),
	/**
	 * End of the selection to return is start of line
	 */
	START("start");

	private final String value;

	/**
	 * @param value
	 */
	private CursorPosition(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

}
