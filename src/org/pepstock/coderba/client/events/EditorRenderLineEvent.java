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
 * Fired whenever a line is (re-)rendered to the DOM element.<br>
 * Fired right after the DOM element is built, before it is added to the document.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorRenderLineEvent extends AbstractEditorEvent<EditorRenderLineEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorRenderLineEventHandler> TYPE = new Type<>();

	/**
	 * Event name
	 */
	public static final String NAME = "renderLine";
	// line handle instance
	private final LineHandle lineHandle;
	// DOM element instance
	private final Element element;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param lineHandle line handle instance
	 * @param element DOM element to render into line
	 */
	public EditorRenderLineEvent(EditorArea editorArea, LineHandle lineHandle, Element element) {
		super(TYPE, editorArea);
		// checks if line handle is consistent
		if (lineHandle == null) {
			// if no, exception
			throw new IllegalArgumentException("Line handle is null");
		}
		// checks if element is consistent
		if (element == null) {
			// if no, exception
			throw new IllegalArgumentException("Element is null");
		}
		this.lineHandle = lineHandle;
		this.element = element;
	}

	/**
	 * Returns the line handle.
	 * 
	 * @return the line handle instance
	 */
	public LineHandle getLineHandle() {
		return lineHandle;
	}

	/**
	 * Returns the DOM element.
	 * 
	 * @return the element instance
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