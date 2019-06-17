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
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.ObjectType;

/**
 * Key maps are ways to associate keys and mouse buttons with functionality.<br>
 * A key map is an object mapping strings that identify the buttons to functions that implement their functionality.<br>
 * Can be used to specify extra key bindings for the editor, alongside the ones defined.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ExtraKeyMapTable extends KeyMapTable {

	// buffer with all bindings between stroke and command
	// K = stroke id
	// V = commands name
	private final Map<String, String> bindings = new HashMap<>();

	/**
	 * Creates an empty key map table
	 */
	public ExtraKeyMapTable() {
		this(null);
	}

	/**
	 * Creates an extra key map by a stored native object.
	 * 
	 * @param nativeObject native object which defines whole key map
	 */
	ExtraKeyMapTable(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets a command binding it to the stroke.
	 * 
	 * @param stroke stroke which will trigger the command.
	 * @param command custom command instance related to the stroke
	 */
	public void setStroke(Stroke stroke, CustomCommand command) {
		// checks if stroke is valid and if command is consistent
		if (Key.isValid(stroke) && command != null) {
			// stores the command by its proxy callback
			setValue(stroke, command.getProxy());
			// stores command into cache
			Commands.add(command);
			// adds relations to bindings
			bindings.put(stroke.value(), command.getName().value());
		}
	}

	/**
	 * Sets a command name (previously stored into commands table) binding it to the stroke.
	 * 
	 * @param stroke stroke which will trigger the command.
	 * @param name name of the command related to the stroke
	 */
	public void setStroke(Stroke stroke, Key name) {
		// checks if stroke and name of command are valid
		if (Key.isValid(stroke) && Key.isValid(name)) {
			// stores the binding into object
			setValue(stroke, name);
		}
	}

	/**
	 * Removes an existing command from table by the binded stroke.
	 * 
	 * @param stroke stroke binded to command to remove
	 */
	public void removeStroke(Stroke stroke) {
		// checks if stroke is valid
		if (Key.isValid(stroke)) {
			// removes the stroke from object
			remove(stroke);
			// adds relations to bindings
			bindings.remove(stroke.value());
		}
	}

	/**
	 * Returns <code>true</code> if the passed stroke is defined.
	 * 
	 * @param stroke stroke instance to search
	 * @return <code>true</code> if the passed stroke is defined.
	 */
	public boolean hasStroke(Stroke stroke) {
		// checks if stroke is valid
		if (Key.isValid(stroke)) {
			// returns if stroke is defined
			return has(stroke);
		}
		// if here, stroke argument is not valid
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.KeyMapTable#getCommand(org.pepstock.coderba.client.entities.Stroke)
	 */
	@Override
	public Command getCommand(Stroke stroke) {
		// checks if stroke is defined
		if (hasStroke(stroke)) {
			// gets object type
			ObjectType type = type(stroke);
			// checks if the type is string
			// if string means that the binding was for a command
			// defined into commands table
			if (ObjectType.STRING.equals(type)) {
				// then uses the normal way to get command
				return super.getCommand(stroke);
			} else {
				// gets name of command
				String name = bindings.get(stroke.value());
				// if name is consistent
				if (name != null) {
					return Commands.get(Key.create(name));
				}
			}
		}
		// if here, stroke not consistent
		return null;
	}

}
