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

import org.pepstock.coderba.client.commons.HasName;

/**
 * Abstract calls to define a type with a mandatory name, as string.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class NamedItem implements HasName {

	// name of object
	private final String name;

	/**
	 * Creates the object with a mandatory name as string.
	 * 
	 * @param name name of object as string
	 */
	NamedItem(String name) {
		// checks if name is consistent
		if (name == null) {
			// if not exception
			throw new IllegalArgumentException("[NamedItem] Name is null");
		}
		this.name = name;
	}

	/**
	 * Returns the name of the object.
	 * 
	 * @return the name of object
	 */
	public final String getName() {
		return name;
	}
}
