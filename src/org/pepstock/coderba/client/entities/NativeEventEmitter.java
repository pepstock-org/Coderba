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

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public abstract class NativeEventEmitter extends NativeObject {

	/**
	 * To avoid any instantiation
	 */
	NativeEventEmitter() {
		// do nothing
	}

	/**
	 * Register an event handler for the given event type (a string) on the instance.
	 * 
	 * @param type event name to activate
	 * @param function callback proxy function
	 */
	final native void on(String type, CallbackProxy.Proxy function);

	/**
	 * Remove an event handler on the instance.
	 * 
	 * @param type event name to activate
	 * @param function callback proxy function
	 */
	final native void off(String type, CallbackProxy.Proxy function);
}