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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.EventManager;
import org.pepstock.coderba.client.events.IsEventManager;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineHandle implements IsEventManager {

	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	
	private final NativeLineHandle nativeObject;

	private final EventManager eventManager;

	/**
	 * 
	 * @param nativeObject
	 */
	LineHandle(NativeLineHandle nativeObject) {
		this.nativeObject = nativeObject;
		// stores the id based on a counter
		this.nativeObject.setId(COUNTER.getAndIncrement());
		// sets event manager
		this.eventManager = new EventManager(this);
	}
	
	public int getId() {
		return nativeObject.getId();
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public String getText() {
		return nativeObject.getText();
	}

	/**
	 * Get the content of the document.
	 * 
	 * @return he content of the document
	 */
	public double getHeight() {
		return nativeObject.getHeight();
	}

	/**
	 * The reverse of posFromIndex.
	 * 
	 * @param position position into document.
	 * @return value is relative to the start of the editor's text
	 */
	public int lineTo() {
		return nativeObject.lineTo();
	}

	/**
	 * Returns the native object instance.
	 * 
	 * @return the native object instance.
	 */
	final NativeLineHandle getObject() {
		return nativeObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.IsEventManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		return eventManager.addHandler(type, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.RemoveHandlerEventHandler#onRemove(org.pepstock.coderba.client.events.
	 * RemoveHandlerEvent)
	 */
	@Override
	public void onRemove(RemoveHandlerEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
	}

}
