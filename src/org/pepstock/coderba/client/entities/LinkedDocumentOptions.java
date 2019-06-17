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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.commons.Key;

/**
 * This is the options to configure new linked document.<br>
 * These are the options that are supported:<br>
 * <ul>
 * <li>sharedHist: boolean. When turned on, the linked copy will share an undo history with the original.<br>
 * Thus, something done in one of the two can be undone in the other, and vice versa.<br>
 * <br>
 * <li>from: integer, to: integer. Can be given to make the new document a subview of the original.<br>
 * Subviews only show a given range of lines. <br>
 * Note that line coordinates inside the subview will be consistent with those of the parent, so that for example a subview
 * starting at line 10 will refer to its first line as line 10, not 0.<br>
 * <li>language: language. By default, the new document inherits the language of the parent. This option can be set to a
 * language to give it a different language and mode.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LinkedDocumentOptions extends BaseEntity {

	/**
	 * Default value if the linked copy will share an undo history with the original, <b>{@value DEFAULT_SHARED_HIST}</b>.
	 */
	public static final boolean DEFAULT_SHARED_HIST = false;

	/**
	 * Default value for range of lines of subview, <b>{@value DEFAULT_FROM_TO}</b>
	 */
	public static final int DEFAULT_FROM_TO = 0;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		SHARED_HIST("sharedHist"),
		FROM("from"),
		TO("to"),
		MODE("mode");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
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

	private Language language = null;

	/**
	 * Creates new empty options.
	 */
	public LinkedDocumentOptions() {
		super();
	}

	/**
	 * Returns <code>true</code> if the linked copy will share an undo history with the original.<br>
	 * Thus, something done in one of the two can be undone in the other, and vice versa.
	 * 
	 * @return <code>true</code> if the linked copy will share an undo history with the original
	 */
	public boolean isSharedHistory() {
		return getValue(Property.SHARED_HIST, DEFAULT_SHARED_HIST);
	}

	/**
	 * Sets <code>true</code> if the linked copy will share an undo history with the original.<br>
	 * Thus, something done in one of the two can be undone in the other, and vice versa.
	 * 
	 * @param sharedHistory <code>true</code> if the linked copy will share an undo history with the original
	 */
	public void setSharedHistory(boolean sharedHistory) {
		setValue(Property.SHARED_HIST, sharedHistory);
	}

	/**
	 * Returns the starting line to make the new document a subview of the original.<br>
	 * Note that line coordinates inside the subview will be consistent with those of the parent.
	 * 
	 * @return the starting line to make the new document a subview of the original
	 */
	public int getFrom() {
		return getValue(Property.FROM, DEFAULT_FROM_TO);
	}

	/**
	 * Sets the starting line to make the new document a subview of the original.<br>
	 * Note that line coordinates inside the subview will be consistent with those of the parent.
	 * 
	 * @param from the starting line to make the new document a subview of the original
	 */
	public void setFrom(int from) {
		setValue(Property.FROM, from);
	}

	/**
	 * Returns the ending line to make the new document a subview of the original.<br>
	 * Note that line coordinates inside the subview will be consistent with those of the parent.
	 * 
	 * @return the ending line to make the new document a subview of the original
	 */
	public int getTo() {
		return getValue(Property.TO, DEFAULT_FROM_TO);
	}

	/**
	 * Sets the ending line to make the new document a subview of the original.<br>
	 * Note that line coordinates inside the subview will be consistent with those of the parent.
	 * 
	 * @param to the ending line to make the new document a subview of the original
	 */
	public void setTo(int to) {
		setValue(Property.TO, to);
	}

	/**
	 * Returns the language to be set to linked document. It can return <code>null</code> if not set.
	 * 
	 * @return the language to be set to linked document. It can return <code>null</code> if not set
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Sets the language to be set to linked document.
	 * 
	 * @param language the language to be set to linked document
	 */
	public void setLanguage(Language language) {
		// checks if language is consistent
		if (language != null) {
			// injects and loads it
			Injector.ensureInjected(language);
			// sets the mode into linked document options
			setValue(Property.MODE, language.getModeSpecification());
		}
	}

}