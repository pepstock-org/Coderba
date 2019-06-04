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

/**
 * CodeMirror distribution contains a number of reusable components that implement extra editor functionality.<br>
 * This object maps abstractly those components.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AddOn extends InjectableItem {

	/**
	 * Creates the object with the mandatory name of addon.
	 * 
	 * @param value the mandatory name of addon
	 */
	protected AddOn(String name) {
		super(name);
	}

	/**
	 * Injects the addon instance into DOM document in order to consume it by CodeMirror.
	 */
	public final void inject() {
		Injector.ensureInjected(this);
	}

}
