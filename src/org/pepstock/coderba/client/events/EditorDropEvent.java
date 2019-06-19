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
 * Fires whenever "drop" native event is fired by editor.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorDropEvent extends AbstractEditorWithNativeEvent<EditorDropEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorDropEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "drop";

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param event native event instance
	 */
	public EditorDropEvent(EditorArea editorArea, EditorNativeEvent event) {
		super(TYPE, editorArea, event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorDropEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorDropEventHandler handler) {
		handler.onDrop(this);
	}

}
