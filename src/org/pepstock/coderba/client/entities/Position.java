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
import org.pepstock.coderba.client.commons.NativeName;
import org.pepstock.coderba.client.defaults.GlobalDefaults;
import org.pepstock.coderba.client.enums.StickyPosition;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Whenever points in the document are represented, the API uses objects with "line" and "ch"(column) properties, mapped by this
 * immutable object.<br>
 * Both are zero-based.<br>
 * Such positions may also have a sticky property holding "before" or "after", whether the position is associated with the
 * character before or after it.<br>
 * This influences, for example, where the cursor is drawn on a line-break or bidi-direction boundary
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = NativeName.OBJECT)
public final class Position extends BaseNativeEntity {

	/**
	 * To avoid any instantiation
	 */
	private Position() {
		// do nothing
	}

	/**
	 * A constructor for the objects that are used to represent the first line number in editor documents.<br>
	 * By default the column is 0, the starting of the line.
	 * 
	 * @return a position with the first line number. Column will be set to 0
	 */
	@JsOverlay
	public static Position create() {
		return create(CodeMirror.get().getDefaults().getFirstLineNumber(), 0);

	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents, using the passed line number.<br>
	 * By default the column will be set to end of line (using {@link Integer#MAX_VALUE}).
	 * 
	 * @param line line number into document
	 * @return a position with passed line number. Column will be set to {@link Integer#MAX_VALUE}
	 */
	@JsOverlay
	public static Position create(int line) {
		return create(line, Integer.MAX_VALUE);
	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents, using the passed line and column
	 * numbers.
	 * 
	 * @param line line number into document
	 * @param column column number into document
	 * @return a position with passed line and column numbers
	 */
	@JsOverlay
	public static Position create(int line, int column) {
		return create(line, column, null);
	}

	/**
	 * A constructor for the objects that are used to represent positions in editor documents, using the passed line and column
	 * numbers.<br>
	 * The sticky can be set to "before" or "after" to make the position explicitly associate with the character before or after
	 * it.
	 * 
	 * @param line line number into document
	 * @param column column number into document
	 * @param sticky before" or "after", whether the position is associated with the character before or after it.
	 * @return a position with passed line and column numbers
	 */
	@JsOverlay
	public static Position create(int line, int column, StickyPosition sticky) {
		// creates object
		Position position = new Position();
		// sets all values
		position.setLine(line);
		position.setColumn(column);
		position.setSticky(sticky);
		return position;
	}

	/**
	 * Clones a object that are used to represent positions in editor documents.<br>
	 * If source is <code>null</code>, a default position is created, with the first line number and column 0.
	 * 
	 * @param source source position to clone
	 * @return new position instance, clone of source one or a default one if source instance is <code>null</code>
	 */
	@JsOverlay
	public static Position create(Position source) {
		// checks if argument is consistent
		if (source != null) {
			// create new position
			Position clonedPosition = create(source.getLine(), source.getColumn());
			// sets sticky if present
			clonedPosition.nativeSetSticky(source.checkAndGet("sticky", source.nativeGetSticky(), null));
			// returns cloned position
			return clonedPosition;
		}
		// returns default position
		return GlobalDefaults.get().getPosition();
	}

	/**
	 * Returns the line number in editor documents.
	 * 
	 * @return the line number in editor documents
	 */
	@JsProperty
	public native int getLine();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the line number in editor documents.
	 * 
	 * @param line the line number in editor documents
	 */
	@JsProperty
	private native void setLine(int line);

	/**
	 * Returns the column number in editor documents.
	 * 
	 * @return the column number in editor documents
	 */
	@JsProperty(name = "ch")
	public native int getColumn();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the column number in editor documents.
	 * 
	 * @param column the column number in editor documents
	 */
	@JsProperty(name = "ch")
	private native void setColumn(int column);

	/**
	 * <b>INTERNAL</b><br>
	 * Returns the sticky attribute.<br>
	 * The sticky can be set to <code>null</code>, "before" or "after" to make the position explicitly associate with the
	 * character before or after it.
	 * 
	 * @return can return <code>null</code>, "before" or "after" to make the position explicitly associate with the character
	 *         before or after it
	 */
	@JsProperty(name = "sticky")
	private native String nativeGetSticky();

	/**
	 * <b>INTERNAL</b><br>
	 * Sets the sticky attribute.<br>
	 * The sticky can be set to <code>null</code>, "before" or "after" to make the position explicitly associate with the
	 * character before or after it.
	 * 
	 * @param sticky can be set to <code>null</code>, "before" or "after" to make the position explicitly associate with the
	 *            character before or after it
	 */
	@JsProperty(name = "sticky")
	private native void nativeSetSticky(String sticky);

	/**
	 * Returns the sticky attribute.<br>
	 * The sticky can be set to {@link StickyPosition#NULL}, {@link StickyPosition#BEFORE} or {@link StickyPosition#AFTER} to
	 * make the position explicitly associate with the character before or after it.
	 * 
	 * @return can return {@link StickyPosition#NULL}, {@link StickyPosition#BEFORE} or {@link StickyPosition#AFTER} to make the
	 *         position explicitly associate with the character before or after it.
	 */
	@JsOverlay
	public StickyPosition getSticky() {
		return Key.getKeyByValue(StickyPosition.class, nativeGetSticky(), StickyPosition.NULL);
	}

	/**
	 * Sets the sticky attribute.<br>
	 * The sticky can be set to {@link StickyPosition#NULL}, {@link StickyPosition#BEFORE} or {@link StickyPosition#AFTER} to
	 * make the position explicitly associate with the character before or after it.
	 * 
	 * @param sticky can be set to {@link StickyPosition#NULL}, {@link StickyPosition#BEFORE} or {@link StickyPosition#AFTER} to
	 *            make the position explicitly associate with the character before or after it.
	 */
	@JsOverlay
	public void setSticky(StickyPosition sticky) {
		// checks if argument is consistent and is not set to NULL
		if (Key.isValid(sticky) && !StickyPosition.NULL.equals(sticky)) {
			nativeSetSticky(sticky.value());
		} else {
			// if here, argument not consistent, sets null
			nativeSetSticky(null);
		}
	}

	/**
	 * Returns <code>true</code> when the motion was clipped by hitting the end or start of the document.
	 * 
	 * @return <code>true</code> when the motion was clipped by hitting the end or start of the document
	 */
	@JsProperty
	public native boolean isHitSide();

	/**
	 * Compares two positions. Returns a negative integer, zero, or a positive integer as first position argument is less than,
	 * equal to, or greater than the second argument.
	 * 
	 * @param firstPosition first position to use
	 * @param secondPosition second position to use
	 * @return a negative integer, zero, or a positive integer as first position argument is less than, equal to, or greater
	 *         than the second argument
	 */
	@JsOverlay
	public static int compare(Position firstPosition, Position secondPosition) {
		// checks if first is not null
		if (firstPosition != null) {
			// checks if second is not null
			if (secondPosition != null) {
				// if here, both positions are consistent and
				// then checks the lines and columns
				int diff = firstPosition.getLine() - secondPosition.getLine();
				// checks if both positions have got the same line number
				if (diff == 0) {
					// if equals
					// checks the column
					return firstPosition.getColumn() - secondPosition.getColumn();
				}
				// if here, lines are not equals
				return diff;
			} else {
				// if here, second position is null
				// then the first position is greater than
				return 1;
			}
		} else {
			// if here, the first position is null
			// checks if second is not null
			if (secondPosition != null) {
				// if here, first position is null
				// then the second position is greater than
				return -1;
			} else {
				// if here both first and second positions are null
				// then equals
				return 0;
			}
		}
	}

	/**
	 * Returns the greater of two positions.<br>
	 * If the arguments have the same value, the result is the first position.
	 * 
	 * @param firstPosition first position to use
	 * @param secondPosition second position to use
	 * @return the larger of first and second position.
	 */
	@JsOverlay
	public static Position max(Position firstPosition, Position secondPosition) {
		return compare(firstPosition, secondPosition) < 0 ? secondPosition : firstPosition;
	}

	/**
	 * Returns the smaller of two positions.<br>
	 * If the arguments have the same value, the result is the second position.
	 * 
	 * @param firstPosition first position to use
	 * @param secondPosition second position to use
	 * @return the smaller of first and second position.
	 */
	@JsOverlay
	public static Position min(Position firstPosition, Position secondPosition) {
		return compare(firstPosition, secondPosition) < 0 ? firstPosition : secondPosition;
	}

}
