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
package org.pepstock.coderba.client;

import java.util.List;

import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.entities.Phrases;
import org.pepstock.coderba.client.enums.Direction;
import org.pepstock.coderba.client.enums.InputStyle;
import org.pepstock.coderba.client.enums.ReadOnly;
import org.pepstock.coderba.client.keymaps.KeyMapDefault;
import org.pepstock.coderba.client.utils.RegExp;

/**
 * All methods to get CodeMirror options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsDefaultOptions {

	/**
	 * When set (default is null) only files whose type is in the array can be dropped into the editor.<br>
	 * The strings should be MIME types, and will be checked against the type of the File object as reported by the browser.
	 * 
	 * @return only files whose type is in the array can be dropped into the editor
	 */
	List<String> getAllowDropFileTypes();

	/**
	 * Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be disabled. A negative value
	 * hides the cursor entirely.
	 * 
	 * @return Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be disabled. A
	 *         negative value hides the cursor entirely.
	 */
	int getCursorBlinkRate();

	/**
	 * Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 * 
	 * @return Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 */
	int getCursorHeight();

	/**
	 * How much extra space to always keep above and below the cursor when approaching the top or bottom of the visible view in
	 * a scrollable document.
	 * 
	 * @return How much extra space to always keep above and below the cursor when approaching the top or bottom of the visible
	 *         view in a scrollable document.
	 */
	int getCursorScrollMargin();

	/**
	 * Flips overall layout and selects base paragraph direction to be left-to-right or right-to-left.
	 * 
	 * @return base paragraph direction to be left-to-right or right-to-left
	 */
	Direction getDirection();

	/**
	 * Can be used to specify extra key bindings for the editor, alongside the ones defined by keyMap. Should be either null, or
	 * a valid key map value.
	 * 
	 * @return extra key bindings for the editor
	 */
	NativeObject getExtraKeys();

	/**
	 * At which number to start counting lines.
	 * 
	 * @return at which number to start counting lines
	 */
	int getFirstLineNumber();

	/**
	 * Can be used to add extra gutters (beyond or instead of the line number gutter). Should be an array of CSS class names
	 * 
	 * @return a list of CSS class names
	 */
	List<String> getGutters();

	/**
	 * The period of inactivity (in milliseconds) that will cause a new history event to be started when typing or deleting.
	 * 
	 * @return The period of inactivity (in milliseconds) that will cause a new history event to be started when typing or
	 *         deleting.
	 */
	int getHistoryEventDelay();

	/**
	 * How many spaces a block (whatever that means in the edited language) should be indented.
	 * 
	 * @return amount of spaces for indenting
	 */
	int getIndentUnit();

	/**
	 * Selects the way it handles input and focus
	 * 
	 * @return the way it handles input and focus.
	 */
	InputStyle getInputStyle();

	/**
	 * Returns the key map to use.
	 * 
	 * @return the key map to use
	 */
	KeyMap getKeyMap();

	/**
	 * Returns the language to apply to the editor
	 * 
	 * @return the language to apply to the editor
	 */
	Language getLanguage();

	/**
	 * Explicitly set the line separator for the editor.<br>
	 * By default (value null), the document will be split on CRLFs as well as lone CRs and LFs, and a single LF will be used as
	 * line separator in all output (such as getValue).<br>
	 * When a specific string is given, lines will only be split on that string, and output will, by default, use that same
	 * separator.
	 * 
	 * @return the line separator for the editor
	 */
	String getLineSeparator();

	/**
	 * When highlighting long lines, in order to stay responsive, the editor will give up and simply style the rest of the line
	 * as plain text when it reaches a certain position. The default is 10 000.
	 * 
	 * @return When highlighting long lines, in order to stay responsive, the editor will give up and simply style the rest of
	 *         the line as plain text when it reaches a certain position. The default is 10 000.
	 */
	int getMaxHighlightLength();

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation. This option determines the return value of that method. When it is null or an object that doesn't have a
	 * property named by the input string, that string is returned. Otherwise, the value of the property corresponding to that
	 * string is returned.
	 * 
	 * @return When it is null or an object that doesn't have a property named by the input string, that string is returned.
	 *         Otherwise, the value of the property corresponding to that string is returned.
	 */
	Phrases getPhrases();

	/**
	 * Indicates how quickly CodeMirror should poll its input textarea for changes (when focused). Most input is captured by
	 * events, but some things, like IME input on some browsers, don't generate events that allow CodeMirror to properly detect
	 * it. Thus, it polls.
	 * 
	 * @return Indicates how quickly CodeMirror should poll its input textarea for changes (when focused)
	 */
	int getPollInterval();

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @return This disables editing of the editor content by the user
	 */
	ReadOnly getReadOnly();

	/**
	 * Chooses a scrollbar implementation. The default is "native", showing native scrollbars.<br>
	 * The core library also provides the "null" style, which completely hides the scrollbars.
	 * 
	 * @return a scrollbar implementation style
	 */
	String getScrollbarStyle();

	/**
	 * A regular expression used to determine which characters should be replaced by a special placeholder.
	 * 
	 * @return A regular expression used to determine which characters should be replaced by a special placeholder.
	 */
	RegExp getSpecialChars();

	/**
	 * The tab index to assign to the editor. If not given({@link UndefinedValues#INTEGER}), no tab index will be assigned.
	 * 
	 * @return The tab index to assign to the editor. If not given({@link UndefinedValues#INTEGER}), no tab index will be
	 *         assigned.
	 */
	int getTabindex();

	/**
	 * The width of a tab character.
	 * 
	 * @return width of a tab character
	 */
	int getTabSize();

	/**
	 * Returns the theme to style the editor with. The default is {@link KeyMapDefault}, for which colors are included in
	 * <code>codemirror.css</code>.
	 * 
	 * @return the theme to style the editor with
	 */
	Theme getTheme();

	/**
	 * The maximum number of undo levels that the editor stores.
	 * 
	 * @return The maximum number of undo levels that the editor stores.
	 */
	int getUndoDepth();

	/**
	 * The starting value of the editor
	 * 
	 * @return The starting value of the editor
	 */
	String getValue();

	/**
	 * Specifies the amount of lines that are rendered above and below the part of the document that's currently scrolled into
	 * view. This affects the amount of updates needed when scrolling, and the amount of work that such an update does.
	 * 
	 * @return the amount of lines that are rendered above and below the part of the document that's currently scrolled into
	 *         view
	 */
	int getViewportMargin();

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @return Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout
	 *         to sleep for workDelay milliseconds.
	 */
	int getWorkDelay();

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @return Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout
	 *         to sleep for workDelay milliseconds.
	 */
	int getWorkTime();

	/**
	 * When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner) mode that produced
	 * it.
	 * 
	 * @return When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner) mode that
	 *         produced it
	 */
	boolean isAddModeClass();

	/**
	 * Specifies whether or not autocapitalization will be enabled on the input.
	 * 
	 * @return Specifies whether or not autocapitalization will be enabled on the input.
	 */
	boolean isAutocapitalize();

	/**
	 * Specifies whether or not autocorrect will be enabled on the input.
	 * 
	 * @return Specifies whether or not autocorrect will be enabled on the input.
	 */
	boolean isAutocorrect();

	/**
	 * Can be used to make CodeMirror focus itself on initialization.
	 * 
	 * @return Can be used to make CodeMirror focus itself on initialization.
	 */
	boolean isAutofocus();

	/**
	 * When "fixedGutter" is on, and there is a horizontal scrollbar, by default the gutter will be visible to the left of this
	 * scrollbar.
	 * 
	 * @return <code>true</code> if the gutter will be visible to the left of this scrollbar
	 */
	boolean isCoverGutterNextToScrollbar();

	/**
	 * Controls whether drag-and-drop is enabled.
	 * 
	 * @return Controls whether drag-and-drop is enabled. On by default.
	 */
	boolean isDragDrop();

	/**
	 * Configures whether the editor should re-indent the current line when a character is typed that might change its proper
	 * indentation (only works if the mode supports indentation).
	 * 
	 * @return <code>true</code> if the editor should re-indent the current line when a character is typed
	 */
	boolean isElectricChars();

	/**
	 * Determines whether the gutter scrolls along with the content horizontally (false) or whether it stays fixed during
	 * horizontal scrolling
	 * 
	 * @return determines whether the gutter scrolls along with the content horizontally (false) or whether it stays fixed
	 *         during horizontal scrolling
	 */
	boolean isFixedGutter();

	/**
	 * By default, CodeMirror will combine adjacent tokens into a single span if they have the same class. This will result in a
	 * simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded corners), this will change the way
	 * the document looks. You can set this option to false to disable this behavior.
	 * 
	 * @return By default, CodeMirror will combine adjacent tokens into a single span if they have the same class. This will
	 *         result in a simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded corners), this
	 *         will change the way the document looks. You can set this option to false to disable this behavior.
	 */
	boolean isFlattenSpans();

	/**
	 * Whether, when indenting, the first N*tabSize spaces should be replaced by N tabs.
	 * 
	 * @return <code>true</code> the first N*tabSize spaces should be replaced by N tabs
	 */
	boolean isIndentWithTabs();

	/**
	 * Whether to show line numbers to the left of the editor.
	 * 
	 * @return <code>true</code> whether to show line numbers to the left of the editor
	 */
	boolean isLineNumbers();

	/**
	 * When enabled, which is the default, doing copy or cut when there is no selection will copy or cut the whole lines that
	 * have cursors on them.
	 * 
	 * @return When enabled, which is the default, doing copy or cut when there is no selection will copy or cut the whole lines
	 *         that have cursors on them.
	 */
	boolean isLineWiseCopyCut();

	/**
	 * Whether it should scroll or wrap for long lines.
	 * 
	 * @return <code>true</code> whether it should scroll or wrap for long lines
	 */
	boolean isLineWrapping();

	/**
	 * When pasting something from an external source (not from the editor itself), if the number of lines matches the number of
	 * selection, CodeMirror will by default insert one line per selection. You can set this to false to disable that behavior.
	 * 
	 * @return When pasting something from an external source (not from the editor itself), if the number of lines matches the
	 *         number of selection, CodeMirror will by default insert one line per selection. You can set this to false to
	 *         disable that behavior.
	 */
	boolean isPasteLinesPerSelection();

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @return This disables editing of the editor content by the user
	 */
	boolean isReadOnly();

	/**
	 * Controls whether, when the context menu is opened with a click outside of the current selection, the cursor is moved to
	 * the point of the click.
	 * 
	 * @return Controls whether, when the context menu is opened with a click outside of the current selection, the cursor is
	 *         moved to the point of the click.
	 */
	boolean isResetSelectionOnContextMenu();

	/**
	 * Determines whether horizontal cursor movement through right-to-left (Arabic, Hebrew) text is visual (pressing the left
	 * arrow moves the cursor left) or logical (pressing the left arrow moves to the next lower index in the string, which is
	 * visually right in right-to-left text).
	 * 
	 * @return whether horizontal cursor movement through right-to-left text is visual or logical
	 */
	boolean isRtlMoveVisually();

	/**
	 * Determines whether multiple selections are joined as soon as they touch (the default) or only when they overlap (true).
	 * 
	 * @return Determines whether multiple selections are joined as soon as they touch (the default) or only when they overlap
	 *         (true).
	 */
	boolean isSelectionsMayTouch();

	/**
	 * Whether the cursor should be drawn when a selection is active.
	 * 
	 * @return Whether the cursor should be drawn when a selection is active
	 */
	boolean isShowCursorWhenSelecting();

	/**
	 * Whether to use the context-sensitive indentation that the mode provides (or just indent the same as the line before).
	 * 
	 * @return <code>true</code> if to use the context-sensitive indentation
	 */
	boolean isSmartIndent();

	/**
	 * Specifies whether or not spell check will be enabled on the input.
	 * 
	 * @return <code>true</code> whether spell check will be enabled on the input
	 */
	boolean isSpellcheck();

}
