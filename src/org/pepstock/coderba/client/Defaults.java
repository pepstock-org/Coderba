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

import org.pepstock.coderba.client.commons.AbstractOptions;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.defaults.GlobalDefaults;

/**
 * Defaults options of CodeMirror.<br>
 * It is used to set global options for all editor instances.<br>
 * It wraps the <code>CodeMirror.defaults</code> property.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Defaults extends AbstractOptions<UserOptionsContainer> {

	/**
	 * Creates the wrapper to the <code>CodeMirror.defaults</code> using the default values.
	 * 
	 * @param nativeObject <code>CodeMirror.defaults</code> instance.
	 */
	Defaults(NativeObject nativeObject) {
		super(new UserOptionsContainer(nativeObject), GlobalDefaults.get());
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
