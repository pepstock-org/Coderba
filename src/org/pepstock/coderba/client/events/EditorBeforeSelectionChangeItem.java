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

import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.entities.Anchor;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Its handler may inspect the set of selection ranges, present as an array of anchor objects in the ranges property of the
 * object argument, and optionally change them by calling the update method on this object, passing an array of ranges in the
 * same format.<br>
 * The object also contains an origin property holding the origin string passed to the selection-changing method, if any.<br>
 * Handlers for this event have the same restriction as "beforeChange" handlers - they should not do anything to directly update
 * the state of the editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class EditorBeforeSelectionChangeItem {

	/**
	 * to avoid any instantiation
	 */
	EditorBeforeSelectionChangeItem() {
	}

	/**
	 * <b>INTERNAL</b><br>
	 * Returns an array of anchor objects in the ranges property of the object argument.
	 * 
	 * @return an array of anchor objects in the ranges property of the object argument
	 */
	@JsProperty(name = "ranges")
	native ArrayEntity<Anchor> nativeGetRanges();

	/**
	 * Returns a list of anchor objects in the ranges property of the object argument.
	 * 
	 * @return a list of anchor objects in the ranges property of the object argument.
	 */
	@JsOverlay
	public List<Anchor> getRanges() {
		return ArrayListHelper.unmodifiableList(nativeGetRanges());
	}

	/**
	 * Returns the origin string passed to the selection-changing method, if any.
	 * 
	 * @return the origin string passed to the selection-changing method, if any
	 */
	@JsProperty
	public native String getOrigin();

	/**
	 * FIXME
	 * 
	 * @return
	 */
	@JsProperty
	public native Object getUpdate();

}
