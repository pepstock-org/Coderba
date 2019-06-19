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
import org.pepstock.coderba.client.entities.MouseConfiguration;
import org.pepstock.coderba.client.enums.MouseRepeat;
import org.pepstock.coderba.client.events.EditorNativeEvent;

/**
 * Allow you to configure the behavior of mouse selection and dragging.<br>
 * The function is called when the left mouse button is pressed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface ConfigureMouseHandler {

	/**
	 * Allow you to configure the behavior of mouse selection and dragging.<br>
	 * The function is called when the left mouse button is pressed.
	 * 
	 * @param editorArea editor area instance
	 * @param repeat type of click by mouse
	 * @param event native event
	 * @return mouse configuration instance
	 */
	MouseConfiguration handle(EditorArea editorArea, MouseRepeat repeat, EditorNativeEvent event);

}
