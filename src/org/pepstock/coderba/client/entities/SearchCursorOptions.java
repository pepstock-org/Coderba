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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.addons.AddOnSearchcursor;
import org.pepstock.coderba.client.commons.Key;

/**
 * Related to {@link AddOnSearchcursor}, the options to configure the search cursor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SearchCursorOptions extends BaseEntity {

	/**
	 * Default value to disable case folding when matching a string, {@value DEFAULT_CASE_FOLD}.
	 */
	public static final boolean DEFAULT_CASE_FOLD = true;

	/**
	 * Default value to disable multi-line matching for regular expressions, {@value DEFAULT_MULTILINE}.
	 */
	public static final boolean DEFAULT_MULTILINE = true;

	// default instance to avoid null pointer when the search cursor is requested without any options
	static final SearchCursorOptions DEFAULT_INSTANCE = new SearchCursorOptions();
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CASE_FOLD("caseFold"),
		MULTILINE("multiline");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
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

	/**
	 * Sets <code>true</code> to enable case folding when matching a string.
	 * 
	 * @param closeOnEnter <code>true</code> to enable case folding when matching a string
	 */
	public void setCaseFold(boolean closeOnEnter) {
		setValue(Property.CASE_FOLD, closeOnEnter);
	}

	/**
	 * Returns <code>true</code> to enable case folding when matching a string.
	 * 
	 * @return <code>true</code> to enable case folding when matching a string
	 */
	public boolean isCaseFold() {
		return getValue(Property.CASE_FOLD, DEFAULT_CASE_FOLD);
	}

	/**
	 * Sets <code>true</code> to enable multi-line matching for regular expressions.
	 * 
	 * @param closeOnBlur <code>true</code> to enable multi-line matching for regular expressions
	 */
	public void setMultiline(boolean closeOnBlur) {
		setValue(Property.MULTILINE, closeOnBlur);
	}

	/**
	 * Returns <code>true</code> to enable multi-line matching for regular expressions.
	 * 
	 * @return <code>true</code> to enable multi-line matching for regular expressions
	 */
	public boolean isMultiline() {
		return getValue(Property.MULTILINE, DEFAULT_MULTILINE);
	}

}
