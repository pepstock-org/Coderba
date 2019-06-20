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
import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.addons.AddOnSearchcursor;
import org.pepstock.coderba.client.callbacks.DialogHandler;
import org.pepstock.coderba.client.commons.ArrayEntity;
import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.Id;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.enums.CoordinatesMode;
import org.pepstock.coderba.client.enums.HorizontalFindUnit;
import org.pepstock.coderba.client.enums.IndentLineMode;
import org.pepstock.coderba.client.enums.VerticalFindUnit;
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.BeforeSelectionChangeItem;
import org.pepstock.coderba.client.events.ChangeItem;
import org.pepstock.coderba.client.events.EditorBeforeChangeEvent;
import org.pepstock.coderba.client.events.EditorBeforeChangeEventHandler;
import org.pepstock.coderba.client.events.EditorBeforeSelectionChangeEvent;
import org.pepstock.coderba.client.events.EditorBeforeSelectionChangeEventHandler;
import org.pepstock.coderba.client.events.EditorBlurEvent;
import org.pepstock.coderba.client.events.EditorBlurEventHandler;
import org.pepstock.coderba.client.events.EditorChangeEvent;
import org.pepstock.coderba.client.events.EditorChangeEventHandler;
import org.pepstock.coderba.client.events.EditorChangesEvent;
import org.pepstock.coderba.client.events.EditorChangesEventHandler;
import org.pepstock.coderba.client.events.EditorContextmenuEvent;
import org.pepstock.coderba.client.events.EditorContextmenuEventHandler;
import org.pepstock.coderba.client.events.EditorCopyEvent;
import org.pepstock.coderba.client.events.EditorCopyEventHandler;
import org.pepstock.coderba.client.events.EditorCursorActivityEvent;
import org.pepstock.coderba.client.events.EditorCursorActivityEventHandler;
import org.pepstock.coderba.client.events.EditorCutEvent;
import org.pepstock.coderba.client.events.EditorCutEventHandler;
import org.pepstock.coderba.client.events.EditorDblclickEvent;
import org.pepstock.coderba.client.events.EditorDblclickEventHandler;
import org.pepstock.coderba.client.events.EditorDragenterEvent;
import org.pepstock.coderba.client.events.EditorDragenterEventHandler;
import org.pepstock.coderba.client.events.EditorDragleaveEvent;
import org.pepstock.coderba.client.events.EditorDragleaveEventHandler;
import org.pepstock.coderba.client.events.EditorDragoverEvent;
import org.pepstock.coderba.client.events.EditorDragoverEventHandler;
import org.pepstock.coderba.client.events.EditorDragstartEvent;
import org.pepstock.coderba.client.events.EditorDragstartEventHandler;
import org.pepstock.coderba.client.events.EditorDropEvent;
import org.pepstock.coderba.client.events.EditorDropEventHandler;
import org.pepstock.coderba.client.events.EditorElectrictInputEvent;
import org.pepstock.coderba.client.events.EditorElectrictInputEventHandler;
import org.pepstock.coderba.client.events.EditorFocusEvent;
import org.pepstock.coderba.client.events.EditorFocusEventHandler;
import org.pepstock.coderba.client.events.EditorGutterClickEvent;
import org.pepstock.coderba.client.events.EditorGutterClickEventHandler;
import org.pepstock.coderba.client.events.EditorGutterContextMenuEvent;
import org.pepstock.coderba.client.events.EditorGutterContextMenuEventHandler;
import org.pepstock.coderba.client.events.EditorInputReadEvent;
import org.pepstock.coderba.client.events.EditorInputReadEventHandler;
import org.pepstock.coderba.client.events.EditorKeyHandledEvent;
import org.pepstock.coderba.client.events.EditorKeyHandledEventHandler;
import org.pepstock.coderba.client.events.EditorKeydownEvent;
import org.pepstock.coderba.client.events.EditorKeydownEventHandler;
import org.pepstock.coderba.client.events.EditorKeypressEvent;
import org.pepstock.coderba.client.events.EditorKeypressEventHandler;
import org.pepstock.coderba.client.events.EditorKeyupEvent;
import org.pepstock.coderba.client.events.EditorKeyupEventHandler;
import org.pepstock.coderba.client.events.EditorMousedownEvent;
import org.pepstock.coderba.client.events.EditorMousedownEventHandler;
import org.pepstock.coderba.client.events.EditorNativeEvent;
import org.pepstock.coderba.client.events.EditorOptionChangeEvent;
import org.pepstock.coderba.client.events.EditorOptionChangeEventHandler;
import org.pepstock.coderba.client.events.EditorPasteEvent;
import org.pepstock.coderba.client.events.EditorPasteEventHandler;
import org.pepstock.coderba.client.events.EditorRefreshEvent;
import org.pepstock.coderba.client.events.EditorRefreshEventHandler;
import org.pepstock.coderba.client.events.EditorRenderLineEvent;
import org.pepstock.coderba.client.events.EditorRenderLineEventHandler;
import org.pepstock.coderba.client.events.EditorScrollCursorIntoViewEvent;
import org.pepstock.coderba.client.events.EditorScrollCursorIntoViewEventHandler;
import org.pepstock.coderba.client.events.EditorScrollEvent;
import org.pepstock.coderba.client.events.EditorScrollEventHandler;
import org.pepstock.coderba.client.events.EditorSwapDocEvent;
import org.pepstock.coderba.client.events.EditorSwapDocEventHandler;
import org.pepstock.coderba.client.events.EditorTouchstartEvent;
import org.pepstock.coderba.client.events.EditorTouchstartEventHandler;
import org.pepstock.coderba.client.events.EditorUpdateEvent;
import org.pepstock.coderba.client.events.EditorUpdateEventHandler;
import org.pepstock.coderba.client.events.EditorViewportChangeEvent;
import org.pepstock.coderba.client.events.EditorViewportChangeEventHandler;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;
import org.pepstock.coderba.client.utils.RegExp;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

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
public final class Editor implements IsEventManager {

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
		void call(NativeEditor editor, ChangeItem item);
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
		void call(NativeEditor editor, ArrayEntity<ChangeItem> items);
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
		void call(NativeEditor editor, ChangeItem item);
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
		void call(NativeEditor editor, String name, EditorNativeEvent event);
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
		void call(NativeEditor editor, BeforeSelectionChangeItem item);
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
		void call(NativeEditor editor, EditorNativeEvent event);
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
		void call(NativeEditor editor, EditorNativeEvent item);
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
		void call(NativeEditor editor, int line, String gutter, EditorNativeEvent event);
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
		void call(NativeEditor editor, int line, String gutter, EditorNativeEvent event);
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
		void call(NativeEditor editor, ChangeItem item);
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
		void call(NativeEditor editor, EditorNativeEvent event);
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

