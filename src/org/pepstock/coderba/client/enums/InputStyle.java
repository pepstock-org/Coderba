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
 * Enumeration to define the way CodeMirror handles input and focus.<br>
 * On mobile browsers, the default is "contenteditable".<br>
 * On desktop browsers, the default is "textarea".
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum InputStyle implements Key
{
	/**
	 * Input and focus manage by a text area element.
	 */
	TEXT_AREA("textarea"),
	/**
	 * Input and focus manage by a content editable element.
	 */
	CONTENT_EDITABLE("contenteditable");

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into CodeMirror configuration
	 */
	private InputStyle(String value) {
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
