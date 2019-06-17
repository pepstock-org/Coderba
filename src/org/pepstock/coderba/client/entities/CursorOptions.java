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
 * <ul>
 * <li>scroll: boolean. Determines whether the selection head should be scrolled into view.
 * <li>origin: string. Determines whether the selection history event may be merged with the previous one. When an origin starts
 * with the character +, and the last recorded selection had the same origin and was similar (close in time, both collapsed or
 * both non-collapsed), the new one will replace the old one. When it starts with *, it will always replace the previous event
 * (if that had the same origin). Built-in motion uses the "+move" origin. User input uses the "+input" origin.
 * <li>bias: integer. Determine the direction into which the selection end points should be adjusted when they fall inside an
 * atomic range. Can be either -1 (backward) or 1 (forward). When not given, the bias will be based on the relative position of
 * the old selection-the editor will try to move further away from that, to prevent getting stuck.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class CursorOptions extends BaseEntity {

	/**
	 * Default value for scrolling, {@value DEFAULT_SCROLL}.
	 */
	public static final boolean DEFAULT_SCROLL = true;

	/**
	 * Default value of origin, {@value DEFAULT_ORIGIN}.
	 */
	public static final String DEFAULT_ORIGIN = "+input";

	/**
	 * Default value for direction, {@value DEFAULT_BIAS} means <b>forward</b>.
	 */
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

	/**
	 * Determines whether the selection head should be scrolled into view.
	 * 
	 * @return <code>true</code> whether the selection head should be scrolled into view
	 */
	public boolean isScroll() {
		return getValue(Property.SCROLL, DEFAULT_SCROLL);
	}

	/**
	 * Determines whether the selection head should be scrolled into view.
	 * 
	 * @param scroll <code>true</code> whether the selection head should be scrolled into view
	 */
	public void setScroll(boolean scroll) {
		setValue(Property.SCROLL, scroll);
	}

	/**
	 * Determines whether the selection history event may be merged with the previous one.<br>
	 * When an origin starts with the character +, and the last recorded selection had the same origin and was similar (close in
	 * time, both collapsed or both non-collapsed), the new one will replace the old one.<br>
	 * When it starts with *, it will always replace the previous event (if that had the same origin).<br>
	 * Built-in motion uses the "+move" origin. User input uses the "+input" origin.
	 * 
	 * @return the selection history event may be merged with the previous one
	 */
	public String getOrigin() {
		return getValue(Property.ORIGIN, DEFAULT_ORIGIN);
	}

	/**
	 * Determines whether the selection history event may be merged with the previous one.<br>
	 * When an origin starts with the character +, and the last recorded selection had the same origin and was similar (close in
	 * time, both collapsed or both non-collapsed), the new one will replace the old one.<br>
	 * When it starts with *, it will always replace the previous event (if that had the same origin).<br>
	 * Built-in motion uses the "+move" origin. User input uses the "+input" origin.
	 * 
	 * @param origin the selection history event may be merged with the previous one
	 */
	public void setOrigin(String origin) {
		setValue(Property.ORIGIN, origin);
	}

	/**
	 * Determine the direction into which the selection end points should be adjusted when they fall inside an atomic range.<br>
	 * Can be either -1 (backward) or 1 (forward).<br>
	 * When not given, the bias will be based on the relative position of the old selection-the editor will try to move further
	 * away from that, to prevent getting stuck.
	 * 
	 * @return the direction into which the selection end points should be adjusted when they fall inside an atomic range
	 */
	public int getBias() {
		return getValue(Property.BIAS, DEFAULT_BIAS);
	}

	/**
	 * Determine the direction into which the selection end points should be adjusted when they fall inside an atomic range.<br>
	 * Can be either -1 (backward) or 1 (forward).<br>
	 * When not given, the bias will be based on the relative position of the old selection-the editor will try to move further
	 * away from that, to prevent getting stuck.
	 * 
	 * @param bias the direction into which the selection end points should be adjusted when they fall inside an atomic range
	 */
	public void setBias(int bias) {
		setValue(Property.BIAS, bias);
	}
}