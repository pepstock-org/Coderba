/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
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
import org.pepstock.coderba.client.EditorAreas;
import org.pepstock.coderba.client.addons.AddOnDialog;
import org.pepstock.coderba.client.callbacks.DialogCloseHandler;
import org.pepstock.coderba.client.callbacks.DialogInputHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyDownHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyUpHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.NodeList;

import jsinterop.annotations.JsFunction;

/**
 * Related to {@link AddOnDialog}, the options to configure a dialog and notification.<br>
 * A dialog provides a very simple way to query users for text input, at the top of the editor.<br>
 * A notification simply shows an HTML fragment as a notification at the top of the editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DialogOptions extends BaseEntity {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called whenever keydown fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DialogKeyDownFunction {

		/**
		 * Is called whenever keydown fires in the dialog's input.<br>
		 * If the callback returns true, the dialog will not do any further processing of the event.
		 * 
		 * @param event keydown event instance
		 * @param value dialog value
		 * @return if the callback returns true, the dialog will not do any further processing of the event
		 */
		boolean call(NativeEvent event, String value);
	}

	/**
	 * Java script FUNCTION that is called whenever keyup fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DialogKeyUpFunction {

		/**
		 * Is called whenever keyup fires in the dialog's input.<br>
		 * If the callback returns true, the dialog will not do any further processing of the event.
		 * 
		 * @param event keyup event instance
		 * @param value dialog value
		 * @return if the callback returns true, the dialog will not do any further processing of the event
		 */
		boolean call(NativeEvent event, String value);
	}

	/**
	 * Java script FUNCTION that is called whenever input event fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DialogInputFunction {

		/**
		 * Is called whenever input event fires in the dialog's input.<br>
		 * If the callback returns true, the dialog will not do any further processing of the event.
		 * 
		 * @param event input event instance
		 * @param value dialog value
		 * @return if the callback returns true, the dialog will not do any further processing of the event
		 */
		boolean call(NativeEvent event, String value);
	}

	/**
	 * Java script FUNCTION that is called after the dialog has been closed and removed from the DOM.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DialogCloseFunction {

		/**
		 * Is called after the dialog has been closed and removed from the DOM.
		 * 
		 * @param element dialog DOM element
		 */
		void call(Element element);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the DialogKeyDownFunction function
	private final CallbackProxy<DialogKeyDownFunction> dialogKeyDownFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DialogKeyUpFunction function
	private final CallbackProxy<DialogKeyUpFunction> dialogKeyUpFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DialogInputFunction function
	private final CallbackProxy<DialogInputFunction> dialogInputFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DialogCloseFunction function
	private final CallbackProxy<DialogCloseFunction> dialogCloseFunctionProxy = JsHelper.get().newCallbackProxy();
	// key down event user handler instance
	private DialogKeyDownHandler keyDownHandler = null;
	// keyup event user handler instance
	private DialogKeyUpHandler keyUpHandler = null;
	// input event user handler instance
	private DialogInputHandler inputHandler = null;
	// close dialog user handler instance
	private DialogCloseHandler closeHandler = null;

	/**
	 * Default value if the dialog will be closed when the user presses enter in the input, {@value DEFAULT_CLOSE_ON_ENTER}.
	 */
	public static final boolean DEFAULT_CLOSE_ON_ENTER = true;

	/**
	 * Default value whether the dialog is closed when it loses focus, {@value DEFAULT_CLOSE_ON_BLUR}.
	 */
	public static final boolean DEFAULT_CLOSE_ON_BLUR = true;

	/**
	 * Default value to specify the amount of time after which the notification will be automatically closed,
	 * {@value DEFAULT_DURATION}.
	 */
	public static final int DEFAULT_DURATION = 3000;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CLOSE_ON_ENTER("closeOnEnter"),
		CLOSE_ON_BLUR("closeOnBlur"),
		DURATION("duration"),
		ON_KEY_DOWN("onKeyDown"),
		ON_KEY_UP("onKeyUp"),
		ON_INPUT("onInput"),
		ON_CLOSE("onClose");

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
	 * Creates the options to be consumed.
	 */
	public DialogOptions() {
		// ---------------------------------------
		// -- SET CALLBACKS to PROXIES from ADDONS
		// ---------------------------------------
		dialogKeyDownFunctionProxy.setCallback(this::onKeyDown);
		dialogKeyUpFunctionProxy.setCallback(this::onKeyUp);
		dialogInputFunctionProxy.setCallback(this::onInput);
		dialogCloseFunctionProxy.setCallback(this::onClose);
		// sets default duration
		setValue(Property.DURATION, DEFAULT_DURATION);
	}

	/**
	 * Sets <code>true</code> if the dialog will be closed when the user presses enter in the input.
	 * 
	 * @param closeOnEnter <code>true</code> if the dialog will be closed when the user presses enter in the input
	 */
	public void setCloseOnEnter(boolean closeOnEnter) {
		setValue(Property.CLOSE_ON_ENTER, closeOnEnter);
	}

	/**
	 * Returns <code>true</code> if the dialog will be closed when the user presses enter in the input.
	 * 
	 * @return <code>true</code> if the dialog will be closed when the user presses enter in the input
	 */
	public boolean isCloseOnEnter() {
		return getValue(Property.CLOSE_ON_ENTER, DEFAULT_CLOSE_ON_ENTER);
	}

	/**
	 * Sets <code>true</code> whether the dialog is closed when it loses focus.
	 * 
	 * @param closeOnBlur <code>true</code> whether the dialog is closed when it loses focus
	 */
	public void setCloseOnBlur(boolean closeOnBlur) {
		setValue(Property.CLOSE_ON_ENTER, closeOnBlur);
	}

	/**
	 * Returns <code>true</code> whether the dialog is closed when it loses focus.
	 * 
	 * @return <code>true</code> whether the dialog is closed when it loses focus
	 */
	public boolean isCloseOnBlur() {
		return getValue(Property.CLOSE_ON_ENTER, DEFAULT_CLOSE_ON_ENTER);
	}

	/**
	 * Sets the amount of time after which the notification will be automatically closed.
	 * 
	 * @param duration the amount of time after which the notification will be automatically closed
	 */
	public void setDuration(int duration) {
		setValue(Property.DURATION, duration);
	}

	/**
	 * Returns the amount of time after which the notification will be automatically closed.
	 * 
	 * @return the amount of time after which the notification will be automatically closed
	 */
	public int getDuration() {
		return getValue(Property.DURATION, DEFAULT_DURATION);
	}

	/**
	 * Returns the "keydown" event handler instance.
	 * 
	 * @return the "keydown" event handler instance
	 */
	public DialogKeyDownHandler getKeyDownHandler() {
		return keyDownHandler;
	}

	/**
	 * Sets the "keydown" event handler instance
	 * 
	 * @param keyDownHandler the "keydown" event handler instance
	 */
	public void setKeyDownHandler(DialogKeyDownHandler keyDownHandler) {
		this.keyDownHandler = keyDownHandler;
		// checks if handler is consistent
		if (keyDownHandler != null) {
			// if yes, sets callback proxy
			setValue(Property.ON_KEY_DOWN, dialogKeyDownFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_KEY_DOWN);
		}
	}

	/**
	 * Returns the "keyup" event handler instance.
	 * 
	 * @return the "keyup" event handler instance
	 */
	public DialogKeyUpHandler getKeyUpHandler() {
		return keyUpHandler;
	}

	/**
	 * Sets the "keyup" event handler instance.
	 * 
	 * @param keyUpHandler the "keyup" event handler instance
	 */
	public void setKeyUpHandler(DialogKeyUpHandler keyUpHandler) {
		this.keyUpHandler = keyUpHandler;
		// checks if handler is consistent
		if (keyUpHandler != null) {
			// if yes, sets callback proxy
			setValue(Property.ON_KEY_UP, dialogKeyUpFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_KEY_UP);
		}
	}

	/**
	 * Returns the "input" event handler instance.
	 * 
	 * @return the "input" event handler instance
	 */
	public DialogInputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * Sets the "input" event handler instance.
	 * 
	 * @param inputHandler the "input" event handler instance
	 */
	public void setInputHandler(DialogInputHandler inputHandler) {
		this.inputHandler = inputHandler;
		// checks if handler is consistent
		if (inputHandler != null) {
			// if yes, sets callback proxy
			setValue(Property.ON_INPUT, dialogInputFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_INPUT);
		}
	}

	/**
	 * Returns the dialog close handler instance.
	 * 
	 * @return the dialog close handler instance
	 */
	public DialogCloseHandler getCloseHandler() {
		return closeHandler;
	}

	/**
	 * Sets the dialog close handler instance.
	 * 
	 * @param closeHandler the dialog close handler instance
	 */
	public void setCloseHandler(DialogCloseHandler closeHandler) {
		this.closeHandler = closeHandler;
		// checks if handler is consistent
		if (closeHandler != null) {
			// if yes, sets callback proxy
			setValue(Property.ON_CLOSE, dialogCloseFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_CLOSE);
		}

	}

	/**
	 * Is called whenever keydown fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @param event keydown event instance
	 * @param value dialog value
	 * @return if the callback returns true, the dialog will not do any further processing of the event
	 */
	private boolean onKeyDown(NativeEvent event, String value) {
		// gets the dialog instance y the custom element attribute set by dialog
		// before invoking
		Dialog dialog = getDialogFromNativeEvent(event);
		// checks if handler and dialog are consistent
		if (keyDownHandler != null && dialog != null) {
			return keyDownHandler.onKeyDown(dialog, event, value);
		}
		// if here, handler or dialog are null
		// then returns default
		return false;
	}

	/**
	 * Is called whenever keyup fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @param event keyup event instance
	 * @param value dialog value
	 * @return if the callback returns true, the dialog will not do any further processing of the event
	 */
	private boolean onKeyUp(NativeEvent event, String value) {
		// gets the dialog instance y the custom element attribute set by dialog
		// before invoking
		Dialog dialog = getDialogFromNativeEvent(event);
		// checks if handler and dialog are consistent
		if (keyUpHandler != null && dialog != null) {
			return keyUpHandler.onKeyUp(dialog, event, value);
		}
		// if here, handler or dialog are null
		// then returns default
		return false;
	}

	/**
	 * Is called whenever input event fires in the dialog's input.<br>
	 * If the callback returns true, the dialog will not do any further processing of the event.
	 * 
	 * @param event input event instance
	 * @param value dialog value
	 * @return if the callback returns true, the dialog will not do any further processing of the event
	 */
	private boolean onInput(NativeEvent event, String value) {
		// gets the dialog instance y the custom element attribute set by dialog
		// before invoking
		Dialog dialog = getDialogFromNativeEvent(event);
		// checks if handler and dialog are consistent
		if (inputHandler != null && dialog != null) {
			return inputHandler.onInput(dialog, event, value);
		}
		// if here, handler or dialog are null
		// then returns default
		return false;
	}

	/**
	 * Is called after the dialog has been closed and removed from the DOM.
	 * 
	 * @param element dialog DOM element
	 */
	private void onClose(Element element) {
		// CodeMirror adds a DIV element as wrapper to user element
		// therefore to get text input element
		// searches by tag name
		NodeList<Element> inputElements = element.getElementsByTagName(InputElement.TAG);
		// scans all found text input elements
		for (int i = 0; i < inputElements.getLength(); i++) {
			// gets element
			Element inputElement = inputElements.getItem(i);
			// checks if has got the editor id
			if (inputElement.hasAttribute(Id.CODERBA_ID_AS_STRING)) {
				// gets editor id
				String editorId = element.getAttribute(Id.CODERBA_ID_AS_STRING);
				// gets editor area by id
				EditorArea area = EditorAreas.get(editorId);
				// checks if area is consistent
				// if not, means the editor id is not correct
				if (area != null) {
					// gets dialog instance from editor
					Dialog dialog = area.getEditor().getDialog();
					// checks if handler and dialog are consistent
					if (closeHandler != null && dialog != null) {
						closeHandler.onClose(dialog, element);
					}
				}
			}
		}
	}

	/**
	 * Gets the dialog instance by the event target of emitted native event.<br>
	 * The event target is the element that was the actual target of the given event.
	 *  
	 * @param eventnative event instance
	 * @return the dialog instance or <code>null</code> if not found
	 */
	private Dialog getDialogFromNativeEvent(NativeEvent event) {
		// gets the element that was the actual target of the given event
		EventTarget target = event.getEventTarget();
		// checks if is a DOM element
		if (Element.is(target)) {
			// gets as element
			Element element = Element.as(target);
			// checks if has got the editor id
			if (element.hasAttribute(Id.CODERBA_ID_AS_STRING)) {
				// gets editor id
				String editorId = element.getAttribute(Id.CODERBA_ID_AS_STRING);
				// gets editor area by id
				EditorArea area = EditorAreas.get(editorId);
				// checks if area is consistent
				// if not, means the editor id is not correct
				if (area != null) {
					// returns the dialog 
					return area.getEditor().getDialog();
				}
			}
		}
		// if here, the event target is not an element
		// or the editor id is not stored or not consistent
		return null;
	}

}
