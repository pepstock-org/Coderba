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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.EditorAreaId;
import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

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
	// native object of KEYMAPS
	private final NativeObject keyMaps;
	// native object of COMMANDS
	private final NativeObject commands;
	// MIME modes object
	private final MimeModes mimeModes;
	// stores CodeMirror Version
	private final String version;

	/**
	 * To avoid any instantiation
	 */
	private CodeMirror() {
		// injects CodeMirror if needed
		Injector.ensureInjected();
		// gets default object
		defaults = NativeCodeMirror.getDefaults();
		// gets key maps object
		keyMaps = NativeCodeMirror.getKeyMaps();
		// gets commands object
		commands = NativeCodeMirror.getCommands();
		// gets mime modes object
		mimeModes = new MimeModes(NativeCodeMirror.getMimeModes());
		// stores the version
		version = NativeCodeMirror.getVersion();
	}

	/**
	 * Returns the singleton instance of the CodeMirror.
	 * 
	 * @return the singleton instance of the CodeMirror
	 */
	public static CodeMirror get() {
		return INSTANCE;
	}

	/**
	 * It contains a string that indicates the version of the library.<br>
	 * This is a triple of integers "major.minor.patch", where patch is zero for releases, and something else (usually one) for
	 * development snapshots.
	 * 
	 * @return It contains a string that indicates the version of the library.
	 */
	public String getVersion() {
		return version;
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
	 * Returns the object containing the loaded key maps.
	 * 
	 * @return the object containing the loaded key maps
	 */
	NativeObject getKeyMaps() {
		return keyMaps;
	}

	/**
	 * Returns the object containing the defined commands.
	 * 
	 * @return the object containing the defined commands
	 */
	NativeObject getCommands() {
		return commands;
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
	 * Returns the mode specification related to the language (by mime of language).
	 * 
	 * @param language language to use to get the mode specification
	 * @return the mode specification related to the language or <code>null</code> if not exists.
	 */
	public ModeSpecification getModeSpecification(Language language) {
		return getMimeModes().getMode(language);
	}

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a editor area and its id as arguments.
	 * 
	 * @param id editor area id instance
	 * @param editorArea editor area id instance
	 * @return an initialized editor with editor area configuration
	 */
	public Editor fromTextArea(EditorAreaId id, EditorArea editorArea) {
		// checks if id is consistent
		if (id == null) {
			// if not exception
			throw new IllegalArgumentException("Editor area id is null");
		}
		// checks if editor area is consistent
		if (editorArea == null) {
			// if not exception
			throw new IllegalArgumentException("Editor area is null");
		}
		// gets the text area element by its id
		Element textArea = Document.get().getElementById(id.getId());
		// checks if editor area is consistent
		if (textArea == null) {
			// if not exception
			throw new IllegalArgumentException("Text area element "+id.getId()+" not found");
		}
		return fromTextArea(id.getId(), textArea, editorArea.getOptions());
	}

	/**
	 * This method provides another way to initialize an editor.<br>
	 * It takes a text area DOM node as first argument and an optional configuration object as second.<br>
	 * It will replace the text area with a CodeMirror instance, and wire up the form of that text area (if any) to make sure
	 * the editor contents are put into the text area when the form is submitted.<br>
	 * The text in the text area will provide the content for the editor.
	 * 
	 * @param id editor area id value
	 * @param element a text area DOM node, already attached to body
	 * @param options user configuration object instance
	 * @return an initialized editor
	 */
	private Editor fromTextArea(String id, Element element, EditorOptions configuration) {
		// checks if text area element is consistent
		if (element != null) {
			// instance to create
			Editor editor;
			// checks if a configuration object has been changed
			if (configuration.isEmpty()) {
				// if no, initialized the editor by default configuration
				editor = new Editor(NativeCodeMirror.fromTextArea(element), configuration.getLanguage());
			} else {
				// if yes, initialized the editor by configuration
				editor = new Editor(NativeCodeMirror.fromTextArea(element, configuration.getObject()), configuration.getLanguage());
			}
			// sets the unique id to editor
			editor.setId(id);
			// switches the configuration from user to runtime
			configuration.setDelegatedOptions(new RuntimeOptions(editor.getNativeObject(), Defaults.get()));
			// returns editor instance
			return editor;
		}
		// if here, the text area is not consistent
		// then exception
		throw new IllegalArgumentException("Unable to initialize the editor because TextArea element is null");
	}
}
