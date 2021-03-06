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
package org.pepstock.coderba.client.events;

import org.pepstock.coderba.client.entities.LineHandle;

/**
 * Fires when the line's text content is changed in any way (but the line is not deleted outright).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LineHandleChangeEvent extends AbstractHandlerEvent<LineHandleChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<LineHandleChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "change";
	// line handle instance
	private final LineHandle lineHandle;
	// change item instance
	private final ChangeItem item;

	/**
	 * Creates a line handle {@value NAME} event.
	 * 
	 * @param lineHandle line handle instance
	 * @param item change item instance
	 */
	public LineHandleChangeEvent(LineHandle lineHandle, ChangeItem item) {
		super(TYPE);
		// checks if line handle is consistent
		if (lineHandle == null) {
			// if no, exception
			throw new IllegalArgumentException("Line handle is null");
		}
		// checks if item is consistent
		if (item == null) {
			// if no, exception
			throw new IllegalArgumentException("Change item is null");
		}
		this.lineHandle = lineHandle;
		this.item = item;
	}

	/**
	 * Returns the line handle instance.
	 * 
	 * @return the line handle instance
	 */
	public LineHandle getLineHandle() {
		return lineHandle;
	}

	/**
	 * Returns the change item.
	 * 
	 * @return the change item
	 */
	public ChangeItem getItem() {
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LineHandleChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LineHandleChangeEventHandler handler) {
		handler.onChange(this);
	}

}