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

import org.pepstock.coderba.client.commons.ArrayListHelper;
import org.pepstock.coderba.client.commons.ArrayString;

/**
 * 
 * {line, handle, text, gutterMarkers, textClass, bgClass, wrapClass, widgets}
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineInfo {
	
	private final LineHandle handle;
	
	private final NativeLineInfo nativeObject;

	/**
	 * 
	 */
	LineInfo(NativeLineInfo nativeObject) {
		this.nativeObject = nativeObject;
		this.handle = new LineHandle(nativeObject.getHandle());
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

	//FIXM widgets is missing

}
