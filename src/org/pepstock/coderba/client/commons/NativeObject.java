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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Base object for all native objects implemented in Coderba.<br>
 * It implements some common methods and wraps some native methods.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
public class NativeObject {

	/**
	 * To avoid any instantiation
	 */
	protected NativeObject() {
		// do nothing
	}

	/**
	 * Defines a new property directly on an object, or modifies an existing property on an object.
	 * 
	 * @param source the object on which to define the property.
	 * @param key the name of the property to be defined or modified.
	 * @param descriptor the descriptor for the property being defined or modified.
	 */
	static native <T extends NativeAbstractDescriptor> void defineProperty(Object source, String key, T descriptor);

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param source the object in which to look for the property.
	 * @param key the name of the property whose description is to be retrieved.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	static native <T extends NativeAbstractDescriptor> T getOwnPropertyDescriptor(Object source, String key);

	/**
	 * Returns an array of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @param source the object of which the enumerable's own properties are to be returned.
	 * @return an array of strings that represent all the enumerable properties of the given object.
	 */
	static native ArrayString keys(NativeObject source);

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	native boolean hasOwnProperty(String key);

	/**
	 * Returns an list of a given object's own property names, in the same order as we get with a normal loop.
	 * 
	 * @return list of strings that represent all the enumerable properties of the given object.
	 */
	@JsOverlay
	public final List<String> propertyKeys() {
		return ArrayListHelper.unmodifiableList(keys(this));
	}

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	@JsOverlay
	final boolean hasProperty(String key) {
		return hasOwnProperty(key);
	}

	/**
	 * Removes a property from this object.
	 * 
	 * @param key property key to be removed.
	 */
	@JsOverlay
	final void removeProperty(String key) {
		JsHelper.get().remove(this, key);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineBooleanProperty(String key, boolean value) {
		// creates a descriptor
		NativeBooleanDescriptor descriptor = new NativeBooleanDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineIntProperty(String key, int value) {
		// creates a descriptor
		NativeIntegerDescriptor descriptor = new NativeIntegerDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineDoubleProperty(String key, double value) {
		// creates a descriptor
		NativeDoubleDescriptor descriptor = new NativeDoubleDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineStringProperty(String key, String value) {
		// creates a descriptor
		NativeStringDescriptor descriptor = new NativeStringDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineCallbackProperty(String key, CallbackProxy.Proxy value) {
		// creates a descriptor
		NativeCallbackProxyDescriptor descriptor = new NativeCallbackProxyDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineObjectProperty(String key, NativeObject value) {
		// creates a descriptor
		NativeObjectDescriptor descriptor = new NativeObjectDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineRegExpProperty(String key, RegExp value) {
		// creates a descriptor
		NativeRegExpDescriptor descriptor = new NativeRegExpDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final void defineElementProperty(String key, Element value) {
		// creates a descriptor
		NativeElementDescriptor descriptor = new NativeElementDescriptor();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final <T extends NativeEntity> void defineEntityProperty(String key, T value) {
		// creates a descriptor
		NativeEntityDescriptor<T> descriptor = new NativeEntityDescriptor<>();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Defines a new property directly on this object, or modifies an existing property.
	 * 
	 * @param key the name of the property to be defined or modified.
	 * @param object the object associated with the property.
	 */
	@JsOverlay
	final <T extends Array> void defineArrayProperty(String key, T value) {
		// creates a descriptor
		NativeArrayDescriptor<T> descriptor = new NativeArrayDescriptor<>();
		// sets value
		descriptor.setValue(value);
		// sets attributes of descriptor to true
		resetPropertyDescriptor(descriptor);
		// defines the property
		defineProperty(this, key, descriptor);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeBooleanDescriptor getBooleanProperty(String key) {
		// checks if the property is present
		if (ObjectType.BOOLEAN.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeIntegerDescriptor getIntProperty(String key) {
		return getInternalNumberProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeDoubleDescriptor getDoubleProperty(String key) {
		return getInternalNumberProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	private final <T extends NativeAbstractDescriptor> T getInternalNumberProperty(String key) {
		// checks if the property is present
		if (ObjectType.NUMBER.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeStringDescriptor getStringProperty(String key) {
		// checks if the property is present
		if (ObjectType.STRING.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeObjectDescriptor getObjectProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeRegExpDescriptor getRegExpProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final NativeElementDescriptor getElementProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final <T extends NativeEntity> NativeEntityDescriptor<T> getEntityProperty(String key) {
		return getInternalObjectProperty(key);
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	private final <T extends NativeAbstractDescriptor> T getInternalObjectProperty(String key) {
		// checks if the property is present
		if (ObjectType.OBJECT.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Returns a property descriptor for an own property (that is, one directly present on an object and not in the object's
	 * prototype chain) of a given object.
	 * 
	 * @param key the name of the property to test.
	 * @return property descriptor of the given property if it exists on the object, <code>null</code> otherwise.
	 */
	@JsOverlay
	final <T extends Array> NativeArrayDescriptor<T> getArrayProperty(String key) {
		// checks if the property is present
		if (ObjectType.ARRAY.equals(JsHelper.get().typeOf(this, key))) {
			// returns the descriptor
			return getOwnPropertyDescriptor(this, key);
		}
		// if here, property does not exist
		return null;
	}

	/**
	 * Sets the properties of a descriptor to <code>true</code>, as default in java script when you use
	 * <code>obj[key] = value</code>
	 * 
	 * @param descriptor the descriptor to be set
	 */
	@JsOverlay
	final static void resetPropertyDescriptor(NativeAbstractDescriptor descriptor) {
		// configures the property
		descriptor.setConfigurable(true);
		descriptor.setEnumerable(true);
		descriptor.setWritable(true);
	}

}
