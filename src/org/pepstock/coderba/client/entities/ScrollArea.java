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
 * 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class ScrollArea extends BaseNativeEntity {

	/**
	 * To avoid any instantiation
	 */
	ScrollArea() {
		// do nothing
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area.
	 */
	@JsProperty
	public native int getTop();

	/**
	 * Returns the left of area.
	 * 
	 * @return the left of area.
	 */
	@JsProperty
	public native int getLeft();

	/**
	 * Returns the height of area.
	 * 
	 * @return the height of area.
	 */
	@JsProperty
	public native int getHeight();

	/**
	 * Returns the width of area.
	 * 
	 * @return the width of area.
	 */
	@JsProperty
	public native int getWidth();

	/**
	 * Returns the client height of area.
	 * 
	 * @return the client height of area.
	 */
	@JsProperty
	public native int getClientHeight();

	/**
	 * Returns the client width of area.
	 * 
	 * @return the client width of area.
	 */
	@JsProperty
	public native int getClientWidth();

}
