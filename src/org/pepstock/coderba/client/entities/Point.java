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
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Point extends BaseEntity {
	
	/**
	 * Name of properties of native object.
	 */
	protected enum Property implements Key
	{
		TOP("top"),
		LEFT("left");

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
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}
	
	/**
	 * 
	 */
	Point(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	
	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	public final void setTop(int top) {
		setValue(Property.TOP, top);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getTop() {
		return getValue(Property.TOP, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the left of area.
	 * 
	 * @return the left of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getLeft() {
		return getValue(Property.LEFT, UndefinedValues.INTEGER);
	}

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	public final void setLeft(int left) {
		setValue(Property.LEFT, left);
	}
}
