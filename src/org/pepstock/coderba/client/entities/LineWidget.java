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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.LineWidgetRedrawEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineWidget extends LineWidgetOptions implements IsEventManager {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineWidgetRedrawFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call();
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the LineHandleDelete function
	private final CallbackProxy<LineWidgetRedrawFunction> lineWidgetRedrawFunctionProxy = JsHelper.get().newCallbackProxy();

	private final NativeLineWidget nativeObject;

	private final Document document;

	private final LineHandle handle;

	private final EventManager eventManager;

	private static final Element DEFAULT_NODE = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		NODE("node"),
		HEIGHT("height");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	/**
	 * 
	 * @param nativeObject
	 * @param document
	 */
	LineWidget(NativeLineWidget nativeObject, Document document) {
		super(nativeObject);
		this.nativeObject = nativeObject;
		this.document = document;
		// stores the id based on a counter
		Id.set(nativeObject);
		NativeLineHandle nativeHandle = nativeObject.getLine();
		if (nativeHandle != null) {
			this.handle = document.getLineHandleById(Id.get(nativeHandle));
		} else {
			this.handle = null;
		}
		// sets event manager
		this.eventManager = new EventManager(this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		lineWidgetRedrawFunctionProxy.setCallback(() -> onRedraw());
	}

	public String getId() {
		return Id.get(nativeObject);
	}

	public int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}

	public Element getNode() {
		return getValue(Property.NODE, DEFAULT_NODE);
	}

	/**
	 * @return the handle
	 */
	public LineHandle getHandle() {
		return handle;
	}

	/**
	 * Removes the widget.
	 */
	public void clear() {
		document.clearLineWidget(getId());
		nativeObject.clear();
	}

	/**
	 * Call this if you made some change to the widget's DOM node that might affect its height.<br>
	 * It'll force CodeMirror to update the height of the line that contains the widget.
	 */
	public void changed() {
		nativeObject.changed();
	}

	// ---------------------------------
	// --- EVENTS METHODS
	// ---------------------------------

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onRedraw() {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new LineWidgetRedrawEvent(area, document, this));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		if (event.isRecognize(LineWidgetRedrawEvent.TYPE)) {
			// checks if type of added event handler is LineWidgetRedrawEvent
			// if there is not any LineWidgetRedrawEvent handler
			if (eventManager.getHandlerCount(LineWidgetRedrawEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(LineWidgetRedrawEvent.NAME, lineWidgetRedrawFunctionProxy.getProxy());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.coderba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
		if (event.isRecognize(LineWidgetRedrawEvent.TYPE)) {
			// checks if type of removed event handler is LineWidgetRedrawEvent
			// if there is not any LineWidgetRedrawEvent handler
			if (eventManager.getHandlerCount(LineWidgetRedrawEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(LineWidgetRedrawEvent.NAME, lineWidgetRedrawFunctionProxy.getProxy());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.IsEventManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		return eventManager.addHandler(type, handler);
	}

}
