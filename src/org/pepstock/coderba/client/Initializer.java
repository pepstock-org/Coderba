/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
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

/**
 * Interface to be implemented to configure the editor before or after its initialization.<br>
 * This is mandatory because the editor can be instantiated ONLY after the widget will be attached to DOM document and therefore
 * there is a point of time when the editor is not consistent.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface Initializer {

	/**
	 * Invoked before the editor will be created.<br>
	 * Here the editor instance will be always null.
	 * 
	 * @param editorArea editor area widget instance
	 */
	default void beforeInit(EditorArea editorArea) {
		// do nothing
	}

	/**
	 * Invoked after the editor will be created.
	 * 
	 * @param editorArea editor area widget instance
	 */
	default void afterInit(EditorArea editorArea) {
		// do nothing
	}

}
