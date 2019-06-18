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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.GlobalDefaults;
import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.Language;
import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.Modes;
import org.pepstock.coderba.client.callbacks.DocumentEachLineHandler;
import org.pepstock.coderba.client.callbacks.DocumentExtendSelectionsHandler;
import org.pepstock.coderba.client.callbacks.LinkedDocumentsHandler;
import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.enums.CursorPosition;
import org.pepstock.coderba.client.enums.LineClassLocation;
import org.pepstock.coderba.client.enums.Select;
import org.pepstock.coderba.client.enums.TextMarkerType;
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.ChangeItem;
import org.pepstock.coderba.client.events.DocumentBeforeChangeEvent;
import org.pepstock.coderba.client.events.DocumentBeforeChangeEventHandler;
import org.pepstock.coderba.client.events.DocumentBeforeSelectionChangeEvent;
import org.pepstock.coderba.client.events.DocumentBeforeSelectionChangeEventHandler;
import org.pepstock.coderba.client.events.DocumentChangeEvent;
import org.pepstock.coderba.client.events.DocumentChangeEventHandler;
import org.pepstock.coderba.client.events.DocumentCursorActivityEvent;
import org.pepstock.coderba.client.events.DocumentCursorActivityEventHandler;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

import jsinterop.annotations.JsFunction;

