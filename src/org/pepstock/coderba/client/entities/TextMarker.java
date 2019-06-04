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

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.UndefinedValues;
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
public final class TextMarker extends TextMarkerOptions implements IsEventManager {

	private final NativeTextMarker nativeObject;

	private final Document document;

	private final EventManager eventManager;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id"),
		EXPLICITLY_CLEARED("explicitlyCleared");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	/**
	 * 
	 */
	TextMarker(NativeTextMarker nativeObject, Document document) {
		super(nativeObject);
		this.nativeObject = nativeObject;
		this.document = document;
		// sets event manager
		this.eventManager = new EventManager(this);
	}

	public int getId() {
		return getValue(Property.ID, UndefinedValues.INTEGER);
	}

	public boolean isCleared() {
		return getValue(Property.EXPLICITLY_CLEARED, false);
	}

	/**
	 * Remove the mark.
	 */
	public void clear() {
		document.clearTextMarker(getId());
		nativeObject.clear();
	}

	/**
	 * Returns a {from, to} object (both holding document positions), indicating the current position of the marked range, or
	 * undefined if the marker is no longer in the document.
	 * 
	 * @return
	 */
	public Range find() {
		return nativeObject.find();
	};

	/**
	 * Called when you've done something that might change the size of the marker and want to cheaply update the display.
	 */
	public void changed() {
		// bookmarks do not have this method
		if (isRange()) {
			nativeObject.changed();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.events.AddHandlerEventHandler#onAdd(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void onAdd(AddHandlerEvent event) {
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
	 * @see org.pepstock.coderba.client.events.IsEventManager#addHandler(com.google.gwt.event.shared.GwtEvent.Type,
	 * com.google.gwt.event.shared.EventHandler)
	 */
	@Override
	public <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler) {
		return eventManager.addHandler(type, handler);
	}

}
