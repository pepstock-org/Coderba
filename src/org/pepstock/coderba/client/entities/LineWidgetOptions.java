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

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * Is an object that configures the behavior of the widget, when the widget is added to a line into a document.<br>
 * The following options are supported (all default to false):<br>
 * <br>
 * coverGutter: boolean Whether the widget should cover the gutter.<br>
 * <br>
 * noHScroll: boolean Whether the widget should stay fixed in the face of horizontal scrolling. <br>
 * <br>
 * above: boolean Causes the widget to be placed above instead of below the text of the line. <br>
 * <br>
 * handleMouseEvents: boolean Determines whether the editor will capture mouse and drag events occurring in this widget. Default
 * is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to capture.<br>
 * <br>
 * insertAt: integer By default, the widget is added below other widgets for the line. This option can be used to place it at a
 * different position (zero for the top, N to put it after the Nth other widget).<br>
 * <br>
 * Note that this only has effect once, when the widget is created.<br>
 * Note that the widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might in
 * some cases affect it.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LineWidgetOptions extends BaseEntity {

	public static final boolean DEFAULT_COVER_GUTTER = false;
	
	public static final boolean DEFAULT_NO_HSCROLL = false;
	
	public static final boolean DEFAULT_ABOVE = false;
	
	public static final boolean DEFAULT_HANDLE_MOUSE_EVENTS = false;

	public static final int DEFAULT_INSERT_AT = UndefinedValues.INTEGER;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		COVER_GUTTER("coverGutter"),
		NO_HSCROLL("noHScroll"),
		ABOVE("above"),
		HANDLE_MOUSE_EVENTS("handleMouseEvents"),
		INSERT_AT("insertAt");

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
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}
	
	public LineWidgetOptions() {
		this(null);
	}

	LineWidgetOptions(NativeObject nativeObject) {
		super(nativeObject);
	}

	public final boolean isCoverGutter() {
		return getValue(Property.COVER_GUTTER, DEFAULT_COVER_GUTTER);
	}

	public final void setCoverGutter(boolean coverGutter) {
		setValue(Property.COVER_GUTTER, coverGutter);
	}

	public final boolean isNoHScroll() {
		return getValue(Property.NO_HSCROLL, DEFAULT_NO_HSCROLL);
	}

	public final void setNoHScroll(boolean noHScroll) {
		setValue(Property.NO_HSCROLL, noHScroll);
	}
	
	public final boolean isAbove() {
		return getValue(Property.ABOVE, DEFAULT_ABOVE);
	}

	public final void setAbove(boolean above) {
		setValue(Property.ABOVE, above);
	}
	
	public final boolean isHandleMouseEvents() {
		return getValue(Property.HANDLE_MOUSE_EVENTS, DEFAULT_HANDLE_MOUSE_EVENTS);
	}

	public final void setHandleMouseEvents(boolean handleMouseEvents) {
		setValue(Property.HANDLE_MOUSE_EVENTS, handleMouseEvents);
	}
	
	public final int getInsertAt() {
		return getValue(Property.INSERT_AT, DEFAULT_INSERT_AT);
	}

	public final void setInsertAt(boolean insertAt) {
		setValue(Property.INSERT_AT, insertAt);
	}

}