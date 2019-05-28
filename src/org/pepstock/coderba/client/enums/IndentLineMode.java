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
 * Adjust the indentation of the given line. The second argument (which defaults to "smart") may be one of: <br>
 * "prev" Base indentation on the indentation of the previous line.<br>
 * "smart" Use the mode's smart indentation if available, behave like "prev" otherwise.<br>
 * "add" Increase the indentation of the line by one indent unit.<br>
 * "subtract" Reduce the indentation of the line.<br>
 * <br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum IndentLineMode implements Key
{
	/**
	 * Base indentation on the indentation of the previous line
	 */
	PREVIOUS("prev"),
	/**
	 * Use the mode's smart indentation if available, behave like "prev" otherwise
	 */
	SMART("smart"),
	/**
	 * Increase the indentation of the line by one indent unit
	 */
	ADD("add"),
	/**
	 * Reduce the indentation of the line
	 */
	SUBTRACT("subtract");

	private final String value;

	/**
	 * @param value
	 */
	private IndentLineMode(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String value() {
		return value;
	}

}
