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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.pepstock.coderba.client.entities.CodeMirror;
import org.pepstock.coderba.client.entities.ModeSpecification;
import org.pepstock.coderba.client.resources.InjectableResource;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;

/**
 * This utility injects CodeMirror java script modules and CODERBA custom java script implementation (for some utilities) into
 * the web page of GWT, on demand.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 */
public final class Injector {

	// key separator
	private static final String KEY_CLASS_AND_METHOD_SEPARATOR = "_";
	// Prefix ID of script element
	private static final String CODERBA_PREFIX_SCRIPT_ELEMENT_ID = "_coderba_";
	// contains all script object injected
	private static final Set<String> ELEMENTS_INJECTED = new HashSet<>();

	/**
	 * To avoid any instantiation
	 */
	private Injector() {
		// do nothing
	}

	/**
	 * Injects CodeMirror java script and CSS style sheet files.
	 */
	public static void ensureInjected() {
		// check if argument is consistent
		internalEnsureInjected(CodeMirrorCode.CODE);
	}

	/**
	 * Injects a language object, injecting the mode resources related to the language.
	 * 
	 * @param language language instance
	 */
	public static void ensureInjected(Language language) {
		// check if argument is consistent
		if (language != null) {
			// injects CodeMirror if missing
			ensureInjected();
			// injects mode of language
			ensureInjected(language.getMode());
			// gets the mode specification, loaded in the previous statement
			ModeSpecification modeSpec = CodeMirror.get().getModeSpecification(language);
			// loads the the mode specification into language
			language.setModeSpecification(modeSpec);
			// adds language into cache
			Languages.get().add(language);
		}
	}

	/**
	 * Injects a mode object. This is not visible because the mode must be loaded by a language.
	 * 
	 * @param mode mode instance
	 */
	private static void ensureInjected(Mode mode) {
		// check if argument is consistent
		if (mode != null) {
			// injects mode
			internalEnsureInjected(mode);
			// adds mode into cache
			Modes.get().add(mode);
		}
	}

	/**
	 * Injects an addon object.This is not visible because the addon must be loaded by "inject" method of AddOn class.
	 * 
	 * @param addon addon instance
	 * @see AddOn
	 */
	static void ensureInjected(AddOn addon) {
		// check if argument is consistent
		if (addon != null) {
			// injects CodeMirror if missing
			ensureInjected();
			// injects addon
			internalEnsureInjected(addon);
			// adds addon into cache
			AddOns.get().add(addon);
		}
	}

	/**
	 * Injects a keymap object.This is not visible because the keymap must be loaded by "inject" method of KeyMap class.
	 * 
	 * @param keymap keymap instance
	 * @see KeyMap
	 */
	static void ensureInjected(KeyMap keyMap) {
		// check if argument is consistent
		if (keyMap != null) {
			// injects CodeMirror if missing
			ensureInjected();
			// injects keymap
			internalEnsureInjected(keyMap);
			// adds keymap into cache
			KeyMaps.get().add(keyMap);
		}
	}

	/**
	 * Injects a theme object.
	 * 
	 * @param theme theme instance
	 */
	public static void ensureInjected(Theme theme) {
		// check if argument is consistent
		if (theme != null) {
			// injects CodeMirror if missing
			ensureInjected();
			// injects theme
			internalEnsureInjected(theme);
			// adds theme into cache
			Themes.get().add(theme);
		}
	}

	/**
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	public static void ensureInjected(ResourcePrototype resource) {
		ensureInjected(resource, Document.get().createScriptElement());
	}

	/**
	 * Injects script or style resources if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	private static void internalEnsureInjected(InjectableItem item) {
		// check if argument is consistent
		if (item != null) {
			// gets resources
			Collection<InjectableResource> resources = item.getResources();
			// checks if there is any resources to add
			if (!resources.isEmpty()) {
				// scans all resources to add
				for (InjectableResource resource : resources) {
					// inject
					ensureInjected(resource.getResource(), resource.getElement());
				}
			}
		}
	}

	/**
	 * Injects a script or style resource if not injected yet.
	 * 
	 * @param resource script resource
	 * @param element DOM element which will contain the resource
	 */
	private static void ensureInjected(ResourcePrototype resource, Element element) {
		// checks if resource is consistent
		if (resource != null) {
			// creates a unique key
			String resourceKey = createKey(resource);
			// checks if already injected
			if (!ELEMENTS_INJECTED.contains(resourceKey)) {
				if (resource instanceof TextResource) {
					TextResource textResource = (TextResource) resource;
					// sets ID
					element.setId(CODERBA_PREFIX_SCRIPT_ELEMENT_ID + resource.getName());
					// sets the script content source
					element.setInnerText(textResource.getText());
					// appends to the body
					Document.get().getBody().appendChild(element);
				}
				ELEMENTS_INJECTED.add(resourceKey);
			}
		}
	}

	/**
	 * Creates a unique key for a resource, in order to be able to cache it.<br>
	 * The key is built by class name of resource, a separator and the name of resource.
	 * 
	 * @param resource resource instance to inject
	 * @return a unique key
	 */
	private static final String createKey(ResourcePrototype resource) {
		return resource.getClass().getName() + KEY_CLASS_AND_METHOD_SEPARATOR + resource.getName();
	}
}