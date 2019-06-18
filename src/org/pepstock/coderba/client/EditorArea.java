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

import org.pepstock.coderba.client.entities.CodeMirror;
import org.pepstock.coderba.client.entities.Editor;
import org.pepstock.coderba.client.entities.EditorOptions;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Position;
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
	// wrapper of text area
	private final DivElement wrapper;
	// text area element to use to highlight
	private final TextAreaElement element;
	// flag if must be initialized on attach
	private boolean initOnAttach = true;
	// flag if must be destroy on detach
	private boolean destroyOnDetach = true;
	// initializer instance
	private Initializer initializer = null;
	// editor instance
	private Editor editor = null;
	// editor configuration
	private final EditorOptions options;
	// id object needed to create the editor
	private final EditorAreaId editorAreaId = new EditorAreaId();

	/**
	 * Creates the widget initializing all needed elements to manage the editing.
	 */
	public EditorArea() {
		// creates wrapper
		wrapper = Document.get().createDivElement();
		// sets relative position
		wrapper.getStyle().setPosition(Position.RELATIVE);
		// sets default width values
		wrapper.getStyle().setWidth(DEFAULT_PCT_SIZE, Unit.PCT);
		wrapper.getStyle().setHeight(DEFAULT_PCT_SIZE, Unit.PCT);
		// this styles must be set to 0
		wrapper.getStyle().setMargin(0, Unit.PX);
		wrapper.getStyle().setTop(0, Unit.PX);
		wrapper.getStyle().setBottom(0, Unit.PX);
		wrapper.getStyle().setLeft(0, Unit.PX);
		wrapper.getStyle().setRight(0, Unit.PX);
		wrapper.getStyle().setZIndex(0);
		// creates a text area
		element = Document.get().createTextAreaElement();
		// sets unique ID
		element.setId(editorAreaId.getId());
		wrapper.appendChild(element);
		// set element
		super.setElement(wrapper);
		// injects codemirror.js java script source
		Injector.ensureInjected();
		// creates the option wrapper
		options = new EditorOptions();
	}

	/**
	 * Returns the ID of editor.<br>
	 * It could be considered as editor unique ID.
	 * 
	 * @return the ID of editor
	 */
	public final String getId() {
		return editorAreaId.getId();
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
	public Initializer getInitializer() {
		return initializer;
	}

	/**
	 * Sets new initializer instance.
	 * 
	 * @param initializer new initializer instance
	 */
	public void setInitializer(Initializer initializer) {
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
			this.editor = CodeMirror.get().fromTextArea(editorAreaId, this);
			// checks an initializer instance has been set
			if (initializer != null) {
				// invokes the custom implementation
				initializer.afterInit(this);
			}
			this.editor.refresh();
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
			// detaches text area
			editor.toTextArea();
			// removes wrapper
			editor.getWrapperElement().removeFromParent();
			// destroy ...
			editor = null;
			// removes editor area instance from cache
			EditorAreas.remove(getId());
		}
	}

}
