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
import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;
import org.pepstock.coderba.client.commons.ObjectType;
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * Key maps are ways to associate keys and mouse buttons with functionality.<br>
 * A key map is an object mapping strings that identify the buttons to functions that implement their functionality.<br>
 * The values of properties in key maps can be either functions of a single argument, strings, or false.<br>
 * Strings refer to commands, which are described into commands table. If the property is set to false, it leaves handling of
 * the key up to the browser.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class KeyMapTable extends NativeObjectContainer {

	// constant used by CodeMirror to chain more than 1 key map
	private static final Key FALLTHROUGH = Key.create("fallthrough");

	/**
	 * Creates a key map table by native object.
	 * 
	 * @param nativeObject native object which is mapping the key map
	 */
	KeyMapTable(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the unmodifiable list of strokes defined for key map.
	 * 
	 * @return the unmodifiable list of strokes defined for key map
	 */
	public final List<Stroke> getStrokes() {
		// creates result
		List<Stroke> maps = new LinkedList<>();
		// calls recursive method to load all strokes
		load(maps, this);
		// returns the unmodifiable list
		return Collections.unmodifiableList(maps);
	}

	/**
	 * Returns the command binded to the stroke, if exist, otherwise <code>null</code>.
	 * 
	 * @param stroke the stroke instance to search
	 * @return the command binded to the stroke, if exist, otherwise <code>null</code>
	 */
	public Command getCommand(Stroke stroke) {
		// checks if stroke is consistent
		if (Key.isValid(stroke)) {
			// gets the object type of stroke
			ObjectType type = type(stroke);
			// if string, is binded to a defined command 
			if (ObjectType.STRING.equals(type)) {
				// gets the command name
				String name = getValue(stroke, UndefinedValues.STRING);
				// checks if name is consistent
				if (name != null) {
					// returns the defined command
					return CommandSet.get().get(Key.create(name));
				}
			} else if (ObjectType.BOOLEAN.equals(type)) {
				// if defined as boolean
				// it leaves handling of
				// the key up to the browser
				return new DisabledCommand();
			} else if (ObjectType.FUNCTION.equals(type)) {
				// if defined as function
				Command.CommandFunction function = getValue(stroke, (Command.CommandFunction) null);
				// creates a command with this implementation
				return new FunctionedCommand(function);
			}
		}
		// if here, the stroke is not valid
		return null;
	}

	/**
	 * Recursive methods to load all strokes defined for key map.<br>
	 * It also loads all related other key maps defined by fall through.
	 * 
	 * @param maps list of already loaded strokes
	 * @param table related key map to read for strokes
	 */
	private void load(List<Stroke> maps, KeyMapTable table) {
		// scans all keys of native object
		for (Key key : table.keys()) {
			// checks if the key is fall through
			if (!Key.equals(FALLTHROUGH, key)) {
				// if not, parses the stroke and loads it
				maps.add(StrokeBuilder.parse(key.value()));
			} else {
				// gets the type of fall through (could be a string or array)
				ObjectType type = type(FALLTHROUGH);
				// checks if is a simple string
				if (ObjectType.STRING.equals(type)) {
					// gets the value to have the name of the other key map
					String value = table.getStringProperty(FALLTHROUGH.value(), UndefinedValues.STRING);
					// checks if is consistent
					if (value != null) {
						// loads the key map table by the name of key map
						load(maps, KeyMapSet.get().getKeyMapTable(Key.create(value)));
					}
				} else if (ObjectType.ARRAY.equals(type)) {
					// if here, fall through is an array
					// gets the value to have the arry of names of the other key maps
					ArrayString values = table.getArrayValue(FALLTHROUGH);
					// checks if is consistent
					if (values != null) {
						// scans the array
						for (int i = 0; i < values.length(); i++) {
							// loads the key map table by the name of key map
							load(maps, KeyMapSet.get().getKeyMapTable(Key.create(values.get(i))));
						}
					}
				}
			}
		}
	}

}
