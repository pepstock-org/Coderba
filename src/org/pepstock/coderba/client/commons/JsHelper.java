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
package org.pepstock.coderba.client.commons;

import org.pepstock.coderba.client.Injector;
import org.pepstock.coderba.client.events.EditorNativeEvent;
import org.pepstock.coderba.client.resources.ResourcesType;

import com.google.gwt.dom.client.EventTarget;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CODERBA java script object implementation with some
 * utilities to act on java script objects.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class JsHelper {
	// static instance for singleton
	private static final JsHelper INSTANCE = new JsHelper();

	/**
	 * To avoid any instantiation
	 */
	private JsHelper() {
		// to be sure that EDIRBA java script object is injected
		Injector.ensureInjected(ResourcesType.getClientBundle().coderba());
	}

	/**
	 * Singleton object to get the helper instance
	 * 
	 * @return jsHelper instance.
	 */
	public static JsHelper get() {
		return INSTANCE;
	}

	/**
	 * Returns the java script object type of object.
	 * 
	 * @param object the object to get type.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object) {
		// checks consistency of arguments
		if (object != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.typeOf(object), Array.isArray(object));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}

	/**
	 * Returns the java script object type of a property.
	 * 
	 * @param object the object on which to search the property.
	 * @param key the string name of the property to test.
	 * @return the object type
	 */
	public ObjectType typeOf(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// gets the object type by javascript type and checking if is an array
			return ObjectType.getType(NativeJsHelper.type(object, key), NativeJsHelper.isArray(object, key));
		}
		// if here the arguments are not consistent
		return ObjectType.UNDEFINED;
	}
	
	/**
	 * Returns a property of java script object as integer.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return integer value or {@link UndefinedValues#INTEGER} if arguments are not consistent
	 */
	public int propertyAsInt(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsInt(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.INTEGER;
	}
	
	/**
	 * Returns a property of java script object as boolean.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return boolean value or {@link UndefinedValues#BOOLEAN} if arguments are not consistent
	 */
	public boolean propertyAsBoolean(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsBoolean(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.BOOLEAN;
	}

	/**
	 * Returns a property of java script object as string.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified..
	 * @return string value or {@link UndefinedValues#STRING} if arguments are not consistent
	 */
	public String propertyAsString(Object object, String key) {
		// checks consistency of arguments
		if (object != null && key != null) {
			return NativeJsHelper.propertyAsString(object, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.STRING;
	}
	
	/**
	 * FIXME
	 * @param element
	 * @param key
	 * @return
	 */
	public String elementAttribute(EventTarget element, String key) {
		// checks consistency of arguments
		if (element != null && key != null) {
			return NativeJsHelper.elementAttribute(element, key);
		}
		// if here, arguments not consistent
		return UndefinedValues.STRING;
	}
	
	/**
	 * Returns a property of java script object as boolean.
	 * 
	 * @param object the object on which to define the property.
	 * @param key the string name of the property to be defined or modified
	 * @param value value to set to ignore the event.
	 */
	public void ignore(EditorNativeEvent object, String key, boolean value) {
		// checks consistency of arguments
		if (object != null && key != null) {
			// if is enabling
			if (value) {
				// sets property
				NativeJsHelper.ignore(object, key);
			} else {
				// if disabling, remove key
				remove(object, key);
			}
		}
	}
	
	/**
	 * Creates new proxy for callback.
	 * 
	 * @param <T> type of callback wrapped by proxy
	 * @return new proxy for callback.
	 */
	public <T> CallbackProxy<T> newCallbackProxy() {
		return NativeJsHelper.newCallbackProxy();
	}

	/**
	 * Removes a property from a java script object.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	void remove(NativeObject object, String key) {
		NativeJsHelper.remove(object, key);
	}

	/**
	 * Removes a property from a java script object.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	void remove(NativeEntity object, String key) {
		NativeJsHelper.remove(object, key);
	}

	/**
	 * Removes a property from a native event.
	 * 
	 * @param object the object on which to remove the property.
	 * @param key the string name of the property to remove.
	 */
	void remove(EditorNativeEvent object, String key) {
		NativeJsHelper.remove(object, key);
	}
	
	/**
	 * Clones the property value from source object to target one.
	 * 
	 * @param source source java script object
	 * @param target target java script object
	 * @param key The property key to clone
	 */
	void cloneProperty(Object source, Object target, Key key) {
		NativeJsHelper.cloneProperty(source, target, key.value());
	}

}
