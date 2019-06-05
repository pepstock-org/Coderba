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

import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.NativeCastableObject;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Each editor is associated with an instance of CodeMirror.Doc, its document. A document represents the editor content, plus a
 * selection, an undo history, and a mode. A document can only be associated with a single editor at a time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true)
abstract class NativeIdable extends NativeCastableObject{

	/**
	 * Returns a boolean indicating whether the object has the specified property as its own property.
	 * 
	 * @param key the string name of the property to test.
	 * @return boolean indicating whether or not the object has the specified property as own property.
	 */
	private native boolean hasOwnProperty(String key);

	/**
	 * Returns the id of object.
	 * 
	 * @return the id of object
	 */
	@JsProperty(name = Id.CODERBA_ID_AS_STRING)
	public final native int getId();

	/**
	 * Sets the id of object
	 * 
	 * @param id the id of object
	 */
	@JsProperty(name = Id.CODERBA_ID_AS_STRING)
	protected native final void setId(int id);

	/**
	 * Returns <code>true</code> if the Coderba id property has been set.
	 * 
	 * @return <code>true</code> if the Coderba id property has been set
	 */
	@JsOverlay
	protected final boolean hasId() {
		return hasOwnProperty(Id.CODERBA_ID_AS_STRING);
	}

}