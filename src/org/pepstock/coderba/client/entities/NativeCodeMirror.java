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
	 * FIXME
	 * Returns the object containing the default options.<br>
	 * You can update this object to change the defaults on your editor.
	 * 
	 * @return the object containing the default options.
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

	/**
	 * FIXME Can be used to define new options for CodeMirror. The updateFunc will be called with the editor instance and the
	 * new value when an editor is initialized, and whenever the option is modified through <code>setOption</code>.
	 * 
	 * @param name
	 * @param defaults
	 * @param updateFunc
	 */
	static native void defineOption(String name, Object defaults, CallbackProxy.Proxy updateFunc);

	/**
	 * FIXME If you want to define extra methods in terms of the CodeMirror API.<br>
	 * This will cause the given value (usually a method) to be added to all CodeMirror instances created from then on.
	 * 
	 * @param name
	 * @param value
	 */
	static native void defineExtension(String name, Object value);

	/**
	 * FIXME Like defineExtension, but the method will be added to the interface for Doc objects instead
	 * 
	 * @param name
	 * @param value
	 */
	static native void defineDocExtension(String name, Object value);

	/**
	 * FIXME If your extension just needs to run some code whenever a CodeMirror instance is initialized.<br>
	 * Give it a function as its only argument, and from then on, that function will be called (with the instance as argument)
	 * whenever a new CodeMirror instance is initialized.
	 * 
	 * @param function
	 */
	static native void defineInitHook(CallbackProxy.Proxy function);

	/**
	 * FIXME Registers a helper value with the given name in the given namespace (type).<br>
	 * This is used to define functionality that may be looked up by mode.<br>
	 * Will create (if it doesn't already exist) a property on the CodeMirror object for the given type, pointing to an object
	 * that maps names to values. I.e. after doing CodeMirror.registerHelper("hint", "foo", myFoo), the value
	 * CodeMirror.hint.foo will point to myFoo.
	 * 
	 * @param type
	 * @param name
	 * @param value
	 */
	static native void registerHelper(String type, String name, Object value); // value: helper

	/**
	 * FIXME Acts like registerHelper, but also registers this helper as 'global', meaning that it will be included by
	 * getHelpers whenever the given predicate returns true when called with the local mode and editor.
	 * 
	 * @param type
	 * @param name
	 * @param predicate
	 * @param value
	 */
	static native void registerGlobalHelper(String type, String name, CallbackProxy.Proxy predicate, Object value); // predicate:
																													// fn(mode,
																													// CodeMirror),
																													// value:
																													// helper

}