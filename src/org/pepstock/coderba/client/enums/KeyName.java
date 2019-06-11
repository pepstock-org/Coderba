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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.pepstock.coderba.client.commons.Key;

/**
 * 
 * Modifiers Shift-, Cmd-, Ctrl-, and Alt-
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public enum KeyName implements Key
{
	KEY_PAUSE("Pause", 3, 19),
	KEY_BACKSPACE("Backspace", 8),
	KEY_TAB("Tab", 9),
	KEY_ENTER("Enter", 13),
	KEY_CAPS_LOCK("CapsLock", 20),
	KEY_ESC("Esc", 27),
	KEY_SPACE("Space", 32),
	KEY_PAGE_UP("PageUp", 33, 63276),
	KEY_PAGE_DOWN("PageDown", 34, 63277),
	KEY_END("End", 35, 63275),
	KEY_HOME("Home", 36, 63273),
	KEY_LEFT("Left", 37, 63234),
	KEY_UP("Up", 38, 63232),
	KEY_RIGHT("Right", 39, 63235),
	KEY_DOWN("Down", 40, 63233),
	KEY_PRINT_SCRN("PrintScrn", 44),
	KEY_INSERT("Insert", 45, 63302),
	KEY_DELETE("Delete", 46, 63272),
	KEY_0("0", 48, 96),
	KEY_1("1", 49, 97),
	KEY_2("2", 50, 98),
	KEY_3("3", 51, 99),
	KEY_4("4", 52, 100),
	KEY_5("5", 53, 101),
	KEY_6("6", 54, 102),
	KEY_7("7", 55, 103),
	KEY_8("8", 56, 104),
	KEY_9("9", 57, 105),
	KEY_SEMICOLON(";", 59, 186),
	KEY_EQUAL("=", 61, 107, 187),
	KEY_A("A", 65),
	KEY_B("B", 66),
	KEY_C("C", 67),
	KEY_D("D", 68),
	KEY_E("E", 69),
	KEY_F("F", 70),
	KEY_G("G", 71),
	KEY_H("H", 72),
	KEY_I("I", 73),
	KEY_J("J", 74),
	KEY_K("K", 75),
	KEY_L("L", 76),
	KEY_M("M", 77),
	KEY_N("N", 78),
	KEY_O("O", 79),
	KEY_P("P", 80),
	KEY_Q("Q", 81),
	KEY_R("R", 82),
	KEY_S("S", 83),
	KEY_T("T", 84),
	KEY_U("U", 85),
	KEY_V("V", 86),
	KEY_W("W", 87),
	KEY_X("X", 88),
	KEY_Y("Y", 89),
	KEY_Z("Z", 90),
	KEY_MOD("Mod", 91, 92, 93),
	KEY_ASTERIX("*", 106),
	KEY_MINUS("-", 109, 173, 189),
	KEY_DOT(".", 110, 190),
	KEY_FORWARD_SLASH("/", 111, 191),
	KEY_F1("F1", 112, 63236),
	KEY_F2("F2", 113, 63237),
	KEY_F3("F3", 114, 63238),
	KEY_F4("F4", 115, 63239),
	KEY_F5("F5", 116, 63240),
	KEY_F6("F6", 117, 63241),
	KEY_F7("F7", 118, 63242),
	KEY_F8("F8", 119, 63243),
	KEY_F9("F9", 120, 63244),
	KEY_F10("F10", 121, 63245),
	KEY_F11("F11", 122, 63246),
	KEY_F12("F12", 123, 63247),
	KEY_SCROLL_LOCK("ScrollLock", 145),
	KEY_COMMA(",", 188),
	KEY_APOSTROPHE("`", 192),
	KEY_OPEN_SQUARE_BRACKET("[", 219),
	KEY_BACK_SLASH("\\", 220),
	KEY_CLOSE_SQUARE_BRACKET("]", 221),
	KEY_SINGLE_QUOTE("'", 222),
	CLICK_LEFT("LeftClick"),
	CLICK_MIDDLE("MiddleClick"),
	CLICK_RIGHT("RightClick"),
	CLICK_DOUBLE_LEFT("LeftDoubleClick"),
	CLICK_DOUBLE_MIDDLE("MiddleDoubleClick"),
	CLICK_DOUBLE_RIGHT("RightDoubleClick"),
	CLICK_TRIPLE_LEFT("LeftTripleClick"),
	CLICK_TRIPLE_MIDDLE("MiddleTripleClick"),
	CLICK_TRIPLE_RIGHT("RightTripleClick");

	private final List<Integer> codes = new ArrayList<>();

	private final String value;

	/**
	 * @param code
	 * @param value
	 */
	private KeyName(String value, int... codes) {
		this.value = value;
		if (codes != null && codes.length > 0) {
			for (int code : codes) {
				this.codes.add(code);
			}
		}
	}

	/**
	 * FIXME
	 * 
	 * @return
	 */
	public List<Integer> code() {
		return Collections.unmodifiableList(codes);
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
