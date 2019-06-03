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
package org.pepstock.coderba.client.defaults;

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
import org.pepstock.coderba.client.keymaps.Default;
import org.pepstock.coderba.client.utils.RegExp;

/**
 * All methods to set CodeMirror options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsOptions extends IsDefaultOptions {
	
	/**
	 * The starting value of the editor.
	 * 
	 * @param value The starting value of the editor
	 */
	void setValue(String value);

	/**
	 * Sets the language to apply to the editor.
	 * 
	 * @param language the language to apply to the editor
	 */
	void setLanguage(Language language);

	/**
	 * Explicitly set the line separator for the editor.<br>
	 * By default (value null), the document will be split on CRLFs as well as lone CRs and LFs, and a single LF will be used as
	 * line separator in all output (such as getValue).<br>
	 * When a specific string is given, lines will only be split on that string, and output will, by default, use that same
	 * separator.
	 * 
	 * @param lineSeparator the line separator for the editor
	 */
	void setLineSeparator(String lineSeparator);
	
	/**
	 * Sets the theme to style the editor with. The default is {@link Default}, for which colors are included in
	 * <code>codemirror.css</code>.
	 * 
	 * @param theme the theme to style the editor with
	 */
	void setTheme(Theme theme);

	/**
	 * How many spaces a block (whatever that means in the edited language) should be indented.
	 * 
	 * @param indentUnit amount of spaces for indenting
	 */
	void setIndentUnit(int indentUnit);

	/**
	 * Whether to use the context-sensitive indentation that the mode provides (or just indent the same as the line before).
	 * 
	 * @param smartIndent <code>true</code> if to use the context-sensitive indentation
	 */
	void setSmartIndent(boolean smartIndent);

	/**
	 * The width of a tab character.
	 * 
	 * @param tabSize width of a tab character
	 */
	void setTabSize(boolean tabSize);

	/**
	 * Whether, when indenting, the first N*tabSize spaces should be replaced by N tabs.
	 * 
	 * @param indentWithTabsif <code>true</code> the first N*tabSize spaces should be replaced by N tabs
	 */
	void setIndentWithTabs(boolean indentWithTabs);

	/**
	 * Configures whether the editor should re-indent the current line when a character is typed that might change its proper
	 * indentation (only works if the mode supports indentation).
	 * 
	 * @param electricChars <code>true</code> if the editor should re-indent the current line when a character is typed
	 */
	void setElectricChars(boolean electricChars);

	/**
	 * A regular expression used to determine which characters should be replaced by a special placeholder.
	 * 
	 * @param specialChars A regular expression used to determine which characters should be replaced by a special placeholder.
	 */
	void setSpecialChars(RegExp specialChars);
	
	/**
	 * Flips overall layout and selects base paragraph direction to be left-to-right or right-to-left.
	 * 
	 * @param direction base paragraph direction to be left-to-right or right-to-left
	 */
	void setDirection(Direction direction);

	/**
	 * Determines whether horizontal cursor movement through right-to-left (Arabic, Hebrew) text is visual (pressing the left
	 * arrow moves the cursor left) or logical (pressing the left arrow moves to the next lower index in the string, which is
	 * visually right in right-to-left text).
	 * 
	 * @param rtlMoveVisually whether horizontal cursor movement through right-to-left text is visual or logical
	 */
	void setRtlMoveVisually(boolean rtlMoveVisually);

	/**
	 * Configures the key map to use.
	 * 
	 * @param keyMap the key map to use
	 */
	void setKeyMap(KeyMap keyMap);

	/**
	 * Can be used to specify extra key bindings for the editor, alongside the ones defined by keyMap. Should be either null, or
	 * a valid key map value.
	 * 
	 * @param extraKeys extra key bindings for the editor
	 */
	void setExtraKeys(NativeObject extraKeys);

	/**
	 * Whether it should scroll or wrap for long lines.
	 * 
	 * @param lineWrapping <code>true</code> whether it should scroll or wrap for long lines
	 */
	void setLineWrapping(boolean lineWrapping);

	/**
	 * Whether to show line numbers to the left of the editor.
	 * 
	 * @param lineNumbers <code>true</code> whether to show line numbers to the left of the editor
	 */
	void setLineNumbers(boolean lineNumbers);

	/**
	 * At which number to start counting lines.
	 * 
	 * @param firstLineNumber at which number to start counting lines
	 */
	void setFirstLineNumber(int firstLineNumber);

	/**
	 * Can be used to add extra gutters (beyond or instead of the line number gutter).
	 * 
	 * @param gutters an array of CSS class names
	 */
	void setGutters(String... gutters);

	/**
	 * Can be used to add extra gutters (beyond or instead of the line number gutter).
	 * 
	 * @param gutters a list of CSS class names
	 */
	void setGutters(List<String> gutters);

	/**
	 * Determines whether the gutter scrolls along with the content horizontally (false) or whether it stays fixed during
	 * horizontal scrolling
	 * 
	 * @param fixedGutter determines whether the gutter scrolls along with the content horizontally (false) or whether it stays
	 *            fixed during horizontal scrolling
	 */
	void setFixedGutter(boolean fixedGutter);

	/**
	 * Chooses a scrollbar implementation. The default is "native", showing native scrollbars.<br>
	 * The core library also provides the "null" style, which completely hides the scrollbars.
	 * 
	 * @param scrollbarStyle a scrollbar implementation style
	 */
	void setScrollbarStyle(String scrollbarStyle);

	/**
	 * When "fixedGutter" is on, and there is a horizontal scrollbar, by default the gutter will be visible to the left of this
	 * scrollbar.
	 * 
	 * @param coverGutterNextToScrollbar <code>true</code> if the gutter will be visible to the left of this scrollbar
	 */
	void setCoverGutterNextToScrollbar(boolean coverGutterNextToScrollbar);

	/**
	 * Selects the way it handles input and focus.
	 * 
	 * @param inputStyle the way it handles input and focus.
	 */
	void setInputStyle(InputStyle inputStyle);

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @param readOnly This disables editing of the editor content by the user
	 */
	void setReadOnly(boolean readOnly);

	/**
	 * This disables editing of the editor content by the user. If the special value "nocursor" is given (instead of simply
	 * true), focusing of the editor is also disallowed.
	 * 
	 * @param readOnly This disables editing of the editor content by the user
	 */
	void setReadOnly(ReadOnly readOnly);

	/**
	 * Whether the cursor should be drawn when a selection is active.
	 * 
	 * @param showCursorWhenSelecting Whether the cursor should be drawn when a selection is active
	 */
	void setShowCursorWhenSelecting(boolean showCursorWhenSelecting);

	/**
	 * When enabled, which is the default, doing copy or cut when there is no selection will copy or cut the whole lines that
	 * have cursors on them.
	 * 
	 * @param lineWiseCopyCut When enabled, which is the default, doing copy or cut when there is no selection will copy or cut
	 *            the whole lines that have cursors on them.
	 */
	void setLineWiseCopyCut(boolean lineWiseCopyCut);

	/**
	 * When pasting something from an external source (not from the editor itself), if the number of lines matches the number of
	 * selection, CodeMirror will by default insert one line per selection. You can set this to false to disable that behavior.
	 * 
	 * @param pasteLinesPerSelection When pasting something from an external source (not from the editor itself), if the number
	 *            of lines matches the number of selection, CodeMirror will by default insert one line per selection. You can
	 *            set this to false to disable that behavior.
	 */
	void setPasteLinesPerSelection(boolean pasteLinesPerSelection);

	/**
	 * Determines whether multiple selections are joined as soon as they touch (the default) or only when they overlap (true).
	 * 
	 * @param selectionsMayTouch Determines whether multiple selections are joined as soon as they touch (the default) or only
	 *            when they overlap (true).
	 */
	void setSelectionsMayTouch(boolean selectionsMayTouch);

	/**
	 * The maximum number of undo levels that the editor stores.
	 * 
	 * @param undoDepth The maximum number of undo levels that the editor stores.
	 */
	void setUndoDepth(int undoDepth);

	/**
	 * The period of inactivity (in milliseconds) that will cause a new history event to be started when typing or deleting.
	 * 
	 * @param historyEventDelay The period of inactivity (in milliseconds) that will cause a new history event to be started
	 *            when typing or deleting.
	 */
	void setHistoryEventDelay(int historyEventDelay);

	/**
	 * The tab index to assign to the editor. If not given ({@link UndefinedValues#INTEGER}), no tab index will be assigned.
	 * 
	 * @param tabindex The tab index to assign to the editor. If not given({@link UndefinedValues#INTEGER}), no tab index will
	 *            be assigned.
	 */
	void setTabindex(int tabindex);

	/**
	 * Can be used to make CodeMirror focus itself on initialization.
	 * 
	 * @param autofocus Can be used to make CodeMirror focus itself on initialization.
	 */
	void setAutofocus(boolean autofocus);

	/**
	 * Some addons run user-visible strings (such as labels in the interface) through the phrase method to allow for
	 * translation. This option determines the return value of that method. When it is null or an object that doesn't have a
	 * property named by the input string, that string is returned. Otherwise, the value of the property corresponding to that
	 * string is returned.
	 * 
	 * @param phrases When it is null or an object that doesn't have a property named by the input string, that string is
	 *            returned. Otherwise, the value of the property corresponding to that string is returned.
	 */
	void setPhrases(Phrases phrases);

	/**
	 * Controls whether drag-and-drop is enabled. On by default.
	 * 
	 * @param dragDrop Controls whether drag-and-drop is enabled. On by default.
	 */
	void setDragDrop(boolean dragDrop);

	/**
	 * When set (default is null) only files whose type is in the list can be dropped into the editor.<br>
	 * The strings should be MIME types, and will be checked against the type of the File object as reported by the browser.
	 * 
	 * @param allowDropFileTypes files whose type is in the list can be dropped into the editor
	 */
	void setAllowDropFileTypes(String... allowDropFileTypes);

	/**
	 * When set (default is null) only files whose type is in the array can be dropped into the editor.<br>
	 * The strings should be MIME types, and will be checked against the type of the File object as reported by the browser.
	 * 
	 * @param allowDropFileTypes files whose type is in the array can be dropped into the editor
	 */
	void setAllowDropFileTypes(List<String> allowDropFileTypes);

	/**
	 * Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be disabled. A negative value
	 * hides the cursor entirely.
	 * 
	 * @param cursorBlinkRate Half-period in milliseconds used for cursor blinking. By setting this to zero, blinking can be
	 *            disabled. A negative value hides the cursor entirely.
	 */
	void setCursorBlinkRate(int cursorBlinkRate);

	/**
	 * How much extra space to always keep above and below the cursor when approaching the top or bottom of the visible view in
	 * a scrollable document.
	 * 
	 * @param cursorScrollMargin How much extra space to always keep above and below the cursor when approaching the top or
	 *            bottom of the visible view in a scrollable document.
	 */
	void setCursorScrollMargin(int cursorScrollMargin);

	/**
	 * Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 * 
	 * @param cursorHeight Determines the height of the cursor. Default is 1, meaning it spans the whole height of the line.
	 */
	void setCursorHeight(int cursorHeight);

	/**
	 * Controls whether, when the context menu is opened with a click outside of the current selection, the cursor is moved to
	 * the point of the click.
	 * 
	 * @param resetSelectionOnContextMenu Controls whether, when the context menu is opened with a click outside of the current
	 *            selection, the cursor is moved to the point of the click.
	 */
	void setResetSelectionOnContextMenu(boolean resetSelectionOnContextMenu);

	/**
	 * Indicates how quickly CodeMirror should poll its input textarea for changes (when focused). Most input is captured by
	 * events, but some things, like IME input on some browsers, don't generate events that allow CodeMirror to properly detect
	 * it. Thus, it polls.
	 * 
	 * @param pollInterval Indicates how quickly CodeMirror should poll its input textarea for changes (when focused)
	 */
	void setPollInterval(int pollInterval);

	/**
	 * By default, CodeMirror will combine adjacent tokens into a single span if they have the same class. This will result in a
	 * simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded corners), this will change the way
	 * the document looks. You can set this option to false to disable this behavior.
	 * 
	 * @param flattenSpans By default, CodeMirror will combine adjacent tokens into a single span if they have the same class.
	 *            This will result in a simpler DOM tree, and thus perform better. With some kinds of styling (such as rounded
	 *            corners), this will change the way the document looks. You can set this option to false to disable this
	 *            behavior.
	 */
	void setFlattenSpans(boolean flattenSpans);

	/**
	 * When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner) mode that produced
	 * it.
	 * 
	 * @param addModeClass When enabled (off by default), an extra CSS class will be added to each token, indicating the (inner)
	 *            mode that produced it
	 */
	void setAddModeClass(boolean addModeClass);

	/**
	 * When highlighting long lines, in order to stay responsive, the editor will give up and simply style the rest of the line
	 * as plain text when it reaches a certain position. The default is 10 000.
	 * 
	 * @param maxHighlightLength When highlighting long lines, in order to stay responsive, the editor will give up and simply
	 *            style the rest of the line as plain text when it reaches a certain position. The default is 10 000.
	 */
	void setMaxHighlightLength(int maxHighlightLength);

	/**
	 * Specifies the amount of lines that are rendered above and below the part of the document that's currently scrolled into
	 * view. This affects the amount of updates needed when scrolling, and the amount of work that such an update does.
	 * 
	 * @param viewportMargin the amount of lines that are rendered above and below the part of the document that's currently
	 *            scrolled into view
	 */
	void setViewportMargin(int viewportMargin);

	/**
	 * Specifies whether or not spellcheck will be enabled on the input.
	 * 
	 * @param spellcheck Specifies whether or not spellcheck will be enabled on the input.
	 */
	void setSpellcheck(boolean spellcheck);

	/**
	 * Specifies whether or not autocorrect will be enabled on the input.
	 * 
	 * @param autocorrect Specifies whether or not autocorrect will be enabled on the input.
	 */
	void setAutocorrect(boolean autocorrect);

	/**
	 * Specifies whether or not autocapitalization will be enabled on the input.
	 * 
	 * @param autocapitalize Specifies whether or not autocapitalization will be enabled on the input.
	 */
	void setAutocapitalize(boolean autocapitalize);

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @param workTime Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use
	 *            timeout to sleep for workDelay milliseconds.
	 */
	void setWorkTime(int workTime);

	/**
	 * Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then use timeout to
	 * sleep for workDelay milliseconds.
	 * 
	 * @param workDelay Highlighting is done by a pseudo background-thread that will work for workTime milliseconds, and then
	 *            use timeout to sleep for workDelay milliseconds.
	 */
	void setWorkDelay(int workDelay);
	
	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	String toJSON();

}
