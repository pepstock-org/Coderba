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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.Mode;
import org.pepstock.coderba.client.Modes;

/**
 * Defines the mode specification of CodeMirror mode.<br>
 * This is useful when the mime is not enough. like to create a document.<br>
 * It does not contain any visible method or property because is immutable.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ModeSpecification {

	// field initialized if mode specification is a string
	private final String name;
	// field initialized if mode specification is a native object
	private final NativeModeSpecification nativeModeSpecification;

	/**
	 * Creates a mode specification with the string name, stored into {@link MimeModes}.
	 * 
	 * @param name name of mode specification
	 */
	ModeSpecification(String name) {
		// stores values
		this.name = name;
		this.nativeModeSpecification = null;
	}

	/**
	 * Creates a mode specification with the native object, stored into {@link MimeModes}.
	 * 
	 * @param nativeModeSpecification native object of mode specification
	 */
	ModeSpecification(NativeModeSpecification nativeModeSpecification) {
		// stores values
		this.name = null;
		this.nativeModeSpecification = nativeModeSpecification;
	}

	/**
	 * Returns <code>true</code> if this mode specification has been defined by a string of mode.
	 * 
	 * @return <code>true</code> if this mode specification has been defined by a string of mode
	 */
	boolean isString() {
		return name != null;
	}

	/**
	 * Returns the name of mode specification if this mode specification has been defined by a string, otherwise
	 * <code>null</code>.
	 * 
	 * @return the name of mode specification if this mode specification has been defined by a string, otherwise
	 *         <code>null</code>
	 */
	String getName() {
		return name;
	}

	/**
	 * Returns the native object of mode specification if this mode specification has been defined by a natove object, otherwise
	 * <code>null</code>.
	 * 
	 * @return the native object of mode specification if this mode specification has been defined by a natove object, otherwise
	 *         <code>null</code>
	 */
	NativeModeSpecification getModeSpecification() {
		return nativeModeSpecification;
	}

	/**
	 * Returns the mode instance.
	 * 
	 * @return the mode instance
	 */
	public Mode getMode() {
		// gets the name of mode
		// checking if must be retrieved by the native object or simply by the string
		if (isString()) {
			return Modes.get().retrieve(name);
		}
		// if here, mode is based on native object of mode specification
		return Modes.get().retrieve(nativeModeSpecification.getName());
	}

}
