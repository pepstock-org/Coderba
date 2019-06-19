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

import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;
import org.pepstock.coderba.client.commons.ObjectType;
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * Maps the "CodeMirror.mimeModes" property of CodeMirror where are stored all mode specification.<br>
 * This is useful when the mime is not enough. like to create a document.<br>
 * The structure of the java script object is:<br>
 * <br>
 * K = language name (mime type)<br>
 * V = mode specification, loaded when the mode has been injected<br>
 * 
 * @author Andrea "Stock" Stocchero
 */
final class MimeModes extends NativeObjectContainer {

	// default when is not found
	private static final NativeModeSpecification DEFAULT_MODE_SPECIFICATION = null;

	/**
	 * Creates the object mapping CodeMirror.mimeModes" property.
	 * 
	 * @param nativeObject CodeMirror.mimeModes" property
	 */
	MimeModes(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the mode specification related to the language (by mime of language).
	 * 
	 * @param language language to use to get the mode specification
	 * @return the mode specification related to the language or <code>null</code> if not exists.
	 */
	ModeSpecification getMode(Language language) {
		// checks if language is consistent
		if (language != null) {
			// creates a key by language name
			// because the key of the object is the mime type of language
			Key key = Key.create(language.getName());
			// gets type
			ObjectType type = type(key);
			// checks if stored as string
			if (ObjectType.STRING.equals(type)) {
				// gets string value
				String value = getValue(key, UndefinedValues.STRING);
				// returns mode specification
				return new ModeSpecification(value);
			} else if (ObjectType.OBJECT.equals(type)) {
				// if here, is an object
				// gets object value
				NativeModeSpecification mode = getValue(key, DEFAULT_MODE_SPECIFICATION);
				// returns mode specification
				return new ModeSpecification(mode);
			}
		}
		// if here, language is not consistent
		// then returns default
		return null;
	}	

}