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
package org.pepstock.coderba.client.addons;

import org.pepstock.coderba.client.AddOn;
import org.pepstock.coderba.client.resources.AddOnResources;
import org.pepstock.coderba.client.resources.ScriptResource;

/**
 * Is the "runmode.node" addon to enable this capability into editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AddOnRunmodenode extends AddOn {
	// name of editor addon
	private static final String NAME = "runmode.node";

	/**
	 * Instance of editor addon.
	 */
	public static final AddOnRunmodenode INSTANCE = new AddOnRunmodenode();

	/**
	 * To avoid any instantiation
	 */
	private AddOnRunmodenode() {
		// passes the name of addon
		super(NAME);
		getResources().add(new ScriptResource(AddOnResources.INSTANCE.runmodenode()));
	}

}
