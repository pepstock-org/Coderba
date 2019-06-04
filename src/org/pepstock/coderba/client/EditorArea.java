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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.user.client.ui.Widget;

/**
 * GWT widget to use into user interface which is wrapping a text area and a CodeMirror editor to manage the content of the area
 * itself.<br>
 * Pay attention that the CodeMirror editor must be initialized after the widget will be attached to DOM document.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class EditorArea extends Widget {

	// PCT standard for size
	private static final double DEFAULT_PCT_SIZE = 100D;
	// text area element to use to highlight
	private final TextAreaElement element;
	// text area ID using GWT unique id
	private final String id = Document.get().createUniqueId();
	// flag if must be initialized on attach
	private boolean initOnAttach = true;
	// flag if must be destroy on detach
	private boolean destroyOnDetach = true;
	// initializer instance
	private Initiliazer initializer = null;
	// editor instance
	private Editor editor = null;
	// init options
	private final UserOptions initOptions;
	// editor configuration
	private final EditorOptions options;

	/**
	 * Creates the widget initializing all needed elements to manage the editing.
	 */
	public EditorArea() {
		// creates a text area
		element = Document.get().createTextAreaElement();
		// sets unique ID
		element.setId(id);
		// sets default width values
		element.getStyle().setWidth(DEFAULT_PCT_SIZE, Unit.PCT);
		element.getStyle().setHeight(DEFAULT_PCT_SIZE, Unit.PCT);
		// this styles must be set to 0
		element.getStyle().setMargin(0, Unit.PX);
		element.getStyle().setTop(0, Unit.PX);
		element.getStyle().setBottom(0, Unit.PX);
		element.getStyle().setLeft(0, Unit.PX);
		element.getStyle().setRight(0, Unit.PX);
		element.getStyle().setZIndex(0);
		// set element
		super.setElement(element);
		// injects codemirror.js java script source
		Injector.ensureInjected();
		// creates a user options
		// before creating the editor
		initOptions = new UserOptions(Defaults.get());
		// creates the option wrapper
		options = new EditorOptions(initOptions);
	}

	/**
	 * Returns the ID of editor.<br>
	 * It could be considered as editor unique ID.
	 * 
	 * @return the ID of editor
	 */
	public final String getId() {
		return id;
	}

	/**
	 * Returns <code>true</code> if editor has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if editor has been initialized, otherwise <code>false</code>.
	 */
	public final boolean isInitialized() {
		return editor != null;
	}

	/**
	 * Returns the editor instance or <code>null</code> if invokes before the widget will attached.
	 * 
	 * @return the editor or <code>null</code> if invokes before the widget will attached.
	 */
	public final Editor getEditor() {
		return editor;
	}

	/**
	 * Returns the editor configuration.
	 * 
	 * @return the editor configuration
	 */
	public final EditorOptions getOptions() {
		return options;
	}

	/**
	 * Returns <code>true</code> if the editor will be initialized after the widget will be attached to DOM.
	 * 
	 * @return <code>true</code> if the editor will be initialized after the widget will be attached to DOM
	 */
	boolean isInitOnAttach() {
		return initOnAttach;
	}

	/**
	 * Sets <code>true</code> if the editor will be initialized after the widget will be attached to DOM.
	 * 
	 * @param initOnAttach <code>true</code> if the editor will be initialized after the widget will be attached to DOM.
	 */
	public void setInitOnAttach(boolean initOnAttach) {
		this.initOnAttach = initOnAttach;
	}

	/**
	 * Returns <code>true</code> if the editor will be destroy after the widget will be detached from DOM.
	 * 
	 * @return <code>true</code> if the editor will be destroy after the widget will be detached from DOM.
	 */
	public boolean isDestroyOnDetach() {
		return destroyOnDetach;
	}

	/**
	 * Sets <code>true</code> if the editor will be destroy after the widget will be detached from DOM.
	 * 
	 * @param destroyOnDetach <code>true</code> if the editor will be destroy after the widget will be detached from DOM.
	 */
	public void setDestroyOnDetach(boolean destroyOnDetach) {
		this.destroyOnDetach = destroyOnDetach;
	}

	/**
	 * Returns the initializer instance.
	 * 
	 * @return the initializer instance
	 */
	public Initiliazer getInitializer() {
		return initializer;
	}

	/**
	 * Sets new initializer instance.
	 * 
	 * @param initializer new initializer instance
	 */
	public void setInitializer(Initiliazer initializer) {
		this.initializer = initializer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onAttach()
	 */
	@Override
	protected void onAttach() {
		// invokes super
		// MANDATORY
		super.onAttach();
		// checks if must create the editor
		if (initOnAttach) {
			// initializes editor
			initialize();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onDetach()
	 */
	@Override
	protected void onDetach() {
		// invokes super
		// MANDATORY
		super.onDetach();
		// checks if must destroy the editor
		if (destroyOnDetach) {
			// destroy
			destroy();
		}
	}

	/**
	 * Initializes the CodeMirror editor.
	 */
	public final void initialize() {
		// checks if editor is already initialized
		if (editor == null) {
			// checks an initializer instance has been set
			if (initializer != null) {
				// invokes the custom implementation
				initializer.beforeInit(this);
			}
			// checks the configuration contains a starting text
			if (options.getValue() != null) {
				// if yes, sets to text area
				element.setInnerHTML(options.getValue());
			}
			// stores the editor area instance into cache
			EditorAreas.add(this);
			// creates the code mirror editor
			this.editor = CodeMirror.get().fromTextArea(element, initOptions);
			// sets the unique id to editor
			editor.setId(getId());
			// switches the configuration from user to runtime
			options.setDelegatedOptions(new RuntimeOptions(editor.getNativeObject(), Defaults.get()));
			// checks an initializer instance has been set
			if (initializer != null) {
				// invokes the custom implementation
				initializer.afterInit(this);
			}
			// notify after initialization
			EditorAreas.fireAfterInit(this);
		}
	}

	/**
	 * Destroy the CodeMirror editor.
	 */
	public final void destroy() {
		// checks if editor is already initialized
		if (editor != null) {
			// notify before destroy
			EditorAreas.fireBeforeDestory(this);
			// destroy ...
			editor = null;
			// removes editor area instance from cache
			EditorAreas.remove(getId());
		}
	}

}
