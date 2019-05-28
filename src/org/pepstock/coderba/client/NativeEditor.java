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

import org.pepstock.coderba.client.commons.Array;
import org.pepstock.coderba.client.commons.ArrayObject;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.entities.NativeDocument;
import org.pepstock.coderba.client.entities.Position;
import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.CODE_MIRROR)
public final class NativeEditor {

	/**
	 * to avoid any instantiation
	 */
	private NativeEditor() {
		// do nothing
	}
	
	/**
	 * 	WHAT IS MISSING
	 * 
        Sets the gutter marker for the given gutter (identified by its CSS class, see the gutters option) to the given value.
        Value can be either null, to clear the marker, or a DOM element, to set it. The DOM element will be shown in the specified gutter next to the specified line. 
        setGutterMarker(line: any, gutterID: string, value: HTMLElement | null): CodeMirror.LineHandle;

        Remove all gutter markers in the gutter with the given ID. 
        clearGutter(gutterID: string): void;

        Set a CSS class name for the given line.line can be a number or a line handle.
        where determines to which element this class should be applied, can can be one of "text" (the text element, which lies in front of the selection),
        "background"(a background element that will be behind the selection),
        or "wrap" (the wrapper node that wraps all of the line's elements, including gutter elements).
        class should be the name of the class to apply. 
        addLineClass(line: any, where: string, _class_: string): CodeMirror.LineHandle;

        Remove a CSS class from a line.line can be a line handle or number.
        where should be one of "text", "background", or "wrap"(see addLineClass).
        class can be left off to remove all classes for the specified node, or be a string to remove only a specific class. 
        removeLineClass(line: any, where: string, class_?: string): CodeMirror.LineHandle;

        Returns the line number, text content, and marker status of the given line, which can be either a number or a line handle. 
        lineInfo(line: any): {
            line: any;
            handle: any;
            text: string;
            // Object mapping gutter IDs to marker elements. 
            gutterMarkers: any;
            textClass: string;
            bgClass: string;
            wrapClass: string;
            // Array of line widgets attached to this line. 
            widgets: any;
        };

        Puts node, which should be an absolutely positioned DOM node, into the editor, positioned right below the given { line , ch } position.
        When scrollIntoView is true, the editor will ensure that the entire node is visible (if possible).
        To remove the widget again, simply use DOM methods (move it somewhere else, or call removeChild on its parent). 
        addWidget(pos: CodeMirror.Position, node: HTMLElement, scrollIntoView: boolean): void;

        Adds a line widget, an element shown below a line, spanning the whole of the editor's width, and moving the lines below it downwards.
        line should be either an integer or a line handle, and node should be a DOM node, which will be displayed below the given line.
        options, when given, should be an object that configures the behavior of the widget.
        Note that the widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might in some cases affect it. 
        addLineWidget(line: any, node: HTMLElement, options?: CodeMirror.LineWidgetOptions): CodeMirror.LineWidget;
	 **/

	// -----------------------------------
	// --- OBJECT methods
	// -----------------------------------

	@JsProperty(name = "options")
	native NativeObject getOptions();

	// ------------------------------------------
	// --- OPTIONS methods
	// ------------------------------------------

	// --- INTEGER
	/**
	 * Sets a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, int value);

	/**
	 * Returns a value (int) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	@JsMethod(name = "getOption")
	native int getOptionValueAsInt(String key);

	// --- DOUBLES
	/**
	 * Sets a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, double value);

	/**
	 * Returns a value (double) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	@JsMethod(name = "getOption")
	native double getOptionValueAsDouble(String key);

	// --- BOOLEANS
	/**
	 * Sets a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, boolean value);

	/**
	 * Returns a value (boolean) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	@JsMethod(name = "getOption")
	native boolean getOptionValueAsBoolean(String key);

	// --- STRINGS
	/**
	 * Returns a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param defaultValue default value if the property is missing
	 * @return value of the property
	 */
	@JsMethod(name = "getOption")
	native String getOptionValueAsString(String key);

