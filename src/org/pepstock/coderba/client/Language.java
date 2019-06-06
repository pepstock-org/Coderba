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
package org.pepstock.coderba.client;

import org.pepstock.coderba.client.entities.ModeSpecification;
import org.pepstock.coderba.client.utils.Window;

/**
 * Defines a programming language to use inside the editor.<br>
 * It has got the relation with its "mode" and its mode specification.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Language extends NamedItem {

	// mode specification instance
	private ModeSpecification modeSpecification = null;
	// flag if mode specification is already loaded
	private boolean isModeSpecLoaded = false;

	/**
	 * Creates a language instance by its name.<br>
	 * The language name is the MIME type defined for that language because a mode can implement more than 1 language.
	 * 
	 * @param name mime type which represents the language.
	 */
	protected Language(String name) {
		super(name);
	}

	/**
	 * Returns the long name (description) of language.
	 * 
	 * @return the long name (description) of language
	 */
	public abstract String getLongName();

	/**
	 * Returns the mode related to the language.
	 * 
	 * @return the mode related to the language
	 */
	public abstract Mode getMode();

	/**
	 * Returns the mode specification for this language.
	 * 
	 * @return the mode specification for this language or <code>null</code> if the mode of language is not already injected or
	 *         if the language uses the default mode.
	 */
	public final ModeSpecification getModeSpecification() {
		return modeSpecification;
	}

	/**
	 * Sets the mode specification for this language, only if not already set.
	 * 
	 * @param modeSpecification the mode specification for this language.
	 */
	final void setModeSpecification(ModeSpecification modeSpecification) {
		Window.getConsole().log("eccome");
		// checks if mode specification is already loaded
		if (!isModeSpecLoaded) {
			// if not, it stores it
			this.modeSpecification = modeSpecification;
			// resets the flag
			isModeSpecLoaded = true;
		}
	}

}
