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
package org.pepstock.coderba.client.defaults;

import java.util.Collections;
import java.util.List;

import org.pepstock.coderba.client.KeyMap;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.Theme;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.entities.Phrases;
import org.pepstock.coderba.client.enums.Direction;
import org.pepstock.coderba.client.enums.InputStyle;
import org.pepstock.coderba.client.enums.ReadOnly;
import org.pepstock.coderba.client.keymaps.KeyMapDefault;
import org.pepstock.coderba.client.languages.PlainText;
import org.pepstock.coderba.client.themes.ThemeDefault;
import org.pepstock.coderba.client.utils.RegExp;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class GlobalDefaults implements IsDefaultOptions {

	private static final GlobalDefaults INSTANCE = new GlobalDefaults();

	private static final String DEFAULT_VALUE = "";

	private static final Language DEFAULT_LANGUAGE = PlainText.LANGUAGE;

	private static final Theme DEFAULT_THEME = ThemeDefault.THEME;

	/**
	 * The default is "default", which is the only key map defined in codemirror itself.
	 */
	private static final KeyMap DEFAULT_KEYMAP = KeyMapDefault.INSTANCE;

	private static final int DEFAULT_INDENT_UNIT = 2;

	private static final boolean DEFAULT_SMART_INDENT = true;

	private static final int DEFAULT_TAB_SIZE = 4;

	private static final boolean DEFAULT_INDENT_WITH_TABS = false;

	private static final boolean DEFAULT_ELECTRIC_CHARS = true;

	private static final Direction DEFAULT_DIRECTION = Direction.LEFT_TO_RIGHT;

	/**
	 * The default is false on Windows, and true on other platforms.
	 */
	private static final boolean DEFAULT_RTL_MOVE_VISUALLY = true;

	private static final boolean DEFAULT_LINE_WRAPPING = false;

	private static final boolean DEFAULT_LINE_NUMBERS = false;

	private static final boolean DEFAULT_FIXED_GUTTER = true;

	private static final int DEFAULT_FIRST_LINE_NUMBER = 1;

	private static final String DEFAULT_SCROLLBAR_STYLE = "native";

	private static final boolean DEFAULT_COVER_GUTTER_NEXT_TO_SCROLLBAR = false;

	private static final InputStyle DEFAULT_INPUT_STYLE = InputStyle.TEXT_AREA;

	private static final boolean DEFAULT_READ_ONLY = false;

	private static final boolean DEFAULT_SHOW_CURSOR_WHEN_SELECTING = false;

	private static final boolean DEFAULT_LINE_WISE_COPY_CUT = true;

	private static final boolean DEFAULT_PASTE_LINES_PER_SELECTION = true;

	private static final boolean DEFAULT_SELECTIONS_MAY_TOUCH = false;

	private static final int DEFAULT_UNDO_DEPTH = 200;

	private static final int DEFAULT_HISTORY_EVENT_DELAY = 1250;

	private static final boolean DEFAULT_DRAG_DROP = true;

	/**
	 * Defaults to off. When "fromTextArea" is used, and no explicit value is given for this option, it will be set to true when
	 * either the source textarea is focused, or it has an autofocus attribute and no other element is focused.
	 */
	private static final boolean DEFAULT_AUTOFOCUS = false;

	private static final int DEFAULT_CURSOR_BLINK_RATE = 530;

	private static final int DEFAULT_CURSOR_SCROLL_MARGIN = 0;

	private static final int DEFAULT_CURSOR_HEIGHT = 1;

	private static final boolean DEFAULT_RESET_SELECTION_ON_CONTEXT_MENU = true;

	private static final int DEFAULT_POLL_INTERVAL = 100;

	private static final boolean DEFAULT_FLATTEN_SPANS = true;

	private static final boolean DEFAULT_ADD_MODE_CLASS = false;

	private static final int DEFAULT_MAX_HIGHLIGHT_LENGTH = 10000;

	private static final int DEFAULT_VIEWPORT_MARGIN = 10;

	private static final boolean DEFAULT_SPELLCHECK = false;

	private static final boolean DEFAULT_AUTOCORRECT = false;

	private static final boolean DEFAULT_AUTOCAPITALIZE = false;

	private static final int DEFAULT_WORK_TIME = 200;

	private static final int DEFAULT_WORK_DELAY = 300;

	private static final Phrases DEFAULT_PHRASES = null;

	private static final List<String> DEFAULT_GUTTERS = Collections.emptyList();

	private static final List<String> DEFAULT_ALLOW_DROP_FILE_TYPES = Collections.emptyList();

	/**
	 * To avoid any instantiation
	 */
	private GlobalDefaults() {
		// do nothing
	}

	public static GlobalDefaults get() {
		return INSTANCE;
	}

	/**
	 * The starting value of the editor
	 * 
	 * @return The starting value of the editor
	 */
	@Override
	public String getValue() {
		return DEFAULT_VALUE;
	}

	/**
	 * Returns the language to apply to the editor
	 * 
	 * @return the language to apply to the editor
	 */
	@Override
	public Language getLanguage() {
		return DEFAULT_LANGUAGE;
	}

	/**
	 * Explicitly set the line separator for the editor.<br>
	 * By default (value null), the document will be split on CRLFs as well as lone CRs and LFs, and a single LF will be used as
	 * line separator in all output (such as getValue).<br>
	 * When a specific string is given, lines will only be split on that string, and output will, by default, use that same
	 * separator.
	 * 
	 * @return the line separator for the editor
	 */
	@Override
	public String getLineSeparator() {
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the theme to style the editor with. The default is {@link KeyMapDefault}, for which colors are included in
	 * <code>codemirror.css</code>.
	 * 
	 * @return the theme to style the editor with
	 */
	@Override
	public Theme getTheme() {
		return DEFAULT_THEME;
	}

	/**
	 * How many spaces a block (whatever that means in the edited language) should be indented.
	 * 
	 * @return amount of spaces for indenting
	 */
	@Override
	public int getIndentUnit() {
		return DEFAULT_INDENT_UNIT;
	}

	/**
	 * Whether to use the context-sensitive indentation that the mode provides (or just indent the same as the line before).
	 * 
	 * @return <code>true</code> if to use the context-sensitive indentation
	 */
	@Override
	public boolean isSmartIndent() {
		return DEFAULT_SMART_INDENT;
	}

	/**
	 * The width of a tab character.
	 * 
	 * @return width of a tab character
	 */
	@Override
	public int getTabSize() {
		return DEFAULT_TAB_SIZE;
	}

	/**
	 * Whether, when indenting, the first N*tabSize spaces should be replaced by N tabs.
	 * 
	 * @return <code>true</code> the first N*tabSize spaces should be replaced by N tabs
	 */
	@Override
	public boolean isIndentWithTabs() {
		return DEFAULT_INDENT_WITH_TABS;
	}

	/**
	 * Configures whether the editor should re-indent the current line when a character is typed that might change its proper
	 * indentation (only works if the mode supports indentation).
	 * 
	 * @return <code>true</code> if the editor should re-indent the current line when a character is typed
	 */
	@Override
	public boolean isElectricChars() {
		return DEFAULT_ELECTRIC_CHARS;
	}

	/**
	 * A regular expression used to determine which characters should be replaced by a special placeholder.
	 * 
	 * @return A regular expression used to determine which characters should be replaced by a special placeholder.
	 */
	@Override
	public RegExp getSpecialChars() {
		return null;
	}

	/**
	 * Flips overall layout and selects base paragraph direction to be left-to-right or right-to-left.
	 * 
	 * @return base paragraph direction to be left-to-right or right-to-left
	 */
	@Override
	public Direction getDirection() {
		return DEFAULT_DIRECTION;
	}

	/**
	 * Determines whether horizontal cursor movement through right-to-left (Arabic, Hebrew) text is visual (pressing the left
	 * arrow moves the cursor left) or logical (pressing the left arrow moves to the next lower index in the string, which is
	 * visually right in right-to-left text).
	 * 
	 * @return whether horizontal cursor movement through right-to-left text is visual or logical
	 */
	@Override
	public boolean isRtlMoveVisually() {
		return DEFAULT_RTL_MOVE_VISUALLY;
	}

	/**
	 * Returns the key map to use.
	 * 
	 * @return the key map to use
	 */
	@Override
	public KeyMap getKeyMap() {
		return DEFAULT_KEYMAP;
	}

	/**
	 * Can be used to specify extra key bindings for the editor, alongside the ones defined by keyMap. Should be either null, or
	 * a valid key map value.
	 * 
	 * @return extra key bindings for the editor
	 */
	@Override
	public NativeObject getExtraKeys() {
		return null;
	}

	/**
	 * Whether it should scroll or wrap for long lines.
	 * 
	 * @return <code>true</code> whether it should scroll or wrap for long lines
	 */
	@Override
	public boolean isLineWrapping() {
		return DEFAULT_LINE_WRAPPING;
	}

	/**
	 * Whether to show line numbers to the left of the editor.
	 * 
	 * @return <code>true</code> whether to show line numbers to the left of the editor
	 */
	@Override
	public boolean isLineNumbers() {
		return DEFAULT_LINE_NUMBERS;
	}

	/**
	 * At which number to start counting lines.
	 * 
	 * @return at which number to start counting lines
	 */
	@Override
	public int getFirstLineNumber() {
		return DEFAULT_FIRST_LINE_NUMBER;
	}

	/**
	 * Can be used to add extra gutters (beyond or instead of the line number gutter). Should be an array of CSS class names
	 * 
	 * @return a list of CSS class names
	 */
	@Override
	public List<String> getGutters() {
		return DEFAULT_GUTTERS;
	}

	/**
	 * Determines whether the gutter scrolls along with the content horizontally (false) or whether it stays fixed during
	 * horizontal scrolling
	 * 
	 * @return determines whether the gutter scrolls along with the content horizontally (false) or whether it stays fixed
	 *         during horizontal scrolling
	 */
	@Override
	public boolean isFixedGutter() {
		return DEFAULT_FIXED_GUTTER;
	}

	/**
	 * Chooses a scrollbar implementation. The default is "native", showing native scrollbars.<br>
	 * The core library also provides the "null" style, which completely hides the scrollbars.
	 * 
	 * @return a scrollbar implementation style
	 */
	@Override
	public String getScrollbarStyle() {
		return DEFAULT_SCROLLBAR_STYLE;
	}

	/**
	 * When "fixedGutter" is on, and there is a horizontal scrollbar, by default the gutter will be visible to the left of this
	 * scrollbar.
	 * 
	 * @return <code>true</code> if the gutter will be visible to the left of this scrollbar
	 */
	@Override
	public boolean isCoverGutterNextToScrollbar() {
		return DEFAULT_COVER_GUTTER_NEXT_TO_SCROLLBAR;
	}

	/**
	 * Selects the way it handles input and focus
	 * 
	 * @return the way it handles input and focus.
	 */
	@Override
	public InputStyle getInputStyle() {
		return DEFAULT_INPUT_STYLE;
	}

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @return This disables editing of the editor content by the user
	 */
	@Override
	public boolean isReadOnly() {
		return DEFAULT_READ_ONLY;
	}

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @return This disables editing of the editor content by the user
	 */
	@Override
	public ReadOnly getReadOnly() {
		return (isReadOnly()) ? ReadOnly.TRUE : ReadOnly.FALSE;
	}

	/**
	 * Whether the cursor should be drawn when a selection is active.
	 * 
	 * @return Whether the cursor should be drawn when a selection is active
	 */
	@Override
	public boolean isShowCursorWhenSelecting() {
		return DEFAULT_SHOW_CURSOR_WHEN_SELECTING;
	}

	/**
	 * When enabled, which is the default, doing copy or cut when there is no selection will copy or cut the whole lines that
	 * have cursors on them.
	 * 
	 * @return When enabled, which is the default, doing copy or cut when there is no selection will copy or cut the whole lines
	 *         that have cursors on them.
	 */
	@Override
	public boolean isLineWiseCopyCut() {
		return DEFAULT_LINE_WISE_COPY_CUT;
	}

	/**
	 * When pasting something from an external source (not from the editor itself), if the number of lines matches the number of
	 * selection, CodeMirror will by default insert one line per selection. You can set this to false to disable that behavior.
	 * 
	 * @return When pasting something from an external source (not from the editor itself), if the number of lines matches the
	 *         number of selection, CodeMirror will by default insert one line per selection. You can set this to false to
	 *         disable that behavior.
	 */
	@Override
	public boolean isPasteLinesPerSelection() {
		return DEFAULT_PASTE_LINES_PER_SELECTION;
	}

	/**
	 * Determines whether multiple selections are joined as soon as they touch (the default) or only when they overlap (true).
	 * 
	 * @return Determines whether multiple selections are joined as soon as they touch (the default) or only when they overlap
	 *         (true).
	 */
	@Override
	public boolean isSelectionsMayTouch() {
		return DEFAULT_SELECTIONS_MAY_TOUCH;
	}

	/**
	 * The maximum number of undo levels that the editor stores.
	 * 
	 * @return The maximum number of undo levels that the editor stores.
	 */
	@Override
	public int getUndoDepth() {
		return DEFAULT_UNDO_DEPTH;
	}

	/**
	 * The period of inactivity (in milliseconds) that will cause a new history event to be started when typing or deleting.
	 * 
	 * @return The period of inactivity (in milliseconds) that will cause a new history event to be started when typing or
	 *         deleting.
	 */
	@Override
	public int getHistoryEventDelay() {
		return DEFAULT_HISTORY_EVENT_DELAY;
	}

	/**
	 * The tab index to assign to the editor. If not given({@link UndefinedValues#INTEGER}), no tab index will be assigned.
	 * 
	 * @return The tab index to assign to the editor. If not given({@link UndefinedValues#INTEGER}), no tab index will be
	 *         assigned.
	 */
	@Override
	public int getTabindex() {
		return UndefinedValues.INTEGER;
	}

	/**
	 * Can be used to make CodeMirror focus itself on initialization.
	 * 
	 * @return Can be used to make CodeMirror focus itself on initialization.
	 */
	@Override
	public boolean isAutofocus() {
		return DEFAULT_AUTOFOCUS;
	}

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation. This option determines the return value of that method. When it is null or an object that doesn't have a
	 * property named by the input string, that string is returned. Otherwise, the value of the property corresponding to that
	 * string is returned.
	 * 
	 * @return When it is null or an object that doesn't have a property named by the input string, that string is returned.
	 *         Otherwise, the value of the property corresponding to that string is returned.
	 */
	@Override
	public Phrases getPhrases() {
		return DEFAULT_PHRASES;
	}

	/**
	 * Controls whether drag-and-drop is enabled.
	 * 
	 * @return Controls whether drag-and-drop is enabled. On by default.
	 */
	@Override
	public boolean isDragDrop() {
		return DEFAULT_DRAG_DROP;
	}

	/**
	 * When set (default is null) only files whose type is in the array can be dropped into the editor.<br>
	 * The strings should be MIME types, and will be checked against the type of the File object as reported by the browser.
	 * 
	 * @return only files whose type is in the array can be dropped into the editor
	 */
	@Override
	public List<String> getAllowDropFileTypes() {
		return DEFAULT_ALLOW_DROP_FILE_TYPES;
	}

	/**
	 * Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be disabled. A negative value
	 * hides the cursor entirely.
	 * 
	 * @return Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be disabled. A
	 *         negative value hides the cursor entirely.
	 */
	@Override
	public int getCursorBlinkRate() {
		return DEFAULT_CURSOR_BLINK_RATE;
	}

	/**
	 * How much extra space to always keep above and below the cursor when approaching the top or bottom of the visible view in
	 * a scrollable document.
	 * 
	 * @return How much extra space to always keep above and below the cursor when approaching the top or bottom of the visible
	 *         view in a scrollable document.
	 */
	@Override
	public int getCursorScrollMargin() {
		return DEFAULT_CURSOR_SCROLL_MARGIN;
	}

	/**
	 * Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 * 
	 * @return Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 */
	@Override
	public int getCursorHeight() {
		return DEFAULT_CURSOR_HEIGHT;
	}

	/**
	 * Controls whether, when the context menu is opened with a click outside of the current selection, the cursor is moved to
	 * the point of the click.
	 * 
	 * @return Controls whether, when the context menu is opened with a click outside of the current selection, the cursor is
	 *         moved to the point of the click.
	 */
	@Override
	public boolean isResetSelectionOnContextMenu() {
		return DEFAULT_RESET_SELECTION_ON_CONTEXT_MENU;
	}

	/**
	 * Indicates how quickly CodeMirror should poll its input textarea for changes (when focused). Most input is captured by
	 * events, but some things, like IME input on some browsers, don't generate events that allow CodeMirror to properly detect
	 * it. Thus, it polls.
	 * 
	 * @return Indicates how quickly CodeMirror should poll its input textarea for changes (when focused)
	 */
	@Override
	public int getPollInterval() {
		return DEFAULT_POLL_INTERVAL;
	}

	/**
	 * By default, CodeMirror will combine adjacent tokens into a single span if they have the same class. This will result in a
	 * simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded corners), this will change the way
	 * the document looks. You can set this option to false to disable this behavior.
	 * 
	 * @return By default, CodeMirror will combine adjacent tokens into a single span if they have the same class. This will
	 *         result in a simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded corners), this
	 *         will change the way the document looks. You can set this option to false to disable this behavior.
	 */
	@Override
	public boolean isFlattenSpans() {
		return DEFAULT_FLATTEN_SPANS;
	}

	/**
	 * When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner) mode that produced
	 * it.
	 * 
	 * @return When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner) mode that
	 *         produced it
	 */
	@Override
	public boolean isAddModeClass() {
		return DEFAULT_ADD_MODE_CLASS;
	}

	/**
	 * When highlighting long lines, in order to stay responsive, the editor will give up and simply style the rest of the line
	 * as plain text when it reaches a certain position. The default is 10 000.
	 * 
	 * @return When highlighting long lines, in order to stay responsive, the editor will give up and simply style the rest of
	 *         the line as plain text when it reaches a certain position. The default is 10 000.
	 */
	@Override
	public int getMaxHighlightLength() {
		return DEFAULT_MAX_HIGHLIGHT_LENGTH;
	}

	/**
	 * Specifies the amount of lines that are rendered above and below the part of the document that's currently scrolled into
	 * view. This affects the amount of updates needed when scrolling, and the amount of work that such an update does.
	 * 
	 * @return the amount of lines that are rendered above and below the part of the document that's currently scrolled into
	 *         view
	 */
	@Override
	public int getViewportMargin() {
		return DEFAULT_VIEWPORT_MARGIN;
	}

	/**
	 * Specifies whether or not spellcheck will be enabled on the input.
	 * 
	 * @return Specifies whether or not spellcheck will be enabled on the input.
	 */
	@Override
	public boolean isSpellcheck() {
		return DEFAULT_SPELLCHECK;
	}

	/**
	 * Specifies whether or not autocorrect will be enabled on the input.
	 * 
	 * @return Specifies whether or not autocorrect will be enabled on the input.
	 */
	@Override
	public boolean isAutocorrect() {
		return DEFAULT_AUTOCORRECT;
	}

	/**
	 * Specifies whether or not autocapitalization will be enabled on the input.
	 * 
	 * @return Specifies whether or not autocapitalization will be enabled on the input.
	 */
	@Override
	public boolean isAutocapitalize() {
		return DEFAULT_AUTOCAPITALIZE;
	}

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @return Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout
	 *         to sleep for workDelay milliseconds.
	 */
	@Override
	public int getWorkTime() {
		return DEFAULT_WORK_TIME;
	}

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @return Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout
	 *         to sleep for workDelay milliseconds.
	 */
	@Override
	public int getWorkDelay() {
		return DEFAULT_WORK_DELAY;
	}

}
