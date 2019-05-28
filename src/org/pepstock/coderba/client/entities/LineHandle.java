/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License";
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

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineHandle {
	
	private final NativeLineHandle nativeObject;

	/**
	 * 
	 */
	LineHandle(NativeLineHandle nativeObject) {
		this.nativeObject = nativeObject;
		if (nativeObject == null) {
			throw new IllegalArgumentException("Native line handle is null!");
		}
	}
	
	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public String getText() {
		return nativeObject.getText();
	}
	
	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public double getHeight() {
		return nativeObject.getHeight();
	}

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text
	 */
	public int lineTo() {
		return nativeObject.lineTo();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeLineHandle getObject() {
		return nativeObject;
	}

}
