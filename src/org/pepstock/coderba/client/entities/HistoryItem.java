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
import org.pepstock.coderba.client.commons.NativeObjectContainerFactory;

/**
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class HistoryItem extends BaseEntity {
	
	static final HistoryItemFactory FACTORY = new HistoryItemFactory();

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
	 * 
	 */
	HistoryItem(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasChanges() {
		return has(Property.CHANGES);
	}

	public List<Anchor> getRanges() {
		ArrayEntity<Anchor> array = getArrayValue(Property.RANGES);
		return ArrayListHelper.unmodifiableList(array);
	}

	public List<HistoryChangeItem> getChanges() {
		ArrayEntity<HistoryChangeItem> array = getArrayValue(Property.CHANGES);
		return ArrayListHelper.unmodifiableList(array);
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class HistoryItemFactory implements NativeObjectContainerFactory<HistoryItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public HistoryItem create(NativeObject nativeObject) {
			return new HistoryItem(nativeObject);
		}

	}

}
