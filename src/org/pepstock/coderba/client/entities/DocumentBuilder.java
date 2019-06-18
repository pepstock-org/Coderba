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

import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.enums.Direction;

/**
 * Comfortable object to create {@link Document} by a builder.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DocumentBuilder {
	
	private String value = null;
	
	private Language language = null;
	
	private int firstLineNumber = Integer.MIN_VALUE;
	
	private String lineSeparator = null;
	
	private Direction direction = null;
	
	/**
	 * To avoid any instantiation
	 */
	private DocumentBuilder() {
		// do nothing
	}
	
	/**
	 * Returns new builder instance.
	 * 
	 * @return new builder instance
	 */
	public static DocumentBuilder create() {
		return new DocumentBuilder();
	}

	/**
	 * The starting value of the editor.
	 * 
	 * @param value The starting value of the editor
	 * @return builder instance
	 */
	public DocumentBuilder setValue(String value) {
		this.value = value;
		return this;
	}

	/**
	 * Sets the language to apply to the editor.
	 * 
	 * @param language the language to apply to the editor
	 * @return builder instance
	 */
	public DocumentBuilder setLanguage(Language language) {
		this.language = language;
		return this;
	}

	/**
	 * At which number to start counting lines.
	 * 
	 * @param firstLineNumber at which number to start counting lines
	 * @return builder instance
	 */
	public DocumentBuilder setFirstLineNumber(int firstLineNumber) {
		this.firstLineNumber = firstLineNumber;
		return this;
	}

	/**
	 * Explicitly set the line separator for the editor.<br>
	 * By default (value null), the document will be split on CRLFs as well as lone CRs and LFs, and a single LF will be used as
	 * line separator in all output (such as getValue).<br>
	 * When a specific string is given, lines will only be split on that string, and output will, by default, use that same
	 * separator.
	 * 
	 * @param lineSeparator the line separator for the editor
	 * @return builder instance
	 */
	public DocumentBuilder setLineSeparator(String lineSeparator) {
		this.lineSeparator = lineSeparator;
		return this;
	}

	/**
	 * Flips overall layout and selects base paragraph direction to be left-to-right or right-to-left.
	 * 
	 * @param direction base paragraph direction to be left-to-right or right-to-left
	 * @return builder instance
	 */
	public DocumentBuilder setDirection(Direction direction) {
		this.direction = direction;
		return this;
	}

	/**
	 * Creates a document with options set previously.<br>
	 * When options has not been set, it uses the defaults.
	 * 
	 * @return a document instance
	 */
	public Document build() {
		// checks value 
		if (value == null) {
			// if not consistent, uses the default
			value = Defaults.get().getValue();
		}
		// checks language
		if (language == null) {
			// if not consistent, uses the default
			language = Defaults.get().getLanguage();
		}
		// checks first line number
		if (firstLineNumber == Integer.MIN_VALUE) {
			// if not consistent, uses the default
			firstLineNumber = Defaults.get().getFirstLineNumber();
		}
		// checks line separator
		if (lineSeparator == null) {
			// if not consistent, uses the default
			lineSeparator = Defaults.get().getLineSeparator();
		}
		// checks direction
		if (direction == null) {
			// if not consistent, uses the default
			direction = Defaults.get().getDirection();
		}
		// injects language
		Injector.ensureInjected(language);
		// creates the reference to document
		NativeDocument nativeDocument;
		// checks if mode specification has been set as string
		if (language.getModeSpecification().isString()) {
			// creates a native document with mode specification as string
			nativeDocument = new NativeDocument(value, language.getModeSpecification().getName(), firstLineNumber, lineSeparator, direction.value());
		} else {
			// creates a native document with mode specification as native mode
			nativeDocument = new NativeDocument(value, language.getModeSpecification().getModeSpecification(), firstLineNumber, lineSeparator, direction.value());
		}
		// creates document
		return new Document(nativeDocument, language);
	}
	

}
