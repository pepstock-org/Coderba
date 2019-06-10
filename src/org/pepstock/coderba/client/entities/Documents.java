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

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton which contains all created documents using the document unique id as key.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class Documents {

	// singleton instance
	private static final Documents INSTANCE = new Documents();
	// maps which contains all documents
	// K = document id
	// V = document instance
	private final Map<String, Document> documents = new HashMap<>();

	/**
	 * To avoid any instantiation
	 */
	private Documents() {
		// do nothing
	}

	/**
	 * Singleton method to get the instance.
	 * 
	 * @return the singleton instance.
	 */
	public static Documents get() {
		return INSTANCE;
	}

	/**
	 * Adds a document into cache.
	 * @param document document instance to be added into cache
	 */
	void add(Document document) {
		if (!documents.containsKey(document.getId())) {
			documents.put(document.getId(), document);
		}
	}

	/**
	 * Returns <code>true</code> if the document id is present into cache.
	 * @param id document id.
	 * @return <code>true</code> if the document id is present into cache 
	 */
	boolean has(String id) {
		// checks if id is consistent
		if (id != null) {
			// returns if id is consistent
			return documents.containsKey(id);
		}
		// if here, id is not consistent
		// then returns always false
		return false;
	}

	/**
	 * Returns the document instance by its id from cache.
	 * @param id document id
	 * @return the document instance from cache 
	 */
	Document retrieve(String id) {
		// checks if id is consistent
		if (id != null) {
			// returns the stored document 
			return documents.get(id);
		}
		// if here, id is not consistent
		// then returns always null
		return null;
	}

}
