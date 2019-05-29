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

import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.ObjectType;
import org.pepstock.coderba.client.utils.JSON;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base abstract object of JSINTEROP native object to use inside the entities.<br>
 * It provides a set of common methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
abstract class BaseNativeEntity {

	/**
	 * Creates an empty native entity
	 */
	BaseNativeEntity() {
		// do nothing
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	@JsOverlay
	public final String toJSON() {
		return JSON.stringify(this, 3);
	}

	/**
	 * Checks if the property is undefined, returning the value got from object if NOT undefined, or the passed default value if
	 * undefined.
	 * 
	 * @param key property key to check
	 * @param value value got from the object
	 * @param defaultValue default value of the property if missing
	 * @return the value of key
	 */
	@JsOverlay
	final int checkAndGet(String key, int value, int defaultValue) {
		// checks if undefined
		if (isUndefined(key)) {
			// if yes, returns default
			return defaultValue;
		}
		// returns the value of object
		return value;
	}

	/**
	 * Checks if the property is undefined, returning the value got from object if NOT undefined, or the passed default value if
	 * undefined.
	 * 
	 * @param key property key to check
	 * @param value value got from the object
	 * @param defaultValue default value of the property if missing
	 * @return the value of key
	 */
	@JsOverlay
	final double checkAndGet(String key, double value, double defaultValue) {
		// checks if undefined
		if (isUndefined(key)) {
			// if yes, returns default
			return defaultValue;
		}
		// returns the value of object
		return value;
	}

	/**
	 * Checks if the property is undefined, returning the value got from object if NOT undefined, or the passed default value if
	 * undefined.
	 * 
	 * @param key property key to check
	 * @param value value got from the object
	 * @param defaultValue default value of the property if missing
	 * @return the value of key
	 */
	@JsOverlay
	final String checkAndGet(String key, String value, String defaultValue) {
		// checks if undefined
		if (isUndefined(key)) {
			// if yes, returns default
			return defaultValue;
		}
		// returns the value of object
		return value;
	}

	/**
	 * Returns <code>true</code> if the property, identified by the passed key, is <code>undefined</code>.
	 * 
	 * @param key property key to check
	 * @return <code>true</code> if the property, identified by the passed key, is <code>undefined</code>
	 */
	@JsOverlay
	private final boolean isUndefined(String key) {
		// checks if the argument is consistent
		if (key != null) {
			// gets the type of the property
			ObjectType type = JsHelper.get().typeOf(this, key);
			// checks if undefined
			return ObjectType.UNDEFINED.equals(type);
		}
		// if here, the key is null
		// then is always undefined
		return true;
	}
}
