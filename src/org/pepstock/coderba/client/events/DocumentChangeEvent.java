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

/**
 * Fired whenever a change occurs to the document. 
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DocumentChangeEvent extends AbstractDocumentEvent<DocumentChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<DocumentChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "change";
	// change item instance
	private final ChangeItem item;

	/**
	 * Creates a document {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param item change item instance
	 */
	public DocumentChangeEvent(EditorArea editorArea, Document document, ChangeItem item) {
		super(TYPE, editorArea, document);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<DocumentChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(DocumentChangeEventHandler handler) {
		handler.onChange(this);
	}

}