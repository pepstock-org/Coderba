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
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Documents {
	
	private static final Documents INSTANCE = new Documents();
	
	private final Map<Integer, Document> documents = new HashMap<>(); 

	/**
	 * To avoid any instantiation
	 */
	private Documents() {
		// do nothing
	}
	
	public static Documents get() {
		return INSTANCE;
	}

	void add(Document document) {
		if (!documents.containsKey(document.getId())) {
			documents.put(document.getId(), document);
		}
	}

	public boolean has(int id) {
		return documents.containsKey(id);
	}

	public Document retrieve(int id) {
		return documents.get(id);
	}

}
