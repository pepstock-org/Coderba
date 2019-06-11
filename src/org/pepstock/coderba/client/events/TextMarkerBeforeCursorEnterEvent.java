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
import org.pepstock.coderba.client.entities.TextMarker;

/**
 * Fired when the cursor enters the marked range.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TextMarkerBeforeCursorEnterEvent extends AbstractTextMarkerEvent<TextMarkerBeforeCursorEnterEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<TextMarkerBeforeCursorEnterEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "beforeCursorEnter";

	/**
	 * Creates a text marker {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param textMarker text marker instance
	 */
	public TextMarkerBeforeCursorEnterEvent(EditorArea editorArea, Document document, TextMarker textMarker) {
		super(TYPE, editorArea, document, textMarker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<TextMarkerBeforeCursorEnterEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(TextMarkerBeforeCursorEnterEventHandler handler) {
		handler.onBeforeCursorEnter(this);
	}

}