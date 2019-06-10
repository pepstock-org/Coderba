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
 * Like the {@link EditorChangeEvent} event, but batched per operation, passing an array containing all the changes that
 * happened in the operation.<br>
 * This event is fired after the operation finished, and display changes it makes will trigger a new operation.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorChangesEvent extends AbstractEditorEvent<EditorChangesEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorChangesEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "changes";
	// list of change items
	private final List<ChangeItem> items;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param items list of change items
	 */
	public EditorChangesEvent(EditorArea editorArea, List<ChangeItem> items) {
		super(TYPE, editorArea);
		// checks if items are consistent
		if (items == null) {
			// if no, exception
			throw new IllegalArgumentException("Editor change items is null");
		}
		this.items = items;
	}

	/**
	 * Returns the list of change items.
	 * 
	 * @return the list of change items
	 */
	public final List<ChangeItem> getItems() {
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