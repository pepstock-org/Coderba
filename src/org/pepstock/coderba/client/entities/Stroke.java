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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.enums.KeyModifier;
import org.pepstock.coderba.client.enums.KeyName;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public interface Stroke extends Key {
	
	static Stroke parse(String value) {
		if (value != null) {
			if (value.contains(KeyMultiStroke.SEPARATOR)) {
				String[] values = value.split(KeyMultiStroke.SEPARATOR);
				List<Stroke> items = new LinkedList<>();
				for (int i = 0; i < values.length; i++) {
					items.add(Stroke.parse(values[i]));
				}
				return new KeyMultiStroke(items);
			} else {
				return Stroke.parseSingle(value);
			}
		}
		throw new IllegalArgumentException("Value is null");
	}

	static Stroke parseSingle(String value) {
		if (value != null) {
			String valueToParse = value.trim();
			if (valueToParse.length() > 0) {
				if (valueToParse.contains(KeyStroke.SEPARATOR) && valueToParse.length() > KeyStroke.SEPARATOR.length()) {
					// checks if last item is minus
					if (valueToParse.endsWith(KeyStroke.SEPARATOR)) {
						// if here value ends with minus
						// now checks if minus is key name to use or mistake
						// if key name, the previous char before minus MUST be another minus
						valueToParse = valueToParse.substring(0, valueToParse.lastIndexOf(KeyStroke.SEPARATOR));
						if (!valueToParse.endsWith(KeyStroke.SEPARATOR)) {
							throw new IllegalArgumentException("Value is not correctly formatted");
						}
						String[] values = valueToParse.split(KeyStroke.SEPARATOR);
						List<KeyModifier> modifiers = new ArrayList<>();
						for (int i = 0; i < values.length; i++) {
							KeyModifier modifier = Key.getKeyByValue(KeyModifier.class, values[i]);
							if (modifier == null) {
								throw new IllegalArgumentException("Key '" + values[i] + "' is not a modifier");
							}
							modifiers.add(modifier);
						}
						return new KeyStroke(KeyName.KEY_MINUS, modifiers.toArray(KeyStroke.EMPTY_MODIFIERS));
					}
					String[] values = valueToParse.split(KeyStroke.SEPARATOR);
					if (values.length > 1) {
						List<KeyModifier> modifiers = new ArrayList<>();
						for (int i = 0; i < values.length - 1; i++) {
							KeyModifier modifier = Key.getKeyByValue(KeyModifier.class, values[i]);
							if (modifier == null) {
								throw new IllegalArgumentException("Key '" + values[i] + "' is not a modifier");
							}
							modifiers.add(modifier);
						}
						KeyName name = Key.getKeyByValue(KeyName.class, values[values.length - 1]);
						return new KeyStroke(name, modifiers.toArray(KeyStroke.EMPTY_MODIFIERS));
					} else {
						KeyName name = Key.getKeyByValue(KeyName.class, values[0]);
						return new KeyStroke(name);
					}
				} else {
					KeyName name = Key.getKeyByValue(KeyName.class, valueToParse);
					return new KeyStroke(name);
				}
			}
			throw new IllegalArgumentException("Value is empty");
		}
		throw new IllegalArgumentException("Value is null");
	}
	
	default boolean isMulti() {
		return false;
	}

}
