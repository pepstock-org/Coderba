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
 * Configuration object to define the type of coordinates that Code Mirror should provide.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum CoordinatesMode implements Key
{
	/**
	 * The coordinates are relative to the top-left corner of the currently visible (scrolled) window.
	 */
	WINDOW("window"),
	/**
	 * The coordinates are relative to the top-left corner of the page.
	 */
	PAGE("page"),
	/**
	 * The coordinates will be relative to the top-left corner of the editable document.
	 */
	LOCAL("local");

	// internal value to use into CodeMirror configuration
	private final String value;

	/**
	 * Creates the enumeration by its value.
	 * 
	 * @param value string value to use into Code Mirror configuration
	 */
	private CoordinatesMode(String value) {
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
