/**
    Copyright 2019 Pepstock.org

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
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference Ace code editor and other java script codes, always needed to CODERBA.
 * 
 * @author Marco "Cuc" Cuccato
 */
public interface Resources extends ClientBundle {

	// static reference of this resource
	public static final Resources INSTANCE = GWT.create(Resources.class);

	@Source("/js/coderba.helper.min.js")
	TextResource coderba();

	/**
	 * Returns the java script source code for CodeMirror.
	 * 
	 * @return <code>codemirror.js</code> code for CodeMirror
	 */
	@Source("/js/lib/codemirror.js")
	TextResource codemirror();

	/**
	 * Returns the CSS source code for CodeMirror.
	 * 
	 * @return <code>codemirror.css</code> CSS code for CodeMirror
	 */
	@Source("/js/lib/codemirror.css")
	TextResource codemirrorCss();

}