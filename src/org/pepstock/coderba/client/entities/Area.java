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

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Object which contains 4 values to identify an area: top, left, bottom and right.<br>
 * It is used by Code Mirror methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Area extends Point {

	/**
	 * To avoid any instantiation
	 */
	private Area() {
		// do nothing
	}

	/**
	 * Creates an area using all fields.
	 * 
	 * @param top top field
	 * @param left left field
	 * @param bottom bottom field
	 * @param right right field
	 * @return an area instance
	 */
	@JsOverlay
	public static Area create(int top, int left, int bottom, int right) {
		// creates an area
		Area area = new Area();
		// sets all values
		area.setTop(top);
		area.setLeft(left);
		area.setBottom(bottom);
		area.setRight(right);
		// returns area
		return area;
	}

	/**
	 * Creates a empty area (all values are set to 0).
	 * 
	 * @return an empty area instance
	 */
	@JsOverlay
	public static Area empty() {
		return create(0, 0, 0, 0);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area.
	 */
	@JsProperty
	public native int getRight();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	@JsProperty
	private native void setRight(int right);

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area.
	 */
	@JsProperty
	public native int getBottom();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	@JsProperty
	private native void setBottom(int bottom);

}
