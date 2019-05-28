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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.coderba.client.commons.HasName;

/**
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class InjectedItems<T extends HasName> {
	
	// buffer with all injectable items instances
	// K = injectable item name
	// V = injectable item
	private final Map<String, T> instances = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	InjectedItems() {
	}
	
	/**
	 * Adds new injectable item instance into collection.
	 * 
	 * @param chart injectable ite
	 */
	final void add(T item) {
		// putting getting the chart
		instances.put(item.getName(), item);
	}

	public final T retrieve(String name) {
		// checks if argument is consistent
		if (name != null) {
			return instances.get(name);
		}
		// if here chart id is not consistent
		return null;
	}

}
