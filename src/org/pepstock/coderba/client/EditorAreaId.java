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
package org.pepstock.coderba.client;

import com.google.gwt.dom.client.Document;

/**
 * This class is not implementable because created by EditoArea instance and passed to create an editor.<br>
 * This is done in order to avoid that anyone can create an Editor with having an area.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EditorAreaId {

	private final String id = Document.get().createUniqueId();;

	/**
	 * Creates the object. It is implemented to reduce the visibility.
	 */
	EditorAreaId() {
		// do nothing
	}

	/**
	 * Returns the editor area id.
	 * 
	 * @return the editor area id.
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EditorAreaId [id=" + id + "]";
	}

}
