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
 * Object which contains 2 values to identify a coordinate (as integer): from and to.<br>
 * It is used by CodeMirror methods
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Coordinate extends BaseEntity {

	/**
	 * Default value of <code>from</code> and <code>to</code> when missing.
	 */
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
	private Coordinate() {
		// creates an empty object
		this(null);
	}

	/**
	 * Creates a container with a native object instance.<br>
	 * This is used when coordinate has been passed by a method of CodeMirror.
	 * 
	 * @param nativeObject a native object instance
	 */
	Coordinate(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Creates a coordinate by a from and to integer values.
	 * 
	 * @param from from value
	 * @param to to value
	 * @return returns the coordinate
	 */
	public static Coordinate create(int from, int to) {
		// creates a coordinate object
		Coordinate coordinate = new Coordinate();
		// sets values
		coordinate.setFrom(from);
		coordinate.setTo(to);
		// returns coordinate instance
		return coordinate;
	}

	/**
	 * Returns the <code>from</code> value.
	 * 
	 * @return the <code>from</code> value
	 */
	public int getFrom() {
		return getValue(Property.FROM, DEFAULT_COORDINATE);
	}

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the <code>from</code> value.
	 * 
	 * @param from the <code>from</code> value
	 */
	private void setFrom(int from) {
		setValue(Property.FROM, from);
	}

	/**
	 * Returns the <code>to</code> value.
	 * 
	 * @return the <code>to</code> value
	 */
	public int getTo() {
		return getValue(Property.TO, DEFAULT_COORDINATE);
	}

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the <code>to</code> value.
	 * 
	 * @param to the <code>to</code> value
	 */
	private void setTo(int to) {
		setValue(Property.TO, to);
	}

}
