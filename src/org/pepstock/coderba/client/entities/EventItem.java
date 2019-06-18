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

import org.pepstock.coderba.client.commons.CallbackProxy.Proxy;
import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Contains the information for each event (and handler) managed by editor component.
 * It is invoked every time that new handler has been added or removed.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventItem<H extends EventHandler, N extends NativeEventEmitter> implements IsEventItem {

	private final GwtEvent.Type<H> handler;

	private final N nativeEventEmitter;

	private final String name;

	private final EventManager manager;

	private final Proxy proxy;

	/**
	 * Creates the time with all data necessary to manage add and remove handler.
	 * 
	 * @param handlerType event handler type to manage
	 * @param nativeEventEmitter native object with on and off methods to register events
	 * @param name  event name to manage
	 * @param manager event manager instance of editor component 
	 * @param proxy the callback proxy to set on or off.
	 */
	EventItem(Type<H> handlerType, N nativeEventEmitter, String name, EventManager manager, Proxy proxy) {
		this.handler = handlerType;
		this.nativeEventEmitter = nativeEventEmitter;
		this.name = name;
		this.manager = manager;
		this.proxy = proxy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.IsEventItem#checkAndOn(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void checkAndOn(AddHandlerEvent event) {
		// checks if type of added event handler is managed
		// if there is not any other handler
		if (event.isRecognize(handler) && manager.getHandlerCount(handler) == 1) {
			// sets the callback proxy in order to call the user event interface
			nativeEventEmitter.on(name, proxy);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.IsEventItem#checkAndOff(org.pepstock.coderba.client.events.RemoveHandlerEvent)
	 */
	@Override
	public void checkAndOff(RemoveHandlerEvent event) {
		// checks if type of removed event handler is managed
		// if there is not any other handler
		if (event.isRecognize(handler) && manager.getHandlerCount(handler) == 0) {
			// sets OFF the callback proxy in order to call the user event interface
			nativeEventEmitter.off(name, proxy);
		}
	}

}
