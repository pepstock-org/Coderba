/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.coderba.client.events;

import java.util.List;

import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.entities.Range;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * is a {from, to, text, removed, origin} object containing information about the changes that occurred as second argument.<br>
 * from and to are the positions (in the pre-change coordinate system) where the change started and ended (for example, it might
 * be {ch:0, line:18} if the position is at the beginning of line #19).<br>
 * text is an array of strings representing the text that replaced the changed range (split by line).<br>
 * removed is the text that used to be between from and to, which is overwritten by this change.<br>
 * This event is fired before the end of an operation, before the DOM updates happen.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class ChangeItem extends Range {

	/**
	 * to avoid any instantiation
	 */
	ChangeItem() {
	}

	@JsProperty(name = "text")
	native ArrayString nativeGetText();

	@JsProperty(name = "removed")
	native ArrayString nativeGetRemoved();

	@JsOverlay
	public List<String> getText() {
		return ArrayListHelper.unmodifiableList(nativeGetText());
	}

	@JsOverlay
	public List<String> getRemoved() {
		return ArrayListHelper.unmodifiableList(nativeGetRemoved());
	}

	@JsProperty
	public native String getOrigin();
}
