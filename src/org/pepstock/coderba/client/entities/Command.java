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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.enums.CommandType;

import jsinterop.annotations.JsFunction;

/**
 * Base class to define a command.<br>
 * Commands are parameter-less actions that can be performed on an editor.<br>
 * Their main use is for key bindings.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Command {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called to invoke the command.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface CommandFunction {

		/**
		 * A function that is called to invoke the command.
		 * 
		 * @param editor editor instance
		 */
		void call(NativeEditor editor);
	}

	// type of command
	private final CommandType type;

	/**
	 * Creates a command by its type.
	 * 
	 * @param type type of command
	 */
	public Command(CommandType type) {
		// checks if type is consistent
		if (type == null) {
			// if no, exception
			throw new IllegalArgumentException("Type of command is null");
		}
		// stores type
		this.type = type;
	}

	/**
	 * Returns the command type
	 * 
	 * @return the type of command.
	 */
	public final CommandType getType() {
		return type;
	}

	/**
	 * Executes the command instance.
	 * 
	 * @param editorArea editor area instance
	 */
	public abstract void execute(EditorArea editorArea);
}
