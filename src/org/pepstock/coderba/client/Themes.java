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
 * Cache of all theme instances loaded.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Themes extends InjectedItems<Theme> {
	
	// singleton instance
	private static final Themes INSTANCE = new Themes();

	/**
	 * To avoid any instantiation
	 */
	private Themes() {
		// do nothing
	}
	
	/**
	 * Returns the singleton instance of the cache.
	 * 
	 * @return the singleton instance of the cache
	 */
	public static Themes get() {
		return INSTANCE;
	}

}
