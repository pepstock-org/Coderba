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

/**
 * Is the "mIRC" language entity in order to abe able to set to the editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class MIRC implements Language {

	private final String name = "text/mirc";

	private final String longName = "mIRC";

	private final Mode mode = org.pepstock.coderba.client.modes.Mirc.MODE;

	public static final MIRC LANGUAGE = new MIRC();

	/**
	 * To avoid any instantiation
	 */
	private MIRC() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.Language#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.Language#getLongName()
	 */
	@Override
	public String getLongName() {
		return longName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.Language#getMode()
	 */
	@Override
	public Mode getMode() {
		return mode;
	}

}
