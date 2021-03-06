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
 * Fired if text input matched the mode's electric patterns, and this caused the line's indentation to change.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorElectrictInputEvent extends AbstractEditorEvent<EditorElectrictInputEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorElectrictInputEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "electricInput";
	// line number
	private final int line;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param line line number
	 */
	public EditorElectrictInputEvent(EditorArea editorArea, int line) {
		super(TYPE, editorArea);
		this.line = line;
	}

	/**
	 * Returns the line number.
	 * 
	 * @return the line number
	 */
	public int getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorElectrictInputEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorElectrictInputEventHandler handler) {
		handler.onElectrictInput(this);
	}

}