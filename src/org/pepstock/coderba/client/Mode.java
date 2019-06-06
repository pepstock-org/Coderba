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
 * Modes typically consist of a single JavaScript file.<br>
 * This file defines, in the simplest case, a lexer (tokenizer) for your language - a function that takes a character stream as
 * input, advances it past a token, and returns a style for that token.<br>
 * More advanced modes can also handle indentation for the language.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class Mode extends InjectableItem {

	/**
	 * Creates the mode with its name as string.
	 * 
	 * @param name name of mode as string
	 */
	protected Mode(String name) {
		super(name);
	}

}
