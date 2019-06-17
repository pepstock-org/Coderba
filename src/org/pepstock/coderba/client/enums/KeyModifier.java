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
package org.pepstock.coderba.client.enums;

import org.pepstock.coderba.client.commons.Key;

/**
 * It is a special key (or combination) on a computer keyboard that temporarily modifies the normal action of another key when
 * pressed together.<br>
 * By themselves, modifier keys usually do nothing.<br>
 * Modifiers are <code>Shift</code>, <code>Cmd</code>, <code>Ctrl</code>, and <code>Alt</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyModifier implements Key
{
	/**
	 * SHIFT key modifier.
	 */
	KEY_SHIFT("Shift", 1),
	/**
	 * CTRL key modifier.
	 */
	KEY_CTRL("Ctrl", 2),
	/**
	 * CMD key modifier (like CTRL for MAC).
	 */
	KEY_CMD("Cmd", 2),
	/**
	 * ALT key modifier.
	 */
	KEY_ALT("Alt", 3);

	// order of modifier
	// minimum if most important
	private final int order;
	// string value of key
	private final String value;

	/**
	 * Creates a modifier with string representation and order number, which is mandatory to specify the default order (Shift-Cmd-Ctrl-Alt).
	 * 
	 * @param value string representation of key.
	 * @param order order of modifier, minimum if most important
	 */
	private KeyModifier(String value, int order) {
		this.value = value;
		this.order = order;
	}

	/**
	 * @return the order
	 */
	public int order() {
		return order;
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
