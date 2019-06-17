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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayObject;
import org.pepstock.coderba.client.commons.ArrayString;
import org.pepstock.coderba.client.commons.Id;

/**
 * Entity which maps a line into a document, providing a set of information about it. {line, handle, text, gutterMarkers,
 * textClass, bgClass, wrapClass, widgets}
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineInfo {

	// native line info object (generated by CodeMirror)
	private final NativeLineInfo nativeObject;
	// line handle instance
	private final LineHandle lineHandle;
	// the current document related to this line info
	private final Document document;

	/**
	 * Creates a line info instance wrapping a native code mirror object and the document which this line info belongs to.
	 * 
	 * @param nativeObject a native code mirror object
	 * @param document document which this line info belongs to
	 */
	LineInfo(NativeLineInfo nativeObject, Document document) {
		this.nativeObject = nativeObject;
		this.document = document;
		// gets line handle from native object
		NativeLineHandle handle = nativeObject.getHandle();
		// if line handle is consistent consistent
		if (handle != null) {
			// gets the line handle by document cache
			this.lineHandle = this.document.getLineHandleById(Id.retrieveFrom(handle));
		} else {
			// otherwise it sets to null
			// FIXME if correct
			this.lineHandle = null;
		}
	}

	/**
	 * Returns the line number.
	 * 
	 * @return the line number
	 */
	public int getLineNumber() {
		return nativeObject.getLine();
	}

	/**
	 * Returns the line handle related to the line or <code>null</code> if not created previously.
	 * 
	 * @return the line handle related to the line or <code>null</code> if not created previously
	 */
	public LineHandle getLineHandle() {
		return lineHandle;
	}

	/**
	 * Returns the content of the line.
	 * 
	 * @return the content of the line
	 */
	public String getText() {
		return nativeObject.getText();
	}

	/**
	 * Returns the CSS style class associated to the line.
	 * 
	 * @return the CSS style class associated to the line
	 */
	public String getTextClass() {
		return nativeObject.getTextClass();
	}

	/**
	 * Returns the background class associated to the line.
	 * 
	 * @return the background class associated to the line
	 */
	public String getBackgroundClass() {
		return nativeObject.getBgClass();
	}

	/**
	 * Returns the CSS style class associated to the line, added to wrap element.
	 * 
	 * @return the CSS style class associated to the line, added to wrap element
	 */
	public String getWrapClass() {
		return nativeObject.getWrapClass();
	}

	/**
	 * Returns an unmodifiable list of CSS style classes associated to the line, added to gutter element.
	 * 
	 * @return an unmodifiable list of CSS style classes associated to the line, added to gutter element
	 */
	public List<String> getGutterMarkers() {
		ArrayString array = nativeObject.getGutterMarkers();
		return ArrayListHelper.unmodifiableList(array);
	}

	/**
	 * Returns an unmodifiable list of line widget associated to this line.
	 * 
	 * @return an unmodifiable list of line widget associated to this line
	 */
	public List<LineWidget> getWidgets() {
		// FIXME makes this into constructor
		// creates the list to return
		List<LineWidget> widgets = new LinkedList<>();
		// gets the array of line widgets by Code Mirror
		ArrayObject array = nativeObject.getWidgets();
		// checks if array is consistent
		if (array != null && !array.isEmpty()) {
			// scans the array
			for (int i = 0; i < array.length(); i++) {
				// gets the id of line widget
				String id = Id.retrieveFrom(array.get(i));
				// gets the line widget object by the document cache
				LineWidget widget = document.getLineWidget(id);
				// if widget is consistent
				if (widget != null) {
					// adds to the list
					widgets.add(widget);
				}
			}
		}
		return Collections.unmodifiableList(widgets);
	}
}
