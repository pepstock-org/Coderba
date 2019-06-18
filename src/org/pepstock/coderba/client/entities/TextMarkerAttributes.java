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
import org.pepstock.coderba.client.commons.NativeEntity;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.UndefinedValues;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Object to map text marker attribute options.<br>
 * When given, add the attributes in the given object to the elements created for the marked text.<br>
 * Adding class or style attributes this way is not supported.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class TextMarkerAttributes extends NativeEntity {

	/**
	 * Creates an empty object ready to load phrases.
	 */
	public TextMarkerAttributes() {
		// do nothing
	}

	/**
	 * Adds the attribute to the elements created for the marked text.
	 * 
	 * @param key key of attribute string
	 * @param value attribute value
	 * 
	 */
	@JsOverlay
	public void setAttribute(String key, String value) {
		setAttribute(Key.create(key), value);
	}

	/**
	 * Adds the attribute to the elements created for the marked text.
	 * 
	 * @param key key of attribute string
	 * @param value attribute value
	 * 
	 */
	@JsOverlay
	public void setAttribute(Key key, String value) {
		// checks if is a valid key
		if (Key.isValid(key)) {
			// checks if value is consistent
			if (value != null) {
				// defines the property
				defineStringProperty(key.value(), value);
			} else {
				// removes property
				removeProperty(key.value());
			}
		}
	}

	/**
	 * Returns the attribute from the elements created for the marked text.
	 * 
	 * @param key key of attribute string
	 * @return attribute value
	 */
	@JsOverlay
	public String getAttribute(String key) {
		return getAttribute(Key.create(key));
	}

	/**
	 * Returns the attribute from the elements created for the marked text.
	 * 
	 * @param key key of attribute string
	 * @return attribute value
	 */
	@JsOverlay
	public String getAttribute(Key key) {
		// checks if is valid key
		if (Key.isValid(key)) {
			// returns value
			return getStringProperty(key.value());
		}
		// returns null because key is not valid
		return UndefinedValues.STRING;
	}

}
