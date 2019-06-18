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
 * <ul>
 * <li>className: string. Assigns a CSS class to the marked stretch of text.
 * <li>inclusiveLeft: boolean. Determines whether text inserted on the left of the marker will end up inside or outside of
 * it.<br>
 * <li>inclusiveRight: boolean. Like inclusiveLeft, but for the right side.
 * <li>atomic: boolean. Atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible to place the
 * cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different meaning-they will prevent the
 * cursor from being placed respectively directly before and directly after the range.
 * <li>collapsed: boolean. Collapsed ranges do not show up in the display. Setting a range to be collapsed will automatically
 * make it atomic.
 * <li>clearOnEnter: boolean. When enabled, will cause the mark to clear itself whenever the cursor enters its range. This is
 * mostly useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event fired
 * on the range handle can be used to be notified when this happens.
 * <li>clearWhenEmpty: boolean. Determines whether the mark is automatically cleared when it becomes empty. Default is true.<br>
 * <li>replacedWith: Element. Use a given node to display this range. Implies both collapsed and atomic. The given DOM node must
 * be an inline element (as opposed to a block element).
 * <li>handleMouseEvents: boolean. When replacedWith is given, this determines whether the editor will capture mouse and drag
 * events occurring in this widget. Default is false-the events will be left alone for the default browser handler, or specific
 * handlers on the widget, to capture.
 * <li>readOnly: boolean. A read-only span can, as long as it is not cleared, not be modified except by calling setValue to
 * reset the whole document. Note: adding a read-only span currently clears the undo history of the editor, because existing
 * undo events being partially nullified by read-only spans would corrupt the history (in the current implementation).
 * <li>addToHistory: boolean. When set to true (default is false), adding this marker will create an event in the undo history
 * that can be individually undone (clearing the marker).
 * <li>startStyle: string. Can be used to specify an extra CSS class to be applied to the leftmost span that is part of the
 * marker.
 * <li>endStyle: string. Equivalent to startStyle, but for the rightmost span.<br>
 * <li>css: string. A string of CSS to be applied to the covered text. For example "color: #fe3".<br>
 * <li>attributes: object. When given, add the attributes in the given object to the elements created for the marked text.
 * Adding class or style attributes this way is not supported.
 * <li>shared: boolean. When the target document is linked to other documents, you can set shared to true to make the marker
 * appear in all documents. By default, a marker appears only in its target document.
 * </ul>
 * <br>
 * For bookmark, these are properties to manage:<br>
 * <ul>
 * <li>widget: Element. Can be used to display a DOM node at the current location of the bookmark (analogous to the replacedWith
 * option to markText).<br>
 * <li>insertLeft: boolean. By default, text typed when the cursor is on top of the bookmark will end up to the right of the
 * bookmark. Set this option to true to make it go to the left instead.<br>
 * <li>shared: boolean. See the corresponding option to markText.<br>
 * <li>handleMouseEvents: boolean.As with markText, this determines whether mouse events on the widget inserted for this
 * bookmark are handled by CodeMirror. The default is false.
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 */
public class TextMarkerOptions extends BaseEntity {

	/**
	 * Default value of CSS style classname, {@link UndefinedValues#STRING}.
	 */
	public static final String DEFAULT_CLASS_NAME = UndefinedValues.STRING;

	/**
	 * Default value whether text inserted on the left of the marker will end up inside or outside of it,
	 * {@value DEFAULT_INCLUSIVE_LEFT}.
	 */
	public static final boolean DEFAULT_INCLUSIVE_LEFT = false;

	/**
	 * Default value whether text inserted on the right of the marker will end up inside or outside of it,
	 * {@value DEFAULT_INCLUSIVE_RIGHT}.
	 */
	public static final boolean DEFAULT_INCLUSIVE_RIGHT = false;

	/**
	 * Default value if atomic ranges act as a single unit when cursor movement is concerned, {@value DEFAULT_ATOMIC}.
	 */
	public static final boolean DEFAULT_ATOMIC = false;

	/**
	 * Default value id collapsed ranges do not show up in the display, {@value DEFAULT_COLLAPSED}.
	 */
	public static final boolean DEFAULT_COLLAPSED = false;

	/**
	 * Default value if will cause the mark to clear itself whenever the cursor enters its range,
	 * {@value DEFAULT_CLEAR_ON_ENTER}.
	 */
	public static final boolean DEFAULT_CLEAR_ON_ENTER = false;

	/**
	 * Default value whether the mark is automatically cleared when it becomes empty, {@value DEFAULT_CLEAR_WHEN_EMPTY}.
	 */
	public static final boolean DEFAULT_CLEAR_WHEN_EMPTY = true;

