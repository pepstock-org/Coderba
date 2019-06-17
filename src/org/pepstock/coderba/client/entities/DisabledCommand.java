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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.enums.CommandType;

/**
 * Used for predefined command which are disabled, which means that into key map table are defined by a boolean,
 * <code>false</code>.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DisabledCommand extends Command {

	/**
	 * Creates the command
	 */
	DisabledCommand() {
		super(CommandType.DISABLED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.Command#execute(org.pepstock.coderba.client.EditorArea)
	 */
	@Override
	public void execute(EditorArea editorArea) {
		// do nothing because is disabled
	}

}
