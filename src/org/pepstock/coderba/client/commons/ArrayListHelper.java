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
package org.pepstock.coderba.client.commons;

import java.util.Collections;
import java.util.List;

/**
 * Utility to create array list objects from java script arrays.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class ArrayListHelper {

	/**
	 * To avoid any instantiation
	 */
	private ArrayListHelper() {
		// nothing
	}

	/**
	 * Creates a array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance
	 */
	public static ArrayDoubleList list(ArrayDouble values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayDoubleList(values);
	}

	/**
	 * Creates a array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static ArrayIntegerList list(ArrayInteger values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayIntegerList(values);
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayStringList list(ArrayString values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayStringList(values);
	}

	/**
	 * Creates a array list of generic java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static ArrayObjectList list(ArrayObject values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayObjectList(values);
	}

	/**
	 * Creates a array list of native entities java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of native entity
	 * @return a array list of native entities instance
	 */
	public static <E extends NativeEntity> ArrayEntityList<E> list(ArrayEntity<E> values) {
		// creates the list
		// if values not consistent
		// creates an empty list
		return new ArrayEntityList<>(values);
	}

	/**
	 * Creates an unmodifiable array list of doubles by a java script array of doubles.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of doubles instance
	 */
	public static List<Double> unmodifiableList(ArrayDouble values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of integers by a java script array of integers.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of integers instance
	 */
	public static List<Integer> unmodifiableList(ArrayInteger values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates a array list of strings by a java script array of strings.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @return a array list of strings instance
	 */
	public static List<String> unmodifiableList(ArrayString values) {
		return Collections.unmodifiableList(list(values));
	}

	/**
	 * Creates an unmodifiable array list of native entity java script objects by a java script array.
	 * 
	 * @param values array of elements to load when the list is creating.
	 * @param <E> type of native entity
	 * @return a array list of native entities instance
	 */
	public static <E extends NativeEntity> List<E> unmodifiableList(ArrayEntity<E> values) {
		return Collections.unmodifiableList(list(values));
	}

}