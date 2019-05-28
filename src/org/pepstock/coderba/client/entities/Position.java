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

import org.pepstock.coderba.client.CodeMirror;
import org.pepstock.coderba.client.commons.Key;
import org.pepstock.coderba.client.commons.NativeObject;
import org.pepstock.coderba.client.commons.NativeObjectContainerFactory;
import org.pepstock.coderba.client.commons.UndefinedValues;
import org.pepstock.coderba.client.defaults.GlobalDefaults;
import org.pepstock.coderba.client.enums.StickyPosition;

/**
 * Whenever points in the document are represented, the API uses objects with "line" and "ch" properties. Both are zero-based.
 * CodeMirror makes sure to 'clip' any positions passed by client code so that they fit inside the document, so you shouldn't
 * worry too much about sanitizing your coordinates. If you give ch a value of null, or don't specify it, it will be replaced
 * with the length of the specified line. Such positions may also have a sticky property holding "before" or "after", whether
 * the position is associated with the character before or after it. This influences, for example, where the cursor is drawn on
 * a line-break or bidi-direction boundary
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Position extends BaseEntity {
	
	public static final int DEFAULT_COLUMN = Integer.MAX_VALUE;
	
	/**
	 * Token factory to build a token by a native object
	 */
	public static final NativeObjectContainerFactory<Position> FACTORY = new PositionFactory();
	
	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		LINE("line"),
		COLUMN("ch"),
		STICKY("sticky"),
		HIT_SIDE("hitSide");

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
	 * A constructor for the objects that are used to represent the line in editor documents.
	 * 
	 * @param line line into document
	 * @return a position only with the line. Column will be set with the length of the specified line.
	 */
	public Position() {
		this(CodeMirror.get().getDefaults().getFirstLineNumber(), 0);
		
	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents. sticky defaults to null, but can
	 * be set to "before" or "after" to make the position explicitly associate with the character before or after it.
	 * 
	 * @param line line into document
	 * @param column column into document
	 * @param sticky before" or "after", whether the position is associated with the character before or after it.
	 * @return a position object
	 */
	public Position(int line) {
		this(line, DEFAULT_COLUMN);		
	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents. sticky defaults to null, but can
	 * be set to "before" or "after" to make the position explicitly associate with the character before or after it.
	 * 
	 * @param line line into document
	 * @param column column into document
	 * @param sticky before" or "after", whether the position is associated with the character before or after it.
	 * @return a position object
	 */
	public Position(int line, int column) {
		this(line, column, null);		
	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents. sticky defaults to null, but can
	 * be set to "before" or "after" to make the position explicitly associate with the character before or after it.
	 * 
	 * @param line line into document
	 * @param column column into document
	 * @param sticky before" or "after", whether the position is associated with the character before or after it.
	 * @return a position object
	 */
	public Position(int line, int column, StickyPosition sticky) {
		this(null);
		setLine(line);
		setColumn(column);
		setSticky(sticky);
	}

	/**
	 * FIXME
	 * @param nativeObject
	 */
	Position(NativeObject nativeObject){
		super(nativeObject);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is {@link GlobalDefaults#getFirstLineNumber()}.
	 */
	public int getLine() {
		return getValue(Property.LINE, UndefinedValues.INTEGER);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is .
	 */
	private void setLine(int line) {
		setValue(Property.LINE, line);
	}

	/**
	 * Returns the right of area.
	 * 
	 * @return the right of area. Default is {@link Integer#MAX_VALUE}.
	 */
	public int getColumn() {
		return getValue(Property.COLUMN, DEFAULT_COLUMN);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is .
	 */
	private void setColumn(int character) {
		setValue(Property.COLUMN, character);
	}

	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public StickyPosition getSticky() {
		return getValue(Property.STICKY, StickyPosition.class, StickyPosition.AFTER);
	}
	
	/**
	 * Returns the top of area.
	 * 
	 * @return the top of area. Default is .
	 */
	public void setSticky(StickyPosition sticky) {
		if (Key.isValid(sticky)) {
			setValue(Property.STICKY, sticky);
		}
	}
	
	/**
	 * Returns the bottom of area.
	 * 
	 * @return the bottom of area. Default is {@link UndefinedValues#INTEGER}.
	 */
	public boolean isHitSide() {
		return getValue(Property.HIT_SIDE, false);
	}
	
	public static Position create(Position source) {
		if (source != null) {
			Position clonedPosition = new Position(source.getLine(), source.getColumn());
			if (source.has(Property.STICKY)) {
				clonedPosition.setSticky(source.getSticky());
			}
			return clonedPosition;
		}
		return new Position();
	}
	
	/**
	 * Compare two positions, return 0 if they are the same, a negative number when a is less, and a positive number otherwise.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int compare(Position a, Position b) {
		if (a != null) {
			if (b != null) {
				int diff = a.getLine() - b.getLine();
				if (diff == 0) {
					return a.getColumn() - b.getColumn();
				}
				return diff;
			} else {
				return 1;
			}
		} else {
			if (b != null) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public static Position max(Position a, Position b) {
		return compare(a, b) < 0 ? b : a;
	}

	public static Position min(Position a, Position b) {
		return compare(a, b) < 0 ? a : b;
	}
	
	/**
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	static class PositionFactory implements NativeObjectContainerFactory<Position>{

		/* (non-Javadoc)
		 * @see org.pepstock.coderba.client.cm.commons.NativeObjectContainerFactory#create(org.pepstock.coderba.client.cm.commons.NativeObject)
		 */
		@Override
		public Position create(NativeObject nativeObject) {
			Position position = new Position(nativeObject);
			if (!position.has(Property.LINE)) {
				throw new IllegalArgumentException("'line' field is missing!");
			}
			if (!position.has(Property.COLUMN)) {
				throw new IllegalArgumentException("'ch' field is missing!");
			}
			return position;
		}
	}
}
