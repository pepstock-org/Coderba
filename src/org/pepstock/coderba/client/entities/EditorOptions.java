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

import java.util.List;

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.KeyMap;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.Theme;
import org.pepstock.coderba.client.callbacks.ConfigureMouseHandler;
import org.pepstock.coderba.client.callbacks.LineNumberFormatterHandler;
import org.pepstock.coderba.client.callbacks.SpecialCharPlaceholderHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.enums.Direction;
import org.pepstock.coderba.client.enums.InputStyle;
import org.pepstock.coderba.client.enums.MouseRepeat;
import org.pepstock.coderba.client.enums.ReadOnly;
import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

import jsinterop.annotations.JsFunction;

/**
 * Wraps the user and runtime options of an editor area and used to configure the editor, before and during the life cycle of
 * editor.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class EditorOptions implements IsOptions {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that, given a special character identified by the specialChars option, produces a DOM node that is
	 * used to represent the character.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface SpecialCharPlaceholderFunction {

		/**
		 * A function that, given a special character identified by the specialChars option, produces a DOM node that is used to
		 * represent the character.
		 * 
		 * @param character a special character identified by the specialChars option
		 * @return a DOM node that is used to represent the character.
		 */
		Element call(char character);
	}

	/**
	 * Java script FUNCTION used to format line numbers.<br>
	 * The function is passed the line number, and should return a string that will be shown in the gutter.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LineNumberFormatterFunction {

		/**
		 * A function used to format line numbers.<br>
		 * The function is passed the line number, and should return a string that will be shown in the gutter.
		 * 
		 * @param line line number to format
		 * @return string that will be shown in the gutter
		 */
		String call(int line);
	}

	/**
	 * Java script FUNCTION used to allow you to configure the behavior of mouse selection and dragging.<br>
	 * The function is called when the left mouse button is pressed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface ConfigureMouseFunction {

		/**
		 * Allow you to configure the behavior of mouse selection and dragging.<br>
		 * The function is called when the left mouse button is pressed.
		 * 
		 * @param editor CodeMirror editor instance
		 * @param repeat type of click by mouse
		 * @param event native event
		 * @return The returned object may have the following properties:<br>
		 *         <ul>
		 *         <li>unit: "char" | "word" | "line" | "rectangle" | fn(CodeMirror, Pos) --> {from: Pos, to: Pos}.<br>
		 *         The unit by which to select. May be one of the built-in units or a function that takes a position and returns
		 *         a range around that, for a custom unit. The default is to return "word" for double clicks, "line" for triple
		 *         clicks, "rectangle" for alt-clicks (or, on Chrome OS, meta-shift-clicks), and "single" otherwise.
		 *         <li>extend: bool. Whether to extend the existing selection range or start a new one. By default, this is
		 *         enabled when shift clicking.
		 *         <li>addNew: bool. When enabled, this adds a new range to the existing selection, rather than replacing
		 *         it.<br>
		 *         The default behavior is to enable this for command-click on Mac OS, and control-click on other platforms.
		 *         <li>moveOnDrag: bool. When the mouse even drags content around inside the editor, this controls whether it is
		 *         copied (false) or moved (true). By default, this is enabled by alt-clicking on Mac OS, and ctrl-clicking
		 *         elsewhere.
		 *         </ul>
		 */
		NativeObject call(NativeEditor editor, String repeat, NativeEvent event);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the specialCharPlaceholder function
	private final CallbackProxy<SpecialCharPlaceholderFunction> specialCharPlaceholderFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the lineNumberFormatter function
	private final CallbackProxy<LineNumberFormatterFunction> lineNumberFormatterFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the configureMouse function
	private final CallbackProxy<ConfigureMouseFunction> configureMouseFunctionProxy = JsHelper.get().newCallbackProxy();
	// special char place holder handler
	private SpecialCharPlaceholderHandler specialCharPlaceholder = null;
	// line number formatter handler
	private LineNumberFormatterHandler lineNumberFormatter = null;
	// configure mouse handler
	private ConfigureMouseHandler configureMouse = null;
	// delegated object to set and get values
	private IsExtendedOptions delegated = null;

	/**
	 * Creates a configuration object wrapping a delegated options manager.<br>
	 * Before a editor creation, the delegate is always and user options.<br>
	 * After editor creation, a runtime options will be set.
	 * 
	 * @see UserOptions
	 */
	public EditorOptions() {
		this.delegated = new UserOptions(Defaults.get());
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		specialCharPlaceholderFunctionProxy.setCallback((character) -> onSpecialCharPlaceholder(character));
		lineNumberFormatterFunctionProxy.setCallback((line) -> onLineNumberFormatter(line));
		configureMouseFunctionProxy.setCallback((editor, repeat, event) -> onConfigureMouse(editor, repeat, event));
	}

	/**
	 * Returns <code>true</code> if no property has been set as option.
	 * 
	 * @return <code>true</code> if no property has been set as option.
	 */
	public final boolean isEmpty() {
		return delegated.isEmpty();
	}

	/**
	 * Sets a runtime options as options manager.<br>
	 * This method is called after editor creation.
	 * 
	 * @param delegated a runtime options instance
	 * @see RuntimeOptions
	 */
	final void setDelegatedOptions(RuntimeOptions delegated) {
		// checks if argument is consistent
		if (delegated != null) {
			// copies the fixed attributes like languages and themes
			delegated.copy(this.delegated);
			// sets the options
			this.delegated = delegated;
		}
	}

	/**
	 * Sets a callback that, given a special character identified by the specialChars option, produces a DOM node that is used
	 * to represent the character.
	 * 
	 * @param specialCharPlaceholder a callback that, given a special character identified by the specialChars option, produces
	 *            a DOM node that is used to represent the character.
	 */
	public void setSpecialCharPlaceholder(SpecialCharPlaceholderHandler specialCharPlaceholder) {
		// sets the callback
		this.specialCharPlaceholder = specialCharPlaceholder;
		// if callback is consistent
		if (specialCharPlaceholder != null) {
			// sets the function for callback
			delegated.setSpecialCharPlaceholder(specialCharPlaceholderFunctionProxy.getProxy());
		} else {
			// if here callback is not consistent
			// and the reset the function for callback
			delegated.setSpecialCharPlaceholder(null);
		}
	}

	/**
	 * Returns a callback that, given a special character identified by the specialChars option, produces a DOM node that is
	 * used to represent the character.
	 * 
	 * @return a callback that, given a special character identified by the specialChars option, produces a DOM node that is
	 *         used to represent the character.
	 */
	public SpecialCharPlaceholderHandler getSpecialCharPlaceholder() {
		return specialCharPlaceholder;
	}

	/**
	 * Sets a function used to format line numbers. The function is passed the line number, and should return a string that will
	 * be shown in the gutter.
	 * 
	 * @param lineNumberFormatter line number formatter instance
	 */
	public void setLineNumberFormatter(LineNumberFormatterHandler lineNumberFormatter) {
		// sets the callback
		this.lineNumberFormatter = lineNumberFormatter;
		// if callback is consistent
		if (lineNumberFormatter != null) {
			// sets the function for callback
			delegated.setLineNumberFormatter(lineNumberFormatterFunctionProxy.getProxy());
		} else {
			// if here callback is not consistent
			// and the reset the function for callback
			delegated.setLineNumberFormatter(null);
		}
	}

	/**
	 * Returns a function used to format line numbers. The function is passed the line number, and should return a string that
	 * will be shown in the gutter.
	 * 
	 * @return line number formatter instance
	 */
	public LineNumberFormatterHandler getLineNumberFormatter() {
		return lineNumberFormatter;
	}

	/**
	 * Sets a function used to allow you to configure the behavior of mouse selection and dragging. The function is called when
	 * the left mouse button is pressed.
	 * 
	 * @param configureMouse configure mouse handler instance
	 */
	public void setConfigureMouse(ConfigureMouseHandler configureMouse) {
		// sets the callback
		this.configureMouse = configureMouse;
		// if callback is consistent
		if (configureMouse != null) {
			// sets the function for callback
			delegated.setConfigureMouse(configureMouseFunctionProxy.getProxy());
		} else {
			// if here callback is not consistent
			// and the reset the function for callback
			delegated.setConfigureMouse(null);
		}
	}

	/**
	 * Returns a function used to allow you to configure the behavior of mouse selection and dragging. The function is called
	 * when the left mouse button is pressed.
	 * 
	 * @return configure mouse handler instance
	 */
	public ConfigureMouseHandler getConfigureMouse() {
		return configureMouse;
	}

	// --------------------------------
	// --- ALL DELEGATED METHODS
	// --------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getValue()
	 */
	@Override
	public String getValue() {
		return delegated.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getLanguage()
	 */
	@Override
	public Language getLanguage() {
		return delegated.getLanguage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getLineSeparator()
	 */
	@Override
	public String getLineSeparator() {
		return delegated.getLineSeparator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getTheme()
	 */
	@Override
	public Theme getTheme() {
		return delegated.getTheme();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getIndentUnit()
	 */
	@Override
	public int getIndentUnit() {
		return delegated.getIndentUnit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isSmartIndent()
	 */
	@Override
	public boolean isSmartIndent() {
		return delegated.isSmartIndent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getTabSize()
	 */
	@Override
	public int getTabSize() {
		return delegated.getTabSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isIndentWithTabs()
	 */
	@Override
	public boolean isIndentWithTabs() {
		return delegated.isIndentWithTabs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isElectricChars()
	 */
	@Override
	public boolean isElectricChars() {
		return delegated.isElectricChars();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getSpecialChars()
	 */
	@Override
	public RegExp getSpecialChars() {
		return delegated.getSpecialChars();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getDirection()
	 */
	@Override
	public Direction getDirection() {
		return delegated.getDirection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isRtlMoveVisually()
	 */
	@Override
	public boolean isRtlMoveVisually() {
		return delegated.isRtlMoveVisually();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getKeyMap()
	 */
	@Override
	public KeyMap getKeyMap() {
		return delegated.getKeyMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getExtraKeys()
	 */
	@Override
	public ExtraKeyMapTable getExtraKeys() {
		return delegated.getExtraKeys();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isLineWrapping()
	 */
	@Override
	public boolean isLineWrapping() {
		return delegated.isLineWrapping();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isLineNumbers()
	 */
	@Override
	public boolean isLineNumbers() {
		return delegated.isLineNumbers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getFirstLineNumber()
	 */
	@Override
	public int getFirstLineNumber() {
		return delegated.getFirstLineNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getGutters()
	 */
	@Override
	public List<String> getGutters() {
		return delegated.getGutters();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isFixedGutter()
	 */
	@Override
	public boolean isFixedGutter() {
		return delegated.isFixedGutter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getScrollbarStyle()
	 */
	@Override
	public String getScrollbarStyle() {
		return delegated.getScrollbarStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isCoverGutterNextToScrollbar()
	 */
	@Override
	public boolean isCoverGutterNextToScrollbar() {
		return delegated.isCoverGutterNextToScrollbar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getInputStyle()
	 */
	@Override
	public InputStyle getInputStyle() {
		return delegated.getInputStyle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() {
		return delegated.isReadOnly();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getReadOnly()
	 */
	@Override
	public ReadOnly getReadOnly() {
		return delegated.getReadOnly();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isShowCursorWhenSelecting()
	 */
	@Override
	public boolean isShowCursorWhenSelecting() {
		return delegated.isShowCursorWhenSelecting();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isLineWiseCopyCut()
	 */
	@Override
	public boolean isLineWiseCopyCut() {
		return delegated.isLineWiseCopyCut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isPasteLinesPerSelection()
	 */
	@Override
	public boolean isPasteLinesPerSelection() {
		return delegated.isPasteLinesPerSelection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isSelectionsMayTouch()
	 */
	@Override
	public boolean isSelectionsMayTouch() {
		return delegated.isSelectionsMayTouch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getUndoDepth()
	 */
	@Override
	public int getUndoDepth() {
		return delegated.getUndoDepth();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getHistoryEventDelay()
	 */
	@Override
	public int getHistoryEventDelay() {
		return delegated.getHistoryEventDelay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getTabindex()
	 */
	@Override
	public int getTabindex() {
		return delegated.getTabindex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isAutofocus()
	 */
	@Override
	public boolean isAutofocus() {
		return delegated.isAutofocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getPhrases()
	 */
	@Override
	public Phrases getPhrases() {
		return delegated.getPhrases();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isDragDrop()
	 */
	@Override
	public boolean isDragDrop() {
		return delegated.isDragDrop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getAllowDropFileTypes()
	 */
	@Override
	public List<String> getAllowDropFileTypes() {
		return delegated.getAllowDropFileTypes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getCursorBlinkRate()
	 */
	@Override
	public int getCursorBlinkRate() {
		return delegated.getCursorBlinkRate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getCursorScrollMargin()
	 */
	@Override
	public int getCursorScrollMargin() {
		return delegated.getCursorScrollMargin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getCursorHeight()
	 */
	@Override
	public int getCursorHeight() {
		return delegated.getCursorHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isResetSelectionOnContextMenu()
	 */
	@Override
	public boolean isResetSelectionOnContextMenu() {
		return delegated.isResetSelectionOnContextMenu();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getPollInterval()
	 */
	@Override
	public int getPollInterval() {
		return delegated.getPollInterval();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isFlattenSpans()
	 */
	@Override
	public boolean isFlattenSpans() {
		return delegated.isFlattenSpans();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isAddModeClass()
	 */
	@Override
	public boolean isAddModeClass() {
		return delegated.isAddModeClass();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getMaxHighlightLength()
	 */
	@Override
	public int getMaxHighlightLength() {
		return delegated.getMaxHighlightLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getViewportMargin()
	 */
	@Override
	public int getViewportMargin() {
		return delegated.getViewportMargin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isSpellcheck()
	 */
	@Override
	public boolean isSpellcheck() {
		return delegated.isSpellcheck();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isAutocorrect()
	 */
	@Override
	public boolean isAutocorrect() {
		return delegated.isAutocorrect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#isAutocapitalize()
	 */
	@Override
	public boolean isAutocapitalize() {
		return delegated.isAutocapitalize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getWorkTime()
	 */
	@Override
	public int getWorkTime() {
		return delegated.getWorkTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.IsDefaultOptions#getWorkDelay()
	 */
	@Override
	public int getWorkDelay() {
		return delegated.getWorkDelay();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {
		delegated.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLanguage(org.pepstock.coderba.client.cm.Language)
	 */
	@Override
	public void setLanguage(Language language) {
		delegated.setLanguage(language);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineSeparator(java.lang.String)
	 */
	@Override
	public void setLineSeparator(String lineSeparator) {
		delegated.setLineSeparator(lineSeparator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTheme(org.pepstock.coderba.client.cm.Theme)
	 */
	@Override
	public void setTheme(Theme theme) {
		delegated.setTheme(theme);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setIndentUnit(int)
	 */
	@Override
	public void setIndentUnit(int indentUnit) {
		delegated.setIndentUnit(indentUnit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSmartIndent(boolean)
	 */
	@Override
	public void setSmartIndent(boolean smartIndent) {
		delegated.setSmartIndent(smartIndent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTabSize(boolean)
	 */
	@Override
	public void setTabSize(boolean tabSize) {
		delegated.setTabSize(tabSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setIndentWithTabs(boolean)
	 */
	@Override
	public void setIndentWithTabs(boolean indentWithTabs) {
		delegated.setIndentWithTabs(indentWithTabs);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setElectricChars(boolean)
	 */
	@Override
	public void setElectricChars(boolean electricChars) {
		delegated.setElectricChars(electricChars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSpecialChars(org.pepstock.coderba.client.utils.RegExp)
	 */
	@Override
	public void setSpecialChars(RegExp specialChars) {
		delegated.setSpecialChars(specialChars);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setDirection(org.pepstock.coderba.client.cm.enums.Direction)
	 */
	@Override
	public void setDirection(Direction direction) {
		delegated.setDirection(direction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setRtlMoveVisually(boolean)
	 */
	@Override
	public void setRtlMoveVisually(boolean rtlMoveVisually) {
		delegated.setRtlMoveVisually(rtlMoveVisually);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setKeyMap(java.lang.String)
	 */
	@Override
	public void setKeyMap(KeyMap keyMap) {
		delegated.setKeyMap(keyMap);
	}

	/* (non-Javadoc)
	 * @see org.pepstock.coderba.client.entities.IsOptions#setExtraKeys(org.pepstock.coderba.client.entities.ExtraKeyMapTable)
	 */
	@Override
	public void setExtraKeys(ExtraKeyMapTable extraKeys) {
		delegated.setExtraKeys(extraKeys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineWrapping(boolean)
	 */
	@Override
	public void setLineWrapping(boolean lineWrapping) {
		delegated.setLineWrapping(lineWrapping);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineNumbers(boolean)
	 */
	@Override
	public void setLineNumbers(boolean lineNumbers) {
		delegated.setLineNumbers(lineNumbers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFirstLineNumber(int)
	 */
	@Override
	public void setFirstLineNumber(int firstLineNumber) {
		delegated.setFirstLineNumber(firstLineNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setGutters(java.lang.String[])
	 */
	@Override
	public void setGutters(String... gutters) {
		delegated.setGutters(gutters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setGutters(java.util.List)
	 */
	@Override
	public void setGutters(List<String> gutters) {
		delegated.setGutters(gutters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFixedGutter(boolean)
	 */
	@Override
	public void setFixedGutter(boolean fixedGutter) {
		delegated.setFixedGutter(fixedGutter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setScrollbarStyle(java.lang.String)
	 */
	@Override
	public void setScrollbarStyle(String scrollbarStyle) {
		delegated.setScrollbarStyle(scrollbarStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCoverGutterNextToScrollbar(boolean)
	 */
	@Override
	public void setCoverGutterNextToScrollbar(boolean coverGutterNextToScrollbar) {
		delegated.setCoverGutterNextToScrollbar(coverGutterNextToScrollbar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setInputStyle(org.pepstock.coderba.client.cm.enums.InputStyle)
	 */
	@Override
	public void setInputStyle(InputStyle inputStyle) {
		delegated.setInputStyle(inputStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		delegated.setReadOnly(readOnly);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setReadOnly(org.pepstock.coderba.client.cm.enums.ReadOnly)
	 */
	@Override
	public void setReadOnly(ReadOnly readOnly) {
		delegated.setReadOnly(readOnly);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setShowCursorWhenSelecting(boolean)
	 */
	@Override
	public void setShowCursorWhenSelecting(boolean showCursorWhenSelecting) {
		delegated.setShowCursorWhenSelecting(showCursorWhenSelecting);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setLineWiseCopyCut(boolean)
	 */
	@Override
	public void setLineWiseCopyCut(boolean lineWiseCopyCut) {
		delegated.setLineWiseCopyCut(lineWiseCopyCut);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPasteLinesPerSelection(boolean)
	 */
	@Override
	public void setPasteLinesPerSelection(boolean pasteLinesPerSelection) {
		delegated.setPasteLinesPerSelection(pasteLinesPerSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSelectionsMayTouch(boolean)
	 */
	@Override
	public void setSelectionsMayTouch(boolean selectionsMayTouch) {
		delegated.setSelectionsMayTouch(selectionsMayTouch);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setUndoDepth(int)
	 */
	@Override
	public void setUndoDepth(int undoDepth) {
		delegated.setUndoDepth(undoDepth);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setHistoryEventDelay(int)
	 */
	@Override
	public void setHistoryEventDelay(int historyEventDelay) {
		delegated.setHistoryEventDelay(historyEventDelay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setTabindex(int)
	 */
	@Override
	public void setTabindex(int tabindex) {
		delegated.setTabindex(tabindex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutofocus(boolean)
	 */
	@Override
	public void setAutofocus(boolean autofocus) {
		delegated.setAutofocus(autofocus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPhrases(java.lang.Object)
	 */
	@Override
	public void setPhrases(Phrases phrases) {
		delegated.setPhrases(phrases);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setDragDrop(boolean)
	 */
	@Override
	public void setDragDrop(boolean dragDrop) {
		delegated.setDragDrop(dragDrop);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAllowDropFileTypes(java.lang.String[])
	 */
	@Override
	public void setAllowDropFileTypes(String... allowDropFileTypes) {
		delegated.setAllowDropFileTypes(allowDropFileTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAllowDropFileTypes(java.util.List)
	 */
	@Override
	public void setAllowDropFileTypes(List<String> allowDropFileTypes) {
		delegated.setAllowDropFileTypes(allowDropFileTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorBlinkRate(int)
	 */
	@Override
	public void setCursorBlinkRate(int cursorBlinkRate) {
		delegated.setCursorBlinkRate(cursorBlinkRate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorScrollMargin(int)
	 */
	@Override
	public void setCursorScrollMargin(int cursorScrollMargin) {
		delegated.setCursorScrollMargin(cursorScrollMargin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setCursorHeight(int)
	 */
	@Override
	public void setCursorHeight(int cursorHeight) {
		delegated.setCursorHeight(cursorHeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setResetSelectionOnContextMenu(boolean)
	 */
	@Override
	public void setResetSelectionOnContextMenu(boolean resetSelectionOnContextMenu) {
		delegated.setResetSelectionOnContextMenu(resetSelectionOnContextMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setPollInterval(int)
	 */
	@Override
	public void setPollInterval(int pollInterval) {
		delegated.setPollInterval(pollInterval);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setFlattenSpans(boolean)
	 */
	@Override
	public void setFlattenSpans(boolean flattenSpans) {
		delegated.setFlattenSpans(flattenSpans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAddModeClass(boolean)
	 */
	@Override
	public void setAddModeClass(boolean addModeClass) {
		delegated.setAddModeClass(addModeClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setMaxHighlightLength(int)
	 */
	@Override
	public void setMaxHighlightLength(int maxHighlightLength) {
		delegated.setMaxHighlightLength(maxHighlightLength);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setViewportMargin(int)
	 */
	@Override
	public void setViewportMargin(int viewportMargin) {
		delegated.setViewportMargin(viewportMargin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setSpellcheck(boolean)
	 */
	@Override
	public void setSpellcheck(boolean spellcheck) {
		delegated.setSpellcheck(spellcheck);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutocorrect(boolean)
	 */
	@Override
	public void setAutocorrect(boolean autocorrect) {
		delegated.setAutocorrect(autocorrect);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setAutocapitalize(boolean)
	 */
	@Override
	public void setAutocapitalize(boolean autocapitalize) {
		delegated.setAutocapitalize(autocapitalize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setWorkTime(int)
	 */
	@Override
	public void setWorkTime(int workTime) {
		delegated.setWorkTime(workTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.cm.entities.IsOptions#setWorkDelay(int)
	 */
	@Override
	public void setWorkDelay(int workDelay) {
		delegated.setWorkDelay(workDelay);
	}

	/**
	 * Returns the string JSON representation of the object.
	 * 
	 * @return the string JSON representation of the object.
	 */
	public final String toJSON() {
		return delegated.toJSON();
	}

	/**
	 * Returns the native object of the options to create an editor.<br>
	 * This is applicable and ONLY when a editor instance is creating and at that time the options are the USER one.<br>
	 * If not, it throws an exception.
	 * 
	 * @return the native object of the options to create an editor
	 */
	final NativeObject getObject() {
		// checks if the current delegate is a USER options
		// USER options are mandatory to create an editor
		if (delegated instanceof UserOptions) {
			// casts
			UserOptions userOptions = (UserOptions) delegated;
			// returns native object
			return userOptions.getObject();
		}
		// if here, there is an invalid call to this method
		// then exception
		throw new UnsupportedOperationException("Unbale to provide the natie object when editor is already initialized");
	}

	// --------------------------------
	// --- PRIVATE EVENT METHODS
	// --------------------------------

	/**
	 * A function that, given a special character identified by the specialChars option, produces a DOM node that is used to
	 * represent the character.
	 * 
	 * @param character a special character identified by the specialChars option
	 * @return a DOM node that is used to represent the character
	 */
	private Element onSpecialCharPlaceholder(char character) {
		// checks if callback is consistent
		if (specialCharPlaceholder != null) {
			// invokes callback
			return specialCharPlaceholder.handle(character);
		}
		// if here, callback is not set
		// then returns an empty DIV element
		return Document.get().createDivElement();
	}

	/**
	 * A function used to format line numbers.<br>
	 * The function is passed the line number, and should return a string that will be shown in the gutter.
	 * 
	 * @param line line number to format
	 * @return string that will be shown in the gutter
	 */
	private String onLineNumberFormatter(int line) {
		// checks if callback is consistent
		if (lineNumberFormatter != null) {
			// invokes the callback
			String result = lineNumberFormatter.format(line);
			// checks if result consistent
			if (result != null) {
				return result;
			}
		}
		// if here, the result is not consistent
		// and then returns a string representation of line
		return String.valueOf(line);
	}

	/**
	 * Allow you to configure the behavior of mouse selection and dragging.<br>
	 * The function is called when the left mouse button is pressed.
	 * 
	 * @param editor CodeMirror editor instance
	 * @param repeat type of click by mouse
	 * @param event native event
	 * @return mouse configuration native object
	 */
	private NativeObject onConfigureMouse(NativeEditor editor, String repeat, NativeEvent event) {
		// creates a mouse repeat object
		MouseRepeat confMouseRepeat = Key.getKeyByValue(MouseRepeat.class, repeat, MouseRepeat.SINGLE);
		// gets editor area from native editor
		EditorArea area = editor.getEditorArea();
		// if editor area and callback are consistent
		if (area != null && configureMouse != null) {
			// invokes callback
			MouseConfiguration result = configureMouse.handle(area, confMouseRepeat, event);
			// checks if result is consistent
			if (result != null) {
				return result.getObject();
			}
		}
		// creates a default mouse configuratio
		MouseConfiguration result = new MouseConfiguration(confMouseRepeat);
		// returns the native object
		return result.getObject();
	}

}
