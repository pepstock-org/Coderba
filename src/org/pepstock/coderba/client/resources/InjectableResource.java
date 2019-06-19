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
	 * Compares the two specified resources.
	 * 
	 * @param k1 the first resource to compare
	 * @param k2 the second resource to compare
	 * @return the value 0 if k1 class is equal to k2; a value less than 0 if k1 class is a script resource is less than k2; and a value greater than
	 *         0 if k1 class is style resource.
	 */
	static int compare(InjectableResource k1, InjectableResource k2) {
		// checks if k1 argument is consistent
		if (k1 == null) {
			// checks if k2 argument is consistent
			if (k2 == null) {
				// both are null then equals
				return 0;
			}
			// k2 is greater being not null
			return -1;
		} else {
			// checks if k2 argument is consistent
			if (k2 == null) {
				// k2 is less being not null
				return 1;
			}
			// checks if k1 is a STYLE resource 
			if (k1 instanceof StyleResource) {
				// if yes, priority 1
				return -1;
			} else if (k2 instanceof StyleResource) {
				// checks if k1 is a STYLE resource 
				return 1;
			}
			// if here, both reosurces are script
			return 0;
		}
	}

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
