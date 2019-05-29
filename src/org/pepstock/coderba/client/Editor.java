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

import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayObject;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.defaults.GlobalDefaults;
import org.pepstock.coderba.client.entities.Anchor;
import org.pepstock.coderba.client.entities.Area;
import org.pepstock.coderba.client.entities.Coordinate;
import org.pepstock.coderba.client.entities.Document;
import org.pepstock.coderba.client.entities.OverlayOptions;
import org.pepstock.coderba.client.entities.Point;
import org.pepstock.coderba.client.entities.Position;
import org.pepstock.coderba.client.entities.Range;
import org.pepstock.coderba.client.entities.ScrollArea;
import org.pepstock.coderba.client.entities.Token;
import org.pepstock.coderba.client.enums.CoordinatesMode;
import org.pepstock.coderba.client.enums.HorizontalFindUnit;
import org.pepstock.coderba.client.enums.IndentLineMode;
import org.pepstock.coderba.client.enums.VerticalFindUnit;

import com.google.gwt.dom.client.Element;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Editor {

	private final NativeEditor nativeObject;
	
	private Document document = null;

	/**
	 * @param nativeObject
	 */
	Editor(NativeEditor nativeObject) {
		this.nativeObject = nativeObject;
		this.document = new Document(nativeObject.getDoc());
	}

	/**
	 * Sets the CODERBA id to editor options.
	 * 
	 * @param id the CODERBA id
	 */
	final void setId(String id) {
		Id.set(nativeObject.getOptions(), id);
	}

	/**
	 * Returns the CODERBA id from editor options.
	 * 
	 * @return the CODERBA id
	 */
	public final String getid() {
		return Id.get(nativeObject.getOptions());
	}

	/**
	 * Get the content of the current editor document.
	 * 
	 * @return he content of the current editor document
	 */
	public final String getValue() {
		return nativeObject.getValue();
	}

	/**
	 * Get the content of the current editor document. You can pass it an optional argument to specify the string to be used to
	 * separate lines (defaults to "\n").
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n")
	 * @return the content of the current editor document
	 */
	public final String getValue(String separator) {
		if (separator != null) {
			return nativeObject.getValue(separator);
		}
		return getValue();
	}

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
	public final boolean hasFocus() {
		return nativeObject.hasFocus();
	}

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
	public final Position findPosH(Position start, int amount, HorizontalFindUnit unit, boolean visually) {
		if (start != null && unit != null) {
			return nativeObject.findPosH(start, amount, unit.value(), visually);
		}
		return GlobalDefaults.get().getPosition();
	}

	/**
	 * Similar to findPosH, but used for vertical motion.<br>
	 * Unit may be "line" or "page".<br>
	 * The other arguments and the returned value have the same interpretation as they have in "findPosH".
	 * 
	 * @return the target position for vertical cursor motion
	 */
	public final Position findPosV(Position start, int amount, VerticalFindUnit unit) {
		if (start != null && unit != null) {
			return nativeObject.findPosV(start, amount, unit.value());
		}
		return GlobalDefaults.get().getPosition();
	}

	/**
	 * Returns the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position.
	 * 
	 * @return the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position
	 */
	public final Anchor findWordAt(Position pos) {
		if (pos != null) {
			return nativeObject.findWordAt(pos);
		}
		return GlobalDefaults.get().getAnchor();
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.<br>
	 * mode can be a mode spec or a mode object (an object with a token method).
	 * 
	 * @param mode
	 */
	public final void addOverlay(Mode mode) {
		addOverlay(mode, null);
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.<br>
	 * mode can be a mode spec or a mode object (an object with a token method).
	 * 
	 * @param mode
	 * @param options
	 */
	public final void addOverlay(Mode mode, OverlayOptions options) {
		if (mode != null) {
			if (options != null) {
				nativeObject.addOverlay(mode.getName(), options);
			} else {
				nativeObject.addOverlay(mode.getName());
			}
		}
	}

	/**
	 * Pass this the exact value passed for the mode parameter to addOverlay, or a string that corresponds to the name property
	 * of that value, to remove an overlay again.
	 * 
	 * @param mode
	 */
	public final void removeOverlay(Mode mode) {
		if (mode != null) {
			nativeObject.removeOverlay(mode.getName());
		}
	}

	/**
	 * Retrieve the currently active document from an editor.
	 * 
	 * @return the currently active document from an editor
	 */
	public final Document getDocument() {
		return document;
	}

	/**
	 * Attach a new document to the editor. Returns the old document, which is now no longer associated with an editor.
	 * 
	 * @param doc new document to the editor
	 * @return the old document, which is now no longer associated with an editor
	 */
	public final Document swapDoc(Document doc) {
		Document currentDoc = getDocument();
		if (doc != null) {
			nativeObject.swapDoc(doc.getNativeDocument());
			this.document = doc;
		}
		return currentDoc;
	}

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules). width and height are numbers
	 * (interpreted as pixels).
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	public final void setSize(double width, double height) {
		nativeObject.setSize(width, height);
	}

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules). width and height are CSS units
	 * ("100%", for example). You can pass null for either of them to indicate that that dimension should not be changed.
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	public final void setSize(String width, String height) {
		nativeObject.setSize(width, height);
	}

	/**
	 * Scroll the editor to a given (pixel) position. Both arguments may be left as null or undefined to have no effect.
	 * 
	 * @param x X position to scroll
	 * @param y Y position to scroll
	 */
	public final void scrollTo(int x, int y) {
		nativeObject.scrollTo(x, y);
	}

	/**
	 * Get an scroll area object that represents the current scroll position, the size of the scrollable area, and the size of
	 * the visible area (minus scrollbars).
	 * 
	 * @return an scroll area object that represents the current scroll position, the size of the scrollable area, and the size
	 *         of the visible area (minus scrollbars).
	 */
	public final ScrollArea getScrollInfo() {
		return nativeObject.getScrollInfo();
	}

	/**
	 * Scrolls the given position into view. <code>what</code>what may be null to scroll the cursor into view or a position to
	 * scroll a character into view<br>
	 * The margin parameter is optional.<br>
	 * When given, it indicates the amount of vertical pixels around the given area that should be made visible as well.
	 * 
	 * @param what may be null to scroll the cursor into view, a position to scroll a character into view
	 * @param margin the amount of vertical pixels around the given area that should be made visible as well
	 */
	public final void scrollIntoView(Position what, int margin) {
		nativeObject.scrollIntoView(what, margin);
	}

	/**
	 * Scrolls the given position into view. what may be null to scroll the cursor into view or a coordinate pixel range (in
	 * editor-local coordinates)<br>
	 * The margin parameter is optional.<br>
	 * When given, it indicates the amount of vertical pixels around the given area that should be made visible as well.
	 * 
	 * @param what may be null to scroll the cursor into view, a coordinate pixel range (in editor-local coordinates)
	 * @param margin the amount of vertical pixels around the given area that should be made visible as well
	 */
	public final void scrollIntoView(Range what, int margin) {
		nativeObject.scrollIntoView(what, margin);
	}

	/**
	 * Scrolls the given position into view. what may be null to scroll the cursor into view or a coordinate range containing
	 * either two character positions or two pixel squares.<br>
	 * The margin parameter is optional.<br>
	 * When given, it indicates the amount of vertical pixels around the given area that should be made visible as well.
	 * 
	 * @param what may be null to scroll the cursor into view or a coordinate range containing either two character positions or
	 *            two pixel squares
	 * @param margin the amount of vertical pixels around the given area that should be made visible as well
	 */
	public final void scrollIntoView(Area what, int margin) {
		nativeObject.scrollIntoView(what, margin);
	}

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
	public final Area cursorCoords(boolean where, CoordinatesMode mode) {
		if (mode != null) {
			return nativeObject.cursorCoords(where, mode.value());
		} else {
			return nativeObject.cursorCoords(where, CoordinatesMode.PAGE.value());
		}
	}

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
	public final Area cursorCoords(Position where, CoordinatesMode mode) {
		if (where != null) {
			if (mode != null) {
				return nativeObject.cursorCoords(where, mode.value());
			} else {
				return nativeObject.cursorCoords(where, CoordinatesMode.PAGE.value());
			}
		}
		// FIXME default value
		return null;
	}

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
	public final Area charCoords(Position position, CoordinatesMode mode) {
		if (position != null) {
			if (mode != null) {
				return nativeObject.charCoords(position, mode.value());
			} else {
				return nativeObject.charCoords(position, CoordinatesMode.PAGE.value());
			}
		}
		// FIXME default value
		return null;
	}

	/**
	 * Given a point object (e.g. coordinates of a mouse event) returns the position that corresponds to it.<br>
	 * The optional mode parameter determines relative to what the coordinates are interpreted.<br>
	 * It may be "window", "page" (the default), or "local".
	 * 
	 * @param point point object
	 * @param mode coordinates mode
	 * @return a position object
	 */
	public final Position coordsChar(Point point, CoordinatesMode mode) {
		if (point != null) {
			if (mode != null) {
				return nativeObject.coordsChar(point, mode.value());
			} else {
				return nativeObject.coordsChar(point, CoordinatesMode.PAGE.value());
			}
		}
		// FIXME default value
		return null;
	}

	/**
	 * Computes the line at the given pixel height. mode can be one of the same strings that coordsChar accepts.
	 * 
	 * @param height the line at the given pixel height
	 * @param mode coordinates mode
	 * @return the line row
	 */
	public final int lineAtHeight(double height, CoordinatesMode mode) {
		if (mode != null) {
			return nativeObject.lineAtHeight(height, mode.value());
		} else {
			return nativeObject.lineAtHeight(height, CoordinatesMode.PAGE.value());
		}
	}

	/**
	 * Computes the height of the top of a line, in the coordinate system specified by mode (see coordsChar), which defaults to
	 * "page".<br>
	 * When a line below the bottom of the document is specified, the returned value is the bottom of the last line in the
	 * document.<br>
	 * By default, the position of the actual text is returned.
	 * 
	 * @param line the line row
	 * @param mode coordinates mode
	 * @return the height of the top of a line, in the coordinate system specified by mode
	 */
	public final double heightAtLine(int line, CoordinatesMode mode) {
		return heightAtLine(line, mode, false);
	}

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
	public final double heightAtLine(int line, CoordinatesMode mode, boolean includeWidgets) {
		if (mode != null) {
			return nativeObject.heightAtLine(line, mode.value(), includeWidgets);
		} else {
			return nativeObject.heightAtLine(line, CoordinatesMode.PAGE.value(), includeWidgets);
		}
	}

	/**
	 * Returns the line height of the default font for the editor.
	 * 
	 * @return the line height of the default font for the editor.
	 */
	public final double getDefaultTextHeight() {
		return nativeObject.defaultTextHeight();
	}

	/**
	 * Returns the pixel width of an 'x' in the default font for the editor.<br>
	 * Note that for non-mono space fonts, this is mostly useless, and even for mono space fonts, non-ascii characters might
	 * have a different width.
	 * 
	 * @return the pixel width of an 'x' in the default font for the edito
	 */
	public final double getDefaultCharWidth() {
		return nativeObject.defaultCharWidth();
	}

	/**
	 * Returns a coordinate object indicating the start (inclusive) and end (exclusive) of the currently rendered part of the
	 * document.<br>
	 * In big documents, when most content is scrolled out of view, CodeMirror will only render the visible part, and a margin
	 * around it.
	 * 
	 * @return a coordinate object indicating the start (inclusive) and end (exclusive) of the currently rendered part of the
	 *         document
	 */
	public final Coordinate getViewport() {
		return nativeObject.getViewport();
	}

	/**
	 * If your code does something to change the size of the editor element (window resizes are already listened for), or
	 * unhides it, you should probably follow up by calling this method to ensure CodeMirror is still looking as intended. See
	 * also the auto-refresh addon.
	 */
	public final void refresh() {
		nativeObject.refresh();
	}

	/**
	 * Gets the inner mode at a given position. This will return the same as getMode for simple modes, but will return an inner
	 * mode for nesting modes (such as htmlmixed).
	 * 
	 * @param position position to use
	 * @return the inner mode at a given position
	 */
	// FIXME
	public final Object getModeAt(Position position) {
		if (position != null) {
			return nativeObject.getModeAt(position);
		}
		// FIXME default
		return null;
	}

	/**
	 * Retrieves information about the token the current mode found before the given position (a position object). The returned
	 * object has the following properties:<br>
	 * <br>
	 * start The character (on the given line) at which the token starts.<br>
	 * end The character at which the token ends.<br>
	 * string The token's string.<br>
	 * type The token type the mode assigned to the token, such as "keyword" or "comment" (may also be null).<br>
	 * state The mode's state at the end of this token.<br>
	 * 
	 * @param pos position of token
	 * @return a token instance
	 */
	public final Token getTokenAt(Position pos) {
		return getTokenAt(pos, false);
	}

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
	public final Token getTokenAt(Position pos, boolean precise) {
		if (pos != null) {
			return Token.FACTORY.create(nativeObject.getTokenAt(pos, precise));
		}
		// FIXME default
		return null;
	}

	/**
	 * This is similar to getTokenAt, but collects all tokens for a given line into a list.<br>
	 * It is much cheaper than repeatedly calling getTokenAt, which re-parses the part of the line before the token for every
	 * call.
	 * 
	 * @param line the line row
	 * @return a list of tokens
	 */
	public final List<Token> getLineTokens(int line) {
		return getLineTokens(line, false);
	}

	/**
	 * This is similar to getTokenAt, but collects all tokens for a given line into a list.<br>
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
	 * @return a list of tokens
	 */
	public final List<Token> getLineTokens(int line, boolean precise) {
		ArrayObject array = nativeObject.getLineTokens(line, precise);
		return ArrayListHelper.unmodifiableList(array, Token.FACTORY);
	}

	/**
	 * This is a (much) cheaper version of getTokenAt useful for when you just need the type of the token at a given position,
	 * and no other information.<br>
	 * Will return null for unstyled tokens, and a string, potentially containing multiple space-separated style names,
	 * otherwise.
	 * 
	 * @param pos position to use
	 * @return a type of token
	 */
	public final String getTokenTypeAt(Position pos) {
		if (pos != null) {
			return nativeObject.getTokenTypeAt(pos);
		}
		return UndefinedValues.STRING;
	}

	/**
	 * Adjust the indentation of the given line.
	 * 
	 * @param line line row to indent
	 */
	public final void indentLine(int line) {
		indentLine(line, null);
	}

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
	public final void indentLine(int line, IndentLineMode dir) {
		if (dir != null) {
			nativeObject.indentLine(line, dir.value());
		} else {
			nativeObject.indentLine(line, IndentLineMode.SMART.value());
		}
	}

	/**
	 * Adjust the indentation of the given line. The second argument is <code>int</code> to add (positive number) or reduce
	 * (negative number) the indentation by the given amount of spaces.
	 * 
	 * @param line line row to indent
	 * @param dir amount of spaces
	 */
	public final void indentLine(int line, int dir) {
		nativeObject.indentLine(line, dir);
	}

	/**
	 * Switches between overwrite and normal insert mode (when not given an argument), or sets the overwrite mode to a specific
	 * state (when given an argument).
	 * 
	 * @param value to set overwrite mode
	 */
	public final void toggleOverwrite() {
		nativeObject.toggleOverwrite();
	}

	/**
	 * Switches between overwrite and normal insert mode (when not given an argument), or sets the overwrite mode to a specific
	 * state (when given an argument).
	 * 
	 * @param value to set overwrite mode
	 */
	public final void toggleOverwrite(boolean value) {
		nativeObject.toggleOverwrite(value);
	}

	/**
	 * Tells you whether the editor's content can be edited by the user.
	 * 
	 * @return Tells you whether the editor's content can be edited by the user.
	 */
	public final boolean isReadOnly() {
		return nativeObject.isReadOnly();
	}

	/**
	 * Runs the command with the given name on the editor.
	 * 
	 * @param name the command with the given name on the editor.
	 */
	// FIXME
	public final void execCommand(String name) {
		if (name != null) {
			nativeObject.execCommand(name);
		}
	}

	/**
	 * Give the editor focus.
	 */
	public final void focus() {
		nativeObject.focus();
	}

	/**
	 * Allow the given string to be translated with the phrases option.
	 * 
	 * @param text string to be translated with the phrases option.
	 * @return translated text.
	 */
	public final String phrase(String text) {
		if (text != null) {
			return nativeObject.phrase(text);
		}
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the input field for the editor. Will be a textarea or an editable div, depending on the value of the inputStyle
	 * option.
	 * 
	 * @return the input field for the editor
	 */
	public final Element getInputField() {
		return nativeObject.getInputField();
	}

	/**
	 * Returns the DOM node that represents the editor, and controls its size. Remove this from your tree to delete an editor
	 * instance.
	 * 
	 * @return the DOM node that represents the editor
	 */
	public final Element getWrapperElement() {
		return nativeObject.getWrapperElement();
	}

	/**
	 * Returns the DOM node that is responsible for the scrolling of the editor.
	 * 
	 * @return the DOM node that is responsible for the scrolling of the editor
	 */
	public final Element getScrollerElement() {
		return nativeObject.getScrollerElement();
	}

	/**
	 * Fetches the DOM node that contains the editor gutters.
	 * 
	 * @return the DOM node that contains the editor gutters
	 */
	public final Element getGutterElement() {
		return nativeObject.getGutterElement();
	}

	/**
	 * @return the nativeObject
	 */
	public final NativeEditor getNativeObject() {
		return nativeObject;
	}

}
