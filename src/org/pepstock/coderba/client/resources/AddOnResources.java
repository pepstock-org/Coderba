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
 * Client bundle to reference CodeMirror code editor and other java script codes, always needed to Coderba.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface AddOnResources extends ClientBundle {

	// static reference of this resource
	public static final AddOnResources INSTANCE = GWT.create(AddOnResources.class);

	// ----------------------------
	// --- JavaScript Resources ---
	// ----------------------------
	
	/**
	 * Returns the java script source code for AddOnComment.
	 * 
	 * @return <code>comment.js</code> code for AddOnComment
	 */
	@Source("/js/addon/comment/comment.js")
	TextResource comment();

	/**
	 * Returns the java script source code for AddOnContinuecomment.
	 * 
	 * @return <code>continuecomment.js</code> code for AddOnContinuecomment
	 */
	@Source("/js/addon/comment/continuecomment.js")
	TextResource continuecomment();

	/**
	 * Returns the java script source code for AddOnDialog.
	 * 
	 * @return <code>dialog.js</code> code for AddOnDialog
	 */
	@Source("/js/addon/dialog/dialog.js")
	TextResource dialog();

	/**
	 * Returns the java script source code for AddOnAutorefresh.
	 * 
	 * @return <code>autorefresh.js</code> code for AddOnAutorefresh
	 */
	@Source("/js/addon/display/autorefresh.js")
	TextResource autorefresh();

	/**
	 * Returns the java script source code for AddOnFullscreen.
	 * 
	 * @return <code>fullscreen.js</code> code for AddOnFullscreen
	 */
	@Source("/js/addon/display/fullscreen.js")
	TextResource fullscreen();

	/**
	 * Returns the java script source code for AddOnPanel.
	 * 
	 * @return <code>panel.js</code> code for AddOnPanel
	 */
	@Source("/js/addon/display/panel.js")
	TextResource panel();

	/**
	 * Returns the java script source code for AddOnPlaceholder.
	 * 
	 * @return <code>placeholder.js</code> code for AddOnPlaceholder
	 */
	@Source("/js/addon/display/placeholder.js")
	TextResource placeholder();

	/**
	 * Returns the java script source code for AddOnRulers.
	 * 
	 * @return <code>rulers.js</code> code for AddOnRulers
	 */
	@Source("/js/addon/display/rulers.js")
	TextResource rulers();

	/**
	 * Returns the java script source code for AddOnClosebrackets.
	 * 
	 * @return <code>closebrackets.js</code> code for AddOnClosebrackets
	 */
	@Source("/js/addon/edit/closebrackets.js")
	TextResource closebrackets();

	/**
	 * Returns the java script source code for AddOnClosetag.
	 * 
	 * @return <code>closetag.js</code> code for AddOnClosetag
	 */
	@Source("/js/addon/edit/closetag.js")
	TextResource closetag();

	/**
	 * Returns the java script source code for AddOnContinuelist.
	 * 
	 * @return <code>continuelist.js</code> code for AddOnContinuelist
	 */
	@Source("/js/addon/edit/continuelist.js")
	TextResource continuelist();

	/**
	 * Returns the java script source code for AddOnMatchbrackets.
	 * 
	 * @return <code>matchbrackets.js</code> code for AddOnMatchbrackets
	 */
	@Source("/js/addon/edit/matchbrackets.js")
	TextResource matchbrackets();

	/**
	 * Returns the java script source code for AddOnMatchtags.
	 * 
	 * @return <code>matchtags.js</code> code for AddOnMatchtags
	 */
	@Source("/js/addon/edit/matchtags.js")
	TextResource matchtags();

	/**
	 * Returns the java script source code for AddOnTrailingspace.
	 * 
	 * @return <code>trailingspace.js</code> code for AddOnTrailingspace
	 */
	@Source("/js/addon/edit/trailingspace.js")
	TextResource trailingspace();

	/**
	 * Returns the java script source code for AddOnBraceFold.
	 * 
	 * @return <code>brace-fold.js</code> code for AddOnBraceFold
	 */
	@Source("/js/addon/fold/brace-fold.js")
	TextResource braceFold();

	/**
	 * Returns the java script source code for AddOnCommentFold.
	 * 
	 * @return <code>comment-fold.js</code> code for AddOnCommentFold
	 */
	@Source("/js/addon/fold/comment-fold.js")
	TextResource commentFold();

	/**
	 * Returns the java script source code for AddOnFoldcode.
	 * 
	 * @return <code>foldcode.js</code> code for AddOnFoldcode
	 */
	@Source("/js/addon/fold/foldcode.js")
	TextResource foldcode();

	/**
	 * Returns the java script source code for AddOnFoldgutter.
	 * 
	 * @return <code>foldgutter.js</code> code for AddOnFoldgutter
	 */
	@Source("/js/addon/fold/foldgutter.js")
	TextResource foldgutter();

	/**
	 * Returns the java script source code for AddOnIndentFold.
	 * 
	 * @return <code>indent-fold.js</code> code for AddOnIndentFold
	 */
	@Source("/js/addon/fold/indent-fold.js")
	TextResource indentFold();

	/**
	 * Returns the java script source code for AddOnMarkdownFold.
	 * 
	 * @return <code>markdown-fold.js</code> code for AddOnMarkdownFold
	 */
	@Source("/js/addon/fold/markdown-fold.js")
	TextResource markdownFold();

	/**
	 * Returns the java script source code for AddOnXmlFold.
	 * 
	 * @return <code>xml-fold.js</code> code for AddOnXmlFold
	 */
	@Source("/js/addon/fold/xml-fold.js")
	TextResource xmlFold();

	/**
	 * Returns the java script source code for AddOnAnywordHint.
	 * 
	 * @return <code>anyword-hint.js</code> code for AddOnAnywordHint
	 */
	@Source("/js/addon/hint/anyword-hint.js")
	TextResource anywordHint();

	/**
	 * Returns the java script source code for AddOnCssHint.
	 * 
	 * @return <code>css-hint.js</code> code for AddOnCssHint
	 */
	@Source("/js/addon/hint/css-hint.js")
	TextResource cssHint();

	/**
	 * Returns the java script source code for AddOnHtmlHint.
	 * 
	 * @return <code>html-hint.js</code> code for AddOnHtmlHint
	 */
	@Source("/js/addon/hint/html-hint.js")
	TextResource htmlHint();

	/**
	 * Returns the java script source code for AddOnJavascriptHint.
	 * 
	 * @return <code>javascript-hint.js</code> code for AddOnJavascriptHint
	 */
	@Source("/js/addon/hint/javascript-hint.js")
	TextResource javascriptHint();

	/**
	 * Returns the java script source code for AddOnShowHint.
	 * 
	 * @return <code>show-hint.js</code> code for AddOnShowHint
	 */
	@Source("/js/addon/hint/show-hint.js")
	TextResource showHint();

	/**
	 * Returns the java script source code for AddOnSqlHint.
	 * 
	 * @return <code>sql-hint.js</code> code for AddOnSqlHint
	 */
	@Source("/js/addon/hint/sql-hint.js")
	TextResource sqlHint();

	/**
	 * Returns the java script source code for AddOnXmlHint.
	 * 
	 * @return <code>xml-hint.js</code> code for AddOnXmlHint
	 */
	@Source("/js/addon/hint/xml-hint.js")
	TextResource xmlHint();

	/**
	 * Returns the java script source code for AddOnCoffeescriptLint.
	 * 
	 * @return <code>coffeescript-lint.js</code> code for AddOnCoffeescriptLint
	 */
	@Source("/js/addon/lint/coffeescript-lint.js")
	TextResource coffeescriptLint();

	/**
	 * Returns the java script source code for AddOnCssLint.
	 * 
	 * @return <code>css-lint.js</code> code for AddOnCssLint
	 */
	@Source("/js/addon/lint/css-lint.js")
	TextResource cssLint();

	/**
	 * Returns the java script source code for AddOnHtmlLint.
	 * 
	 * @return <code>html-lint.js</code> code for AddOnHtmlLint
	 */
	@Source("/js/addon/lint/html-lint.js")
	TextResource htmlLint();

	/**
	 * Returns the java script source code for AddOnJavascriptLint.
	 * 
	 * @return <code>javascript-lint.js</code> code for AddOnJavascriptLint
	 */
	@Source("/js/addon/lint/javascript-lint.js")
	TextResource javascriptLint();

	/**
	 * Returns the java script source code for AddOnJsonLint.
	 * 
	 * @return <code>json-lint.js</code> code for AddOnJsonLint
	 */
	@Source("/js/addon/lint/json-lint.js")
	TextResource jsonLint();

	/**
	 * Returns the java script source code for AddOnLint.
	 * 
	 * @return <code>lint.js</code> code for AddOnLint
	 */
	@Source("/js/addon/lint/lint.js")
	TextResource lint();

	/**
	 * Returns the java script source code for AddOnYamlLint.
	 * 
	 * @return <code>yaml-lint.js</code> code for AddOnYamlLint
	 */
	@Source("/js/addon/lint/yaml-lint.js")
	TextResource yamlLint();

	/**
	 * Returns the java script source code for AddOnMerge.
	 * 
	 * @return <code>merge.js</code> code for AddOnMerge
	 */
	@Source("/js/addon/merge/merge.js")
	TextResource merge();

	/**
	 * Returns the java script source code for AddOnLoadmode.
	 * 
	 * @return <code>loadmode.js</code> code for AddOnLoadmode
	 */
	@Source("/js/addon/mode/loadmode.js")
	TextResource loadmode();

	/**
	 * Returns the java script source code for AddOnMultiplex.
	 * 
	 * @return <code>multiplex.js</code> code for AddOnMultiplex
	 */
	@Source("/js/addon/mode/multiplex.js")
	TextResource multiplex();

	/**
	 * Returns the java script source code for AddOnOverlay.
	 * 
	 * @return <code>overlay.js</code> code for AddOnOverlay
	 */
	@Source("/js/addon/mode/overlay.js")
	TextResource overlay();

	/**
	 * Returns the java script source code for AddOnSimple.
	 * 
	 * @return <code>simple.js</code> code for AddOnSimple
	 */
	@Source("/js/addon/mode/simple.js")
	TextResource simple();

	/**
	 * Returns the java script source code for AddOnColorize.
	 * 
	 * @return <code>colorize.js</code> code for AddOnColorize
	 */
	@Source("/js/addon/runmode/colorize.js")
	TextResource colorize();

	/**
	 * Returns the java script source code for AddOnRunmodeStandalone.
	 * 
	 * @return <code>runmode-standalone.js</code> code for AddOnRunmodeStandalone
	 */
	@Source("/js/addon/runmode/runmode-standalone.js")
	TextResource runmodeStandalone();

	/**
	 * Returns the java script source code for AddOnRunmode.
	 * 
	 * @return <code>runmode.js</code> code for AddOnRunmode
	 */
	@Source("/js/addon/runmode/runmode.js")
	TextResource runmode();

	/**
	 * Returns the java script source code for AddOnRunmodenode.
	 * 
	 * @return <code>runmode.node.js</code> code for AddOnRunmodenode
	 */
	@Source("/js/addon/runmode/runmode.node.js")
	TextResource runmodenode();

	/**
	 * Returns the java script source code for AddOnAnnotatescrollbar.
	 * 
	 * @return <code>annotatescrollbar.js</code> code for AddOnAnnotatescrollbar
	 */
	@Source("/js/addon/scroll/annotatescrollbar.js")
	TextResource annotatescrollbar();

	/**
	 * Returns the java script source code for AddOnScrollpastend.
	 * 
	 * @return <code>scrollpastend.js</code> code for AddOnScrollpastend
	 */
	@Source("/js/addon/scroll/scrollpastend.js")
	TextResource scrollpastend();

	/**
	 * Returns the java script source code for AddOnSimplescrollbars.
	 * 
	 * @return <code>simplescrollbars.js</code> code for AddOnSimplescrollbars
	 */
	@Source("/js/addon/scroll/simplescrollbars.js")
	TextResource simplescrollbars();

	/**
	 * Returns the java script source code for AddOnJumpToLine.
	 * 
	 * @return <code>jump-to-line.js</code> code for AddOnJumpToLine
	 */
	@Source("/js/addon/search/jump-to-line.js")
	TextResource jumpToLine();

	/**
	 * Returns the java script source code for AddOnMatchHighlighter.
	 * 
	 * @return <code>match-highlighter.js</code> code for AddOnMatchHighlighter
	 */
	@Source("/js/addon/search/match-highlighter.js")
	TextResource matchHighlighter();

	/**
	 * Returns the java script source code for AddOnMatchesonscrollbar.
	 * 
	 * @return <code>matchesonscrollbar.js</code> code for AddOnMatchesonscrollbar
	 */
	@Source("/js/addon/search/matchesonscrollbar.js")
	TextResource matchesonscrollbar();

	/**
	 * Returns the java script source code for AddOnSearch.
	 * 
	 * @return <code>search.js</code> code for AddOnSearch
	 */
	@Source("/js/addon/search/search.js")
	TextResource search();

	/**
	 * Returns the java script source code for AddOnSearchcursor.
	 * 
	 * @return <code>searchcursor.js</code> code for AddOnSearchcursor
	 */
	@Source("/js/addon/search/searchcursor.js")
	TextResource searchcursor();

	/**
	 * Returns the java script source code for AddOnActiveLine.
	 * 
	 * @return <code>active-line.js</code> code for AddOnActiveLine
	 */
	@Source("/js/addon/selection/active-line.js")
	TextResource activeLine();

	/**
	 * Returns the java script source code for AddOnMarkSelection.
	 * 
	 * @return <code>mark-selection.js</code> code for AddOnMarkSelection
	 */
	@Source("/js/addon/selection/mark-selection.js")
	TextResource markSelection();

	/**
	 * Returns the java script source code for AddOnSelectionPointer.
	 * 
	 * @return <code>selection-pointer.js</code> code for AddOnSelectionPointer
	 */
	@Source("/js/addon/selection/selection-pointer.js")
	TextResource selectionPointer();

	/**
	 * Returns the java script source code for AddOnTern.
	 * 
	 * @return <code>tern.js</code> code for AddOnTern
	 */
	@Source("/js/addon/tern/tern.js")
	TextResource tern();

	/**
	 * Returns the java script source code for AddOnWorker.
	 * 
	 * @return <code>worker.js</code> code for AddOnWorker
	 */
	@Source("/js/addon/tern/worker.js")
	TextResource worker();

	/**
	 * Returns the java script source code for AddOnHardwrap.
	 * 
	 * @return <code>hardwrap.js</code> code for AddOnHardwrap
	 */
	@Source("/js/addon/wrap/hardwrap.js")
	TextResource hardwrap();
	
	// ----------------------------
	// --- CSS Resources        ---
	// ----------------------------
	
	/**
	 * Returns the CSS source code for AddOnDialog.
	 * 
	 * @return <code>dialog.css</code> CSS code for AddOnDialog
	 */
	@Source("/js/addon/dialog/dialog.css")
	TextResource dialogCss();
	
	/**
	 * Returns the CSS source code for AddOnFullscreen.
	 * 
	 * @return <code>fullscreen.css</code> CSS code for AddOnFullscreen
	 */
	@Source("/js/addon/display/fullscreen.css")
	TextResource fullscreenCss();
	
	/**
	 * Returns the CSS source code for AddOnFoldgutter.
	 * 
	 * @return <code>foldgutter.css</code> CSS code for AddOnFoldgutter
	 */
	@Source("/js/addon/fold/foldgutter.css")
	TextResource foldgutterCss();
	
	/**
	 * Returns the CSS source code for AddOnShowHint.
	 * 
	 * @return <code>show-hint.css</code> CSS code for AddOnShowHint
	 */
	@Source("/js/addon/hint/show-hint.css")
	TextResource showHintCss();
	
	/**
	 * Returns the CSS source code for AddOnLint.
	 * 
	 * @return <code>lint.css</code> CSS code for AddOnLint
	 */
	@Source("/js/addon/lint/lint.css")
	TextResource lintCss();
	
	/**
	 * Returns the CSS source code for AddOnMerge.
	 * 
	 * @return <code>merge.css</code> CSS code for AddOnMerge
	 */
	@Source("/js/addon/merge/merge.css")
	TextResource mergeCss();
	
	/**
	 * Returns the CSS source code for AddOnSimplescrollbars.
	 * 
	 * @return <code>simplescrollbars.css</code> CSS code for AddOnSimplescrollbars
	 */
	@Source("/js/addon/scroll/simplescrollbars.css")
	TextResource simplescrollbarsCss();
	
	/**
	 * Returns the CSS source code for AddOnMatchesonscrollbar.
	 * 
	 * @return <code>matchesonscrollbar.css</code> CSS code for AddOnMatchesonscrollbar
	 */
	@Source("/js/addon/search/matchesonscrollbar.css")
	TextResource matchesonscrollbarCss();
	
	/**
	 * Returns the CSS source code for AddOnTern.
	 * 
	 * @return <code>tern.css</code> CSS code for AddOnTern
	 */
	@Source("/js/addon/tern/tern.css")
	TextResource ternCss();
	
}