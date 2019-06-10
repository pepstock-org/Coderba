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
 * Adjust the indentation of the given line.<br>The argument (which defaults to "smart") may be one of: <br>
 * <ul>
 * <li>"prev" Base indentation on the indentation of the previous line.
 * <li>"smart" Use the mode's smart indentation if available, behave like "prev" otherwise.
 * <li>"add" Increase the indentation of the line by one indent unit.
 * <li>"subtract" Reduce the indentation of the line.>
 * </ul>
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

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into Code Mirror configuration
	 */
	private IndentLineMode(String value) {
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
