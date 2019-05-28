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

import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Each editor is associated with an instance of CodeMirror.Doc, its document. A document represents the editor content, plus a
 * selection, an undo history, and a mode. A document can only be associated with a single editor at a time.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class NativeTextMarker extends NativeObject {

	// /** Returns an object representing the options for the marker. If copyWidget is given true, it will clone the value of
	// the replacedWith option, if any. */
	// getOptions(copyWidget: boolean): CodeMirror.TextMarkerOptions;
	//
	// /** Fired when the cursor enters the marked range */
	// on(eventName: 'beforeCursorEnter', handler: () => void) : void;
	// off(eventName: 'beforeCursorEnter', handler: () => void) : void;
	//
	// /** Fired when the range is cleared, either through cursor movement in combination with clearOnEnter or through a call to
	// its clear() method */
	// on(eventName: 'clear', handler: (from: Position, to: Position) => void) : void;
	// off(eventName: 'clear', handler: () => void) : void;
	//
	// /** Fired when the last part of the marker is removed from the document by editing operations */
	// on(eventName: 'hide', handler: () => void) : void;
	// off(eventname: 'hide', handler: () => void) : void;
	//
	// /** Fired when, after the marker was removed by editing, a undo operation brough the marker back */
	// on(eventName: 'unhide', handler: () => void) : void;
	// off(eventname: 'unhide', handler: () => void) : void;

	/**
	 * To avoid any instantiation
	 */
	private NativeTextMarker() {
		// do nothing
	}

	/**
	 * Returns the id of marker.<br>
	 * It has been implemented because it must be checked before creating a TextMarker.
	 * 
	 * @return the id of marker
	 */
	@JsProperty
	native int getId();

	/**
	 * Remove the mark.
	 */
	native void clear();

	/**
	 * Returns a {from, to} object (both holding document positions), indicating the current position of the marked range, or
	 * undefined if the marker is no longer in the document.
	 * 
	 * @return
	 */
	native NativeObject find(); // {from: CodeMirror.Position, to: CodeMirror.Position};

	/**
	 * Called when you've done something that might change the size of the marker and want to cheaply update the display.
	 */
	native void changed();
}