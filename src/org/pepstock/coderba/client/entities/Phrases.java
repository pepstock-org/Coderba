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
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainerFactory;
import org.pepstock.coderba.client.commons.UndefinedValues;

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
public final class Phrases extends BaseEntity {

	/**
	 * Phrases factory to build a phrases object by a native object.
	 * FIXME
	 */
	public static final NativeObjectContainerFactory<Phrases> FACTORY = new PhrasesFactory();

	/**
	 * Creates an empty object ready to load phrases.
	 */
	public Phrases() {
		this(null);
	}

	/**
	 * Creates an empty object ready to load phrases.
	 */
	Phrases(NativeObject nativeObject) {
		super(nativeObject);
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
	public void setUserVisibleString(Key key, String value) {
		setValue(key, value);
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
	public String getUserVisibleString(Key key) {
		return getValue(key, UndefinedValues.STRING);
	}

	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class PhrasesFactory implements NativeObjectContainerFactory<Phrases> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public Phrases create(NativeObject nativeObject) {
			return new Phrases(nativeObject);
		}

	}
}
