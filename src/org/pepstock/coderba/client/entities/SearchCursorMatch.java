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

import org.pepstock.coderba.client.addons.AddOnSearchcursor;
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.utils.RegExpResult;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Related to {@link AddOnSearchcursor}, maps the matches after searching.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class SearchCursorMatch extends Range {

	/**
	 * To avoid any instantiation
	 */
	SearchCursorMatch() {
	}

	/**
	 * If matching a regular expression, the return value will be the array returned by the match method, in case you want to
	 * extract matched groups.
	 * 
	 * @return regular expression result
	 */
	@JsProperty
	public native RegExpResult getMatch();

}
