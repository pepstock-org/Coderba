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
import org.pepstock.coderba.client.commons.NativeObjectContainerFactory;
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Coordinate extends BaseEntity {
	
	/**
	 * Area factory to build an area by a native object
	 */
	public static final NativeObjectContainerFactory<Coordinate> FACTORY = new CoordinateFactory();
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		FROM("from"),
		TO("to");

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
	Coordinate(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getFrom() {
		return getValue(Property.FROM, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getTo() {
		return getValue(Property.TO, UndefinedValues.INTEGER);
	}
	
	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	public void setFrom(int from) {
		setValue(Property.FROM, from);
	}

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	public void setTo(int to) {
		setValue(Property.TO, to);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class CoordinateFactory implements NativeObjectContainerFactory<Coordinate> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public Coordinate create(NativeObject nativeObject) {
			return new Coordinate(nativeObject);
		}

	}

}
