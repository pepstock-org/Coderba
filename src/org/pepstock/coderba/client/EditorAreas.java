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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Cache which collects all editor area instances to be able to enable callbacks.<br>
 * Manages also the user life cycle listeners on editor area. 
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EditorAreas {

	// buffer with all editor area instances
	// K = EDITOR AREA id (CODERBA ID)
	// V = editor area instance
	private static final Map<String, EditorArea> EDITOR_AREA_INSTANCES = new HashMap<>();
	// list with all editor areas life cycle listeners
	private static final List<EditorAreasLifecycleListener> LISTENERS = new LinkedList<>();

	/**
	 * To avoid any instantiation
	 */
	private EditorAreas() {
	}

	/**
	 * Adds new editor areas life cycle listener instance into collection.
	 * 
	 * @param listener editor areas life cycle listener instance
	 */
	public static void addLifecycleListener(EditorAreasLifecycleListener listener) {
		// checks if listener is consistent
		if (listener != null) {
			LISTENERS.add(listener);
		} else {
			// if here, listener is not consistent
			// then exception
			throw new IllegalArgumentException("Listener is null");
		}
	}

	/**
	 * Removes a editor areas life cycle listener instance from collection.
	 * 
	 * @param listener editor areas life cycle listener instance
	 */
	public static void removeLifecycleListener(EditorAreasLifecycleListener listener) {
		// checks if listener is consistent
		// no exception because it is removing it
		if (listener != null) {
			LISTENERS.remove(listener);
		}
	}

	/**
	 * Adds new editor area instance into collection.
	 * 
	 * @param editorArea editor area instance
	 */
	static void add(EditorArea editorArea) {
		// putting getting the editor area
		EditorArea prevArea = EDITOR_AREA_INSTANCES.put(editorArea.getId(), editorArea);
		// if previous editor area instance is not consistent
		// means that editor area is new and then...
		if (prevArea == null) {
			// ...scans all listener to send notification
			for (EditorAreasLifecycleListener listener : LISTENERS) {
				listener.onBeforeInit(editorArea);
			}
		}
	}

	/**
	 * Fires the notification to all listeners after editor area initialization.
	 * 
	 * @param editorArea editor area instance
	 */
	static void fireAfterInit(EditorArea editorArea) {
		// ...scans all listener to send notification
		for (EditorAreasLifecycleListener listener : LISTENERS) {
			listener.onAfterInit(editorArea);
		}
	}

	/**
	 * Fires the notification to all listeners before editor area destroy.
	 * 
	 * @param editorArea editor area instance
	 */
	static void fireBeforeDestory(EditorArea editorArea) {
		// ...scans all listener to send notification
		for (EditorAreasLifecycleListener listener : LISTENERS) {
			listener.onBeforeDestroy(editorArea);
		}
	}

	/**
	 * Returns the editor area instance by its id.
	 * 
	 * @param editorAreaId editor area id
	 * @return editor area instance or <code>null</code> if not exist.
	 */
	public static EditorArea get(String editorAreaId) {
		// checks if argument is consistent
		if (editorAreaId != null) {
			return EDITOR_AREA_INSTANCES.get(editorAreaId);
		}
		// if here editor area id is not consistent
		return null;
	}

	/**
	 * Removes an editor area instance by its id.
	 * 
	 * @param editorAreaId editor area id
	 */
	static void remove(String editorAreaId) {
		// removes getting the area
		EditorArea area = EDITOR_AREA_INSTANCES.remove(editorAreaId);
		// if area instance is consistent
		if (area != null) {
			// scans all listener to send notification
			for (EditorAreasLifecycleListener listener : LISTENERS) {
				listener.onAfterDestroy(area);
			}
		}
	}

}
