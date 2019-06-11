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
 * Fires whenever the view port of the editor changes (due to scrolling, editing, or any other factor).
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorViewportChangeEvent extends AbstractEditorEvent<EditorViewportChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorViewportChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "viewportChange";
	// starting line number
	private final int from;
	// ending line number
	private final int to;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param from starting line number
	 * @param to ending line number
	 */
	public EditorViewportChangeEvent(EditorArea editorArea, int from, int to) {
		super(TYPE, editorArea);
		this.from = from;
		this.to = to;
	}

	/**
	 * Returns the starting line number.
	 * 
	 * @return the starting line number.
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * Returns the ending line number
	 * 
	 * @return the ending line number
	 */
	public int getTo() {
		return to;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorViewportChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorViewportChangeEventHandler handler) {
		handler.onViewportChange(this);
	}

}