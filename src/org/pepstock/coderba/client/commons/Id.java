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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Enums the property ID used by CODERBA to identify the editor area.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum Id implements Key
{
	/**
	 * Name of java script property
	 */
	CODERBA_ID(Id.CODERBA_ID_AS_STRING);

	// name value of property
	private final String value;

	/**
	 * Creates a property with the value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Id(String value) {
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

	/**
	 * Coderba ID as string
	 */
	public static final String CODERBA_ID_AS_STRING = "_coderbaId";

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(Integer.MIN_VALUE);
	// string prefix for generated id
	private static final String PREFIX_ID = "coderba-";

	/**
	 * Returns the ID from java script object (CodeMirror options).
	 * 
	 * @param nativeObject java script object (CodeMirror options)
	 * @return the property value or {@link UndefinedValues#STRING} if not exist
	 */
	public static String get(NativeObject nativeObject) {
		// checks if argument is consistent and property exists
		if (nativeObject != null && nativeObject.hasProperty(CODERBA_ID.value())) {
			// gets descriptor
			NativeStringDescriptor descriptor = nativeObject.getStringProperty(CODERBA_ID.value());
			// if descriptor is consistent, return value
			return descriptor != null ? descriptor.getValue() : UndefinedValues.STRING;
		}
		// property doesn't exist
		return UndefinedValues.STRING;
	}

	/**
	 * Sets the property value to java script object.
	 * 
	 * @param nativeObject java script object
	 */
	public static String generate() {
		return PREFIX_ID + COUNTER.incrementAndGet();
	}

	/**
	 * Sets the property value to java script object.
	 * 
	 * @param nativeObject java script object
	 */
	public static void set(NativeObject nativeObject) {
		set(nativeObject, generate());
	}

	/**
	 * Sets the property value to java script object.
	 * 
	 * @param nativeObject java script object
	 * @param id editor area id
	 */
	public static void set(NativeObject nativeObject, String id) {
		// checks if argument is consistent and property exists
		if (nativeObject != null && id != null) {
			// sets id
			nativeObject.defineStringProperty(CODERBA_ID.value(), id);
		}
	}

}
