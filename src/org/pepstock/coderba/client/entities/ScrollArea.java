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
public final class ScrollArea extends BaseEntity {
	
	/**
	 * ScrollaArea factory to build a scroll area by a native object
	 */
	public static final NativeObjectContainerFactory<ScrollArea> FACTORY = new ScrollAreaFactory();
	
	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		TOP("top"),
		LEFT("left"),
		WIDTH("width"),
		HEIGHT("height"),
		CLIENT_WIDTH("clientWidth"),
		CLIENT_HEIGHT("clientHeight");

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
	ScrollArea(NativeObject nativeObject) {
		super(nativeObject);
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
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getWidth() {
		return getValue(Property.WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getClientHeight() {
		return getValue(Property.CLIENT_HEIGHT, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public final int getClientWidth() {
		return getValue(Property.CLIENT_WIDTH, UndefinedValues.INTEGER);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class ScrollAreaFactory implements NativeObjectContainerFactory<ScrollArea> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public ScrollArea create(NativeObject nativeObject) {
			return new ScrollArea(nativeObject);
		}

	}

}
