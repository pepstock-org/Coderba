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
 * Fired after a key is handled through a key map.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorKeyHandledEvent extends AbstractEditorWithNativeEvent<EditorKeyHandledEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorKeyHandledEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "keyHandled";
	// key map instance
	private final String keyName;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param event native DOM keydown or keypress event instance
	 * @param keyName the name of the handled key (for example "Ctrl-X" or "'q'")
	 */
	public EditorKeyHandledEvent(EditorArea editorArea, EditorNativeEvent event, String keyName) {
		super(TYPE, editorArea, event);
		// checks if name is consistent
		if (keyName == null) {
			// if no, exception
			throw new IllegalArgumentException("Name is null");
		}
		this.keyName = keyName;
	}

	/**
	 * Returns the name of handled key
	 * 
	 * @return the name of handled key
	 */
	public String getKeyName() {
		return keyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorKeyHandledEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorKeyHandledEventHandler handler) {
		handler.onKeyHandled(this);
	}

}