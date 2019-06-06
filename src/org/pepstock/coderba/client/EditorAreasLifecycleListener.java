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
 * Interface to catch the initialization and destroy of all editor areas.<br>
 * Used to configure or clean up resources.
 * 
 * @author Andrea "Stock" Stocchero
 * @see EditorAreas
 */
public interface EditorAreasLifecycleListener {

	/**
	 * Called before initializing 'editor'.
	 * 
	 * @param editorArea the editor area instance.
	 */
	default void onBeforeInit(EditorArea editorArea) {
		// do nothing
	}

	/**
	 * Called after 'editor' has been initialized and before the first update.
	 * 
	 * @param editorArea the editor area instance.
	 */
	default void onAfterInit(EditorArea editorArea) {
		// do nothing
	}

	/**
	 * Called before the editor has been destroyed.
	 * 
	 * @param editorArea the editor area instance.
	 */
	default void onBeforeDestroy(EditorArea editorArea) {
		// do nothing
	}

	/**
	 * Called after the editor area has been destroyed.
	 * 
	 * @param editorArea the editor area instance.
	 */
	default void onAfterDestroy(EditorArea editorArea) {
		// do nothing
	}

}
