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
public final class Coordinate extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	private Coordinate() {
		// do nothing
	}

	@JsOverlay
	public static Coordinate create(int from, int to) {
		Coordinate range = new Coordinate();
		range.setFrom(from);
		range.setTo(to);
		return range;
	}

	@JsProperty
	public native int getFrom();

	@JsProperty
	private native void setFrom(int from);

	@JsProperty
	public native int getTo();

	@JsProperty
	private native void setTo(int to);

}
