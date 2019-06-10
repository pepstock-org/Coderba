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

import com.google.gwt.dom.client.NativeEvent;

/**
 * Event which is fired when new event handler has been removed to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorKeyHandledEvent extends AbstractEditorEvent<EditorKeyHandledEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorKeyHandledEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "keyHandled";

	private final String name;

	private final NativeEvent event;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public EditorKeyHandledEvent(EditorArea editorArea, String name, NativeEvent event) {
		super(TYPE, editorArea);
		if (name == null) {
			throw new IllegalArgumentException("[EditorKeyHandledEvent] Name is null");
		}
		if (event == null) {
			throw new IllegalArgumentException("[EditorKeyHandledEvent] Event is null");
		}
		this.name = name;
		this.event = event;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the event
	 */
	public final NativeEvent getEvent() {
		return event;
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