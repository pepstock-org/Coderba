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
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.enums.CommandType;

/**
 * Maps a predefined command with the name and function which has been assigned to a stroke by a key map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class NamedCommand extends Command {
	// name of command
	private final Key name;
	// defined function for comman
	private Command.CommandFunction function;

	/**
	 * Creates a command with its name and defined function.
	 * 
	 * @param name name of command
	 * @param function predefined related function
	 */
	NamedCommand(Key name, Command.CommandFunction function) {
		super(CommandType.NAMED);
		// checks if name is consistent
		Key.checkIfValid(name);
		// checks if function is consistent
		if (function == null) {
			throw new IllegalArgumentException("Function is null");
		}
		// stores name and function
		this.name = name;
		this.function = function;
	}

	/**
	 * Returns the name of command.
	 * 
	 * @return the name of command
	 */
	public Key getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.Command#execute(org.pepstock.coderba.client.EditorArea)
	 */
	@Override
	public void execute(EditorArea editorArea) {
		// gets editor instance
		Editor editor = editorArea.getEditor();
		// checks if editor is consistent
		if (editor != null) {
			// invokes the function with native editor object
			function.call(editor.getNativeObject());
		}
	}

}
