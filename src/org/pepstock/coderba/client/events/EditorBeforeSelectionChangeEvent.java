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
import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.entities.Anchor;

/**
 * This event is fired before the selection is moved.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorBeforeSelectionChangeEvent extends AbstractEditorEvent<EditorBeforeSelectionChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorBeforeSelectionChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "beforeSelectionChange";
	// change item instance
	private final BeforeSelectionChangeItem item;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param item change item instance
	 */
	public EditorBeforeSelectionChangeEvent(EditorArea editorArea, BeforeSelectionChangeItem item) {
		super(TYPE, editorArea);
		// checks if item is consistent
		if (item == null) {
			// if no, exception
			throw new IllegalArgumentException("Change item is null");
		}
		this.item = item;
	}

	/**
	 * Returns the change item.
	 * 
	 * @return the change item
	 */
	public BeforeSelectionChangeItem getItem() {
		return item;
	}

	/**
	 * Enables changes on the item before firing the change selection event.
	 * 
	 * @param ranges array of anchors
	 */
	public void update(List<Anchor> ranges) {
		// checks if ranges is consistent
		if (ranges != null && !ranges.isEmpty()) {
			ArrayEntity<Anchor> nativeRanges = ArrayEntity.fromOrEmpty(ranges);
			item.update(nativeRanges);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorBeforeSelectionChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorBeforeSelectionChangeEventHandler handler) {
		handler.onBeforeSelectionChange(this);
	}

}