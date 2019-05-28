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
package org.pepstock.coderba.client.callbacks;

/**
 * A function used to format line numbers. The function is passed the line number, and should return a string that will be shown
 * in the gutter.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public interface LineNumberFormatterHandler {

	/**
	 * A function used to format line numbers. The function is passed the line number, and should return a string that will be
	 * shown in the gutter.
	 * 
	 * @param line line number to format
	 * @return string that will be shown in the gutter
	 */
	String format(int line);

}
