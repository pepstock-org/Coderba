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
 * Base object of native object container.<br>
 * Used for entities classes of Coderba implementation.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
abstract class BaseEntity extends NativeObjectContainer {

	/**
	 * Creates an empty container.
	 */
	BaseEntity() {
		super();
	}

	/**
	 * Creates a container with a native object instance.
	 * 
	 * @param nativeObject a native object instance
	 */
	BaseEntity(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeObject getObject() {
		return getNativeObject();
	}

}
