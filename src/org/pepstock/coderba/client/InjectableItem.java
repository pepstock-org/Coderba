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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.resources.InjectableResource;

/**
 * Defines an object which must inject script or CSS style resources into DOM document, at runtime.<br>
 * It must have a name.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class InjectableItem extends NamedItem {

	// internal collections of resources to be inject
	private final List<InjectableResource> resources = new LinkedList<>();
	// internal collections of items (dependencies) to be inject
	private final List<InjectableItem> dependencies = new LinkedList<>();

	/**
	 * Creates the object with a mandatory name as string.
	 * 
	 * @param name name of object as string
	 */
	InjectableItem(String name) {
		super(name);
	}

	/**
	 * Returns the collection of script or style resources which will belongs to this item.
	 * 
	 * @return the collection of script or style resources
	 */
	protected final List<InjectableResource> getResources() {
		return resources;
	}
	
	/**
	 * Returns the collection of items (dependencies) to this item.
	 * 
	 * @return the collection of items (dependencies) to this ite
	 */
	protected final List<InjectableItem> getDependencies() {
		return dependencies;
	}


}