	/**
	 * Sets a value (string) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, String value);

	// --- NATIVE OBJECTS
	/**
	 * Returns a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	@JsMethod(name = "getOption")
	native NativeObject getOptionValue(String key);

	/**
	 * Sets a value (JavaScript Object) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, NativeObject value);

	// --- CALLBACKS
	/**
	 * Sets a value (callback proxy function) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, CallbackProxy.Proxy value);

	// --- ARRAYS
	/**
	 * Returns a value (array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param <T> type of array
	 * @return value of the property or <code>null</code> if not exist
	 */
	@JsMethod(name = "getOption")
	native <T extends Array> T getArrayOptionValue(String key);

	/**
	 * Sets a value (Array) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 * @param <T> type of array
	 */
	@JsMethod(name = "setOption")
	native <T extends Array> void setArrayOptionValue(String key, T value);

	// --- REGEXP
	/**
	 * Returns a value (regExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	@JsMethod(name = "getOption")
	native RegExp getOptionValueAsRegExp(String key);

	/**
	 * Sets a value (RegExp) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, RegExp value);

	// --- DOM Element
	/**
	 * Returns a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @return value of the property or <code>null</code> if not there
	 */
	@JsMethod(name = "getOption")
	native Element getOptionValueAsElement(String key);

	/**
	 * Sets a value (DOM element) into embedded JavaScript object at specific property.
	 * 
	 * @param key key of the property of JavaScript object.
	 * @param value value to be set
	 */
	@JsMethod(name = "setOption")
	native void setOptionValue(String key, Element value);
	
	// ------------------------------------------
	// --- END OPTIONS methods
	// ------------------------------------------

	/**
	 * Get the content of the current editor document.
	 * 
	 * @return he content of the current editor document
	 */
	native String getValue();

	/**
	 * Get the content of the current editor document. You can pass it an optional argument to specify the string to be used to
	 * separate lines (defaults to "\n").
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n")
	 * @return the content of the current editor document
	 */
	native String getValue(String separator);

	/**
	 * Set the content of the current editor document.
	 * @param content the content of the current editor document
	 */
	native void setValue(String content);

	/**
	 * Tells you whether the editor currently has focus.
	 * 
	 * @return Tells you whether the editor currently has focus.
	 */
	native boolean hasFocus();

	/**
	 * Used to find the target position for horizontal cursor motion.<br>
	 * "start" is a position object, amount an integer (may be negative), and unit one of the string "char", "column", or
	 * "word".<br>
	 * Will return a position that is produced by moving amount times the distance specified by unit.<br>
	 * When visually is true, motion in right-to-left text will be visual rather than logical.<br>
	 * When the motion was clipped by hitting the end or start of the document, the returned value will have a hitSide property
	 * set to true.
	 * 
	 * @return the target position for horizontal cursor motion
	 */
	native NativeObject findPosH(NativeObject start, int amount, String unit, boolean visually);

	/**
	 * Similar to findPosH, but used for vertical motion.<br>
	 * Unit may be "line" or "page".<br>
	 * The other arguments and the returned value have the same interpretation as they have in "findPosH".
	 * 
	 * @return the target position for vertical cursor motion
	 */
	native NativeObject findPosV(NativeObject start, int amount, String unit);

	/**
	 * Returns the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position.
	 * 
	 * @return the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position
	 */
	native NativeObject findWordAt(NativeObject pos);

	/**
	 * Attach an additional key map to the editor.<br>
	 * This is mostly useful for addons that need to register some key handlers without trampling on the extraKeys option.<br>
	 * Maps added in this way have a higher precedence than the extraKeys and keyMap options, and between them, the maps added
	 * earlier have a lower precedence than those added later, unless the bottom argument was passed, in which case they end up
	 * below other key maps added with this method.
	 * 
	 * @param keyMapName name of keymap
	 * @param bottom if <code>true</code>, it ends up below other key maps added with this method.
	 */
	// FIXME
	native void addKeyMap(String keyMapName, boolean bottom);

