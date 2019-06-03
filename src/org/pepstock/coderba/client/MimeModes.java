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
package org.pepstock.coderba.client;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;

/**
 * Maps the "CodeMirror.mimeModes" property of Code mirror where are stored all mode specification.<br>
 * This is useful when the mime is not enough. like to create a document.
 * 
 * @author Andrea "Stock" Stocchero
 */
final class MimeModes extends NativeObjectContainer {
	
	private static final ModeSpecification DEFAULT_MODE_SPECIFICATION = null;

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
		// checks 
		if (language != null) {
			Key key = Key.create(language.getName());
			return getValue(key, DEFAULT_MODE_SPECIFICATION);
		}
		return null;
	}
}