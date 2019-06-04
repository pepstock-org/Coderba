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
 * Cache of all mode instances loaded.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Modes extends InjectedItems<Mode> {

	// singleton instance
	private static final Modes INSTANCE = new Modes();

	/**
	 * To avoid any instantiation
	 */
	private Modes() {
		// do nothing
	}

	/**
	 * Returns the singleton instance of the cache.
	 * 
	 * @return the singleton instance of the cache
	 */
	public static Modes get() {
		return INSTANCE;
	}

}
