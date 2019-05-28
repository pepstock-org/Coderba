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
 */
public final class Range extends BaseEntity{
	
	/**
	 * Range factory to build a range by a native object
	 */
	public static final NativeObjectContainerFactory<Range> FACTORY = new RangeFactory();
	
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
	
	public Range(Position from, Position to) {
		this(null);
		if (from == null) {
			throw new IllegalArgumentException("From field is null");
		}
		if (to == null) {
			throw new IllegalArgumentException("To field is null");
		}
		setFrom(from);
		setTo(to);
	}
	
	Range(NativeObject nativeObject){
		super(nativeObject);
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public Position getFrom() {
		return Position.FACTORY.create(getValue(Property.FROM));
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	private void setFrom(Position From) {
		setValue(Property.FROM, From);
	}
	
	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public Position getTo() {
		return Position.FACTORY.create(getValue(Property.TO));
	}
	
	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	private void setTo(Position To) {
		setValue(Property.TO, To);
	}
	
	public static Range create(Range source) {
		if (source != null) {
			return new Range(source.getFrom(), source.getTo());
		}
		Position from = new Position();
		Position to = new Position(from.getLine());
		return new Range(from, to);
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class RangeFactory implements NativeObjectContainerFactory<Range> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public Range create(NativeObject nativeObject) {
			Range range = new Range(nativeObject);
			if (!range.has(Property.FROM)) {
				throw new IllegalArgumentException("From field is missing");
			}
			if (!range.has(Property.TO)) {
				throw new IllegalArgumentException("To field is missing");
			}
			return range;
		}

	}

	
}