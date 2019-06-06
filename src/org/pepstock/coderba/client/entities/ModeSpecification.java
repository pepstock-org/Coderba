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

import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.Modes;
import org.pepstock.coderba.client.commons.NativeEntity;
import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Defines the mode specification of CodeMirror mode.<br>
 * This is useful when the mime is not enough. like to create a document.<br>
 * It does not contain any visible method or property because is immutable.
 * 
 * FIXME Missing "state" property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class ModeSpecification extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	private ModeSpecification() {
		// do nothing
	}

	/**
	 * Returns the name of mode
	 * 
	 * @return the name of mode
	 */
	@JsProperty
	private native String getName();

	/**
	 * Returns the mode instance.
	 * 
	 * @return the mode instance
	 */
	@JsOverlay
	public Mode getMode() {
		return Modes.get().retrieve(getName());
	}

}
