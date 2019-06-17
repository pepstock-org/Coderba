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

import java.util.List;

import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.NativeName;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Entity which maps the change item into the history of a document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class HistoryChangeItem extends Range {

	/**
	 * To avoid any instantiation
	 */
	private HistoryChangeItem() {
		// do nothing
	}

	/**
	 * <b>INTERNAL</b><br>
	 * Returns an array of strings which is representing the changes into document.
	 * 
	 * @return an array of strings which is representing the changes into document.
	 */
	@JsProperty(name = "text")
	private native ArrayString nativeGetText();

	/**
	 * Returns an unmodifiable list of strings which is representing the changes into document.
	 * 
	 * @return an unmodifiable list of strings which is representing the changes into document
	 */
	@JsOverlay
	public List<String> getText() {
		// gets array
		ArrayString array = nativeGetText();
		// returns the list
		return ArrayListHelper.unmodifiableList(array);
	}

}
