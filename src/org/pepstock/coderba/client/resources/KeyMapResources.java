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
package org.pepstock.coderba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CodeMirror code editor and other java script codes, always needed to CODERBA.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface KeyMapResources extends ClientBundle {

	// static reference of this resource
	public static final KeyMapResources INSTANCE = GWT.create(KeyMapResources.class);

	/**
 * Returns the java script source code for Emacs.
 * @return <code>emacs.js</code> code for Emacs
 */
 @Source("/js/keymap/emacs.js")
 TextResource emacs();

/**
 * Returns the java script source code for Sublime.
 * @return <code>sublime.js</code> code for Sublime
 */
 @Source("/js/keymap/sublime.js")
 TextResource sublime();

/**
 * Returns the java script source code for Vim.
 * @return <code>vim.js</code> code for Vim
 */
 @Source("/js/keymap/vim.js")
 TextResource vim();



}
