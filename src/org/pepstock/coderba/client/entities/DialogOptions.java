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

import java.util.Locale;

import org.pepstock.coderba.client.addons.AddOnDialog;
import org.pepstock.coderba.client.callbacks.DialogCloseHandler;
import org.pepstock.coderba.client.callbacks.DialogInputHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyDownHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyUpHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.utils.Window;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;

import jsinterop.annotations.JsFunction;

/**
 * The options to configure a dialog.<br>
 * A dialog, related to {@link AddOnDialog}, provides a very simple way to query users for text input, at the top of the editor.
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
		 * @param instance dialog element
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
	
	private DialogKeyDownHandler keyDownHandler = null;
	
	private DialogKeyUpHandler keyUpHandler = null;
	
	private DialogInputHandler inputHandler = null;
	
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
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CLOSE_ON_ENTER("closeOnEnter"),
		CLOSE_ON_BLUR("closeOnBlur"),
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
	 * FIXME
	 * @return the keyDownHandler
	 */
	public DialogKeyDownHandler getKeyDownHandler() {
		return keyDownHandler;
	}

	/**
	 * @param keyDownHandler the keyDownHandler to set
	 */
	public void setKeyDownHandler(DialogKeyDownHandler keyDownHandler) {
		this.keyDownHandler = keyDownHandler;
		if (keyDownHandler != null) {
			setValue(Property.ON_KEY_DOWN, dialogKeyDownFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_KEY_DOWN);
		}
	}

	/**
	 * @return the keyUpHandler
	 */
	public DialogKeyUpHandler getKeyUpHandler() {
		return keyUpHandler;
	}

	/**
	 * @param keyUpHandler the keyUpHandler to set
	 */
	public void setKeyUpHandler(DialogKeyUpHandler keyUpHandler) {
		this.keyUpHandler = keyUpHandler;
		if (keyUpHandler != null) {
			setValue(Property.ON_KEY_UP, dialogKeyUpFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_KEY_UP);
		}
	}

	/**
	 * @return the inputHandler
	 */
	public DialogInputHandler getInputHandler() {
		return inputHandler;
	}

	/**
	 * @param inputHandler the inputHandler to set
	 */
	public void setInputHandler(DialogInputHandler inputHandler) {
		this.inputHandler = inputHandler;
		if (inputHandler != null) {
			setValue(Property.ON_INPUT, dialogInputFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_INPUT);
		}
	}

	/**
	 * @return the closeHandler
	 */
	public DialogCloseHandler getCloseHandler() {
		return closeHandler;
	}

	/**
	 * @param closeHandler the closeHandler to set
	 */
	public void setCloseHandler(DialogCloseHandler closeHandler) {
		this.closeHandler = closeHandler;
		if (closeHandler != null) {
			setValue(Property.ON_CLOSE, dialogCloseFunctionProxy.getProxy());
		} else {
			// removes property if the property exists
			removeIfExists(Property.ON_CLOSE);
		}

	}

	private boolean onKeyDown(NativeEvent event, String value) {
		if (keyDownHandler != null) {
			return keyDownHandler.onKeyDown(event, value);
		}
		return false;
	}

	private boolean onKeyUp(NativeEvent event, String value) {
		if (keyUpHandler != null) {
			return keyUpHandler.onKeyUp(event, value);
		}
		return false;
	}

	private boolean onInput(NativeEvent event, String value) {
		EventTarget target = event.getEventTarget();
		Window.getConsole().log(JsHelper.get().elementAttribute(target, Id.CODERBA_ID_AS_STRING.toLowerCase(Locale.getDefault())));
		if (inputHandler != null) {
			return inputHandler.onInput(event, value);
		}
		return false;
	}

	private void onClose(Element element) {
		if (closeHandler != null) {
			closeHandler.onClose(element);
		}
	}

}