	/**
	 * Disable a keymap added with addKeyMap. Either pass in the key map object itself, or a string, which will be compared
	 * against the name property of the active key maps.
	 * 
	 * @param keyMapName name of keymap
	 */
	// FIXME
	native void removeKeyMap(String keyMapName);

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.<br>
	 * mode can be a mode spec or a mode object (an object with a token method).
	 * 
	 * @param mode
	 */
	native void addOverlay(String mode);

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.<br>
	 * mode can be a mode spec or a mode object (an object with a token method).
	 * 
	 * @param mode
	 * @param options
	 */
	native void addOverlay(String mode, NativeObject options);

	/**
	 * Pass this the exact value passed for the mode parameter to addOverlay, or a string that corresponds to the name property
	 * of that value, to remove an overlay again.
	 * 
	 * @param mode
	 */
	native void removeOverlay(String mode);

	/**
	 * Register an event handler for the given event type (a string) on the editor instance. There is also a
	 * CodeMirror.on(object, type, func) version that allows registering of events on any object.
	 * 
	 * @param type
	 * @param function
	 */
	// FIXME
	native void on(String type, CallbackProxy.Proxy function);

	/**
	 * Remove an event handler on the editor instance. An equivalent CodeMirror.off(object, type, func) also exists.
	 * 
	 * @param type
	 * @param function
	 */
	// FIXME
	native void off(String type, CallbackProxy.Proxy function);

	/**
	 * Retrieve the currently active document from an editor.
	 * 
	 * @return the currently active document from an editor
	 */
	native NativeDocument getDoc();

