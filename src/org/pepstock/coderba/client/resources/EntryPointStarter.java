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

import org.pepstock.coderba.client.Injector;

import com.google.gwt.resources.client.ResourceCallback;
import com.google.gwt.resources.client.ResourceException;
import com.google.gwt.resources.client.TextResource;

/**
 * Utility to start an entry point leveraging on deferred resources mode.<br>
 * This helps when the GWT application is leveraging on code splitting.<br>
 * <br>
 * 
 * <pre>
 * GWT.runAsync(new RunAsyncCallback() {
 * 
 * 	&#64;Override
 * 	public void onFailure(Throwable throwable) {
 * 		Window.alert("Code download failed");
 * 	}
 * 
 * 	&#64;Override
 * 	public void onSuccess() {
 * 		EntryPointStarter.run(new Runnable() {
 * 
 * 			&#64;Override
 * 			public void run() {
 * 				// CODERBA charts and api...
 * 			}
 * 		});
 * 	}
 * });
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class EntryPointStarter {

	/**
	 * To avoid any instantiation
	 */
	private EntryPointStarter() {
		// do nothing
	}

	/**
	 * Start an entry point as a runnable. This runnable instance must contains all calls to chart.<br>
	 * This helps when the GWT application is leveraging on code splitting.
	 * 
	 * @param runnable the entry point instance as runnable
	 */
	public static void run(final Runnable runnable) {
		// checks if the entry point is consistent
		if (runnable == null) {
			// if not, exception
			throw new IllegalArgumentException("Entry point runnable is null");
		}
		// sets deferred resources
		ResourcesType.setClientBundle(DeferredResources.INSTANCE);
		try {
			// loads CodeMirror in async
			DeferredResources.INSTANCE.codemirror().getText(new ResourceCallback<TextResource>() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onError(com.google.gwt.resources.client.ResourceException)
				 */
				@Override
				public void onError(ResourceException e) {
					throw new ResourceLoadException("Unable to load CodeMirror resource", e);
				}

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.gwt.resources.client.ResourceCallback#onSuccess(com.google.gwt.resources.client.ResourcePrototype)
				 */
				@Override
				public void onSuccess(TextResource resource) {
					// injects the CodeMirror
					Injector.ensureInjected(resource);
					// executes the entry point
					runnable.run();
				}
			});
		} catch (ResourceException e) {
			// if here, there is an error on resource callback
			throw new ResourceLoadException("Unable to load CodeMirror resource", e);
		}
	}
}
