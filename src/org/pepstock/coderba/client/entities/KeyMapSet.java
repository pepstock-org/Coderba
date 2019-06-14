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
 * FIXME Defaults options of CodeMirror.<br>
 * It is used to set global options for all editor instances.<br>
 * It wraps the <code>CodeMirror.defaults</code> property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMapSet extends NativeObjectContainer {

	// singleton instance
	private static final KeyMapSet INSTANCE = new KeyMapSet();

	/**
	 * FIXME Creates the wrapper to the <code>CodeMirror.defaults</code> using the default values.<br>
	 * Uses a USER options container as the global defaults for internal defaults.
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
	
	public List<KeyMap> getKeyMaps(){
		List<KeyMap> maps = new LinkedList<>();
		for (Key key : keys()) {
			KeyMap map = KeyMaps.get().retrieve(key.value());
			if (map != null) {
				maps.add(map);
			}
		}
		return Collections.unmodifiableList(maps);
	}

	public KeyMapTable getTable(KeyMap keyMap){
		if (keyMap != null) {
			Key keyMapKey = Key.create(keyMap.getName());
			if (has(keyMapKey)) {
				return getKeyMapTable(keyMapKey);
			}
		}
		return null;
	}

	KeyMapTable getKeyMapTable(Key name) {
		NativeObject table = getValue(name);
		if (table != null) {
			return  new KeyMapTable(table);
		}
		return null;
	}

	
	/**
	 * Returns the native object.
	 * 
	 * @return the native object.
	 */
	final NativeObject getObject() {
		return getNativeObject();
	}
	
}
