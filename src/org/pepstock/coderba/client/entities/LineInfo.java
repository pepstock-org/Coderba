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
 * 
 * {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}
 * 
 * FIXME doc
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineInfo {

	private final LineHandle handle;

	private final NativeLineInfo nativeObject;

	private final Document document;

	/**
	 * 
	 */
	LineInfo(NativeLineInfo nativeObject, Document document) {
		this.nativeObject = nativeObject;
		this.document = document;
		NativeLineHandle handle = nativeObject.getHandle();
		if (handle != null) {
			this.handle = this.document.getLineHandleById(Id.get(handle));
		} else {
			this.handle = null;
		}
	}

	public int getLine() {
		return nativeObject.getLine();
	}

	public LineHandle getLineHandle() {
		return handle;
	}

	public String getText() {
		return nativeObject.getText();
	}

	public String getTextClass() {
		return nativeObject.getTextClass();
	}

	public String getBgClass() {
		return nativeObject.getBgClass();
	}

	public String getWrapClass() {
		return nativeObject.getWrapClass();
	}

	public List<String> getGutterMarkers() {
		ArrayString array = nativeObject.getGutterMarkers();
		return ArrayListHelper.unmodifiableList(array);
	}

	public List<LineWidget> getWidgets() {
		List<LineWidget> widgets = new LinkedList<>();
		ArrayObject array = nativeObject.getWidgets();
		if (array != null) {
			for (int i = 0; i < array.length(); i++) {
				String id = Id.get(array.get(i));
				LineWidget widget = document.getLineWidget(id);
				if (widget != null) {
					widgets.add(widget);
				}
			}
		}
		return Collections.unmodifiableList(widgets);
	}
}
