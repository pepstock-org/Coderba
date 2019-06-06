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
package org.pepstock.coderba.client.commons;

import java.util.List;

import org.pepstock.coderba.client.GlobalDefaults;
import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.IsDefaultOptions;
import org.pepstock.coderba.client.KeyMap;
import org.pepstock.coderba.client.KeyMaps;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.Languages;
import org.pepstock.coderba.client.Theme;
import org.pepstock.coderba.client.Themes;
import org.pepstock.coderba.client.entities.IsOptions;
import org.pepstock.coderba.client.entities.Phrases;
import org.pepstock.coderba.client.enums.Direction;
import org.pepstock.coderba.client.enums.InputStyle;
import org.pepstock.coderba.client.enums.Options;
import org.pepstock.coderba.client.enums.ReadOnly;
import org.pepstock.coderba.client.utils.RegExp;

/**
 * Core class to manage all CodeMirror options.<br>
 * This class is used for all types of options:<br>
 * <ul>
 * <li>DEFAULTS, provided out of the box by CodeMirror
 * <li>USER, provided by user to create an editor
 * <li>EDITOR, provided by CodeMirror as merge between DEFAULT and USER (used at runtime)
 * </ul>
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of object container.
 */
public abstract class AbstractOptions<T extends AbstractNativeObjectContainer> implements IsOptions {

	// default options values
	private final IsDefaultOptions defaultsValue;
	// native object container which manage all set and get
	// by key
	private final T nativeObjectContainer;

	/**
	 * Creates the options manager by a container and default values.
	 * 
	 * @param nativeObjectContainer native object container which manage all set and get by key
	 * @param defaultsValue default values
	 */
	protected AbstractOptions(T nativeObjectContainer, IsDefaultOptions defaultsValue) {
		this.nativeObjectContainer = nativeObjectContainer;
		this.defaultsValue = defaultsValue;
	}

