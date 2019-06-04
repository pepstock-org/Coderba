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

import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.TextAreaElement;

/**
 * CodeMirror is a code-editor component that can be embedded in Web pages.<br>
 * The core library provides only the editor component and this class provides this component, with all static methods to
 * consume by other classes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CodeMirror {

	// singleton instance
	private static final CodeMirror INSTANCE = new CodeMirror();
	// native object of DEFAULT
	private final NativeObject defaults;
	// MIME modes object
	private final MimeModes mimeModes;

	/**
	 * To avoid any instantiation
	 */
	private CodeMirror() {
		// injects CodeMirror if needed
		Injector.ensureInjected();
		// gets default object
		defaults = NativeCodeMirror.getDefaults();
		// gets mime modes object
		mimeModes = new MimeModes(NativeCodeMirror.getMimeModes());
	}

	/**
	 * Returns the singleton instance of the code mirror.
	 * 
	 * @return the singleton instance of the code mirror
	 */
	static CodeMirror get() {
		return INSTANCE;
	}

	/**
	 * It contains a string that indicates the version of the library.<br>
	 * This is a triple of integers "major.minor.patch", where patch is zero for releases, and something else (usually one) for
	 * development snapshots.
	 * 
	 * @return It contains a string that indicates the version of the library.
	 */
	public static String getVersion() {
		return NativeCodeMirror.getVersion();
	}

	/**
	 * Returns the object containing the default options.<br>
	 * You can update this object to change the defaults on your editor.
	 * 
	 * @return the object containing the default options.
	 */
	NativeObject getDefaults() {
		return defaults;
	}

	/**
	 * Returns the map of CodeMirror, which maps MIME types to mode specification.
	 * 
	 * @return the map of CodeMirror, which maps MIME types to mode specification
	 */
	MimeModes getMimeModes() {
		return mimeModes;
	}

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
	Editor fromTextArea(TextAreaElement element) {
		return fromTextArea(element, null);
	}

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
	Editor fromTextArea(TextAreaElement element, UserOptions configuration) {
		// checks if text area element is consistent
		if (element != null) {
			// checks if a configuration object is passed
			if (configuration != null) {
				// if yes, initialized the editor by configuration
				return new Editor(NativeCodeMirror.fromTextArea(element, configuration.getObject()));
			} else {
				// if no, initialized the editor by default configuration
				return new Editor(NativeCodeMirror.fromTextArea(element));
			}
		}
		// if here, the text area is not consistent
		// then exception
		throw new IllegalArgumentException("Unable to initialize the editor because TextArea element is null");
	}
}
