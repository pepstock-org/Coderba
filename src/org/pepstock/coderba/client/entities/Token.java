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

import org.pepstock.coderba.client.commons.NativeEntity;
import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Defines a token, created by the current editor mode.
 * 
 * Missing "state" property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Token extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	private Token() {
		// do nothing
	}

	/**
	 * Returns the character position into a line at which the token starts.
	 * 
	 * @return the character position into a line at which the token starts
	 */
	@JsProperty
	public native int getStart();

	/**
	 * Returns the character position into a line at which the token ends.
	 * 
	 * @return the character position into a line at which the token ends
	 */
	@JsProperty
	public native int getEnd();

	/**
	 * Returns the token type the mode assigned to the token, such as "keyword" or "comment" (may also be null).
	 * 
	 * @return the token type the mode assigned to the token
	 */
	@JsProperty
	public native String getType();

	/**
	 * Returns the token's string.
	 * 
	 * @return the token's string
	 */
	@JsProperty
	public native String getString();

}
