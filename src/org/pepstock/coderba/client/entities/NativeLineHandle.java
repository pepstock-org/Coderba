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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Each editor is associated with an instance of CodeMirror.Doc, its document. A document represents the editor content, plus a
 * selection, an undo history, and a mode. A document can only be associated with a single editor at a time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class NativeLineHandle {

	/**
	 * to avoid any instantiation
	 */
	private NativeLineHandle() {
		// do nothing
	}

	/**
	 * Returns the id of object.
	 * 
	 * @return the id of object
	 */
	@JsProperty
	public native int getId();

	/**
	 * Sets the id of object
	 * 
	 * @param id the id of object
	 */
	@JsProperty
	native void setId(int id);

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	@JsProperty
	native String getText();

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	@JsProperty
	native double getHeight();

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text
	 */
	@JsMethod
	native int lineTo();

}