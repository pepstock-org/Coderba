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
 * 
 * FIXME change into baseEntity
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Coordinate extends BaseEntity {

	public static final int DEFAULT_COORDINATE = 0;

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
		 * @see org.pepstock.coderba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}
	
	/**
	 * To avoid any instantiation
	 */
	private Coordinate(){
		this(null);
	}

	/**
	 * 
	 * @param nativeObject
	 */
	Coordinate(NativeObject nativeObject) {
		super(nativeObject);
	}

	public static Coordinate create(int from, int to) {
		Coordinate coordinate = new Coordinate();
		coordinate.setFrom(from);
		coordinate.setTo(to);
		return coordinate;
	}

	public int getFrom() {
		return getValue(Property.FROM, DEFAULT_COORDINATE);
	}

	private void setFrom(int from) {
		setValue(Property.FROM, from);
	}

	public int getTo() {
		return getValue(Property.TO, DEFAULT_COORDINATE);
	}

	private void setTo(int to) {
		setValue(Property.TO, to);
	}

}
