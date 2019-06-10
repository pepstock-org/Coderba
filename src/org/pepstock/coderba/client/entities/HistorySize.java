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

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;

/**
 * Entity which maps the current size of history of document.<br>
 * It contains the <code>redo</code> and <code>undo</code> amounts.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class HistorySize extends BaseEntity {

	/**
	 * Default value of amount of changes, {@value EMPTY_HISTORY}.
	 */
	public static final int EMPTY_HISTORY = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		UNDO("undo"),
		REDO("redo");

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
	 * Creates a container with a native object instance.<br>
	 * Not visible because must be created internally by the document.
	 * 
	 * @param nativeObject a native object instance
	 */
	HistorySize(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the amount of <code>undo</code> changes.
	 * 
	 * @return the amount of <code>undo</code> changes.
	 */
	public int getUndo() {
		return getValue(Property.UNDO, EMPTY_HISTORY);
	}

	/**
	 * Returns the amount of <code>redo</code> changes.
	 * 
	 * @return the amount of <code>redo</code> changes.
	 */
	public int getRedo() {
		return getValue(Property.REDO, EMPTY_HISTORY);
	}

}