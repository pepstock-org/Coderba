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
import org.pepstock.coderba.client.entities.Range;
import org.pepstock.coderba.client.entities.TextMarker;

/**
 * Fired when the range is cleared, either through cursor movement in combination with <code>clearOnEnter</code> or through a
 * call to its <code>clear()</code> method.<br>
 * Will only be fired once per handle.<br>
 * Note that deleting the range through text editing does not fire this event, because an undo action might bring the range back
 * into existence.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class TextMarkerClearEvent extends AbstractTextMarkerEvent<TextMarkerClearEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<TextMarkerClearEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "clear";
	// range instance
	private final Range range;

	/**
	 * Creates a text marker {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param textMarker text marker instance
	 * @param range range instance which gives the part of the document that the range spanned when it was cleared.
	 */
	public TextMarkerClearEvent(EditorArea editorArea, Document document, TextMarker textMarker, Range range) {
		super(TYPE, editorArea, document, textMarker);
		// checks if range is consistent
		if (range == null) {
			// if no, excpetion
			throw new IllegalArgumentException("Range is null");
		}
		this.range = range;
	}

	/**
	 * Returns the range which gives the part of the document that the range spanned when it was cleared.
	 * 
	 * @return the range instance
	 */
	public Range getRange() {
		return range;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<TextMarkerClearEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(TextMarkerClearEventHandler handler) {
		handler.onClear(this);
	}

}