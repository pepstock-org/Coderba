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
import org.pepstock.coderba.client.events.TextMarkerBeforeCursorEnterEventHandler;
import org.pepstock.coderba.client.events.TextMarkerClearEvent;
import org.pepstock.coderba.client.events.TextMarkerClearEventHandler;
import org.pepstock.coderba.client.events.TextMarkerHideEvent;
import org.pepstock.coderba.client.events.TextMarkerHideEventHandler;
import org.pepstock.coderba.client.events.TextMarkerUnhideEvent;
import org.pepstock.coderba.client.events.TextMarkerUnhideEventHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * Object, provided by CodeMirror, that can be used to mark a range of text with a specific CSS class name.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class TextMarker extends TextMarkerOptions implements IsEventManager {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called when the cursor enters the marked range.<br>
	 * From this event handler, the editor state may be inspected but not modified, with the exception that the range on which
	 * the event fires may be cleared.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerBeforeCursorEnterFunction {

		/**
		 * Is called when the cursor enters the marked range.<br>
		 * From this event handler, the editor state may be inspected but not modified, with the exception that the range on
		 * which the event fires may be cleared.
		 */
		void call();
	}

	/**
	 * Java script FUNCTION that is called when the range is cleared, either through cursor movement in combination with
	 * <code>clearOnEnter</code> or through a call to its <code>clear()</code> method.<br>
	 * Will only be fired once per handle.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerClearFunction {

		/**
		 * Is called when the range is cleared, either through cursor movement in combination with <code>clearOnEnter</code> or
		 * through a call to its <code>clear()</code> method.<br>
		 * Will only be fired once per handle.
		 * 
		 * @param from the starting position of part of the document that the range spanned when it was cleared.
		 * @param to the ending position of part of the document that the range spanned when it was cleared.
		 */
		void call(Position from, Position to);
	}

	/**
	 * Java script FUNCTION that is called when the last part of the marker is removed from the document by editing operations.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerHideFunction {

		/**
		 * Is called when the last part of the marker is removed from the document by editing operations.
		 */
		void call();
	}

	/**
	 * Java script FUNCTION that is called when, after the marker was removed by editing, a undo operation brought the marker
	 * back.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface TextMarkerUnhideFunction {

		/**
		 * Is called when, after the marker was removed by editing, a undo operation brought the marker back.
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
	// native marker object (generated by CodeMirror)
	private final NativeTextMarker nativeObject;
	// the current document managed by editor related to this marker
	private final Document document;
	// event manager instance
	private final EventManager eventManager;
	// event items manager instance
	private final EventItemManager eventItemManager;

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
	 * Creates a text marker instance wrapping a native CodeMirror object and the document which this text marker belongs to.
	 * 
	 * @param nativeObject a native CodeMirror object
	 * @param document document which this text marker belongs to
	 */
	TextMarker(NativeTextMarker nativeObject, Document document) {
		super(nativeObject);
		this.nativeObject = nativeObject;
		this.document = document;
		// sets event manager
		this.eventManager = new EventManager(this);
		this.eventItemManager = new EventItemManager();
		// stores the id of the marker
		Id.applyTo(nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		textMarkerBeforeCursorEnterFunctionProxy.setCallback(this::onBeforeCursorEnter);
		textMarkerClearFunctionProxy.setCallback(this::onClear);
		textMarkerHideFunctionProxy.setCallback(this::onHide);
		textMarkerUnhideFunctionProxy.setCallback(this::onUnhide);

		eventItemManager.addEventItem(
				new EventItem<TextMarkerBeforeCursorEnterEventHandler, NativeTextMarker>(TextMarkerBeforeCursorEnterEvent.TYPE, nativeObject, TextMarkerBeforeCursorEnterEvent.NAME, eventManager, textMarkerBeforeCursorEnterFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<TextMarkerClearEventHandler, NativeTextMarker>(TextMarkerClearEvent.TYPE, nativeObject, TextMarkerClearEvent.NAME, eventManager, textMarkerClearFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<TextMarkerHideEventHandler, NativeTextMarker>(TextMarkerHideEvent.TYPE, nativeObject, TextMarkerHideEvent.NAME, eventManager, textMarkerHideFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<TextMarkerUnhideEventHandler, NativeTextMarker>(TextMarkerUnhideEvent.TYPE, nativeObject, TextMarkerUnhideEvent.NAME, eventManager, textMarkerUnhideFunctionProxy.getProxy()));
	}

	/**
	 * Returns the text marker unique ID.
	 * 
	 * @return the text marker unique ID
	 */
	public String getId() {
		return Id.retrieveFrom(nativeObject);
	}

	/**
	 * Returns the document which this text marker belongs to.
	 * 
	 * @return the document which this text marker belongs to
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Returns <code>true</code> if it has been explicitly cleared by <code>clear()</code> method.
	 * 
	 * @return <code>true</code> if it has been explicitly cleared by <code>clear()</code> method
	 */
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
	 * Returns a range (both holding document positions), indicating the current position of the marked range, or undefined if
	 * the marker is no longer in the document.
	 * 
	 * @return a range (both holding document positions), indicating the current position of the marked range, or undefined if
	 *         the marker is no longer in the document.
	 */
	public Range find() {
		return nativeObject.find();
	}

	/**
	 * Called when you've done something that might change the size of the marker and want to cheaply update the display.
	 */
	public void changed() {
		// checks because bookmarks do not have this method
		if (isRange()) {
			nativeObject.changed();
		}
	}

	// ---------------------------------
	// --- EVENTS METHODS
	// ---------------------------------

	/**
	 * Fired when the cursor enters the marked range. From this event handler, the editor state may be inspected but not
	 * modified, with the exception that the range on which the event fires may be cleared.
	 */
	private void onBeforeCursorEnter() {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// checks if consistent
		if (area != null) {
			// fires events using itself as text marker
			eventManager.fireEvent(new TextMarkerBeforeCursorEnterEvent(this));
		}
	}

	/**
	 * Fired when the range is cleared, either through cursor movement in combination with <code>clearOnEnter</code> or through
	 * a call to its <code>clear()</code> method.<br>
	 * Will only be fired once per handle.<br>
	 * Note that deleting the range through text editing does not fire this event, because an undo action might bring the range
	 * back into existence.
	 * 
	 * @param from the starting position of part of the document that the range spanned when it was cleared.
	 * @param to the ending position of part of the document that the range spanned when it was cleared.
	 */
	private void onClear(Position from, Position to) {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// checks if consistent
		if (area != null) {
			// fires events using itself as text marker
			eventManager.fireEvent(new TextMarkerClearEvent(this, Range.create(from, to)));
		}
	}

	/**
	 * Fired when the last part of the marker is removed from the document by editing operations.
	 */
	private void onHide() {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// checks if consistent
		if (area != null) {
			// fires events using itself as text marker
			eventManager.fireEvent(new TextMarkerHideEvent(this));
		}
	}

	/**
	 * Fired when, after the marker was removed by editing, a undo operation brought the marker back.
	 */
	private void onUnhide() {
		// gets editor area
		EditorArea area = document.getEditorArea();
		// checks if consistent
		if (area != null) {
			// fires events using itself as text marker
			eventManager.fireEvent(new TextMarkerUnhideEvent(this));
		}
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
	 * @see org.pepstock.coderba.client.events.IsEventManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		return eventManager.addHandler(type, handler);
	}

}