	/**
	 * Returns the native object container used at creation time.
	 * 
	 * @return the native object container used at creation time
	 */
	protected final T getNativeObjectContainer() {
		return nativeObjectContainer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getAllowDropFileTypes()
	 */
	@Override
	public List<String> getAllowDropFileTypes() {
		if (nativeObjectContainer.has(Options.ALLOW_DROP_FILE_TYPES)) {
			ArrayString array = ArrayString.fromOrNull(nativeObjectContainer.getArrayValue(Options.ALLOW_DROP_FILE_TYPES));
			if (array != null) {
				return ArrayListHelper.list(array);
			}
		}
		return defaultsValue.getAllowDropFileTypes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getCursorBlinkRate()
	 */
	@Override
	public int getCursorBlinkRate() {
		return nativeObjectContainer.getValue(Options.CURSOR_BLINK_RATE, defaultsValue.getCursorBlinkRate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getCursorHeight()
	 */
	@Override
	public int getCursorHeight() {
		return nativeObjectContainer.getValue(Options.CURSOR_HEIGHT, defaultsValue.getCursorHeight());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getCursorScrollMargin()
	 */
	@Override
	public int getCursorScrollMargin() {
		return nativeObjectContainer.getValue(Options.CURSOR_SCROLL_MARGIN, defaultsValue.getCursorScrollMargin());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getDirection()
	 */
	@Override
	public Direction getDirection() {
		return nativeObjectContainer.getValue(Options.DIRECTION, Direction.class, defaultsValue.getDirection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getExtraKeys()
	 */
	@Override
	public NativeObject getExtraKeys() {
		//FIXME
		return nativeObjectContainer.getValue(Options.EXTRA_KEYS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getFirstLineNumber()
	 */
	@Override
	public int getFirstLineNumber() {
		return nativeObjectContainer.getValue(Options.FIRST_LINE_NUMBER, defaultsValue.getFirstLineNumber());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getGutters()
	 */
	@Override
	public List<String> getGutters() {
		if (nativeObjectContainer.has(Options.GUTTERS)) {
			ArrayString array = nativeObjectContainer.getArrayValue(Options.GUTTERS);
			return ArrayListHelper.list(array);
		}
		return defaultsValue.getGutters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getHistoryEventDelay()
	 */
	@Override
	public int getHistoryEventDelay() {
		return nativeObjectContainer.getValue(Options.HISTORY_EVENT_DELAY, defaultsValue.getHistoryEventDelay());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getIndentUnit()
	 */
	@Override
	public int getIndentUnit() {
		return nativeObjectContainer.getValue(Options.INDENT_UNIT, defaultsValue.getIndentUnit());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getInputStyle()
	 */
	@Override
	public InputStyle getInputStyle() {
		return nativeObjectContainer.getValue(Options.INPUT_STYLE, InputStyle.class, defaultsValue.getInputStyle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getKeyMap()
	 */
	@Override
	public KeyMap getKeyMap() {
		String value = nativeObjectContainer.getValue(Options.KEY_MAP, defaultsValue.getKeyMap().getName());
		String defaultKeyMapName = GlobalDefaults.get().getKeyMap().getName();
		if (defaultKeyMapName.equalsIgnoreCase(value) && KeyMaps.get().retrieve(defaultKeyMapName) == null) {
			GlobalDefaults.get().getKeyMap().inject();
		}
		return KeyMaps.get().retrieve(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getLanguage()
	 */
	@Override
	public Language getLanguage() {
		String value = nativeObjectContainer.getValue(Options.MODE, defaultsValue.getLanguage().getName());
		String defaultLanguageName = GlobalDefaults.get().getLanguage().getName();
		if (defaultLanguageName.equalsIgnoreCase(value) && Languages.get().retrieve(defaultLanguageName) == null) {
			Injector.ensureInjected(GlobalDefaults.get().getLanguage());
		}
		return Languages.get().retrieve(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getLineSeparator()
	 */
	@Override
	public String getLineSeparator() {
		return nativeObjectContainer.getValue(Options.LINE_SEPARATOR, defaultsValue.getLineSeparator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getMaxHighlightLength()
	 */
	@Override
	public int getMaxHighlightLength() {
		return nativeObjectContainer.getValue(Options.MAX_HIGHLIGHT_LENGTH, defaultsValue.getMaxHighlightLength());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getPhrases()
	 */
	@Override
	public Phrases getPhrases() {
		return nativeObjectContainer.getValue(Options.PHRASES, (Phrases) null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getPollInterval()
	 */
	@Override
	public int getPollInterval() {
		return nativeObjectContainer.getValue(Options.POLL_INTERVAL, defaultsValue.getPollInterval());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getReadOnly()
	 */
	@Override
	public ReadOnly getReadOnly() {
		if (!nativeObjectContainer.has(Options.READ_ONLY)) {
			return defaultsValue.getReadOnly();
		}
		ObjectType type = nativeObjectContainer.type(Options.READ_ONLY);
		if (ObjectType.BOOLEAN.equals(type)) {
			boolean isReadOnly = nativeObjectContainer.getValue(Options.READ_ONLY, defaultsValue.isReadOnly());
			if (isReadOnly) {
				return ReadOnly.TRUE;
			}
			return ReadOnly.FALSE;
		}
		return nativeObjectContainer.getValue(Options.READ_ONLY, ReadOnly.class, ReadOnly.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getScrollbarStyle()
	 */
	@Override
	public String getScrollbarStyle() {
		return nativeObjectContainer.getValue(Options.SCROLLBAR_STYLE, defaultsValue.getScrollbarStyle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getSpecialChars()
	 */
	@Override
	public RegExp getSpecialChars() {
		return nativeObjectContainer.getValue(Options.SPECIAL_CHARS, defaultsValue.getSpecialChars());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getTabindex()
	 */
	@Override
	public int getTabindex() {
		return nativeObjectContainer.getValue(Options.TABINDEX, defaultsValue.getTabindex());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getTabSize()
	 */
	@Override
	public int getTabSize() {
		return nativeObjectContainer.getValue(Options.TAB_SIZE, defaultsValue.getTabSize());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getTheme()
	 */
	@Override
	public Theme getTheme() {
		String value = nativeObjectContainer.getValue(Options.THEME, defaultsValue.getTheme().getName());
		Theme defaultTheme = GlobalDefaults.get().getTheme();
		if (defaultTheme.getName().equalsIgnoreCase(value)) {
			Injector.ensureInjected(defaultTheme);
			return defaultTheme;
		}
		return Themes.get().retrieve(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getUndoDepth()
	 */
	@Override
	public int getUndoDepth() {
		return nativeObjectContainer.getValue(Options.UNDO_DEPTH, defaultsValue.getUndoDepth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getValue()
	 */
	@Override
	public String getValue() {
		return nativeObjectContainer.getValue(Options.VALUE, defaultsValue.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getViewportMargin()
	 */
	@Override
	public int getViewportMargin() {
		return nativeObjectContainer.getValue(Options.VIEWPORT_MARGIN, defaultsValue.getViewportMargin());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getWorkDelay()
	 */
	@Override
	public int getWorkDelay() {
		return nativeObjectContainer.getValue(Options.WORK_DELAY, defaultsValue.getWorkDelay());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#getWorkTime()
	 */
	@Override
	public int getWorkTime() {
		return nativeObjectContainer.getValue(Options.WORK_TIME, defaultsValue.getWorkTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isAddModeClass()
	 */
	@Override
	public boolean isAddModeClass() {
		return nativeObjectContainer.getValue(Options.ADD_MODE_CLASS, defaultsValue.isAddModeClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isAutocapitalize()
	 */
	@Override
	public boolean isAutocapitalize() {
		return nativeObjectContainer.getValue(Options.AUTOCAPITALIZE, defaultsValue.isAutocapitalize());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isAutocorrect()
	 */
	@Override
	public boolean isAutocorrect() {
		return nativeObjectContainer.getValue(Options.AUTOCORRECT, defaultsValue.isAutocorrect());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isAutofocus()
	 */
	@Override
	public boolean isAutofocus() {
		return nativeObjectContainer.getValue(Options.AUTOFOCUS, defaultsValue.isAutofocus());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isCoverGutterNextToScrollbar()
	 */
	@Override
	public boolean isCoverGutterNextToScrollbar() {
		return nativeObjectContainer.getValue(Options.COVER_GUTTER_NEXT_TO_SCROLLBAR, defaultsValue.isCoverGutterNextToScrollbar());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isDragDrop()
	 */
	@Override
	public boolean isDragDrop() {
		return nativeObjectContainer.getValue(Options.DRAG_DROP, defaultsValue.isDragDrop());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isElectricChars()
	 */
	@Override
	public boolean isElectricChars() {
		return nativeObjectContainer.getValue(Options.ELECTRIC_CHARS, defaultsValue.isElectricChars());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isFixedGutter()
	 */
	@Override
	public boolean isFixedGutter() {
		return nativeObjectContainer.getValue(Options.FIXED_GUTTER, defaultsValue.isFixedGutter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isFlattenSpans()
	 */
	@Override
	public boolean isFlattenSpans() {
		return nativeObjectContainer.getValue(Options.FLATTEN_SPANS, defaultsValue.isFlattenSpans());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isIndentWithTabs()
	 */
	@Override
	public boolean isIndentWithTabs() {
		return nativeObjectContainer.getValue(Options.INDENT_WITH_TABS, defaultsValue.isIndentWithTabs());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isLineNumbers()
	 */
	@Override
	public boolean isLineNumbers() {
		return nativeObjectContainer.getValue(Options.LINE_NUMBERS, defaultsValue.isLineNumbers());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isLineWiseCopyCut()
	 */
	@Override
	public boolean isLineWiseCopyCut() {
		return nativeObjectContainer.getValue(Options.LINE_WISE_COPY_CUT, defaultsValue.isLineWiseCopyCut());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isLineWrapping()
	 */
	@Override
	public boolean isLineWrapping() {
		return nativeObjectContainer.getValue(Options.LINE_WRAPPING, defaultsValue.isLineWrapping());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isPasteLinesPerSelection()
	 */
	@Override
	public boolean isPasteLinesPerSelection() {
		return nativeObjectContainer.getValue(Options.PASTE_LINES_PER_SELECTION, defaultsValue.isPasteLinesPerSelection());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() {
		if (!nativeObjectContainer.has(Options.READ_ONLY)) {
			return defaultsValue.isReadOnly();
		}
		ObjectType type = nativeObjectContainer.type(Options.READ_ONLY);
		if (ObjectType.BOOLEAN.equals(type)) {
			return nativeObjectContainer.getValue(Options.READ_ONLY, defaultsValue.isReadOnly());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isResetSelectionOnContextMenu()
	 */
	@Override
	public boolean isResetSelectionOnContextMenu() {
		return nativeObjectContainer.getValue(Options.RESET_SELECTION_ON_CONTEXT_MENU, defaultsValue.isResetSelectionOnContextMenu());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isRtlMoveVisually()
	 */
	@Override
	public boolean isRtlMoveVisually() {
		return nativeObjectContainer.getValue(Options.RTL_MOVE_VISUALLY, defaultsValue.isRtlMoveVisually());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isSelectionsMayTouch()
	 */
	@Override
	public boolean isSelectionsMayTouch() {
		return nativeObjectContainer.getValue(Options.SELECTIONS_MAY_TOUCH, defaultsValue.isSelectionsMayTouch());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isShowCursorWhenSelecting()
	 */
	@Override
	public boolean isShowCursorWhenSelecting() {
		return nativeObjectContainer.getValue(Options.SHOW_CURSOR_WHEN_SELECTING, defaultsValue.isShowCursorWhenSelecting());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isSmartIndent()
	 */
	@Override
	public boolean isSmartIndent() {
		return nativeObjectContainer.getValue(Options.SMART_INDENT, defaultsValue.isSmartIndent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.defaults.IsDefaultOptions#isSpellcheck()
	 */
	@Override
	public boolean isSpellcheck() {
		return nativeObjectContainer.getValue(Options.SPELLCHECK, defaultsValue.isSpellcheck());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAddModeClass(boolean)
	 */
	@Override
	public void setAddModeClass(boolean addModeClass) {
		nativeObjectContainer.setValue(Options.ADD_MODE_CLASS, addModeClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAllowDropFileTypes(java.util.List)
	 */
	@Override
	public void setAllowDropFileTypes(List<String> allowDropFileTypes) {
		nativeObjectContainer.setArrayValue(Options.ALLOW_DROP_FILE_TYPES, ArrayString.fromOrNull(allowDropFileTypes));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAllowDropFileTypes(java.lang.String[])
	 */
	@Override
	public void setAllowDropFileTypes(String... allowDropFileTypes) {
		nativeObjectContainer.setArrayValue(Options.ALLOW_DROP_FILE_TYPES, ArrayString.fromOrNull(allowDropFileTypes));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutocapitalize(boolean)
	 */
	@Override
	public void setAutocapitalize(boolean autocapitalize) {
		nativeObjectContainer.setValue(Options.AUTOCAPITALIZE, autocapitalize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutocorrect(boolean)
	 */
	@Override
	public void setAutocorrect(boolean autocorrect) {
		nativeObjectContainer.setValue(Options.AUTOCORRECT, autocorrect);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutofocus(boolean)
	 */
	@Override
	public void setAutofocus(boolean autofocus) {
		nativeObjectContainer.setValue(Options.AUTOFOCUS, autofocus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCoverGutterNextToScrollbar(boolean)
	 */
	@Override
	public void setCoverGutterNextToScrollbar(boolean coverGutterNextToScrollbar) {
		nativeObjectContainer.setValue(Options.COVER_GUTTER_NEXT_TO_SCROLLBAR, coverGutterNextToScrollbar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorBlinkRate(int)
	 */
	@Override
	public void setCursorBlinkRate(int cursorBlinkRate) {
		nativeObjectContainer.setValue(Options.CURSOR_BLINK_RATE, cursorBlinkRate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorHeight(int)
	 */
	@Override
	public void setCursorHeight(int cursorHeight) {
		nativeObjectContainer.setValue(Options.CURSOR_HEIGHT, cursorHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorScrollMargin(int)
	 */
	@Override
	public void setCursorScrollMargin(int cursorScrollMargin) {
		nativeObjectContainer.setValue(Options.CURSOR_SCROLL_MARGIN, cursorScrollMargin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setDirection(org.pepstock.coderba.client.cm.enums.Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		nativeObjectContainer.setValue(Options.DIRECTION, direction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setDragDrop(boolean)
	 */
	@Override
	public void setDragDrop(boolean dragDrop) {
		nativeObjectContainer.setValue(Options.DRAG_DROP, dragDrop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setElectricChars(boolean)
	 */
	@Override
	public void setElectricChars(boolean electricChars) {
		nativeObjectContainer.setValue(Options.ELECTRIC_CHARS, electricChars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setExtraKeys(org.pepstock.coderba.client.cm.commons.NativeObject)
	 */
	@Override
	public void setExtraKeys(NativeObject extraKeys) {
		//FIXME
		nativeObjectContainer.setValue(Options.EXTRA_KEYS, extraKeys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFirstLineNumber(int)
	 */
	@Override
	public void setFirstLineNumber(int firstLineNumber) {
		nativeObjectContainer.setValue(Options.FIRST_LINE_NUMBER, firstLineNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFixedGutter(boolean)
	 */
	@Override
	public void setFixedGutter(boolean fixedGutter) {
		nativeObjectContainer.setValue(Options.FIXED_GUTTER, fixedGutter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFlattenSpans(boolean)
	 */
	@Override
	public void setFlattenSpans(boolean flattenSpans) {
		nativeObjectContainer.setValue(Options.FLATTEN_SPANS, flattenSpans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setGutters(java.util.List)
	 */
	@Override
	public void setGutters(List<String> gutters) {
		nativeObjectContainer.setArrayValue(Options.GUTTERS, ArrayString.fromOrEmpty(gutters));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setGutters(java.lang.String[])
	 */
	@Override
	public void setGutters(String... gutters) {
		nativeObjectContainer.setArrayValue(Options.GUTTERS, ArrayString.fromOrEmpty(gutters));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setHistoryEventDelay(int)
	 */
	@Override
	public void setHistoryEventDelay(int historyEventDelay) {
		nativeObjectContainer.setValue(Options.HISTORY_EVENT_DELAY, historyEventDelay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setIndentUnit(int)
	 */
	@Override
	public void setIndentUnit(int indentUnit) {
		nativeObjectContainer.setValue(Options.INDENT_UNIT, indentUnit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setIndentWithTabs(boolean)
	 */
	@Override
	public void setIndentWithTabs(boolean indentWithTabs) {
		nativeObjectContainer.setValue(Options.INDENT_WITH_TABS, indentWithTabs);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setInputStyle(org.pepstock.coderba.client.cm.enums.InputStyle)
	 */
	@Override
	public void setInputStyle(InputStyle inputStyle) {
		nativeObjectContainer.setValue(Options.INPUT_STYLE, inputStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setKeyMap(java.lang.String)
	 */
	@Override
	public void setKeyMap(KeyMap keyMap) {
		if (keyMap != null) {
			keyMap.inject();
			nativeObjectContainer.setValue(Options.KEY_MAP, keyMap.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLanguage(org.pepstock.coderba.client.cm.Language)
	 */
	@Override
	public void setLanguage(Language language) {
		if (language != null) {
			Injector.ensureInjected(language);
			nativeObjectContainer.setValue(Options.MODE, language.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineNumbers(boolean)
	 */
	@Override
	public void setLineNumbers(boolean lineNumbers) {
		nativeObjectContainer.setValue(Options.LINE_NUMBERS, lineNumbers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineSeparator(java.lang.String)
	 */
	@Override
	public void setLineSeparator(String lineSeparator) {
		nativeObjectContainer.setValue(Options.LINE_SEPARATOR, lineSeparator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineWiseCopyCut(boolean)
	 */
	@Override
	public void setLineWiseCopyCut(boolean lineWiseCopyCut) {
		nativeObjectContainer.setValue(Options.LINE_WISE_COPY_CUT, lineWiseCopyCut);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineWrapping(boolean)
	 */
	@Override
	public void setLineWrapping(boolean lineWrapping) {
		nativeObjectContainer.setValue(Options.LINE_WRAPPING, lineWrapping);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setMaxHighlightLength(int)
	 */
	@Override
	public void setMaxHighlightLength(int maxHighlightLength) {
		nativeObjectContainer.setValue(Options.MAX_HIGHLIGHT_LENGTH, maxHighlightLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPasteLinesPerSelection(boolean)
	 */
	@Override
	public void setPasteLinesPerSelection(boolean pasteLinesPerSelection) {
		nativeObjectContainer.setValue(Options.PASTE_LINES_PER_SELECTION, pasteLinesPerSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPhrases(java.lang.Object)
	 */
	@Override
	public void setPhrases(Phrases phrases) {
		nativeObjectContainer.setValue(Options.PHRASES, phrases);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPollInterval(int)
	 */
	@Override
	public void setPollInterval(int pollInterval) {
		nativeObjectContainer.setValue(Options.POLL_INTERVAL, pollInterval);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		nativeObjectContainer.setValue(Options.READ_ONLY, readOnly);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setReadOnly(org.pepstock.coderba.client.cm.enums.ReadOnly)
	 */
	@Override
	public void setReadOnly(ReadOnly readOnly) {
		if (ReadOnly.NO_CURSOR.equals(readOnly)) {
			nativeObjectContainer.setValue(Options.READ_ONLY, readOnly);
		} else if (ReadOnly.TRUE.equals(readOnly)) {
			setReadOnly(true);
		} else {
			setReadOnly(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setResetSelectionOnContextMenu(boolean)
	 */
	@Override
	public void setResetSelectionOnContextMenu(boolean resetSelectionOnContextMenu) {
		nativeObjectContainer.setValue(Options.RESET_SELECTION_ON_CONTEXT_MENU, resetSelectionOnContextMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setRtlMoveVisually(boolean)
	 */
	@Override
	public void setRtlMoveVisually(boolean rtlMoveVisually) {
		nativeObjectContainer.setValue(Options.RTL_MOVE_VISUALLY, rtlMoveVisually);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setScrollbarStyle(java.lang.String)
	 */
	@Override
	public void setScrollbarStyle(String scrollbarStyle) {
		nativeObjectContainer.setValue(Options.SCROLLBAR_STYLE, scrollbarStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSelectionsMayTouch(boolean)
	 */
	@Override
	public void setSelectionsMayTouch(boolean selectionsMayTouch) {
		nativeObjectContainer.setValue(Options.SELECTIONS_MAY_TOUCH, selectionsMayTouch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setShowCursorWhenSelecting(boolean)
	 */
	@Override
	public void setShowCursorWhenSelecting(boolean showCursorWhenSelecting) {
		nativeObjectContainer.setValue(Options.SHOW_CURSOR_WHEN_SELECTING, showCursorWhenSelecting);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSmartIndent(boolean)
	 */
	@Override
	public void setSmartIndent(boolean smartIndent) {
		nativeObjectContainer.setValue(Options.SMART_INDENT, smartIndent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSpecialChars(org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	public void setSpecialChars(RegExp specialChars) {
		nativeObjectContainer.setValue(Options.SPECIAL_CHARS, specialChars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSpellcheck(boolean)
	 */
	@Override
	public void setSpellcheck(boolean spellcheck) {
		nativeObjectContainer.setValue(Options.SPELLCHECK, spellcheck);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTabindex(int)
	 */
	@Override
	public void setTabindex(int tabindex) {
		if (tabindex == UndefinedValues.INTEGER) {
			nativeObjectContainer.remove(Options.TABINDEX);
		} else {
			nativeObjectContainer.setValue(Options.TABINDEX, tabindex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTabSize(boolean)
	 */
	@Override
	public void setTabSize(boolean tabSize) {
		nativeObjectContainer.setValue(Options.TAB_SIZE, tabSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTheme(org.pepstock.coderba.client.cm.Theme)
	 */
	@Override
	public void setTheme(Theme theme) {
		if (theme != null && theme.getName() != null) {
			Injector.ensureInjected(theme);
			nativeObjectContainer.setValue(Options.THEME, theme.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setUndoDepth(int)
	 */
	@Override
	public void setUndoDepth(int undoDepth) {
		nativeObjectContainer.setValue(Options.UNDO_DEPTH, undoDepth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		if (value != null) {
			nativeObjectContainer.setValue(Options.VALUE, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setViewportMargin(int)
	 */
	@Override
	public void setViewportMargin(int viewportMargin) {
		nativeObjectContainer.setValue(Options.VIEWPORT_MARGIN, viewportMargin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setWorkDelay(int)
	 */
	@Override
	public void setWorkDelay(int workDelay) {
		nativeObjectContainer.setValue(Options.WORK_DELAY, workDelay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setWorkTime(int)
	 */
	@Override
	public void setWorkTime(int workTime) {
		nativeObjectContainer.setValue(Options.WORK_TIME, workTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsOptions#toJSON()
	 */
	@Override
	public final String toJSON() {
		return nativeObjectContainer.toJSON();
	}
}
