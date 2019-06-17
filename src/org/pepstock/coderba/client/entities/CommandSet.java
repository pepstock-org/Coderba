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

import java.util.Collections;
import java.util.List;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObjectContainer;
import org.pepstock.coderba.client.commons.ObjectType;

/**
 * Commands are parameter-less actions that can be performed on an editor.<br>
 * Their main use is for key bindings.<br>
 * Commands are defined by adding properties to the <code>CodeMirror.commands</code> object.<br>
 * The value of a command property must be a function of one argument (an editor instance).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CommandSet extends NativeObjectContainer {

	// singleton instance
	private static final CommandSet INSTANCE = new CommandSet();

	/**
	 * Creates the wrapper to the <code>CodeMirror.commands</code>.
	 */
	CommandSet() {
		super(CodeMirror.get().getCommands());
	}

	/**
	 * Returns the singleton instance of the commands.
	 * 
	 * @return the singleton instance of the commands
	 */
	public static CommandSet get() {
		return INSTANCE;
	}

	/**
	 * Returns an unmodifiable list with all defined command names.
	 * 
	 * @return an unmodifiable list with all defined command names
	 */
	public List<Key> names() {
		return Collections.unmodifiableList(keys());
	}

	/**
	 * Sets a new command instance.
	 * 
	 * @param command instance of custom command to set.
	 */
	public void set(CustomCommand command) {
		// checks if command is consistent
		if (command != null) {
			// adds command
			setValue(command.getName(), command.getProxy());
			// adds command to cache
			Commands.add(command);
		}
	}

	/**
	 * Returns the command instance previously defined.
	 * 
	 * @param name command name to get
	 * @return the command instance or <code>null</code> if not exists
	 */
	public Command get(Key name) {
		// checks if the name is already into cache
		CustomCommand result = Commands.get(name);
		// if the command was already stored
		if (result != null) {
			// returns custom command from cache
			return result;
		}
		// if here, the name of command is not of custom command
		// but it could be one of predefined values.
		Command.CommandFunction function = getFunction(name);
		// gets function
		if (function != null) {
			// creates a named command
			return new NamedCommand(name, function);
		}
		// if here, the name does not exists
		// then returns null
		return null;
	}

	/**
	 * Returns the function defined for the command. This is used ONLY for predefined command.
	 * 
	 * @param name command name to get function
	 * @return the function defined for the command
	 */
	Command.CommandFunction getFunction(Key name) {
		// checks if name is valid
		if (Key.isValid(name)) {
			// gets object type
			ObjectType type = type(name);
			// if is function
			if (ObjectType.FUNCTION.equals(type)) {
				// gets function
				Command.CommandFunction function = getValue(name, (Command.CommandFunction) null);
				// and returns it
				return function;
			}
		}
		// if here, the name is not consistent
		// or the command is not a function
		return null;
	}
}
