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

import com.google.gwt.dom.client.TextAreaElement;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CodeMirror {
	
	private static final CodeMirror INSTANCE = new CodeMirror();
	
	private final Defaults defaults;
	
	private final MimeModes mimeModes;

	/**
	 * to avoid any instantiation
	 */
	private CodeMirror() {
		Injector.ensureInjected();
		defaults = new Defaults(NativeCodeMirror.getDefaults());
		mimeModes = new MimeModes(NativeCodeMirror.getMimeModes());
	}
	
	public static CodeMirror get() {
		return INSTANCE;
	}
	
	/**
	 * It contains a string that indicates the version of the library. This is a triple of integers "major.minor.patch", where
	 * patch is zero for releases, and something else (usually one) for dev snapshots.
	 * 
	 * @return It contains a string that indicates the version of the library. This is a triple of integers "major.minor.patch",
	 *         where patch is zero for releases, and something else (usually one) for dev snapshots.
	 */
	public String getVersion() {
		return NativeCodeMirror.getVersion();
	}
	
	/**
	 * Returns the object containing the default options. You can update this object to change the defaults on your page.
	 * 
	 * @return the object containing the default options.
	 */
	public Defaults getDefaults() {
		return defaults;
	}
	
	MimeModes getMimeModes() {
		return mimeModes;
	}
	
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
	Editor fromTextArea(TextAreaElement host) {
		return fromTextArea(host, null);
	}

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
	Editor fromTextArea(TextAreaElement host, UserOptions configuration) {
		if (host != null) {
			if (configuration != null) {
				return new Editor(NativeCodeMirror.fromTextArea(host, configuration.getObject()));
			} else {
				return new Editor(NativeCodeMirror.fromTextArea(host));
			}
		}
		throw new IllegalArgumentException("TextArea element is null");
	}

	/**
	 * Find the column position at a given string index using a given tabsize.
	 * 
	 * @param line
	 * @param index
	 * @param tabSize
	 * @return
	 */
	public int countColumn(String line, int index, int tabSize) {
		if (line != null) {
			return NativeCodeMirror.countColumn(line, index, tabSize);
		}
		return -1;
	}

}
