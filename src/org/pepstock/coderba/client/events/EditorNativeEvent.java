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
package org.pepstock.coderba.client.events;

import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;

import com.google.gwt.dom.client.NativeEvent;

/**
 * This event extends the native DOM event of GWT.<br>
 * It has been created mapping the native event provided by CodeMirror interfaces.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class EditorNativeEvent extends NativeEvent {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LAYER_X("layerX"),
		LAYER_Y("layerY"),
		IGNORED("codemirrorIgnore");

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
	 * Needed for GWT injection
	 */
	protected EditorNativeEvent() {
		// do nothing
	}

	/**
	 * Returns the layer X property
	 * 
	 * @return the layer X property
	 */
	public final int getLayerX() {
		return JsHelper.get().propertyAsInt(this, Property.LAYER_X.value());
	}

	/**
	 * Returns the layer Y property
	 * 
	 * @return the layer Y property
	 */
	public final int getLayerY() {
		return JsHelper.get().propertyAsInt(this, Property.LAYER_Y.value());
	}

	/**
	 * Returns <code>true</code> if the ignored has been set and to signal that CodeMirror should do no further handling.
	 * 
	 * @return <code>true</code> if the ignored has been set
	 */
	public final boolean isIgnored() {
		return JsHelper.get().propertyAsBoolean(this, Property.IGNORED.value());
	}

	/**
	 * Sets <code>true</code> if the native event must be ignored and to signal that CodeMirror should do no further handling.
	 * 
	 * @param ignored <code>true</code> if the native event must be ignored
	 */
	public final void setIgnored(boolean ignored) {
		JsHelper.get().ignore(this, Property.IGNORED.value(), ignored);
	}

}