	/**
	 * Default value of a element to use to display this range, <code>null</code>.
	 */
	public static final Element DEFAULT_REPLACE_WITH = null;

	/**
	 * Default value whether the editor will capture mouse and drag events occurring in this widget,
	 * {@value DEFAULT_HANDLE_MOUSE_EVENTS}.
	 */
	public static final boolean DEFAULT_HANDLE_MOUSE_EVENTS = false;

	/**
	 * Default value if a read-only span can, as long as it is not cleared, not be modified except by calling
	 * <code>setValue()</code> to reset the whole document, {@value DEFAULT_READ_ONLY}.
	 */
	public static final boolean DEFAULT_READ_ONLY = false;

	/**
	 * Default value if adding this marker will create an event in the undo history that can be individually undone (clearing
	 * the marker), {@value DEFAULT_ADD_TO_HISTORY}.
	 */
	public static final boolean DEFAULT_ADD_TO_HISTORY = false;

	/**
	 * Default value which can be used to specify an extra CSS class to be applied to the leftmost span that is part of the
	 * marker, {@link UndefinedValues#STRING}.
	 */
	public static final String DEFAULT_START_STYLE = UndefinedValues.STRING;

	/**
	 * Default value which can be used to specify an extra CSS class to be applied to the rightmost span that is part of the
	 * marker, {@link UndefinedValues#STRING}.
	 */
	public static final String DEFAULT_END_STYLE = UndefinedValues.STRING;

	/**
	 * Default value of CSS to be applied to the covered text, {@link UndefinedValues#STRING}.
	 */
	public static final String DEFAULT_CSS = UndefinedValues.STRING;

	/**
	 * Default value of a DOM node at the current location of the bookmark, <code>null</code>.
	 */
	public static final Element DEFAULT_WIDGET = null;

	/**
	 * Default value of a attributes value of element created for the marked text, <code>null</code>.
	 */
	public static final TextMarkerAttributes DEFAULT_ATTRIBUTES = null;

	/**
	 * Default value if the target document is linked to other documents, you can set shared to true to make the marker appear
	 * in all documents, {@value DEFAULT_SHARED}.
	 */
	public static final boolean DEFAULT_SHARED = false;

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

	// type of marker
	private final TextMarkerType type;

	/**
	 * Creates new marker options, using {@link TextMarkerType#RANGE} as type.
	 */
	public TextMarkerOptions() {
		this(TextMarkerType.RANGE);
	}

	/**
	 * Creates new marker options, using the marker type to configure.
	 * 
	 * @param type marker type of this options.
	 */
	public TextMarkerOptions(TextMarkerType type) {
		// creates always an empty options
		super();
		// checks if type is consistent
		if (type != null) {
			// stores type
			this.type = type;
		} else {
			// if not consistent, default is RANGE
			this.type = TextMarkerType.RANGE;
		}
	}

	/**
	 * Creates a container with a native object instance.<br>
	 * Not visible because must be created internally by the document.
	 * 
	 * @param nativeObject a native object instance
	 */
	TextMarkerOptions(NativeTextMarker nativeObject) {
		super(nativeObject);
		// sets the type
		this.type = getValue(Property.TYPE, TextMarkerType.class, TextMarkerType.RANGE);
	}

	/**
	 * Returns the text marker type.
	 * 
	 * @return the text marker type
	 */
	public final TextMarkerType getType() {
		return type;
	}

	/**
	 * Returns <code>true</code> if the text marker represents a bookmark.
	 * 
	 * @return <code>true</code> if the text marker represents a bookmark
	 */
	public final boolean isBookmark() {
		return TextMarkerType.BOOKMARK.equals(type);
	}

	/**
	 * Returns <code>true</code> if the text marker represents a normal text marker.
	 * 
	 * @return <code>true</code> if the text marker represents a normal text marker
	 */
	public final boolean isRange() {
		return TextMarkerType.RANGE.equals(type);
	}

	/**
	 * Returns a CSS class to the marked stretch of text.
	 * 
	 * @return a CSS class to the marked stretch of text
	 */
	public final String getClassName() {
		return getValue(Property.CLASS_NAME, DEFAULT_CLASS_NAME);
	}

