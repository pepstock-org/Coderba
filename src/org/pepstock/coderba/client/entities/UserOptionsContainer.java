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

import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainer;

/**
 * Manages set and get of all options by a native object container instance and its set and get methods.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class UserOptionsContainer extends NativeObjectContainer {

	/**
	 * Creates an empty user options.
	 */
	public UserOptionsContainer() {
		this(null);
	}

	/**
	 * Creates a container wrapping a native object.<br>
	 * This is used by DEFAULT options.
	 * 
	 * @param nativeObject native object to wrap. The instance is getting by <code>CodeMirror.defaults</code> property.
	 */
	UserOptionsContainer(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject getObject() {
		return super.getNativeObject();
	}

}
