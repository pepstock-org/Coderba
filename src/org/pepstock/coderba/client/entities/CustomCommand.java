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

import org.pepstock.coderba.client.EditorArea;
import org.pepstock.coderba.client.callbacks.CommandHandler;
import org.pepstock.coderba.client.commons.CallbackProxy;
import org.pepstock.coderba.client.commons.CallbackProxy.Proxy;
import org.pepstock.coderba.client.commons.JsHelper;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.enums.CommandType;

/**
 * Defines a command defined by user, to interact with editor by a command.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CustomCommand extends Command {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the documentEachLine function
	private final CallbackProxy<CommandFunction> commandFunctionProxy = JsHelper.get().newCallbackProxy();
	// name of command
	private final Key name;
	// interface of command handler to implement.
	private final CommandHandler handler;

	/**
	 * Creates a custom command with its name and handler to invoke.
	 * 
	 * @param name name of command, as string
	 * @param handler handler to be invoked to perform the action
	 */
	public CustomCommand(String name, CommandHandler handler) {
		this(Key.create(name), handler);
	}

	/**
	 * Creates a custom command with its name and handler to invoke.
	 * 
	 * @param name name of command
	 * @param handler handler to be invoked to perform the action
	 */
	public CustomCommand(Key name, CommandHandler handler) {
		// sets the command type
		super(CommandType.CUSTOM);
		// checks if name is consistent
		Key.checkIfValid(name);
		// checks if handle is consistent
		if (handler == null) {
			// if no, exception
			throw new IllegalArgumentException("Handler is null");
		}
		// stores name and handler
		this.name = name;
		this.handler = handler;
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		commandFunctionProxy.setCallback(this::onExecute);
	}

	/**
	 * Called to invoke the command handler.
	 * 
	 * @param editor editor instance where apply the command on
	 */
	private void onExecute(NativeEditor editor) {
		// checks if editor is consistent
		if (editor != null) {
			// gets editor area
			EditorArea area = editor.getEditorArea();
			// checks if area is consistent
			if (area != null) {
				// executes
				handler.execute(area);
			}
		}
	}

	/**
	 * Returns the proxy function needed to invoke the handler.
	 * 
	 * @returnthe proxy function needed to invoke the handler
	 */
	Proxy getProxy() {
		return commandFunctionProxy.getProxy();
	}

	/**
	 * Returns the name of command.
	 * 
	 * @return the name of command
	 */
	public Key getName() {
		return name;
	}

	/**
	 * Returns the command handler instance related to this command.
	 * 
	 * @return the command handler instance related to this command
	 */
	public CommandHandler getHandler() {
		return handler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.entities.Command#execute(org.pepstock.coderba.client.EditorArea)
	 */
	@Override
	public void execute(EditorArea editorArea) {
		handler.execute(editorArea);
	}

}
