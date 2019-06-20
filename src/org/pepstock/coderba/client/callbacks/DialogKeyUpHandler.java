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
package org.pepstock.coderba.client.callbacks;

import org.pepstock.coderba.client.addons.AddOnDialog;
import org.pepstock.coderba.client.entities.Dialog;

import com.google.gwt.dom.client.NativeEvent;

/**
 * Related to {@link AddOnDialog}, defines the dialog callback that will be called whenever "keyup" event fires in the dialog's
 * input.<br>
 * If your callback returns <code>true</code>, the dialog will not do any further processing of the event.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface DialogKeyUpHandler {

	/**
	 * It will be called whenever "keyup" event fires in the dialog's input.<br>
	 * If your callback returns <code>true</code>, the dialog will not do any further processing of the event.
	 * 
	 * @param dialog dialog instance
	 * @param event "keyup" native event instance
	 * @param value value of text input element
	 * @return <code>true</code>, the dialog will not do any further processing of the event
	 */
	boolean onKeyUp(Dialog dialog, NativeEvent event, String value);

}
