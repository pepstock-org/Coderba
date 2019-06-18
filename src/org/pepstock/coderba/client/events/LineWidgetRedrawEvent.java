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

import org.pepstock.coderba.client.entities.LineWidget;

/**
 * Fired whenever the editor re-adds the widget to the DOM.<br>
 * This will happen once right after the widget is added (if it is scrolled into view), and then again whenever it is scrolled
 * out of view and back in again, or when changes to the editor options or the line the widget is on require the widget to be
 * redrawn.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LineWidgetRedrawEvent extends AbstractHandlerEvent<LineWidgetRedrawEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<LineWidgetRedrawEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "redraw";
	// line widget instance
	private final LineWidget lineWidget;

	/**
	 * Creates a line widget {@value NAME} event.
	 * 
	 * @param lineWidget line widget instance
	 */
	public LineWidgetRedrawEvent(LineWidget lineWidget) {
		super(TYPE);
		// checks if line widget is consistent
		if (lineWidget == null) {
			// if no, exception
			throw new IllegalArgumentException("Line widget is null");
		}
		this.lineWidget = lineWidget;
	}
	
	/**
	 * Returns the line widget instance
	 * 
	 * @return the line widget instance
	 */
	public LineWidget getLineWidget() {
		return lineWidget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<LineWidgetRedrawEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(LineWidgetRedrawEventHandler handler) {
		handler.onRedraw(this);
	}

}