/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
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

import org.pepstock.coderba.client.entities.TextMarker;

import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract class for all events of text markers of a document.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <H> type of event handlerf
 */
public abstract class AbstractTextMarkerEvent<H extends EventHandler> extends AbstractHandlerEvent<H> {

	// text marker instance
	private final TextMarker textmarker;

	/**
	 * Creates a text marker event.
	 * 
	 * @param handlerType event handler type
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param textMarker text marker instance
	 */
	public AbstractTextMarkerEvent(Type<? extends EventHandler> handlerType, TextMarker textMarker) {
		super(handlerType);
		// checks if text marker is consistent
		if (textMarker == null) {
			// if no, exception
			throw new IllegalArgumentException("Text marker handle is null");
		}
		this.textmarker = textMarker;
	}

	/**
	 * Returns the text marker instance.
	 * 
	 * @return the text marker instance
	 */
	public final TextMarker getTextmarker() {
		return textmarker;
	}

}
