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
 * Event which is fired when new event handler has been removed to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorOptionChangeEvent extends AbstractEditorEvent<EditorOptionChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorOptionChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name of CodeMirror
	 */
	public static final String NAME = "optionChange";

	private final String property;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public EditorOptionChangeEvent(EditorArea editorArea, String property) {
		super(TYPE, editorArea);
		this.property = property;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorOptionChangeEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorOptionChangeEventHandler handler) {
		handler.onOptionChange(this);
	}

}