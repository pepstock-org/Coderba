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
 * <br>
 * sharedHist: boolean. When turned on, the linked copy will share an undo history with the original. Thus, something done in
 * one of the two can be undone in the other, and vice versa.<br>
 * <br>
 * from: integer<br>
 * to: integer<br>
 * Can be given to make the new document a subview of the original. Subviews only show a given range of lines. Note that line
 * coordinates inside the subview will be consistent with those of the parent, so that for example a subview starting at line 10
 * will refer to its first line as line 10, not 0.<br>
 * <br>
 * mode: string|object. By default, the new document inherits the mode of the parent. This option can be set to a mode spec to
 * give it a different mode.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class LinkedDocumentOptions extends BaseEntity {

	public static final boolean DEFAULT_SHARED_HIST = false;

	public static final int DEFAULT_FROM_TO = 0;

	public static final String DEFAULT_MODE = Defaults.get().getLanguage().getName();

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

	public LinkedDocumentOptions() {
		super();
	}

	public boolean isSharedHistory() {
		return getValue(Property.SHARED_HIST, DEFAULT_SHARED_HIST);
	}

	public void setSharedHistory(boolean sharedHistory) {
		setValue(Property.SHARED_HIST, sharedHistory);
	}

	public int getFrom() {
		return getValue(Property.FROM, DEFAULT_FROM_TO);
	}

	public void setFrom(int from) {
		setValue(Property.FROM, from);
	}

	public int getTo() {
		return getValue(Property.TO, DEFAULT_FROM_TO);
	}

	public void setTo(int to) {
		setValue(Property.TO, to);
	}

	// public Language getLanguage() {
	// String languageName = getValue(Property.MODE, DEFAULT_MODE);
	// return Languages.get().retrieve(languageName);
	// }

	public void setLanguage(Language language) {
		if (language != null) {
			Injector.ensureInjected(language);
			setValue(Property.MODE, language.getModeSpecification());
		}
	}

}