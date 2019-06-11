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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.enums.KeyModifier;
import org.pepstock.coderba.client.enums.KeyName;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class SequenceKey implements Key {

	private static final String SEPARATOR = "-";

	private static final KeyModifier[] EMPTY_MODIFIERS = new KeyModifier[0];

	// internal comparator to sort colors by own offset
	private static final Comparator<KeyModifier> COMPARATOR = (KeyModifier o1, KeyModifier o2) -> Integer.compare(o1.order(), o2.order());

	private final List<KeyModifier> modifiers;

	private final KeyName name;

	private String value = null;

	/**
	 * @param name
	 */
	public SequenceKey(KeyName name) {
		this(name, EMPTY_MODIFIERS);
	}

	/**
	 * @param name
	 */
	public SequenceKey(KeyName name, KeyModifier... modifiers) {
		if (name == null) {
			throw new IllegalArgumentException("Key name is null");
		}
		this.name = name;
		if (modifiers != null && modifiers.length > 0) {
			List<KeyModifier> args = Arrays.asList(modifiers);
			Collections.sort(args, COMPARATOR);
			this.modifiers = Collections.unmodifiableList(args);
		} else {
			this.modifiers = Collections.emptyList();
		}
		buildValue();
	}
	
	public static final SequenceKey parse(String value) {
		String[] values = value.split(SEPARATOR);
		if (values.length > 1) {
			List<KeyModifier> modifiers = new ArrayList<>();
			for (int i = 0; i < values.length - 1; i++) {
				KeyModifier modifier = Key.getKeyByValue(KeyModifier.class, values[i]);
				if (modifier == null) {
					throw new IllegalArgumentException("Key '"+values[i]+"' is not a modifier");
				}
				modifiers.add(modifier);
			}
			KeyName name = Key.getKeyByValue(KeyName.class, values[values.length - 1]);
			return new SequenceKey(name, modifiers.toArray(EMPTY_MODIFIERS));
		} else {
			KeyName name = Key.getKeyByValue(KeyName.class, values[0]);
			return new SequenceKey(name);
		}
	}


	/**
	 * @return the modifiers
	 */
	public List<KeyModifier> getModifiers() {
		return modifiers;
	}

	/**
	 * @return the name
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

	/**
	 * FIXME
	 */
	private void buildValue() {
		StringBuilder sb = new StringBuilder();
		if (!modifiers.isEmpty()) {
			for (KeyModifier modifier : modifiers) {
				sb.append(modifier.value()).append(SEPARATOR);
			}
		}
		sb.append(name.value());
		value = sb.toString();
	}

}