	/**
	 * Java script FUNCTION that is called when the editorFired when CodeMirror is handling a DOM event of this type.<br>
	 * You can <code>preventDefault</code> the event, or give it a truthy <code>codemirrorIgnore</code> property, to signal that
	 * CodeMirror should do no further handling.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	@JsFunction
	interface EditorDOMEventFunction {

		/**
		 * Is called when the editorFired when CodeMirror is handling a DOM event of this type.<br>
		 * You can <code>preventDefault</code> the event, or give it a truthy <code>codemirrorIgnore</code> property, to signal
		 * that CodeMirror should do no further handling.
		 * 
		 * @param editor native editor instance
		 * @param event native DOM event
		 */
		void call(NativeEditor editor, EditorNativeEvent event);
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
	// callback proxy to invoke the EditorMousedownEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorMousedownFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDblclickEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDblclickFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorTouchstartEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorTouchstartFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorContextmenuEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorContextmenuFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorKeydownEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorKeydownFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorKeypressEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorKeypressFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorKeyupEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorKeyupFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorCutEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorCutFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorCopyEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorCopyFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorPasteEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorPasteFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDragstartEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDragstartFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDragenterEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDragenterFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDragoverEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDragoverFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDragleaveEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDragleaveFunctionProxy = JsHelper.get().newCallbackProxy();
	// callback proxy to invoke the EditorDropEvent function
	private final CallbackProxy<EditorDOMEventFunction> editorDropFunctionProxy = JsHelper.get().newCallbackProxy();

	// native editor object (generated by CodeMirror)
	private final NativeEditor nativeObject;
	// event manager instance
	private final EventManager eventManager;
	// the current document managed by editor
	private Document document = null;
	// event items manager instance
	private final EventItemManager eventItemManager;
	// dialog instance for addon
	private Dialog dialog = null;

