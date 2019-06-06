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
package org.pepstock.coderba.client.callbacks;

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.entities.Position;
import org.pepstock.coderba.client.entities.Range;

/**
 * Allow you to configure the behavior of mouse configuration unit.<br>
 * The function takes a position and returns a range around that, for a custom unit.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface MouseUnitHandler {

	/**
	 * Allow you to configure the behavior of mouse configuration unit.<br>
	 * The function takes a position and returns a range around that, for a custom unit.
	 * 
	 * @param editorArea editor area instance
	 * @param position position of mouse
	 * @return a range around that, for a custom unit
	 */
	Range handle(EditorArea editorArea, Position position);

}
