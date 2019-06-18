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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.coderba.client.events.AddHandlerEvent;
import org.pepstock.coderba.client.events.RemoveHandlerEvent;

/**
 * Manages event item of editor component, storing every item for each event to mange into a list.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class EventItemManager implements IsEventItem {

	// contains all event items
	private final List<IsEventItem> items = new LinkedList<>();

	/**
	 * Adds new event item.
	 * 
	 * @param item event item instance
	 */
	void addEventItem(IsEventItem item) {
		// checks if argument is consistent
		if (item != null) {
			// adds to list
			items.add(item);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.IsEventItem#checkAndOn(org.pepstock.coderba.client.events.AddHandlerEvent)
	 */
	@Override
	public void checkAndOn(AddHandlerEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// scans all defined items
			for (IsEventItem item : items) {
				// checks and set ON event listening
				item.checkAndOn(event);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.IsEventItem#checkAndOff(org.pepstock.coderba.client.events.RemoveHandlerEvent)
	 */
	@Override
	public void checkAndOff(RemoveHandlerEvent event) {
		// checks if argument is consistent
		if (event != null) {
			// scans all defined items
			for (IsEventItem item : items) {
				// checks and set OFF event listening
				item.checkAndOff(event);
			}
		}
	}

}
