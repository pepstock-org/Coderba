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

import com.google.gwt.dom.client.Element;

/**
 * Options to use to set a text marker.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class BookmarkOptions extends BaseEntity {

	public static final Element DEFAULT_WIDGET = null;

	public static final boolean DEFAULT_INCLUSIVE_LEFT = false;

	public static final boolean DEFAULT_HANDLE_MOUSE_EVENTS = false;

	public static final boolean DEFAULT_SHARED = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		WIDGET("widget"),
		INCLUSIVE_LEFT("inclusiveLeft"),
		HANDLE_MOUSE_EVENTS("handleMouseEvents"),
		SHARED("shared");

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

	public BookmarkOptions() {
		super(null);
	}

	public boolean isInclusiveLeft() {
		return getValue(Property.INCLUSIVE_LEFT, DEFAULT_INCLUSIVE_LEFT);
	}

	public void setInclusiveLeft(boolean inclusiveLeft) {
		setValue(Property.INCLUSIVE_LEFT, inclusiveLeft);
	}

	public boolean isHandleMouseEvents() {
		return getValue(Property.HANDLE_MOUSE_EVENTS, DEFAULT_HANDLE_MOUSE_EVENTS);
	}

	public void setHandleMouseEvents(boolean handleMouseEvents) {
		setValue(Property.HANDLE_MOUSE_EVENTS, handleMouseEvents);
	}

	public boolean isShared() {
		return getValue(Property.SHARED, DEFAULT_SHARED);
	}

	public void setShared(boolean shared) {
		setValue(Property.SHARED, shared);
	}

}