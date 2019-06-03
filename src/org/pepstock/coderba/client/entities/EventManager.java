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

import java.util.ArrayList;
import java.util.List;

import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.UmbrellaException;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class EventManager {

	private final HandlerManager eventHandlerManager;
	
	private final List<HandlerRegistration> handlerRegistrations = new ArrayList<>();
	
	/**
	 * 
	 */
	protected EventManager() {
		eventHandlerManager = new HandlerManager(this, false);
	}

	protected final void removeAllHandlers() {
		for (HandlerRegistration handler : handlerRegistrations) {
			handler.removeHandler();
		}
	}
	
	/**
	 * Gets the number of handlers listening to the event type.
	 * 
	 * @param type the event type
	 * @return the number of registered handlers
	 */
	protected final int getHandlerCount(GwtEvent.Type<?> type) {
		return eventHandlerManager.getHandlerCount(type);
	}
	
	 /**
	   * Fires the given event to the handlers listening to the event's type.
	   * <p>
	   * Any exceptions thrown by handlers will be bundled into a
	   * {@link UmbrellaException} and then re-thrown after all handlers have
	   * completed. An exception thrown by a handler will not prevent other handlers
	   * from executing.
	   * <p>
	   * Note, any subclass should be very careful about overriding this method, as
	   * adds/removes of handlers will not be safe except within this
	   * implementation.
	   * 
	   * @param event the event
	   */
	  protected final void fireEvent(GwtEvent<?> event) {
		  if (event != null) {
			  eventHandlerManager.fireEvent(event);
		  }
	  }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.event.shared.HandlerManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	public final <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		// FIXME checks arguments
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
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.shared.HandlerManager#removeHandler(com.google.gwt.event.shared.GwtEvent.Type, com.google.gwt.event.shared.EventHandler)
	 */
	private final <H extends EventHandler> void removeHandler(Type<H> type, H handler) {
		// removes handler
		eventHandlerManager.removeHandler(type, handler);
		// sends the event
		eventHandlerManager.fireEvent(new RemoveHandlerEvent(type));
	}
	
	/**
	 * FIXME
	 * @author Andrea "Stock" Stocchero
	 *
	 * @param <H>
	 */
	private static class InternalHandlerRegistration<H extends EventHandler> implements HandlerRegistration{
		
		private final EventManager manager;
		
		private final Type<H> type;
		
		private final H handler;
		
		private HandlerRegistration delegate;

		/**
		 * @param manager
		 */
		private InternalHandlerRegistration(EventManager manager, HandlerRegistration registration, Type<H> type, H handler) {
			this.manager = manager;
			this.type = type;
			this.handler = handler;
			this.delegate = registration;
		}

		/* (non-Javadoc)
		 * @see com.google.web.bindery.event.shared.HandlerRegistration#removeHandler()
		 */
		@Override
		public void removeHandler() {
			delegate.removeHandler();
			manager.removeHandler(type, handler);
		}
		
	}
	
}
