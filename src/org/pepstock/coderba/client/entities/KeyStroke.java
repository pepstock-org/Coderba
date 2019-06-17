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

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.pepstock.coderba.client.enums.KeyModifier;
import org.pepstock.coderba.client.enums.KeyName;

/**
 * Defines a set of key names to associate keys and mouse buttons for key map.<br>
 * A key stroke can be defined with 0 or more modifiers and 1 key name.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyStroke implements Stroke {
	// separator for single stroke
	static final String SEPARATOR = "-";
	// constant for an empty modifiers
	static final KeyModifier[] EMPTY_MODIFIERS = new KeyModifier[0];
	// internal comparator to sort colors by own offset
	private static final Comparator<KeyModifier> COMPARATOR = (KeyModifier o1, KeyModifier o2) -> Integer.compare(o1.order(), o2.order());
	// list of modifiers of this stroke
	private final List<KeyModifier> modifiers;
	// key name which define this stroke
	private final KeyName name;
	// string representation of stroke
	private final String value;

	/**
	 * Creates a stroke with only a key name (no modifiers).
	 * 
	 * @param name the key name related to this stroke.
	 */
	public KeyStroke(KeyName name) {
		this(name, EMPTY_MODIFIERS);
	}

	/**
	 * Creates a stroke with a key name and an array of modifiers.
	 * 
	 * @param name the key name related to this stroke.
	 * @param modifiers array of modifiers related to this stroke
	 */
	public KeyStroke(KeyName name, KeyModifier... modifiers) {
		// key name is mandatory and checks if consistent
		if (name == null) {
			// if not, exception
			throw new IllegalArgumentException("Key name is null");
		}
		// stores the key name
		this.name = name;
		// checks if modifiers are consistent
		if (modifiers != null && modifiers.length > 0) {
			// creates a list from modifiers
			List<KeyModifier> args = Arrays.asList(modifiers);
			// sorts them to have teh right order of modifiers
			Collections.sort(args, COMPARATOR);
			// sets modifiers
			this.modifiers = Collections.unmodifiableList(args);
		} else {
			// stores an empty
			this.modifiers = Collections.emptyList();
		}
		// creates a builder to create a string representation
		StringBuilder sb = new StringBuilder();
		// checks if modifiers are consistent
		if (!this.modifiers.isEmpty()) {
			// scans modifiers
			for (KeyModifier modifier : modifiers) {
				// appends teh string representation
				sb.append(modifier.value()).append(SEPARATOR);
			}
		}
		// adds finally the key name value
		sb.append(name.value());
		// sets string representation
		this.value = sb.toString();
	}

	/**
	 * Returns the unmodifiable list of modifiers.
	 * 
	 * @return the unmodifiable list of modifiers
	 */
	public List<KeyModifier> getModifiers() {
		return modifiers;
	}

	/**
	 * Returns the key name related to the stroke.
	 * 
	 * @return the key name related to the stroke
	 */
	public KeyName getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
