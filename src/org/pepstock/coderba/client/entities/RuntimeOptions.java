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
import org.pepstock.coderba.client.enums.Options;

/**
 * Manages the options of an editor already instantiated.<br>
 * It uses a native editor to set and get options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class RuntimeOptions extends AbstractExtendedOptions<RuntimeOptionsContainer> {

	/**
	 * Create an options manager by native editor and default values.
	 * 
	 * @param nativeObject native editor with set and get methods.
	 * @param defaultsValue default values
	 */
	RuntimeOptions(NativeEditor nativeObject, IsDefaultOptions defaultsValue) {
		super(new RuntimeOptionsContainer(nativeObject), defaultsValue);
	}

	/**
	 * Copies the entities which can not be retrieved by java script object, like language or theme.
	 * 
	 * @param options options from copy the entities.
	 */
	void copy(IsExtendedOptions options) {
		super.copyItems(options);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getExtraKeys()
	 */
	@Override
	public ExtraKeyMapTable getExtraKeys() {
		// this methods has been override 
		// because to reduce the visibility of constructor
		// of extra key map
		ExtraKeyMapTable extraKeyMapTable = super.getExtraKeys();
		// checks if table already passed
		// or existing into native object 
		if (extraKeyMapTable == null) {
			// gets native object
			NativeObject object = getNativeObjectContainer().getObjectProperty(Options.EXTRA_KEYS.value());
			// creates and returns extra map into object
			return new ExtraKeyMapTable(object);	
		}
		return extraKeyMapTable;
	}

}
