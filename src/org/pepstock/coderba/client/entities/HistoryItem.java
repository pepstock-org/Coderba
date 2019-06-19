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

import java.util.List;

import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;

/**
 * Entity which maps the history item of CodeMirror document.<br>
 * It contains the changes and the ranges where the changes are applied.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class HistoryItem extends BaseEntity {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		RANGES("ranges"),
		CHANGES("changes");

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
	HistoryItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> if the item contains changes.
	 * 
	 * @return <code>true</code> if the item contains changes
	 */
	public boolean hasChanges() {
		return has(Property.CHANGES);
	}

	/**
	 * Returns an unmodifiable list of anchors which represents the ranges of changes.
	 * 
	 * @return an unmodifiable list of anchors
	 */
	public List<Anchor> getRanges() {
		// gets array
		ArrayEntity<Anchor> array = getArrayValue(Property.RANGES);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns an unmodifiable list of changes.
	 * 
	 * @return an unmodifiable list of changes
	 */
	public List<HistoryChangeItem> getChanges() {
		// gets array
		ArrayEntity<HistoryChangeItem> array = getArrayValue(Property.CHANGES);
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

}
