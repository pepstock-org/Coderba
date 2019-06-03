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

import java.util.ArrayList;
import java.util.List;

import org.pepstock.coderba.client.commons.CallbackProxy.Proxy;
import org.pepstock.coderba.client.utils.JSON;
import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Element;

/**
 * Base class for all classes which are wrapping a native java script object.
 * 
 * @author Andrea "Stock" Stocchero
 */
public abstract class NativeObjectContainer extends AbstractNativeObjectContainer {

	// native object instance
	private final NativeObject nativeObject;

	/**
	 * Creates the object with an empty native object instance.
	 */
	protected NativeObjectContainer() {
		this(new NativeObject());
	}

	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(NativeObject nativeObject) {
		this.nativeObject = (nativeObject == null ? new NativeObject() : nativeObject);
	}
	
	/**
	 * Creates the object with native object instance to be wrapped.
	 * 
	 * @param nativeObject native object instance to be wrapped.
	 */
	protected NativeObjectContainer(NativeCastableObject nativeObject) {
		this.nativeObject = (nativeObject == null ? new NativeObject() : JsHelper.get().cast(nativeObject));
	}

	// ------------------------------------------
	// --- COMMONS
	// ------------------------------------------
	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	protected final NativeObject getNativeObject() {
		return nativeObject;
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	@Override
	public final String toJSON() {
		return JSON.stringify(nativeObject, 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#hasProperty(java.lang.String)
	 */
	@Override
	protected final boolean hasProperty(String key) {
		return nativeObject.hasProperty(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#keys()
	 */
	@Override
	protected List<Key> keys() {
		// creates the result
		List<Key> keys = new ArrayList<>();
		// scans all properties names of object
		for (String key : nativeObject.propertyKeys()) {
			// adds a key object by name of the property
			keys.add(new StandardKey(key));
		}
		return keys;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getType(java.lang.String)
	 */
	@Override
	protected ObjectType getType(String key) {
		return JsHelper.get().typeOf(nativeObject, key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#removeProperty(java.lang.String)
	 */
	@Override
	protected void removeProperty(String key) {
		nativeObject.removeProperty(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineIntProperty(java.lang.String, int)
	 */
	@Override
	protected void defineIntProperty(String key, int value) {
		// if here, key is consistent
		nativeObject.defineIntProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getIntProperty(java.lang.String, int)
	 */
	@Override
	protected int getIntProperty(String key, int defaultValue) {
		// gets descriptor
		NativeIntegerDescriptor descriptor = nativeObject.getIntProperty(key);
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineDoubleProperty(java.lang.String, double)
	 */
	@Override
	protected void defineDoubleProperty(String key, double value) {
		// if here, key is consistent
		nativeObject.defineDoubleProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getDoubleProperty(java.lang.String, double)
	 */
	@Override
	protected double getDoubleProperty(String key, double defaultValue) {
		// gets descriptor
		NativeDoubleDescriptor descriptor = nativeObject.getDoubleProperty(key);
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineBooleanProperty(java.lang.String, boolean)
	 */
	@Override
	protected void defineBooleanProperty(String key, boolean value) {
		// if here, key is consistent
		nativeObject.defineBooleanProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getBooleanProperty(java.lang.String, boolean)
	 */
	@Override
	protected boolean getBooleanProperty(String key, boolean defaultValue) {
		// gets descriptor
		NativeBooleanDescriptor descriptor = nativeObject.getBooleanProperty(key);
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineStringProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	protected void defineStringProperty(String key, String value) {
		// sets value
		nativeObject.defineStringProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getStringProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	protected String getStringProperty(String key, String defaultValue) {
		// gets descriptor
		NativeStringDescriptor descriptor = nativeObject.getStringProperty(key);
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineObjectProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.NativeObject)
	 */
	@Override
	protected void defineObjectProperty(String key, NativeObject value) {
		// sets value
		nativeObject.defineObjectProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getObjectProperty(java.lang.String)
	 */
	@Override
	protected NativeObject getObjectProperty(String key) {
		// gets descriptor
		NativeObjectDescriptor descriptor = nativeObject.getObjectProperty(key);
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineCallbackProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.CallbackProxy.Proxy)
	 */
	@Override
	protected void defineCallbackProperty(String key, Proxy value) {
		// sets value
		nativeObject.defineCallbackProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineArrayProperty(java.lang.String,
	 * org.pepstock.coderba.client.cm.commons.Array)
	 */
	@Override
	protected <T extends Array> void defineArrayProperty(String key, T value) {
		// sets value
		nativeObject.defineArrayProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getArrayProperty(java.lang.String)
	 */
	@Override
	protected <T extends Array> T getArrayProperty(String key) {
		// gets descriptor
		NativeArrayDescriptor<T> descriptor = nativeObject.getArrayProperty(key);
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#defineRegExpProperty(java.lang.String,
	 * org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	protected void defineRegExpProperty(String key, RegExp value) {
		// sets value
		nativeObject.defineRegExpProperty(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.commons.AbstractNativeObjectContainer#getRegExpProperty(java.lang.String,
	 * org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	protected RegExp getRegExpProperty(String key, RegExp defaultValue) {
		// gets descriptor
		NativeRegExpDescriptor descriptor = nativeObject.getRegExpProperty(key);
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineElementProperty(java.lang.String, com.google.gwt.dom.client.Element)
	 */
	@Override
	protected void defineElementProperty(String key, Element value) {
		// sets value
		nativeObject.defineElementProperty(key, value);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getElementProperty(java.lang.String, com.google.gwt.dom.client.Element)
	 */
	@Override
	protected Element getElementProperty(String key, Element defaultValue) {
		// gets descriptor
		NativeElementDescriptor descriptor = nativeObject.getElementProperty(key);
		// returns value
		return descriptor == null ? null : descriptor.getValue();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#defineEntityProperty(java.lang.String, org.pepstock.coderba.client.commons.NativeEntity)
	 */
	@Override
	protected <T extends NativeEntity> void defineEntityProperty(String key, T value) {
		// sets value
		nativeObject.defineEntityProperty(key, value);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.commons.AbstractNativeObjectContainer#getEntityProperty(java.lang.String, org.pepstock.coderba.client.commons.NativeEntity)
	 */
	@Override
	protected <T extends NativeEntity> T getEntityProperty(String key, T defaultValue) {
		// gets descriptor
		NativeEntityDescriptor<T> descriptor = nativeObject.getEntityProperty(key);
		// returns value
		return descriptor == null ? defaultValue : descriptor.getValue();
	}
	
	

}
