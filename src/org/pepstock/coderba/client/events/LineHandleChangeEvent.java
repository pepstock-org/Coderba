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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.entities.Document;
import org.pepstock.coderba.client.entities.LineHandle;

/**
 * Event which is fired when new event handler has been removed to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LineHandleChangeEvent extends AbstractLineHandleEvent<LineHandleChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<LineHandleChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "change";

	private final ChangeItem item;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public LineHandleChangeEvent(EditorArea editorArea, Document document, LineHandle lineHandle, ChangeItem item) {
		super(TYPE, editorArea, document, lineHandle);
		if (item == null) {
			throw new IllegalArgumentException("[DocumentChangeEvent] Line handle change item is null");
		}
		this.item = item;
	}

	/**
	 * @return the item
	 */
	public final ChangeItem getItem() {
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