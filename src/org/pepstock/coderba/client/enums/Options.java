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
package org.pepstock.coderba.client.enums;

import org.pepstock.coderba.client.commons.Key;

/**
 * Name of all properties of Code Mirror options object (configuration).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum Options implements Key
{
	ADD_MODE_CLASS("addModeClass"),
	ALLOW_DROP_FILE_TYPES("allowDropFileTypes"),
	AUTOCAPITALIZE("autocapitalize"),
	AUTOCORRECT("autocorrect"),
	AUTOFOCUS("autofocus"),
	CONFIGURE_MOUSE("configureMouse"),
	COVER_GUTTER_NEXT_TO_SCROLLBAR("coverGutterNextToScrollbar"),
	CURSOR_BLINK_RATE("cursorBlinkRate"),
	CURSOR_HEIGHT("cursorHeight"),
	CURSOR_SCROLL_MARGIN("cursorScrollMargin"),
	DIRECTION("direction"),
	DRAG_DROP("dragDrop"),
	ELECTRIC_CHARS("electricChars"),
	EXTRA_KEYS("extraKeys"),
	FIRST_LINE_NUMBER("firstLineNumber"),
	FIXED_GUTTER("fixedGutter"),
	FLATTEN_SPANS("flattenSpans"),
	GUTTERS("gutters"),
	HISTORY_EVENT_DELAY("historyEventDelay"),
	INDENT_UNIT("indentUnit"),
	INDENT_WITH_TABS("indentWithTabs"),
	INPUT_STYLE("inputStyle"),
	KEY_MAP("keyMap"),
	LINE_NUMBER_FORMATTER("lineNumberFormatter"),
	LINE_NUMBERS("lineNumbers"),
	LINE_SEPARATOR("lineSeparator"),
	LINE_WISE_COPY_CUT("lineWiseCopyCut"),
	LINE_WRAPPING("lineWrapping"),
	MAX_HIGHLIGHT_LENGTH("maxHighlightLength"),
	MODE("mode"),
	PASTE_LINES_PER_SELECTION("pasteLinesPerSelection"),
	PHRASES("phrases"),
	POLL_INTERVAL("pollInterval"),
	READ_ONLY("readOnly"),
	RESET_SELECTION_ON_CONTEXT_MENU("resetSelectionOnContextMenu"),
	RTL_MOVE_VISUALLY("rtlMoveVisually"),
	SCROLLBAR_STYLE("scrollbarStyle"),
	SELECTIONS_MAY_TOUCH("selectionsMayTouch"),
	SHOW_CURSOR_WHEN_SELECTING("showCursorWhenSelecting"),
	SMART_INDENT("smartIndent"),
	SPECIAL_CHAR_PLACEHOLDER("specialCharPlaceholder"),
	SPECIAL_CHARS("specialChars"),
	SPELLCHECK("spellcheck"),
	TAB_SIZE("tabSize"),
	TABINDEX("tabindex"),
	THEME("theme"),
	UNDO_DEPTH("undoDepth"),
	VALUE("value"),
	VIEWPORT_MARGIN("viewportMargin"),
	WORK_DELAY("workDelay"),
	WORK_TIME("workTime");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use into native object.
	 * 
	 * @param value value of property name
	 */
	private Options(String value) {
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
