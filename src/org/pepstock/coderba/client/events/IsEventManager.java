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
package org.pepstock.coderba.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Interface to implement for objects which must fire events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface IsEventManager extends AddHandlerEventHandler, RemoveHandlerEventHandler {

	/**
	 * Adds a handler.
	 * 
	 * @param <H> The type of handler
	 * @param type the event type associated with this handler
	 * @param handler the handler
	 * @return the handler registration, can be stored in order to remove the handler later
	 */
	<H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler);

}
