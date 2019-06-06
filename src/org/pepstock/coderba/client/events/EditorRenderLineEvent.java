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
import org.pepstock.coderba.client.entities.LineHandle;

import com.google.gwt.dom.client.Element;

/**
 * Event which is fired when new event handler has been removed to the chart.<br>
 * This event should use only for use internal only to manage internally all handlers.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorRenderLineEvent extends AbstractEditorEvent<EditorRenderLineEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorRenderLineEventHandler> TYPE = new Type<>();
	/**
	 * Event name of CodeMirror
	 */
	public static final String NAME = "renderLine";

	private final LineHandle lineHandle;

	private final Element element;

	/**
	 * Creates the event with the type of removed handler.
	 * 
	 * @param handlerType the type of removed handler.
	 */
	public EditorRenderLineEvent(EditorArea editorArea, LineHandle lineHandle, Element element) {
		super(TYPE, editorArea);
		this.lineHandle = lineHandle;
		this.element = element;
	}

	/**
	 * @return the lineHandle
	 */
	public LineHandle getLineHandle() {
		return lineHandle;
	}

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorRenderLineEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorRenderLineEventHandler handler) {
		handler.onRenderLine(this);
	}

}