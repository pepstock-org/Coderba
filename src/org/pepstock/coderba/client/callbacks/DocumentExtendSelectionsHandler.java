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
import org.pepstock.coderba.client.entities.Anchor;
import org.pepstock.coderba.client.entities.Position;

/**
 * Applies the given implementation of this interface to all existing selections.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface DocumentExtendSelectionsHandler {

	/**
	 * Called to extend the selections on the document.
	 * 
	 * @param editorArea editor area instance
	 * @param anchor anchor of extended selections
	 * @return position instance of the selection
	 */
	Position handle(EditorArea editorArea, Anchor anchor);

}
