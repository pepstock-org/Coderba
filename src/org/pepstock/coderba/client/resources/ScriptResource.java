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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.resources.client.TextResource;

/**
 * Defines a SCRIPT element which will wrap a text resource which contains java script code.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScriptResource extends AbstractInjectableResource {

	// creates a DOM element for SCRIPT
	private final ScriptElement element = Document.get().createScriptElement();

	/**
	 * Creates the object with the text resource to be injected.
	 * 
	 * @param resource the text resource to be injected
	 */
	public ScriptResource(TextResource resource) {
		super(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.resources.InjectableResource#getElement()
	 */
	@Override
	public final Element getElement() {
		return element;
	}

}
