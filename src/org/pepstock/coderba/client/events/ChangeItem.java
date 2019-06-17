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
import org.pepstock.coderba.client.entities.Position;
import org.pepstock.coderba.client.entities.Range;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Object containing information about the changes that occurred.<br>
 * from and to are the positions (in the pre-change coordinate system) where the change started and ended.<br>
 * The event is fired before the end of an operation, before the DOM updates happen.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class ChangeItem extends Range {

	/**
	 * To avoid any instantiation
	 */
	private ChangeItem() {
		// do nothing
	}

	/**
	 * <b>INTERNAL</b><br>
	 * Returns an array of strings representing the text that replaced the changed range (split by line).
	 * 
	 * @return an array of strings representing the text that replaced the changed range (split by line)
	 */
	@JsProperty(name = "text")
	native ArrayString nativeGetText();

	/**
	 * <b>INTERNAL</b><br>
	 * Returns the text that used to be between from and to, which is overwritten by this change.
	 * 
	 * @return the text that used to be between from and to, which is overwritten by this change.
	 */
	@JsProperty(name = "removed")
	native ArrayString nativeGetRemoved();

	/**
	 * Returns a list of strings representing the text that replaced the changed range (split by line).
	 * 
	 * @return a list of strings representing the text that replaced the changed range (split by line).
	 */
	@JsOverlay
	public List<String> getText() {
		return ArrayListHelper.unmodifiableList(nativeGetText());
	}

	/**
	 * Return the text that used to be between from and to, which is overwritten by this change.
	 * 
	 * @return the text that used to be between from and to, which is overwritten by this change.
	 */
	@JsOverlay
	public List<String> getRemoved() {
		return ArrayListHelper.unmodifiableList(nativeGetRemoved());
	}

	/**
	 * Returns the origin string.
	 * 
	 * @return the origin string
	 */
	@JsProperty
	public native String getOrigin();

	/**
	 * It can be called to cancel the change, and, if the change isn't coming from an undo or redo event, an update(from, to,
	 * text) method, which may be used to modify the change.
	 */
	@JsMethod
	native void cancel();

	/**
	 * If the change isn't coming from an undo or redo event, this method may be used to modify the change.<br>
	 * Undo or redo changes can't be modified, because they hold some meta information for restoring old marked ranges that is
	 * only valid for that specific change.
	 * 
	 * @param from starting change position
	 * @param to ending change position
	 * @param text an array of strings representing the text that replaced the changed range (split by line)
	 */
	@JsMethod
	native void update(Position from, Position to, ArrayString text);
}
