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

import org.pepstock.coderba.client.Editor;
import org.pepstock.coderba.client.EditorArea;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractEditorEvent<H extends EventHandler> extends AbstractHandlerEvent<H> {
	
	private final EditorArea editorArea;

	/**
	 * @param handlerType
	 */
	public AbstractEditorEvent(Type<? extends EventHandler> handlerType, EditorArea editorArea) {
		super(handlerType);
		if (editorArea == null) {
			throw new IllegalArgumentException("[AbstarctEditorEvent] Editor area is null");
		}
		this.editorArea = editorArea;
	}
	
	/**
	 * @return the editorArea
	 */
	public final EditorArea getEditorArea() {
		return editorArea;
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.GwtEvent#getSource()
	 */
	@Override
	public final Editor getSource() {
		return (Editor) super.getSource();
	}

}
