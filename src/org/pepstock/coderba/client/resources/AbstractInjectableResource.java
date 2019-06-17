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

import com.google.gwt.resources.client.TextResource;

/**
 * Abstract object to map a resource (script or style) to be injected.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractInjectableResource implements InjectableResource {
	// text resource to be injected
	private final TextResource resource;

	/**
	 * Creates the object with the text resource to be injected.
	 * 
	 * @param resource the text resource to be injected
	 */
	public AbstractInjectableResource(TextResource resource) {
		// checks if resource is consistent
		if (resource == null) {
			// if no, exception
			throw new IllegalArgumentException("Resource instance is null");
		}
		// stores the resource
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.resources.InjectableResource#getResource()
	 */
	@Override
	public final TextResource getResource() {
		return resource;
	}

}
