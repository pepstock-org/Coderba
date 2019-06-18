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

import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * CodeMirror is a code-editor component that can be embedded in Web pages.<br>
 * The core library provides only the editor component and this class provides this component, with all static methods to
 * consume by other classes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.CODE_MIRROR)
final class NativeCodeMirror {

	/**
	 * to avoid any instantiation
	 */
	private NativeCodeMirror() {
		// do nothing
	}

	// -----------------------------------
	// --- STATIC methods
	// -----------------------------------

	/**
	 * It contains a string that indicates the version of the library.<br>
	 * This is a triple of integers "major.minor.patch", where patch is zero for releases, and something else (usually one) for
	 * development snapshots.
	 * 
	 * @return a string that indicates the version of the library.
	 */
	@JsProperty(name = "version")
	static native String getVersion();

	/**
	 * Returns the object containing the default options.<br>
	 * You can update this object to change the defaults on your editor.
	 * 
	 * @return the object containing the default options.
	 */
	@JsProperty(name = "defaults")
	static native NativeObject getDefaults();

	/**
	 * Returns the object containing the loaded key maps.
	 * 
	 * @return the object containing the loaded key maps
	 */
	@JsProperty(name = "keyMap")
	static native NativeObject getKeyMaps();

	/**
	 * Returns the map of CodeMirror, which maps MIME types to mode specification.
	 * 
	 * @return the map of CodeMirror, which maps MIME types to mode specification
	 */
	@JsProperty(name = "mimeModes")
	static native NativeObject getMimeModes();

	/**
	 * Returns the object containing the defined commands.
	 * 
	 * @return the object containing the defined commands
	 */
	@JsProperty(name = "commands")
	static native NativeObject getCommands();

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a text area DOM node as first argument and an optional configuration object as second.<br>
	 * It will replace the text area with a CodeMirror instance, and wire up the form of that text area (if any) to make sure
	 * the editor contents are put into the text area when the form is submitted.<br>
	 * The text in the text area will provide the content for the editor.
	 * 
	 * @param element a text area DOM node, already attached to body
	 * @param options user configuration object instance
	 * @return an initialized editor
	 */
	static native NativeEditor fromTextArea(Element element, NativeObject options);

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a text area DOM node as argument.<br>
	 * It will replace the text area with a CodeMirror instance, and wire up the form of that text area (if any) to make sure
	 * the editor contents are put into the text area when the form is submitted.<br>
	 * The text in the text area will provide the content for the editor.
	 * 
	 * @param element a text area DOM node, already attached to body
	 * @return an initialized editor with default configuration
	 */
	static native NativeEditor fromTextArea(Element element);

	/**
	 * When a map contains multi-stoke bindings or keys with modifiers that are not specified in the default order
	 * (Shift-Cmd-Ctrl-Alt), you must call CodeMirror.normalizeKeyMap on it before it can be used.<br>
	 * This function takes a keymap and modifies it to normalize modifier order and properly recognize multi-stroke
	 * bindings.<br>
	 * 
	 * @param map a key map to normalize
	 * @return It will return the keymap itself
	 */
	static native NativeObject normalizeKeyMap(NativeObject map);

}