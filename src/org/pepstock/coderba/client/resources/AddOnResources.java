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
package org.pepstock.coderba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CodeMirror code editor and other java script codes, always needed to CODERBA.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AddOnResources extends ClientBundle {

	// static reference of this resource
	public static final AddOnResources INSTANCE = GWT.create(AddOnResources.class);

	/**
 * Returns the java script source code for Comment.
 * @return <code>comment.js</code> code for Comment
 */
 @Source("/js/addon/comment/comment.js")
 TextResource comment();

/**
 * Returns the java script source code for Continuecomment.
 * @return <code>continuecomment.js</code> code for Continuecomment
 */
 @Source("/js/addon/comment/continuecomment.js")
 TextResource continuecomment();

/**
 * Returns the CSS source code for Dialog.
 * @return <code>dialog.css</code> CSS code for Dialog
 */
 @Source("/js/addon/dialog/dialog.css")
 TextResource dialogCss();

/**
 * Returns the java script source code for Dialog.
 * @return <code>dialog.js</code> code for Dialog
 */
 @Source("/js/addon/dialog/dialog.js")
 TextResource dialog();

/**
 * Returns the java script source code for Autorefresh.
 * @return <code>autorefresh.js</code> code for Autorefresh
 */
 @Source("/js/addon/display/autorefresh.js")
 TextResource autorefresh();

/**
 * Returns the CSS source code for Fullscreen.
 * @return <code>fullscreen.css</code> CSS code for Fullscreen
 */
 @Source("/js/addon/display/fullscreen.css")
 TextResource fullscreenCss();

/**
 * Returns the java script source code for Fullscreen.
 * @return <code>fullscreen.js</code> code for Fullscreen
 */
 @Source("/js/addon/display/fullscreen.js")
 TextResource fullscreen();

/**
 * Returns the java script source code for Panel.
 * @return <code>panel.js</code> code for Panel
 */
 @Source("/js/addon/display/panel.js")
 TextResource panel();

/**
 * Returns the java script source code for Placeholder.
 * @return <code>placeholder.js</code> code for Placeholder
 */
 @Source("/js/addon/display/placeholder.js")
 TextResource placeholder();

/**
 * Returns the java script source code for Rulers.
 * @return <code>rulers.js</code> code for Rulers
 */
 @Source("/js/addon/display/rulers.js")
 TextResource rulers();

/**
 * Returns the java script source code for Closebrackets.
 * @return <code>closebrackets.js</code> code for Closebrackets
 */
 @Source("/js/addon/edit/closebrackets.js")
 TextResource closebrackets();

/**
 * Returns the java script source code for Closetag.
 * @return <code>closetag.js</code> code for Closetag
 */
 @Source("/js/addon/edit/closetag.js")
 TextResource closetag();

/**
 * Returns the java script source code for Continuelist.
 * @return <code>continuelist.js</code> code for Continuelist
 */
 @Source("/js/addon/edit/continuelist.js")
 TextResource continuelist();

/**
 * Returns the java script source code for Matchbrackets.
 * @return <code>matchbrackets.js</code> code for Matchbrackets
 */
 @Source("/js/addon/edit/matchbrackets.js")
 TextResource matchbrackets();

/**
 * Returns the java script source code for Matchtags.
 * @return <code>matchtags.js</code> code for Matchtags
 */
 @Source("/js/addon/edit/matchtags.js")
 TextResource matchtags();

/**
 * Returns the java script source code for Trailingspace.
 * @return <code>trailingspace.js</code> code for Trailingspace
 */
 @Source("/js/addon/edit/trailingspace.js")
 TextResource trailingspace();

/**
 * Returns the java script source code for BraceFold.
 * @return <code>brace-fold.js</code> code for BraceFold
 */
 @Source("/js/addon/fold/brace-fold.js")
 TextResource braceFold();

/**
 * Returns the java script source code for CommentFold.
 * @return <code>comment-fold.js</code> code for CommentFold
 */
 @Source("/js/addon/fold/comment-fold.js")
 TextResource commentFold();

/**
 * Returns the java script source code for Foldcode.
 * @return <code>foldcode.js</code> code for Foldcode
 */
 @Source("/js/addon/fold/foldcode.js")
 TextResource foldcode();

/**
 * Returns the CSS source code for Foldgutter.
 * @return <code>foldgutter.css</code> CSS code for Foldgutter
 */
 @Source("/js/addon/fold/foldgutter.css")
 TextResource foldgutterCss();

/**
 * Returns the java script source code for Foldgutter.
 * @return <code>foldgutter.js</code> code for Foldgutter
 */
 @Source("/js/addon/fold/foldgutter.js")
 TextResource foldgutter();

/**
 * Returns the java script source code for IndentFold.
 * @return <code>indent-fold.js</code> code for IndentFold
 */
 @Source("/js/addon/fold/indent-fold.js")
 TextResource indentFold();

/**
 * Returns the java script source code for MarkdownFold.
 * @return <code>markdown-fold.js</code> code for MarkdownFold
 */
 @Source("/js/addon/fold/markdown-fold.js")
 TextResource markdownFold();

/**
 * Returns the java script source code for XmlFold.
 * @return <code>xml-fold.js</code> code for XmlFold
 */
 @Source("/js/addon/fold/xml-fold.js")
 TextResource xmlFold();

/**
 * Returns the java script source code for AnywordHint.
 * @return <code>anyword-hint.js</code> code for AnywordHint
 */
 @Source("/js/addon/hint/anyword-hint.js")
 TextResource anywordHint();

/**
 * Returns the java script source code for CssHint.
 * @return <code>css-hint.js</code> code for CssHint
 */
 @Source("/js/addon/hint/css-hint.js")
 TextResource cssHint();

/**
 * Returns the java script source code for HtmlHint.
 * @return <code>html-hint.js</code> code for HtmlHint
 */
 @Source("/js/addon/hint/html-hint.js")
 TextResource htmlHint();

/**
 * Returns the java script source code for JavascriptHint.
 * @return <code>javascript-hint.js</code> code for JavascriptHint
 */
 @Source("/js/addon/hint/javascript-hint.js")
 TextResource javascriptHint();

/**
 * Returns the CSS source code for ShowHint.
 * @return <code>show-hint.css</code> CSS code for ShowHint
 */
 @Source("/js/addon/hint/show-hint.css")
 TextResource showHintCss();

/**
 * Returns the java script source code for ShowHint.
 * @return <code>show-hint.js</code> code for ShowHint
 */
 @Source("/js/addon/hint/show-hint.js")
 TextResource showHint();

/**
 * Returns the java script source code for SqlHint.
 * @return <code>sql-hint.js</code> code for SqlHint
 */
 @Source("/js/addon/hint/sql-hint.js")
 TextResource sqlHint();

/**
 * Returns the java script source code for XmlHint.
 * @return <code>xml-hint.js</code> code for XmlHint
 */
 @Source("/js/addon/hint/xml-hint.js")
 TextResource xmlHint();

/**
 * Returns the java script source code for CoffeescriptLint.
 * @return <code>coffeescript-lint.js</code> code for CoffeescriptLint
 */
 @Source("/js/addon/lint/coffeescript-lint.js")
 TextResource coffeescriptLint();

/**
 * Returns the java script source code for CssLint.
 * @return <code>css-lint.js</code> code for CssLint
 */
 @Source("/js/addon/lint/css-lint.js")
 TextResource cssLint();

/**
 * Returns the java script source code for HtmlLint.
 * @return <code>html-lint.js</code> code for HtmlLint
 */
 @Source("/js/addon/lint/html-lint.js")
 TextResource htmlLint();

/**
 * Returns the java script source code for JavascriptLint.
 * @return <code>javascript-lint.js</code> code for JavascriptLint
 */
 @Source("/js/addon/lint/javascript-lint.js")
 TextResource javascriptLint();

/**
 * Returns the java script source code for JsonLint.
 * @return <code>json-lint.js</code> code for JsonLint
 */
 @Source("/js/addon/lint/json-lint.js")
 TextResource jsonLint();

/**
 * Returns the CSS source code for Lint.
 * @return <code>lint.css</code> CSS code for Lint
 */
 @Source("/js/addon/lint/lint.css")
 TextResource lintCss();

/**
 * Returns the java script source code for Lint.
 * @return <code>lint.js</code> code for Lint
 */
 @Source("/js/addon/lint/lint.js")
 TextResource lint();

/**
 * Returns the java script source code for YamlLint.
 * @return <code>yaml-lint.js</code> code for YamlLint
 */
 @Source("/js/addon/lint/yaml-lint.js")
 TextResource yamlLint();

/**
 * Returns the CSS source code for Merge.
 * @return <code>merge.css</code> CSS code for Merge
 */
 @Source("/js/addon/merge/merge.css")
 TextResource mergeCss();

/**
 * Returns the java script source code for Merge.
 * @return <code>merge.js</code> code for Merge
 */
 @Source("/js/addon/merge/merge.js")
 TextResource merge();

/**
 * Returns the java script source code for Loadmode.
 * @return <code>loadmode.js</code> code for Loadmode
 */
 @Source("/js/addon/mode/loadmode.js")
 TextResource loadmode();

/**
 * Returns the java script source code for Multiplex.
 * @return <code>multiplex.js</code> code for Multiplex
 */
 @Source("/js/addon/mode/multiplex.js")
 TextResource multiplex();

/**
 * Returns the java script source code for Overlay.
 * @return <code>overlay.js</code> code for Overlay
 */
 @Source("/js/addon/mode/overlay.js")
 TextResource overlay();

/**
 * Returns the java script source code for Simple.
 * @return <code>simple.js</code> code for Simple
 */
 @Source("/js/addon/mode/simple.js")
 TextResource simple();

/**
 * Returns the java script source code for Colorize.
 * @return <code>colorize.js</code> code for Colorize
 */
 @Source("/js/addon/runmode/colorize.js")
 TextResource colorize();

/**
 * Returns the java script source code for RunmodeStandalone.
 * @return <code>runmode-standalone.js</code> code for RunmodeStandalone
 */
 @Source("/js/addon/runmode/runmode-standalone.js")
 TextResource runmodeStandalone();

/**
 * Returns the java script source code for Runmode.
 * @return <code>runmode.js</code> code for Runmode
 */
 @Source("/js/addon/runmode/runmode.js")
 TextResource runmode();

/**
 * Returns the java script source code for Runmodenode.
 * @return <code>runmode.node.js</code> code for Runmodenode
 */
 @Source("/js/addon/runmode/runmode.node.js")
 TextResource runmodenode();

/**
 * Returns the java script source code for Annotatescrollbar.
 * @return <code>annotatescrollbar.js</code> code for Annotatescrollbar
 */
 @Source("/js/addon/scroll/annotatescrollbar.js")
 TextResource annotatescrollbar();

/**
 * Returns the java script source code for Scrollpastend.
 * @return <code>scrollpastend.js</code> code for Scrollpastend
 */
 @Source("/js/addon/scroll/scrollpastend.js")
 TextResource scrollpastend();

/**
 * Returns the CSS source code for Simplescrollbars.
 * @return <code>simplescrollbars.css</code> CSS code for Simplescrollbars
 */
 @Source("/js/addon/scroll/simplescrollbars.css")
 TextResource simplescrollbarsCss();

/**
 * Returns the java script source code for Simplescrollbars.
 * @return <code>simplescrollbars.js</code> code for Simplescrollbars
 */
 @Source("/js/addon/scroll/simplescrollbars.js")
 TextResource simplescrollbars();

/**
 * Returns the java script source code for JumpToLine.
 * @return <code>jump-to-line.js</code> code for JumpToLine
 */
 @Source("/js/addon/search/jump-to-line.js")
 TextResource jumpToLine();

/**
 * Returns the java script source code for MatchHighlighter.
 * @return <code>match-highlighter.js</code> code for MatchHighlighter
 */
 @Source("/js/addon/search/match-highlighter.js")
 TextResource matchHighlighter();

/**
 * Returns the CSS source code for Matchesonscrollbar.
 * @return <code>matchesonscrollbar.css</code> CSS code for Matchesonscrollbar
 */
 @Source("/js/addon/search/matchesonscrollbar.css")
 TextResource matchesonscrollbarCss();

/**
 * Returns the java script source code for Matchesonscrollbar.
 * @return <code>matchesonscrollbar.js</code> code for Matchesonscrollbar
 */
 @Source("/js/addon/search/matchesonscrollbar.js")
 TextResource matchesonscrollbar();

/**
 * Returns the java script source code for Search.
 * @return <code>search.js</code> code for Search
 */
 @Source("/js/addon/search/search.js")
 TextResource search();

/**
 * Returns the java script source code for Searchcursor.
 * @return <code>searchcursor.js</code> code for Searchcursor
 */
 @Source("/js/addon/search/searchcursor.js")
 TextResource searchcursor();

/**
 * Returns the java script source code for ActiveLine.
 * @return <code>active-line.js</code> code for ActiveLine
 */
 @Source("/js/addon/selection/active-line.js")
 TextResource activeLine();

/**
 * Returns the java script source code for MarkSelection.
 * @return <code>mark-selection.js</code> code for MarkSelection
 */
 @Source("/js/addon/selection/mark-selection.js")
 TextResource markSelection();

/**
 * Returns the java script source code for SelectionPointer.
 * @return <code>selection-pointer.js</code> code for SelectionPointer
 */
 @Source("/js/addon/selection/selection-pointer.js")
 TextResource selectionPointer();

/**
 * Returns the CSS source code for Tern.
 * @return <code>tern.css</code> CSS code for Tern
 */
 @Source("/js/addon/tern/tern.css")
 TextResource ternCss();

/**
 * Returns the java script source code for Tern.
 * @return <code>tern.js</code> code for Tern
 */
 @Source("/js/addon/tern/tern.js")
 TextResource tern();

/**
 * Returns the java script source code for Worker.
 * @return <code>worker.js</code> code for Worker
 */
 @Source("/js/addon/tern/worker.js")
 TextResource worker();

/**
 * Returns the java script source code for Hardwrap.
 * @return <code>hardwrap.js</code> code for Hardwrap
 */
 @Source("/js/addon/wrap/hardwrap.js")
 TextResource hardwrap();



}
