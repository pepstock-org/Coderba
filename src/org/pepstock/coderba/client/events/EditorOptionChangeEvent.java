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
 * Dispatched every time an option is changed with <code>setOption</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorOptionChangeEvent extends AbstractEditorEvent<EditorOptionChangeEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorOptionChangeEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "optionChange";
	// property name instance
	private final String property;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param property property name
	 */
	public EditorOptionChangeEvent(EditorArea editorArea, String property) {
		super(TYPE, editorArea);
		// checks if argument is consistent
		if (property == null) {
			// if no, exception
			throw new IllegalArgumentException("Property is null");
		}
		this.property = property;
	}

	/**
	 * Returns the property name.
	 * 
	 * @return the property name
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