	/**
	 * Creates an editor instance wrapping a native CodeMirror object.
	 * 
	 * @param nativeObject a native CodeMirror object
	 * @param language language to apply to the document
	 */
	Editor(NativeEditor nativeObject, Language language) {
		this.nativeObject = nativeObject;
		// gets also the document
		this.document = new Document(nativeObject.getDoc(), language);
		// sets event managers
		this.eventManager = new EventManager(this);
		this.eventItemManager = new EventItemManager();
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		editorChangeFunctionProxy.setCallback(this::onChange);
		editorChangesFunctionProxy.setCallback(this::onChanges);
		editorBeforeChangeFunctionProxy.setCallback(this::onBeforeChange);
		editorCursorActivityFunctionProxy.setCallback(this::onCursorActivity);
		editorKeyHandledFunctionProxy.setCallback(this::onKeyHandled);
		editorBeforeSelectionChangeFunctionProxy.setCallback(this::onBeforeSelectionChange);
		editorBlurFunctionProxy.setCallback(this::onBlur);
		editorElectrictInputFunctionProxy.setCallback(this::onElectrictInput);
		editorFocusFunctionProxy.setCallback(this::onFocus);
		editorGutterClickFunctionProxy.setCallback(this::onGutterClick);
		editorGutterContextMenuFunctionProxy.setCallback(this::onGutterContextMenu);
		editorInputReadFunctionProxy.setCallback(this::onInputRead);
		editorOptionChangeFunctionProxy.setCallback(this::onOptionChange);
		editorRefreshFunctionProxy.setCallback(this::onRefresh);
		editorRenderLineFunctionProxy.setCallback(this::onRenderLine);
		editorScrollCursorIntoViewFunctionProxy.setCallback(this::onScrollCursorIntoView);
		editorScrollFunctionProxy.setCallback(this::onScroll);
		editorSwapDocFunctionProxy.setCallback(this::onSwapDoc);
		editorUpdateFunctionProxy.setCallback(this::onUpdate);
		editorViewportChangeFunctionProxy.setCallback(this::onViewportChange);
		editorMousedownFunctionProxy.setCallback(this::onMousedown);
		editorDblclickFunctionProxy.setCallback(this::onDblclick);
		editorTouchstartFunctionProxy.setCallback(this::onTouchstart);
		editorContextmenuFunctionProxy.setCallback(this::onContextmenu);
		editorKeydownFunctionProxy.setCallback(this::onKeydown);
		editorKeypressFunctionProxy.setCallback(this::onKeypress);
		editorKeyupFunctionProxy.setCallback(this::onKeyup);
		editorCutFunctionProxy.setCallback(this::onCut);
		editorCopyFunctionProxy.setCallback(this::onCopy);
		editorPasteFunctionProxy.setCallback(this::onPaste);
		editorDragstartFunctionProxy.setCallback(this::onDragstart);
		editorDragenterFunctionProxy.setCallback(this::onDragenter);
		editorDragoverFunctionProxy.setCallback(this::onDragover);
		editorDragleaveFunctionProxy.setCallback(this::onDragleave);
		editorDropFunctionProxy.setCallback(this::onDrop);

		eventItemManager.addEventItem(new EventItem<EditorChangeEventHandler, NativeEditor>(EditorChangeEvent.TYPE, nativeObject, EditorChangeEvent.NAME, eventManager, editorChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorChangesEventHandler, NativeEditor>(EditorChangesEvent.TYPE, nativeObject, EditorChangesEvent.NAME, eventManager, editorChangesFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorBeforeChangeEventHandler, NativeEditor>(EditorBeforeChangeEvent.TYPE, nativeObject, EditorBeforeChangeEvent.NAME, eventManager, editorBeforeChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorCursorActivityEventHandler, NativeEditor>(EditorCursorActivityEvent.TYPE, nativeObject, EditorCursorActivityEvent.NAME, eventManager, editorCursorActivityFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorKeyHandledEventHandler, NativeEditor>(EditorKeyHandledEvent.TYPE, nativeObject, EditorKeyHandledEvent.NAME, eventManager, editorKeyHandledFunctionProxy.getProxy()));
		eventItemManager.addEventItem(
				new EventItem<EditorBeforeSelectionChangeEventHandler, NativeEditor>(EditorBeforeSelectionChangeEvent.TYPE, nativeObject, EditorBeforeSelectionChangeEvent.NAME, eventManager, editorBeforeSelectionChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorBlurEventHandler, NativeEditor>(EditorBlurEvent.TYPE, nativeObject, EditorBlurEvent.NAME, eventManager, editorBlurFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorElectrictInputEventHandler, NativeEditor>(EditorElectrictInputEvent.TYPE, nativeObject, EditorElectrictInputEvent.NAME, eventManager, editorElectrictInputFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorFocusEventHandler, NativeEditor>(EditorFocusEvent.TYPE, nativeObject, EditorFocusEvent.NAME, eventManager, editorFocusFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorGutterClickEventHandler, NativeEditor>(EditorGutterClickEvent.TYPE, nativeObject, EditorGutterClickEvent.NAME, eventManager, editorGutterClickFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorGutterContextMenuEventHandler, NativeEditor>(EditorGutterContextMenuEvent.TYPE, nativeObject, EditorGutterContextMenuEvent.NAME, eventManager, editorGutterContextMenuFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorInputReadEventHandler, NativeEditor>(EditorInputReadEvent.TYPE, nativeObject, EditorInputReadEvent.NAME, eventManager, editorInputReadFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorOptionChangeEventHandler, NativeEditor>(EditorOptionChangeEvent.TYPE, nativeObject, EditorOptionChangeEvent.NAME, eventManager, editorOptionChangeFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorRefreshEventHandler, NativeEditor>(EditorRefreshEvent.TYPE, nativeObject, EditorRefreshEvent.NAME, eventManager, editorRefreshFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorRenderLineEventHandler, NativeEditor>(EditorRenderLineEvent.TYPE, nativeObject, EditorRenderLineEvent.NAME, eventManager, editorRenderLineFunctionProxy.getProxy()));
		eventItemManager
				.addEventItem(new EventItem<EditorScrollCursorIntoViewEventHandler, NativeEditor>(EditorScrollCursorIntoViewEvent.TYPE, nativeObject, EditorScrollCursorIntoViewEvent.NAME, eventManager, editorScrollCursorIntoViewFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorScrollEventHandler, NativeEditor>(EditorScrollEvent.TYPE, nativeObject, EditorScrollEvent.NAME, eventManager, editorScrollFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorSwapDocEventHandler, NativeEditor>(EditorSwapDocEvent.TYPE, nativeObject, EditorSwapDocEvent.NAME, eventManager, editorSwapDocFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorUpdateEventHandler, NativeEditor>(EditorUpdateEvent.TYPE, nativeObject, EditorUpdateEvent.NAME, eventManager, editorUpdateFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorViewportChangeEventHandler, NativeEditor>(EditorViewportChangeEvent.TYPE, nativeObject, EditorViewportChangeEvent.NAME, eventManager, editorViewportChangeFunctionProxy.getProxy()));

		eventItemManager.addEventItem(new EventItem<EditorMousedownEventHandler, NativeEditor>(EditorMousedownEvent.TYPE, nativeObject, EditorMousedownEvent.NAME, eventManager, editorMousedownFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDblclickEventHandler, NativeEditor>(EditorDblclickEvent.TYPE, nativeObject, EditorDblclickEvent.NAME, eventManager, editorDblclickFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorTouchstartEventHandler, NativeEditor>(EditorTouchstartEvent.TYPE, nativeObject, EditorTouchstartEvent.NAME, eventManager, editorTouchstartFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorContextmenuEventHandler, NativeEditor>(EditorContextmenuEvent.TYPE, nativeObject, EditorContextmenuEvent.NAME, eventManager, editorContextmenuFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorKeydownEventHandler, NativeEditor>(EditorKeydownEvent.TYPE, nativeObject, EditorKeydownEvent.NAME, eventManager, editorKeydownFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorKeypressEventHandler, NativeEditor>(EditorKeypressEvent.TYPE, nativeObject, EditorKeypressEvent.NAME, eventManager, editorKeypressFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorKeyupEventHandler, NativeEditor>(EditorKeyupEvent.TYPE, nativeObject, EditorKeyupEvent.NAME, eventManager, editorKeyupFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorCutEventHandler, NativeEditor>(EditorCutEvent.TYPE, nativeObject, EditorCutEvent.NAME, eventManager, editorCutFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorCopyEventHandler, NativeEditor>(EditorCopyEvent.TYPE, nativeObject, EditorCopyEvent.NAME, eventManager, editorCopyFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorPasteEventHandler, NativeEditor>(EditorPasteEvent.TYPE, nativeObject, EditorPasteEvent.NAME, eventManager, editorPasteFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDragstartEventHandler, NativeEditor>(EditorDragstartEvent.TYPE, nativeObject, EditorDragstartEvent.NAME, eventManager, editorDragstartFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDragenterEventHandler, NativeEditor>(EditorDragenterEvent.TYPE, nativeObject, EditorDragenterEvent.NAME, eventManager, editorDragenterFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDragoverEventHandler, NativeEditor>(EditorDragoverEvent.TYPE, nativeObject, EditorDragoverEvent.NAME, eventManager, editorDragoverFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDragleaveEventHandler, NativeEditor>(EditorDragleaveEvent.TYPE, nativeObject, EditorDragleaveEvent.NAME, eventManager, editorDragleaveFunctionProxy.getProxy()));
		eventItemManager.addEventItem(new EventItem<EditorDropEventHandler, NativeEditor>(EditorDropEvent.TYPE, nativeObject, EditorDropEvent.NAME, eventManager, editorDropFunctionProxy.getProxy()));

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
		Id.applyTo(nativeObject.getOptions(), id);
	}

	/**
	 * Returns the CODERBA id from editor options.
	 * 
	 * @return the CODERBA id
	 */
	public String getid() {
		return Id.retrieveFrom(nativeObject.getOptions());
	}

	/**
	 * Returns the editor area instance which this editor belongs to.
	 * 
	 * @return the editor area instance which this editor belongs to
	 */
	public EditorArea getEditorArea() {
		return nativeObject.getEditorArea();
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
	 * Used to find the target position for horizontal cursor motion.
	 * 
	 * @param start a position object
	 * @param amount an integer (may be negative)
	 * @param unit one of the string "char", "column" or "word"
	 * @param visually When visually is true, motion in right-to-left text will be visual rather than logical
	 * @return the target position for horizontal cursor motion
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
	 * Attach an additional key map to the editor.<br>
	 * This is mostly useful for addons that need to register some key handlers without trampling on the extraKeys option.<br>
	 * Maps added in this way have a higher precedence than the extraKeys and keyMap options, and between them, the maps added
	 * earlier have a lower precedence than those added later, unless the bottom argument was passed, in which case they end up
	 * below other key maps added with this method.
	 * 
	 * @param keyMap keymap instance
	 */
	public void addKeyMap(KeyMap keyMap) {
		addKeyMap(keyMap, false);
	}

	/**
	 * Attach an additional key map to the editor.<br>
	 * This is mostly useful for addons that need to register some key handlers without trampling on the extraKeys option.<br>
	 * Maps added in this way have a higher precedence than the extraKeys and keyMap options, and between them, the maps added
	 * earlier have a lower precedence than those added later, unless the bottom argument was passed, in which case they end up
	 * below other key maps added with this method.
	 * 
	 * @param keyMap keymap instance
	 * @param bottom if <code>true</code>, it ends up below other key maps added with this method.
	 */
	public void addKeyMap(KeyMap keyMap, boolean bottom) {
		// checks if keymap is consistent
		if (keyMap != null) {
			// injects keymap
			keyMap.inject();
			// sets key map to editor
			nativeObject.addKeyMap(keyMap.getName(), bottom);
		}
	}

	/**
	 * Disable a keymap added with addKeyMap.<br>
	 * Either pass in the key map object itself, or a string, which will be compared against the name property of the active key
	 * maps.
	 * 
	 * @param keyMap keymap instance
	 */
	public void removeKeyMap(KeyMap keyMap) {
		// checks if keymap is consistent
		if (keyMap != null) {
			// injects keymap
			keyMap.inject();
			// sets key map to editor
			nativeObject.removeKeyMap(keyMap.getName());
		}
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.
	 * 
	 * @param language can be a mode spec or a mode object (an object with a token method)
	 */
	public void addOverlay(Language language) {
		addOverlay(language, null);
	}

	/**
	 * Enable a highlighting overlay.<br>
	 * This is a stateless mini-mode that can be used to add extra highlighting.
	 * 
	 * @param language can be a mode spec or a mode object (an object with a token method)
	 * @param options overlay options
	 */
	public void addOverlay(Language language, OverlayOptions options) {
		// checks if mode is consistent
		if (language != null) {
			// checks if options are consistent
			if (options != null) {
				nativeObject.addOverlay(language.getName(), options.getObject());
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
			nativeObject.swapDoc(doc.getObject());
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
		return new ScrollArea(nativeObject.getScrollInfo());
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
	 * @param position a position object
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
		return new Coordinate(nativeObject.getViewport());
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
			// mode specification
			Object object = nativeObject.getModeAt(position);
			// checks the type of the mode
			if (object instanceof String) {
				// if string
				// returns a specification by string
				return new ModeSpecification((String) object);
			} else if (object instanceof NativeModeSpecification) {
				// if object
				// returns a specification by string
				return new ModeSpecification((NativeModeSpecification) object);
			}
		}
		// if here, position is not consistent
		// then returns the default mode specification
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
	public void execCommand(String name) {
		executeCommand(Key.create(name));
	}

	/**
	 * Runs the command with the given name on the editor.
	 * 
	 * @param name the command with the given name on the editor.
	 */
	public void executeCommand(Key name) {
		// checks if name of command is consistent
		if (Key.isValid(name)) {
			// then executes
			nativeObject.execCommand(name.value());
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

	/**
	 * Copy the content of the editor into the textarea.
	 */
	public void save() {
		nativeObject.save();
	}

	/**
	 * Remove the editor, and restore the original text area (with the editor's current content).<br>
	 * If you dynamically create and destroy editors made with `fromTextArea`, without destroying the form they are part of, you
	 * should make sure to call `toTextArea` to remove the editor, or its `"submit"` handler on the form will cause a memory
	 * leak.
	 */
	public void toTextArea() {
		nativeObject.toTextArea();
	}

	// ------------------------------
	// --- EVENTS
	// ------------------------------

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

	// ---------------------------------
	// --- EVENTS METHODS
	// ---------------------------------

	/**
	 * Fires batched per operation, passing an array containing all the changes that happened in the operation.<br>
	 * This event is fired after the operation finished, and display changes it makes will trigger a new operation.
	 * 
	 * @param editor native editor instance
	 * @param items array of change items
	 */
	private void onChanges(NativeEditor editor, ArrayEntity<ChangeItem> items) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// creates the list of change items
			List<ChangeItem> list = ArrayListHelper.unmodifiableList(items);
			// fires the event
			eventManager.fireEvent(new EditorChangesEvent(area, list));
		}
	}

	/**
	 * Fires every time the content of the editor is changed.
	 * 
	 * @param editor native editor instance
	 * @param item object containing information about the changes that occurred
	 */
	private void onChange(NativeEditor editor, ChangeItem item) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorChangeEvent(area, item));
		}
	}

	/**
	 * This event is fired before a change is applied, and its handler may choose to modify or cancel the change
	 * 
	 * @param editor native editor instance
	 * @param item object containing information about the changes that occurred
	 */
	private void onBeforeChange(NativeEditor editor, ChangeItem item) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorBeforeChangeEvent(area, item));
		}
	}

	/**
	 * Will be fired when the cursor or selection moves, or any change is made to the editor content.
	 * 
	 * @param editor native editor instance
	 */
	private void onCursorActivity(NativeEditor editor) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorCursorActivityEvent(area));
		}
	}

	/**
	 * Fired after a key is handled through a key map.
	 * 
	 * @param editor native editor instance
	 * @param name the name of the handled key (for example "Ctrl-X" or "'q'")
	 * @param event the DOM key down or key press event
	 */
	private void onKeyHandled(NativeEditor editor, String name, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorKeyHandledEvent(area, event, name));
		}
	}

	/**
	 * This event is fired before the selection is moved.
	 * 
	 * @param editor native editor instance
	 * @param item selection change item instance
	 */
	private void onBeforeSelectionChange(NativeEditor editor, BeforeSelectionChangeItem item) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorBeforeSelectionChangeEvent(area, item));
		}
	}

	/**
	 * Fires whenever the editor is unfocused.
	 * 
	 * @param editor native editor instance
	 * @param event DOM event instance
	 */
	private void onBlur(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorBlurEvent(area, event));
		}
	}

	/**
	 * Fired if text input matched the mode's electric patterns, and this caused the line's indentation to change.
	 * 
	 * @param editor native editor instance
	 * @param line line number
	 */
	private void onElectrictInput(NativeEditor editor, int line) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorElectrictInputEvent(area, line));
		}
	}

	/**
	 * Fires whenever the editor is focused.
	 * 
	 * @param editor native editor instance
	 * @param event DOM event instance
	 */
	private void onFocus(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorFocusEvent(area, event));
		}
	}

	/**
	 * Fires when the editor gutter (the line-number area) is clicked.
	 * 
	 * @param editor native editor instance
	 * @param line the (zero-based) number of the line that was clicked
	 * @param gutter the CSS class of the gutter that was clicked
	 * @param event DOM event instance
	 */
	private void onGutterClick(NativeEditor editor, int line, String gutter, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorGutterClickEvent(area, line, gutter, event));
		}
	}

	/**
	 * Fires when the editor gutter (the line-number area) receives a context menu event.
	 * 
	 * @param editor native editor instance
	 * @param line the (zero-based) number of the line that was clicked
	 * @param gutter the CSS class of the gutter that was clicked
	 * @param event DOM event instance
	 */
	private void onGutterContextMenu(NativeEditor editor, int line, String gutter, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorGutterContextMenuEvent(area, line, gutter, event));
		}
	}

	/**
	 * Fired whenever new input is read from the hidden textarea (typed or pasted by the user).
	 * 
	 * @param editor native editor instance
	 * @param item object containing information about the changes that occurred
	 */
	private void onInputRead(NativeEditor editor, ChangeItem item) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorInputReadEvent(area, item));
		}
	}

	/**
	 * Dispatched every time an option is changed with "setOption" method.
	 * 
	 * @param editor native editor instance
	 * @param property property name affected by change
	 */
	private void onOptionChange(NativeEditor editor, String property) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorOptionChangeEvent(area, property));
		}
	}

	/**
	 * Fires when the editor is refreshed or resized.<br>
	 * Mostly useful to invalidate cached values that depend on the editor or character size.
	 * 
	 * @param editor native editor instance
	 */
	private void onRefresh(NativeEditor editor) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorRefreshEvent(area));
		}
	}

