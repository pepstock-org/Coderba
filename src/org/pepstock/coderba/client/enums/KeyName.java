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
package org.pepstock.coderba.client.enums;

import org.pepstock.coderba.client.commons.Key;

/**
 * Enumerates all keys usable to defines a stroke.<br>
 * It interacts with a key map.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyName implements Key
{
	KEY_PAUSE("Pause"),
	KEY_BACKSPACE("Backspace"),
	KEY_TAB("Tab"),
	KEY_ENTER("Enter"),
	KEY_CAPS_LOCK("CapsLock"),
	KEY_ESC("Esc"),
	KEY_SPACE("Space"),
	KEY_PAGE_UP("PageUp"),
	KEY_PAGE_DOWN("PageDown"),
	KEY_END("End"),
	KEY_HOME("Home"),
	KEY_LEFT("Left"),
	KEY_UP("Up"),
	KEY_RIGHT("Right"),
	KEY_DOWN("Down"),
	KEY_PRINT_SCRN("PrintScrn"),
	KEY_INSERT("Insert"),
	KEY_DELETE("Delete"),
	KEY_0("0"),
	KEY_1("1"),
	KEY_2("2"),
	KEY_3("3"),
	KEY_4("4"),
	KEY_5("5"),
	KEY_6("6"),
	KEY_7("7"),
	KEY_8("8"),
	KEY_9("9"),
	KEY_SEMICOLON(";"),
	KEY_EQUAL("="),
	KEY_A("A"),
	KEY_B("B"),
	KEY_C("C"),
	KEY_D("D"),
	KEY_E("E"),
	KEY_F("F"),
	KEY_G("G"),
	KEY_H("H"),
	KEY_I("I"),
	KEY_J("J"),
	KEY_K("K"),
	KEY_L("L"),
	KEY_M("M"),
	KEY_N("N"),
	KEY_O("O"),
	KEY_P("P"),
	KEY_Q("Q"),
	KEY_R("R"),
	KEY_S("S"),
	KEY_T("T"),
	KEY_U("U"),
	KEY_V("V"),
	KEY_W("W"),
	KEY_X("X"),
	KEY_Y("Y"),
	KEY_Z("Z"),
	KEY_MOD("Mod"),
	KEY_ASTERIX("*"),
	KEY_MINUS("-"),
	KEY_DOT("."),
	KEY_FORWARD_SLASH("/"),
	KEY_F1("F1"),
	KEY_F2("F2"),
	KEY_F3("F3"),
	KEY_F4("F4"),
	KEY_F5("F5"),
	KEY_F6("F6"),
	KEY_F7("F7"),
	KEY_F8("F8"),
	KEY_F9("F9"),
	KEY_F10("F10"),
	KEY_F11("F11"),
	KEY_F12("F12"),
	KEY_SCROLL_LOCK("ScrollLock"),
	KEY_COMMA(","),
	KEY_APOSTROPHE("`"),
	KEY_OPEN_SQUARE_BRACKET("["),
	KEY_BACK_SLASH("\\"),
	KEY_CLOSE_SQUARE_BRACKET("]"),
	KEY_SINGLE_QUOTE("'"),
	CLICK_LEFT("LeftClick"),
	CLICK_MIDDLE("MiddleClick"),
	CLICK_RIGHT("RightClick"),
	CLICK_DOUBLE_LEFT("LeftDoubleClick"),
	CLICK_DOUBLE_MIDDLE("MiddleDoubleClick"),
	CLICK_DOUBLE_RIGHT("RightDoubleClick"),
	CLICK_TRIPLE_LEFT("LeftTripleClick"),
	CLICK_TRIPLE_MIDDLE("MiddleTripleClick"),
	CLICK_TRIPLE_RIGHT("RightTripleClick");

	// string representation of key
	private final String value;

	/**
	 * Creates a key with its string representation.
	 * 
	 * @param value string representation of key
	 */
	private KeyName(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.coderba.client.commons.Key#value()
	 */
	@Override
	public String value() {
		return value;
	}

}
