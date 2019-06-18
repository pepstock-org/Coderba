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
 * Creates stroke parsing a string which represents a stroke (i.e. Ctrl-Alt-Space).<br>
 * It creates a multi or single stroke, parsing the string.
 * 
 * @author Andrea "Stock" Stocchero
 * @see KeyStroke
 * @see KeyMultiStroke
 */
public final class StrokeParser {

	/**
	 * To avoid any instantiation
	 */
	private StrokeParser() {
		// do nothing
	}

	/**
	 * Parses a string to create a stroke, both single or multi one.
	 * 
	 * @param value stroke string representation (i.e. Ctrl-Alt-Space)
	 * @return new stroke instance
	 */
	public static Stroke parse(String value) {
		// checks if argument is consistent
		if (value != null && value.trim().length() > 0) {
			// trims from spaces
			String valueToParse = value.trim();
			// checks if still consistent
			if (valueToParse.length() > 0) {
				// starts parsing
				parseStroke(valueToParse);
			}
			// if here, argument not consistent (empty)
			// then exception
			throw new IllegalArgumentException("Value is empty");

		}
		// if here, argument not consistent (null)
		// then exception
		throw new IllegalArgumentException("Value is null");
	}

	/**
	 * Parses a string to create a stroke, both single or multi one.
	 * 
	 * @param value stroke string representation (i.e. Ctrl-Alt-Space)
	 * @return new stroke instance
	 */
	private static Stroke parseStroke(String value) {
		// checks if the string contains the MULTI stroke separator (blank)
		if (value.contains(KeyMultiStroke.SEPARATOR)) {
			// splits by multi stroke separator (blank)
			String[] values = value.split(KeyMultiStroke.SEPARATOR);
			// creates an empty list
			List<Stroke> items = new LinkedList<>();
			// scans all split strings
			for (int i = 0; i < values.length; i++) {
				// parses a single stroke
				// and adds to list
				items.add(StrokeParser.parseSingle(values[i]));
			}
			// creates and returns a multi stroke
			return new KeyMultiStroke(items);
		} else {
			// if here, it's not a multi stroke
			// bu single one
			return StrokeParser.parseSingle(value);
		}
	}

	/**
	 * Parses a string to create a SINGLE stroke.
	 * 
	 * @param value stroke string representation (i.e. Ctrl-Alt-Space)
	 * @return new SINGLE stroke instance
	 * @see KeyStroke
	 */
	private static KeyStroke parseSingle(String value) {
		// checks if contains single stroke separator (minus)
		// and contains more than 1 char (with some modifiers)
		if (value.contains(KeyStroke.SEPARATOR) && value.length() > KeyStroke.SEPARATOR.length()) {
			// checks if the string ends with separator
			// that means that the stroke has got MINUS as keyname
			if (value.endsWith(KeyStroke.SEPARATOR)) {
				// if here, it's a MULTI stroke
				// where key name is MINUS
				return parseWithKeyNameAsSeparator(value);
			} else {
				// if here, it's a MULTI stroke
				return parseWithoutKeyNameAsSeparator(value);
			}
		} else {
			// if here, there isn't any modifiers
			// stroke with single char
			KeyName name = Key.getKeyByValue(KeyName.class, value);
			// creates and returns a SINGLE stroke
			return new KeyStroke(name);
		}
	}

	/**
	 * Parse a string to create a SINGLE stroke where key name is MINUS.
	 * 
	 * @param value stroke string representation where key name is MINUS.
	 * @return new SINGLE stroke instance
	 * @see KeyStroke
	 */
	private static KeyStroke parseWithKeyNameAsSeparator(String value) {
		// if here value ends with minus
		// now checks if minus is key name to use or mistake
		// removes last minus
		value = value.substring(0, value.lastIndexOf(KeyStroke.SEPARATOR));
		// if key name, the previous char before minus MUST be another minus
		if (!value.endsWith(KeyStroke.SEPARATOR)) {
			// not a good string
			throw new IllegalArgumentException("Value is not correctly formatted");
		}
		// splits by SINGLE stroke separator (minus)
		String[] values = value.split(KeyStroke.SEPARATOR);
		// creates a list of modifiers
		List<KeyModifier> modifiers = new ArrayList<>();
		// scans all split values
		for (int i = 0; i < values.length; i++) {
			// gets modifier
			KeyModifier modifier = Key.getKeyByValue(KeyModifier.class, values[i]);
			// if not a good modifier
			if (modifier == null) {
				// exception
				throw new IllegalArgumentException("Key '" + values[i] + "' is not a modifier");
			}
			// adds to list
			modifiers.add(modifier);
		}
		// creates a SINGLKE stroke using MINUS as key name and the list of modifiers
		// if there are
		return new KeyStroke(KeyName.KEY_MINUS, modifiers.toArray(KeyStroke.EMPTY_MODIFIERS));
	}

	/**
	 * Parse a string to create a SINGLE stroke where key name is NOT MINUS.
	 * 
	 * @param value stroke string representation where key name is NOT MINUS.
	 * @return new SINGLE stroke instance
	 * @see KeyStroke
	 */
	private static KeyStroke parseWithoutKeyNameAsSeparator(String value) {
		// splits by SINGLE stroke separator (minus)
		String[] values = value.split(KeyStroke.SEPARATOR);
		// checks if there is any modifier
		if (values.length > 1) {
			// creates a list of modifiers
			List<KeyModifier> modifiers = new ArrayList<>();
			// scans all split values (excluding the last which is the key name)
			for (int i = 0; i < values.length - 1; i++) {
				// gets modifier
				KeyModifier modifier = Key.getKeyByValue(KeyModifier.class, values[i]);
				// if not a good modifier
				if (modifier == null) {
					// exception
					throw new IllegalArgumentException("Key '" + values[i] + "' is not a modifier");
				}
				// adds to list
				modifiers.add(modifier);
			}
			// last split value is the key name
			KeyName name = Key.getKeyByValue(KeyName.class, values[values.length - 1]);
			// creates a SINGLKE stroke using last split value as key name and the list of modifiers
			// if there are
			return new KeyStroke(name, modifiers.toArray(KeyStroke.EMPTY_MODIFIERS));
		} else {
			// if here, there is not any modifier
			// because there is not the SINGLE stroke separator
			// gets key name
			KeyName name = Key.getKeyByValue(KeyName.class, values[0]);
			// creates and returns the SINGLE stroke
			return new KeyStroke(name);
		}
	}

}
