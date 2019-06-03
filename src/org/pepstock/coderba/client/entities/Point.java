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

import org.pepstock.coderba.client.commons.NativeEntity;
import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
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
public class Point extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	Point() {
		// do nothing
	}

	@JsOverlay
	public static Point create(int top, int left) {
		Point point = new Point();
		point.setTop(top);
		point.setLeft(left);
		return point;
	}

	/**
	 * Sets the top location in pixel
	 * 
	 * @param top the top location in pixel
	 */
	@JsProperty
	protected final native void setTop(int top);

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area.
	 */
	@JsProperty
	public final native int getTop();

	/**
	 * Sets the left location in pixel
	 * 
	 * @param left the left location in pixel
	 */
	@JsProperty
	protected final native void setLeft(int left);

	/**
	 * Returns the left of area.
	 * 
	 * @return the left of area.
	 */
	@JsProperty
	public final native int getLeft();

}
