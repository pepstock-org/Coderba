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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Related to {@link AddOnSearchcursor}, it can be used to implement search/replace functionality on document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class SearchCursor {

	/**
	 * To avoid any instantiation
	 */
	SearchCursor() {
	}

	/**
	 * Search forward from the current position.<br>
	 * The return value indicates whether a match was found.
	 * 
	 * @return <code>true</code> whether a match was found
	 */
	@JsMethod
	public native boolean findNext();

	/**
	 * Search backward from the current position.<br>
	 * The return value indicates whether a match was found.
	 * 
	 * @return <code>true</code> whether a match was found
	 */
	@JsMethod
	public native boolean findPrevious();
	
	/**
	 * If matching a regular expression, the return value will be the array returned, in case you want to
	 * extract matched groups.
	 * 
	 * @param backward <code>true</code> if is backward search.
	 * @param start starting position of search
	 * @return match object
	 */
	@JsMethod(name = "matches")
	private native SearchCursorMatch nativeMatches(boolean backward, Position start);

	/**
	 * These are only valid when the last call to findNext or findPrevious did not return false.<br>
	 * It returns the position pointing at the start of the match
	 * 
	 * @return the position of match
	 */
	@JsMethod
	public native Position from();

	/**
	 * These are only valid when the last call to findNext or findPrevious did not return false.<br>
	 * It returns the position pointing at the end of the match
	 * 
	 * @return the position of match
	 */
	@JsMethod
	public native Position to();

	/**
	 * Replaces the currently found match with the given text and adjusts the cursor position to reflect the replacement.
	 * 
	 * @param text text to replace the match
	 * @param origin how to adjusts the cursor position to reflect the replacement
	 */
	@JsMethod
	public native void replace(String text, String origin);
	
	/**
	 * If matching a regular expression, the return value will be the array returned, in case you want to
	 * extract matched groups.
	 * 
	 * @param backward <code>true</code> if is backward search.
	 * @param start starting position of search
	 * @return match object
	 */
	@JsOverlay
	public SearchCursorMatch matches(boolean backward, Position start) {
		// checks if position is consistent
		if (start != null) {
			nativeMatches(backward, start);
		}
		// if here, uses the default position
		return nativeMatches(backward, Position.create());		
	}
}
