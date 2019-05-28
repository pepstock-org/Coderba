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
package org.pepstock.coderba.client.entities;

import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainerFactory;
import org.pepstock.coderba.client.commons.UndefinedValues;

/**
 * 
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class Anchor extends BaseEntity{
	
	/**
	 * Anchor factory to build a anchor by a native object
	 */
	public static final NativeObjectContainerFactory<Anchor> FACTORY = new AnchorFactory();
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		ANCHOR("anchor"),
		HEAD("head"),
		FROM("from"),
		TO("to");

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


	public Anchor(Range range) {
		this(range.getFrom(), range.getTo());
	}

	public Anchor(Position anchor, Position head) {
		this((NativeObject)null);
		if (anchor == null) {
			throw new IllegalArgumentException("Anchor is null");
		}
		if (head == null) {
			throw new IllegalArgumentException("Head is null");
		}
		setAnchor(anchor);
		setHead(head);
		setFromAndTo();
	}

	
	Anchor(NativeObject nativeObject){
		super(nativeObject);
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public Position getAnchor() {
		return Position.FACTORY.create(getValue(Property.ANCHOR));
	}

	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public void setAnchor(Position anchor) {
		setValue(Property.ANCHOR, anchor);
		if (anchor != null) {
			setFromAndTo();
		}
	}
	
	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public Position getHead() {
		return Position.FACTORY.create(getValue(Property.HEAD));
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public void setHead(Position head) {
		setValue(Property.HEAD, head);
		if (head != null) {
			setFromAndTo();
		}
	}
	
	/**
	 * Sets automatically FROM and TO keys, accordingly with anchor and head
	 */
	private void setFromAndTo() {
		if (has(Property.ANCHOR, Property.HEAD)) {
			Position anchor = getAnchor();
			Position head = getHead();
			setValue(Property.FROM, Position.min(anchor, head));
			setValue(Property.TO, Position.max(anchor, head));
		}
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class AnchorFactory implements NativeObjectContainerFactory<Anchor> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.
		 * NativeObject)
		 */
		@Override
		public Anchor create(NativeObject nativeObject) {
			Anchor anchor = new Anchor(nativeObject);
			if (!anchor.has(Property.ANCHOR)) {
				throw new IllegalArgumentException("Anchor field is missing");
			}
			if (!anchor.has(Property.HEAD)) {
				throw new IllegalArgumentException("Head field is missing");
			}
			return anchor;
		}

	}

	
}