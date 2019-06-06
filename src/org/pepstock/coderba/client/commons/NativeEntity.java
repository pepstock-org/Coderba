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
package org.pepstock.coderba.client.commons;

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
public abstract class NativeEntity {

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	native boolean hasOwnProperty(String key);

	/**
	 * Creates an empty native entity
	 */
	protected NativeEntity() {
		// do nothing
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
	protected final int checkAndGet(String key, int value, int defaultValue) {
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
	protected final double checkAndGet(String key, double value, double defaultValue) {
		// checks if undefined
		if (isUndefined(key)) {
			// if yes, returns default
			return defaultValue;
		}
		// returns the value of object
		return value;
	}

	/**
	 * Removes a property from this object.
	 * 
	 * @param key property key to be removed.
	 */
	@JsOverlay
	protected final void removeProperty(String key) {
		JsHelper.get().remove(this, key);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	protected final void defineStringProperty(String key, String value) {
		// creates a descriptor
		NativeStringDescriptor descriptor = new NativeStringDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		NativeObject.resetPropertyDescriptor(descriptor);
		// defines the property
		NativeObject.defineProperty(this, key, descriptor);
	}

	/**
	 * Returns a string value for an own property (that is, one directly present on an object and not in the object's prototype
	 * chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return string value of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	protected final String getStringProperty(String key) {
		// checks if the property is present
		if (ObjectType.STRING.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			NativeStringDescriptor descriptor = NativeObject.getOwnPropertyDescriptor(this, key);
			if (descriptor != null) {
				return descriptor.getValue();
			}
		}
		// if here, property does not exist
		return null;
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
	protected final String checkAndGet(String key, String value, String defaultValue) {
		// checks if undefined
		if (isUndefined(key)) {
			// if yes, returns default
			return defaultValue;
		}
		// returns the value of object
		return value;
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