	/**
	 * Fired whenever a line is (re-)rendered to the DOM.<br>
	 * Fired right after the DOM element is built, before it is added to the document.
	 * 
	 * @param editor native editor instance
	 * @param line line number
	 * @param element DOM element used to render the line
	 */
	private void onRenderLine(NativeEditor editor, NativeLineHandle line, Element element) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			Document doc = area.getEditor().getDocument();
			LineHandle lineHandle = doc.getLineHandleById(Id.retrieveFrom(line));
			if (lineHandle != null) {
				// fires the event
				eventManager.fireEvent(new EditorRenderLineEvent(area, lineHandle, element));
			}
		}
	}

	/**
	 * Fires when the editor tries to scroll its cursor into view.<br>
	 * Can be hooked into to take care of additional scrollable containers around the editor.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onScrollCursorIntoView(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorScrollCursorIntoViewEvent(area, event));
		}
	}

	/**
	 * Fires when the editor is scrolled.
	 * 
	 * @param editor native editor instance
	 */
	private void onScroll(NativeEditor editor) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorScrollEvent(area));
		}
	}

	/**
	 * This is signalled when the editor's document is replaced using the "swapDoc" method.
	 * 
	 * @param editor native editor instance
	 * @param oldDoc old native document instance
	 */
	private void onSwapDoc(NativeEditor editor, NativeDocument oldDoc) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		String oldDocId = Id.retrieveFrom(oldDoc);
		if (area != null && Documents.get().has(oldDocId)) {
			Document oldDocument = Documents.get().retrieve(oldDocId);
			// fires the event
			eventManager.fireEvent(new EditorSwapDocEvent(area, oldDocument));
		}
	}

	/**
	 * Will be fired whenever CodeMirror updates its DOM display.
	 * 
	 * @param editor native editor instance
	 */
	private void onUpdate(NativeEditor editor) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorUpdateEvent(area));
		}
	}

	/**
	 * Fires whenever the view port of the editor changes (due to scrolling, editing, or any other factor).
	 * 
	 * @param editor native editor instance
	 * @param from new start of viewport
	 * @param to new end of viewport
	 */
	private void onViewportChange(NativeEditor editor, int from, int to) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorViewportChangeEvent(area, from, to));
		}
	}

	/**
	 * Fires when the editor fires "mousedown" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onMousedown(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorMousedownEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "dblclick" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDblclick(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDblclickEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "touchstart" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onTouchstart(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorTouchstartEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "contextmenu" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onContextmenu(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorContextmenuEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "keydown" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onKeydown(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorKeydownEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "keypress" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onKeypress(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorKeypressEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "keyup" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onKeyup(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorKeyupEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "cut" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onCut(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorCutEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "copy" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onCopy(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorCopyEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "paste" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onPaste(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorPasteEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "dragstart" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDragstart(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDragstartEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "dragenter" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDragenter(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDragenterEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "dragover" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDragover(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDragoverEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "dragleave" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDragleave(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDragleaveEvent(area, event));
		}
	}

	/**
	 * Fires when the editor fires "drop" event.
	 * 
	 * @param editor native editor instance
	 * @param event DOME event instance
	 */
	private void onDrop(NativeEditor editor, EditorNativeEvent event) {
		// gets editor area
		EditorArea area = editor.getEditorArea();
		// checks if area is consistent
		if (area != null) {
			// fires the event
			eventManager.fireEvent(new EditorDropEvent(area, event));
		}
	}

	// -----------------------------------------
	// ADDITIONAL methods from ADDON
	// -----------------------------------------

	// -----------------------------------------
	// ADDON DIALOG
	// -----------------------------------------

	/**
	 * Returns the dialog instance or <code>null</code> if not instantiated.
	 * 
	 * @return the dialog instance or <code>null</code> if not instantiated.
	 */
	Dialog getDialog() {
		return dialog;
	}

	/**
	 * Open a dialog on top of the editor, using new {@link InputElement}, without any options.
	 * 
	 * @param dialogHandler handler instance invoked when ENTER is pressed into text field.
	 * @return a dialog object
	 */
	public Dialog openDialog(DialogHandler dialogHandler) {
		return openDialog(dialogHandler, null);
	}

	/**
	 * Open a dialog on top of the editor, using the HTML element, without any options.
	 * 
	 * @param dialogHandler handler instance invoked when ENTER is pressed into text field.
	 * @param element can be called with an HTML fragment or a detached DOM node that provides the prompt (should include an
	 *            input or button tag).
	 * @return a dialog object
	 */
	public Dialog openDialog(DialogHandler dialogHandler, Element element) {
		return openDialog(dialogHandler, element, null);
	}

	/**
	 * Open a dialog on top of the editor, using the HTML element and the dialog options passed as arguments.
	 * 
	 * @param dialogHandler handler instance invoked when ENTER is pressed into text field.
	 * @param element can be called with an HTML fragment or a detached DOM node that provides the prompt (should include an
	 *            input or button tag).
	 * @param options options to configure the dialog
	 * @return a dialog object
	 */
	public Dialog openDialog(DialogHandler dialogHandler, Element element, DialogOptions options) {
		// checks if dialog was already created
		if (dialog == null) {
			// creates new dialog
			dialog = new Dialog(this);
		}
		// open dialog
		dialog.open(dialogHandler, element, options);
		// returns dialog
		return dialog;
	}

	/**
	 * Shows an HTML fragment as a notification at the top of the editor.
	 * 
	 * @param element an HTML fragment as a notification at the top of the editor.
	 * @return a dialog object
	 */
	public Dialog openNotification(Element element) {
		return openNotification(element, new DialogOptions());
	}

	/**
	 * Shows an HTML fragment as a notification at the top of the editor. It takes a single option: duration, the amount of time
	 * after which the notification will be automatically closed. If duration is zero, the dialog will not be closed
	 * automatically.
	 * 
	 * @param element an HTML fragment as a notification at the top of the editor.
	 * @param options options to configure the notification, it takes a single option: duration, the amount of time after which
	 *            the notification will be automatically closed. If duration is zero, the dialog will not be closed
	 *            automatically.
	 * @return a dialog object
	 */
	public Dialog openNotification(Element element, DialogOptions options) {
		// checks if dialog was already created
		if (dialog == null) {
			// creates new dialog
			dialog = new Dialog(this);
		}
		// open dialog
		dialog.notify(element, options);
		// returns dialog
		return dialog;
	}

	// -----------------------------------------
	// ADDON SEARCH CURSOR
	// -----------------------------------------

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(String query) {
		return getSearchCursor(query, null, null);
	}

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(RegExp query) {
		return getSearchCursor(query, null, null);
	}

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @param start starting position for searching
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(String query, Position start) {
		return getSearchCursor(query, start, null);
	}

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @param start starting position for searching
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(RegExp query, Position start) {
		return getSearchCursor(query, start, null);
	}

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @param start starting position for searching
	 * @param options search cursor options instance
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(String query, Position start, SearchCursorOptions options) {
		// checks if query is consistent
		if (query != null) {
			// Injects add on
			AddOnSearchcursor.INSTANCE.inject();
			// invokes method
			return nativeObject.getSearchCursor(query, checkPositionForSearchCursor(start), checkOptionsForSearchCursor(options));
		}
		// if here, the query is not consistent
		return null;
	}

	/**
	 * Returns a cursor item which can be used to implement search/replace functionality.
	 * 
	 * @param query text to search
	 * @param start starting position for searching
	 * @param options search cursor options instance
	 * @return a cursor item which can be used to implement search/replace functionality or <code>null</code> if query is not
	 *         consistent
	 */
	public SearchCursor getSearchCursor(RegExp query, Position start, SearchCursorOptions options) {
		// checks if query is consistent
		if (query != null) {
			// Injects add on
			AddOnSearchcursor.INSTANCE.inject();
			// invokes method
			return nativeObject.getSearchCursor(query, checkPositionForSearchCursor(start), checkOptionsForSearchCursor(options));
		}
		// if here, the query is not consistent
		return null;
	}

	/**
	 * Checks the position passed as argument if consistent and if no, returns a default position. 
	 * 
	 * @param start starting position for searching
	 * @return the real starting position for searching
	 */
	private Position checkPositionForSearchCursor(Position start) {
		// if start position is not consistent
		if (start == null) {
			// if yes, returns a default
			return Position.create();
		} else {
			// returns the argument
			return start;
		}
	}

	/**
	 * Checks the search options, passed as argument, if consistent and if no, returns an empty. 
	 * 
	 * @param options the search options, passed as argument
	 * @return the real options for searching
	 */
	private NativeObject checkOptionsForSearchCursor(SearchCursorOptions options) {
		// checks if options is consistent
		if (options != null) {
			// if yes, returns the native object
			return options.getObject();
		}
		// returns default item
		return SearchCursorOptions.DEFAULT_INSTANCE.getObject();
	}

	// -----------------------------------------

	/**
	 * Returns the native editor object.
	 * 
	 * @return the native editor object
	 */
	NativeEditor getNativeObject() {
		return nativeObject;
	}

}
