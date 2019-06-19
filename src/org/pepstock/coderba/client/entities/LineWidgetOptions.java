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
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * Is an object that configures the behavior of the widget, when the widget is added to a line into a document.<br>
 * The following options are supported (all default to false):<br>
 * <ul>
 * <li>coverGutter: boolean Whether the widget should cover the gutter.
 * <li>noHScroll: boolean Whether the widget should stay fixed in the face of horizontal scrolling.
 * <li>above: boolean Causes the widget to be placed above instead of below the text of the line.
 * <li>handleMouseEvents: boolean Determines whether the editor will capture mouse and drag events occurring in this widget.<br>
 * Default is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to
 * capture.
 * <li>insertAt: integer By default, the widget is added below other widgets for the line. This option can be used to place it
 * at a different position (zero for the top, N to put it after the Nth other widget).
 * </ul>
 * <br>
 * Note that this only has effect once, when the widget is created.<br>
 * Note that the widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might in
 * some cases affect it.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LineWidgetOptions extends BaseEntity {

	/**
	 * Default value whether the widget should cover the gutter, {@value DEFAULT_COVER_GUTTER}.
	 */
	public static final boolean DEFAULT_COVER_GUTTER = false;

	/**
	 * Default value whether the widget should stay fixed in the face of horizontal scrolling, {@value DEFAULT_NO_HSCROLL}.
	 */
	public static final boolean DEFAULT_NO_HSCROLL = false;

	/**
	 * Default value whether the widget to be placed above instead of below the text of the line, {@value DEFAULT_ABOVE}.
	 */
	public static final boolean DEFAULT_ABOVE = false;

	/**
	 * Default value whether the editor will capture mouse and drag events occurring in this widget,
	 * {@value DEFAULT_HANDLE_MOUSE_EVENTS}.
	 */
	public static final boolean DEFAULT_HANDLE_MOUSE_EVENTS = false;

	/**
	 * Default value if the widget is added below other widgets for the line, {@value DEFAULT_INSERT_AT}.
	 */
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
		 * @see org.pepstock.coderba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	/**
	 * Creates new empty line widget options.
	 */
	public LineWidgetOptions() {
		this(null);
	}

	/**
	 * Creates a container with a native object instance.<br>
	 * This is used when coordinate has been passed by a method of CodeMirror.
	 * 
	 * @param nativeObject a native object instance
	 */
	LineWidgetOptions(NativeLineWidget nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns <code>true</code> whether the widget should cover the gutter.
	 * 
	 * @return <code>true</code> whether the widget should cover the gutter
	 */
	public final boolean isCoverGutter() {
		return getValue(Property.COVER_GUTTER, DEFAULT_COVER_GUTTER);
	}

	/**
	 * Sets whether the widget should cover the gutter.
	 * 
	 * @param coverGutter <code>true</code> whether the widget should cover the gutter
	 */
	public final void setCoverGutter(boolean coverGutter) {
		setValue(Property.COVER_GUTTER, coverGutter);
	}

	/**
	 * Returns whether the widget should stay fixed in the face of horizontal scrolling.
	 * 
	 * @return <code>true</code> whether the widget should stay fixed in the face of horizontal scrolling
	 */
	public final boolean isNoHScroll() {
		return getValue(Property.NO_HSCROLL, DEFAULT_NO_HSCROLL);
	}

	/**
	 * Sets whether the widget should stay fixed in the face of horizontal scrolling
	 * 
	 * @param noHScroll <code>true</code> whether the widget should stay fixed in the face of horizontal scrolling
	 */
	public final void setNoHScroll(boolean noHScroll) {
		setValue(Property.NO_HSCROLL, noHScroll);
	}

	/**
	 * Returns <code>true</code> to placed the widget above instead of below the text of the line.
	 * 
	 * @return <code>true</code> to placed the widget above instead of below the text of the line
	 */
	public final boolean isAbove() {
		return getValue(Property.ABOVE, DEFAULT_ABOVE);
	}

	/**
	 * Sets <code>true</code> to placed the widget above instead of below the text of the line.
	 * 
	 * @param above <code>true</code> to placed the widget above instead of below the text of the line
	 */
	public final void setAbove(boolean above) {
		setValue(Property.ABOVE, above);
	}

	/**
	 * Returns <code>true</code> whether the editor will capture mouse and drag events occurring in this widget.<br>
	 * Default is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to
	 * capture.
	 * 
	 * @return <code>true</code> whether the editor will capture mouse and drag events occurring in this widget.<br>
	 *         Default is false-the events will be left alone for the default browser handler, or specific handlers on the
	 *         widget, to capture.
	 */
	public final boolean isHandleMouseEvents() {
		return getValue(Property.HANDLE_MOUSE_EVENTS, DEFAULT_HANDLE_MOUSE_EVENTS);
	}

	/**
	 * Sets <code>true</code> whether the editor will capture mouse and drag events occurring in this widget.<br>
	 * Default is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to
	 * capture.
	 * 
	 * @param handleMouseEvents <code>true</code> whether the editor will capture mouse and drag events occurring in this
	 *            widget.<br>
	 *            Default is false-the events will be left alone for the default browser handler, or specific handlers on the
	 *            widget, to capture.
	 */
	public final void setHandleMouseEvents(boolean handleMouseEvents) {
		setValue(Property.HANDLE_MOUSE_EVENTS, handleMouseEvents);
	}

	/**
	 * By default, the widget is added below other widgets for the line. This option can be used to place it at a different
	 * position (zero for the top, N to put it after the Nth other widget).
	 * 
	 * @return position of the widget
	 */
	public final int getInsertAt() {
		return getValue(Property.INSERT_AT, DEFAULT_INSERT_AT);
	}

	/**
	 * By default, the widget is added below other widgets for the line. This option can be used to place it at a different
	 * position (zero for the top, N to put it after the Nth other widget).
	 * 
	 * @param insertAt position of the widget
	 */
	public final void setInsertAt(boolean insertAt) {
		setValue(Property.INSERT_AT, insertAt);
	}

}