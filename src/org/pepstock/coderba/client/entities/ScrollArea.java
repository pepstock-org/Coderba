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
 * FIXME
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScrollArea extends BaseEntity {

	/**
	 * Name of properties of object.
	 */
	private enum Property implements Key
	{
		TOP("top"),
		LEFT("left"),
		HEIGHT("height"),
		WIDTH("width"),
		CLIENT_HEIGHT("clientHeight"),
		CLIENT_WIDTH("clientWidth");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into object.
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
	 * @paramObject
	 */
	ScrollArea(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area.
	 */

	public int getTop() {
		return getValue(Property.TOP, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the left of area.
	 * 
	 * @return the left of area.
	 */

	public int getLeft() {
		return getValue(Property.LEFT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height of area.
	 * 
	 * @return the height of area.
	 */

	public int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the width of area.
	 * 
	 * @return the width of area.
	 */

	public int getWidth() {
		return getValue(Property.WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the client height of area.
	 * 
	 * @return the client height of area.
	 */

	public int getClientHeight() {
		return getValue(Property.CLIENT_HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the client width of area.
	 * 
	 * @return the client width of area.
	 */

	public int getClientWidth() {
		return getValue(Property.CLIENT_WIDTH, UndefinedValues.INTEGER);
	}

}
