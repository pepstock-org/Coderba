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
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;
import org.pepstock.coderba.client.events.TextMarkerBeforeCursorEnterEvent;
import org.pepstock.coderba.client.events.TextMarkerClearEvent;
import org.pepstock.coderba.client.events.TextMarkerHideEvent;
import org.pepstock.coderba.client.events.TextMarkerUnhideEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TextMarker extends TextMarkerOptions implements IsEventManager {

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
	interface TextMarkerBeforeCursorEnterFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call();
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerClearFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call(Position from, Position to);
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerHideFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call();
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerUnhideFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 */
		void call();
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the TextMarkerBeforeCursorEnter function
	private final CallbackProxy<TextMarkerBeforeCursorEnterFunction> textMarkerBeforeCursorEnterFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the TextMarkerBeforeCursorEnter function
	private final CallbackProxy<TextMarkerClearFunction> textMarkerClearFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the TextMarkerBeforeCursorEnter function
	private final CallbackProxy<TextMarkerHideFunction> textMarkerHideFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the TextMarkerBeforeCursorEnter function
	private final CallbackProxy<TextMarkerUnhideFunction> textMarkerUnhideFunctionProxy = JsHelper.get().newCallbackProxy();

	private final NativeTextMarker nativeObject;

	private final Document document;

	private final EventManager eventManager;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		EXPLICITLY_CLEARED("explicitlyCleared");

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
	 */
	TextMarker(NativeTextMarker nativeObject, Document document) {
		super(nativeObject);
		this.nativeObject = nativeObject;
		this.document = document;
		// sets event manager
		this.eventManager = new EventManager(this);
		Id.set(nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		textMarkerBeforeCursorEnterFunctionProxy.setCallback(() -> onBeforeCursorEnter());
		textMarkerClearFunctionProxy.setCallback((from, to) -> onClear(from, to));
		textMarkerHideFunctionProxy.setCallback(() -> onHide());
		textMarkerUnhideFunctionProxy.setCallback(() -> onUnhide());
	}

	public String getId() {
		return Id.get(nativeObject);
	}

	public boolean isCleared() {
		return getValue(Property.EXPLICITLY_CLEARED, false);
	}

	/**
	 * Remove the mark.
	 */
	public void clear() {
		document.clearTextMarker(getId());
		nativeObject.clear();
	}

	/**
	 * Returns a {from, to} object (both holding document positions), indicating the current position of the marked range, or
	 * undefined if the marker is no longer in the document.
	 * 
	 * @return
	 */
	public Range find() {
		return nativeObject.find();
	};

	/**
	 * Called when you've done something that might change the size of the marker and want to cheaply update the display.
	 */
	public void changed() {
		// bookmarks do not have this method
		if (isRange()) {
			nativeObject.changed();
		}
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
	private void onBeforeCursorEnter() {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new TextMarkerBeforeCursorEnterEvent(area, document, this));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onClear(Position from, Position to) {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new TextMarkerClearEvent(area, document, this, Range.create(from, to)));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onHide() {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new TextMarkerHideEvent(area, document, this));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onUnhide() {
		EditorArea area = document.getEditorArea();
		if (area != null) {
			eventManager.fireEvent(new TextMarkerUnhideEvent(area, document, this));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		if (event.isRecognize(TextMarkerBeforeCursorEnterEvent.TYPE)) {
			// checks if type of added event handler is TextMarkerBeforeCursorEnterEvent
			// if there is not any TextMarkerBeforeCursorEnterEvent handler
			if (eventManager.getHandlerCount(TextMarkerBeforeCursorEnterEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(TextMarkerBeforeCursorEnterEvent.NAME, textMarkerBeforeCursorEnterFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerClearEvent.TYPE)) {
			// checks if type of added event handler is TextMarkerClearEvent
			// if there is not any TextMarkerClearEvent handler
			if (eventManager.getHandlerCount(TextMarkerClearEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(TextMarkerClearEvent.NAME, textMarkerClearFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerHideEvent.TYPE)) {
			// checks if type of added event handler is TextMarkerHideEvent
			// if there is not any TextMarkerHideEvent handler
			if (eventManager.getHandlerCount(TextMarkerHideEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(TextMarkerHideEvent.NAME, textMarkerHideFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerUnhideEvent.TYPE)) {
			// checks if type of added event handler is TextMarkerUnhideEvent
			// if there is not any TextMarkerUnhideEvent handler
			if (eventManager.getHandlerCount(TextMarkerUnhideEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(TextMarkerUnhideEvent.NAME, textMarkerUnhideFunctionProxy.getProxy());
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
		if (event.isRecognize(TextMarkerBeforeCursorEnterEvent.TYPE)) {
			// checks if type of removed event handler is TextMarkerBeforeCursorEnterEvent
			// if there is not any TextMarkerBeforeCursorEnterEvent handler
			if (eventManager.getHandlerCount(TextMarkerBeforeCursorEnterEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(TextMarkerBeforeCursorEnterEvent.NAME, textMarkerBeforeCursorEnterFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerClearEvent.TYPE)) {
			// checks if type of removed event handler is TextMarkerClearEvent
			// if there is not any TextMarkerClearEvent handler
			if (eventManager.getHandlerCount(TextMarkerClearEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(TextMarkerClearEvent.NAME, textMarkerClearFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerHideEvent.TYPE)) {
			// checks if type of removed event handler is TextMarkerHideEvent
			// if there is not any TextMarkerHideEvent handler
			if (eventManager.getHandlerCount(TextMarkerHideEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(TextMarkerHideEvent.NAME, textMarkerHideFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(TextMarkerUnhideEvent.TYPE)) {
			// checks if type of removed event handler is TextMarkerUnhideEvent
			// if there is not any TextMarkerUnhideEvent handler
			if (eventManager.getHandlerCount(TextMarkerUnhideEvent.TYPE) == 0) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.off(TextMarkerUnhideEvent.NAME, textMarkerUnhideFunctionProxy.getProxy());
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
