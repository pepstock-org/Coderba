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
import org.pepstock.coderba.client.addons.AddOnDialog;
import org.pepstock.coderba.client.callbacks.DialogHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;

import jsinterop.annotations.JsFunction;

/**
 * Related to {@link AddOnDialog}, provides a very simple way to query users for text input, at the top of the editor.
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
	// editor instancef
	private final Editor editor;
	// dialog handler ONLY for DIALOG addon
	private final DialogHandler dialogHandler;
	// callback to hide the dialog
	private DialogFunction hideCallback = null;
	// flag to know if dialog is already open
	private boolean isOpen = false;

	/**
	 * Creates a dialog object with own editor instance and the dialog handler.
	 * 
	 * @param editor editor instance which this dialog belongs to
	 * @param dialogHandler handler instance invoked when ENTER is pressed into text field.
	 */
	Dialog(Editor editor, DialogHandler dialogHandler) {
		// forces inject if addon
		AddOnDialog.INSTANCE.inject();
		this.editor = editor;
		this.dialogHandler = dialogHandler;
		// ---------------------------------------
		// -- SET CALLBACKS to PROXIES from ADDONS
		// ---------------------------------------
		openDialogFunctionProxy.setCallback(this::onDialogEnter);
	}

	/**
	 * @return the editor
	 */
	public Editor getEditor() {
		return editor;
	}

	/**
	 * Returns the handler instance invoked when ENTER is pressed into text field.
	 * 
	 * @return the handler instance invoked when ENTER is pressed into text field
	 */
	public DialogHandler getDialogHandler() {
		return dialogHandler;
	}
	
	/**
	 * Open a dialog on top of the editor, using new {@link InputElement}, without any options.
	 */
	public void open() {
		open(Document.get().createTextInputElement());
	}
	
	/**
	 * Open a dialog on top of the editor, using the HTML element, without any options.
	 * 
	 * @param element can be called with an HTML fragment or a detached DOM node that provides the prompt (should include an
	 *            input or button tag).
	 */
	public void open(Element element) {
		open(element, null);
	}
	
	/**
	 * Open a dialog on top of the editor, using new {@link InputElement} and the dialog options passed as argument.
	 * 
	 * @param options options to configure the dialog
	 */
	public void open(DialogOptions options) {
		open(Document.get().createTextInputElement(), options);
	}

	/**
	 * Open a dialog on top of the editor, using the HTML element and the dialog options passed as arguments.
	 * 
	 * @param element can be called with an HTML fragment or a detached DOM node that provides the prompt (should include an
	 *            input or button tag).
	 * @param options options to configure the dialog
	 */
	public void open(Element element, DialogOptions options) {
		// checks if dialog is open
		if (!isOpen) {
			// object with options
			NativeObject nativeOptions = null;
			// checks if options are consistent
			if (options != null) {
				nativeOptions = options.getObject();
			} 
			// if element is not consistent
			if (element == null) {
				Element newElement = Document.get().createTextInputElement();
				newElement.setAttribute(Id.CODERBA_ID_AS_STRING, editor.getid());
				// creates a input element
				// invokes the open editor and stores teh callback for closing
				hideCallback = editor.getNativeObject().openDialog(newElement, openDialogFunctionProxy.getProxy(), nativeOptions);
			} else {
				element.setAttribute(Id.CODERBA_ID_AS_STRING, editor.getid());
				// invokes the open editor and stores teh callback for closing
				hideCallback = editor.getNativeObject().openDialog(element, openDialogFunctionProxy.getProxy(), nativeOptions);
			}
			// sets flag as open
			isOpen = true;
		}
	}

	/**
	 * If called, will close the dialog immediately
	 */
	public void hide() {
		// checks if callback is consistent and
		// dialog is open
		if (hideCallback != null && isOpen) {
			// force closure
			hideCallback.call();
		}
		// sets flag as close
		isOpen = false;
	}

	/**
	 * Fires when the dialog receives a ENTER key event.
	 */
	private void onDialogEnter() {
		// gets editor area
		EditorArea area = editor.getNativeObject().getEditorArea();
		// checks if area is consistent
		if (area != null && dialogHandler != null) {
			// invokes handler
			dialogHandler.onEnter(area);
		}
	}

}
