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
 * This is signalled when the editor's document is replaced using the <code>swapDoc</code> method.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorSwapDocEvent extends AbstractEditorEvent<EditorSwapDocEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorSwapDocEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "swapDoc";
	// document instance
	private final Document document;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param document old document instance
	 */
	public EditorSwapDocEvent(EditorArea editorArea, Document document) {
		super(TYPE, editorArea);
		// checks if argument is consistent
		if (document == null) {
			// if no, exception
			throw new IllegalArgumentException("Document is null");
		}
		this.document = document;
	}

	/**
	 * Returns the old document instance.
	 * 
	 * @return the old document instance
	 */
	public Document getDocument() {
		return document;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorSwapDocEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorSwapDocEventHandler handler) {
		handler.onSwap(this);
	}

}