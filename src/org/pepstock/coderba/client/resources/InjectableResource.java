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

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.TextResource;

/**
 * Defines how the resource, to be injected, must be implemented.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface InjectableResource {

	/**
	 * Returns a text resource instance which must be injected.
	 * 
	 * @return a text resource instance which must be injected
	 */
	TextResource getResource();

	/**
	 * Returns a DOM element which must wrap the text resource.
	 * 
	 * @return a DOM element which must wrap the text resource
	 */
	Element getElement();
}
