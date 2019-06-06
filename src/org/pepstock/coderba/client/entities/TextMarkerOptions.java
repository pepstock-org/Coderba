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
import org.pepstock.coderba.client.enums.TextMarkerType;

import com.google.gwt.dom.client.Element;

/**
 * Options to use to set a text marker, both marker and bookmark.<br>
 * These options are supported:<br>
 * The options parameter is optional.<br>
 * When given, it should be an object that may contain the following configuration options: <br>
 * <br>
 * className: string. Assigns a CSS class to the marked stretch of text.<br>
 * <br>
 * inclusiveLeft: boolean. Determines whether text inserted on the left of the marker will end up inside or outside of it.<br>
 * <br>
 * inclusiveRight: boolean. Like inclusiveLeft, but for the right side.<br>
 * <br>
 * atomic: boolean. Atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible to place the
 * cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different meaning-they will prevent the
 * cursor from being placed respectively directly before and directly after the range.<br>
 * <br>
 * collapsed: boolean. Collapsed ranges do not show up in the display. Setting a range to be collapsed will automatically make
 * it atomic.<br>
 * <br>
 * clearOnEnter: boolean. When enabled, will cause the mark to clear itself whenever the cursor enters its range. This is mostly
 * useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event fired on the
 * range handle can be used to be notified when this happens.<br>
 * <br>
 * clearWhenEmpty: boolean. Determines whether the mark is automatically cleared when it becomes empty. Default is true.<br>
 * <br>
 * replacedWith: Element. Use a given node to display this range. Implies both collapsed and atomic. The given DOM node must be
 * an inline element (as opposed to a block element).<br>
 * <br>
 * handleMouseEvents: boolean. When replacedWith is given, this determines whether the editor will capture mouse and drag events
 * occurring in this widget. Default is false-the events will be left alone for the default browser handler, or specific
 * handlers on the widget, to capture.<br>
 * <br>
 * readOnly: boolean. A read-only span can, as long as it is not cleared, not be modified except by calling setValue to reset
 * the whole document. Note: adding a read-only span currently clears the undo history of the editor, because existing undo
 * events being partially nullified by read-only spans would corrupt the history (in the current implementation).<br>
 * <br>
 * addToHistory: boolean. When set to true (default is false), adding this marker will create an event in the undo history that
 * can be individually undone (clearing the marker).<br>
 * <br>
 * startStyle: string. Can be used to specify an extra CSS class to be applied to the leftmost span that is part of the
 * marker.<br>
 * <br>
 * endStyle: string. Equivalent to startStyle, but for the rightmost span.<br>
 * <br>
 * css: string. A string of CSS to be applied to the covered text. For example "color: #fe3".<br>
 * <br>
 * attributes: object. When given, add the attributes in the given object to the elements created for the marked text. Adding
 * class or style attributes this way is not supported.<br>
 * <br>
 * shared: boolean. When the target document is linked to other documents, you can set shared to true to make the marker appear
 * in all documents. By default, a marker appears only in its target document. <br>
 * For bookmark, these are properties to manage:<br>
 * <br>
 * widget: Element. Can be used to display a DOM node at the current location of the bookmark (analogous to the replacedWith
 * option to markText).<br>
 * <br>
 * insertLeft: boolean. By default, text typed when the cursor is on top of the bookmark will end up to the right of the
 * bookmark. Set this option to true to make it go to the left instead.<br>
 * <br>
 * shared: boolean. See the corresponding option to markText.<br>
 * <br>
 * handleMouseEvents: boolean.As with markText, this determines whether mouse events on the widget inserted for this bookmark
 * are handled by CodeMirror. The default is false.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class TextMarkerOptions extends BaseEntity {

	public static final String DEFAULT_CLASS_NAME = UndefinedValues.STRING;

	public static final boolean DEFAULT_INCLUSIVE_LEFT = false;

	public static final boolean DEFAULT_INCLUSIVE_RIGHT = false;

	public static final boolean DEFAULT_ATOMIC = false;

	public static final boolean DEFAULT_COLLAPSED = false;

	public static final boolean DEFAULT_CLEAR_ON_ENTER = false;

	public static final boolean DEFAULT_CLEAR_WHEN_EMPTY = false;

	public static final Element DEFAULT_REPLACE_WITH = null;

	public static final boolean DEFAULT_HANDLE_MOUSE_EVENTS = false;

	public static final boolean DEFAULT_READ_ONLY = false;

	public static final boolean DEFAULT_ADD_TO_HISTORY = false;

	public static final String DEFAULT_START_STYLE = UndefinedValues.STRING;

	public static final String DEFAULT_END_STYLE = UndefinedValues.STRING;

	public static final String DEFAULT_CSS = UndefinedValues.STRING;

	public static final Element DEFAULT_WIDGET = null;

	// FIXME
	public static final Object DEFAULT_ATTRIBUTES = null;

	public static final boolean DEFAULT_SHARED = false;

	private final TextMarkerType type;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		CLASS_NAME("className"),
		INCLUSIVE_LEFT("inclusiveLeft"),
		INCLUSIVE_RIGHT("inclusiveRight"),
		ATOMIC("atomic"),
		COLLAPSED("collapsed"),
		CLEAR_ON_ENTER("clearOnEnter"),
		CLEAR_WHEN_EMPTY("clearWhenEmpty"),
		REPLACE_WITH("replacedWith"),
		HANDLE_MOUSE_EVENTS("handleMouseEvents"),
		READ_ONLY("readOnly"),
		ADD_TO_HISTORY("addToHistory"),
		START_STYLE("startStyle"),
		END_STYLE("endStyle"),
		CSS("css"),
		ATTRIBUTES("attributes"),
		SHARED("shared"),
		// only for bookmark
		WIDGET("widget"),
		// only to get type even if a marker is created
		TYPE("type");

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

	public TextMarkerOptions() {
		this(TextMarkerType.RANGE);
	}

	public TextMarkerOptions(TextMarkerType type) {
		super();
		if (type != null) {
			this.type = type;
		} else {
			this.type = TextMarkerType.RANGE;
		}
	}

	TextMarkerOptions(NativeTextMarker nativeObject) {
		super(nativeObject);
		this.type = getValue(Property.TYPE, TextMarkerType.class, TextMarkerType.RANGE);
	}

	public final TextMarkerType getType() {
		return type;
	}

	public final boolean isBookmark() {
		return TextMarkerType.BOOKMARK.equals(type);
	}

	public final boolean isRange() {
		return TextMarkerType.RANGE.equals(type);
	}

	public final String getClassName() {
		return getValue(Property.CLASS_NAME, DEFAULT_CLASS_NAME);
	}

	public final void setClassName(String className) {
		if (isRange()) {
			setValue(Property.CLASS_NAME, className);
		}
	}

	public final boolean isInclusiveLeft() {
		return getValue(Property.INCLUSIVE_LEFT, DEFAULT_INCLUSIVE_LEFT);
	}

	public final void setInclusiveLeft(boolean inclusiveLeft) {
		setValue(Property.INCLUSIVE_LEFT, inclusiveLeft);
	}

	public final boolean isInclusiveRight() {
		return getValue(Property.INCLUSIVE_RIGHT, DEFAULT_INCLUSIVE_RIGHT);
	}

	public final void setInclusiveRight(boolean inclusiveRight) {
		if (isRange()) {
			setValue(Property.INCLUSIVE_RIGHT, inclusiveRight);
		}
	}

	public final boolean isAtomic() {
		return getValue(Property.ATOMIC, DEFAULT_ATOMIC);
	}

	public final void setAtomic(boolean atomic) {
		if (isRange()) {
			setValue(Property.ATOMIC, atomic);
		}
	}

	public final boolean isCollapsed() {
		return getValue(Property.COLLAPSED, DEFAULT_COLLAPSED);
	}

	public final void setCollapsed(boolean collapsed) {
		if (isRange()) {
			setValue(Property.COLLAPSED, collapsed);
		}
	}

	public final boolean isClearOnEnter() {
		return getValue(Property.CLEAR_ON_ENTER, DEFAULT_CLEAR_ON_ENTER);
	}

	public final void setClearOnEnter(boolean clearOnEnter) {
		if (isRange()) {
			setValue(Property.CLEAR_ON_ENTER, clearOnEnter);
		}
	}

	public final boolean isClearWhenEmpty() {
		return getValue(Property.CLEAR_WHEN_EMPTY, DEFAULT_CLEAR_WHEN_EMPTY);
	}

	public final void setClearWhenEmpty(boolean clearWhenEmpty) {
		if (isRange()) {
			setValue(Property.CLEAR_ON_ENTER, clearWhenEmpty);
		}
	}

	public final Element getReplacedWith() {
		return getValue(Property.REPLACE_WITH, DEFAULT_REPLACE_WITH);
	}

	public final void setReplacedWith(Element replacedWith) {
		if (isRange()) {
			setValue(Property.REPLACE_WITH, replacedWith);
		}
	}

	public final boolean isHandleMouseEvents() {
		return getValue(Property.HANDLE_MOUSE_EVENTS, DEFAULT_HANDLE_MOUSE_EVENTS);
	}

	public final void setHandleMouseEvents(boolean handleMouseEvents) {
		setValue(Property.HANDLE_MOUSE_EVENTS, handleMouseEvents);
	}

	public final boolean isReadOnly() {
		return getValue(Property.READ_ONLY, DEFAULT_READ_ONLY);
	}

	public final void setReadOnly(boolean readOnly) {
		if (isRange()) {
			setValue(Property.READ_ONLY, readOnly);
		}
	}

	public final boolean isAddToHistory() {
		return getValue(Property.ADD_TO_HISTORY, DEFAULT_ADD_TO_HISTORY);
	}

	public final void setAddToHistory(boolean addToHistory) {
		if (isRange()) {
			setValue(Property.ADD_TO_HISTORY, addToHistory);
		}
	}

	public final String getStartStyle() {
		return getValue(Property.START_STYLE, DEFAULT_START_STYLE);
	}

	public final void setStartStyle(String startStyle) {
		if (isRange()) {
			setValue(Property.START_STYLE, startStyle);
		}
	}

	public final String getEndStyle() {
		return getValue(Property.END_STYLE, DEFAULT_END_STYLE);
	}

	public final void setEndStyle(String endStyle) {
		if (isRange()) {
			setValue(Property.END_STYLE, endStyle);
		}
	}

	public final String getCss() {
		return getValue(Property.CSS, DEFAULT_CSS);
	}

	public final void setCss(String css) {
		if (isRange()) {
			setValue(Property.CSS, css);
		}
	}

	// ATTRIBUTES("attributes"),

	public final boolean isShared() {
		return getValue(Property.SHARED, DEFAULT_SHARED);
	}

	public final void setShared(boolean shared) {
		setValue(Property.SHARED, shared);
	}

	public final Element getWidget() {
		return getValue(Property.WIDGET, DEFAULT_WIDGET);
	}

	public final void setWidget(Element replacedWith) {
		if (isBookmark()) {
			setValue(Property.REPLACE_WITH, replacedWith);
		}
	}
}
