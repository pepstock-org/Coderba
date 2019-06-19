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

import org.pepstock.coderba.client.commons.CallbackProxy;

/**
 * Interface which maps the options of CodeMirror where you can add native callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsExtendedOptions extends IsOptions {

	/**
	 * Returns <code>true</code> if no property has been set as option.
	 * 
	 * @return <code>true</code> if no property has been set as option.
	 */
	boolean isEmpty();

	/**
	 * Sets a function used to format line numbers.<br>
	 * The function is passed the line number, and should return a string that will be shown in the gutter.
	 * 
	 * @param lineNumberFormatter line number formatter instance
	 */
	void setLineNumberFormatter(CallbackProxy.Proxy lineNumberFormatter);

	/**
	 * Sets a callback that, given a special character identified by the specialChars option, produces a DOM node that is used
	 * to represent the character.
	 * 
	 * @param specialCharPlaceholder a callback that, given a special character identified by the specialChars option, produces
	 *            a DOM node that is used to represent the character.
	 */
	void setSpecialCharPlaceholder(CallbackProxy.Proxy specialCharPlaceholder);

	/**
	 * Allows you to configure the behavior of mouse selection and dragging.
	 * 
	 * @see MouseConfiguration
	 * @param configureMouse a callback that allows you to configure the behavior of mouse selection and dragging.
	 */
	void setConfigureMouse(CallbackProxy.Proxy configureMouse);

}
