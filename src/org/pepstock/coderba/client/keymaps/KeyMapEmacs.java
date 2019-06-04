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
package org.pepstock.coderba.client.keymaps;

import org.pepstock.coderba.client.KeyMap;
import org.pepstock.coderba.client.resources.KeyMapResources;
import org.pepstock.coderba.client.resources.ScriptResource;

/**
 * Is the "emacs" key map to enable the editor to manage keyboard.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMapEmacs extends KeyMap {
	// name of editor keymap
	private static final String NAME = "emacs";

	/**
	 * Instance of editor keymap.
	 */
	public static final KeyMapEmacs INSTANCE = new KeyMapEmacs();

	/**
	 * To avoid any instantiation
	 */
	private KeyMapEmacs() {
		// passes the name of keymap
		super(NAME);
		getResources().add(new ScriptResource(KeyMapResources.INSTANCE.emacs()));
	}
}
