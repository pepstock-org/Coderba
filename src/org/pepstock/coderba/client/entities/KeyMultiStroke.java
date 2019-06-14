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

import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMultiStroke implements Stroke {

	static final String SEPARATOR = " ";

	private final List<Stroke> items = new LinkedList<>();

	private String value = null;

	/**
	 * @param name
	 */
	public KeyMultiStroke(Stroke... items) {
		if (items == null || items.length == 0) {
			throw new IllegalArgumentException("Items are null or not consistent");
		}
		for (Stroke item : items) {
			if (item.isMulti() && item instanceof KeyMultiStroke) {
				KeyMultiStroke multiStroke = (KeyMultiStroke) item;
				this.items.addAll(multiStroke.getItems());
			} else {
				this.items.add(item);
			}
		}
		buildValue();
	}

	/**
	 * @param name
	 */
	KeyMultiStroke(List<Stroke> items) {
		this(items.toArray(new KeyStroke[0]));
	}

	/**
	 * @return the modifiers
	 */
	public List<Stroke> getItems() {
		return items;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.entities.Stroke#isMulti()
	 */
	@Override
	public boolean isMulti() {
		return true;
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
		for (Stroke item : items) {
			if (sb.length() > 0) {
				sb.append(SEPARATOR);
			}
			sb.append(item.value());
		}
		value = sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value();
	}
	
	

}
