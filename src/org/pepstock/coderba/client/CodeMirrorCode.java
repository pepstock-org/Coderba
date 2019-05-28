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

import org.pepstock.coderba.client.resources.Resources;
import org.pepstock.coderba.client.resources.ScriptResource;
import org.pepstock.coderba.client.resources.StyleResource;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
final class CodeMirrorCode extends InjectableItem {

	private static final String NAME = "CodeMirror";

	/**
	 * Instance of editor mode.
	 */
	static final CodeMirrorCode CODE = new CodeMirrorCode();

	/**
	 */
	CodeMirrorCode() {
		super(NAME);
		getResources().add(new ScriptResource(Resources.INSTANCE.codemirror()));
		getResources().add(new StyleResource(Resources.INSTANCE.codemirrorCss()));
	}
}
