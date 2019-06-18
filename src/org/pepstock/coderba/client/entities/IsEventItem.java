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

import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

/**
 * Defines an event item, with methods to be invpoke to set on or off the event listening on editor components.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface IsEventItem {

	/**
	 * Checks if this event item is related to add handler event, in order to set ON event listening.
	 * 
	 * @param event add event handler instance
	 */
	void checkAndOn(AddHandlerEvent event);

	/**
	 * Checks if this event item is related to remove handler event, in order to set OFF event listening.
	 * 
	 * @param event remove event handler instance
	 */
	void checkAndOff(RemoveHandlerEvent event);

}
