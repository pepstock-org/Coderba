/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License"){}
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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.pepstock.coderba.client.CodeMirror;
import org.pepstock.coderba.client.Editor;
import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.Modes;
import org.pepstock.coderba.client.NativeEditor;
import org.pepstock.coderba.client.callbacks.DocumentEachLineHandler;
import org.pepstock.coderba.client.callbacks.DocumentExtendSelectionsHandler;
import org.pepstock.coderba.client.callbacks.LinkedDocumentsHandler;
import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.ArrayTextMarker;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.defaults.GlobalDefaults;
import org.pepstock.coderba.client.enums.CursorPosition;
import org.pepstock.coderba.client.enums.LineClassLocation;
import org.pepstock.coderba.client.enums.Select;
import org.pepstock.coderba.client.enums.TextMarkerType;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsFunction;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Document {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called to iterate over the whole document, and call callback for each line, passing the line
	 * handle.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentEachLineFunction {

		/**
		 * A function that is called to iterate over the whole document, and call callback for each line, passing the line
		 * handle.
		 * 
		 * @param handle line handle for each row
		 */
		void call(NativeLineHandle handle);
	}

	/**
	 * Java script FUNCTION that is called to apply the given function to all existing selections.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentExtendSelectionsFunction {

		/**
		 * A function that is called to apply the given function to all existing selections.
		 * 
		 * @param anchor anchor object for selection
		 * @return the position of selection
		 */
		Position call(Anchor anchor);
	}

	/**
	 * Java script FUNCTION that is called for all documents linked to the target document.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface LinkedDocumentsFunction {

		/**
		 * A function that is called for all documents linked to the target document.
		 * 
		 * @param document the linked document
		 * @param sharedHistory indicating whether that document shares history with the target.
		 */
		void call(NativeDocument document, boolean sharedHistory);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the documentEachLine function
	private final CallbackProxy<DocumentEachLineFunction> documentEachLineFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the documentEachLine function
	private final CallbackProxy<DocumentExtendSelectionsFunction> documentExtendSelectionsFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the lined documents function
	private final CallbackProxy<LinkedDocumentsFunction> linkedDocumentsFunctionProxy = JsHelper.get().newCallbackProxy();

	private final NativeDocument nativeObject;

	private final Map<Integer, TextMarker> markers = new HashMap<>();

	private final Map<Integer, LineWidget> lineWidgets = new HashMap<>();

	private DocumentEachLineHandler documentEachLineHandler = null;

	private DocumentExtendSelectionsHandler documentExtendSelectionsHandler = null;

	private LinkedDocumentsHandler linkedDocumentsHandler = null;

	/**
	 * @param nativeObject
	 */
	public Document(NativeDocument nativeObject) {
		this.nativeObject = nativeObject;
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		documentEachLineFunctionProxy.setCallback((handle) -> onDocumentEachLine(handle));
		documentExtendSelectionsFunctionProxy.setCallback((anchor) -> onDocumentExtendSelections(anchor));
		linkedDocumentsFunctionProxy.setCallback((document, sharedHistory) -> onLinkedDcouments(document, sharedHistory));
	}

	/**
	 * A function that is called to iterate over the whole document, and call callback for each line, passing the line handle.
	 * 
	 * @param handle line handle for each row
	 */
	private void onDocumentEachLine(NativeLineHandle handle) {
		NativeEditor nativeEditor = nativeObject.getEditor();
		if (documentEachLineHandler != null && nativeEditor != null) {
			EditorArea area = nativeEditor.getEditorArea();
			if (area != null) {
				documentEachLineHandler.handle(area, new LineHandle(handle));
			}
		}
	}

	/**
	 * A function that is called to apply the given function to all existing selections.
	 * 
	 * @param anchor anchor object for selection
	 * @return the position of selection
	 */
	private Position onDocumentExtendSelections(Anchor anchor) {
		NativeEditor nativeEditor = nativeObject.getEditor();
		if (documentExtendSelectionsHandler != null && nativeEditor != null) {
			EditorArea area = nativeEditor.getEditorArea();
			if (area != null) {
				Position result = documentExtendSelectionsHandler.handle(area, anchor);
				if (result != null) {
					return result;
				}
			}
		}
		return anchor.getAnchor();
	}

	/**
	 * A function that is called for all documents linked to the target document.
	 * 
	 * @param document the linked document
	 * @param sharedHistory indicating whether that document shares history with the target.
	 */
	private void onLinkedDcouments(NativeDocument document, boolean sharedHistory) {
		NativeEditor nativeEditor = nativeObject.getEditor();
		if (linkedDocumentsHandler != null && nativeEditor != null) {
			EditorArea area = nativeEditor.getEditorArea();
			if (area != null) {
				linkedDocumentsHandler.handle(area, new Document(document), sharedHistory);
			}
		}
	}

	/**
	 * @return the nativeObject
	 */
	public final NativeDocument getNativeDocument() {
		return nativeObject;
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public String getValue() {
		return nativeObject.getValue();
	}

	/**
	 * Get the content of the document. You can pass it an optional argument to specify the string to be used to separate lines
	 * (defaults to "\n").
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n")
	 * @return the content of the document
	 */
	public String getValue(String separator) {
		if (separator != null) {
			return nativeObject.getValue(separator);
		}
		return getValue();
	}

	/**
	 * Set the content of the document.
	 * 
	 * @param content the content of the document
	 */
	public void setValue(String content) {
		String contentToSet = content == null ? GlobalDefaults.get().getValue() : content;
		nativeObject.setValue(contentToSet);
	}

	/**
	 * Get the text between the given points in the editor, which should be position objects.
	 * 
	 * @param range starting position and end position to use
	 * @return the text between the given points in the editor, which should be position objects
	 */
	public String getRange(Range range) {
		if (range != null) {
			return getRange(range.getFrom(), range.getTo());
		}
		return GlobalDefaults.get().getValue();
	}

	/**
	 * Get the text between the given points in the editor, which should be position objects.
	 * 
	 * @param from starting position to use
	 * @param to ending position to use
	 * @return the text between the given points in the editor, which should be position objects
	 */
	public String getRange(Position from, Position to) {
		if (from != null && to != null) {
			return nativeObject.getRange(from, to);
		}
		return GlobalDefaults.get().getValue();
	}

	/**
	 * Get the text between the given points in the editor, which should be position objects. An optional third argument can be
	 * given to indicate the line separator string to use (defaults to "\n").
	 * 
	 * @param range starting position and end position to use
	 * @param seperator the line separator string to use (defaults to "\n")
	 * @return the text between the given points in the editor, which should be position objects
	 */
	public String getRange(Range range, String seperator) {
		if (range != null) {
			return getRange(range.getFrom(), range.getTo(), seperator);
		}
		return GlobalDefaults.get().getValue();
	}

	/**
	 * Get the text between the given points in the editor, which should be position objects. An optional third argument can be
	 * given to indicate the line separator string to use (defaults to "\n").
	 * 
	 * @param from starting position to use
	 * @param to ending position to use
	 * @param seperator the line separator string to use (defaults to "\n")
	 * @return the text between the given points in the editor, which should be position objects
	 */
	public String getRange(Position from, Position to, String seperator) {
		if (from != null && to != null) {
			if (seperator != null) {
				return nativeObject.getRange(from, to);
			}
			return getRange(from, to);
		}
		return GlobalDefaults.get().getValue();
	}

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> must be position objects.
	 * 
	 * @param replacement new text to be added
	 * @param from starting position to use
	 */
	public void replaceRange(String replacement, Position from) {
		if (replacement != null && from != null) {
			nativeObject.replaceRange(replacement, from);
		}
	}

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> and <code>to</code> must be position objects.<br>
	 * <code>to</code> can be left off to simply insert the string at position from.
	 * 
	 * @param replacement new text to be added
	 * @param range starting position and ending position to use
	 */
	public void replaceRange(String replacement, Range range) {
		if (range != null) {
			replaceRange(replacement, range.getFrom(), range.getTo());
		}
	}

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> and <code>to</code> must be position objects.<br>
	 * <code>to</code> can be left off to simply insert the string at position from.
	 * 
	 * @param replacement new text to be added
	 * @param from starting position to use
	 * @param to ending position to use
	 */
	public void replaceRange(String replacement, Position from, Position to) {
		if (replacement != null && from != null && to != null) {
			nativeObject.replaceRange(replacement, from, to);
		}
	}

	/**
	 * Replace the part of the document between from and to with the given string. <br>
	 * <code>from</code> and <code>to</code> must be position objects.<br>
	 * <code>to</code> can be left off to simply insert the string at position from.<br>
	 * When <code>origin</code> is given, it will be passed on to "change" events, and its first letter will be used to
	 * determine whether this change can be merged with previous history events, in the way described for selection origins.
	 * 
	 * @param replacement new text to be added
	 * @param range starting position and ending position to use
	 * @param origin When <code>origin</code> is given, it will be passed on to "change" events, and its first letter will be
	 *            used to determine whether this change can be merged with previous history events, in the way described for
	 *            selection origins.
	 */
	public void replaceRange(String replacement, Range range, String origin) {
		if (range != null) {
			replaceRange(replacement, range.getFrom(), range.getTo(), origin);
		}
	}

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
	public void replaceRange(String replacement, Position from, Position to, String origin) {
		if (replacement != null && from != null && to != null) {
			if (origin != null) {
				nativeObject.replaceRange(replacement, from, to, origin);
			} else {
				replaceRange(replacement, from, to);
			}
		}
	}

	/**
	 * Get the content of line by its number.
	 * 
	 * @param line line number
	 * @return the content of line
	 */
	public String getLine(int line) {
		if (isValidLine(line)) {
			return nativeObject.getLine(line);
		}
		return GlobalDefaults.get().getValue();
	}

	/**
	 * Get the number of lines in the editor.
	 * 
	 * @return the number of lines in the editor
	 */
	public int lineCount() {
		return nativeObject.lineCount();
	}

	/**
	 * Get the first line of the editor. This will usually be zero but for linked sub-views, or documents instantiated with a
	 * non-zero first line, it might return other values.
	 * 
	 * @return the first line of the editor
	 */
	public int firstLine() {
		return nativeObject.firstLine();
	}

	/**
	 * Get the last line of the editor. This will usually be lineCount() - 1, but for linked sub-views, it might return other
	 * values.
	 * 
	 * @return the last line of the editor
	 */
	public int lastLine() {
		return nativeObject.lastLine();
	}

	/**
	 * Fetches the line handle for the given line number.
	 * 
	 * @param line line number
	 * @return a line handle instance. if passed line is not in the document, returns <code>null</code>
	 */
	public LineHandle getLineHandle(int line) {
		if (isValidLine(line)) {
			return new LineHandle(nativeObject.getLineHandle(line));
		}
		return null;
	}

	/**
	 * Given a line handle, returns the current position of that line (or null when it is no longer in the document).
	 * 
	 * @param handle line handle instance
	 * @return the current position of that line (or null when it is no longer in the document). If line handle is not
	 *         consistent, returns {@link UndefinedValues#INTEGER}.
	 */
	public int getLineNumber(LineHandle handle) {
		if (handle != null) {
			return nativeObject.getLineNumber(handle.getObject());
		}
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns the "each line" handler.
	 * 
	 * @return the "each line" handler
	 */
	public final DocumentEachLineHandler getDocumentEachLineHandler() {
		return documentEachLineHandler;
	}

	/**
	 * Sets the "each line" handler, previously calling the scan.
	 * 
	 * @param documentEachLineHandler the "each line" handler, previously calling the scan
	 */
	public final void setDocumentEachLineHandler(DocumentEachLineHandler documentEachLineHandler) {
		this.documentEachLineHandler = documentEachLineHandler;
	}

	/**
	 * Iterate over the whole document, and call the "each line" handler previously set for each line, passing the line
	 * handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.<br>
	 */
	public void eachLine() {
		if (documentEachLineHandler != null) {
			nativeObject.eachLine(documentEachLineFunctionProxy.getProxy());
		}
	}

	/**
	 * Iterate over the whole document, and call callback for each line, passing the line handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.
	 * 
	 * @param handler callback invoked scanning the lines. The callback is set as the document one
	 */
	public void eachLine(DocumentEachLineHandler handler) {
		this.documentEachLineHandler = handler;
		eachLine();
	}

	/**
	 * Iterate over the range from start up to (not including) end, and call callback for each line, passing the line
	 * handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.<br>
	 * Note that line handles have a text property containing the line's content (as a string).
	 * 
	 * @param start starting row line
	 * @param end ending row line
	 */
	public void eachLine(int start, int end) {
		if (documentEachLineHandler != null && isValidLine(start) && isValidLine(end)) {
			nativeObject.eachLine(start, end, documentEachLineFunctionProxy.getProxy());
		}
	}

	/**
	 * Iterate over the range from start up to (not including) end, and call callback for each line, passing the line
	 * handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.
	 * 
	 * @param start starting row line
	 * @param end ending row line
	 * @param handler callback invoked scanning the lines. The callback is set as the document one
	 */
	public void eachLine(int start, int end, DocumentEachLineHandler handler) {
		this.documentEachLineHandler = handler;
		eachLine(start, end);
	}

	/**
	 * Set the editor content as 'clean', a flag that it will retain until it is edited, and which will be set again when such
	 * an edit is undone again.<br>
	 * Useful to track whether the content needs to be saved.<br>
	 * This function is deprecated in favor of changeGeneration, which allows multiple subsystems to track different notions of
	 * cleanness without interfering.
	 */
	public void markClean() {
		nativeObject.markClean();
	}

	/**
	 * Returns a number that can later be passed to isClean to test whether any edits were made (and not undone) in the
	 * meantime. If closeEvent is true, the current history event will be "closed", meaning it can't be combined with further
	 * changes (rapid typing or deleting events are typically combined).
	 * 
	 * @param closeEvent If closeEvent is true, the current history event will be "closed", meaning it can't be combined with
	 *            further changes (rapid typing or deleting events are typically combined)
	 * @return a number that can later be passed to isClean to test whether any edits were made (and not undone) in the meantime
	 */
	public int changeGeneration(boolean closeEvent) {
		return nativeObject.changeGeneration(closeEvent);
	}

	/**
	 * Returns whether the document is currently clean - not modified since initialization or the last call to markClean if no
	 * argument is passed, or since the matching call to "changeGeneration" if a generation value is given.
	 * 
	 * @param generation a number to test whether any edits were made (and not undone) in the meantime
	 * @return whether the document is currently clean
	 */
	public boolean isClean(int generation) {
		return nativeObject.isClean(generation);
	}

	/**
	 * Get the currently selected code.
	 * 
	 * @return the currently selected code
	 */
	public String getSelection() {
		return nativeObject.getSelection();
	}

	/**
	 * Get the currently selected code.<br>
	 * Optionally pass a line separator to put between the lines in the output.<br>
	 * When multiple selections are present, they are concatenated with instances of separator in between.
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n"
	 * @return the currently selected code
	 */
	public String getSelection(String separator) {
		if (separator != null) {
			return nativeObject.getSelection(separator);
		}
		return getSelection();
	}

	/**
	 * Returns an array containing a string for each selection, representing the content of the selections.
	 * 
	 * @return an array containing a string for each selection, representing the content of the selections
	 */
	public List<String> getSelections() {
		ArrayString array = nativeObject.getSelections();
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns an array containing a string for each selection, representing the content of the selections.<br>
	 * Optionally pass a line separator to split the lines.
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n"
	 * @return an array containing a string for each selection, representing the content of the selections
	 */
	public List<String> getSelections(String separator) {
		if (separator != null) {
			ArrayString array = nativeObject.getSelections(separator);
			return ArrayListHelper.list(array);
		}
		return getSelections();
	}

	/**
	 * Replace the selection(s) with the given string.<br>
	 * By default, the new selection ends up after the inserted text.
	 * 
	 * @param replacement string to use for replacement
	 */
	public void replaceSelection(String replacement) {
		if (replacement != null) {
			nativeObject.replaceSelection(replacement);
		}
	}

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
	public void replaceSelection(String replacement, Select select) {
		if (replacement != null) {
			if (select != null) {
				nativeObject.replaceSelection(replacement, select.value());
			} else {
				replaceSelection(replacement);
			}
		}
	}

	/**
	 * The length of the given array should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the array.
	 * 
	 * @param replacement array of strings to use for replacement
	 */
	public void replaceSelections(String... replacement) {
		replaceSelections(ArrayString.fromOrNull(replacement));
	}

	/**
	 * The length of the given array should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the array.
	 * 
	 * @param replacement array of strings to use for replacement
	 */
	public void replaceSelections(List<String> replacement) {
		replaceSelections(ArrayString.fromOrNull(replacement));
	}

	/**
	 * The length of the given array should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the array.
	 * 
	 * @param replacement array of strings to use for replacement
	 */
	private void replaceSelections(ArrayString replacement) {
		if (replacement != null && !replacement.isEmpty()) {
			nativeObject.replaceSelections(replacement);
		}
	}

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
	public void replaceSelections(Select select, String... replacement) {
		replaceSelections(ArrayString.fromOrNull(replacement), select);
	}

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
	public void replaceSelections(Select select, List<String> replacement) {
		replaceSelections(ArrayString.fromOrNull(replacement), select);
	}

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
	private void replaceSelections(ArrayString replacement, Select select) {
		if (replacement != null && !replacement.isEmpty()) {
			if (select != null) {
				nativeObject.replaceSelections(replacement, select.value());
			} else {
				replaceSelections(replacement);
			}
		}
	}

	/**
	 * Returns the cursor position.
	 * 
	 * @return A position object will be returned.
	 */
	public Position getCursor() {
		return getCursor(CursorPosition.HEAD);
	}

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
	public Position getCursor(CursorPosition start) {
		CursorPosition startToUse = start != null ? start : CursorPosition.HEAD;
		return getCursor(startToUse);
	}

	/**
	 * Retrieves a list of all current selections.<br>
	 * These will always be sorted, and never overlap (overlapping selections are merged).<br>
	 * Each object in the array contains anchor and head ( range object) properties referring to position objects.
	 * 
	 * @return a list of all current selections (by array contains anchor and head ( range object) properties referring to
	 *         position objects)
	 */
	public List<Anchor> listSelections() {
		ArrayEntity<Anchor> array = nativeObject.listSelections();
		return ArrayListHelper.list(array);
	}

	/**
	 * Return true if any text is selected.
	 * 
	 * @return true if any text is selected
	 */
	public boolean somethingSelected() {
		return nativeObject.somethingSelected();
	}

	/**
	 * Set the cursor position by a line number and using the 0 column of line.<br>
	 * Will replace all selections with a single, empty selection at the given position.
	 * 
	 * @param line line number
	 * @param ch column number
	 */
	public void setCursor(int line) {
		setCursor(line, 0);
	}

	/**
	 * Set the cursor position.<br>
	 * You can pass the line and the character as two separate parameters.<br>
	 * Will replace all selections with a single, empty selection at the given position.
	 * 
	 * @param line line number
	 * @param ch column number
	 */
	public void setCursor(int line, int ch) {
		setCursor(line, ch, null);
	}

	/**
	 * Set the cursor position.<br>
	 * You can pass the line and the character as two separate parameters.<br>
	 * Will replace all selections with a single, empty selection at the given position.<br>
	 * The supported options are the same as for setSelection
	 * 
	 * @param line line number
	 * @param ch column number
	 * @param options options instance
	 */
	public void setCursor(int line, int ch, CursorOptions options) {
		nativeObject.setCursor(line, ch, checkOptions(options).getObject());
	}

	/**
	 * Set the cursor position.<br>
	 * You can pass a single position object.<br>
	 * Will replace all selections with a single, empty selection at the given position.
	 * 
	 * @param pos position of cursor
	 */
	public void setCursor(Position pos) {
		setCursor(pos, null);
	}

	/**
	 * Set the cursor position.<br>
	 * You can pass a single position object.<br>
	 * Will replace all selections with a single, empty selection at the given position.<br>
	 * The supported options are the same as for setSelection
	 * 
	 * @param pos position of cursor
	 * @param options options instance
	 */
	public void setCursor(Position pos, CursorOptions options) {
		if (pos != null) {
			nativeObject.setCursor(pos, checkOptions(options).getObject());
		}
	}

	/**
	 * Set a single selection range.
	 * 
	 * @param range starting position and ending position
	 */
	public void setSelection(Range range) {
		setSelection(range, null);
	}

	/**
	 * Set a single selection range. These options are supported:<br>
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
	 * @param range starting position and ending position
	 * @param options options instance
	 */
	public void setSelection(Range range, CursorOptions options) {
		if (range != null) {
			setSelection(range.getFrom(), range.getTo(), options);
		}
	}

	/**
	 * Set a single selection range.<br>
	 * anchor and head should be position objects.<br>
	 * head defaults to anchor when not given.
	 * 
	 * @param anchor starting position
	 * @param head ending position
	 */
	public void setSelection(Position anchor, Position head) {
		setSelection(anchor, head, null);
	}

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
	public void setSelection(Position anchor, Position head, CursorOptions options) {
		if (anchor != null && head != null) {
			nativeObject.setSelection(anchor, head, checkOptions(options).getObject());
		}
	}

	/**
	 * Sets a new set of selections.<br>
	 * There must be at least one selection in the given array.
	 * 
	 * @param ranges array of anchors (anchors)
	 */
	public void setSelections(List<Anchor> ranges) {
		setSelections(ranges, null);
	}

	/**
	 * Sets a new set of selections.<br>
	 * There must be at least one selection in the given array.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param ranges array of anchors (anchors)
	 * @param options options instance
	 */
	public void setSelections(List<Anchor> ranges, CursorOptions options) {
		ArrayEntity<Anchor> array = ArrayEntity.fromOrNull(ranges);
		if (array != null) {
			nativeObject.setSelections(array, null, checkOptions(options).getObject());
		}
	}

	/**
	 * Sets a new set of selections.<br>
	 * There must be at least one selection in the given array.<br>
	 * When primary is a number, it determines which selection is the primary one.<br>
	 * When it is not given, the primary index is taken from the previous selection, or set to the last range if the previous
	 * selection had less ranges than the new one.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param ranges array of anchors (anchors)
	 * @param primary determines which selection is the primary one.<br>
	 *            When it is not given, the primary index is taken from the previous selection, or set to the last range if the
	 *            previous selection had less ranges than the new one.
	 * @param options options instance
	 */
	public void setSelections(List<Anchor> ranges, int primary, CursorOptions options) {
		ArrayEntity<Anchor> array = ArrayEntity.fromOrNull(ranges);
		if (array != null) {
			nativeObject.setSelections(array, primary, checkOptions(options).getObject());
		}
	}

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param range starting position and ending position
	 */
	public void addSelection(Range range) {
		if (range != null) {
			addSelection(range.getFrom(), range.getTo());
		}
	}

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param anchor starting position and ending position
	 */
	public void addSelection(Anchor anchor) {
		if (anchor != null) {
			addSelection(anchor.getAnchor(), anchor.getHead());
		}
	}

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param anchor starting position
	 */
	public void addSelection(Position anchor) {
		if (anchor != null) {
			nativeObject.addSelection(anchor);
		}
	}

	/**
	 * Adds a new selection to the existing set of selections, and makes it the primary selection.
	 * 
	 * @param anchor starting position
	 * @param head ending position
	 */
	public void addSelection(Position anchor, Position head) {
		if (anchor != null && head != null) {
			nativeObject.addSelection(anchor, head);
		}
	}

	/**
	 * Similar to setSelection, but will, if shift is held or the extending flag is set, move the head of the selection while
	 * leaving the anchor at its current place. to is optional, and can be passed to ensure a region (for example a word or
	 * paragraph) will end up selected (in addition to whatever lies between that region and the current anchor).<br>
	 * When multiple selections are present, all but the primary selection will be dropped by this method.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param range starting position and ending position
	 */
	public void extendSelection(Range range) {
		extendSelection(range, null);
	}

	/**
	 * Similar to setSelection, but will, if shift is held or the extending flag is set, move the head of the selection while
	 * leaving the anchor at its current place. to is optional, and can be passed to ensure a region (for example a word or
	 * paragraph) will end up selected (in addition to whatever lies between that region and the current anchor).<br>
	 * When multiple selections are present, all but the primary selection will be dropped by this method.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param from starting position
	 * @param to ending position
	 */
	public void extendSelection(Position from, Position to) {
		extendSelection(from, to, null);
	}

	/**
	 * Similar to setSelection, but will, if shift is held or the extending flag is set, move the head of the selection while
	 * leaving the anchor at its current place. to is optional, and can be passed to ensure a region (for example a word or
	 * paragraph) will end up selected (in addition to whatever lies between that region and the current anchor).<br>
	 * When multiple selections are present, all but the primary selection will be dropped by this method.<br>
	 * Supports the same options as setSelection.
	 * 
	 * @param range starting position and ending position
	 * @param options options instance
	 */
	public void extendSelection(Range range, CursorOptions options) {
		if (range != null) {
			extendSelection(range.getFrom(), range.getTo(), options);
		}
	}

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
	public void extendSelection(Position from, Position to, CursorOptions options) {
		if (from != null && to != null) {
			nativeObject.extendSelection(from, to, checkOptions(options).getObject());
		}
	}

	/**
	 * An equivalent of extendSelection that acts on all selections at once.
	 * 
	 * @param heads list of starting position
	 */
	public void extendSelections(List<Position> heads) {
		extendSelections(heads, null);
	}

	/**
	 * An equivalent of extendSelection that acts on all selections at once.
	 * 
	 * @param heads list of starting position
	 * @param options options instance
	 */
	public void extendSelections(List<Position> heads, CursorOptions options) {
		ArrayEntity<Position> array = ArrayEntity.fromOrNull(heads);
		if (array != null) {
			nativeObject.extendSelections(array, checkOptions(options).getObject());
		}
	}

	/**
	 * Returns the document extend selections handler.
	 * 
	 * @return the document extend selections handler
	 */
	public final DocumentExtendSelectionsHandler getDocumentExtendSelectionsHandler() {
		return documentExtendSelectionsHandler;
	}

	/**
	 * Sets the document extend selections handler.
	 * 
	 * @param documentExtendSelectionsHandler the document extend selections handler
	 */
	public final void setDocumentExtendSelectionsHandler(DocumentExtendSelectionsHandler documentExtendSelectionsHandler) {
		this.documentExtendSelectionsHandler = documentExtendSelectionsHandler;
	}

	/**
	 * Applies the given function to all existing selections, and calls extendSelections on the result.
	 */
	public void extendSelectionsBy() {
		extendSelectionsBy(null);
	}

	/**
	 * Applies the given function to all existing selections, and calls extendSelections on the result.
	 * 
	 * @param options options instance
	 */
	public void extendSelectionsBy(CursorOptions options) {
		if (this.documentExtendSelectionsHandler != null) {
			nativeObject.extendSelectionsBy(documentExtendSelectionsFunctionProxy.getProxy(), checkOptions(options).getObject());
		}
	}

	/**
	 * Applies the given function to all existing selections, and calls extendSelections on the result.
	 * 
	 * @param handler callback invoked to apply selection.
	 * @param options options instance
	 */
	public void extendSelectionsBy(DocumentExtendSelectionsHandler handler, CursorOptions options) {
		this.documentExtendSelectionsHandler = handler;
		extendSelectionsBy(options);
	}

	/**
	 * Sets or clears the 'extending' flag, which acts similar to the shift key, in that it will cause cursor movement and calls
	 * to extendSelection to leave the selection anchor in place.
	 * 
	 * @param value Sets or clears the 'extending' flag
	 */
	public void setExtending(boolean value) {
		nativeObject.setExtending(value);
	}

	/**
	 * Get the value of the 'extending' flag.
	 * 
	 * @return the value of the 'extending' flag.
	 */
	public boolean isExtending() {
		return nativeObject.getExtending();
	}

	/**
	 * Retrieve the editor associated with a document. May return null.
	 * 
	 * @return the editor associated with a document
	 */
	public Editor getEditor() {
		NativeEditor nativeEditor = nativeObject.getEditor();
		if (nativeEditor != null) {
			EditorArea area = nativeEditor.getEditorArea();
			if (area != null) {
				return area.getEditor();
			}
		}
		return null;
	}

	/**
	 * Gets the (outer) mode object for the editor.<br>
	 * Note that this is distinct from getOption("mode"), which gives you the mode specification, rather than the resolved,
	 * instantiated mode object
	 * 
	 * @return the (outer) mode object for the editor
	 */
	public Mode getMode() {
		ModeReference reference = nativeObject.getMode();
		if (reference != null && reference.getName() != null) {
			Mode mode = Modes.get().retrieve(reference.getName());
			if (mode != null) {
				return mode;
			}
		}
		return CodeMirror.get().getDefaults().getLanguage().getMode();
	}

	/**
	 * Create an identical copy of the given doc.
	 * 
	 * @return an identical copy of the given doc
	 */
	public Document copy() {
		return copy(false);
	}

	/**
	 * Create an identical copy of the given doc. When copyHistory is true, the history will also be copied.Can not be called
	 * directly on an editor.
	 * 
	 * @param copyHistory if true, the history will also be copied.Can not be called directly on an editor.
	 * @return an identical copy of the given doc
	 */
	public Document copy(boolean copyHistory) {
		Document copiedDocument = new Document(nativeObject.copy(copyHistory));
		copiedDocument.setDocumentEachLineHandler(documentEachLineHandler);
		copiedDocument.setDocumentExtendSelectionsHandler(documentExtendSelectionsHandler);
		copiedDocument.setLinkedDocumentsHandler(linkedDocumentsHandler);
		return copiedDocument;
	}

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
	public Document linkedDocument(LinkedDocumentOptions options) {
		return new Document(nativeObject.linkedDoc(checkOptions(options).getObject()));
	}

	/**
	 * Break the link between two documents.<br>
	 * After calling this, changes will no longer propagate between the documents, and, if they had a shared history, the
	 * history will become separate.
	 * 
	 * @param doc document to be unlinked
	 */
	public void unlinkDocument(Document doc) {
		if (doc != null) {
			nativeObject.unlinkDoc(doc.nativeObject);
		}
	}

	/**
	 * Returns the linked documents handler.
	 * 
	 * @return the linked documents handler
	 */
	public LinkedDocumentsHandler getLinkedDocumentsHandler() {
		return linkedDocumentsHandler;
	}

	/**
	 * Sets the linked documents handler.
	 * 
	 * @param linkedDocumentsHandler the linked documents handler
	 */
	public void setLinkedDocumentsHandler(LinkedDocumentsHandler linkedDocumentsHandler) {
		this.linkedDocumentsHandler = linkedDocumentsHandler;
	}

	/**
	 * Will call the given function for all documents linked to the target document.<br>
	 * It will be passed two arguments, the linked document and a boolean indicating whether that document shares history with
	 * the target.
	 * 
	 * @param function function instance to be called for all documents linked to the target document.
	 */
	public void iterLinkedDocuments() {
		if (this.linkedDocumentsHandler != null) {
			nativeObject.iterLinkedDocs(linkedDocumentsFunctionProxy.getProxy());
		}
	}

	/**
	 * Will call the given function for all documents linked to the target document.<br>
	 * It will be passed two arguments, the linked document and a boolean indicating whether that document shares history with
	 * the target.
	 * 
	 * @param function function instance to be called for all documents linked to the target document.
	 */
	public void iterLinkedDocuments(LinkedDocumentsHandler handler) {
		this.linkedDocumentsHandler = handler;
		iterLinkedDocuments();
	}

	/**
	 * Undo one edit (if any undo events are stored).
	 */
	public void undo() {
		nativeObject.undo();
	}

	/**
	 * Redo one undone edit.
	 */
	public void redo() {
		nativeObject.redo();
	}

	/**
	 * Undo one edit or selection change.
	 */
	public void undoSelection() {
		nativeObject.undoSelection();
	}

	/**
	 * Redo one undone edit or selection change.
	 */
	public void redoSelection() {
		nativeObject.redoSelection();
	}

	/**
	 * Returns an object with {undo, redo } properties , both of which hold integers , indicating the amount of stored undo and
	 * redo operations.
	 * 
	 * @return an object with {undo, redo } properties , both of which hold integers , indicating the amount of stored undo and
	 *         redo operations
	 */
	public HistorySize getHistorySize() {
		return new HistorySize(nativeObject.historySize());
	}

	/**
	 * Clears the editor's undo history.
	 */
	public void clearHistory() {
		nativeObject.clearHistory();
	}

	/**
	 * Get a (JSON-serializable) representation of the undo history.
	 * 
	 * @return a (JSON-serializable) representation of the undo history
	 */
	public History getHistory() {
		return new History(nativeObject.getHistory());
	}

	/**
	 * Replace the editor's undo history with the one provided, which must be a value as returned by getHistory.<br>
	 * Note that this will have entirely undefined results if the editor content isn't also the same as it was when getHistory
	 * was called.
	 * 
	 * @param history a (JSON-serializable) representation of the undo history
	 */
	void setHistory(History history) {
		if (history != null) {
			nativeObject.setHistory(history.getObject());
		}
	}

	/**
	 * The method will return an object that represents the marker (with constructor CodeMirror.TextMarker), which exposes three
	 * methods:<br>
	 * clear(), to remove the mark,<br>
	 * <br>
	 * find(), which returns a {from, to} object (both holding document positions), indicating the current position of the
	 * marked range, or undefined if the marker is no longer in the document, <br>
	 * <br>
	 * changed(), which you can call if you've done something that might change the size of the marker (for example changing the
	 * content of a replacedWith node), and want to cheaply update the display.<br>
	 * <br>
	 * Can be used to mark a range of text with a specific CSS class name.<br>
	 * from and to should be {line, ch} objects.<br>
	 * 
	 * @param range starting position and ending position of marker
	 * @return a text marker instance. If the range is not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Range range) {
		return markText(range, null);
	}

	/**
	 * The method will return an object that represents the marker (with constructor CodeMirror.TextMarker), which exposes three
	 * methods:<br>
	 * clear(), to remove the mark,<br>
	 * <br>
	 * find(), which returns a {from, to} object (both holding document positions), indicating the current position of the
	 * marked range, or undefined if the marker is no longer in the document, <br>
	 * <br>
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
	 * @param range starting position and ending position of marker
	 * @param options options instance
	 * @return a text marker instance. If the range is not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Range range, TextMarkerOptions options) {
		if (range != null) {
			return markText(range.getFrom(), range.getTo(), options);
		}
		return null;
	}

	/**
	 * The method will return an object that represents the marker (with constructor CodeMirror.TextMarker), which exposes three
	 * methods:<br>
	 * clear(), to remove the mark,<br>
	 * <br>
	 * find(), which returns a {from, to} object (both holding document positions), indicating the current position of the
	 * marked range, or undefined if the marker is no longer in the document, <br>
	 * <br>
	 * changed(), which you can call if you've done something that might change the size of the marker (for example changing the
	 * content of a replacedWith node), and want to cheaply update the display.<br>
	 * <br>
	 * Can be used to mark a range of text with a specific CSS class name.<br>
	 * from and to should be {line, ch} objects.<br>
	 * 
	 * 
	 * @param from starting position of marker
	 * @param to ending position of marker
	 * @return a text marker instance. If the positions are not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Position from, Position to) {
		return markText(from, to, null);
	}

	/**
	 * The method will return an object that represents the marker (with constructor CodeMirror.TextMarker), which exposes three
	 * methods:<br>
	 * clear(), to remove the mark,<br>
	 * <br>
	 * find(), which returns a {from, to} object (both holding document positions), indicating the current position of the
	 * marked range, or undefined if the marker is no longer in the document, <br>
	 * <br>
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
	 * @return a text marker instance. If the positions are not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Position from, Position to, TextMarkerOptions options) {
		if (from != null && to != null) {
			TextMarker marker = new TextMarker(nativeObject.markText(from, to, checkOptions(options, TextMarkerType.RANGE).getObject()), this);
			markers.put(marker.getId(), marker);
			return marker;
		}
		return null;
	}

	/**
	 * Inserts a bookmark, a handle that follows the text around it as it is being edited, at the given position.<br>
	 * A bookmark has two methods find() and clear().<br>
	 * The first returns the current position of the bookmark, if it is still in the document. <br>
	 * The second explicitly removes the bookmark.
	 * 
	 * @param pos position of bookmark
	 * @return a text marker instance. If the position is not consistent, returns <code>null</code>.
	 */
	public TextMarker setBookmark(Position pos) {
		return setBookmark(pos, null);
	}

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
	 * @return a text marker instance. If the position is not consistent, returns <code>null</code>.
	 */
	public TextMarker setBookmark(Position pos, TextMarkerOptions options) {
		if (pos != null) {
			TextMarker marker = new TextMarker(nativeObject.setBookmark(pos, checkOptions(options, TextMarkerType.BOOKMARK).getObject()), this);
			markers.put(marker.getId(), marker);
			return marker;
		}
		return null;
	}

	/**
	 * Returns a list of all the bookmarks and marked ranges found between the given range.
	 * 
	 * @param range starting position and ending position
	 * @return a list of all the bookmarks and marked ranges found between the given range
	 */
	public List<TextMarker> findMarks(Range range) {
		if (range != null) {
			return findMarks(range.getFrom(), range.getTo());
		}
		return Collections.emptyList();
	}

	/**
	 * Returns a list of all the bookmarks and marked ranges found between the given positions.
	 * 
	 * @param from starting position
	 * @param to ending position
	 * @return a list of all the bookmarks and marked ranges found between the given positions
	 */
	public List<TextMarker> findMarks(Position from, Position to) {
		if (from != null && to != null) {
			ArrayTextMarker array = nativeObject.findMarks(from, to);
			return load(array);
		}
		return Collections.emptyList();
	}

	/**
	 * Returns a list of all the bookmarks and marked ranges present at the given position.
	 * 
	 * @param pos position into document
	 * @return a list of all the bookmarks and marked ranges
	 */
	public List<TextMarker> findMarksAt(Position pos) {
		if (pos != null) {
			ArrayTextMarker array = nativeObject.findMarksAt(pos);
			return load(array);
		}
		return Collections.emptyList();
	}

	/**
	 * Returns a list containing all marked ranges in the document.
	 * 
	 * @return a list containing all marked ranges in the document.
	 */
	public List<TextMarker> getAllMarks() {
		ArrayTextMarker array = nativeObject.getAllMarks();
		return load(array);
	}

	/**
	 * Called by "clear" of text marker when it's clearing.
	 * 
	 * @param markerId the marker id.
	 */
	void clearTextMarker(int markerId) {
		markers.remove(markerId);
	}

	/**
	 * Sets the gutter marker for the given gutter (identified by its CSS class, see the gutters option) to the given value.
	 * Value can be either null, to clear the marker, or a DOM element, to set it.<br>
	 * The DOM element will be shown in the specified gutter next to the specified line.
	 * 
	 * @param line line row
	 * @param gutterID CSS class name (ID)
	 * @param value Value can be either null, to clear the marker, or a DOM element,
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle setGutterMarker(int line, String gutterID, Element value) {
		if (isValidLine(line) && gutterID != null) {
			NativeLineHandle nativeHandle = nativeObject.setGutterMarker(line, gutterID, value);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Sets the gutter marker for the given gutter (identified by its CSS class, see the gutters option) to the given value.
	 * Value can be either null, to clear the marker, or a DOM element, to set it.<br>
	 * The DOM element will be shown in the specified gutter next to the specified line.
	 * 
	 * @param line a line handler instance
	 * @param gutterID CSS class name (ID)
	 * @param value Value can be either null, to clear the marker, or a DOM element,
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle setGutterMarker(LineHandle line, String gutterID, Element value) {
		if (line != null && gutterID != null) {
			NativeLineHandle nativeHandle = nativeObject.setGutterMarker(line.getObject(), gutterID, value);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Remove all gutter markers in the gutter with the given ID.
	 * 
	 * @param gutterID CSS class name (ID)
	 */
	public void clearGutter(String gutterID) {
		if (gutterID != null) {
			nativeObject.clearGutter(gutterID);
		}
	}

	/**
	 * Set a CSS class name for the given line.<br>
	 * Class should be the name of the class to apply.
	 * 
	 * @param line line number
	 * @param className CSS class name
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle addLineClass(int line, String className) {
		return addLineClass(line, LineClassLocation.TEXT, className);
	}

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
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle addLineClass(int line, LineClassLocation where, String className) {
		if (isValidLine(line) && className != null) {
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			NativeLineHandle nativeHandle = nativeObject.addLineClass(line, whereToUse.value(), className);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Set a CSS class name for the given line.<br>
	 * class should be the name of the class to apply.
	 * 
	 * @param line line handler instance
	 * @param className CSS class name
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle addLineClass(LineHandle line, String className) {
		return addLineClass(line, LineClassLocation.TEXT, className);
	}

	/**
	 * Set a CSS class name for the given line.<br>
	 * <code>where</code>determines to which element this class should be applied, can can be one of "text" (the text element,
	 * which lies in front of the selection), "background" (a background element that will be behind the selection), "gutter"
	 * (the line's gutter space), or "wrap" (the wrapper node that wraps all of the line's elements, including gutter elements).
	 * class should be the name of the class to apply.
	 * 
	 * @param line line handler instance
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle addLineClass(LineHandle line, LineClassLocation where, String className) {
		if (line != null && className != null) {
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			NativeLineHandle nativeHandle = nativeObject.addLineClass(line.getObject(), whereToUse.value(), className);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Remove all CSS classes from a line.
	 * 
	 * @param line line number
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(int line) {
		return removeLineClass(line, LineClassLocation.TEXT);
	}

	/**
	 * Remove all CSS classes from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap".
	 * 
	 * @param line line number
	 * @param where determines to which element this class should be applied
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(int line, LineClassLocation where) {
		return removeLineClass(line, where, null);
	}

	/**
	 * Remove a CSS class from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap" (see addLineClass). class can be left off to remove
	 * all classes for the specified node, or be a string to remove only a specific class.
	 * 
	 * @param line line number
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(int line, LineClassLocation where, String className) {
		if (isValidLine(line)) {
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			NativeLineHandle nativeHandle = nativeObject.removeLineClass(line, whereToUse.value(), className);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Remove all CSS classes from a line.
	 * 
	 * @param line line handle
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(LineHandle line) {
		return removeLineClass(line, LineClassLocation.TEXT);
	}

	/**
	 * Remove all CSS classes from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap".
	 * 
	 * @param line line handle
	 * @param where determines to which element this class should be applied
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(LineHandle line, LineClassLocation where) {
		return removeLineClass(line, where, null);
	}

	/**
	 * Remove a CSS class from a line.<br>
	 * <code>where</code> should be one of "text", "background", or "wrap" (see addLineClass). class can be left off to remove
	 * all classes for the specified node, or be a string to remove only a specific class.
	 * 
	 * @param line line handle instance
	 * @param where determines to which element this class should be applied
	 * @param className CSS class name
	 * @return a line handle or <code>null</code> if the arguments are not consistent
	 */
	public LineHandle removeLineClass(LineHandle line, LineClassLocation where, String className) {
		if (line != null) {
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			NativeLineHandle nativeHandle = nativeObject.removeLineClass(line.getObject(), whereToUse.value(), className);
			if (nativeHandle != null) {
				return new LineHandle(nativeHandle);
			}
		}
		return null;
	}

	/**
	 * Called by "clear" of line widget when it's clearing.
	 * 
	 * @param widgetId the line widget id.
	 */
	void clearLineWidget(int widgetId) {
		lineWidgets.remove(widgetId);
	}

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
	 * @return
	 */
	public LineWidget addLineWidget(int line, Element node, LineWidgetOptions options) {
		if (isValidLine(line) && node != null) {
			NativeLineWidget nativeWidget = nativeObject.addLineWidget(line, node, checkOptions(options).getObject());
			if (nativeWidget != null) {
				LineWidget widget = new LineWidget(nativeWidget, this);
				lineWidgets.put(widget.getId(), widget);
				return widget;
			}
		}
		return null;
	}

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
	 * @param line LINEHANDLER
	 * @param node
	 * @param options
	 * @return
	 */
	public LineWidget addLineWidget(LineHandle line, Element node, LineWidgetOptions options) {
		if (line != null && node != null) {
			NativeLineWidget nativeWidget = nativeObject.addLineWidget(line.getObject(), node, checkOptions(options).getObject());
			if (nativeWidget != null) {
				LineWidget widget = new LineWidget(nativeWidget, this);
				lineWidgets.put(widget.getId(), widget);
				return widget;
			}
		}
		return null;
	}

	/**
	 * Returns an already created line widget by its id.
	 * 
	 * @param id line widget id.
	 * @return line widget instance or <code>null</code> if not exists
	 */
	LineWidget getLineWidget(int id) {
		return lineWidgets.get(id);
	}

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
	 *         attached to this line, and the various class properties refer to classes added with addLineClass.<br>
	 *         If arguments are not consistent, returns <code>null</code>.
	 */
	public LineInfo getLineInfo(int line) {
		if (isValidLine(line)) {
			NativeLineInfo nativeInfo = nativeObject.lineInfo(line);
			if (nativeInfo != null) {
				return new LineInfo(nativeInfo, this);
			}
		}
		return null;
	}

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
	 *         attached to this line, and the various class properties refer to classes added with addLineClass.<br>
	 *         If arguments are not consistent, returns <code>null</code>.
	 */
	public LineInfo getLineInfo(LineHandle line) {
		if (line != null) {
			NativeLineInfo nativeInfo = nativeObject.lineInfo(line.getObject());
			if (nativeInfo != null) {
				return new LineInfo(nativeInfo, this);
			}
		}
		return null;
	}

	/**
	 * Returns the preferred line separator string for this document, as per the option by the same name.<br>
	 * When that option is null, the string "\n" is returned.
	 * 
	 * @return the preferred line separator string for this document
	 */
	public String getLineSeparator() {
		return nativeObject.lineSeparator();
	}

	/**
	 * Calculates and returns a position object for a zero-based index who's value is relative to the start of the editor's
	 * text.<br>
	 * If the index is out of range of the text then the returned object is clipped to start or end of the text respectively.
	 * 
	 * @param index value is relative to the start of the editor's text
	 * @return a position object for a zero-based index
	 */
	public Position getPositionFromIndex(int index) {
		return nativeObject.posFromIndex(index);
	}

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text. If arguments are not consistent, returns
	 *         {@link UndefinedValues#INTEGER}
	 */
	public int getIndexFromPosition(Position position) {
		if (position != null) {
			return nativeObject.indexFromPos(position);
		}
		return UndefinedValues.INTEGER;
	}

	/**
	 * Checks if the passed line is consistent (inside the document).
	 * 
	 * @param line line number to check
	 * @return <code>true</code> if line number is consistent (inside the document)
	 */
	private boolean isValidLine(int line) {
		return line >= firstLine() && line <= lineCount();
	}

	/**
	 * Checks if passed options is consistent and then returns it otherwise returns an empty options.
	 * 
	 * @param options options instance to be checked
	 * @return if passed options is consistent and then returns it otherwise returns an empty options.
	 */
	private CursorOptions checkOptions(CursorOptions options) {
		return options != null ? options : new CursorOptions();
	}

	/**
	 * Checks if passed options is consistent and then returns it otherwise returns an empty options.
	 * 
	 * @param options options instance to be checked
	 * @return if passed options is consistent and then returns it otherwise returns an empty options.
	 */
	private LineWidgetOptions checkOptions(LineWidgetOptions options) {
		return options != null ? options : new LineWidgetOptions();
	}

	/**
	 * Checks if passed options is consistent and then returns it otherwise returns an empty options.
	 * 
	 * @param options options instance to be checked
	 * @return if passed options is consistent and then returns it otherwise returns an empty options.
	 */
	private LinkedDocumentOptions checkOptions(LinkedDocumentOptions options) {
		return options != null ? options : new LinkedDocumentOptions();
	}

	/**
	 * Checks if passed options is consistent and then returns it otherwise returns an empty options.
	 * 
	 * @param options options instance to be checked
	 * @return if passed options is consistent and then returns it otherwise returns an empty options.
	 */
	private TextMarkerOptions checkOptions(TextMarkerOptions options, TextMarkerType type) {
		if (options != null && options.getType().equals(type)) {
			return options;
		}
		return new TextMarkerOptions(type);
	}

	/**
	 * Loads the markers from an array, checking that the array is consistent with the map internally maintained.
	 * 
	 * @param array array of markers in CodeMirror
	 * @return a list of text markers
	 */
	private List<TextMarker> load(ArrayTextMarker array) {
		if (array != null && !array.isEmpty()) {
			List<TextMarker> result = new LinkedList<>();
			for (int i = 0; i < array.length(); i++) {
				NativeTextMarker nativeMarker = array.get(i);
				if (!markers.containsKey(nativeMarker.getId())) {
					markers.put(nativeMarker.getId(), new TextMarker(nativeMarker, this));
				}
				result.add(markers.get(nativeMarker.getId()));
			}
			return Collections.unmodifiableList(result);
		}
		return Collections.emptyList();
	}

}
