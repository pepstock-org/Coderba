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

import org.pepstock.coderba.client.commons.ArrayObject;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Object has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}, where gutterMarkers is
 * an object mapping gutter IDs to marker elements, and widgets is an array of line widgets attached to this line, and the
 * various class properties refer to classes added with addLineClass.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeLineInfo {

	/**
	 * to avoid any instantiation
	 */
	private NativeLineInfo() {
		// do nothing
	}

	@JsProperty
	native int getLine();

	@JsProperty
	native NativeLineHandle getHandle();

	@JsProperty
	native String getText();

	@JsProperty
	native ArrayString getGutterMarkers();

	@JsProperty
	native String getTextClass();

	@JsProperty
	native String getBgClass();

	@JsProperty
	native String getWrapClass();

	@JsProperty
	native ArrayObject getWidgets();

}