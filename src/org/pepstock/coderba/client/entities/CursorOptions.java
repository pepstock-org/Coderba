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

/**
 * Options to use to set a cursor.<br>
 * These options are supported:<br>
 * <br>
 * scroll: boolean. Determines whether the selection head should be scrolled into view. Defaults to true.<br>
 * <br>
 * origin: string. Determines whether the selection history event may be merged with the previous one. When an origin starts
 * with the character +, and the last recorded selection had the same origin and was similar (close in time, both collapsed or
 * both non-collapsed), the new one will replace the old one. When it starts with *, it will always replace the previous event
 * (if that had the same origin). Built-in motion uses the "+move" origin. User input uses the "+input" origin.<br>
 * <br>
 * bias: number. Determine the direction into which the selection endpoints should be adjusted when they fall inside an atomic
 * range. Can be either -1 (backward) or 1 (forward). When not given, the bias will be based on the relative position of the old
 * selection-the editor will try to move further away from that, to prevent getting stuck.<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CursorOptions extends BaseEntity {

	public static final boolean DEFAULT_SCROLL = true;

	public static final String DEFAULT_ORIGIN = "+input";

	public static final int DEFAULT_BIAS = 1;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SCROLL("scroll"),
		BIAS("bias"),
		ORIGIN("origin");

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

	public CursorOptions() {
		super();
	}

	/**
	 * Determines whether the selection head should be scrolled into view.
	 * 
	 * @return <code>true</code> whether the selection head should be scrolled into view
	 */
	public boolean isScroll() {
		return getValue(Property.SCROLL, DEFAULT_SCROLL);
	}

	public void setScroll(boolean scroll) {
		setValue(Property.SCROLL, scroll);
	}

	public String getOrigin() {
		return getValue(Property.ORIGIN, DEFAULT_ORIGIN);
	}

	public void setOrigin(String origin) {
		setValue(Property.ORIGIN, origin);
	}

	public int getBias() {
		return getValue(Property.BIAS, DEFAULT_BIAS);
	}

	public void setBias(int bias) {
		setValue(Property.BIAS, bias);
	}
}