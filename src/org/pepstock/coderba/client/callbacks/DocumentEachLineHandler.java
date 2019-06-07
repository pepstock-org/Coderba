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
package org.pepstock.coderba.client.callbacks;

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.entities.Document;
import org.pepstock.coderba.client.entities.LineHandle;

/**
 * To implement to iterate over the whole document and called for each line, passing the line handle.<br>
 * This is a faster way to visit a range of line handlers than calling {@link Document#getLineHandle(int)} for each of them.<br>
 * Note that line handles have a text property containing the line's content (as a string).
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface DocumentEachLineHandler {

	/**
	 * To iterate over the whole document and called for each line, passing the line handle.
	 * 
	 * @param editorArea editor area instance
	 * @param handle line handle instance
	 */
	void handle(EditorArea editorArea, LineHandle handle);

}
