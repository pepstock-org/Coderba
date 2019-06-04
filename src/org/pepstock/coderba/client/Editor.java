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

import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.entities.Anchor;
import org.pepstock.coderba.client.entities.Area;
import org.pepstock.coderba.client.entities.Coordinate;
import org.pepstock.coderba.client.entities.Document;
import org.pepstock.coderba.client.entities.EventManager;
import org.pepstock.coderba.client.entities.NativeDocument;
import org.pepstock.coderba.client.entities.NativeLineHandle;
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
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.AddHandlerEventHandler;
import org.pepstock.coderba.client.events.EditorBeforeChangeEvent;
import org.pepstock.coderba.client.events.EditorBeforeSelectionChangeEvent;
import org.pepstock.coderba.client.events.EditorBeforeSelectionChangeItem;
import org.pepstock.coderba.client.events.EditorBlurEvent;
import org.pepstock.coderba.client.events.EditorChangeEvent;
import org.pepstock.coderba.client.events.EditorChangeItem;
import org.pepstock.coderba.client.events.EditorChangesEvent;
import org.pepstock.coderba.client.events.EditorCursorActivityEvent;
import org.pepstock.coderba.client.events.EditorElectrictInputEvent;
import org.pepstock.coderba.client.events.EditorFocusEvent;
import org.pepstock.coderba.client.events.EditorGutterClickEvent;
import org.pepstock.coderba.client.events.EditorGutterContextMenuEvent;
import org.pepstock.coderba.client.events.EditorInputReadEvent;
import org.pepstock.coderba.client.events.EditorKeyHandledEvent;
import org.pepstock.coderba.client.events.EditorOptionChangeEvent;
import org.pepstock.coderba.client.events.EditorRefreshEvent;
import org.pepstock.coderba.client.events.EditorRenderLineEvent;
import org.pepstock.coderba.client.events.EditorScrollCursorIntoViewEvent;
import org.pepstock.coderba.client.events.EditorScrollEvent;
import org.pepstock.coderba.client.events.EditorSwapDocEvent;
import org.pepstock.coderba.client.events.EditorUpdateEvent;
import org.pepstock.coderba.client.events.EditorViewportChangeEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEventHandler;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;

import jsinterop.annotations.JsFunction;

