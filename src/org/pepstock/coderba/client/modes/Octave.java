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
package org.pepstock.coderba.client.modes;

import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.resources.ModeResources;
import org.pepstock.coderba.client.resources.ScriptResource;

/**
 * Is the "octave" mode to enable the editor to manage this programming language.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Octave extends Mode {
	// name of editor mode
	private static final String NAME = "octave";

	/**
	 * Instance of editor mode.
	 */
	public static final Octave MODE = new Octave();

	/**
	 * To avoid any instantiation
	 */
	private Octave() {
		// passes the name of mode
		super(NAME);
		getResources().add(new ScriptResource(ModeResources.INSTANCE.octave()));

	}
}
