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
 * Defines a range of positions into editor documents.<br>
 * The range is built by starting and ending positions.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public class Range extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	protected Range() {
		// do nothing
	}

	/**
	 * Creates a range by starting and ending positions
	 * 
	 * @param from starting position of range
	 * @param to ending position of range
	 * @return a range of positions
	 */
	@JsOverlay
	public static final Range create(Position from, Position to) {
		// checks if FROM argument is consistent
		if (from == null) {
			// if not exception
			throw new IllegalArgumentException("[Range] From field is null");
		}
		// checks if TO argument is consistent
		if (to == null) {
			// if not exception
			throw new IllegalArgumentException("[Range] To field is null");
		}
		// creates a range
		Range range = new Range();
		// sets the MIN position between passed positions as FROM
		range.setFrom(Position.min(from, to));
		// sets the MAX position between passed positions as TO
		range.setTo(Position.max(from, to));
		return range;
	}

	/**
	 * Returns the starting position of range in editor documents.
	 * 
	 * @return the starting position of range in editor documents.
	 */
	@JsProperty
	public final native Position getFrom();

	/**
	 * <b>INTERNAL<b><br>
	 * Sets the starting position of range in editor documents.
	 * 
	 * @param from the starting position of range in editor documents
	 */
	@JsProperty
	private final native void setFrom(Position from);

	/**
	 * Returns the ending position of range in editor documents.
	 * 
	 * @return the ending position of range in editor documents.
	 */
	@JsProperty
	public final native Position getTo();

	/**
	 * <b>INTERNAL<b><br>
	 * Sets the ending position of range in editor documents.
	 * 
	 * @param to the ending position of range in editor documents
	 */
	@JsProperty
	private final native void setTo(Position to);

}