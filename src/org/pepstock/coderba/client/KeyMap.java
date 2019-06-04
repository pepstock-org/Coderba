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
package org.pepstock.coderba.client;

/**
 * Key maps are ways to associate keys and mouse buttons with functionality.<br>
 * A key map is an object mapping strings that identify the buttons to functions that implement their functionality.<br>
 * The CodeMirror distributions comes with Emacs, Vim, and Sublime Text-style keymaps.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class KeyMap extends InjectableItem {

	/**
	 * Creates a keymap by its name.
	 * 
	 * @param name name of keymap
	 */
	protected KeyMap(String name) {
		super(name);
	}

	/**
	 * Injects the keymap instance into DOM document in order to consume it by CodeMirror.
	 */
	public final void inject() {
		Injector.ensureInjected(this);
	}

}
