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
import org.pepstock.coderba.client.resources.Resources;

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
		Injector.ensureInjected(Resources.INSTANCE.coderba());
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
	 * Creates new proxy for callback which will pass <code>this</code> environment of java script as first argument of callback
	 * method.
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
	 * Clones the property value from source object to target one.
	 * 
	 * @param source source java script object
	 * @param target target java script object
	 * @param key The property key to clone
	 */
	void cloneProperty(Object source, Object target, Key key) {
		NativeJsHelper.cloneProperty(source, target, key.value());
	}

	/**
	 * Cast an object to a native object in order to be able to use into a native object container.
	 * 
	 * @param object object to cast
	 * @return a native object instance
	 */
	NativeObject cast(Object object) {
		return NativeJsHelper.cast(object);
	}

}
