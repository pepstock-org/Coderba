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
package org.pepstock.coderba.client.languages;

import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.modes.ModeR;

/**
 * Is the "R" language entity in order to abe able to set to the editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class R extends Language {

	private static final String NAME = "text/x-rsrc";

	private static final String LONG_NAME = "R";

	private static final Mode MODE = ModeR.MODE;

	public static final R LANGUAGE = new R();

	/**
	 * To avoid any instantiation
	 */
	private R() {
		super(NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.Language#getLongName()
	 */
	@Override
	public String getLongName() {
		return LONG_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.Language#getMode()
	 */
	@Override
	public Mode getMode() {
		return MODE;
	}

}
