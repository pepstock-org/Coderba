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

/**
 * Commands are parameter-less actions that can be performed on an editor.<br>
 * Their main use is for key bindings.<br>
 * This interface must be implemented to manage own commands.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface CommandHandler {

	/**
	 * Invoked by editor area instance where to apply the command.
	 * 
	 * @param editorArea editor area instance where to apply the command
	 */
	void execute(EditorArea editorArea);
	
}
