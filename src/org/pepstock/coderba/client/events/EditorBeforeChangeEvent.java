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

/**
 * This event is fired before a change is applied, and its handler may choose to modify or cancel the change.<br>
 * It also has a cancel() method, which can be called to cancel the change, and, if the change isn't coming from an undo or redo
 * event, an update(from, to, text) method, which may be used to modify the change.<br>
 * <br>
 * Undo or redo changes can't be modified, because they hold some meta information for restoring old marked ranges that is only
 * valid for that specific change.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorBeforeChangeEvent extends AbstractEditorEvent<EditorBeforeChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorBeforeChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "beforeChange";
	// change item instance
	private final ChangeItem item;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param item change item instance
	 */
	public EditorBeforeChangeEvent(EditorArea editorArea, ChangeItem item) {
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
	public final ChangeItem getItem() {
		return item;
	}

	/**
	 * It can be called to cancel the change.
	 */
	public final void cancel() {
		item.cancel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorBeforeChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorBeforeChangeEventHandler handler) {
		handler.onBeforeChange(this);
	}

}