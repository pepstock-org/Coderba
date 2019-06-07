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
 * FIXME
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

	History(NativeObject nativeObject) {
		super(nativeObject);
	}

	public List<HistoryItem> getDone() {
		ArrayObject array = getArrayValue(Property.DONE);
		return createListFromArray(array);
	}

	public List<HistoryItem> getUndone() {
		ArrayObject array = getArrayValue(Property.UNDONE);
		return createListFromArray(array);
	}

	private List<HistoryItem> createListFromArray(ArrayObject array) {
		List<HistoryItem> items = new LinkedList<>();
		if (array != null && !array.isEmpty()) {
			for (int i = 0; i < array.length(); i++) {
				items.add(new HistoryItem(array.get(i)));
			}
		}
		return Collections.unmodifiableList(items);
	}

}
