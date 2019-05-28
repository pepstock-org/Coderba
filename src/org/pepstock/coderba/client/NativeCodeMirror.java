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

import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.TextAreaElement;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.CODE_MIRROR)
public final class NativeCodeMirror {

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
	 * It contains a string that indicates the version of the library. This is a triple of integers "major.minor.patch", where
	 * patch is zero for releases, and something else (usually one) for dev snapshots.
	 * 
	 * @return It contains a string that indicates the version of the library. This is a triple of integers "major.minor.patch",
	 *         where patch is zero for releases, and something else (usually one) for dev snapshots.
	 */
	@JsProperty(name = "version")
	static native String getVersion();

	/**
	 * Returns the object containing the default options. You can update this object to change the defaults on your page.
	 * 
	 * @return the object containing the default options.
	 */
	@JsProperty(name = "defaults")
	static native NativeObject getDefaults();
	
	@JsProperty(name = "mimeModes")
	static native NativeObject getMimeModes();

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a textarea DOM node as first argument and an optional configuration object as second.<br>
	 * It will replace the textarea with a CodeMirror instance, and wire up the form of that textarea (if any) to make sure the
	 * editor contents are put into the textarea when the form is submitted.<br>
	 * The text in the textarea will provide the content for the editor.
	 * 
	 * @param host a textarea DOM node, already attached to body
	 * @param options configuration object instance
	 * @return an initialized editor
	 */
	static native NativeEditor fromTextArea(TextAreaElement host, NativeObject options);

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a textarea DOM node as argument.<br>
	 * It will replace the textarea with a CodeMirror instance, and wire up the form of that textarea (if any) to make sure the
	 * editor contents are put into the textarea when the form is submitted.<br>
	 * The text in the textarea will provide the content for the editor.
	 * 
	 * @param host a textarea DOM node, already attached to body
	 * @return an initialized editor
	 */
	static native NativeEditor fromTextArea(TextAreaElement host);

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

	// /** Utility function that computes an end position from a change (an object with from, to, and text properties, as passed
	// to
	// various event handlers).
	// The returned position will be the end of the changed range, after the change is applied. */
	// function changeEnd(change: EditorChange): Position;

	/**
	 * A constructor for the objects that are used to represent the line in editor documents.
	 * 
	 * @param line line into document
	 * @return a position only with the line. Column will be set with the length of the specified line.
	 */
	@JsMethod(name = "Pos")
	static native NativeObject createPosition(int line);

	/**
	 * A constructor for the objects that are used to represent positions in editor documents. sticky defaults to null, but can
	 * be set to "before" or "after" to make the position explicitly associate with the character before or after it.
	 * 
	 * @param line line into document
	 * @param ch column into document
	 * @param sticky before" or "after", whether the position is associated with the character before or after it.
	 * @return a position object
	 */
	@JsMethod(name = "Pos")
	static native NativeObject createPosition(int line, int ch, String sticky);

	/**
	 * FIXME Utility function that computes an end position from a change (an object with from, to, and text properties, as
	 * passed to various event handlers). The returned position will be the end of the changed range, after the change is
	 * applied.
	 * 
	 * @param change
	 * @return
	 */
	static native NativeObject changeEnd(Object change); // {line, ch}

	/**
	 * Find the column position at a given string index using a given tabsize.
	 * 
	 * @param line
	 * @param index
	 * @param tabSize
	 * @return
	 */
	static native int countColumn(String line, int index, int tabSize);

	/**
	 * Compare two positions, return 0 if they are the same, a negative number when a is less, and a positive number otherwise.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static native int cmpPos(NativeObject a, NativeObject b);

}