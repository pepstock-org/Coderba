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

import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Each editor is associated with an instance of CodeMirror.Doc, its document. A document represents the editor content, plus a
 * selection, an undo history, and a mode. A document can only be associated with a single editor at a time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class NativeTextMarker extends NativeEventEmitter {

	/**
	 * To avoid any instantiation
	 */
	private NativeTextMarker() {
		// do nothing
	}

	/**
	 * Remove the mark.
	 */
	native void clear();

	/**
	 * Returns a {from, to} object (both holding document positions), indicating the current position of the marked range, or
	 * undefined if the marker is no longer in the document.
	 * 
	 * @return
	 */
	native Range find();

	/**
	 * Called when you've done something that might change the size of the marker and want to cheaply update the display.
	 */
	native void changed();

//	/**
//	 * Register an event handler for the given event type (a string) on the text marker instance.
//	 * 
//	 * @param type event name to activate
//	 * @param function callback proxy function
//	 */
//	native void on(String type, CallbackProxy.Proxy function);
//
//	/**
//	 * Remove an event handler on the text marker instance.
//	 * 
//	 * @param type event name to activate
//	 * @param function callback proxy function
//	 */
//	native void off(String type, CallbackProxy.Proxy function);
}