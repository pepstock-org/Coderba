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

import java.util.List;

import org.pepstock.coderba.client.commons.Key;

/**
 * 
 * Modifiers Shift-, Cmd-, Ctrl-, and Alt- 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyModifier implements Key
{
	KEY_SHIFT("Shift", 16, 1),
	KEY_CTRL("Ctrl", 17, 2),
	KEY_CMD("Cmd", 17, 2),
	KEY_ALT("Alt", 18, 3);
	
//	private final List<Integer> code;
	
	private final int order;
	
	private final String value;

	/**
	 * @param code
	 * @param value
	 */
	private KeyModifier(String value, int order, int... codes) {
		this.value = value;
		this.order = order;
//		this.code = Collections.unmodifiableList(Arrays.asList(codes));
	}
	
	/**
	 * @return the order
	 */
	public int order() {
		return order;
	}

	/**
	 * FIXME
	 * @return
	 */
	public List<Integer> code() {
//		return code;
		return null;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}
	
}
