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

import com.google.gwt.dom.client.Element;

/**
 * Related to {@link AddOnDialog}, defines the dialog callback that will be called after the dialog has been closed and removed
 * from the DOM.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface DialogCloseHandler {

	/**
	 * It will be called after the dialog has been closed and removed from the DOM.
	 * 
	 * @param dialog dialog instance to close
	 * @param element HTML element which represents DOM element of dialog
	 */
	void onClose(Dialog dialog, Element element);

}
