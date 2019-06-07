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
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.ChangeItem;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.LineHandleChangeEvent;
import org.pepstock.coderba.client.events.LineHandleDeleteEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;
import org.pepstock.coderba.client.utils.Window;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineHandle implements IsEventManager {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called every time the content of the document is changed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineHandleChangeFunction {

		/**
		 * Is called every time the content of the document is changed.
		 * 
		 * @param document native document instance
		 * @param item document change item
		 */
		void call(NativeLineHandle lineHandle, ChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineHandleDeleteFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call();
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the LineHandleChange function
	private final CallbackProxy<LineHandleChangeFunction> lineHandleChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the LineHandleDelete function
	private final CallbackProxy<LineHandleDeleteFunction> lineHandleDeleteFunctionProxy = JsHelper.get().newCallbackProxy();

	private final NativeLineHandle nativeObject;

	private final EventManager eventManager;

	private final Document document;

	/**
	 * 
	 * @param nativeObject
	 */
	LineHandle(NativeLineHandle nativeObject, Document document) {
		this.nativeObject = nativeObject;
		Window.getConsole().log(nativeObject);
		this.document = document;
		// stores the id based on a counter
		Id.set(nativeObject);
		// sets event manager
		this.eventManager = new EventManager(this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		lineHandleChangeFunctionProxy.setCallback((lineHandle, item) -> onChange(lineHandle, item));
		lineHandleDeleteFunctionProxy.setCallback(() -> onDelete());
	}

	public String getId() {
		return Id.get(nativeObject);
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public String getText() {
		return nativeObject.getText();
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public double getHeight() {
		return nativeObject.getHeight();
	}

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text
	 */
	public int lineNo() {
		return nativeObject.lineNo();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeLineHandle getObject() {
		return nativeObject;
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
	private void onChange(NativeLineHandle nativeLineHandle, ChangeItem item) {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			LineHandle lineHandle = document.getLineHandleById(Id.get(nativeLineHandle));
			eventManager.fireEvent(new LineHandleChangeEvent(area, document, lineHandle, item));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onDelete() {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new LineHandleDeleteEvent(area, document, this));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.coderba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
		if (event.isRecognize(LineHandleChangeEvent.TYPE)) {
			// checks if type of removed event handler is LineHandleChangeEvent
			// if there is not any LineHandleChangeEvent handler
			if (eventManager.getHandlerCount(LineHandleChangeEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(LineHandleChangeEvent.NAME, lineHandleChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(LineHandleDeleteEvent.TYPE)) {
			// checks if type of removed event handler is LineHandleDeleteEvent
			// if there is not any LineHandleDeleteEvent handler
			if (eventManager.getHandlerCount(LineHandleDeleteEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(LineHandleDeleteEvent.NAME, lineHandleDeleteFunctionProxy.getProxy());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		if (event.isRecognize(LineHandleChangeEvent.TYPE)) {
			// checks if type of added event handler is LineHandleChangeEvent
			// if there is not any LineHandleChangeEvent handler
			if (eventManager.getHandlerCount(LineHandleChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(LineHandleChangeEvent.NAME, lineHandleChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(LineHandleDeleteEvent.TYPE)) {
			// checks if type of added event handler is LineHandleDeleteEvent
			// if there is not any LineHandleDeleteEvent handler
			if (eventManager.getHandlerCount(LineHandleDeleteEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(LineHandleDeleteEvent.NAME, lineHandleDeleteFunctionProxy.getProxy());
			}
		}
	}

}
