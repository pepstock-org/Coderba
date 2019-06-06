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
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsExtendedOptions extends IsOptions {

	/**
	 * Sets a function used to format line numbers. The function is passed the line number, and should return a string that will
	 * be shown in the gutter.
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
	 * Allows you to configure the behavior of mouse selection and dragging.<br>
	 * The function is called when the left mouse button is pressed.<br>
	 * The returned object may have the following properties:<br>
	 * <ul>
	 * <li>unit: "char" | "word" | "line" | "rectangle" | fn(CodeMirror, Pos) --> {from: Pos, to: Pos}.<br>
	 * The unit by which to select. May be one of the built-in units or a function that takes a position and returns a range
	 * around that, for a custom unit. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle"
	 * for alt-clicks (or, on Chrome OS, meta-shift-clicks), and "single" otherwise.
	 * <li>extend: bool. Whether to extend the existing selection range or start a new one. By default, this is enabled when
	 * shift clicking.
	 * <li>addNew: bool. When enabled, this adds a new range to the existing selection, rather than replacing it.<br>
	 * The default behavior is to enable this for command-click on Mac OS, and control-click on other platforms.
	 * <li>moveOnDrag: bool. When the mouse even drags content around inside the editor, this controls whether it is copied
	 * (false) or moved (true). By default, this is enabled by alt-clicking on Mac OS, and ctrl-clicking elsewhere.
	 * </ul>
	 * 
	 * @param configureMouse a callback that allows you to configure the behavior of mouse selection and dragging.
	 */
	void setConfigureMouse(CallbackProxy.Proxy configureMouse);

}
