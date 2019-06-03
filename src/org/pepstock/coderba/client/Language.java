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

import org.pepstock.coderba.client.utils.Window;

/**
 * Defines a programming language to use inside the editor.<br>
 * It has got the relation with its "mode" and its mode specification.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Language extends NamedItem {

	private ModeSpecification modeSpecification = null;
	
	private boolean isModeSpecLoaded = false;

	/**
	 * @param value
	 */
	protected Language(String name) {
		super(name);
	}

	public abstract String getLongName();

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
		// checks if mode spec is already loaded
		if (!isModeSpecLoaded) {
			// if not, it stores it
			this.modeSpecification = modeSpecification;
			// resets the flag
			isModeSpecLoaded = true;
		}
	}

}
