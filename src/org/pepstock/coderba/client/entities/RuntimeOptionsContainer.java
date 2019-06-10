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

import java.util.ArrayList;
import java.util.List;

import org.pepstock.coderba.client.commons.AbstractNativeObjectContainer;
import org.pepstock.coderba.client.commons.Array;
import org.pepstock.coderba.client.commons.CallbackProxy.Proxy;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeEntity;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.ObjectType;
import org.pepstock.coderba.client.utils.JSON;
import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Element;

/**
 * Manages set and get of all options by a native editor instance and its set and get option methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class RuntimeOptionsContainer extends AbstractNativeObjectContainer {

	// native editor instance
	private final NativeEditor nativeEditor;

	/**
	 * Creates the object with native editor instance to be wrapped.
	 * 
	 * @param nativeEditor native editor instance to be wrapped.
	 */
	RuntimeOptionsContainer(NativeEditor nativeEditor) {
		// checks if argument is consistent
		if (nativeEditor == null) {
			// if not exception
			throw new IllegalArgumentException("Native editor instance is null");
		}
		this.nativeEditor = nativeEditor;
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	@Override
	public final String toJSON() {
		return JSON.stringify(nativeEditor.getOptions(), 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#hasProperty(java.lang.String)
	 */
	@Override
	protected boolean hasProperty(String key) {
		ObjectType type = JsHelper.get().typeOf(nativeEditor.getOptions(), key);
		return !ObjectType.UNDEFINED.equals(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#keys()
	 */
	@Override
	protected List<Key> keys() {
		// creates the result
		List<Key> keys = new ArrayList<>();
		// scans all properties names of object
		for (String key : nativeEditor.getOptions().propertyKeys()) {
			// adds a key object by name of the property
			keys.add(Key.create(key));
		}
		return keys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getType(java.lang.String)
	 */
	@Override
	protected ObjectType getType(String key) {
		return JsHelper.get().typeOf(nativeEditor.getOptions(), key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#removeProperty(java.lang.String)
	 */
	@Override
	protected void removeProperty(String key) {
		// creates the key
		Key keyObject = Key.create(key);
		// clones the value from default into this options
		cloneFromDefaults(Defaults.get().getObject(), nativeEditor.getOptions(), keyObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineIntProperty(java.lang.String, int)
	 */
	@Override
	protected void defineIntProperty(String key, int value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getIntProperty(java.lang.String, int)
	 */
	@Override
	protected int getIntProperty(String key, int defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsInt(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineDoubleProperty(java.lang.String, double)
	 */
	@Override
	protected void defineDoubleProperty(String key, double value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getDoubleProperty(java.lang.String, double)
	 */
	@Override
	protected double getDoubleProperty(String key, double defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsDouble(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineBooleanProperty(java.lang.String,
	 * boolean)
	 */
	@Override
	protected void defineBooleanProperty(String key, boolean value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getBooleanProperty(java.lang.String, boolean)
	 */
	@Override
	protected boolean getBooleanProperty(String key, boolean defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsBoolean(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineStringProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	protected void defineStringProperty(String key, String value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getStringProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	protected String getStringProperty(String key, String defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsString(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineObjectProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.NativeObject)
	 */
	@Override
	protected void defineObjectProperty(String key, NativeObject value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getObjectProperty(java.lang.String)
	 */
	@Override
	protected NativeObject getObjectProperty(String key) {
		// returns value
		return nativeEditor.getOptionValue(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineCallbackProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.CallbackProxy.Proxy)
	 */
	@Override
	protected void defineCallbackProperty(String key, Proxy value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineArrayProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.Array)
	 */
	@Override
	protected <T extends Array> void defineArrayProperty(String key, T value) {
		// if here, key is consistent
		nativeEditor.setArrayOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getArrayProperty(java.lang.String)
	 */
	@Override
	protected <T extends Array> T getArrayProperty(String key) {
		// returns value
		return nativeEditor.getArrayOptionValue(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineRegExpProperty(java.lang.String,
	 * org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	protected void defineRegExpProperty(String key, RegExp value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getRegExpProperty(java.lang.String,
	 * org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	protected RegExp getRegExpProperty(String key, RegExp defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsRegExp(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineElementProperty(java.lang.String,
	 * com.google.gwt.dom.client.Element)
	 */
	@Override
	protected void defineElementProperty(String key, Element value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getElementProperty(java.lang.String,
	 * com.google.gwt.dom.client.Element)
	 */
	@Override
	protected Element getElementProperty(String key, Element defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsElement(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineEntityProperty(java.lang.String,
	 * org.pepstock.coderba.client.commons.NativeEntity)
	 */
	@Override
	protected <T extends NativeEntity> void defineEntityProperty(String key, T value) {
		// if here, key is consistent
		nativeEditor.setOptionValue(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getEntityProperty(java.lang.String,
	 * org.pepstock.coderba.client.commons.NativeEntity)
	 */
	@Override
	protected <T extends NativeEntity> T getEntityProperty(String key, T defaultValue) {
		// checks if the value of property is undefined
		if (!hasProperty(key)) {
			return defaultValue;
		}
		// returns value
		return nativeEditor.getOptionValueAsEntity(key);
	}

	/**
	 * Clones the property from an object to another.
	 * 
	 * @param source object source to use
	 * @param target object target to use
	 * @param key property name
	 */
	final void cloneFromDefaults(Object source, Object target, Key key) {
		// checks consistency of arguments
		if (source != null && target != null && Key.isValid(key)) {
			// invokes clone
			cloneProperty(source, target, key);
		}
	}

	/**
	 * Returns the native editor instance.
	 * 
	 * @return the native editor instance.
	 */
	final NativeEditor getNativeEditor() {
		return nativeEditor;
	}

}
