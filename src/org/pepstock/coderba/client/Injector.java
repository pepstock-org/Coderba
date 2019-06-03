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

import org.pepstock.coderba.client.resources.InjectableResource;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.client.TextResource;

/**
 * This utility injects CodeMirror java script modules and CODERBA custom java script implementation (for some utilities) into the web page of
 * GWT, on demand.
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
	 * Injects a script resource if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	public static void ensureInjected() {
		// check if argument is consistent
		internalEnsureInjected(CodeMirrorCode.CODE);
	}
	
	/**
	 * Injects a script resource if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	public static void ensureInjected(Language language) {
		// check if argument is consistent
		if (language != null) {
			ensureInjected();
			ensureInjected(language.getMode());
			ModeSpecification modeSpec = CodeMirror.get().getMimeModes().getMode(language);
			language.setModeSpecification(modeSpec);
			Languages.get().add(language);
		}
	}

	/**
	 * Injects a script resource if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	public static void ensureInjected(Mode mode) {
		// check if argument is consistent
		if (mode != null) {
			ensureInjected();
			internalEnsureInjected(mode);
			Modes.get().add(mode);
		}
	}

	/**
	 * Injects a script resource if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	public static void ensureInjected(KeyMap keyMap) {
		// check if argument is consistent
		if (keyMap != null) {
			ensureInjected();
			internalEnsureInjected(keyMap);
			KeyMaps.get().add(keyMap);
		}
	}

	/**
	 * Injects a script resource if not injected yet by an injectable item.
	 * 
	 * @param item injectable item to inject
	 */
	public static void ensureInjected(Theme theme) {
		// check if argument is consistent
		if (theme != null) {
			ensureInjected();
			internalEnsureInjected(theme);
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
	 * Injects a script resource if not injected yet by an injectable item.
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
	 * Injects a script resource if not injected yet.
	 * 
	 * @param resource script resource
	 */
	private static void ensureInjected(ResourcePrototype resource, Element element) {
		// checks if resource is consistent
		if (resource != null) {
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
	
	private static final String createKey(ResourcePrototype resource) {
		return resource.getClass().getName() + KEY_CLASS_AND_METHOD_SEPARATOR + resource.getName();
	}
}