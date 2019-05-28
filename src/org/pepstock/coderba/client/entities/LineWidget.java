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

import java.util.concurrent.atomic.AtomicInteger;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.UndefinedValues;

import com.google.gwt.dom.client.Element;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class LineWidget extends LineWidgetOptions{
	
	// internal count
	private static final AtomicInteger COUNTER = new AtomicInteger(0);
	
	private final NativeLineWidget nativeObject;
	
	private final Document document;
	
	private final LineHandle handle;
	
	private static final Element DEFAULT_NODE = null;
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ID("id"),
		NODE("node"),
		HEIGHT("height");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}
	}

	/**
	 * 
	 */
	LineWidget(NativeLineWidget nativeObject, Document document) {
		super(nativeObject);
		this.nativeObject = nativeObject;
		if (keys().isEmpty()) {
			throw new IllegalArgumentException("Native line widget is empty!");
		}
		if (document == null) {
			throw new IllegalArgumentException("Document instance is null!");
		}
		this.document = document;
		// stores the id based on a counter
		setValue(Property.ID, COUNTER.getAndIncrement());
		this.handle = new LineHandle(nativeObject.getLine());
	}
	
	public int getId() {
		return getValue(Property.ID, UndefinedValues.INTEGER);
	}

	public int getHeight() {
		return getValue(Property.HEIGHT, UndefinedValues.INTEGER);
	}
	
	public Element getNode() {
		return getValue(Property.NODE, DEFAULT_NODE);
	}
	
	/**
	 * @return the handle
	 */
	public LineHandle getHandle() {
		return handle;
	}

	/**
	 * Removes the widget.
	 */
	public void clear() {
		document.clearLineWidget(getId());
		nativeObject.clear();
	}

	/**
	 * Call this if you made some change to the widget's DOM node that might affect its height.<br>
	 * It'll force CodeMirror to update the height of the line that contains the widget.
	 */
	public void changed() {
		nativeObject.changed();
	}
}
