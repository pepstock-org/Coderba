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
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.entities.KeyStroke;
import org.pepstock.coderba.client.enums.KeyModifier;
import org.pepstock.coderba.client.enums.KeyName;
import org.pepstock.coderba.client.resources.AddOnResources;
import org.pepstock.coderba.client.resources.ScriptResource;

/**
 * Is the "jump-to-line" addon to enable this capability into editor.<br>
 * Implements a {@link AddOnJumpToLine.Command#JUMP_TO_LINE} command and binding {@link AddOnJumpToLine#ALT_G} to it.<br>
 * Accepts <code>linenumber, +/-linenumber, line:char, scroll%</code> and <code>:linenumber </code> formats.<br>
 * This will make use of openDialog when available to make prompting for line number neater.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AddOnJumpToLine extends AddOn {

	/**
	 * Name of command names activated by this addon.
	 */
	public enum Command implements Key
	{
		JUMP_TO_LINE("jumpToLine");

		// name value of property
		private final String value;

		/**
		 * Creates with the command name.
		 * 
		 * @param value value of command name
		 */
		private Command(String value) {
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

	// name of editor addon
	private static final String NAME = "jump-to-line";

	/**
	 * Instance of editor addon.
	 */
	public static final AddOnJumpToLine INSTANCE = new AddOnJumpToLine();

	/**
	 * "Alt-G" Stroke defined to invoke the {@link AddOnJumpToLine.Command#JUMP_TO_LINE} command.
	 */
	public static final KeyStroke ALT_G = new KeyStroke(KeyName.KEY_G, KeyModifier.KEY_ALT);

	/**
	 * To avoid any instantiation
	 */
	private AddOnJumpToLine() {
		// passes the name of addon
		super(NAME);
		getResources().add(new ScriptResource(AddOnResources.INSTANCE.jumpToLine()));
		getDependencies().add(AddOnDialog.INSTANCE);
	}

}