/**
 * CodeMirror is a code-editor component that can be embedded in Web pages.<br>
 * The core library provides only the editor component, no accompanying buttons, auto-completion, or other IDE
 * functionality.<br>
 * It does provide a rich API on top of which such functionality can be straightforwardly implemented.<br>
 * CodeMirror works with language-specific modes.<br>
 * Modes are JavaScript programs that help color (and optionally indent) text written in a given language.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Editor extends EventManager implements AddHandlerEventHandler, RemoveHandlerEventHandler {

	// ---------------------------
	// -- JAVASCRIPT FUNCTIONS ---
	// ---------------------------

	/**
	 * Java script FUNCTION that is called every time the content of the editor is changed.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorChangeFunction {

		/**
		 * Is called every time the content of the editor is changed.
		 * 
		 * @param editor native editor instance
		 * @param item editor change item
		 */
		void call(NativeEditor editor, EditorChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called like the "change" event, but batched per operation, passing an array containing all
	 * the changes that happened in the operation.<br>
	 * This event is fired after the operation finished, and display changes it makes will trigger a new operation.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorChangesFunction {

		/**
		 * Is called like the "change" event, but batched per operation, passing an array containing all the changes that
		 * happened in the operation.
		 * 
		 * @param editor native editor instance
		 * @param items array of changes
		 */
		void call(NativeEditor editor, ArrayEntity<EditorChangeItem> items);
	}

	/**
	 * Java script FUNCTION that is called before a change is applied, and its handler may choose to modify or cancel the
	 * change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorBeforeChangeFunction {

		/**
		 * Is called before a change is applied, and its handler may choose to modify or cancel the change.
		 * 
		 * @param editor native editor instance
		 * @param item editor change item
		 */
		void call(NativeEditor editor, EditorChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called when the cursor or selection moves, or any change is made to the editor content.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorCursorActivityFunction {

		/**
		 * Is called when the cursor or selection moves, or any change is made to the editor content.
		 * 
		 * @param editor native editor instance
		 */
		void call(NativeEditor editor);
	}

	/**
	 * Java script FUNCTION that is called after a key is handled through a key map.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorKeyHandledFunction {

		/**
		 * Is called after a key is handled through a key map.
		 * 
		 * @param editor native editor instance
		 * @param name the name of the handled key (for example "Ctrl-X" or "'q'")
		 * @param event the DOM keydown or keypress event.
		 */
		void call(NativeEditor editor, String name, NativeEvent event);
	}

	/**
	 * Java script FUNCTION that is called before the selection is moved.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorBeforeSelectionChangeFunction {

		/**
		 * Is called before the selection is moved.
		 * 
		 * @param editor native editor instance
		 * @param item editor change item
		 */
		void call(NativeEditor editor, EditorBeforeSelectionChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called whenever the editor is unfocused.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorBlurFunction {

		/**
		 * Is called whenever the editor is unfocused.
		 * 
		 * @param editor native editor instance
		 * @param event native DOM event
		 */
		void call(NativeEditor editor, NativeEvent event);
	}

	/**
	 * Java script FUNCTION that is called if text input matched the mode's electric patterns, and this caused the line's
	 * indentation to change.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorElectrictInputFunction {

		/**
		 * Is called if text input matched the mode's electric patterns, and this caused the line's indentation to change.
		 * 
		 * @param editor native editor instance
		 * @param line line number
		 */
		void call(NativeEditor editor, int line);
	}

	/**
	 * Java script FUNCTION that is called whenever the editor is focused.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorFocusFunction {

		/**
		 * Is called whenever the editor is focused.
		 * 
		 * @param editor native editor instance
		 * @param event native DOM event
		 */
		void call(NativeEditor editor, NativeEvent item);
	}

	/**
	 * Java script FUNCTION that is called when the editor gutter (the line-number area) is clicked.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorGutterClickFunction {

		/**
		 * Is called when the editor gutter (the line-number area) is clicked.
		 * 
		 * @param editor native editor instance
		 * @param line the (zero-based) number of the line that was clicked
		 * @param gutter the CSS class of the gutter that was clicked
		 * @param event the raw mousedown event object
		 */
		void call(NativeEditor editor, int line, String gutter, NativeEvent event);
	}

	/**
	 * Java script FUNCTION that is called when the editor gutter (the line-number area) receives a context menu event.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorGutterContextMenuFunction {

		/**
		 * Is called when the editor gutter (the line-number area) receives a context menu event.
		 * 
		 * @param editor native editor instance
		 * @param line the (zero-based) number of the line that was clicked
		 * @param gutter the CSS class of the gutter that was clicked
		 * @param event the raw contextmenu mouse event object
		 */
		void call(NativeEditor editor, int line, String gutter, NativeEvent event);
	}

	/**
	 * Java script FUNCTION that is called whenever new input is read from the hidden textarea (typed or pasted by the user).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorInputReadFunction {

		/**
		 * Is called whenever new input is read from the hidden textarea (typed or pasted by the user).
		 * 
		 * @param editor native editor instance
		 * @param item editor change item
		 */
		void call(NativeEditor editor, EditorChangeItem item);
	}

	/**
	 * Java script FUNCTION that is called every time an option is changed with "setOption".
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorOptionChangeFunction {

		/**
		 * Is called every time an option is changed with "setOption".
		 * 
		 * @param editor native editor instance
		 * @param property property name
		 */
		void call(NativeEditor editor, String property);
	}

	/**
	 * Java script FUNCTION that is called when the editor is refreshed or resized. Mostly useful to invalidate cached values
	 * that depend on the editor or character size.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorRefreshFunction {

		/**
		 * Is called when the editor is refreshed or resized. Mostly useful to invalidate cached values that depend on the
		 * editor or character size.
		 * 
		 * @param editor native editor instance
		 */
		void call(NativeEditor editor);
	}

	/**
	 * Java script FUNCTION that is called whenever a line is (re-)rendered to the DOM. Fired right after the DOM element is
	 * built, before it is added to the document.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorRenderLineFunction {

		/**
		 * Is called whenever a line is (re-)rendered to the DOM. Fired right after the DOM element is built, before it is added
		 * to the document.
		 * 
		 * @param editor native editor instance
		 * @param line the handler may mess with the style of the resulting element, or add event handlers, but should not try
		 *            to change the state of the editor.
		 * @param element the DOM element is built, before it is added to the document
		 */
		void call(NativeEditor editor, NativeLineHandle line, Element element);
	}

	/**
	 * Java script FUNCTION that is called when the editor tries to scroll its cursor into view. Can be hooked into to take care
	 * of additional scrollable containers around the editor.<br>
	 * When the event object has its preventDefault method called, CodeMirror will not itself try to scroll the window.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorScrollCursorIntoViewFunction {

		/**
		 * Is called when the editor tries to scroll its cursor into view. Can be hooked into to take care of additional
		 * scrollable containers around the editor.<br>
		 * When the event object has its preventDefault method called, CodeMirror will not itself try to scroll the window.
		 * 
		 * @param editor native editor instance
		 * @param event native DOM event
		 */
		void call(NativeEditor editor, NativeEvent event);
	}

	/**
	 * Java script FUNCTION that is called when the editor is scrolled.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorScrollFunction {

		/**
		 * Is called when the editor is scrolled.
		 * 
		 * @param editor native editor instance
		 */
		void call(NativeEditor editor);
	}

	/**
	 * Java script FUNCTION that is called when the editor's document is replaced using the swapDoc method.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorSwapDocFunction {

		/**
		 * Is called when the editor's document is replaced using the swapDoc method.
		 * 
		 * @param editor native editor instance
		 * @param oldDoc native old document instance
		 */
		void call(NativeEditor editor, NativeDocument oldDoc);
	}

	/**
	 * Java script FUNCTION that is called whenever CodeMirror updates its DOM display.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorUpdateFunction {

		/**
		 * Is called whenever CodeMirror updates its DOM display.
		 * 
		 * @param editor native editor instance
		 */
		void call(NativeEditor editor);
	}

	/**
	 * Java script FUNCTION that is called whenever the view port of the editor changes (due to scrolling, editing, or any other
	 * factor).
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorViewportChangeFunction {

		/**
		 * Is called whenever the view port of the editor changes (due to scrolling, editing, or any other factor).<br>
		 * The from and to arguments give the new start and end of the viewport.
		 * 
		 * @param editor native editor instance
		 * @param from the new start of the viewport
		 * @param to the new end of the viewport
		 */
		void call(NativeEditor editor, int from, int to);
	}

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the EditorBeforeChangeEvent function
	private final CallbackProxy<EditorBeforeChangeFunction> editorBeforeChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorBeforeSelectionChangeEvent function
	private final CallbackProxy<EditorBeforeSelectionChangeFunction> editorBeforeSelectionChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorBlurEvent function
	private final CallbackProxy<EditorBlurFunction> editorBlurFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorChangeEvent function
	private final CallbackProxy<EditorChangeFunction> editorChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorChangesEvent function
	private final CallbackProxy<EditorChangesFunction> editorChangesFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorCursorActivityEvent function
	private final CallbackProxy<EditorCursorActivityFunction> editorCursorActivityFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorElectrictInputEvent function
	private final CallbackProxy<EditorElectrictInputFunction> editorElectrictInputFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorFocusEvent function
	private final CallbackProxy<EditorFocusFunction> editorFocusFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorGutterClickEvent function
	private final CallbackProxy<EditorGutterClickFunction> editorGutterClickFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorGutterContextMenuEvent function
	private final CallbackProxy<EditorGutterContextMenuFunction> editorGutterContextMenuFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorInputReadEvent function
	private final CallbackProxy<EditorInputReadFunction> editorInputReadFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorKeyHandledEvent function
	private final CallbackProxy<EditorKeyHandledFunction> editorKeyHandledFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorOptionChangeEvent function
	private final CallbackProxy<EditorOptionChangeFunction> editorOptionChangeFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorRefreshEvent function
	private final CallbackProxy<EditorRefreshFunction> editorRefreshFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorRenderLineEvent function
	private final CallbackProxy<EditorRenderLineFunction> editorRenderLineFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorScrollCursorIntoViewEvent function
	private final CallbackProxy<EditorScrollCursorIntoViewFunction> editorScrollCursorIntoViewFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorScrollEvent function
	private final CallbackProxy<EditorScrollFunction> editorScrollFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorSwapDocEvent function
	private final CallbackProxy<EditorSwapDocFunction> editorSwapDocFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorUpdateEvent function
	private final CallbackProxy<EditorUpdateFunction> editorUpdateFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorViewportChangeEvent function
	private final CallbackProxy<EditorViewportChangeFunction> editorViewportChangeFunctionProxy = JsHelper.get().newCallbackProxy();

	private final NativeEditor nativeObject;

	private Document document = null;

	/**
	 * Creates an editor instance wrapping a native code mirror object.
	 * 
	 * @param nativeObject a native code mirror object
	 */
	Editor(NativeEditor nativeObject) {
		this.nativeObject = nativeObject;
		// gets also the document
		this.document = new Document(nativeObject.getDoc());
		addHandler(AddHandlerEvent.TYPE, this);
		addHandler(RemoveHandlerEvent.TYPE, this);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		editorChangeFunctionProxy.setCallback((editor, item) -> onChange(editor, item));
		editorChangesFunctionProxy.setCallback((editor, items) -> onChanges(editor, items));
		editorBeforeChangeFunctionProxy.setCallback((editor, item) -> onBeforeChange(editor, item));
		editorCursorActivityFunctionProxy.setCallback((editor) -> onCursorActivity(editor));
		editorKeyHandledFunctionProxy.setCallback((editor, name, event) -> onKeyHandled(editor, name, event));
		editorBeforeSelectionChangeFunctionProxy.setCallback((editor, item) -> onBeforeSelectionChange(editor, item));
		editorBlurFunctionProxy.setCallback((editor, event) -> onBlur(editor, event));
		editorElectrictInputFunctionProxy.setCallback((editor, line) -> onElectrictInput(editor, line));
		editorFocusFunctionProxy.setCallback((editor, event) -> onFocus(editor, event));
		editorGutterClickFunctionProxy.setCallback((editor, line, gutter, event) -> onGutterClick(editor, line, gutter, event));
		editorGutterContextMenuFunctionProxy.setCallback((editor, line, gutter, event) -> onGutterContextMenu(editor, line, gutter, event));
		editorInputReadFunctionProxy.setCallback((editor, item) -> onInputRead(editor, item));
		editorOptionChangeFunctionProxy.setCallback((editor, property) -> onOptionChange(editor, property));
		editorRefreshFunctionProxy.setCallback((editor) -> onRefresh(editor));
		editorRenderLineFunctionProxy.setCallback((editor, line, element) -> onRenderLine(editor, line, element));
		editorScrollCursorIntoViewFunctionProxy.setCallback((editor, event) -> onScrollCursorIntoView(editor, event));
		editorScrollFunctionProxy.setCallback((editor) -> onScroll(editor));
		editorSwapDocFunctionProxy.setCallback((editor, oldDoc) -> onSwapDoc(editor, oldDoc));
		editorUpdateFunctionProxy.setCallback((editor) -> onUpdate(editor));
		editorViewportChangeFunctionProxy.setCallback((editor, from, to) -> onViewportChange(editor, from, to));
	}

	// ---------------------------------
	// --- EVENTS METHODS
	// ---------------------------------

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param items
	 */
	private void onChanges(NativeEditor editor, ArrayEntity<EditorChangeItem> items) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			List<EditorChangeItem> list = ArrayListHelper.unmodifiableList(items);
			fireEvent(new EditorChangesEvent(area, list));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onChange(NativeEditor editor, EditorChangeItem item) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorChangeEvent(area, item));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onBeforeChange(NativeEditor editor, EditorChangeItem item) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorBeforeChangeEvent(area, item));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onCursorActivity(NativeEditor editor) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorCursorActivityEvent(area));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onKeyHandled(NativeEditor editor, String name, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorKeyHandledEvent(area, name, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onBeforeSelectionChange(NativeEditor editor, EditorBeforeSelectionChangeItem item) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorBeforeSelectionChangeEvent(area, item));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param event
	 */
	private void onBlur(NativeEditor editor, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorBlurEvent(area, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param line
	 */
	private void onElectrictInput(NativeEditor editor, int line) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorElectrictInputEvent(area, line));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param event
	 */
	private void onFocus(NativeEditor editor, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorFocusEvent(area, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param line
	 * @param gutter
	 * @param event
	 */
	private void onGutterClick(NativeEditor editor, int line, String gutter, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorGutterClickEvent(area, line, gutter, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param line
	 * @param gutter
	 * @param event
	 */
	private void onGutterContextMenu(NativeEditor editor, int line, String gutter, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorGutterContextMenuEvent(area, line, gutter, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param item
	 */
	private void onInputRead(NativeEditor editor, EditorChangeItem item) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorInputReadEvent(area, item));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param property
	 */
	private void onOptionChange(NativeEditor editor, String property) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorOptionChangeEvent(area, property));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 */
	private void onRefresh(NativeEditor editor) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorRefreshEvent(area));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param line
	 * @param element
	 */
	private void onRenderLine(NativeEditor editor, NativeLineHandle line, Element element) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			// FIXME
			// fireEvent(new EditorRenderLineEvent(area, line, element));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param event
	 */
	private void onScrollCursorIntoView(NativeEditor editor, NativeEvent event) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorScrollCursorIntoViewEvent(area, event));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 */
	private void onScroll(NativeEditor editor) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorScrollEvent(area));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 * @param oldDoc
	 */
	private void onSwapDoc(NativeEditor editor, NativeDocument oldDoc) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			// FIXME
			// fireEvent(new EditorSwapDocEvent(area, oldDoc));
		}
	}

	/**
	 * FIXME
	 * 
	 * @param editor
	 */
	private void onUpdate(NativeEditor editor) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorUpdateEvent(area));
		}
	}

	private void onViewportChange(NativeEditor editor, int from, int to) {
		EditorArea area = editor.getEditorArea();
		if (area != null) {
			fireEvent(new EditorViewportChangeEvent(area, from, to));
		}
	}

	// ---------------------------------
	// --- METHODS
	// ---------------------------------

	/**
	 * Sets the CODERBA id to editor options.
	 * 
	 * @param id the CODERBA id
	 */
	void setId(String id) {
		Id.set(nativeObject.getOptions(), id);
	}

	/**
	 * Returns the CODERBA id from editor options.
	 * 
	 * @return the CODERBA id
	 */
	public String getid() {
		return Id.get(nativeObject.getOptions());
	}

	/**
	 * Get the content of the current editor document.
	 * 
	 * @return he content of the current editor document
	 */
	public String getValue() {
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
	public String getValue(String separator) {
		// checks if argument is consistent
		if (separator != null) {
			// invokes by separator
			return nativeObject.getValue(separator);
		}
		// invokes get value without separator
		return getValue();
	}

	/**
	 * Set the content of the current editor document.
	 * 
	 * @param content the content of the current editor document
	 */
	public void setValue(String content) {
		// checks if argument is consistent
		if (content != null) {
			// sets value
			nativeObject.setValue(content);
		} else {
			// argument is null and then
			// set the default value
			nativeObject.setValue(Defaults.get().getValue());
		}
	}

	/**
	 * Tells you whether the editor currently has focus.
	 * 
	 * @return Tells you whether the editor currently has focus.
	 */
	public boolean hasFocus() {
		return nativeObject.hasFocus();
	}

	/**
	 * 
	 * @param visually When visually is true, motion in right-to-left text will be visual rather than logical
	 * @return a position that is produced by moving amount times the distance specified by unit. When the motion was clipped by
	 *         hitting the end or start of the document, the returned value will have a hitSide property set to true
	 */
	public Position findPosH(Position start, int amount, HorizontalFindUnit unit, boolean visually) {
		// checks if argument is consistent
		if (start != null && unit != null) {
			return nativeObject.findPosH(start, amount, unit.value(), visually);
		}
		// returns an empty position
		return Position.empty();
	}

	/**
	 * Used to find the target position for vertical cursor motion.
	 * 
	 * @param start a position object
	 * @param amount an integer (may be negative)
	 * @param unit one of the string "line" or "page"
	 * @return the target position for vertical cursor motion
	 */
	public Position findPosV(Position start, int amount, VerticalFindUnit unit) {
		// checks if argument is consistent
		if (start != null && unit != null) {
			return nativeObject.findPosV(start, amount, unit.value());
		}
		// returns an empty position
		return Position.empty();
	}

	/**
	 * Returns the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position.
	 * 
	 * @param position the position of the 'word' (the stretch of letters, whitespace, or punctuation)
	 * @return the start and end of the 'word' (the stretch of letters, whitespace, or punctuation) at the given position
	 */
	public Anchor findWordAt(Position position) {
		// checks if argument is consistent
		if (position != null) {
			return nativeObject.findWordAt(position);
		}
		// returns an empty area
		return Anchor.empty();
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.
	 * 
	 * @param language can be a mode spec or a mode object (an object with a token method)
	 */
	public void addOverlay(Language language) {
		addOverlay(language, null);
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.<br>
	 * For example, the search addon uses it to highlight the term that's currently being searched.
	 * 
	 * @param lanaguage can be a mode spec or a mode object (an object with a token method)
	 * @param options overlay options
	 */
	public void addOverlay(Language language, OverlayOptions options) {
		// checks if mode is consistent
		if (language != null) {
			// checks if options are consistent
			if (options != null) {
				// FIXME get mode spec by MIME MODE
				nativeObject.addOverlay(language.getName(), options);
			} else {
				// applies without options
				nativeObject.addOverlay(language.getName());
			}
		}
	}

	/**
	 * Pass this the exact value passed for the mode parameter to addOverlay, or a string that corresponds to the name property
	 * of that value, to remove an overlay again.
	 * 
	 * @param mode can be a mode spec or a mode object
	 */
	// FIXME get mode spec by MIME MODE
	public void removeOverlay(Mode mode) {
		if (mode != null) {
			nativeObject.removeOverlay(mode.getName());
		}
	}

	/**
	 * Retrieve the currently active document from an editor.
	 * 
	 * @return the currently active document from an editor
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Attach a new document to the editor.<br>
	 * Returns the old document, which is now no longer associated with an editor.
	 * 
	 * @param doc new document to the editor
	 * @return the old document, which is now no longer associated with an editor
	 */
	public Document swapDoc(Document doc) {
		// gets document
		Document currentDoc = getDocument();
		// checks if argument is consistent
		if (doc != null) {
			// swaps document
			nativeObject.swapDoc(doc.getNativeDocument());
			// and stores into this editor
			this.document = doc;
		}
		// returns the current doc
		return currentDoc;
	}

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules). width and height are numbers
	 * (interpreted as pixels).
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	public void setSize(double width, double height) {
		nativeObject.setSize(width, height);
	}

	/**
	 * Programmatically set the size of the editor (overriding the applicable CSS rules).<br>
	 * width and height are CSS units ("100%", for example).<br>
	 * You can pass null for either of them to indicate that that dimension should not be changed.
	 * 
	 * @param width width of editor
	 * @param height height of editor
	 */
	public void setSize(String width, String height) {
		nativeObject.setSize(width, height);
	}

	/**
	 * Scroll the editor to a given (pixel) position.<br>
	 * Both arguments may be left as null or undefined to have no effect.
	 * 
	 * @param x X position to scroll
	 * @param y Y position to scroll
	 */
	public void scrollTo(int x, int y) {
		nativeObject.scrollTo(x, y);
	}

	/**
	 * Get an scroll area object that represents the current scroll position, the size of the scrollable area, and the size of
	 * the visible area (minus scrollbars).
	 * 
	 * @return an scroll area object that represents the current scroll position, the size of the scrollable area, and the size
	 *         of the visible area (minus scrollbars).
	 */
	public ScrollArea getScrollInfo() {
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
	public void scrollIntoView(Position what, int margin) {
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
	public void scrollIntoView(Range what, int margin) {
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
	public void scrollIntoView(Area what, int margin) {
		nativeObject.scrollIntoView(what, margin);
	}

	/**
	 * Returns an area object containing the coordinates of the cursor position, whether you want the start (true) or the end
	 * (false) of the selection.
	 * 
	 * @param where a boolean indicating whether you want the start (true) or the end (false) of the selection
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return an area object containing the coordinates of the cursor position
	 */
	public Area cursorCoords(boolean where, CoordinatesMode mode) {
		// checks if mode is consistent
		if (mode != null) {
			return nativeObject.cursorCoords(where, mode.value());
		} else {
			// if here, uses the default
			return nativeObject.cursorCoords(where, CoordinatesMode.PAGE.value());
		}
	}

	/**
	 * Returns an area object containing the coordinates of the cursor position.<br>
	 * If a position object is given, it specifies the precise position at which you want to measure.
	 * 
	 * @param where is a position object is given, it specifies the precise position at which you want to measure.
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return an area object containing the coordinates of the cursor position
	 */
	public Area cursorCoords(Position where, CoordinatesMode mode) {
		// checks if position is consistent
		if (where != null) {
			// checks if mode is consistent
			if (mode != null) {
				return nativeObject.cursorCoords(where, mode.value());
			} else {
				// if here, uses the default
				return nativeObject.cursorCoords(where, CoordinatesMode.PAGE.value());
			}
		}
		// returns an empty area
		return Area.empty();
	}

	/**
	 * Returns the position and dimensions of an arbitrary character.<br>
	 * This differs from cursorCoords in that it'll give the size of the whole character, rather than just the position that the
	 * cursor would have when it would sit at that position.
	 * 
	 * @param pos a position object
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return an area object containing the coordinates of the cursor position
	 */
	public Area charCoords(Position position, CoordinatesMode mode) {
		// checks if position is consistent
		if (position != null) {
			// checks if mode is consistent
			if (mode != null) {
				return nativeObject.charCoords(position, mode.value());
			} else {
				// if here, uses the default
				return nativeObject.charCoords(position, CoordinatesMode.PAGE.value());
			}
		}
		// returns an empty area
		return Area.empty();
	}

	/**
	 * Given a point object (e.g. coordinates of a mouse event) returns the position that corresponds to it.<br>
	 * The optional mode parameter determines relative to what the coordinates are interpreted.<br>
	 * It may be "window", "page" (the default), or "local".
	 * 
	 * @param point a point object (e.g. coordinates of a mouse event)
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return the position that corresponds to coordinates (like a mouse event)
	 */
	public Position coordsChar(Point point, CoordinatesMode mode) {
		// checks if point is consistent
		if (point != null) {
			// checks if mode is consistent
			if (mode != null) {
				return nativeObject.coordsChar(point, mode.value());
			} else {
				// if here, uses the default
				return nativeObject.coordsChar(point, CoordinatesMode.PAGE.value());
			}
		}
		// returns an empty position
		return Position.empty();
	}

	/**
	 * Computes the line at the given pixel height.
	 * 
	 * @param height the line at the given pixel height
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return the line row
	 */
	public int lineAtHeight(double height, CoordinatesMode mode) {
		// checks if mode is consistent
		if (mode != null) {
			return nativeObject.lineAtHeight(height, mode.value());
		} else {
			// if here, uses the default
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
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @return the height of the top of a line, in the coordinate system specified by mode
	 */
	public double heightAtLine(int line, CoordinatesMode mode) {
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
	 * @param mode coordinates mode, If mode is "local", they will be relative to the top-left corner of the editable
	 *            document.<br>
	 *            If it is "page" or not given, they are relative to the top-left corner of the page.<br>
	 *            If mode is "window", the coordinates are relative to the top-left corner of the currently visible (scrolled)
	 *            window.
	 * @param includeWidgets if true and the line has line widgets, the position above the first line widget is returned
	 * @return the height of the top of a line, in the coordinate system specified by mode
	 */
	public double heightAtLine(int line, CoordinatesMode mode, boolean includeWidgets) {
		// checks if mode is consistent
		if (mode != null) {
			return nativeObject.heightAtLine(line, mode.value(), includeWidgets);
		} else {
			// if here, uses the default
			return nativeObject.heightAtLine(line, CoordinatesMode.PAGE.value(), includeWidgets);
		}
	}

	/**
	 * Returns the line height of the default font for the editor.
	 * 
	 * @return the line height of the default font for the editor.
	 */
	public double getDefaultTextHeight() {
		return nativeObject.defaultTextHeight();
	}

	/**
	 * Returns the pixel width of an 'x' in the default font for the editor.<br>
	 * Note that for non-mono space fonts, this is mostly useless, and even for mono space fonts, non-ascii characters might
	 * have a different width.
	 * 
	 * @return the pixel width of an 'x' in the default font for the editor
	 */
	public double getDefaultCharWidth() {
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
	public Coordinate getViewport() {
		return nativeObject.getViewport();
	}

	/**
	 * If your code does something to change the size of the editor element (window resizes are already listened for), or
	 * unhides it, you should probably follow up by calling this method to ensure CodeMirror is still looking as intended.
	 */
	public void refresh() {
		nativeObject.refresh();
	}

	/**
	 * Gets the inner mode at a given position.<br>
	 * This will return the same as getMode for simple modes, but will return an inner mode for nesting modes (such as
	 * htmlmixed).
	 * 
	 * @param position position to use
	 * @return the inner mode at a given position
	 */
	public ModeSpecification getModeAt(Position position) {
		// checks if position is consistent
		if (position != null) {
			return nativeObject.getModeAt(position);
		}
		// FIXME defaults;
		return Defaults.get().getLanguage().getModeSpecification();
	}

	/**
	 * Retrieves information about the token the current mode found before the given position (a position object).
	 * 
	 * @param position position of token
	 * @return a token instance
	 */
	public Token getTokenAt(Position position) {
		return getTokenAt(position, false);
	}

	/**
	 * Retrieves information about the token the current mode found before the given position (a position object).
	 * 
	 * @param position position of token
	 * @param precise If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not
	 *            specified, the token will use cached state information, which will be faster but might not be accurate if
	 *            edits were recently made and highlighting has not yet completed.
	 * @return a token instance
	 */
	public Token getTokenAt(Position position, boolean precise) {
		// checks if position is consistent
		if (position != null) {
			return nativeObject.getTokenAt(position, precise);
		}
		return null;
	}

	/**
	 * Collects all tokens for a given line into a list.
	 * 
	 * @param line the line row
	 * @return a list of tokens
	 */
	public List<Token> getLineTokens(int line) {
		return getLineTokens(line, false);
	}

	/**
	 * Collects all tokens for a given line into a list.
	 * 
	 * @param line the line row
	 * @param precise If precise is true, the token will be guaranteed to be accurate based on recent edits. If false or not
	 *            specified, the token will use cached state information, which will be faster but might not be accurate if
	 *            edits were recently made and highlighting has not yet completed.
	 * @return a list of tokens
	 */
	public List<Token> getLineTokens(int line, boolean precise) {
		ArrayEntity<Token> array = nativeObject.getLineTokens(line, precise);
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * This is a useful method for when you just need the type of the token at a given position, and no other information.<br>
	 * Will return null for un-styled tokens, and a string, potentially containing multiple space-separated style names,
	 * otherwise.
	 * 
	 * @param position position to use
	 * @return a type of token
	 */
	public String getTokenTypeAt(Position position) {
		// checks if position is consistent
		if (position != null) {
			return nativeObject.getTokenTypeAt(position);
		}
		// if here, position not consistent
		// then returns null
		return UndefinedValues.STRING;
	}

	/**
	 * Adjust the indentation of the given line.
	 * 
	 * @param line line row to indent
	 */
	public void indentLine(int line) {
		indentLine(line, null);
	}

	/**
	 * Adjust the indentation of the given line.
	 * 
	 * @param line line row to indent
	 * @param dir the direction (which defaults to "smart") may be one of: <br>
	 *            "prev" Base indentation on the indentation of the previous line.<br>
	 *            "smart" Use the mode's smart indentation if available, behave like "prev" otherwise.<br>
	 *            "add" Increase the indentation of the line by one indent unit.<br>
	 *            "subtract" Reduce the indentation of the line.<br>
	 */
	public void indentLine(int line, IndentLineMode dir) {
		// checks if direction is consistent
		if (dir != null) {
			nativeObject.indentLine(line, dir.value());
		} else {
			// if here uses the default
			nativeObject.indentLine(line, IndentLineMode.SMART.value());
		}
	}

	/**
	 * Adjust the indentation of the given line.
	 * 
	 * @param line line row to indent
	 * @param dir amount of spaces to add (positive number) or reduce (negative number) the indentation
	 */
	public void indentLine(int line, int dir) {
		nativeObject.indentLine(line, dir);
	}

	/**
	 * Switches between overwrite and normal insert mode (when not given an argument)
	 */
	public void toggleOverwrite() {
		nativeObject.toggleOverwrite();
	}

	/**
	 * Switches between overwrite and normal insert mode (when not given an argument), or sets the overwrite mode to a specific
	 * state (when given an argument).
	 * 
	 * @param value to set overwrite mode
	 */
	public void toggleOverwrite(boolean value) {
		nativeObject.toggleOverwrite(value);
	}

	/**
	 * Tells you whether the editor's content can be edited by the user.
	 * 
	 * @return Tells you whether the editor's content can be edited by the user.
	 */
	public boolean isReadOnly() {
		return nativeObject.isReadOnly();
	}

	/**
	 * Runs the command with the given name on the editor.
	 * 
	 * @param name the command with the given name on the editor.
	 */
	// FIXME
	public void execCommand(String name) {
		if (name != null) {
			nativeObject.execCommand(name);
		}
	}

	/**
	 * Give the editor focus.
	 */
	public void focus() {
		nativeObject.focus();
	}

	/**
	 * Allow the given string to be translated with the phrases option.
	 * 
	 * @param text string to be translated with the phrases option.
	 * @return translated text.
	 */
	public String phrase(String text) {
		// checks if argument is consistent
		if (text != null) {
			return nativeObject.phrase(text);
		}
		// if here returns an undefined string as result
		return UndefinedValues.STRING;
	}

	/**
	 * Returns the input field for the editor.<br>
	 * Will be a <code>textarea</code> or an editable <code>div</code> element, depending on the value of the inputStyle option.
	 * 
	 * @return the input field for the editor
	 */
	public Element getInputField() {
		return nativeObject.getInputField();
	}

	/**
	 * Returns the DOM node that represents the editor, and controls its size. Remove this from your tree to delete an editor
	 * instance.
	 * 
	 * @return the DOM node that represents the editor
	 */
	public Element getWrapperElement() {
		return nativeObject.getWrapperElement();
	}

	/**
	 * Returns the DOM node that is responsible for the scrolling of the editor.
	 * 
	 * @return the DOM node that is responsible for the scrolling of the editor
	 */
	public Element getScrollerElement() {
		return nativeObject.getScrollerElement();
	}

	/**
	 * Fetches the DOM node that contains the editor gutters.
	 * 
	 * @return the DOM node that contains the editor gutters
	 */
	public Element getGutterElement() {
		return nativeObject.getGutterElement();
	}

	// ------------------------------
	// --- EVENTS
	// ------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.coderba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
		if (event.isRecognize(EditorBeforeChangeEvent.TYPE)) {
			// checks if type of removed event handler is EditorBeforeChangeEvent
			// if there is not any EditorBeforeChangeEvent handler
			if (getHandlerCount(EditorBeforeChangeEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorBeforeChangeEvent.NAME, editorBeforeChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorBeforeSelectionChangeEvent.TYPE)) {
			// checks if type of removed event handler is EditorBeforeSelectionChangeEvent
			// if there is not any EditorBeforeSelectionChangeEvent handler
			if (getHandlerCount(EditorBeforeSelectionChangeEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorBeforeSelectionChangeEvent.NAME, editorBeforeSelectionChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorBlurEvent.TYPE)) {
			// checks if type of removed event handler is EditorBlurEvent
			// if there is not any EditorBlurEvent handler
			if (getHandlerCount(EditorBlurEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorBlurEvent.NAME, editorBlurFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorChangeEvent.TYPE)) {
			// checks if type of removed event handler is EditorChangeEvent
			// if there is not any EditorChangeEvent handler
			if (getHandlerCount(EditorChangeEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorChangeEvent.NAME, editorChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorChangesEvent.TYPE)) {
			// checks if type of removed event handler is EditorChangesEvent
			// if there is not any EditorChangesEvent handler
			if (getHandlerCount(EditorChangesEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorChangesEvent.NAME, editorChangesFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorCursorActivityEvent.TYPE)) {
			// checks if type of removed event handler is EditorCursorActivityEvent
			// if there is not any EditorCursorActivityEvent handler
			if (getHandlerCount(EditorCursorActivityEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorCursorActivityEvent.NAME, editorCursorActivityFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorElectrictInputEvent.TYPE)) {
			// checks if type of removed event handler is EditorElectrictInputEvent
			// if there is not any EditorElectrictInputEvent handler
			if (getHandlerCount(EditorElectrictInputEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorElectrictInputEvent.NAME, editorElectrictInputFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorFocusEvent.TYPE)) {
			// checks if type of removed event handler is EditorFocusEvent
			// if there is not any EditorFocusEvent handler
			if (getHandlerCount(EditorFocusEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorFocusEvent.NAME, editorFocusFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorGutterClickEvent.TYPE)) {
			// checks if type of removed event handler is EditorGutterClickEvent
			// if there is not any EditorGutterClickEvent handler
			if (getHandlerCount(EditorGutterClickEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorGutterClickEvent.NAME, editorGutterClickFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorGutterContextMenuEvent.TYPE)) {
			// checks if type of removed event handler is EditorGutterContextMenuEvent
			// if there is not any EditorGutterContextMenuEvent handler
			if (getHandlerCount(EditorGutterContextMenuEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorGutterContextMenuEvent.NAME, editorGutterContextMenuFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorInputReadEvent.TYPE)) {
			// checks if type of removed event handler is EditorInputReadEvent
			// if there is not any EditorInputReadEvent handler
			if (getHandlerCount(EditorInputReadEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorInputReadEvent.NAME, editorInputReadFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorKeyHandledEvent.TYPE)) {
			// checks if type of removed event handler is EditorKeyHandledEvent
			// if there is not any EditorKeyHandledEvent handler
			if (getHandlerCount(EditorKeyHandledEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorKeyHandledEvent.NAME, editorKeyHandledFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorOptionChangeEvent.TYPE)) {
			// checks if type of removed event handler is EditorOptionChangeEvent
			// if there is not any EditorOptionChangeEvent handler
			if (getHandlerCount(EditorOptionChangeEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorOptionChangeEvent.NAME, editorOptionChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorRefreshEvent.TYPE)) {
			// checks if type of removed event handler is EditorRefreshEvent
			// if there is not any EditorRefreshEvent handler
			if (getHandlerCount(EditorRefreshEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorRefreshEvent.NAME, editorRefreshFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorRenderLineEvent.TYPE)) {
			// checks if type of removed event handler is EditorRenderLineEvent
			// if there is not any EditorRenderLineEvent handler
			if (getHandlerCount(EditorRenderLineEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorRenderLineEvent.NAME, editorRenderLineFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorScrollCursorIntoViewEvent.TYPE)) {
			// checks if type of removed event handler is EditorScrollCursorIntoViewEvent
			// if there is not any EditorScrollCursorIntoViewEvent handler
			if (getHandlerCount(EditorScrollCursorIntoViewEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorScrollCursorIntoViewEvent.NAME, editorScrollCursorIntoViewFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorScrollEvent.TYPE)) {
			// checks if type of removed event handler is EditorScrollEvent
			// if there is not any EditorScrollEvent handler
			if (getHandlerCount(EditorScrollEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorScrollEvent.NAME, editorScrollFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorSwapDocEvent.TYPE)) {
			// checks if type of removed event handler is EditorSwapDocEvent
			// if there is not any EditorSwapDocEvent handler
			if (getHandlerCount(EditorSwapDocEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorSwapDocEvent.NAME, editorSwapDocFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorUpdateEvent.TYPE)) {
			// checks if type of removed event handler is EditorUpdateEvent
			// if there is not any EditorUpdateEvent handler
			if (getHandlerCount(EditorUpdateEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorUpdateEvent.NAME, editorUpdateFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorViewportChangeEvent.TYPE)) {
			// checks if type of removed event handler is EditorViewportChangeEvent
			// if there is not any EditorViewportChangeEvent handler
			if (getHandlerCount(EditorViewportChangeEvent.TYPE) == 0) {
				// sets OFF the callback proxy in order to call the user event interface
				nativeObject.off(EditorViewportChangeEvent.NAME, editorViewportChangeFunctionProxy.getProxy());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
		if (event.isRecognize(EditorBeforeChangeEvent.TYPE)) {
			// checks if type of added event handler is EditorBeforeChangeEvent
			// if there is not any EditorBeforeChangeEvent handler
			if (getHandlerCount(EditorBeforeChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorBeforeChangeEvent.NAME, editorBeforeChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorBeforeSelectionChangeEvent.TYPE)) {
			// checks if type of added event handler is EditorBeforeSelectionChangeEvent
			// if there is not any EditorBeforeSelectionChangeEvent handler
			if (getHandlerCount(EditorBeforeSelectionChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorBeforeSelectionChangeEvent.NAME, editorBeforeSelectionChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorBlurEvent.TYPE)) {
			// checks if type of added event handler is EditorBlurEvent
			// if there is not any EditorBlurEvent handler
			if (getHandlerCount(EditorBlurEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorBlurEvent.NAME, editorBlurFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorChangeEvent.TYPE)) {
			// checks if type of added event handler is EditorChangeEvent
			// if there is not any EditorChangeEvent handler
			if (getHandlerCount(EditorChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorChangeEvent.NAME, editorChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorChangesEvent.TYPE)) {
			// checks if type of added event handler is EditorChangesEvent
			// if there is not any EditorChangesEvent handler
			if (getHandlerCount(EditorChangesEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorChangesEvent.NAME, editorChangesFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorCursorActivityEvent.TYPE)) {
			// checks if type of added event handler is EditorCursorActivityEvent
			// if there is not any EditorCursorActivityEvent handler
			if (getHandlerCount(EditorCursorActivityEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorCursorActivityEvent.NAME, editorCursorActivityFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorElectrictInputEvent.TYPE)) {
			// checks if type of added event handler is EditorElectrictInputEvent
			// if there is not any EditorElectrictInputEvent handler
			if (getHandlerCount(EditorElectrictInputEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorElectrictInputEvent.NAME, editorElectrictInputFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorFocusEvent.TYPE)) {
			// checks if type of added event handler is EditorFocusEvent
			// if there is not any EditorFocusEvent handler
			if (getHandlerCount(EditorFocusEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorFocusEvent.NAME, editorFocusFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorGutterClickEvent.TYPE)) {
			// checks if type of added event handler is EditorGutterClickEvent
			// if there is not any EditorGutterClickEvent handler
			if (getHandlerCount(EditorGutterClickEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorGutterClickEvent.NAME, editorGutterClickFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorGutterContextMenuEvent.TYPE)) {
			// checks if type of added event handler is EditorGutterContextMenuEvent
			// if there is not any EditorGutterContextMenuEvent handler
			if (getHandlerCount(EditorGutterContextMenuEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorGutterContextMenuEvent.NAME, editorGutterContextMenuFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorInputReadEvent.TYPE)) {
			// checks if type of added event handler is EditorInputReadEvent
			// if there is not any EditorInputReadEvent handler
			if (getHandlerCount(EditorInputReadEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorInputReadEvent.NAME, editorInputReadFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorKeyHandledEvent.TYPE)) {
			// checks if type of added event handler is EditorKeyHandledEvent
			// if there is not any EditorKeyHandledEvent handler
			if (getHandlerCount(EditorKeyHandledEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorKeyHandledEvent.NAME, editorKeyHandledFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorOptionChangeEvent.TYPE)) {
			// checks if type of added event handler is EditorOptionChangeEvent
			// if there is not any EditorOptionChangeEvent handler
			if (getHandlerCount(EditorOptionChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorOptionChangeEvent.NAME, editorOptionChangeFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorRefreshEvent.TYPE)) {
			// checks if type of added event handler is EditorRefreshEvent
			// if there is not any EditorRefreshEvent handler
			if (getHandlerCount(EditorRefreshEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorRefreshEvent.NAME, editorRefreshFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorRenderLineEvent.TYPE)) {
			// checks if type of added event handler is EditorRenderLineEvent
			// if there is not any EditorRenderLineEvent handler
			if (getHandlerCount(EditorRenderLineEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorRenderLineEvent.NAME, editorRenderLineFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorScrollCursorIntoViewEvent.TYPE)) {
			// checks if type of added event handler is EditorScrollCursorIntoViewEvent
			// if there is not any EditorScrollCursorIntoViewEvent handler
			if (getHandlerCount(EditorScrollCursorIntoViewEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorScrollCursorIntoViewEvent.NAME, editorScrollCursorIntoViewFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorScrollEvent.TYPE)) {
			// checks if type of added event handler is EditorScrollEvent
			// if there is not any EditorScrollEvent handler
			if (getHandlerCount(EditorScrollEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorScrollEvent.NAME, editorScrollFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorSwapDocEvent.TYPE)) {
			// checks if type of added event handler is EditorSwapDocEvent
			// if there is not any EditorSwapDocEvent handler
			if (getHandlerCount(EditorSwapDocEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorSwapDocEvent.NAME, editorSwapDocFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorUpdateEvent.TYPE)) {
			// checks if type of added event handler is EditorUpdateEvent
			// if there is not any EditorUpdateEvent handler
			if (getHandlerCount(EditorUpdateEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorUpdateEvent.NAME, editorUpdateFunctionProxy.getProxy());
			}
		} else if (event.isRecognize(EditorViewportChangeEvent.TYPE)) {
			// checks if type of added event handler is EditorViewportChangeEvent
			// if there is not any EditorViewportChangeEvent handler
			if (getHandlerCount(EditorViewportChangeEvent.TYPE) == 1) {
				// sets the callback proxy in order to call the user event interface
				nativeObject.on(EditorViewportChangeEvent.NAME, editorViewportChangeFunctionProxy.getProxy());
			}
		}
	}

	/**
	 * Returns the native editor object.
	 * 
	 * @return the native editor object
	 */
	NativeEditor getNativeObject() {
		return nativeObject;
	}

}
