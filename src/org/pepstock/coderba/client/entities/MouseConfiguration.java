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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.callbacks.MouseUnitHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.enums.MouseRepeat;
import org.pepstock.coderba.client.enums.MouseUnit;

import jsinterop.annotations.JsFunction;

/**
 * Maps the result of a configure mouse handler.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class MouseConfiguration extends BaseEntity {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that takes a position and returns a range around that, for a custom unit.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface MouseUnitFunction {

		/**
		 * A function that takes a position and returns a range around that, for a custom unit.
		 * 
		 * @param editor CodeMirror editor instance
		 * @param pos position
		 * @return a range around that, for a custom unit
		 */
		Range call(NativeEditor editor, Position pos);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the mouse unit function
	private final CallbackProxy<MouseUnitFunction> mouseUnitFunctionnProxy = JsHelper.get().newCallbackProxy();

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		UNIT("unit"),
		EXTEND("extend"),
		ADD_NEW("addNew"),
		MODE_ON_DRAG("moveOnDrag");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	private MouseUnitHandler mouseUnit = null;

	/**
	 * Creates an empty object.<br>
	 * The unit by which to select. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for
	 * alt-clicks (or, on Chrome OS, meta-shift-clicks), and "char" otherwise.
	 */
	public MouseConfiguration() {
		this(null);
	}

	/**
	 * Creates an object by mouse repeat value.<br>
	 * The unit by which to select. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for
	 * alt-clicks (or, on Chrome OS, meta-shift-clicks), and "char" otherwise.
	 * 
	 * @param repeat mouse repeat instance.
	 */
	public MouseConfiguration(MouseRepeat repeat) {
		setUnit(repeat);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		mouseUnitFunctionnProxy.setCallback((editor, pos) -> onMouseUnit(editor, pos));
	}

	/**
	 * A function that takes a position and returns a range around that, for a custom unit.
	 * 
	 * @param editor CodeMirror editor instance
	 * @param pos position
	 * @return a range around that, for a custom unit
	 */
	private Range onMouseUnit(NativeEditor editor, Position pos) {
		EditorArea area = editor.getEditorArea();
		if (area != null && mouseUnit != null) {
			Range result = mouseUnit.handle(area, pos);
			if (result != null) {
				return result;
			}
		}
		return Range.create(pos, pos);
	}

	/**
	 * A function that takes a position and returns a range around that, for a custom unit.
	 * 
	 * @param mouseUnit mouse unti handler instance
	 */
	public void setUnit(MouseUnitHandler mouseUnit) {
		this.mouseUnit = mouseUnit;
		setValue(Property.UNIT, mouseUnitFunctionnProxy.getProxy());
	}

	/**
	 * A function that takes a position and returns a range around that, for a custom unit.
	 * 
	 * @return the unit by which to select by a repeat instance
	 */
	public MouseUnitHandler getUnitHandler() {
		return this.mouseUnit;
	}

	/**
	 * The unit by which to select. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for
	 * alt-clicks (or, on Chrome OS, meta-shift-clicks), and "char" otherwise.
	 * 
	 * @param repeat the unit by which to select by a repeat instance
	 */
	public void setUnit(MouseRepeat repeat) {
		// checks if argument is consistent
		if (repeat != null) {
			// sets the unit by repeat
			setUnit(repeat.getDefaultUnit());
		}
	}

	/**
	 * The unit by which to select. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for
	 * alt-clicks (or, on Chrome OS, meta-shift-clicks), and "char" otherwise.
	 * 
	 * @param unit the unit by which to select
	 */
	public void setUnit(MouseUnit unit) {
		setValue(Property.UNIT, unit);
	}

	/**
	 * The unit by which to select. The default is to return "word" for double clicks, "line" for triple clicks, "rectangle" for
	 * alt-clicks (or, on Chrome OS, meta-shift-clicks), and "char" otherwise.
	 * 
	 * @return the unit by which to select
	 */
	public MouseUnit getUnit() {
		return getValue(Property.UNIT, MouseUnit.class, MouseUnit.CHAR);
	}

	/**
	 * Whether to extend the existing selection range or start a new one. By default, this is enabled when shift clicking.
	 * 
	 * @param extend Whether to extend the existing selection range or start a new one
	 */
	public void setExtend(boolean extend) {
		setValue(Property.EXTEND, extend);
	}

	/**
	 * Whether to extend the existing selection range or start a new one. By default, this is enabled when shift clicking.
	 * 
	 * @return Whether to extend the existing selection range or start a new one
	 */
	public boolean isExtend() {
		return getValue(Property.EXTEND, true);
	}

	/**
	 * When enabled, this adds a new range to the existing selection, rather than replacing it.<br>
	 * The default behavior is to enable this for command-click on Mac OS, and control-click on other platforms.
	 * 
	 * @param addNew When enabled, this adds a new range to the existing selection, rather than replacing it
	 */
	public void setAddNew(boolean addNew) {
		setValue(Property.ADD_NEW, addNew);
	}

	/**
	 * When enabled, this adds a new range to the existing selection, rather than replacing it.<br>
	 * The default behavior is to enable this for command-click on Mac OS, and control-click on other platforms.
	 * 
	 * @return When enabled, this adds a new range to the existing selection, rather than replacing it
	 */
	public boolean isAddNew() {
		return getValue(Property.ADD_NEW, false);
	}

	/**
	 * When the mouse even drags content around inside the editor, this controls whether it is copied (false) or moved (true).
	 * By default, this is enabled by alt-clicking on Mac OS, and ctrl-clicking elsewhere.
	 * 
	 * @param moveOnDrag When the mouse even drags content around inside the editor, this controls whether it is copied (false)
	 *            or moved (true)
	 */
	public void setMoveOnDrag(boolean moveOnDrag) {
		setValue(Property.MODE_ON_DRAG, moveOnDrag);
	}

	/**
	 * When the mouse even drags content around inside the editor, this controls whether it is copied (false) or moved (true).
	 * By default, this is enabled by alt-clicking on Mac OS, and ctrl-clicking elsewhere.
	 * 
	 * @return When the mouse even drags content around inside the editor, this controls whether it is copied (false) or moved
	 *         (true)
	 */
	public boolean isMoveOnDrag() {
		return getValue(Property.MODE_ON_DRAG, false);
	}

}
