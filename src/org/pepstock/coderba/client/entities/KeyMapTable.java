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

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;
import org.pepstock.coderba.client.commons.ObjectType;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.utils.Window;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMapTable extends NativeObjectContainer {
		
	private static final Key FALLTHROUGH = Key.create("fallthrough");

	/**
	 * @param nativeObject
	 */
	KeyMapTable(NativeObject nativeObject) {
		super(nativeObject);
	}
	
	public List<Stroke> getKeys(){
		List<Stroke> maps = new LinkedList<>();
		load(maps, this);
		return Collections.unmodifiableList(maps);
	}

	public Command getCommand(Stroke item) {
		if (item != null) {
			ObjectType type = type(item);
			if (ObjectType.STRING.equals(type)) {
				Window.getConsole().log(item.value()+" is String");
			} else if (ObjectType.BOOLEAN.equals(type)) {
				Window.getConsole().log(item.value()+" is Boolean");
			} else if (ObjectType.FUNCTION.equals(type)) {
				Window.getConsole().log(item.value()+" is Function");
			}
		}
		return null;
	}
	
	private void load(List<Stroke> maps, KeyMapTable table) {
		for (Key key : table.keys()) {
			Window.getConsole().log("Key :"+key.value()+":");
			if (!Key.equals(FALLTHROUGH, key)) {
				maps.add(Stroke.parse(key.value()));
			} else {
				String value = table.getStringProperty(FALLTHROUGH.value(), UndefinedValues.STRING); 
				if (value != null) {
					load(maps, KeyMapSet.get().getKeyMapTable(Key.create(value)));
				}	
			}
		}
	}
	
}

