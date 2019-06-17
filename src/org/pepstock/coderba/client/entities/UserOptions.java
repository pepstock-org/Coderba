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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.IsDefaultOptions;
import org.pepstock.coderba.client.commons.AbstractExtendedOptions;
import org.pepstock.coderba.client.commons.NativeObject;

/**
 * Core class to manage USER options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class UserOptions extends AbstractExtendedOptions<UserOptionsContainer> {

	/**
	 * Creates a new options manager for user, with an empty native object and default values.
	 * 
	 * @param defaultsValue default values
	 */
	UserOptions(IsDefaultOptions defaultsValue) {
		super(new UserOptionsContainer(), defaultsValue);
	}

	/**
	 * Returns the native object to apply to editor during its creation.
	 * 
	 * @return the native object to apply to editor during its creation
	 */
	final NativeObject getObject() {
		return getNativeObjectContainer().getObject();
	}
}
