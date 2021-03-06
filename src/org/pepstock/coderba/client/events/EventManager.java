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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.UmbrellaException;

/**
 * Common event manager, responsible for adding handlers to event sources and firing those handlers on passed in events.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EventManager {
	// GWT manager
	private final HandlerManager eventHandlerManager;
	// event manager instance
	private final IsEventManager eventManagerImpl;
	// list of created handler registration
	private final List<HandlerRegistration> handlerRegistrations = new ArrayList<>();

	/**
	 * Creates an event manager by an object which must fire events.
	 * 
	 * @param eventManager object which must fire events
	 */
	public EventManager(IsEventManager eventManager) {
		// checks if event manager is consistent
		if (eventManager == null) {
			// if no, exception
			throw new IllegalArgumentException("Event manager is null");
		}
		// streos event manager
		this.eventManagerImpl = eventManager;
		// creates GWT manager
		eventHandlerManager = new HandlerManager(this.eventManagerImpl, false);
		// adds to event manager as handler to add and remove handlers
		eventHandlerManager.addHandler(AddHandlerEvent.TYPE, eventManager);
		eventHandlerManager.addHandler(RemoveHandlerEvent.TYPE, eventManager);
	}

	/**
	 * Removes all handlers
	 */
	public void removeAllHandlers() {
		// scans all handle registration
		for (HandlerRegistration handler : handlerRegistrations) {
			// removes handler
			handler.removeHandler();
		}
	}

	/**
	 * Gets the number of handlers listening to the event type.
	 * 
	 * @param type the event type
	 * @return the number of registered handlers
	 */
	public int getHandlerCount(GwtEvent.Type<?> type) {
		return eventHandlerManager.getHandlerCount(type);
	}

	/**
	 * Fires the given event to the handlers listening to the event's type.
	 * <p>
	 * Any exceptions thrown by handlers will be bundled into a {@link UmbrellaException} and then re-thrown after all handlers
	 * have completed. An exception thrown by a handler will not prevent other handlers from executing.
	 * <p>
	 * Note, any subclass should be very careful about overriding this method, as adds/removes of handlers will not be safe
	 * except within this implementation.
	 * 
	 * @param event the event
	 */
	public void fireEvent(GwtEvent<?> event) {
		// if event is consistent
		if (event != null) {
			// fires event
			eventHandlerManager.fireEvent(event);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.HandlerManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		// checks if arguments are consistent
		if (type == null || handler == null) {
			// if not, exception
			throw new IllegalArgumentException("Type or handler instance is null");
		}
		// adds handler
		HandlerRegistration registration = new InternalHandlerRegistration<H>(this, eventHandlerManager.addHandler(type, handler), type, handler);
		// if the handler is a chart event handler one
		// sends the event
		eventHandlerManager.fireEvent(new AddHandlerEvent(type));
		// stores the registration
		handlerRegistrations.add(registration);
		// returns registration
		return registration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.HandlerManager#removeHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	<H extends EventHandler> void removeHandler(Type<H> type, H handler) {
		// removes handler
		eventHandlerManager.removeHandler(type, handler);
		// sends the event
		eventHandlerManager.fireEvent(new RemoveHandlerEvent(type));
	}

	/**
	 * Custom implementation of handle registration in order to clean the list with all handle registration.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 * @param <H> event handler type
	 */
	private static class InternalHandlerRegistration<H extends EventHandler> implements HandlerRegistration {
		// event manager
		private final EventManager manager;
		// handler type
		private final Type<H> type;
		// handle instance
		private final H handler;
		// hadnel registration to wrap
		private HandlerRegistration delegate;

		/**
		 * Creates a custom handle registration.
		 * 
		 * @param manager event manager
		 * @param registration handler registration instance
		 * @param type type of handler
		 * @param handler handler instance
		 */
		private InternalHandlerRegistration(EventManager manager, HandlerRegistration registration, Type<H> type, H handler) {
			this.manager = manager;
			this.type = type;
			this.handler = handler;
			this.delegate = registration;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.google.web.bindery.event.shared.HandlerRegistration#removeHandler()
		 */
		@Override
		public void removeHandler() {
			// invokes remove handler of handler registration
			delegate.removeHandler();
			// remove the handler
			manager.removeHandler(type, handler);
		}

	}

}
