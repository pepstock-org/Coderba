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
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might in some cases
 * affect it.<br>
 * This method returns an object that represents the widget placement. It'll have a line property pointing at the line handle
 * that it is associated with, and the following methods: <br>
 * clear() Removes the widget. <br>
 * changed() Call this if you made some change to the widget's DOM node that might affect its height. It'll force CodeMirror to
 * update the height of the line that contains the widget.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeLineWidget extends NativeEventEmitter {

	/**
	 * To avoid any instantiation
	 */
	private NativeLineWidget() {
		// do nothing
	}

	/**
	 * Returns the line handle.
	 * 
	 * @return the line handle
	 */
	@JsProperty
	native NativeLineHandle getLine();

	/**
	 * Removes the widget.
	 */
	native void clear();

	/**
	 * Call this if you made some change to the widget's DOM node that might affect its height.<br>
	 * It'll force CodeMirror to update the height of the line that contains the widget.
	 */
	native void changed();

}