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

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractDocumentEvent<H extends EventHandler> extends AbstractEditorEvent<H> {

	private final Document document;

	/**
	 * @param handlerType
	 */
	public AbstractDocumentEvent(Type<? extends EventHandler> handlerType, EditorArea editorArea, Document document) {
		super(handlerType, editorArea);
		if (document == null) {
			throw new IllegalArgumentException("[AbstarctDocumentEvent] Document is null");
		}
		this.document = document;
	}

	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

}