/**
 * Entity which maps a document of an editor.<br>
 * It contains the content to show and all methods to manage them.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Document implements IsEventManager {

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

	/**
	 * Java script FUNCTION that is called every time the content of the document is changed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentChangeFunction {

		/**
		 * Is called every time the content of the document is changed.
		 * 
		 * @param document native document instance
		 * @param item document change item
		 */
		void call(NativeDocument document, ChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentBeforeChangeFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 * 
		 * @param document native document instance
		 * @param item document change item
		 */
		void call(NativeDocument document, ChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called when the cursor or selection moves, or any change is made to the document content.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentCursorActivityFunction {

		/**
		 * Is called when the cursor or selection moves, or any change is made to the document content.
		 * 
		 * @param document native document instance
		 */
		void call(NativeDocument document);
	}

	/**
	 * Java script FUNCTION that is called before the selection is moved.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface DocumentBeforeSelectionChangeFunction {

		/**
		 * Is called before the selection is moved.
		 * 
		 * @param document native document instance
		 * @param anchor anchor of selection
		 */
		void call(NativeDocument document, Anchor anchor);
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
	// callback proxy to invoke the DocumentBeforeChangeEvent function
	private final CallbackProxy<DocumentBeforeChangeFunction> documentBeforeChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DocumentBeforeSelectionChangeEvent function
	private final CallbackProxy<DocumentBeforeSelectionChangeFunction> documentBeforeSelectionChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DocumentChangeEvent function
	private final CallbackProxy<DocumentChangeFunction> documentChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the DocumentCursorActivityEvent function
	private final CallbackProxy<DocumentCursorActivityFunction> documentCursorActivityFunctionProxy = JsHelper.get().newCallbackProxy();
	// native document object (generated by CodeMirror)
	private final NativeDocument nativeObject;
	// event manager instance
	private final EventManager eventManager;
	// language of the document
	private final Language language;
	// maps all created markers
	private final Map<String, TextMarker> markers = new HashMap<>();
	// maps all line widgets
	private final Map<String, LineWidget> lineWidgets = new HashMap<>();
	// maps all line handles
	private final Map<String, LineHandle> lineHandles = new HashMap<>();
	// document each line handler callback
	private DocumentEachLineHandler documentEachLineHandler = null;
	// document extend selection handler callback
	private DocumentExtendSelectionsHandler documentExtendSelectionsHandler = null;
	// linked document handler callback
	private LinkedDocumentsHandler linkedDocumentsHandler = null;
	// event items manager instance
	private final EventItemManager eventItemManager;

	/**
	 * Creates an editor instance wrapping a native code mirror object.
	 * 
	 * @param nativeObject a native code mirror object
	 * @param language language to apply to the document
	 */
	Document(NativeDocument nativeObject, Language language) {
		// checks if language is consistent
		if (language == null) {
			throw new IllegalArgumentException("Language is null");
		}
		// stores native document
		this.nativeObject = nativeObject;
		// injects to be sure that is loaded
		Injector.ensureInjected(language);
		this.language = language;
		// sets event manager
		this.eventManager = new EventManager(this);
		this.eventItemManager = new EventItemManager();
		// stores id
		Id.applyTo(nativeObject);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		documentEachLineFunctionProxy.setCallback(this::onDocumentEachLine);
		documentExtendSelectionsFunctionProxy.setCallback(this::onDocumentExtendSelections);
		linkedDocumentsFunctionProxy.setCallback(this::onLinkedDcouments);
		documentChangeFunctionProxy.setCallback(this::onChange);
		documentBeforeChangeFunctionProxy.setCallback(this::onBeforeChange);
		documentCursorActivityFunctionProxy.setCallback(this::onCursorActivity);
		documentBeforeSelectionChangeFunctionProxy.setCallback(this::onBeforeSelectionChange);

		eventItemManager.addEventItem(new EventItem<DocumentChangeEventHandler, NativeDocument>(DocumentChangeEvent.TYPE, nativeObject, DocumentChangeEvent.NAME, eventManager, documentChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<DocumentBeforeChangeEventHandler, NativeDocument>(DocumentBeforeChangeEvent.TYPE, nativeObject, DocumentBeforeChangeEvent.NAME, eventManager, documentBeforeChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<DocumentCursorActivityEventHandler, NativeDocument>(DocumentCursorActivityEvent.TYPE, nativeObject, DocumentCursorActivityEvent.NAME, eventManager, documentCursorActivityFunctionProxy.getProxy()));
		eventItemManager.addEventItem(
				new EventItem<DocumentBeforeSelectionChangeEventHandler, NativeDocument>(DocumentBeforeSelectionChangeEvent.TYPE, nativeObject, DocumentBeforeSelectionChangeEvent.NAME, eventManager, documentBeforeSelectionChangeFunctionProxy.getProxy()));

		// adds to cache
		Documents.get().add(this);
	}

	/**
	 * Returns the native document.
	 * 
	 * @return the native document
	 */
	NativeDocument getObject() {
		return nativeObject;
	}

	/**
	 * Returns the unique ID of document.
	 * 
	 * @return the unique ID of document
	 */
	public String getId() {
		return Id.retrieveFrom(nativeObject);
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return the content of the document
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
		// checks if separator is consistent
		if (separator != null) {
			// returns by separator
			return nativeObject.getValue(separator);
		}
		// returns the value
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
		// checks if range is consistent
		if (range != null) {
			// returns the content
			return getRange(range.getFrom(), range.getTo());
		}
		// returns the default content (empty)
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
		// checks if argument are consistent
		if (from != null && to != null) {
			return nativeObject.getRange(from, to);
		}
		// returns the default content (empty)
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
		// checks if range is consistent
		if (range != null) {
			return getRange(range.getFrom(), range.getTo(), seperator);
		}
		// returns the default content (empty)
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
		// checks if argument are consistent
		if (from != null && to != null) {
			// checks if separator is consistent
			if (seperator != null) {
				// gets by separator
				return nativeObject.getRange(from, to, seperator);
			}
			// returns without separator
			return getRange(from, to);
		}
		// returns the default content (empty)
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
		// checks if argument are consistent
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
		// checks if range is consistent
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
		// checks if argument are consistent
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
		// checks if range is consistent
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
		// checks if argument are consistent
		if (replacement != null && from != null && to != null) {
			// if origin is consistent
			if (origin != null) {
				// invokes code mirror with origin
				nativeObject.replaceRange(replacement, from, to, origin);
			} else {
				// invokes without origin
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
		// checks if line is in range
		if (isValidLine(line)) {
			return nativeObject.getLine(line);
		}
		// returns the default content (empty)
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
		// checks if line is valid
		if (isValidLine(line)) {
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.getLineHandle(line);
			// checks if consistent
			if (nativeLineHandle != null) {
				// checks if there is an id
				String lineHandleId = Id.retrieveFrom(nativeLineHandle);
				// if line handle already in cache
				if (lineHandles.containsKey(lineHandleId)) {
					// returns from cache
					return lineHandles.get(lineHandleId);
				} else {
					// creates new line handle
					LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
					// stores into cache
					lineHandles.put(lineHandle.getId(), lineHandle);
					// returns it
					return lineHandle;
				}
			}
		}
		// returns null
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
		// checks if line handle is consistent
		if (handle != null) {
			return nativeObject.getLineNumber(handle.getObject());
		}
		// returns an undefined value
		return UndefinedValues.INTEGER;
	}

	/**
	 * Returns the "each line" handler.
	 * 
	 * @return the "each line" handler
	 */
	public DocumentEachLineHandler getDocumentEachLineHandler() {
		return documentEachLineHandler;
	}

	/**
	 * Sets the "each line" handler, previously calling the scan.
	 * 
	 * @param documentEachLineHandler the "each line" handler, previously calling the scan
	 */
	public void setDocumentEachLineHandler(DocumentEachLineHandler documentEachLineHandler) {
		this.documentEachLineHandler = documentEachLineHandler;
	}

	/**
	 * Iterate over the whole document, and call the "each line" handler previously set for each line, passing the line
	 * handle.<br>
	 * This is a faster way to visit a range of line handlers than calling getLineHandle for each of them.<br>
	 */
	public void eachLine() {
		// checks if handler is consistent
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
		// sets handler
		this.documentEachLineHandler = handler;
		// and scans all lines
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
		// checks if arguments are consistent
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
		// checks if separator is consistent
		if (separator != null) {
			return nativeObject.getSelection(separator);
		}
		// if not, invokes the selection without separator
		return getSelection();
	}

	/**
	 * Returns a list containing a string for each selection, representing the content of the selections.
	 * 
	 * @return a list containing a string for each selection, representing the content of the selections
	 */
	public List<String> getSelections() {
		ArrayString array = nativeObject.getSelections();
		return ArrayListHelper.list(array);
	}

	/**
	 * Returns a list containing a string for each selection, representing the content of the selections.<br>
	 * Optionally pass a line separator to split the lines.
	 * 
	 * @param separator You can pass it an optional argument to specify the string to be used to separate lines (defaults to
	 *            "\n"
	 * @return a list containing a string for each selection, representing the content of the selections
	 */
	public List<String> getSelections(String separator) {
		// checks if separator is consistent
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
		// checks if argument consistent
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
		// checks if argument consistent
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
	 * The length of the given list should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the list.
	 * 
	 * @param replacement list of strings to use for replacement
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
	 * The length of the given list should be the same as the number of active selections.<br>
	 * Replaces the content of the selections with the strings in the list.<br>
	 * The optional select argument can be used to change this-passing "around" will cause the new text to be selected, passing
	 * "start" will collapse the selection to the start of the inserted text.
	 * 
	 * @param replacement list of strings to use for replacement
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
		// checks if argument are consistent
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
	 * @return a position of cursor
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
	 * @return a position of cursor
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
		// checks if position is consistent
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
	 * Set a single selection range, with a cursor options instance.
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
		// checks if the list of ranges is consistent
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
		// checks if the list of ranges is consistent
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
		// checks if argument is consistent
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
		// checks if argument is consistent
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
		// checks if argument is consistent
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
		// checks if arguments are consistent
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
		// checks if argument is consistent
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
		// checks if arguments are consistent
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
		// checks if list argument is consistent
		if (array != null) {
			nativeObject.extendSelections(array, checkOptions(options).getObject());
		}
	}

	/**
	 * Returns the document extend selections handler.
	 * 
	 * @return the document extend selections handler
	 */
	public DocumentExtendSelectionsHandler getDocumentExtendSelectionsHandler() {
		return documentExtendSelectionsHandler;
	}

	/**
	 * Sets the document extend selections handler.
	 * 
	 * @param documentExtendSelectionsHandler the document extend selections handler
	 */
	public void setDocumentExtendSelectionsHandler(DocumentExtendSelectionsHandler documentExtendSelectionsHandler) {
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
		// checks if hanlder is consistent
		if (documentExtendSelectionsHandler != null) {
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
		// gets editor area
		EditorArea area = getEditorArea();
		// checks is consistent
		if (area != null) {
			// returns editor
			return area.getEditor();
		}
		// otherwise is null
		return null;
	}

	/**
	 * Retrieve the editor area associated with a document. May return null.
	 * 
	 * @return the editor area associated with a document
	 */
	public EditorArea getEditorArea() {
		// gets native editor
		NativeEditor nativeEditor = nativeObject.getEditor();
		// if consistent
		if (nativeEditor != null) {
			// returns editor area
			return nativeEditor.getEditorArea();
		}
		// if here, returns null
		return null;
	}

	/**
	 * Returns the language set to the document.
	 * 
	 * @return the language set to the document
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Gets the (outer) mode object for the editor.
	 * 
	 * @return the (outer) mode object for the editor
	 */
	public Mode getMode() {
		// gets the reference to mode
		ModeReference reference = nativeObject.getMode();
		// checks if reference is consistent
		if (reference != null && reference.getName() != null) {
			// gets the mode
			Mode mode = Modes.get().retrieve(reference.getName());
			// if mode is consistent
			if (mode != null) {
				return mode;
			}
		}
		// if here, the mode is not consistent
		// then returns the default
		return Defaults.get().getLanguage().getMode();
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
		// creates new document
		Document copiedDocument = new Document(nativeObject.copy(copyHistory), language);
		// copies the handlers
		copiedDocument.setDocumentEachLineHandler(documentEachLineHandler);
		copiedDocument.setDocumentExtendSelectionsHandler(documentExtendSelectionsHandler);
		copiedDocument.setLinkedDocumentsHandler(linkedDocumentsHandler);
		return copiedDocument;
	}

	/**
	 * Create a new document that's linked to the target document.<br>
	 * Linked documents will stay in sync (changes to one are also applied to the other) until unlinked.
	 * 
	 * @param options options instance
	 * @return new document that's linked to the target document
	 */
	public Document linkedDocument(LinkedDocumentOptions options) {
		// checks if language of new linked options has been set
		if (options.getLanguage() != null) {
			// linked the document with new language
			return new Document(nativeObject.linkedDoc(checkOptions(options).getObject()), options.getLanguage());
		} else {
			// if here it uses the language of this document
			return new Document(nativeObject.linkedDoc(checkOptions(options).getObject()), language);
		}
	}

	/**
	 * Break the link between two documents.<br>
	 * After calling this, changes will no longer propagate between the documents, and, if they had a shared history, the
	 * history will become separate.
	 * 
	 * @param doc document to be unlinked
	 */
	public void unlinkDocument(Document doc) {
		// checks if argument is consistent
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
	 * Will call the existing handler for all documents linked to the target document.
	 */
	public void iterLinkedDocuments() {
		// checks if handler is consistent
		if (linkedDocumentsHandler != null) {
			nativeObject.iterLinkedDocs(linkedDocumentsFunctionProxy.getProxy());
		}
	}

	/**
	 * Will call the given handler for all documents linked to the target document.
	 * 
	 * @param handler handler instance to be called for all documents linked to the target document.
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
	 * The method will return an object that represents the marker.
	 * 
	 * @param range starting position and ending position of marker
	 * @return a text marker instance. If the range is not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Range range) {
		return markText(range, null);
	}

	/**
	 * The method will return an object that represents the marker, with marker options.
	 * 
	 * 
	 * @param range starting position and ending position of marker
	 * @param options options instance
	 * @return a text marker instance. If the range is not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Range range, TextMarkerOptions options) {
		// checks if range is consistent
		if (range != null) {
			return markText(range.getFrom(), range.getTo(), options);
		}
		// if range is not consistent returns null
		return null;
	}

	/**
	 * The method will return an object that represents the marker.
	 * 
	 * @param from starting position of marker
	 * @param to ending position of marker
	 * @return a text marker instance. If the positions are not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Position from, Position to) {
		return markText(from, to, null);
	}

	/**
	 * The method will return an object that represents the marker, with marker options.
	 * 
	 * @param from starting position of marker
	 * @param to ending position of marker
	 * @param options options instance
	 * @return a text marker instance. If the positions are not consistent, returns <code>null</code>.
	 */
	public TextMarker markText(Position from, Position to, TextMarkerOptions options) {
		// checks if arguments are consistent
		if (from != null && to != null) {
			// creates a marker
			TextMarker marker = new TextMarker(nativeObject.markText(from, to, checkOptions(options, TextMarkerType.RANGE).getObject()), this);
			// stores markers into cache
			markers.put(marker.getId(), marker);
			return marker;
		}
		// if here, arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Inserts a bookmark, a handle that follows the text around it as it is being edited, at the given position.
	 * 
	 * @param pos position of bookmark
	 * @return a text marker instance. If the position is not consistent, returns <code>null</code>.
	 */
	public TextMarker setBookmark(Position pos) {
		return setBookmark(pos, null);
	}

	/**
	 * Returns an existing text marker instance by its id.
	 * 
	 * @param id id of text marker
	 * @return an existing text marker or <code>null</code> if not exists
	 */
	TextMarker getMarkerById(String id) {
		// checks if argument is consistent
		if (id != null) {
			return markers.get(id);
		}
		// if here, id is not consistent
		// then returns null
		return null;
	}

	/**
	 * Inserts a bookmark, a handle that follows the text around it as it is being edited, at the given position, with bookmark
	 * options.
	 * 
	 * @param pos position of bookmark
	 * @param options options instance
	 * @return a text marker instance. If the position is not consistent, returns <code>null</code>.
	 */
	public TextMarker setBookmark(Position pos, TextMarkerOptions options) {
		// checks if argument is consistent
		if (pos != null) {
			// creates a marker
			TextMarker marker = new TextMarker(nativeObject.setBookmark(pos, checkOptions(options, TextMarkerType.BOOKMARK).getObject()), this);
			// stores the marker into cache
			markers.put(marker.getId(), marker);
			return marker;
		}
		// if here, arguments are not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns a list of all the bookmarks and marked ranges found between the given range.
	 * 
	 * @param range starting position and ending position
	 * @return a list of all the bookmarks and marked ranges found between the given range
	 */
	public List<TextMarker> findMarks(Range range) {
		// checks if range is consistent
		if (range != null) {
			return findMarks(range.getFrom(), range.getTo());
		}
		// if not, returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns a unmodifiable list of all the bookmarks and marked ranges found between the given positions.
	 * 
	 * @param from starting position
	 * @param to ending position
	 * @return a unmodifiable list of all the bookmarks and marked ranges found between the given positions
	 */
	public List<TextMarker> findMarks(Position from, Position to) {
		// checks if positions are consistent
		if (from != null && to != null) {
			// gets the array
			ArrayTextMarker array = nativeObject.findMarks(from, to);
			// transforms into a list
			return load(array);
		}
		// if not, returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns a unmodifiable list of all the bookmarks and marked ranges present at the given position.
	 * 
	 * @param pos position into document
	 * @return a unmodifiable list of all the bookmarks and marked ranges
	 */
	public List<TextMarker> findMarksAt(Position pos) {
		// checks if position is consistent
		if (pos != null) {
			// gets the array
			ArrayTextMarker array = nativeObject.findMarksAt(pos);
			// transforms into a list
			return load(array);
		}
		// if not, returns an empty list
		return Collections.emptyList();
	}

	/**
	 * Returns a unmodifiable list containing all marked ranges in the document.
	 * 
	 * @return a unmodifiable list containing all marked ranges in the document.
	 */
	public List<TextMarker> getAllMarks() {
		// gets markers array
		ArrayTextMarker array = nativeObject.getAllMarks();
		// transforms into a list
		return load(array);
	}

	/**
	 * Called by "clear" of text marker when it's clearing.
	 * 
	 * @param id the marker id.
	 */
	void clearTextMarker(String id) {
		// checks if argument is consistent
		if (id != null) {
			markers.remove(id);
		}
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
		// checks if arguments are consistent
		if (isValidLine(line) && gutterID != null) {
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.setGutterMarker(line, gutterID, value);
			// checks if consistent
			if (nativeLineHandle != null) {
				// creates a line handle
				LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
				// stores into cache
				lineHandles.put(lineHandle.getId(), lineHandle);
				return lineHandle;
			}
		}
		// if not, returns null.
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
		// checks if arguments are consistent
		if (line != null && gutterID != null) {
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.setGutterMarker(line.getObject(), gutterID, value);
			// checks if consistent
			if (nativeLineHandle != null) {
				// creates a line handle
				LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
				// stores into cache
				lineHandles.put(lineHandle.getId(), lineHandle);
				return lineHandle;
			}
		}
		// if not, returns null.
		return null;
	}

	/**
	 * Returns a line handle by its id.
	 * 
	 * @param id the id of line handle
	 * @return already existing line handle or <code>null</code> if not exists
	 */
	LineHandle getLineHandleById(String id) {
		// checks if argument is consistent
		if (id != null) {
			return lineHandles.get(id);
		}
		// if here, id is not consistent
		// then returns null
		return null;
	}

	/**
	 * Remove all gutter markers in the gutter with the given ID.
	 * 
	 * @param gutterID CSS class name (ID)
	 */
	public void clearGutter(String gutterID) {
		// checks if gutter id is consistent
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
		// checks if lines and class name are consistent
		if (isValidLine(line) && className != null) {
			// checks line class location to use
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			// gets line handle
			NativeLineHandle nativeHandle = nativeObject.addLineClass(line, whereToUse.value(), className);
			// checks if consistent
			if (nativeHandle != null) {
				// creates a line handle
				LineHandle handle = new LineHandle(nativeHandle, this);
				// stores into cache
				lineHandles.put(handle.getId(), handle);
				return handle;
			}
		}
		// if arguments not consistent
		// returns null
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
		// checks if line and class name are consistent
		if (line != null && className != null) {
			// checks line class location to use
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.addLineClass(line.getObject(), whereToUse.value(), className);
			// checks if consistent
			if (nativeLineHandle != null) {
				// creates a line handle
				LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
				// stores into cache
				lineHandles.put(lineHandle.getId(), lineHandle);
				return lineHandle;
			}
		}
		// if arguments not consistent
		// returns null
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
		// checks if line is consistent
		if (isValidLine(line)) {
			// checks line class location to use
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.removeLineClass(line, whereToUse.value(), className);
			// checks if consistent
			if (nativeLineHandle != null) {
				// creates a line handle
				LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
				// stores into cache
				lineHandles.put(lineHandle.getId(), lineHandle);
				return lineHandle;
			}
		}
		// if arguments not consistent
		// returns null
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
		// checks if line is consistent
		if (line != null) {
			// checks line class location to use
			LineClassLocation whereToUse = where == null ? LineClassLocation.TEXT : where;
			// gets line handle
			NativeLineHandle nativeLineHandle = nativeObject.removeLineClass(line.getObject(), whereToUse.value(), className);
			// checks if consistent
			if (nativeLineHandle != null) {
				// creates a line handle
				LineHandle lineHandle = new LineHandle(nativeLineHandle, this);
				// stores into cache
				lineHandles.put(lineHandle.getId(), lineHandle);
				return lineHandle;
			}
		}
		// if arguments not consistent
		// returns null
		return null;
	}

	/**
	 * Called by "clear" of line widget when it's clearing.
	 * 
	 * @param id the line widget id.
	 */
	void clearLineWidget(String id) {
		// checks if id is consistent
		if (id != null) {
			lineWidgets.remove(id);
		}
	}

	/**
	 * Adds a line widget, an element shown below a line, spanning the whole of the editor's width, and moving the lines below
	 * it downwards. <br>
	 * node should be a DOM node, which will be displayed below the given line.<br>
	 * options, when given, should be an object that configures the behavior of the widget.
	 * 
	 * @param line line number
	 * @param node a DOM node, which will be displayed below the given line
	 * @param options object that configures the behavior of the widget
	 * @return a line widget instance
	 */
	public LineWidget addLineWidget(int line, Element node, LineWidgetOptions options) {
		// checks if lines and node are consistent
		if (isValidLine(line) && node != null) {
			// gets line widget
			NativeLineWidget nativeWidget = nativeObject.addLineWidget(line, node, checkOptions(options).getObject());
			// checks if consistent
			if (nativeWidget != null) {
				// creates a line widget
				LineWidget widget = new LineWidget(nativeWidget, this);
				// stores into cache
				lineWidgets.put(widget.getId(), widget);
				return widget;
			}
		}
		// if not, returns null
		return null;
	}

	/**
	 * Adds a line widget, an element shown below a line, spanning the whole of the editor's width, and moving the lines below
	 * it downwards. <br>
	 * node should be a DOM node, which will be displayed below the given line. options, when given, should be an object that
	 * configures the behavior of the widget.
	 * 
	 * @param line line handler instance
	 * @param node a DOM node, which will be displayed below the given line
	 * @param options object that configures the behavior of the widget
	 * @return a line widget instance
	 */
	public LineWidget addLineWidget(LineHandle line, Element node, LineWidgetOptions options) {
		// checks if lines and node are consistent
		if (line != null && node != null) {
			// gets line widget
			NativeLineWidget nativeWidget = nativeObject.addLineWidget(line.getObject(), node, checkOptions(options).getObject());
			// checks if consistent
			if (nativeWidget != null) {
				// creates a line widget
				LineWidget widget = new LineWidget(nativeWidget, this);
				// stores into cache
				lineWidgets.put(widget.getId(), widget);
				return widget;
			}
		}
		// if not, returns null
		return null;
	}

	/**
	 * Returns an already created line widget by its id.
	 * 
	 * @param id line widget id.
	 * @return line widget instance or <code>null</code> if not exists
	 */
	LineWidget getLineWidget(String id) {
		// checks if argument is consistent
		if (id != null) {
			return lineWidgets.get(id);
		}
		// if here, id is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the line number, text content, and marker status of the given line, which can be either a number or a line
	 * handle.
	 * 
	 * @param line line number
	 * @return a line info object
	 */
	public LineInfo getLineInfo(int line) {
		// checks if line is consistent
		if (isValidLine(line)) {
			// gets line info
			NativeLineInfo nativeInfo = nativeObject.lineInfo(line);
			// checks if consistent
			if (nativeInfo != null) {
				// returns a info object
				return new LineInfo(nativeInfo, this);
			}
		}
		// if here, line is not consistent
		// then returns null
		return null;
	}

	/**
	 * Returns the line number, text content, and marker status of the given line, which can be either a number or a line
	 * handle.
	 * 
	 * @param line line handle
	 * @return a line info object
	 */
	public LineInfo getLineInfo(LineHandle line) {
		// checks if line is consistent
		if (line != null) {
			// gets line info
			NativeLineInfo nativeInfo = nativeObject.lineInfo(line.getObject());
			// checks if consistent
			if (nativeInfo != null) {
				// returns a info object
				return new LineInfo(nativeInfo, this);
			}
		}
		// if here, line is not consistent
		// then returns null
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
		// checks if position is consistent
		if (position != null) {
			return nativeObject.indexFromPos(position);
		}
		// if here, argument is not consistent
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
		// checks if arguments are consistent
		if (options != null && options.getType().equals(type)) {
			return options;
		}
		// otherwise is creating an empty marker options
		return new TextMarkerOptions(type);
	}

	/**
	 * Loads the markers from an array, checking that the array is consistent with the map internally maintained.
	 * 
	 * @param array array of markers in CodeMirror
	 * @return a unmodifiable list of text markers
	 */
	private List<TextMarker> load(ArrayTextMarker array) {
		// checks if argument is consistent
		if (array != null && !array.isEmpty()) {
			// creates a list
			List<TextMarker> result = new LinkedList<>();
			// scans all native markers
			for (int i = 0; i < array.length(); i++) {
				// gets marker
				NativeTextMarker nativeMarker = array.get(i);
				// gets its ID
				String storedId = Id.retrieveFrom(nativeMarker);
				// if it has not got an ID
				if (storedId == null) {
					// creates new marker
					TextMarker newTextMarker = new TextMarker(nativeMarker, this);
					// gets new ID
					storedId = newTextMarker.getId();
					// stores into cache
					markers.put(storedId, newTextMarker);
				}
				// loads result list
				result.add(markers.get(storedId));
			}
			// returns the list
			return Collections.unmodifiableList(result);
		}
		// if argument is not consistent
		// returns an empty list
		return Collections.emptyList();
	}

	// ---------------------------------
	// --- CALLBACkS METHODS
	// ---------------------------------

	/**
	 * A function that is called to iterate over the whole document, and call callback for each line, passing the line handle.
	 * 
	 * @param nativeLineHandle line handle for each row
	 */
	private void onDocumentEachLine(NativeLineHandle nativeLineHandle) {
		// gets the native editor
		NativeEditor nativeEditor = nativeObject.getEditor();
		// checks if handler and native editor are consistent
		if (documentEachLineHandler != null && nativeEditor != null) {
			// gets editor area
			EditorArea area = nativeEditor.getEditorArea();
			// checks area is consistent
			if (area != null) {
				// gets line handle ID
				LineHandle lineHandle;
				String lineHandleId = Id.retrieveFrom(nativeLineHandle);
				// if contains into cache
				if (lineHandles.containsKey(lineHandleId)) {
					lineHandle = lineHandles.get(lineHandleId);
				} else {
					// or creates new line handle
					lineHandle = new LineHandle(nativeLineHandle, this);
					// stores into cache
					lineHandles.put(lineHandle.getId(), lineHandle);
				}
				// invokes the handler
				documentEachLineHandler.handle(area, lineHandle);
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
		// gets the native editor
		NativeEditor nativeEditor = nativeObject.getEditor();
		// checks if handler and native editor are consistent
		if (documentExtendSelectionsHandler != null && nativeEditor != null) {
			// gets editor area
			EditorArea area = nativeEditor.getEditorArea();
			// checks area is consistent
			if (area != null) {
				// invokes the handler
				Position result = documentExtendSelectionsHandler.handle(area, anchor);
				// if result is consistent
				if (result != null) {
					return result;
				}
			}
		}
		// if arguments are not consistent
		// returns the anchor position of argument
		return anchor.getAnchor();
	}

	/**
	 * A function that is called for all documents linked to the target document.
	 * 
	 * @param document the linked document
	 * @param sharedHistory indicating whether that document shares history with the target.
	 */
	private void onLinkedDcouments(NativeDocument document, boolean sharedHistory) {
		// gets the native editor
		NativeEditor nativeEditor = document.getEditor();
		// checks if handler and native editor are consistent
		if (linkedDocumentsHandler != null && nativeEditor != null) {
			// gets editor area
			EditorArea area = nativeEditor.getEditorArea();
			// checks area is consistent
			if (area != null) {
				// gets the document by document id
				Document storedDocument = Documents.get().retrieve(Id.retrieveFrom(document));
				// invokes the handler
				linkedDocumentsHandler.handle(area, storedDocument, sharedHistory);
			}
		}
	}

	// ---------------------------------
	// --- EVENTS METHODS
	// ---------------------------------

	/**
	 * Fired whenever a change occurs to the document.
	 * 
	 * @param document native document instance
	 * @param item change item instance
	 */
	private void onChange(NativeDocument document, ChangeItem item) {
		// gets editor instance
		NativeEditor editor = document.getEditor();
		// checks if editor is consistent
		if (editor != null) {
			// gets editor area
			EditorArea area = editor.getEditorArea();
			// checks if area is consistent
			if (area != null) {
				// fires event
				eventManager.fireEvent(new DocumentChangeEvent(this, item));
			}
		}
	}

	/**
	 * This event is fired before a change is applied, and its handler may choose to modify or cancel the change.
	 * 
	 * @param document native document instance
	 * @param item change item instance
	 */
	private void onBeforeChange(NativeDocument document, ChangeItem item) {
		// gets editor instance
		NativeEditor editor = document.getEditor();
		// checks if editor is consistent
		if (editor != null) {
			// gets editor area
			EditorArea area = editor.getEditorArea();
			// checks if area is consistent
			if (area != null) {
				// fires event
				eventManager.fireEvent(new DocumentBeforeChangeEvent(this, item));
			}
		}
	}

	/**
	 * Fired whenever the cursor or selection in this document changes.
	 * 
	 * @param document native document instance
	 */
	private void onCursorActivity(NativeDocument document) {
		// gets editor instance
		NativeEditor editor = document.getEditor();
		// checks if editor is consistent
		if (editor != null) {
			// gets editor area
			EditorArea area = editor.getEditorArea();
			// checks if area is consistent
			if (area != null) {
				// fires event
				eventManager.fireEvent(new DocumentCursorActivityEvent(this));
			}
		}
	}

	/**
	 * This event is fired before the selection is moved. Its handler may inspect a range.
	 * 
	 * @param document native document instance
	 * @param anchor anchor of selection
	 */
	private void onBeforeSelectionChange(NativeDocument document, Anchor anchor) {
		// gets editor instance
		NativeEditor editor = document.getEditor();
		// checks if editor is consistent
		if (editor != null) {
			// gets editor area
			EditorArea area = editor.getEditorArea();
			// checks if area is consistent
			if (area != null) {
				// fires event
				eventManager.fireEvent(new DocumentBeforeSelectionChangeEvent(this, anchor));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.IsEventManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		return eventManager.addHandler(type, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.coderba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
		eventItemManager.checkAndOff(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		eventItemManager.checkAndOn(event);
	}

}
