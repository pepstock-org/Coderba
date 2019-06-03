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
 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
 * translation.<br>
 * This option determines the return value of that method.<br>
 * When it is null or an object that doesn't have a property named by the input string, that string is returned.<br>
 * Otherwise, the value of the property corresponding to that string is returned.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Phrases extends NativeEntity {

	/**
	 * Creates an empty object ready to load phrases.
	 */
	public Phrases() {
		// do nothing
	}

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation.<br>
	 * This option determines the return value of that method.<br>
	 * When it is null or an object that doesn't have a property named by the input string, that string is returned.<br>
	 * Otherwise, the value of the property corresponding to that string is returned.
	 * 
	 * @param key key of user-visible string
	 * @param value user-visible string
	 * 
	 */
	@JsOverlay
	public void setUserVisibleString(String key, String value) {
		setUserVisibleString(Key.create(key), value);
	}

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation.<br>
	 * This option determines the return value of that method.<br>
	 * When it is null or an object that doesn't have a property named by the input string, that string is returned.<br>
	 * Otherwise, the value of the property corresponding to that string is returned.
	 * 
	 * @param key key of user-visible string
	 * @param value user-visible string
	 * 
	 */
	@JsOverlay
	public void setUserVisibleString(Key key, String value) {
		if (Key.isValid(key)) {
			if (value != null) {
				defineStringProperty(key.value(), value);
			} else {
				removeProperty(key.value());
			}
		}
	}

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation.<br>
	 * This option determines the return value of that method.<br>
	 * When it is null or an object that doesn't have a property named by the input string, that string is returned.<br>
	 * Otherwise, the value of the property corresponding to that string is returned.
	 * 
	 * @param key key of user-visible string
	 * @return a user-visible string or {@link UndefinedValues#STRING}
	 */
	@JsOverlay
	public String getUserVisibleString(String key) {
		return getUserVisibleString(Key.create(key));
	}

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation.<br>
	 * This option determines the return value of that method.<br>
	 * When it is null or an object that doesn't have a property named by the input string, that string is returned.<br>
	 * Otherwise, the value of the property corresponding to that string is returned.
	 * 
	 * @param key key of user-visible string
	 * @return a user-visible string or {@link UndefinedValues#STRING}
	 */
	@JsOverlay
	public String getUserVisibleString(Key key) {
		if (Key.isValid(key)) {
			return getStringProperty(key.value());
		}
		return UndefinedValues.STRING;
	}

}
