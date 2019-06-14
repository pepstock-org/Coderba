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
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyStroke implements Stroke {

	static final String SEPARATOR = "-";

	static final KeyModifier[] EMPTY_MODIFIERS = new KeyModifier[0];

	// internal comparator to sort colors by own offset
	private static final Comparator<KeyModifier> COMPARATOR = (KeyModifier o1, KeyModifier o2) -> Integer.compare(o1.order(), o2.order());

	private final List<KeyModifier> modifiers;

	private final KeyName name;

	private String value = null;

	/**
	 * @param name
	 */
	public KeyStroke(KeyName name) {
		this(name, EMPTY_MODIFIERS);
	}

	/**
	 * @param name
	 */
	public KeyStroke(KeyName name, KeyModifier... modifiers) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value();
	}

}
