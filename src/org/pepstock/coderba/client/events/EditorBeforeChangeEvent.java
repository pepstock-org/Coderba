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
 * <br>
 * The changeObj object has from, to, and text properties, as with the "change" event.<br>
 * <br>
 * It also has a cancel() method, which can be called to cancel the change, and, if the change isn't coming from an undo or redo
 * event, an update(from, to, text) method, which may be used to modify the change.<br>
 * <br>
 * Undo or redo changes can't be modified, because they hold some metainformation for restoring old marked ranges that is only
 * valid for that specific change.<br>
 * <br>
 * All three arguments to update are optional, and can be left off to leave the existing value for that field intact.<br>
 * <br>
 * Note: you may not do anything from a "beforeChange" handler that would cause changes to the document or its visualization.
 * <br>
 * <br>
 * Doing so will, since this handler is called directly from the bowels of the CodeMirror implementation, probably cause the
 * editor to become corrupted.
 * FIXME missing methods
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorBeforeChangeEvent extends AbstractEditorEvent<EditorBeforeChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorBeforeChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name of CodeMirror
	 */
	public static final String NAME = "beforeChange";

	private final EditorChangeItem item;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public EditorBeforeChangeEvent(EditorArea editorArea, EditorChangeItem item) {
		super(TYPE, editorArea);
		if (item == null) {
			throw new IllegalArgumentException("[EditorBeforeChangeEvent] Editor change item is null");
		}
		this.item = item;
	}



	/**
	 * @return the item
	 */
	public final EditorChangeItem getItem() {
		return item;
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