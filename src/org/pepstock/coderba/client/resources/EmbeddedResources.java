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
package org.pepstock.coderba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CodeMirror, always needed to CODERBA.<br>
 * This resources type will load the CodeMirror module in sync mode, as part of GWT module to be down loaded.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface EmbeddedResources extends Resources<TextResource> {

	/**
	 * Static reference to resources java script source code
	 */
	public static final EmbeddedResources INSTANCE = GWT.create(EmbeddedResources.class);

	/**
	 * Returns the java script source code for CodeMirror.
	 * 
	 * @return <code>codemirror.js</code> code for CodeMirror
	 */
	@Source("/js/lib/codemirror.js")
	TextResource codemirror();

}