	/**
	 * Attach a new document to the editor. Returns the old document, which is now no longer associated with an editor.
	 * 
	 * @param doc new document to the editor
	 * @return the old document, which is now no longer associated with an editor
	 */
	native void swapDoc(NativeDocument doc);

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules). width and height are numbers
	 * (interpreted as pixels).
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	native void setSize(double width, double height);

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules). width and height are CSS units
	 * ("100%", for example). You can pass null for either of them to indicate that that dimension should not be changed.
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	native void setSize(String width, String height);

	/**
	 * Scroll the editor to a given (pixel) position. Both arguments may be left as null or undefined to have no effect.
	 * 
	 * @param x X position to scroll
	 * @param y Y position to scroll
	 */
	native void scrollTo(int x, int y);

	/**
	 * Get an scroll area object that represents the current scroll position, the size of the scrollable area, and the size of
	 * the visible area (minus scrollbars).
	 * 
	 * @return an scroll area object that represents the current scroll position, the size of the scrollable area, and the size
	 *         of the visible area (minus scrollbars).
	 */
	native NativeObject getScrollInfo();

	/**
	 * Scrolls the given position into view. what may be null to scroll the cursor into view, a position to scroll a character
	 * into view, a coordinate pixel range (in editor-local coordinates), or a coordinate range containing either two character
	 * positions or two pixel squares.<br>
	 * The margin parameter is optional.<br>
	 * When given, it indicates the amount of vertical pixels around the given area that should be made visible as well.
	 * 
	 * @param what may be null to scroll the cursor into view, a position to scroll a character into view, a coordinate pixel
	 *            range (in editor-local coordinates), or a coordinate range containing either two character positions or two
	 *            pixel squares
	 * @param margin the amount of vertical pixels around the given area that should be made visible as well
	 */
	native void scrollIntoView(NativeObject what, int margin);

	/**
	 * Returns an area object containing the coordinates of the cursor position.<br>
	 * If mode is "local", they will be relative to the top-left corner of the editable document.<br>
	 * If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 * If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled) window.<br>
	 * <code>where</code> can be a boolean indicating whether you want the start (true) or the end (false) of the selection, or,
	 * if a position object is given, it specifies the precise position at which you want to measure.
	 * 
	 * @param where <code>where</code> can be a boolean indicating whether you want the start (true) or the end (false) of the
	 *            selection
	 * @param mode coordinates mode
	 * @return an area object containing the coordinates of the cursor position
	 */
	native NativeObject cursorCoords(boolean where, String mode);

	/**
	 * Returns an area object containing the coordinates of the cursor position.<br>
	 * If mode is "local", they will be relative to the top-left corner of the editable document.<br>
	 * If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 * If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled) window.<br>
	 * <code>where</code> can be a boolean indicating whether you want the start (true) or the end (false) of the selection, or,
	 * if a position object is given, it specifies the precise position at which you want to measure.
	 * 
	 * @param where <code>where</code> is a position object is given, it specifies the precise position at which you want to
	 *            measure.
	 * @param mode coordinates mode
	 * @return an area object containing the coordinates of the cursor position
	 */
	native NativeObject cursorCoords(NativeObject where, String mode);

	/**
	 * Returns the position and dimensions of an arbitrary character.<br>
	 * <code>pos</code> should be a position object.<br>
	 * This differs from cursorCoords in that it'll give the size of the whole character, rather than just the position that the
	 * cursor would have when it would sit at that position.
	 * 
	 * @param pos a position object
	 * @param mode coordinates mode
	 * @return an area object containing the coordinates of the cursor position
	 */
	native NativeObject charCoords(NativeObject pos, String mode);

	/**
	 * Given a point object (e.g. coordinates of a mouse event) returns the position that corresponds to it.<br>
	 * The optional mode parameter determines relative to what the coordinates are interpreted.<br>
	 * It may be "window", "page" (the default), or "local".
	 * 
	 * @param object point object
	 * @param mode coordinates mode
	 * @return a position object
	 */
	native NativeObject coordsChar(NativeObject object, String mode);

	/**
	 * Computes the line at the given pixel height. mode can be one of the same strings that coordsChar accepts.
	 * 
	 * @param height the line at the given pixel height
	 * @param mode coordinates mode
	 * @return the line row
	 */
	native int lineAtHeight(double height, String mode);

	/**
	 * Computes the height of the top of a line, in the coordinate system specified by mode (see coordsChar), which defaults to
	 * "page".<br>
	 * When a line below the bottom of the document is specified, the returned value is the bottom of the last line in the
	 * document.<br>
	 * By default, the position of the actual text is returned.<br>
	 * If "includeWidgets" is true and the line has line widgets, the position above the first line widget is returned.
	 * 
	 * @param line the line row
	 * @param mode coordinates mode
	 * @param includeWidgets if true and the line has line widgets, the position above the first line widget is returned
	 * @return the height of the top of a line, in the coordinate system specified by mode
	 */
	native double heightAtLine(int line, String mode, boolean includeWidgets);

	/**
	 * FIXME
	 * 
	 * @param line
	 * @param mode
	 * @return
	 */
	// native double heightAtLine(LineHandle line, String mode: string, boolean includeWidgets);

	/**
	 * Returns the line height of the default font for the editor.
	 * 
	 * @return the line height of the default font for the editor.
	 */
	native double defaultTextHeight();

	/**
	 * Returns the pixel width of an 'x' in the default font for the editor.<br>
	 * Note that for non-monospace fonts, this is mostly useless, and even for monospace fonts, non-ascii characters might have
	 * a different width.
	 * 
	 * @return the pixel width of an 'x' in the default font for the edito
	 */
	native double defaultCharWidth();

	/**
	 * Returns a coordinate object indicating the start (inclusive) and end (exclusive) of the currently rendered part of the
	 * document.<br>
	 * In big documents, when most content is scrolled out of view, CodeMirror will only render the visible part, and a margin
	 * around it.
	 * 
	 * @return a coordinate object indicating the start (inclusive) and end (exclusive) of the currently rendered part of the
	 *         document
	 */
	native NativeObject getViewport();

	/**
	 * If your code does something to change the size of the editor element (window resizes are already listened for), or
	 * unhides it, you should probably follow up by calling this method to ensure CodeMirror is still looking as intended. See
	 * also the autorefresh addon.
	 */
	native void refresh();

	/**
	 * Gets the inner mode at a given position. This will return the same as getMode for simple modes, but will return an inner
	 * mode for nesting modes (such as htmlmixed).
	 * 
	 * @param position position to use
	 * @return the inner mode at a given position
	 */
	// FIXME
	native Object getModeAt(NativeObject position);

	/**
	 * Retrieves information about the token the current mode found before the given position (a position object). The returned
	 * object has the following properties:<br>
	 * <br>
	 * start The character (on the given line) at which the token starts.<br>
	 * end The character at which the token ends.<br>
	 * string The token's string.<br>
	 * type The token type the mode assigned to the token, such as "keyword" or "comment" (may also be null).<br>
	 * state The mode's state at the end of this token.<br>
	 * <br>
	 * If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not specified, the
	 * token will use cached state information, which will be faster but might not be accurate if edits were recently made and
	 * highlighting has not yet completed.
	 * 
	 * @param pos position of token
	 * @param precise If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not
	 *            specified, the token will use cached state information, which will be faster but might not be accurate if
	 *            edits were recently made and highlighting has not yet completed.
	 * @return a token instance
	 */
	native NativeObject getTokenAt(NativeObject pos, boolean precise);

	/**
	 * This is similar to getTokenAt, but collects all tokens for a given line into an array.<br>
	 * It is much cheaper than repeatedly calling getTokenAt, which re-parses the part of the line before the token for every
	 * call.<br>
	 * If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not specified, the
	 * token will use cached state information, which will be faster but might not be accurate if edits were recently made and
	 * highlighting has not yet completed.
	 * 
	 * @param line the line row
	 * @param precise If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not
	 *            specified, the token will use cached state information, which will be faster but might not be accurate if
	 *            edits were recently made and highlighting has not yet completed.
	 * @return an array of tokens
	 */
	native ArrayObject getLineTokens(int line, boolean precise);

	/**
	 * This is a (much) cheaper version of getTokenAt useful for when you just need the type of the token at a given position,
	 * and no other information.<br>
	 * Will return null for unstyled tokens, and a string, potentially containing multiple space-separated style names,
	 * otherwise.
	 * 
	 * @param pos position to use
	 * @return a type of token
	 */
	native String getTokenTypeAt(NativeObject pos);

	/**
	 * Fetch the set of applicable helper values for the given position. Helpers provide a way to look up functionality
	 * appropriate for a mode. The type argument provides the helper namespace (see registerHelper), in which the values will be
	 * looked up. When the mode itself has a property that corresponds to the type, that directly determines the keys that are
	 * used to look up the helper values (it may be either a single string, or an array of strings). Failing that, the mode's
	 * helperType property and finally the mode's name are used.<br>
	 * <br>
	 * For example, the JavaScript mode has a property fold containing "brace". When the brace-fold addon is loaded, that
	 * defines a helper named brace in the fold namespace. This is then used by the foldcode addon to figure out that it can use
	 * that folding function to fold JavaScript code.<br>
	 * <br>
	 * When any 'global' helpers are defined for the given namespace, their predicates are called on the current mode and
	 * editor, and all those that declare they are applicable will also be added to the array that is returned.
	 * 
	 * @param pos
	 * @param type
	 * @return
	 */
	// FIXME
	native ArrayString getHelpers(Position pos, String type); // array<helper>

	/**
	 * Returns the first applicable helper value.
	 * 
	 * @param pos
	 * @param type
	 * @return
	 */
	// FIXME
	native Object getHelper(Position pos, String type); // helper

	/**
	 * Returns the mode's parser state, if any, at the end of the given line number. If no line number is given, the state at
	 * the end of the document is returned. This can be useful for storing parsing errors in the state, or getting other kinds
	 * of contextual information for a line. precise is defined as in getTokenAt().
	 * 
	 * @param line
	 * @param precise
	 * @return
	 */
	// FIXME
	native Object getStateAfter(int line, boolean precise);

	/**
	 * CodeMirror internally buffers changes and only updates its DOM structure after it has finished performing some operation.
	 * If you need to perform a lot of operations on a CodeMirror instance, you can call this method with a function argument.
	 * It will call the function, buffering up all changes, and only doing the expensive update after the function returns. This
	 * can be a lot faster. The return value from this method will be the return value of your function.
	 * 
	 * @param function
	 * @return
	 */
	// FIXME
	native Object operation(CallbackProxy.Proxy function);

	/**
	 * In normal circumstances, use the above operation method. But if you want to buffer operations happening asynchronously,
	 * or that can't all be wrapped in a callback function, you can call startOperation to tell CodeMirror to start buffering
	 * changes, and endOperation to actually render all the updates. Be careful: if you use this API and forget to call
	 * endOperation, the editor will just never update.
	 */
	// FIXME
	native void startOperation();

	/**
	 * In normal circumstances, use the above operation method. But if you want to buffer operations happening asynchronously,
	 * or that can't all be wrapped in a callback function, you can call startOperation to tell CodeMirror to start buffering
	 * changes, and endOperation to actually render all the updates. Be careful: if you use this API and forget to call
	 * endOperation, the editor will just never update.
	 */
	// FIXME
	native void endOperation();

	/**
	 * Adjust the indentation of the given line. The second argument (which defaults to "smart") may be one of: <br>
	 * "prev" Base indentation on the indentation of the previous line.<br>
	 * "smart" Use the mode's smart indentation if available, behave like "prev" otherwise.<br>
	 * "add" Increase the indentation of the line by one indent unit.<br>
	 * "subtract" Reduce the indentation of the line.<br>
	 * <br>
	 * 
	 * @param line line row to indent
	 * @param dir the direction
	 */
	native void indentLine(int line, String dir);

	/**
	 * Adjust the indentation of the given line. The second argument is <code>int</code> to add (positive number) or reduce
	 * (negative number) the indentation by the given amount of spaces.
	 * 
	 * @param line line row to indent
	 * @param dir amount of spaces
	 */
	native void indentLine(int line, int dir);

	/**
	 * Switches between overwrite and normal insert mode.
	 */
	native void toggleOverwrite();

	/**
	 * Sets the overwrite mode to a specific state (when given an argument).
	 * 
	 * @param value to set overwrite mode
	 */
	native void toggleOverwrite(boolean value);

	/**
	 * Tells you whether the editor's content can be edited by the user.
	 * 
	 * @return Tells you whether the editor's content can be edited by the user.
	 */
	native boolean isReadOnly();

	/**
	 * Runs the command with the given name on the editor.
	 * 
	 * @param name the command with the given name on the editor.
	 */
	native void execCommand(String name);

	/**
	 * Give the editor focus.
	 */
	native void focus();

	/**
	 * Allow the given string to be translated with the phrases option.
	 * 
	 * @param text string to be translated with the phrases option.
	 * @return translated text.
	 */
	native String phrase(String text);

	/**
	 * Returns the input field for the editor. Will be a textarea or an editable div, depending on the value of the inputStyle
	 * option.
	 * 
	 * @return the input field for the editor
	 */
	native Element getInputField();

	/**
	 * Returns the DOM node that represents the editor, and controls its size. Remove this from your tree to delete an editor
	 * instance.
	 * 
	 * @return the DOM node that represents the editor
	 */
	native Element getWrapperElement();

	/**
	 * Returns the DOM node that is responsible for the scrolling of the editor.
	 * 
	 * @return the DOM node that is responsible for the scrolling of the editor
	 */
	native Element getScrollerElement();

	/**
	 * Fetches the DOM node that contains the editor gutters.
	 * 
	 * @return the DOM node that contains the editor gutters
	 */
	native Element getGutterElement();

	/**
	 * Returns the CODERBA ID, set to the chart.
	 * 
	 * @return the CODERBA ID
	 */
	@JsOverlay
	public String getId() {
		return Id.get(getOptions());
	}
}