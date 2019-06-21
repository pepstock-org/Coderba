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
package org.pepstock.coderba.client.entities;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.GlobalDefaults;
import org.pepstock.coderba.client.KeyMap;
import org.pepstock.coderba.client.KeyMaps;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;

/**
 * Maps the key maps object, defined of CodeMirror.<br>
 * It wraps the <code>CodeMirror.keyMap</code> property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMapSet extends NativeObjectContainer {

	// singleton instance
	private static final KeyMapSet INSTANCE = new KeyMapSet();

	/**
	 * Creates the wrapper to the <code>CodeMirror.keyMap</code>.<br>
	 */
	KeyMapSet() {
		super(CodeMirror.get().getKeyMaps());
		// to inject the defaults keymap
		GlobalDefaults.get();
	}

	/**
	 * Returns the singleton instance of the key maps.
	 * 
	 * @return the singleton instance of the key maps
	 */
	public static KeyMapSet get() {
		return INSTANCE;
	}

	/**
	 * Returns an unmodifiable list of the defined key maps.
	 * 
	 * @return an unmodifiable list of the defined key maps
	 */
	public List<KeyMap> getKeyMaps() {
		// creates the result list
		List<KeyMap> maps = new LinkedList<>();
		// scans all keys
		for (Key key : keys()) {
			// search for key map
			KeyMap map = KeyMaps.get().retrieve(key.value());
			// if found
			if (map != null) {
				// adds to result
				maps.add(map);
			}
		}
		// returns the unmodifiable list
		return Collections.unmodifiableList(maps);
	}

	/**
	 * Returns the key map table for a specific key map, otherwise returns <code>null</code>.
	 * 
	 * @param keyMap key map object to search
	 * @return the key map table for a specific key map, otherwise returns <code>null</code>.
	 */
	public KeyMapTable getTable(KeyMap keyMap) {
		// checks if key map is consistent
		if (keyMap != null) {
			// creates a key by key map name
			Key keyMapKey = Key.create(keyMap.getName());
			// checks if there is the property
			if (has(keyMapKey)) {
				// returns the table
				return getKeyMapTable(keyMapKey);
			}
		}
		// if here, the key map is not consistent
		return null;
	}

	/**
	 * Returns a key map table by the key related to key map name or <code>null</code> if no exists.
	 * 
	 * @param name name of key map as key
	 * @return a key map table by the key related to key map name or <code>null</code> if no exists
	 */
	KeyMapTable getKeyMapTable(Key name) {
		// getx native object of key map
		NativeObject table = getValue(name);
		// FIXME fallthough to manage. no good in this way
		///
		// checks if consistent
		if (table != null) {
			// returns key map table
			return new KeyMapTable(table);
		}
		// if here key map name no exists
		return null;
	}

}
