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
 * Native object, provided by Code Mirror, has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass,
 * widgets}, where gutterMarkers is an object mapping gutter IDs to marker elements, and widgets is an array of line widgets
 * attached to this line, and the various class properties refer to classes added with addLineClass.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
final class NativeLineInfo {

	/**
	 * To avoid any instantiation
	 */
	private NativeLineInfo() {
		// do nothing
	}

	/**
	 * Returns the line number.
	 * 
	 * @return the line number
	 */
	@JsProperty
	native int getLine();

	/**
	 * Returns the line handle related to the line.
	 * 
	 * @return the line handle related to the line
	 */
	@JsProperty
	native NativeLineHandle getHandle();

	/**
	 * Returns the content of the line.
	 * 
	 * @return the content of the line
	 */
	@JsProperty
	native String getText();

	/**
	 * Returns an array of CSS style classes associated to the line, added to gutter element.
	 * 
	 * @return an array of CSS style classes associated to the line, added to gutter element
	 */
	@JsProperty
	native ArrayString getGutterMarkers();

	/**
	 * Returns the CSS style class associated to the line.
	 * 
	 * @return the CSS style class associated to the line
	 */
	@JsProperty
	native String getTextClass();

	/**
	 * Returns the background class associated to the line.
	 * 
	 * @return the background class associated to the line
	 */
	@JsProperty
	native String getBgClass();

	/**
	 * Returns the CSS style class associated to the line, added to wrap element.
	 * 
	 * @return the CSS style class associated to the line, added to wrap element
	 */
	@JsProperty
	native String getWrapClass();

	/**
	 * Returns an array of line widget associated to this line.
	 * 
	 * @return an array of line widget associated to this line
	 */
	@JsProperty
	native ArrayObject getWidgets();

}