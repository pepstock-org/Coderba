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

import java.util.List;

import org.pepstock.coderba.client.EditorArea;

/**
 * Like the "change" event, but batched per operation, passing an array containing all the changes that happened in the
 * operation. This event is fired after the operation finished, and display changes it makes will trigger a new operation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorChangesEvent extends AbstractEditorEvent<EditorChangesEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorChangesEventHandler> TYPE = new Type<>();
	/**
	 * Event name of CodeMirror
	 */
	public static final String NAME = "changes";

	private final List<EditorChangeItem> items;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public EditorChangesEvent(EditorArea editorArea, List<EditorChangeItem> items) {
		super(TYPE, editorArea);
		if (items == null) {
			throw new IllegalArgumentException("[EditorChangesEvent] Editor change items is null");
		}
		this.items = items;
	}

	/**
	 * @return the item
	 */
	public final List<EditorChangeItem> getItems() {
		return items;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorChangesEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorChangesEventHandler handler) {
		handler.onChanges(this);
	}

}