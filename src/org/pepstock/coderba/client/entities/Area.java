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
import jsinterop.annotations.JsType;

/**
 * 
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

	@JsOverlay
	public static Area create(int top, int left, int bottom, int right) {
		Area area = new Area();
		area.setTop(top);
		area.setLeft(left);
		area.setBottom(bottom);
		area.setRight(right);
		return area;
	}
	
	@JsOverlay
	public static Area empty() {
		return create(0, 0, 0, 0);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area.
	 */
	public native int getRight();

	/**
	 * Sets the right location in pixel
	 * 
	 * @param right the right location in pixel
	 */
	private native void setRight(int right);

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area.
	 */
	public native int getBottom();

	/**
	 * Sets the bottom location in pixel
	 * 
	 * @param bottom the bottom location in pixel
	 */
	private native void setBottom(int bottom);

}
