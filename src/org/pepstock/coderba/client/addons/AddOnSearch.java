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
import org.pepstock.coderba.client.resources.AddOnResources;
import org.pepstock.coderba.client.resources.ScriptResource;

/**
 * Is the "search" addon to enable this capability into editor.<br>
 * Implements the search commands.<br>
 * CodeMirror has keys bound to these {@link AddOnSearch.Command} by default, but will not do anything with them unless an implementation is provided.<br>
 * It will make use of {@link AddOnDialog} when available to make prompting for search queries less ugly.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class AddOnSearch extends AddOn {

	/**
	 * Name of command names activated by this addon.
	 */
	public enum Command implements Key
	{
		FIND("find"),
		FIND_PERSISTENT("findPersistent"),
		FIND_PERSISTENT_NEXT("findPersistentNext"),
		FIND_PERSISTENT_PREV("findPersistentPrev"),
		FIND_NEXT("findNext"),
		FIND_PREV("findPrev"),
		CLEAR_SEARCH("clearSearch"),
		REPLACE("replace"),
		REPLACE_ALL("replaceAll");

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
	private static final String NAME = "search";

	/**
	 * Instance of editor addon.
	 */
	public static final AddOnSearch INSTANCE = new AddOnSearch();

	/**
	 * To avoid any instantiation
	 */
	private AddOnSearch() {
		// passes the name of addon
		super(NAME);
		getResources().add(new ScriptResource(AddOnResources.INSTANCE.search()));
		getDependencies().add(AddOnSearchcursor.INSTANCE);
		getDependencies().add(AddOnDialog.INSTANCE);
	}
}
