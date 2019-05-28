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
	private boolean initOnLoad = true;
	// flag if must be destroy on detach
	private boolean destroyOnUnload = true;
	// initializer instance
	private Initiliazer initializer = null;
	// editor instance
	private Editor editor = null;
	// init options
	private final UserOptions initOptions;
	// editor configuration
	private final EditorOptions options;
	/**
	 * @param element
	 */
	public EditorArea() {
		element = Document.get().createTextAreaElement();
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
		initOptions = new UserOptions(CodeMirror.get().getDefaults());
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
	 * Returns <code>true</code> if CodeMirror editor has been initialized, otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> if CodeMirror editor has been initialized, otherwise <code>false</code>.
	 */
	public final boolean isInitialized() {
		return editor != null;
	}

	/**
	 * @return the editor
	 */
	public final Editor getEditor() {
		return editor;
	}
	
	/**
	 * @return the configuration
	 */
	public final EditorOptions getOptions() {
		return options;
	}

	/**
	 * @return the initOnLoad
	 */
	boolean isInitOnLoad() {
		return initOnLoad;
	}

	/**
	 * @param initOnLoad the initOnLoad to set
	 */
	public void setInitOnLoad(boolean initOnLoad) {
		this.initOnLoad = initOnLoad;
	}

	/**
	 * @return the destroyOnUnload
	 */
	public boolean isDestroyOnUnload() {
		return destroyOnUnload;
	}

	/**
	 * @param destroyOnUnload the destroyOnUnload to set
	 */
	public void setDestroyOnUnload(boolean destroyOnUnload) {
		this.destroyOnUnload = destroyOnUnload;
	}

	/**
	 * @return the initializer
	 */
	public Initiliazer getInitializer() {
		return initializer;
	}

	/**
	 * @param initializer the initializer to set
	 */
	public void setInitializer(Initiliazer initializer) {
		this.initializer = initializer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	@Override
	protected void onLoad() {
		if (initOnLoad) {
			initialize();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Widget#onUnload()
	 */
	@Override
	protected void onUnload() {
		if (destroyOnUnload) {
			// destroy
			destroy();
		}
	}
	
	public final void initialize() {
		if (editor == null) {
			if (initializer != null) {
				initializer.beforeInit(this);
			}
			if (options.getValue() != null) {
				element.setInnerHTML(options.getValue());
			}
			// stores the chart instance into collection
			EditorAreas.add(this);
			this.editor = CodeMirror.get().fromTextArea(element, initOptions);
			editor.setId(getId());
			options.setDelegatedOptions(new RuntimeOptions(editor.getNativeObject(), CodeMirror.get().getDefaults()));
			if (initializer != null) {
				initializer.afterInit(this);
			}
			// notify after init
			EditorAreas.fireAfterInit(this);
		}
	}
	
	public final void destroy() {
		if (editor != null) {
			// notify before destroy
			EditorAreas.fireBeforeDestory(this);
			// destroy ...
			// removes chart instance from collection
			EditorAreas.remove(getId());
		}
	}

}
