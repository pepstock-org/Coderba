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
import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract class for all events of editors where a native event is laways passed as argument.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <H> type of event handler
 */
public abstract class AbstractEditorWithNativeEvent<H extends EventHandler> extends AbstractEditorEvent<H> {

	private final NativeEvent event;

	/**
	 * Creates an editor event.
	 * 
	 * @param handlerType event handler type
	 * @param editorArea editor area instance
	 * @param event native event instance
	 */
	public AbstractEditorWithNativeEvent(Type<? extends EventHandler> handlerType, EditorArea editorArea, NativeEvent event) {
		super(handlerType, editorArea);
		this.event = event;
	}

	/**
	 * Returns the native event instance.
	 * 
	 * @return the native event instance
	 */
	public final NativeEvent getEvent() {
		return event;
	}

}