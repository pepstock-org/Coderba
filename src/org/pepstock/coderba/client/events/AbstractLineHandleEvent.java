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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.entities.Document;
import org.pepstock.coderba.client.entities.LineHandle;

import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract class for all events of line handles of a document.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <H> type of event handler
 */
public abstract class AbstractLineHandleEvent<H extends EventHandler> extends AbstractDocumentEvent<H> {

	// line handle instance
	private final LineHandle lineHandle;

	/**
	 * Creates a line handle event.
	 * 
	 * @param handlerType event handler type
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param lineHandle line handle instance
	 */
	public AbstractLineHandleEvent(Type<? extends EventHandler> handlerType, EditorArea editorArea, Document document, LineHandle lineHandle) {
		super(handlerType, editorArea, document);
		// checks if line handle is consistent
		if (lineHandle == null) {
			// if no, exception
			throw new IllegalArgumentException("Line handle is null");
		}
		this.lineHandle = lineHandle;
	}

	/**
	 * Returns the line handle instance.
	 * 
	 * @return the line handle instance
	 */
	public final LineHandle getLineHandle() {
		return lineHandle;
	}

}
