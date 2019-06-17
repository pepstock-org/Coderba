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
 * Fires when the editor gutter (the line-number area) is clicked.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class EditorGutterClickEvent extends AbstractEditorWithNativeEvent<EditorGutterClickEventHandler> {

	/**
	 * Event type
	 */
	public static final Type<EditorGutterClickEventHandler> TYPE = new Type<>();
	/**
	 * Event name
	 */
	public static final String NAME = "gutterClick";
	// line number
	private final int line;
	// gutter CCSS class name
	private final String gutter;

	/**
	 * Creates an editor {@value NAME} event.
	 * 
	 * @param editorArea editor area instance
	 * @param line line number
	 * @param gutter CCS class name
	 * @param event native event instance
	 */
	public EditorGutterClickEvent(EditorArea editorArea, int line, String gutter, NativeEvent event) {
		super(TYPE, editorArea, event);
		this.line = line;
		this.gutter = gutter;
	}

	/**
	 * Returns the line number.
	 * 
	 * @return the line number
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Returns the CSS gutter class.
	 * 
	 * @return the CSS gutter class
	 */
	public String getGutter() {
		return gutter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
	 */
	@Override
	public Type<EditorGutterClickEventHandler> getAssociatedType() {
		return TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	protected void dispatch(EditorGutterClickEventHandler handler) {
		handler.onGutterClick(this);
	}

}