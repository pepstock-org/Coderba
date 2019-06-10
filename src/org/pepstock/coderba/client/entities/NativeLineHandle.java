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

import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Native object, provided by CodeMirror, which represents a single line of document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeLineHandle extends NativeObject {

	/**
	 * to avoid any instantiation
	 */
	private NativeLineHandle() {
		// do nothing
	}

	/**
	 * Gets the content of the document related to this line handle.
	 * 
	 * @return the content of the document related to this line handle
	 */
	@JsProperty
	native String getText();

	/**
	 * Gets the height of the line.
	 * 
	 * @return the height of the line
	 */
	@JsProperty
	native double getHeight();

	/**
	 * Returns the line number of the handle into document.
	 * 
	 * @return the line number of the handle into document
	 */
	@JsMethod
	native int lineNo();

	/**
	 * Register an event handler for the given event type (a string) on the line handle instance.
	 * 
	 * @param type event name to activate
	 * @param function callback proxy function
	 */
	@JsMethod
	native void on(String type, CallbackProxy.Proxy function);

	/**
	 * Remove an event handler on the line handle instance.
	 * 
	 * @param type event name to activate
	 * @param function callback proxy function
	 */
	@JsMethod
	native void off(String type, CallbackProxy.Proxy function);

}