/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
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

import java.util.HashMap;
import java.util.Map;

import org.pepstock.coderba.client.commons.Key;

/**
 * Cache which collects all custom command instances to be able to enable callbacks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Commands {

	// buffer with all custom command instances
	// K = commands name id
	// V = commands instance
	private static final Map<String, CustomCommand> COMMANDS_CACHE = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private Commands() {
	}

	/**
	 * Adds a custom command to the cache.
	 * 
	 * @param command instance of custom command to add
	 */
	static void add(CustomCommand command) {
		// checks if command is consistent
		if (command != null) {
			// puts into chace
			COMMANDS_CACHE.put(command.getName().value(), command);
		}
	}

	/**
	 * Removes a stored command from cache.
	 * 
	 * @param name the name of command to remove
	 */
	static void remove(Key name) {
		// checks if argument is consistent
		if (Key.isValid(name)) {
			// remove
			COMMANDS_CACHE.remove(name.value());
		}
	}

	/**
	 * Returns the command instance by its name as key.
	 * 
	 * @param name command name as key
	 * @return command instance or <code>null</code> if not exist.
	 */
	static CustomCommand get(Key name) {
		// checks if argument is consistent
		if (Key.isValid(name)) {
			return COMMANDS_CACHE.get(name.value());
		}
		// if here editor area id is not consistent
		return null;
	}

}
