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

import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.ArrayTextMarker;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsType;

/**
 * Each editor is associated with an instance of CodeMirror.Doc, its document.<br>
 * A document represents the editor content, plus a selection, an undo history, and a mode.<br>
 * A document can only be associated with a single editor at a time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = NativeName.CODE_MIRROR, name = NativeName.DOCUMENT)
final class NativeDocument extends NativeEventEmitter {

	/**
	 * to avoid any instantiation
	 */
	NativeDocument(String text) {
		// do nothing
	}

	/**
	 * Creates a document without creating an editor.
	 * 
	 * @param text content if the document
	 * @param mode mode specification related to language
	 * @param firstLineNumber first line number
	 * @param lineSeparator line separator to use
	 * @param direction direction of the document
	 */
	NativeDocument(String text, NativeModeSpecification mode, int firstLineNumber, String lineSeparator, String direction) {
		// do nothing
	}

	/**
	 * Creates a document without creating an editor.
	 * 
	 * @param text content if the document
	 * @param mode mode specification related to language
	 * @param firstLineNumber first line number
	 * @param lineSeparator line separator to use
	 * @param direction direction of the document
	 */
	NativeDocument(String text, String mode, int firstLineNumber, String lineSeparator, String direction) {
		// do nothing
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	native String getValue();

	/**
	 * Get the content of the document. You can pass it an optional argument to specify the string to be used to separate lines
	 * (defaults to "\n").
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n")
	 * @return the content of the document
	 */
	native String getValue(String separator);

	/**
	 * Set the content of the document.
	 * 
	 * @param content the content of the document
	 */
	native void setValue(String content);

	/**
	 * Get the text between the given points in the editor, which should be position objects.
	 * 
	 * @param from starting position to use
	 * @param to ending position to use
	 * @return the text between the given points in the editor, which should be position objects
	 */
	native String getRange(Position from, Position to);

	/**
	 * Get the text between the given points in the editor, which should be position objects. An optional third argument can be
	 * given to indicate the line separator string to use (defaults to "\n").
	 * 
	 * @param from starting position to use
	 * @param to ending position to use
	 * @param seperator the line separator string to use (defaults to "\n")
	 * @return the text between the given points in the editor, which should be position objects
	 */
	native String getRange(Position from, Position to, String seperator);

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> must be position objects.
	 * 
	 * @param replacement new text to be added
	 * @param from starting position to use
	 */
	native void replaceRange(String replacement, Position from);

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> and <code>to</code> must be position objects.<br>
	 * <code>to</code> can be left off to simply insert the string at position from.
	 * 
	 * @param replacement new text to be added
	 * @param from starting position to use
	 * @param to ending position to use
	 */
	native void replaceRange(String replacement, Position from, Position to);

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> and <code>to</code> must be position objects.<br>
	 * <code>to</code> can be left off to simply insert the string at position from.<br>
	 * When <code>origin</code> is given, it will be passed on to "change" events, and its first letter will be used to
	 * determine whether this change can be merged with previous history events, in the way described for selection origins.
	 * 
	 * @param replacement new text to be added
	 * @param from starting position to use
	 * @param to ending position to use
	 * @param origin When <code>origin</code> is given, it will be passed on to "change" events, and its first letter will be
	 *            used to determine whether this change can be merged with previous history events, in the way described for
	 *            selection origins.
	 */
	native void replaceRange(String replacement, Position from, Position to, String origin);

	/**
	 * Get the content of line by its number.
	 * 
	 * @param line line number
	 * @return the content of line
	 */
	native String getLine(int line);

	/**
	 * Get the number of lines in the editor.
	 * 
	 * @return the number of lines in the editor
	 */
	native int lineCount();

	/**
	 * Get the first line of the editor. This will usually be zero but for linked sub-views, or documents instantiated with a
	 * non-zero first line, it might return other values.
	 * 
	 * @return the first line of the editor
	 */
	native int firstLine();

	/**
	 * Get the last line of the editor. This will usually be lineCount() - 1, but for linked sub-views, it might return other
	 * values.
	 * 
	 * @return the last line of the editor
	 */
	native int lastLine();

	/**
	 * Fetches the line handle for the given line number.
	 * 
	 * @param line line number
	 * @return a line handle instance
	 */
	native NativeLineHandle getLineHandle(int line);

	/**
	 * Given a line handle, returns the current position of that line (or null when it is no longer in the document).
	 * 
	 * @param handle line handle instance
	 * @return the current position of that line (or null when it is no longer in the document)
	 */
	native int getLineNumber(NativeLineHandle handle);

	/**
	 * Iterate over the whole document, and call callback for each line, passing the line handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.<br>
	 * Note that line handles have a text property containing the line's content (as a string).
	 * 
	 * @param callback callback invoked scanning the lines
	 */
	native void eachLine(CallbackProxy.Proxy callback);

	/**
	 * Iterate over the range from start up to (not including) end, and call callback for each line, passing the line
	 * handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.<br>
	 * Note that line handles have a text property containing the line's content (as a string).
	 * 
	 * @param start starting row line
	 * @param end ending row line
	 * @param callback callback invoked scanning the lines
	 */
	native void eachLine(int start, int end, CallbackProxy.Proxy callback);

	/**
	 * Set the editor content as 'clean', a flag that it will retain until it is edited, and which will be set again when such
	 * an edit is undone again.<br>
	 * Useful to track whether the content needs to be saved.<br>
	 * This function is deprecated in favor of changeGeneration, which allows multiple subsystems to track different notions of
	 * cleanness without interfering.
	 */
	native void markClean();

	/**
	 * Returns a number that can later be passed to isClean to test whether any edits were made (and not undone) in the
	 * meantime. If closeEvent is true, the current history event will be "closed", meaning it can't be combined with further
	 * changes (rapid typing or deleting events are typically combined).
	 * 
	 * @param closeEvent If closeEvent is true, the current history event will be "closed", meaning it can't be combined with
	 *            further changes (rapid typing or deleting events are typically combined)
	 * @return a number that can later be passed to isClean to test whether any edits were made (and not undone) in the meantime
	 */
	native int changeGeneration(boolean closeEvent);

	/**
	 * Returns whether the document is currently clean - not modified since initialization or the last call to markClean if no
	 * argument is passed, or since the matching call to "changeGeneration" if a generation value is given.
	 * 
	 * @param generation a number to test whether any edits were made (and not undone) in the meantime
	 * @return whether the document is currently clean
	 */
	native boolean isClean(int generation);

	/**
	 * Get the currently selected code.
	 * 
	 * @return the currently selected code
	 */
	native String getSelection();

	/**
	 * Get the currently selected code.<br>
	 * Optionally pass a line separator to put between the lines in the output.<br>
	 * When multiple selections are present, they are concatenated with instances of separator in between.
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n"
	 * @return the currently selected code
	 */
	native String getSelection(String separator);

	/**
	 * Returns an array containing a string for each selection, representing the content of the selections.
	 * 
	 * @return an array containing a string for each selection, representing the content of the selections
	 */
	native ArrayString getSelections();

	/**
	 * Returns an array containing a string for each selection, representing the content of the selections.<br>
	 * Optionally pass a line separator to split the lines.
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n"
	 * @return an array containing a string for each selection, representing the content of the selections
	 */
	native ArrayString getSelections(String separator);

	/**
	 * Replace the selection(s) with the given string.<br>
	 * By default, the new selection ends up after the inserted text.
	 * 
	 * @param replacement string to use for replacement
	 */
	native void replaceSelection(String replacement);

	/**
	 * Replace the selection(s) with the given string.<br>
	 * By default, the new selection ends up after the inserted text.<br>
	 * The optional select argument can be used to change this-passing "around" will cause the new text to be selected, passing
	 * "start" will collapse the selection to the start of the inserted text.
	 * 
	 * @param replacement string to use for replacement
	 * @param select argument can be used to change this-passing "around" will cause the new text to be selected, passing
	 *            "start" will collapse the selection to the start of the inserted text.
	 */
	native void replaceSelection(String replacement, String select);

	/**
	 * The length of the given array should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the array.
	 * 
	 * @param replacement array of strings to use for replacement
	 */
	native void replaceSelections(ArrayString replacement);

	/**
	 * The length of the given array should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the array.<br>
	 * The optional select argument can be used to change this-passing "around" will cause the new text to be selected, passing
	 * "start" will collapse the selection to the start of the inserted text.
	 * 
	 * @param replacement array of strings to use for replacement
	 * @param select argument can be used to change this-passing "around" will cause the new text to be selected, passing
	 *            "start" will collapse the selection to the start of the inserted text.
	 */
	native void replaceSelections(ArrayString replacement, String select);

	/**
	 * Returns the cursor position.<br>
	 * <code>start</code> is a an optional string indicating which end of the selection to return.<br>
	 * It may be "start" , "end" , "head"(the side of the selection that moves when you press shift + arrow), or "anchor"(the
	 * fixed side of the selection).Omitting the argument is the same as passing "head".
	 * 
	 * @param start string indicating which end of the selection to return.<br>
	 *            It may be "start" , "end" , "head"(the side of the selection that moves when you press shift + arrow), or
	 *            "anchor"(the fixed side of the selection).Omitting the argument is the same as passing "head".
	 * @return A position object will be returned.
	 */
	native Position getCursor(String start); // POSITION

	/**
	 * Retrieves a list of all current selections.<br>
	 * These will always be sorted, and never overlap (overlapping selections are merged).<br>
	 * Each object in the array contains anchor and head ( range object) properties referring to position objects.
	 * 
	 * @return a list of all current selections (by array contains anchor and head ( range object) properties referring to
	 *         position objects)
	 */
	native ArrayEntity<Anchor> listSelections();

	/**
	 * Return true if any text is selected.
	 * 
	 * @return true if any text is selected
	 */
	native boolean somethingSelected();

	/**
	 * Set the cursor position.<br>
	 * You can either pass a single position object, or the line and the character as two separate parameters.<br>
	 * Will replace all selections with a single, empty selection at the given position.<br>
	 * The supported options are the same as for setSelection
	 * 
	 * @param line line number
	 * @param ch column number
	 * @param options options instance
	 */
	native void setCursor(int line, int ch, NativeObject options);

	/**
	 * Set the cursor position.<br>
	 * You can either pass a single position object, or the line and the character as two separate parameters.<br>
	 * Will replace all selections with a single, empty selection at the given position.<br>
	 * The supported options are the same as for setSelection
	 * 
	 * @param pos position of cursor
	 * @param options options instance
	 */
	native void setCursor(Position pos, NativeObject options);

	/**
	 * Set a single selection range.<br>
	 * anchor and head should be position objects.<br>
	 * head defaults to anchor when not given.<br>
	 * These options are supported:<br>
	 * <br>
	 * scroll: boolean. Determines whether the selection head should be scrolled into view. Defaults to true.<br>
	 * <br>
	 * origin: string. Determines whether the selection history event may be merged with the previous one. When an origin starts
	 * with the character +, and the last recorded selection had the same origin and was similar (close in time, both collapsed
	 * or both non-collapsed), the new one will replace the old one. When it starts with *, it will always replace the previous
	 * event (if that had the same origin). Built-in motion uses the "+move" origin. User input uses the "+input" origin.<br>
	 * <br>
	 * bias: number. Determine the direction into which the selection endpoints should be adjusted when they fall inside an
	 * atomic range. Can be either -1 (backward) or 1 (forward). When not given, the bias will be based on the relative position
	 * of the old selection-the editor will try to move further away from that, to prevent getting stuck.<br>
	 * 
	 * @param anchor starting position
	 * @param head ending position
	 * @param options options instance
	 */
	native void setSelection(Position anchor, Position head, NativeObject options);

	/**
	 * Sets a new set of selections.<br>
	 * There must be at least one selection in the given array.<br>
	 * When primary is a number, it determines which selection is the primary one.<br>
	 * When it is not given, the primary index is taken from the previous selection, or set to the last range if the previous
	 * selection had less ranges than the new one.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param ranges array of ranges (RANGE)
	 * @param primary determines which selection is the primary one.<br>
	 *            When it is not given, the primary index is taken from the previous selection, or set to the last range if the
	 *            previous selection had less ranges than the new one.
	 * @param options options instance
	 */
	native void setSelections(ArrayEntity<Anchor> ranges, Object primary, NativeObject options);

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param anchor starting position
	 */
	native void addSelection(Position anchor);

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param anchor starting position
	 * @param head ending position
	 */
	native void addSelection(Position anchor, Position head);

	/**
	 * Similar to setSelection, but will, if shift is held or the extending flag is set, move the head of the selection while
	 * leaving the anchor at its current place. to is optional, and can be passed to ensure a region (for example a word or
	 * paragraph) will end up selected (in addition to whatever lies between that region and the current anchor).<br>
	 * When multiple selections are present, all but the primary selection will be dropped by this method.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param from starting position
	 * @param to ending position
	 * @param options options instance
	 */
	native void extendSelection(Position from, Position to, NativeObject options);

	/**
	 * An equivalent of extendSelection that acts on all selections at once.
	 * 
	 * @param heads array of starting position
	 * @param options options instance
	 */
	native void extendSelections(ArrayEntity<Position> heads, NativeObject options);

	/**
	 * Applies the given function to all existing selections, and calls extendSelections on the result.
	 * 
	 * @param function function invoked to apply selection.
	 * @param options options instance
	 */
	native void extendSelectionsBy(CallbackProxy.Proxy function, NativeObject options);

	/**
	 * Sets or clears the 'extending' flag, which acts similar to the shift key, in that it will cause cursor movement and calls
	 * to extendSelection to leave the selection anchor in place.
	 * 
	 * @param value Sets or clears the 'extending' flag
	 */
	native void setExtending(boolean value);

	/**
	 * Get the value of the 'extending' flag.
	 * 
	 * @return the value of the 'extending' flag.
	 */
	native boolean getExtending();

	/**
	 * Retrieve the editor associated with a document. May return null.
	 * 
	 * @return the editor associated with a document
	 */
	native NativeEditor getEditor();

	/**
	 * Gets the (outer) mode object for the editor.<br>
	 * Note that this is distinct from getOption("mode"), which gives you the mode specification, rather than the resolved,
	 * instantiated mode object
	 * 
	 * @return the (outer) mode object for the editor
	 */
	native ModeReference getMode();

	/**
	 * Create an identical copy of the given doc. When copyHistory is true, the history will also be copied.Can not be called
	 * directly on an editor.
	 * 
	 * @param copyHistory if true, the history will also be copied.Can not be called directly on an editor.
	 * @return an identical copy of the given doc
	 */
	native NativeDocument copy(boolean copyHistory);

	/**
	 * Create a new document that's linked to the target document.<br>
	 * Linked documents will stay in sync (changes to one are also applied to the other) until unlinked.<br>
	 * These are the options that are supported:<br>
	 * <br>
	 * sharedHist: boolean. When turned on, the linked copy will share an undo history with the original. Thus, something done
	 * in one of the two can be undone in the other, and vice versa.<br>
	 * <br>
	 * from: integer<br>
	 * to: integer<br>
	 * Can be given to make the new document a subview of the original. Subviews only show a given range of lines. Note that
	 * line coordinates inside the subview will be consistent with those of the parent, so that for example a subview starting
	 * at line 10 will refer to its first line as line 10, not 0.<br>
	 * <br>
	 * mode: string|object. By default, the new document inherits the mode of the parent. This option can be set to a mode spec
	 * to give it a different mode.
	 * 
	 * @param options options instance
	 * @return new document that's linked to the target document
	 */
	native NativeDocument linkedDoc(NativeObject options);

	/**
	 * Break the link between two documents.<br>
	 * After calling this, changes will no longer propagate between the documents, and, if they had a shared history, the
	 * history will become separate.
	 * 
	 * @param doc document to be unlinked
	 */
	native void unlinkDoc(NativeDocument doc);

	/**
	 * Will call the given function for all documents linked to the target document.<br>
	 * It will be passed two arguments, the linked document and a boolean indicating whether that document shares history with
	 * the target.
	 * 
	 * @param function function instance to be called for all documents linked to the target document.
	 */
	native void iterLinkedDocs(CallbackProxy.Proxy function);

	/**
	 * Undo one edit (if any undo events are stored).
	 */
	native void undo();

	/**
	 * Redo one undone edit.
	 */
	native void redo();

	/**
	 * Undo one edit or selection change.
	 */
	native void undoSelection();

	/**
	 * Redo one undone edit or selection change.
	 */
	native void redoSelection();

	/**
	 * Returns an object with {undo, redo} properties , both of which hold integers , indicating the amount of stored undo and
	 * redo operations.
	 * 
	 * @return an object with {undo, redo} properties , both of which hold integers , indicating the amount of stored undo and
	 *         redo operations
	 */
	native NativeObject historySize();

	/**
	 * Clears the editor's undo history.
	 */
	native void clearHistory();

	/**
	 * Get a (JSON-serializable) representation of the undo history.
	 * 
	 * @return a (JSON-serializable) representation of the undo history
	 */
	native NativeObject getHistory();

	/**
	 * Replace the editor's undo history with the one provided, which must be a value as returned by getHistory.<br>
	 * Note that this will have entirely undefined results if the editor content isn't also the same as it was when getHistory
	 * was called.
	 * 
	 * @param history a (JSON-serializable) representation of the undo history
	 */
	native void setHistory(NativeObject history);

	/**
	 * The method will return an object that represents the marker (with constructor CodeMirror.TextMarker), which exposes three
	 * methods:<br>
	 * clear(), to remove the mark,<br>
	 * find(), which returns a {from, to} object (both holding document positions), indicating the current position of the
	 * marked range, or undefined if the marker is no longer in the document, <br>
	 * changed(), which you can call if you've done something that might change the size of the marker (for example changing the
	 * content of a replacedWith node), and want to cheaply update the display.<br>
	 * <br>
	 * Can be used to mark a range of text with a specific CSS class name.<br>
	 * from and to should be {line, ch} objects.<br>
	 * The options parameter is optional.<br>
	 * When given, it should be an object that may contain the following configuration options: <br>
	 * <br>
	 * className: string. Assigns a CSS class to the marked stretch of text.<br>
	 * <br>
	 * inclusiveLeft: boolean. Determines whether text inserted on the left of the marker will end up inside or outside of
	 * it.<br>
	 * <br>
	 * inclusiveRight: boolean. Like inclusiveLeft, but for the right side.<br>
	 * <br>
	 * atomic: boolean. Atomic ranges act as a single unit when cursor movement is concerned-i.e. it is impossible to place the
	 * cursor inside of them. In atomic ranges, inclusiveLeft and inclusiveRight have a different meaning-they will prevent the
	 * cursor from being placed respectively directly before and directly after the range.<br>
	 * <br>
	 * collapsed: boolean. Collapsed ranges do not show up in the display. Setting a range to be collapsed will automatically
	 * make it atomic.<br>
	 * <br>
	 * clearOnEnter: boolean. When enabled, will cause the mark to clear itself whenever the cursor enters its range. This is
	 * mostly useful for text-replacement widgets that need to 'snap open' when the user tries to edit them. The "clear" event
	 * fired on the range handle can be used to be notified when this happens.<br>
	 * <br>
	 * clearWhenEmpty: boolean. Determines whether the mark is automatically cleared when it becomes empty. Default is true.<br>
	 * <br>
	 * replacedWith: Element. Use a given node to display this range. Implies both collapsed and atomic. The given DOM node must
	 * be an inline element (as opposed to a block element).<br>
	 * <br>
	 * handleMouseEvents: boolean. When replacedWith is given, this determines whether the editor will capture mouse and drag
	 * events occurring in this widget. Default is false-the events will be left alone for the default browser handler, or
	 * specific handlers on the widget, to capture.<br>
	 * <br>
	 * readOnly: boolean. A read-only span can, as long as it is not cleared, not be modified except by calling setValue to
	 * reset the whole document. Note: adding a read-only span currently clears the undo history of the editor, because existing
	 * undo events being partially nullified by read-only spans would corrupt the history (in the current implementation).<br>
	 * <br>
	 * addToHistory: boolean. When set to true (default is false), adding this marker will create an event in the undo history
	 * that can be individually undone (clearing the marker).<br>
	 * <br>
	 * startStyle: string. Can be used to specify an extra CSS class to be applied to the leftmost span that is part of the
	 * marker.<br>
	 * <br>
	 * endStyle: string. Equivalent to startStyle, but for the rightmost span.<br>
	 * <br>
	 * css: string. A string of CSS to be applied to the covered text. For example "color: #fe3".<br>
	 * <br>
	 * attributes: object. When given, add the attributes in the given object to the elements created for the marked text.
	 * Adding class or style attributes this way is not supported.<br>
	 * <br>
	 * shared: boolean. When the target document is linked to other documents, you can set shared to true to make the marker
	 * appear in all documents. By default, a marker appears only in its target document.
	 * 
	 * 
	 * @param from starting position of marker
	 * @param to ending position of marker
	 * @param options options instance
	 * @return a text marker instance
	 */
	native NativeTextMarker markText(Position from, Position to, NativeObject options);

	/**
	 * Inserts a bookmark, a handle that follows the text around it as it is being edited, at the given position.<br>
	 * A bookmark has two methods find() and clear().<br>
	 * The first returns the current position of the bookmark, if it is still in the document. <br>
	 * The second explicitly removes the bookmark.<br>
	 * The options argument is optional.<br>
	 * If given, the following properties are recognized:<br>
	 * <br>
	 * widget: Element. Can be used to display a DOM node at the current location of the bookmark (analogous to the replacedWith
	 * option to markText).<br>
	 * <br>
	 * insertLeft: boolean. By default, text typed when the cursor is on top of the bookmark will end up to the right of the
	 * bookmark. Set this option to true to make it go to the left instead.<br>
	 * <br>
	 * shared: boolean. See the corresponding option to markText.<br>
	 * <br>
	 * handleMouseEvents: boolean.As with markText, this determines whether mouse events on the widget inserted for this
	 * bookmark are handled by CodeMirror. The default is false.
	 * 
	 * @param pos position of bookmark
	 * @param options options instance
	 * @return a text marker instance
	 */
	native NativeTextMarker setBookmark(Position pos, NativeObject options);

	/**
	 * Returns an array of all the bookmarks and marked ranges found between the given positions.
	 * 
	 * @param from starting position
	 * @param to ending position
	 * @return an array of all the bookmarks and marked ranges found between the given positions
	 */
	native ArrayTextMarker findMarks(Position from, Position to);

	/**
	 * Returns an array of all the bookmarks and marked ranges present at the given position.
	 * 
	 * @param pos position into document
	 * @return an array of all the bookmarks and marked ranges
	 */
	native ArrayTextMarker findMarksAt(Position pos);

	/**
	 * Returns an array containing all marked ranges in the document.
	 * 
	 * @return an array containing all marked ranges in the document.
	 */
	native ArrayTextMarker getAllMarks();

	/**
	 * Sets the gutter marker for the given gutter (identified by its CSS class, see the gutters option) to the given value.
	 * Value can be either null, to clear the marker, or a DOM element, to set it.<br>
	 * The DOM element will be shown in the specified gutter next to the specified line.
	 * 
	 * @param line line row
	 * @param gutterID CSS class name (ID)
	 * @param value Value can be either null, to clear the marker, or a DOM element,
	 * @return
	 */
	native NativeLineHandle setGutterMarker(int line, String gutterID, Element value);

	/**
	 * Sets the gutter marker for the given gutter (identified by its CSS class, see the gutters option) to the given value.
	 * Value can be either null, to clear the marker, or a DOM element, to set it.<br>
	 * The DOM element will be shown in the specified gutter next to the specified line.
	 * 
	 * @param line LINEHADLER
	 * @param gutterID CSS class name (ID)
	 * @param value Value can be either null, to clear the marker, or a DOM element,
	 * @return
	 */
	native NativeLineHandle setGutterMarker(NativeLineHandle line, String gutterID, Element value);

	/**
	 * Remove all gutter markers in the gutter with the given ID.
	 * 
	 * @param gutterID CSS class name (ID)
	 */
	native void clearGutter(String gutterID);

	/**
	 * Set a CSS class name for the given line.<br>
	 * <code>where</code>determines to which element this class should be applied, can can be one of "text" (the text element,
	 * which lies in front of the selection), "background" (a background element that will be behind the selection), "gutter"
	 * (the line's gutter space), or "wrap" (the wrapper node that wraps all of the line's elements, including gutter elements).
	 * class should be the name of the class to apply.
	 * 
	 * @param line line number
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return LINEHANDLER
	 */
	native NativeLineHandle addLineClass(int line, String where, String className);

	/**
	 * Set a CSS class name for the given line.<br>
	 * <code>where</code>determines to which element this class should be applied, can can be one of "text" (the text element,
	 * which lies in front of the selection), "background" (a background element that will be behind the selection), "gutter"
	 * (the line's gutter space), or "wrap" (the wrapper node that wraps all of the line's elements, including gutter elements).
	 * class should be the name of the class to apply.
	 * 
	 * @param line LINEHANDLER
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return LINEHANDLER
	 */
	native NativeLineHandle addLineClass(NativeLineHandle line, String where, String className);

	/**
	 * Remove a CSS class from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap" (see addLineClass). class can be left off to remove
	 * all classes for the specified node, or be a string to remove only a specific class.
	 * 
	 * @param line line number
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return LINEHANDLER
	 */
	native NativeLineHandle removeLineClass(int line, String where, String className);

	/**
	 * Remove a CSS class from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap" (see addLineClass). class can be left off to remove
	 * all classes for the specified node, or be a string to remove only a specific class.
	 * 
	 * @param line LINEHANDLER
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return LINEHANDLER
	 */
	native NativeLineHandle removeLineClass(NativeLineHandle line, String where, String className);

	/**
	 * Adds a line widget, an element shown below a line, spanning the whole of the editor's width, and moving the lines below
	 * it downwards. <br>
	 * node should be a DOM node, which will be displayed below the given line. options, when given, should be an object that
	 * configures the behavior of the widget.<br>
	 * The following options are supported (all default to false):<br>
	 * <br>
	 * coverGutter: boolean Whether the widget should cover the gutter.<br>
	 * <br>
	 * noHScroll: boolean Whether the widget should stay fixed in the face of horizontal scrolling. <br>
	 * <br>
	 * above: boolean Causes the widget to be placed above instead of below the text of the line. <br>
	 * <br>
	 * handleMouseEvents: boolean Determines whether the editor will capture mouse and drag events occurring in this widget.
	 * Default is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to
	 * capture.<br>
	 * <br>
	 * insertAt: integer By default, the widget is added below other widgets for the line. This option can be used to place it
	 * at a different position (zero for the top, N to put it after the Nth other widget).<br>
	 * <br>
	 * Note that this only has effect once, when the widget is created.<br>
	 * Note that the widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might
	 * in some cases affect it.<br>
	 * This method returns an object that represents the widget placement.<br>
	 * It'll have a line property pointing at the line handle that it is associated with, and the following methods:<br>
	 * <br>
	 * clear() Removes the widget. <br>
	 * <br>
	 * changed() Call this if you made some change to the widget's DOM node that might affect its height. It'll force CodeMirror
	 * to update the height of the line that contains the widget.
	 * 
	 * @param line line number
	 * @param node
	 * @param options
	 * @return line widget instance
	 */
	native NativeLineWidget addLineWidget(int line, Element node, NativeObject options);

	/**
	 * Adds a line widget, an element shown below a line, spanning the whole of the editor's width, and moving the lines below
	 * it downwards. <br>
	 * node should be a DOM node, which will be displayed below the given line. options, when given, should be an object that
	 * configures the behavior of the widget.<br>
	 * The following options are supported (all default to false):<br>
	 * <br>
	 * coverGutter: boolean Whether the widget should cover the gutter.<br>
	 * <br>
	 * noHScroll: boolean Whether the widget should stay fixed in the face of horizontal scrolling. <br>
	 * <br>
	 * above: boolean Causes the widget to be placed above instead of below the text of the line. <br>
	 * <br>
	 * handleMouseEvents: boolean Determines whether the editor will capture mouse and drag events occurring in this widget.
	 * Default is false-the events will be left alone for the default browser handler, or specific handlers on the widget, to
	 * capture.<br>
	 * <br>
	 * insertAt: integer By default, the widget is added below other widgets for the line. This option can be used to place it
	 * at a different position (zero for the top, N to put it after the Nth other widget).<br>
	 * <br>
	 * Note that this only has effect once, when the widget is created.<br>
	 * Note that the widget node will become a descendant of nodes with CodeMirror-specific CSS classes, and those classes might
	 * in some cases affect it.<br>
	 * This method returns an object that represents the widget placement.<br>
	 * It'll have a line property pointing at the line handle that it is associated with, and the following methods:<br>
	 * <br>
	 * clear() Removes the widget. <br>
	 * <br>
	 * changed() Call this if you made some change to the widget's DOM node that might affect its height. It'll force CodeMirror
	 * to update the height of the line that contains the widget.
	 * 
	 * @param line line handler
	 * @param node
	 * @param options
	 * @return
	 */
	native NativeLineWidget addLineWidget(NativeLineHandle line, Element node, NativeObject options);

	/**
	 * Returns the line number, text content, and marker status of the given line, which can be either a number or a line
	 * handle.<br>
	 * The returned object has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}, where
	 * gutterMarkers is an object mapping gutter IDs to marker elements, and widgets is an array of line widgets attached to
	 * this line, and the various class properties refer to classes added with addLineClass.
	 * 
	 * @param line line number
	 * @return object has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}, where
	 *         gutterMarkers is an object mapping gutter IDs to marker elements, and widgets is an array of line widgets
	 *         attached to this line, and the various class properties refer to classes added with addLineClass.
	 */
	native NativeLineInfo lineInfo(int line);

	/**
	 * Returns the line number, text content, and marker status of the given line, which can be either a number or a line
	 * handle.<br>
	 * The returned object has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}, where
	 * gutterMarkers is an object mapping gutter IDs to marker elements, and widgets is an array of line widgets attached to
	 * this line, and the various class properties refer to classes added with addLineClass.
	 * 
	 * @param line LINEHANDLER
	 * @return object has the structure {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}, where
	 *         gutterMarkers is an object mapping gutter IDs to marker elements, and widgets is an array of line widgets
	 *         attached to this line, and the various class properties refer to classes added with addLineClass.
	 */
	native NativeLineInfo lineInfo(NativeLineHandle line);

	/**
	 * Returns the preferred line separator string for this document, as per the option by the same name.<br>
	 * When that option is null, the string "\n" is returned.
	 * 
	 * @return the preferred line separator string for this document
	 */
	native String lineSeparator();

	/**
	 * Calculates and returns a position object for a zero-based index who's value is relative to the start of the editor's
	 * text.<br>
	 * If the index is out of range of the text then the returned object is clipped to start or end of the text respectively.
	 * 
	 * @param index value is relative to the start of the editor's text
	 * @return a position object for a zero-based index
	 */
	native Position posFromIndex(int index);

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text
	 */
	native int indexFromPos(Position position);
}