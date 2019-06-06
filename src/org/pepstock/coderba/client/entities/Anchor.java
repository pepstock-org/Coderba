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
 * Defines an immutable anchor and a head (and automatically from and to) by two positions.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Anchor extends NativeEntity {

	/**
	 * To avoid any instantiation
	 */
	private Anchor() {
		// do nothing
	}

	/**
	 * Creates an EMPTY anchor, to use as default.
	 * 
	 * @return an EMPTY anchor, to use as default
	 */
	@JsOverlay
	public static Anchor empty() {
		// creates the anchor by from and to positions of range
		Position defaultPosition = Position.empty();
		return create(defaultPosition, defaultPosition);
	}

	/**
	 * Creates an anchor by a range (from and to positions).
	 * 
	 * @param range range instance
	 * @return an anchor instance
	 */
	@JsOverlay
	public static Anchor create(Range range) {
		// checks if argument is consistent
		if (range == null) {
			// if not, exception
			throw new IllegalArgumentException("[Anchor] Range is null");
		}
		// creates the anchor by from and to positions of range
		return create(range.getFrom(), range.getTo());

	}

	/**
	 * Creates an anchor by two positions, the first as anchor, the second as head.
	 * 
	 * @param anchor position instance as anchor
	 * @param head position instance as head
	 * @return an anchor instance
	 */
	@JsOverlay
	public static Anchor create(Position anchor, Position head) {
		// checks if ANCHOR argument is consistent
		if (anchor == null) {
			// if not, exception
			throw new IllegalArgumentException("[Anchor] Anchor is null");
		}
		// checks if HEAD argument is consistent
		if (head == null) {
			// if not, exception
			throw new IllegalArgumentException("[Anchor] Head is null");
		}
		// creates an anchor
		Anchor object = new Anchor();
		// sets values
		object.setAnchor(anchor);
		object.setHead(head);
		// sets FROM and TO based on other values
		object.setFromAndTo();
		return object;
	}

	/**
	 * Returns the anchor position.
	 * 
	 * @return the anchor position
	 */
	@JsProperty
	public native Position getAnchor();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the anchor position.
	 * 
	 * @param anchor the anchor position
	 */
	@JsProperty
	private native void setAnchor(Position anchor);

	/**
	 * Returns the head position.
	 * 
	 * @return the head position
	 */
	@JsProperty
	public native Position getHead();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the head position.
	 * 
	 * @param head the head position
	 */
	@JsProperty
	private native void setHead(Position head);

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the "from" property (the smaller between anchor and head).
	 * 
	 * @param from the "from" property (the smaller between anchor and head)
	 */
	@JsProperty
	private native void setFrom(Position from);

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the "to" property (the larger between anchor and head).
	 * 
	 * @param to the "to" property (the smaller between anchor and head)
	 */
	@JsProperty
	private native void setTo(Position to);

	/**
	 * Sets automatically FROM and TO keys, accordingly with anchor and head
	 */
	@JsOverlay
	private void setFromAndTo() {
		// gets anchor and head
		Position anchor = getAnchor();
		Position head = getHead();
		// sets from and to based on MIN and MAX between anchor and head
		setFrom(Position.min(anchor, head));
		setTo(Position.max(anchor, head));
	}
}