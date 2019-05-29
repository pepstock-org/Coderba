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
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class MimeModes extends NativeObjectContainer {

	/**
	 * @param nativeObject
	 */
	MimeModes(NativeObject nativeObject) {
		super(nativeObject);
	}

	public NativeObject getMimeMode(Language language) {
		Key key = Key.create(language.getName());
		if (has(key)) {
			return getValue(key);
		}
		return null;
	}

}