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
package org.pepstock.coderba.client.commons;

import org.pepstock.coderba.client.commons.CallbackProxy.Proxy;
import org.pepstock.coderba.client.defaults.IsDefaultOptions;
import org.pepstock.coderba.client.defaults.IsExtendedOptions;
import org.pepstock.coderba.client.enums.Options;

/**
 * Core class to manage all CodeMirror options with callbacks<br>
 * This class is used for the following type of options:<br>
 * <ul>
 * <li>USER, provided by user to create an editor
 * <li>EDITOR, provided by CodeMirror as merge between DEFAULT and USER (used at runtime)
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of object container.
 */
public abstract class AbstractExtendedOptions<T extends AbstractNativeObjectContainer> extends AbstractOptions<T> implements IsExtendedOptions {

	/**
	 * Creates the options manager by a container and default values.
	 * 
	 * @param nativeObjectContainer native object container which manage all set and get by key
	 * @param defaultsValue default values
	 */
	protected AbstractExtendedOptions(T nativeObjectContainer, IsDefaultOptions defaultsValue) {
		super(nativeObjectContainer, defaultsValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineNumberFormatter(org.pepstock.coderba.client.cm.commons.
	 * CallbackProxy.Proxy)
	 */
	@Override
	public void setLineNumberFormatter(Proxy lineNumberFormatter) {
		getNativeObjectContainer().setValue(Options.LINE_NUMBER_FORMATTER, lineNumberFormatter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSpecialCharPlaceholder(org.pepstock.coderba.client.cm.commons.
	 * CallbackProxy.Proxy)
	 */
	@Override
	public void setSpecialCharPlaceholder(Proxy specialCharPlaceholder) {
		getNativeObjectContainer().setValue(Options.SPECIAL_CHAR_PLACEHOLDER, specialCharPlaceholder);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.coderba.client.defaults.IsExtendedOptions#setConfigureMouse(org.pepstock.coderba.client.commons.CallbackProxy.
	 * Proxy)
	 */
	@Override
	public void setConfigureMouse(Proxy configureMouse) {
		getNativeObjectContainer().setValue(Options.CONFIGURE_MOUSE, configureMouse);
	}

}
