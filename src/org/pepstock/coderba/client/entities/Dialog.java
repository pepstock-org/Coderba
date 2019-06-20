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

import org.pepstock.coderba.client.addons.AddOnDialog;
import org.pepstock.coderba.client.callbacks.DialogHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;

import jsinterop.annotations.JsFunction;

/**
 * Related to {@link AddOnDialog}, provides a very simple way to query users for text input, at the top of the editor.<br>
 * It also provides the capability to add HTML fragment as a notification at the top of the editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Dialog {

	// -----------------------------------------
	// ADDITIONAL CALLBACK from ADDON
	// -----------------------------------------
	/**
	 * Java script FUNCTION that is called when an ENTER has been pressed from dialog form and when the dialog is closed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DialogFunction {

		/**
		 * Is called when an ENTER has been pressed from dialog form and when the dialog is closed.
		 */
		void call();
	}

	// callback proxy to invoke the dialog function when ENTER is pressed
	private final CallbackProxy<DialogFunction> openDialogFunctionProxy = JsHelper.get().newCallbackProxy();
	// editor instance
	private final Editor editor;
	// dialog handler ONLY for DIALOG addon
	private DialogHandler dialogHandler;
	// callback to hide the dialog
	private DialogFunction hideDialogCallback = null;
	// callback to hide the notification
	private DialogFunction hideNotificationCallback = null;

	/**
	 * Creates a dialog object with own editor instance.
	 * 
	 * @param editor editor instance which this dialog belongs to
	 */
	Dialog(Editor editor) {
		// forces inject if addon
		AddOnDialog.INSTANCE.inject();
		this.editor = editor;
		// ---------------------------------------
		// -- SET CALLBACKS to PROXIES from ADDONS
		// ---------------------------------------
		openDialogFunctionProxy.setCallback(this::onDialogEnter);
	}

	/**
	 * Returns the editor instance which this dialog belongs to.
	 * 
	 * @return the editor instance which this dialog belongs to
	 */
	public Editor getEditor() {
		return editor;
	}

	/**
	 * Open a dialog on top of the editor, using the HTML element and the dialog options passed as arguments.
	 * 
	 * @param dialogHandler handler instance invoked when ENTER is pressed into text field.
	 * @param element can be called with an HTML fragment or a detached DOM node that provides the prompt (should include an
	 *            input or button tag).
	 * @param options options to configure the dialog
	 */
	void open(DialogHandler dialogHandler, Element element, DialogOptions options) {
		// checks if handler is consistent
		if (dialogHandler != null) {
			// stores handler
			this.dialogHandler = dialogHandler;
			// object with options
			NativeObject nativeOptions = null;
			// checks if options are consistent
			if (options != null) {
				nativeOptions = options.getObject();
			}
			// if element is not consistent
			if (element == null) {
				// creates a text input element
				Element newElement = Document.get().createTextInputElement();
				// checks and apply
				checkAndApplyId(newElement);
				// creates a input element
				// invokes the open editor and stores teh callback for closing
				hideDialogCallback = editor.getNativeObject().openDialog(newElement, openDialogFunctionProxy.getProxy(), nativeOptions);
			} else {
				checkAndApplyId(element);
				// invokes the open dialog by the editor and stores teh callback for closing
				hideDialogCallback = editor.getNativeObject().openDialog(element, openDialogFunctionProxy.getProxy(), nativeOptions);
			}
		}
	}

	/**
	 * Shows an HTML fragment as a notification at the top of the editor.
	 * 
	 * @param element an HTML fragment as a notification at the top of the editor.
	 * @param options options to configure the notification, it takes a single option: duration, the amount of time after which
	 *            the notification will be automatically closed. If duration is zero, the dialog will not be closed
	 *            automatically.
	 */
	void notify(Element element, DialogOptions options) {
		// if element is not consistent
		if (element != null) {
			// object with options
			NativeObject nativeOptions = null;
			// checks if options are consistent
			if (options != null) {
				nativeOptions = options.getObject();
			}
			// invokes the open notification by the editor and stores the callback for closing
			hideNotificationCallback = editor.getNativeObject().openNotification(element, nativeOptions);
		}
	}

	/**
	 * If called, will close the dialog immediately
	 */
	public void hide() {
		// checks if callback is consistent
		if (hideDialogCallback != null) {
			// force closure
			hideDialogCallback.call();
		}
	}

	/**
	 * If called, will close the notification immediately
	 */
	public void hideNotification() {
		// checks if callback is consistent
		if (hideNotificationCallback != null) {
			// force closure
			hideNotificationCallback.call();
		}
	}

	/**
	 * Fires when the dialog receives a ENTER key event.
	 */
	private void onDialogEnter() {
		// checks if area is consistent
		if (dialogHandler != null) {
			// invokes handler
			dialogHandler.onEnter(this);
		}
	}

	/**
	 * Checks the HTML fragment passed to open the dialog in order to set into text input element the editor id in order that
	 * options can retrieve the editor instance related to callbacks.
	 * 
	 * @param element HTML fragment passed to open the dialog
	 */
	private void checkAndApplyId(Element element) {
		// checks if the root element is itself a text input element
		if (InputElement.TAG.equalsIgnoreCase(element.getTagName())) {
			// sets editor id as attribute of element
			element.setAttribute(Id.CODERBA_ID_AS_STRING, editor.getId());
		} else {
			// gets all text input element from root DOM element
			// passed as argument
			NodeList<Element> inputElements = element.getElementsByTagName(InputElement.TAG);
			// scans all text input elements
			for (int i = 0; i < inputElements.getLength(); i++) {
				// gets element
				Element inputElement = inputElements.getItem(i);
				// sets editor id as attribute of element
				inputElement.setAttribute(Id.CODERBA_ID_AS_STRING, editor.getId());
			}
		}
	}

}
