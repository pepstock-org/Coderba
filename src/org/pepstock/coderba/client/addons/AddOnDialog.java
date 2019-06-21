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
package org.pepstock.coderba.client.addons;

import org.pepstock.coderba.client.AddOn;
import org.pepstock.coderba.client.callbacks.DialogCloseHandler;
import org.pepstock.coderba.client.callbacks.DialogHandler;
import org.pepstock.coderba.client.callbacks.DialogInputHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyDownHandler;
import org.pepstock.coderba.client.callbacks.DialogKeyUpHandler;
import org.pepstock.coderba.client.entities.Dialog;
import org.pepstock.coderba.client.entities.DialogOptions;
import org.pepstock.coderba.client.entities.Editor;
import org.pepstock.coderba.client.resources.AddOnResources;
import org.pepstock.coderba.client.resources.ScriptResource;
import org.pepstock.coderba.client.resources.StyleResource;

/**
 * Is the "dialog" addon to enable this capability into editor.<br>
 * Provides a very simple way to query users for text input.
 * 
 * @author Andrea "Stock" Stocchero
 * @see Dialog
 * @see DialogOptions
 * @see DialogHandler
 * @see DialogInputHandler
 * @see DialogKeyUpHandler
 * @see DialogKeyDownHandler
 * @see DialogCloseHandler
 * @see Editor#openDialog(org.pepstock.coderba.client.callbacks.DialogHandler, com.google.gwt.dom.client.Element, DialogOptions)
 * @see Editor#openNotification(com.google.gwt.dom.client.Element, DialogOptions)
 */
public final class AddOnDialog extends AddOn {
	// name of editor addon
	private static final String NAME = "dialog";

	/**
	 * Instance of editor addon.
	 */
	public static final AddOnDialog INSTANCE = new AddOnDialog();

	/**
	 * To avoid any instantiation
	 */
	private AddOnDialog() {
		// passes the name of addon
		super(NAME);
		getResources().add(new ScriptResource(AddOnResources.INSTANCE.dialog()));
		getResources().add(new StyleResource(AddOnResources.INSTANCE.dialogCss()));
	}

}
