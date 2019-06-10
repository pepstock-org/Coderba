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
import org.pepstock.coderba.client.entities.LineWidget;

import com.google.gwt.event.shared.EventHandler;

/**
 * Abstract class for all events of line widgets of a document.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <H> type of event handler
 */
public abstract class AbstractLineWidgetEvent<H extends EventHandler> extends AbstractDocumentEvent<H> {

	// line widget instance
	private final LineWidget lineWidget;

	/**
	 * Creates a line widget event.
	 * 
	 * @param handlerType event handler type
	 * @param editorArea editor area instance
	 * @param document document instance
	 * @param lineWidget line widget instance
	 */
	public AbstractLineWidgetEvent(Type<? extends EventHandler> handlerType, EditorArea editorArea, Document document, LineWidget lineWidget) {
		super(handlerType, editorArea, document);
		// checks if line widget is consistent
		if (lineWidget == null) {
			// if no, exception
			throw new IllegalArgumentException("Line widget is null");
		}
		this.lineWidget = lineWidget;
	}

	/**
	 * Returns the line widget instance
	 * 
	 * @return the line widget instance
	 */
	public final LineWidget getLineWidget() {
		return lineWidget;
	}

}
