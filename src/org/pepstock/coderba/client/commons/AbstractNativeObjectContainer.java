/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUString WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.coderba.client.commons;

import java.util.List;

import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Element;

/**
 * Base class for all classes which are wrapping a native java script object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class AbstractNativeObjectContainer {

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	public abstract String toJSON();

	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 */
	protected abstract boolean hasProperty(String key);

	/**
	 * Returns true if the embedded JavaScript object contains an element at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at specific property
	 */
	protected final boolean has(Key key) {
		// checks arguments if consistent
		// if not consistent, returns not found
		if (Key.isValid(key)) {
			return hasProperty(key.value());
		}
		// if here, key is not consistent
		return false;
	}

	/**
	 * Returns true if the embedded JavaScript object contains an element at all properties.
	 * 
	 * @param keys set of keys of the properties of JavaScript object.
	 * @return <code>true</code> if the embedded JavaScript object contains an element at all properties.
	 */
	protected final boolean has(Key... keys) {
		// checks arguments if consistent
		if (keys != null && keys.length > 0) {
			// scans keys
			for (Key key : keys) {
				// if one is not present
				// returns false
				if (!has(key)) {
					return false;
				}
			}
			return true;
		}
		// if here, argument is not consistent
		return false;
	}

	/**
	 * Returns the list of properties names of the object.
	 * 
	 * @return the list of properties names of the object.
	 */
	protected abstract List<Key> keys();

	/**
	 * Returns the java script type of the property.
	 * 
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	protected abstract ObjectType getType(String key);

	/**
	 * Returns the java script type of the property.
	 * 
	 * @param key name of the java script property.
	 * @return the java script type of the property.
	 */
	protected final ObjectType type(Key key) {
		// checks arguments if consistent
		// if not consistent, returns undefined
		return Key.isValid(key) ? getType(key.value()) : ObjectType.UNDEFINED;
	}

	/**
	 * Removes an element (by key) from the embedded JavaScript object.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected abstract void removeProperty(String key);

	/**
	 * Removes an element (by key) from the embedded JavaScript object if exists.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected final void removeIfExists(Key key) {
		// checks if there is
		if (has(key)) {
			// and then remove
			remove(key);
		}
	}

	/**
	 * Removes an element (by key) from the embedded JavaScript object.
	 * 
	 * @param key key of the property of JavaScript object.
	 */
	protected final void remove(Key key) {
		// checks arguments if consistent
		// if not consistent, do nothing
		if (Key.isValid(key)) {
			removeProperty(key.value());
		}
	}

	/**
	 * Removes a set of elements (by keys) from the embedded JavaScript object.
	 * 
	 * @param keys set of keys of the properties of JavaScript object.
	 */
	protected final void remove(Key... keys) {
		// checks arguments if consistent
		if (keys != null && keys.length > 0) {
			// scans all keys
			for (Key key : keys) {
				// removes if exists
				removeIfExists(key);
			}
		}
	}

	// ------------------------------------------
	// --- INTEGERS
	// ------------------------------------------

	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineIntProperty(String key, int value);

	/**
	 * Returns a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected abstract int getIntProperty(String key, int defaultValue);

	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, int value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		defineIntProperty(key.value(), value);
	}

	/**
	 * Returns a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final int getValue(Key key, int defaultValue) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// gets type
		ObjectType type = type(key);
		// checks if the property exists
		if (!has(key) || ObjectType.UNDEFINED.equals(type)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getIntProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (Array or integer) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of integers to be set
	 */
	protected final void setValueOrArray(Key key, int... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayInteger.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a integer.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single number value
	 * @return value of the property (by array)
	 */
	protected final ArrayInteger getValueOrArray(Key key, int defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.NUMBER.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayInteger.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist or has got a wrong type
		return ArrayInteger.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- DOUBLES
	// ------------------------------------------

	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineDoubleProperty(String key, double value);

	/**
	 * Returns a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected abstract double getDoubleProperty(String key, double defaultValue);

	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, double value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		defineDoubleProperty(key.value(), value);
	}

	/**
	 * Returns a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final double getValue(Key key, double defaultValue) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// gets type
		ObjectType type = type(key);
		// checks if the property exists
		if (!has(key) || ObjectType.UNDEFINED.equals(type)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getDoubleProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (Array or double) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of doubles to be set
	 */
	protected final void setValueOrArray(Key key, double... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayDouble.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a double.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single number value
	 * @return value of the property (by array)
	 */
	protected final ArrayDouble getValueOrArray(Key key, double defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.NUMBER.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayDouble.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist or has got a wrong type
		return ArrayDouble.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- BOOLEANS
	// ------------------------------------------

	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineBooleanProperty(String key, boolean value);

	/**
	 * Returns a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected abstract boolean getBooleanProperty(String key, boolean defaultValue);

	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, boolean value) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// if here, key is consistent
		defineBooleanProperty(key.value(), value);
	}

	/**
	 * Returns a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final boolean getValue(Key key, boolean defaultValue) {
		// checks if the key is consistent
		// if not, exception
		Key.checkIfValid(key);
		// gets type
		ObjectType type = type(key);
		// checks if the property exists
		if (!has(key) || ObjectType.UNDEFINED.equals(type)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getBooleanProperty(key.value(), defaultValue);
	}

	// ------------------------------------------
	// --- STRINGS
	// ------------------------------------------

	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineStringProperty(String key, String value);

	/**
	 * Returns a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected abstract String getStringProperty(String key, String defaultValue);

	/**
	 * Returns a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	protected final String getValue(Key key, String defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getStringProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, String value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineStringProperty(key.value(), value);
		}
	}

	/**
	 * Sets a value (Array or string) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values values of strings to be set
	 */
	protected final void setValueOrArray(Key key, String... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single string value
	 * @return value of the property (by array)
	 */
	protected final ArrayString getValueOrArray(Key key, String defaultValue) {
		// gets object type of key
		ObjectType type = type(key);
		// checks if property type
		if (ObjectType.STRING.equals(type)) {
			// if here, is a single value, therefore creates an array
			// with only 1 element
			return ArrayString.fromOrEmpty(getValue(key, defaultValue));
		} else if (ObjectType.ARRAY.equals(type)) {
			// if here, is an array, therefore return it
			return getArrayValue(key);
		}
		// if here the property doesn't exist
		// returns default
		return ArrayString.fromOrEmpty(defaultValue);
	}

	// ------------------------------------------
	// --- NATIVE OBJECTS
	// ------------------------------------------

	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineObjectProperty(String key, NativeObject value);

	/**
	 * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	protected abstract NativeObject getObjectProperty(String key);

	/**
	 * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final NativeObject getValue(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return null;
		}
		// gets descriptor
		return getObjectProperty(key.value());
	}

	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObject value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineObjectProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- NATIVE OBJECT CONTAINERS
	// ------------------------------------------
	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property by object container.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, NativeObjectContainer value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineObjectProperty(key.value(), value.getNativeObject());
		}
	}

	// ------------------------------------------
	// --- CALLBACKS
	// ------------------------------------------

	/**
	 * Sets a value (callback proxy function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineCallbackProperty(String key, CallbackProxy.Proxy value);

	/**
	 * Sets a value (callback proxy function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, CallbackProxy.Proxy value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineCallbackProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- ENUMERATIONS
	// ------------------------------------------
	/**
	 * Returns a value (key) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param clazz class of object to get all enumeration values
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of key
	 * @return value of the property
	 */
	protected final <T extends Key> T getValue(Key key, Class<T> clazz, T defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// checks consistency of default value
		Key.checkIfValid(defaultValue);
		// gets the string value
		String value = getValue(key, defaultValue.value());
		// gets the key by value
		return Key.getKeyByValue(clazz, value, defaultValue);
	}

	/**
	 * Sets a value (EnumValue) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of key
	 */
	protected final <T extends Key> void setValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// checks if the value is consistent
			// if not, exception
			Key.checkIfValid(value);
			// if here, key is consistent
			// sets value
			defineStringProperty(key.value(), value.value());
		}
	}

	/**
	 * Sets a value (Array or string by keys) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a string.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param values value of keys to be set
	 */
	protected final void setValueOrArray(Key key, Key... values) {
		// checks if values are consistent
		if (values != null) {
			// checks if there is only 1 element
			if (values.length == 1) {
				// if 1 element, sets the object
				setValue(key, values[0]);
			} else {
				// if more than 1 element, sets the array
				setArrayValue(key, ArrayString.fromOrEmpty(values));
			}
		} else {
			// if not consistent, remove the property
			removeIfExists(key);
		}
	}

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.<br>
	 * This must be used when a java script property can contain an array or a key.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the value was stored as single key value
	 * @return value of the property (by array) or <code>null</code> if not exist
	 */
	protected final ArrayString getValueOrArray(Key key, Key defaultValue) {
		// the same logic as a string
		// checks if default value is consistent
		return getValueOrArray(key, Key.isValid(defaultValue) ? defaultValue.value() : null);
	}

	// ------------------------------------------
	// --- ARRAYS
	// ------------------------------------------

	/**
	 * Sets a value (Array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	protected abstract <T extends Array> void defineArrayProperty(String key, T value);

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of array
	 * @return value of the property or <code>null</code> if not exist
	 */
	protected abstract <T extends Array> T getArrayProperty(String key);

	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of array
	 * @return value of the property or <code>null</code> if not exist
	 */
	protected final <T extends Array> T getArrayValue(Key key) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns null
			return null;
		}
		// gets descriptor
		return getArrayProperty(key.value());
	}

	/**
	 * Sets a value (Array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	protected final <T extends Array> void setArrayValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineArrayProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- REGEXP
	// ------------------------------------------

	/**
	 * Sets a value (RegExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineRegExpProperty(String key, RegExp value);

	/**
	 * Returns a value (regExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object
	 * @param defaultValue default value of the property of RegExp
	 * @return value of the property or <code>null</code> if not there
	 */
	protected abstract RegExp getRegExpProperty(String key, RegExp defaultValue);

	/**
	 * Returns a value (regExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value of the property of RegExp
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final RegExp getValue(Key key, RegExp defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getRegExpProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (RegExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, RegExp value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineRegExpProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- DOM ELEMENT
	// ------------------------------------------

	/**
	 * Sets a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected abstract void defineElementProperty(String key, Element value);

	/**
	 * Returns a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value of the property of element
	 * @return value of the property or <code>null</code> if not there
	 */
	protected abstract Element getElementProperty(String key, Element defaultValue);

	/**
	 * Returns a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value of the property of element
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final Element getValue(Key key, Element defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getElementProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	protected final void setValue(Key key, Element value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineElementProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- NATIVE ENTITY
	// ------------------------------------------

	/**
	 * Sets a value (native entity) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of native entity
	 * @param value value to be set
	 */
	protected abstract <T extends NativeEntity> void defineEntityProperty(String key, T value);

	/**
	 * Returns a value (native entity) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of native entity
	 * @return value of the property or <code>null</code> if not there
	 */
	protected abstract <T extends NativeEntity> T getEntityProperty(String key, T defaultValue);

	/**
	 * Returns a value (native entity) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of native entity
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final <T extends NativeEntity> T getValue(Key key, T defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getEntityProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (native entity) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of native entity
	 */
	protected final <T extends NativeEntity> void setValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineEntityProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- NATIVE FUNCTION
	// ------------------------------------------

	/**
	 * Sets a value (function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of native entity
	 * @param value value to be set
	 */
	protected abstract <T> void defineFunctionProperty(String key, T value);

	/**
	 * Returns a value (function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of native entity
	 * @return value of the property or <code>null</code> if not there
	 */
	protected abstract <T> T getFunctionProperty(String key, T defaultValue);

	/**
	 * Returns a value (function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @param <T> type of native entity
	 * @return value of the property or <code>null</code> if not there
	 */
	protected final <T> T getValue(Key key, T defaultValue) {
		// checks if the property exists
		if (!has(key)) {
			// if no, returns the default value
			return defaultValue;
		}
		// gets descriptor
		return getFunctionProperty(key.value(), defaultValue);
	}

	/**
	 * Sets a value (function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of native entity
	 */
	protected final <T> void setValue(Key key, T value) {
		// if value is null
		// try to remove the reference if exists
		if (value == null) {
			// removes property if the property exists
			removeIfExists(key);
		} else {
			// checks if the key is consistent
			// if not, exception
			Key.checkIfValid(key);
			// if here, key is consistent
			// sets value
			defineFunctionProperty(key.value(), value);
		}
	}

	// ------------------------------------------
	// --- CLONE
	// ------------------------------------------

	/**
	 * Clones a property from source to target object, by property name.
	 * 
	 * @param source source object
	 * @param target target object
	 * @param key property name
	 */
	protected final void cloneProperty(Object source, Object target, Key key) {
		JsHelper.get().cloneProperty(source, target, key);
	}
}