	/**
	 * Sets a CSS class to the marked stretch of text.
	 * 
	 * @param className a CSS class to the marked stretch of text
	 */
	public final void setClassName(String className) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.CLASS_NAME, className);
		}
	}

	/**
	 * Returns <code>true</code> whether text inserted on the left of the marker will end up inside or outside of it.
	 * 
	 * @return <code>true</code> whether text inserted on the left of the marker will end up inside or outside of it
	 */
	public final boolean isInclusiveLeft() {
		return getValue(Property.INCLUSIVE_LEFT, DEFAULT_INCLUSIVE_LEFT);
	}

	/**
	 * Sets <code>true</code> whether text inserted on the left of the marker will end up inside or outside of it.
	 * 
	 * @param inclusiveLeft <code>true</code> whether text inserted on the left of the marker will end up inside or outside of
	 *            it
	 */
	public final void setInclusiveLeft(boolean inclusiveLeft) {
		setValue(Property.INCLUSIVE_LEFT, inclusiveLeft);
	}

	/**
	 * Returns <code>true</code> whether text inserted on the right of the marker will end up inside or outside of it.
	 * 
	 * @return <code>true</code> whether text inserted on the right of the marker will end up inside or outside of it
	 */
	public final boolean isInclusiveRight() {
		return getValue(Property.INCLUSIVE_RIGHT, DEFAULT_INCLUSIVE_RIGHT);
	}

	/**
	 * Sets <code>true</code> whether text inserted on the right of the marker will end up inside or outside of it.
	 * 
	 * @param inclusiveRight <code>true</code> whether text inserted on the right of the marker will end up inside or outside of
	 *            it
	 */
	public final void setInclusiveRight(boolean inclusiveRight) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.INCLUSIVE_RIGHT, inclusiveRight);
		}
	}

	/**
	 * Returns <code>true</code> if atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible
	 * to place the cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different meaning-they will
	 * prevent the cursor from being placed respectively directly before and directly after the range.
	 * 
	 * @return <code>true</code> if atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible
	 *         to place the cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different
	 *         meaning-they will prevent the cursor from being placed respectively directly before and directly after the range.
	 */
	public final boolean isAtomic() {
		return getValue(Property.ATOMIC, DEFAULT_ATOMIC);
	}

	/**
	 * Sets <code>true</code> if atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible to
	 * place the cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different meaning-they will
	 * prevent the cursor from being placed respectively directly before and directly after the range.
	 * 
	 * @param atomic <code>true</code> if atomic ranges act as a single unit when cursor movement is concerned-i.e. it is
	 *            impossible to place the cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a
	 *            different meaning-they will prevent the cursor from being placed respectively directly before and directly
	 *            after the range.
	 */
	public final void setAtomic(boolean atomic) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.ATOMIC, atomic);
		}
	}

	/**
	 * Returns <code>true</code> if collapsed ranges do not show up in the display. Setting a range to be collapsed will
	 * automatically make it atomic.
	 * 
	 * @return <code>true</code> if collapsed ranges do not show up in the display. Setting a range to be collapsed will
	 *         automatically make it atomic
	 */
	public final boolean isCollapsed() {
		return getValue(Property.COLLAPSED, DEFAULT_COLLAPSED);
	}

	/**
	 * Sets <code>true</code> if collapsed ranges do not show up in the display. Setting a range to be collapsed will
	 * automatically make it atomic.
	 * 
	 * @param collapsed <code>true</code> if collapsed ranges do not show up in the display. Setting a range to be collapsed
	 *            will automatically make it atomic
	 */
	public final void setCollapsed(boolean collapsed) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.COLLAPSED, collapsed);
		}
	}

	/**
	 * Returns <code>true</code>, will cause the mark to clear itself whenever the cursor enters its range. This is mostly
	 * useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event fired on
	 * the range handle can be used to be notified when this happens.
	 * 
	 * @return <code>true</code>, will cause the mark to clear itself whenever the cursor enters its range. This is mostly
	 *         useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event
	 *         fired on the range handle can be used to be notified when this happens
	 */
	public final boolean isClearOnEnter() {
		return getValue(Property.CLEAR_ON_ENTER, DEFAULT_CLEAR_ON_ENTER);
	}

	/**
	 * Sets <code>true</code>, will cause the mark to clear itself whenever the cursor enters its range. This is mostly useful
	 * for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event fired on the
	 * range handle can be used to be notified when this happens.
	 * 
	 * @param clearOnEnter <code>true</code>, will cause the mark to clear itself whenever the cursor enters its range. This is
	 *            mostly useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The
	 *            "clear" event fired on the range handle can be used to be notified when this happens
	 */
	public final void setClearOnEnter(boolean clearOnEnter) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.CLEAR_ON_ENTER, clearOnEnter);
		}
	}

	/**
	 * Returns <code>true</code> whether the mark is automatically cleared when it becomes empty.
	 * 
	 * @return <code>true</code> whether the mark is automatically cleared when it becomes empty
	 */
	public final boolean isClearWhenEmpty() {
		return getValue(Property.CLEAR_WHEN_EMPTY, DEFAULT_CLEAR_WHEN_EMPTY);
	}

	/**
	 * Sets <code>true</code> whether the mark is automatically cleared when it becomes empty.
	 * 
	 * @param clearWhenEmpty <code>true</code> whether the mark is automatically cleared when it becomes empty
	 */
	public final void setClearWhenEmpty(boolean clearWhenEmpty) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.CLEAR_ON_ENTER, clearWhenEmpty);
		}
	}

	/**
	 * Returns a given node to display this range. Implies both collapsed and atomic. The given DOM node must be an inline
	 * element (as opposed to a block element).
	 * 
	 * @return a given node to display this range. Implies both collapsed and atomic. The given DOM node must be an inline
	 *         element (as opposed to a block element)
	 */
	public final Element getReplacedWith() {
		return getValue(Property.REPLACE_WITH, DEFAULT_REPLACE_WITH);
	}

	/**
	 * Sets a given node to display this range. Implies both collapsed and atomic. The given DOM node must be an inline element
	 * (as opposed to a block element)
	 * 
	 * @param replacedWith a given node to display this range. Implies both collapsed and atomic. The given DOM node must be an
	 *            inline element (as opposed to a block element)
	 */
	public final void setReplacedWith(Element replacedWith) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.REPLACE_WITH, replacedWith);
		}
	}

	/**
	 * Returns <code>true</code> when replacedWith is given, this determines whether the editor will capture mouse and drag
	 * events occurring in this widget. Default is false-the events will be left alone for the default browser handler, or
	 * specific handlers on the widget, to capture
	 * 
	 * @return <code>true</code> when replacedWith is given, this determines whether the editor will capture mouse and drag
	 *         events occurring in this widget. Default is false-the events will be left alone for the default browser handler,
	 *         or specific handlers on the widget, to capture
	 */
	public final boolean isHandleMouseEvents() {
		return getValue(Property.HANDLE_MOUSE_EVENTS, DEFAULT_HANDLE_MOUSE_EVENTS);
	}

	/**
	 * Sets <code>true</code> when replacedWith is given, this determines whether the editor will capture mouse and drag events
	 * occurring in this widget. Default is false-the events will be left alone for the default browser handler, or specific
	 * handlers on the widget, to capture.
	 * 
	 * @param handleMouseEvents <code>true</code> when replacedWith is given, this determines whether the editor will capture
	 *            mouse and drag events occurring in this widget. Default is false-the events will be left alone for the default
	 *            browser handler, or specific handlers on the widget, to capture
	 */
	public final void setHandleMouseEvents(boolean handleMouseEvents) {
		setValue(Property.HANDLE_MOUSE_EVENTS, handleMouseEvents);
	}

	/**
	 * Returns <code>true</code> if span can, as long as it is not cleared, not be modified except by calling setValue to reset
	 * the whole document. Note: adding a read-only span currently clears the undo history of the editor, because existing undo
	 * events being partially nullified by read-only spans would corrupt the history (in the current implementation).
	 * 
	 * @return <code>true</code> if span can, as long as it is not cleared, not be modified except by calling setValue to reset
	 *         the whole document. Note: adding a read-only span currently clears the undo history of the editor, because
	 *         existing undo events being partially nullified by read-only spans would corrupt the history (in the current
	 *         implementation)
	 */
	public final boolean isReadOnly() {
		return getValue(Property.READ_ONLY, DEFAULT_READ_ONLY);
	}

	/**
	 * Sets <code>true</code> <code>true</code> if span can, as long as it is not cleared, not be modified except by calling
	 * setValue to reset the whole document. Note: adding a read-only span currently clears the undo history of the editor,
	 * because existing undo events being partially nullified by read-only spans would corrupt the history (in the current
	 * implementation).
	 * 
	 * @param readOnly <code>true</code> if span can, as long as it is not cleared, not be modified except by calling setValue
	 *            to reset the whole document. Note: adding a read-only span currently clears the undo history of the editor,
	 *            because existing undo events being partially nullified by read-only spans would corrupt the history (in the
	 *            current implementation)
	 */
	public final void setReadOnly(boolean readOnly) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.READ_ONLY, readOnly);
		}
	}

	/**
	 * Returns <code>true</code> <code>true</code> to add this marker will create an event in the undo history that can be
	 * individually undone (clearing the marker).
	 * 
	 * @return <code>true</code> to add this marker will create an event in the undo history that can be individually undone
	 *         (clearing the marker)
	 */
	public final boolean isAddToHistory() {
		return getValue(Property.ADD_TO_HISTORY, DEFAULT_ADD_TO_HISTORY);
	}

	/**
	 * Sets <code>true</code> to add this marker will create an event in the undo history that can be individually undone
	 * (clearing the marker).
	 * 
	 * @param addToHistory <code>true</code> to add this marker will create an event in the undo history that can be
	 *            individually undone (clearing the marker)
	 */
	public final void setAddToHistory(boolean addToHistory) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.ADD_TO_HISTORY, addToHistory);
		}
	}

	/**
	 * Returns an extra CSS class to be applied to the leftmost span that is part of the marker.
	 * 
	 * @return an extra CSS class to be applied to the leftmost span that is part of the marker.
	 */
	public final String getStartStyle() {
		return getValue(Property.START_STYLE, DEFAULT_START_STYLE);
	}

	/**
	 * Sets an extra CSS class to be applied to the leftmost span that is part of the marker.
	 * 
	 * @param startStyle an extra CSS class to be applied to the leftmost span that is part of the marker.
	 */
	public final void setStartStyle(String startStyle) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.START_STYLE, startStyle);
		}
	}

	/**
	 * Returns an extra CSS class to be applied to the rightmost span that is part of the marker.
	 * 
	 * @return an extra CSS class to be applied to the rightmost span that is part of the marker
	 */
	public final String getEndStyle() {
		return getValue(Property.END_STYLE, DEFAULT_END_STYLE);
	}

	/**
	 * Sets an extra CSS class to be applied to the rightmost span that is part of the marker
	 * 
	 * @param endStyle an extra CSS class to be applied to the rightmost span that is part of the marker
	 */
	public final void setEndStyle(String endStyle) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.END_STYLE, endStyle);
		}
	}

	/**
	 * Returns a string of CSS to be applied to the covered text.
	 * 
	 * @return a string of CSS to be applied to the covered text
	 */
	public final String getCss() {
		return getValue(Property.CSS, DEFAULT_CSS);
	}

	/**
	 * Sets a string of CSS to be applied to the covered text. For example "color: #fe3".
	 * 
	 * @param css a string of CSS to be applied to the covered text
	 */
	public final void setCss(String css) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.CSS, css);
		}
	}

	/**
	 * Returns the attributes to the elements created for the marked text or <code>null</code> if not set.
	 * 
	 * @return the attributes to the elements created for the marked text or <code>null</code> if not set
	 */
	public final TextMarkerAttributes getAttributes() {
		return getValue(Property.ATTRIBUTES, DEFAULT_ATTRIBUTES);
	}

	/**
	 * Sets the attributes to the elements created for the marked text.
	 * 
	 * @param attributes the attributes to the elements created for the marked text
	 */
	public final void setAttributes(TextMarkerAttributes attributes) {
		// checks if type is range (valid only that)
		if (isRange()) {
			setValue(Property.ATTRIBUTES, attributes);
		}
	}

	/**
	 * Returns <code>true</code> if you can set shared to true to make the marker appear in all documents. By default, a marker
	 * appears only in its target document.
	 * 
	 * @return <code>true</code> if you can set shared to true to make the marker appear in all documents. By default, a marker
	 *         appears only in its target document
	 */
	public final boolean isShared() {
		return getValue(Property.SHARED, DEFAULT_SHARED);
	}

	/**
	 * Sets <code>true</code> if you can set shared to true to make the marker appear in all documents. By default, a marker
	 * appears only in its target document.
	 * 
	 * @param shared <code>true</code> if you can set shared to true to make the marker appear in all documents. By default, a
	 *            marker appears only in its target document
	 */
	public final void setShared(boolean shared) {
		setValue(Property.SHARED, shared);
	}

	/**
	 * Returns the DOM element to display at the current location of the bookmark.
	 * 
	 * @return the DOM element to display at the current location of the bookmark
	 */
	public final Element getWidget() {
		return getValue(Property.WIDGET, DEFAULT_WIDGET);
	}

	/**
	 * Sets the DOM element to display at the current location of the bookmark.
	 * 
	 * @param widget the DOM element to display at the current location of the bookmark
	 */
	public final void setWidget(Element widget) {
		// checks if type is bookmark (valid only that)
		if (isBookmark()) {
			setValue(Property.WIDGET, widget);
		}
	}
}
