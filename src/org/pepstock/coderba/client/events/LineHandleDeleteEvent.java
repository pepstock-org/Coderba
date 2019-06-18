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
 * Will be fired when the line object is deleted.<br>
 * A line object is associated with the start of the line.<br>
 * Mostly useful when you need to find out when your gutter markers on a given line are removed.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LineHandleDeleteEvent extends AbstractHandlerEvent<LineHandleDeleteEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<LineHandleDeleteEventHandler> TYPE = new Type<>();

	/**
	 * Event name
	 */
	public static final String NAME = "delete";

	// line handle instance
	private final LineHandle lineHandle;

	/**
	 * Creates a line handle {@value NAME} event.
	 * 
	 * @param lineHandle line handle instance
	 */
	public LineHandleDeleteEvent(LineHandle lineHandle) {
		super(TYPE);
		// checks if line handle is consistent
		if (lineHandle == null) {
			// if no, exception
			throw new IllegalArgumentException("Line handle is null");
		}
		this.lineHandle = lineHandle;
	}

	/**
	 * Returns the line handle instance.
	 * 
	 * @return the line handle instance
	 */
	public LineHandle getLineHandle() {
		return lineHandle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LineHandleDeleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LineHandleDeleteEventHandler handler) {
		handler.onDelete(this);
	}

}