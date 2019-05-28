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
public final class Token extends BaseEntity {
	
	/**
	 * Token factory to build a token by a native object
	 */
	public static final NativeObjectContainerFactory<Token> FACTORY = new TokenFactory();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		START("start"),
		END("end"),
		TYPE("type"),
		STRING("string"),
		STATE("state");

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
	Token(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getStart() {
		return getValue(Property.START, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public int getEnd() {
		return getValue(Property.END, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public String getType() {
		return getValue(Property.TYPE, UndefinedValues.STRING);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public String getString() {
		return getValue(Property.STRING, UndefinedValues.STRING);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	// FIXME
	public Object getState() {
		return getValue(Property.STATE, UndefinedValues.STRING);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class TokenFactory implements NativeObjectContainerFactory<Token> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public Token create(NativeObject nativeObject) {
			return new Token(nativeObject);
		}

	}

}
