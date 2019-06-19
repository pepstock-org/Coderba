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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.commons.ArrayObject;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;

/**
 * Entity which maps the history of changes applied on the document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class History extends BaseEntity {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		DONE("done"),
		UNDONE("undone");

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
	History(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the unmodifiable list of <code>DONE</code> changes on document.
	 * 
	 * @return the unmodifiable list of <code>DONE</code> changes on document
	 */
	public List<HistoryItem> getDone() {
		// gets the array
		ArrayObject array = getArrayValue(Property.DONE);
		// builds the list
		return createListFromArray(array);
	}

	/**
	 * Returns the unmodifiable list of <code>UNDONE</code> changes on document.
	 * 
	 * @return the unmodifiable list of <code>UNDONE</code> changes on document
	 */
	public List<HistoryItem> getUndone() {
		// gets the array
		ArrayObject array = getArrayValue(Property.UNDONE);
		// builds the list
		return createListFromArray(array);
	}

	/**
	 * Creates an unmodifiable list of history items by a native array from CodeMirror methods.
	 * 
	 * @param array array of native items.
	 * @return an unmodifiable list of history items
	 */
	private List<HistoryItem> createListFromArray(ArrayObject array) {
		// creates an empty list
		List<HistoryItem> items = new LinkedList<>();
		// checks if array is consistent
		if (array != null && !array.isEmpty()) {
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// loads the list creating new items
				items.add(new HistoryItem(array.get(i)));
			}
		}
		// returns an unmodifiable list of history items
		return Collections.unmodifiableList(items);
	}

}
