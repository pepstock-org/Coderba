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
 * The options parameter is optional to set overlay. If given, it should be an object, optionally containing the following
 * options:<br>
 * <ul>
 * <li><b>opaque</b>, as <code>boolean</code> can be given to allow the overlay styling, when not null, to override the styling
 * of the base mode entirely, instead of the two being applied together. Default is <code>false</code>.
 * <li><b>priority</b>, as <code>int</code> determines the ordering in which the overlays are applied. Those with high priority
 * are applied after those with lower priority, and able to override the opaqueness of the ones that come before. Defaults to
 * <code>0</code>.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class OverlayOptions extends BaseEntity {
	
	public static final boolean DEFAULT_OPAQUE = true;

	public static final int DEFAULT_PRIORITY = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OPAQUE("opaque"),
		PRIORITY("priority");

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

	public void setOpaque(boolean opaque) {
		setValue(Property.OPAQUE, opaque);
	}

	public boolean isOpaque() {
		return getValue(Property.OPAQUE, DEFAULT_OPAQUE);
	}

	public void setPriority(int priority) {
		setValue(Property.OPAQUE, priority);
	}

	public int getPriority() {
		return getValue(Property.PRIORITY, DEFAULT_PRIORITY);
	}

}
