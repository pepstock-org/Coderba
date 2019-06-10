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
import org.pepstock.coderba.client.entities.Anchor;
import org.pepstock.coderba.client.entities.Document;

/**
 * This event is fired before the selection is moved.<br>
 * Its handler may inspect the set of selection ranges, present as an array of anchors, and optionally change them by calling
 * the update method on this object, passing an array of ranges in the same format FIXME .<br>
 * The object also contains an origin property holding the origin string passed to the selection-changing method, if any.<br>
 * Handlers for this event have the same restriction as "beforeChange" handlers — they should not do anything to directly update
 * the state of the editor.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DocumentBeforeSelectionChangeEvent extends AbstractDocumentEvent<DocumentBeforeSelectionChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<DocumentBeforeSelectionChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "beforeSelectionChange";
	// instance of anchor of selection
	private final Anchor anchor;

	/**
	 * Creates a document "beforeSelectionChange" event.
	 * 
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param anchor anhcor of selection
	 */
	public DocumentBeforeSelectionChangeEvent(EditorArea editorArea, Document document, Anchor anchor) {
		super(TYPE, editorArea, document);
		// checks if anchor is consistent
		if (anchor == null) {
			// if no, excpetion
			throw new IllegalArgumentException("Selection Anchor is null");
		}
		this.anchor = anchor;
	}

	/**
	 * Returns the anchor instance to selection.
	 * 
	 * @return the anchor instance to selection
	 */
	public final Anchor getSelectionAnchor() {
		return anchor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<DocumentBeforeSelectionChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(DocumentBeforeSelectionChangeEventHandler handler) {
		handler.onBeforeSelectionChange(this);
	}

}