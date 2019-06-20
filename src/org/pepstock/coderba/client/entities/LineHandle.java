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
import org.pepstock.coderba.client.events.LineHandleChangeEventHandler;
import org.pepstock.coderba.client.events.LineHandleDeleteEvent;
import org.pepstock.coderba.client.events.LineHandleDeleteEventHandler;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * Entity which maps a single line of document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineHandle implements IsEventManager {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called when the line's text content is changed in any way (but the line is not deleted
	 * outright).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineHandleChangeFunction {

		/**
		 * Is called when the line's text content is changed in any way (but the line is not deleted outright).
		 * 
		 * @param lineHandle line handle instance related to the event
		 * @param item line handle change item
		 */
		void call(NativeLineHandle lineHandle, ChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called when the line object is deleted.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineHandleDeleteFunction {

		/**
		 * Is called when the line object is deleted.
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
	// native line handle object (generated by CodeMirror)
	private final NativeLineHandle nativeObject;
	// event manager instance
	private final EventManager eventManager;
	// the current document related to this line handle
	private final Document document;
	// event items manager instance
	private final EventItemManager eventItemManager;

	/**
	 * Creates a line handle instance wrapping a native CodeMirror object and the document which this line handle belongs to.
	 * 
	 * @param nativeObject a native CodeMirror object
	 * @param document document which this line handle belongs to
	 */
	LineHandle(NativeLineHandle nativeObject, Document document) {
		this.nativeObject = nativeObject;
		this.document = document;
		// stores the id based on a counter
		Id.applyTo(nativeObject);
		// sets event manager
		this.eventManager = new EventManager(this);
		this.eventItemManager = new EventItemManager();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		lineHandleChangeFunctionProxy.setCallback(this::onChange);
		lineHandleDeleteFunctionProxy.setCallback(this::onDelete);

		eventItemManager.addEventItem(new EventItem<LineHandleChangeEventHandler, NativeLineHandle>(LineHandleChangeEvent.TYPE, nativeObject, LineHandleChangeEvent.NAME, eventManager, lineHandleChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<LineHandleDeleteEventHandler, NativeLineHandle>(LineHandleDeleteEvent.TYPE, nativeObject, LineHandleDeleteEvent.NAME, eventManager, lineHandleDeleteFunctionProxy.getProxy()));
		// register to document
		document.register(this);
	}

	/**
	 * Returns the line handle unique ID.
	 * 
	 * @return the line handle unique ID
	 */
	public String getId() {
		return Id.retrieveFrom(nativeObject);
	}

	/**
	 * Returns the document which this line handle belongs to.
	 * 
	 * @return the document which this line handle belongs to
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Gets the content of the document related to this line handle.
	 * 
	 * @return the content of the document related to this line handle
	 */
	public String getText() {
		return nativeObject.getText();
	}

	/**
	 * Gets the height of the line.
	 * 
	 * @return the height of the line
	 */
	public double getHeight() {
		return nativeObject.getHeight();
	}

	/**
	 * Returns the line number of the handle into document.
	 * 
	 * @return the line number of the handle into document
	 */
	public int getLineNumber() {
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
	 * Fires when the line's text content is changed in any way (but the line is not deleted outright).
	 * 
	 * @param nativeLineHandle line handle instance related to the event
	 * @param item line handle change item
	 */
	private void onChange(NativeLineHandle nativeLineHandle, ChangeItem item) {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// if area is consistent
		if (area != null) {
			// fires event
			eventManager.fireEvent(new LineHandleChangeEvent(this, item));
		}
	}

	/**
	 * Will be fired when the line object is deleted.
	 */
	private void onDelete() {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// if area is consistent
		if (area != null) {
			// fires event using this line handle as subject of event
			eventManager.fireEvent(new LineHandleDeleteEvent(this));
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
		eventItemManager.checkAndOff(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		eventItemManager.checkAndOn(event);
	}

}
