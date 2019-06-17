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

/**
 * Maps a set of strokes which are alias among us.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class KeyMultiStroke implements Stroke {
	// separator among strokes
	static final String SEPARATOR = " ";
	// constant of empty strokes
	static final Stroke[] EMPTY_STROKES = new Stroke[0];
	// list of strokes which defines this multi stroke
	private final List<Stroke> items = new LinkedList<>();
	// string representation of this stroke
	private final String value;

	/**
	 * Defines a multi stroke object with a list of all strokes.
	 * 
	 * @param strokes list of all strokes which defines this object
	 */
	public KeyMultiStroke(Stroke... strokes) {
		// checks if strokes are consistent
		if (strokes == null || strokes.length == 0) {
			// if no, exception
			throw new IllegalArgumentException("Strokes are null or not consistent");
		}
		// creates a string builder
		// to create the string representation of this stroke
		StringBuilder sb = new StringBuilder();
		// scans all strokes
		for (Stroke stroke : strokes) {
			// checks if passed stroke is already a multi one
			if (stroke.isMulti() && stroke instanceof KeyMultiStroke) {
				// casts to multi stroke
				KeyMultiStroke multiStroke = (KeyMultiStroke) stroke;
				// adds all strokes
				this.items.addAll(multiStroke.getStrokes());
			} else {
				// if here, the stroke is simple one
				// and adds to list
				this.items.add(stroke);
			}
			// if it is not the first
			if (sb.length() > 0) {
				// adds separator
				sb.append(SEPARATOR);
			}
			// adds stroke value
			sb.append(stroke.value());
		}
		// sets the value of this stroke
		this.value = sb.toString();
	}

	/**
	 * Defines a multi stroke object with a list of all strokes.
	 * 
	 * @param strokes list of all strokes which defines this object
	 */
	KeyMultiStroke(List<Stroke> strokes) {
		this(strokes.toArray(EMPTY_STROKES));
	}

	/**
	 * Returns the unmodifiable list of stroke which defines the multi stroke.
	 * 
	 * @return the unmodifiable list of stroke which defines the multi stroke
	 */
	public List<Stroke> getStrokes() {
		return Collections.unmodifiableList(items);
	}

	/*
	 * (non-Javadoc)
	 * 
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

}
