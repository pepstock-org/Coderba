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
 * Client bundle to reference MODE CodeMirror instances, always needed to CODERBA.
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface ModeResources extends ClientBundle {

	// static reference of this resource
	public static final ModeResources INSTANCE = GWT.create(ModeResources.class);

	// ----------------------------
	// --- JavaScript Resources ---
	// ----------------------------

	/**
	 * Returns the java script source code for ModeApl.
	 * 
	 * @return <code>apl.js</code> code for ModeApl
	 */
	@Source("/js/mode/apl/apl.js")
	TextResource apl();

	/**
	 * Returns the java script source code for ModeAsciiarmor.
	 * 
	 * @return <code>asciiarmor.js</code> code for ModeAsciiarmor
	 */
	@Source("/js/mode/asciiarmor/asciiarmor.js")
	TextResource asciiarmor();

	/**
	 * Returns the java script source code for ModeAsn1.
	 * 
	 * @return <code>asn.1.js</code> code for ModeAsn1
	 */
	@Source("/js/mode/asn.1/asn.1.js")
	TextResource asn1();

	/**
	 * Returns the java script source code for ModeAsterisk.
	 * 
	 * @return <code>asterisk.js</code> code for ModeAsterisk
	 */
	@Source("/js/mode/asterisk/asterisk.js")
	TextResource asterisk();

	/**
	 * Returns the java script source code for ModeBrainfuck.
	 * 
	 * @return <code>brainfuck.js</code> code for ModeBrainfuck
	 */
	@Source("/js/mode/brainfuck/brainfuck.js")
	TextResource brainfuck();

	/**
	 * Returns the java script source code for ModeClike.
	 * 
	 * @return <code>clike.js</code> code for ModeClike
	 */
	@Source("/js/mode/clike/clike.js")
	TextResource clike();

	/**
	 * Returns the java script source code for ModeClojure.
	 * 
	 * @return <code>clojure.js</code> code for ModeClojure
	 */
	@Source("/js/mode/clojure/clojure.js")
	TextResource clojure();

	/**
	 * Returns the java script source code for ModeCmake.
	 * 
	 * @return <code>cmake.js</code> code for ModeCmake
	 */
	@Source("/js/mode/cmake/cmake.js")
	TextResource cmake();

	/**
	 * Returns the java script source code for ModeCobol.
	 * 
	 * @return <code>cobol.js</code> code for ModeCobol
	 */
	@Source("/js/mode/cobol/cobol.js")
	TextResource cobol();

	/**
	 * Returns the java script source code for ModeCoffeescript.
	 * 
	 * @return <code>coffeescript.js</code> code for ModeCoffeescript
	 */
	@Source("/js/mode/coffeescript/coffeescript.js")
	TextResource coffeescript();

	/**
	 * Returns the java script source code for ModeCommonlisp.
	 * 
	 * @return <code>commonlisp.js</code> code for ModeCommonlisp
	 */
	@Source("/js/mode/commonlisp/commonlisp.js")
	TextResource commonlisp();

	/**
	 * Returns the java script source code for ModeCrystal.
	 * 
	 * @return <code>crystal.js</code> code for ModeCrystal
	 */
	@Source("/js/mode/crystal/crystal.js")
	TextResource crystal();

	/**
	 * Returns the java script source code for ModeCss.
	 * 
	 * @return <code>css.js</code> code for ModeCss
	 */
	@Source("/js/mode/css/css.js")
	TextResource css();

	/**
	 * Returns the java script source code for ModeCypher.
	 * 
	 * @return <code>cypher.js</code> code for ModeCypher
	 */
	@Source("/js/mode/cypher/cypher.js")
	TextResource cypher();

	/**
	 * Returns the java script source code for ModeD.
	 * 
	 * @return <code>d.js</code> code for ModeD
	 */
	@Source("/js/mode/d/d.js")
	TextResource d();

	/**
	 * Returns the java script source code for ModeDart.
	 * 
	 * @return <code>dart.js</code> code for ModeDart
	 */
	@Source("/js/mode/dart/dart.js")
	TextResource dart();

	/**
	 * Returns the java script source code for ModeDiff.
	 * 
	 * @return <code>diff.js</code> code for ModeDiff
	 */
	@Source("/js/mode/diff/diff.js")
	TextResource diff();

	/**
	 * Returns the java script source code for ModeDjango.
	 * 
	 * @return <code>django.js</code> code for ModeDjango
	 */
	@Source("/js/mode/django/django.js")
	TextResource django();

	/**
	 * Returns the java script source code for ModeDockerfile.
	 * 
	 * @return <code>dockerfile.js</code> code for ModeDockerfile
	 */
	@Source("/js/mode/dockerfile/dockerfile.js")
	TextResource dockerfile();

	/**
	 * Returns the java script source code for ModeDtd.
	 * 
	 * @return <code>dtd.js</code> code for ModeDtd
	 */
	@Source("/js/mode/dtd/dtd.js")
	TextResource dtd();

	/**
	 * Returns the java script source code for ModeDylan.
	 * 
	 * @return <code>dylan.js</code> code for ModeDylan
	 */
	@Source("/js/mode/dylan/dylan.js")
	TextResource dylan();

	/**
	 * Returns the java script source code for ModeEbnf.
	 * 
	 * @return <code>ebnf.js</code> code for ModeEbnf
	 */
	@Source("/js/mode/ebnf/ebnf.js")
	TextResource ebnf();

	/**
	 * Returns the java script source code for ModeEcl.
	 * 
	 * @return <code>ecl.js</code> code for ModeEcl
	 */
	@Source("/js/mode/ecl/ecl.js")
	TextResource ecl();

	/**
	 * Returns the java script source code for ModeEiffel.
	 * 
	 * @return <code>eiffel.js</code> code for ModeEiffel
	 */
	@Source("/js/mode/eiffel/eiffel.js")
	TextResource eiffel();

	/**
	 * Returns the java script source code for ModeElm.
	 * 
	 * @return <code>elm.js</code> code for ModeElm
	 */
	@Source("/js/mode/elm/elm.js")
	TextResource elm();

	/**
	 * Returns the java script source code for ModeErlang.
	 * 
	 * @return <code>erlang.js</code> code for ModeErlang
	 */
	@Source("/js/mode/erlang/erlang.js")
	TextResource erlang();

	/**
	 * Returns the java script source code for ModeFactor.
	 * 
	 * @return <code>factor.js</code> code for ModeFactor
	 */
	@Source("/js/mode/factor/factor.js")
	TextResource factor();

	/**
	 * Returns the java script source code for ModeFcl.
	 * 
	 * @return <code>fcl.js</code> code for ModeFcl
	 */
	@Source("/js/mode/fcl/fcl.js")
	TextResource fcl();

	/**
	 * Returns the java script source code for ModeForth.
	 * 
	 * @return <code>forth.js</code> code for ModeForth
	 */
	@Source("/js/mode/forth/forth.js")
	TextResource forth();

	/**
	 * Returns the java script source code for ModeFortran.
	 * 
	 * @return <code>fortran.js</code> code for ModeFortran
	 */
	@Source("/js/mode/fortran/fortran.js")
	TextResource fortran();

	/**
	 * Returns the java script source code for ModeGas.
	 * 
	 * @return <code>gas.js</code> code for ModeGas
	 */
	@Source("/js/mode/gas/gas.js")
	TextResource gas();

	/**
	 * Returns the java script source code for ModeGfm.
	 * 
	 * @return <code>gfm.js</code> code for ModeGfm
	 */
	@Source("/js/mode/gfm/gfm.js")
	TextResource gfm();

	/**
	 * Returns the java script source code for ModeGherkin.
	 * 
	 * @return <code>gherkin.js</code> code for ModeGherkin
	 */
	@Source("/js/mode/gherkin/gherkin.js")
	TextResource gherkin();

	/**
	 * Returns the java script source code for ModeGo.
	 * 
	 * @return <code>go.js</code> code for ModeGo
	 */
	@Source("/js/mode/go/go.js")
	TextResource go();

	/**
	 * Returns the java script source code for ModeGroovy.
	 * 
	 * @return <code>groovy.js</code> code for ModeGroovy
	 */
	@Source("/js/mode/groovy/groovy.js")
	TextResource groovy();

	/**
	 * Returns the java script source code for ModeHaml.
	 * 
	 * @return <code>haml.js</code> code for ModeHaml
	 */
	@Source("/js/mode/haml/haml.js")
	TextResource haml();

	/**
	 * Returns the java script source code for ModeHandlebars.
	 * 
	 * @return <code>handlebars.js</code> code for ModeHandlebars
	 */
	@Source("/js/mode/handlebars/handlebars.js")
	TextResource handlebars();

	/**
	 * Returns the java script source code for ModeHaskell.
	 * 
	 * @return <code>haskell.js</code> code for ModeHaskell
	 */
	@Source("/js/mode/haskell/haskell.js")
	TextResource haskell();

	/**
	 * Returns the java script source code for ModeHaskellLiterate.
	 * 
	 * @return <code>haskell-literate.js</code> code for ModeHaskellLiterate
	 */
	@Source("/js/mode/haskell-literate/haskell-literate.js")
	TextResource haskellLiterate();

	/**
	 * Returns the java script source code for ModeHaxe.
	 * 
	 * @return <code>haxe.js</code> code for ModeHaxe
	 */
	@Source("/js/mode/haxe/haxe.js")
	TextResource haxe();

	/**
	 * Returns the java script source code for ModeHtmlembedded.
	 * 
	 * @return <code>htmlembedded.js</code> code for ModeHtmlembedded
	 */
	@Source("/js/mode/htmlembedded/htmlembedded.js")
	TextResource htmlembedded();

	/**
	 * Returns the java script source code for ModeHtmlmixed.
	 * 
	 * @return <code>htmlmixed.js</code> code for ModeHtmlmixed
	 */
	@Source("/js/mode/htmlmixed/htmlmixed.js")
	TextResource htmlmixed();

	/**
	 * Returns the java script source code for ModeHttp.
	 * 
	 * @return <code>http.js</code> code for ModeHttp
	 */
	@Source("/js/mode/http/http.js")
	TextResource http();

	/**
	 * Returns the java script source code for ModeIdl.
	 * 
	 * @return <code>idl.js</code> code for ModeIdl
	 */
	@Source("/js/mode/idl/idl.js")
	TextResource idl();

	/**
	 * Returns the java script source code for ModeJavascript.
	 * 
	 * @return <code>javascript.js</code> code for ModeJavascript
	 */
	@Source("/js/mode/javascript/javascript.js")
	TextResource javascript();

	/**
	 * Returns the java script source code for ModeJinja2.
	 * 
	 * @return <code>jinja2.js</code> code for ModeJinja2
	 */
	@Source("/js/mode/jinja2/jinja2.js")
	TextResource jinja2();

	/**
	 * Returns the java script source code for ModeJsx.
	 * 
	 * @return <code>jsx.js</code> code for ModeJsx
	 */
	@Source("/js/mode/jsx/jsx.js")
	TextResource jsx();

	/**
	 * Returns the java script source code for ModeJulia.
	 * 
	 * @return <code>julia.js</code> code for ModeJulia
	 */
	@Source("/js/mode/julia/julia.js")
	TextResource julia();

	/**
	 * Returns the java script source code for ModeLivescript.
	 * 
	 * @return <code>livescript.js</code> code for ModeLivescript
	 */
	@Source("/js/mode/livescript/livescript.js")
	TextResource livescript();

	/**
	 * Returns the java script source code for ModeLua.
	 * 
	 * @return <code>lua.js</code> code for ModeLua
	 */
	@Source("/js/mode/lua/lua.js")
	TextResource lua();

	/**
	 * Returns the java script source code for ModeMarkdown.
	 * 
	 * @return <code>markdown.js</code> code for ModeMarkdown
	 */
	@Source("/js/mode/markdown/markdown.js")
	TextResource markdown();

	/**
	 * Returns the java script source code for ModeMathematica.
	 * 
	 * @return <code>mathematica.js</code> code for ModeMathematica
	 */
	@Source("/js/mode/mathematica/mathematica.js")
	TextResource mathematica();

	/**
	 * Returns the java script source code for ModeMbox.
	 * 
	 * @return <code>mbox.js</code> code for ModeMbox
	 */
	@Source("/js/mode/mbox/mbox.js")
	TextResource mbox();

	/**
	 * Returns the java script source code for ModeMirc.
	 * 
	 * @return <code>mirc.js</code> code for ModeMirc
	 */
	@Source("/js/mode/mirc/mirc.js")
	TextResource mirc();

	/**
	 * Returns the java script source code for ModeMllike.
	 * 
	 * @return <code>mllike.js</code> code for ModeMllike
	 */
	@Source("/js/mode/mllike/mllike.js")
	TextResource mllike();

	/**
	 * Returns the java script source code for ModeModelica.
	 * 
	 * @return <code>modelica.js</code> code for ModeModelica
	 */
	@Source("/js/mode/modelica/modelica.js")
	TextResource modelica();

	/**
	 * Returns the java script source code for ModeMscgen.
	 * 
	 * @return <code>mscgen.js</code> code for ModeMscgen
	 */
	@Source("/js/mode/mscgen/mscgen.js")
	TextResource mscgen();

	/**
	 * Returns the java script source code for ModeMumps.
	 * 
	 * @return <code>mumps.js</code> code for ModeMumps
	 */
	@Source("/js/mode/mumps/mumps.js")
	TextResource mumps();

	/**
	 * Returns the java script source code for ModeNginx.
	 * 
	 * @return <code>nginx.js</code> code for ModeNginx
	 */
	@Source("/js/mode/nginx/nginx.js")
	TextResource nginx();

	/**
	 * Returns the java script source code for ModeNsis.
	 * 
	 * @return <code>nsis.js</code> code for ModeNsis
	 */
	@Source("/js/mode/nsis/nsis.js")
	TextResource nsis();

	/**
	 * Returns the java script source code for ModeNtriples.
	 * 
	 * @return <code>ntriples.js</code> code for ModeNtriples
	 */
	@Source("/js/mode/ntriples/ntriples.js")
	TextResource ntriples();

	/**
	 * Returns the java script source code for ModeOctave.
	 * 
	 * @return <code>octave.js</code> code for ModeOctave
	 */
	@Source("/js/mode/octave/octave.js")
	TextResource octave();

	/**
	 * Returns the java script source code for ModeOz.
	 * 
	 * @return <code>oz.js</code> code for ModeOz
	 */
	@Source("/js/mode/oz/oz.js")
	TextResource oz();

	/**
	 * Returns the java script source code for ModePascal.
	 * 
	 * @return <code>pascal.js</code> code for ModePascal
	 */
	@Source("/js/mode/pascal/pascal.js")
	TextResource pascal();

	/**
	 * Returns the java script source code for ModePegjs.
	 * 
	 * @return <code>pegjs.js</code> code for ModePegjs
	 */
	@Source("/js/mode/pegjs/pegjs.js")
	TextResource pegjs();

	/**
	 * Returns the java script source code for ModePerl.
	 * 
	 * @return <code>perl.js</code> code for ModePerl
	 */
	@Source("/js/mode/perl/perl.js")
	TextResource perl();

	/**
	 * Returns the java script source code for ModePhp.
	 * 
	 * @return <code>php.js</code> code for ModePhp
	 */
	@Source("/js/mode/php/php.js")
	TextResource php();

	/**
	 * Returns the java script source code for ModePig.
	 * 
	 * @return <code>pig.js</code> code for ModePig
	 */
	@Source("/js/mode/pig/pig.js")
	TextResource pig();

	/**
	 * Returns the java script source code for ModePowershell.
	 * 
	 * @return <code>powershell.js</code> code for ModePowershell
	 */
	@Source("/js/mode/powershell/powershell.js")
	TextResource powershell();

	/**
	 * Returns the java script source code for ModeProperties.
	 * 
	 * @return <code>properties.js</code> code for ModeProperties
	 */
	@Source("/js/mode/properties/properties.js")
	TextResource properties();

	/**
	 * Returns the java script source code for ModeProtobuf.
	 * 
	 * @return <code>protobuf.js</code> code for ModeProtobuf
	 */
	@Source("/js/mode/protobuf/protobuf.js")
	TextResource protobuf();

	/**
	 * Returns the java script source code for ModePug.
	 * 
	 * @return <code>pug.js</code> code for ModePug
	 */
	@Source("/js/mode/pug/pug.js")
	TextResource pug();

	/**
	 * Returns the java script source code for ModePuppet.
	 * 
	 * @return <code>puppet.js</code> code for ModePuppet
	 */
	@Source("/js/mode/puppet/puppet.js")
	TextResource puppet();

	/**
	 * Returns the java script source code for ModePython.
	 * 
	 * @return <code>python.js</code> code for ModePython
	 */
	@Source("/js/mode/python/python.js")
	TextResource python();

	/**
	 * Returns the java script source code for ModeQ.
	 * 
	 * @return <code>q.js</code> code for ModeQ
	 */
	@Source("/js/mode/q/q.js")
	TextResource q();

	/**
	 * Returns the java script source code for ModeR.
	 * 
	 * @return <code>r.js</code> code for ModeR
	 */
	@Source("/js/mode/r/r.js")
	TextResource r();

	/**
	 * Returns the java script source code for ModeRpm.
	 * 
	 * @return <code>rpm.js</code> code for ModeRpm
	 */
	@Source("/js/mode/rpm/rpm.js")
	TextResource rpm();

	/**
	 * Returns the java script source code for ModeRst.
	 * 
	 * @return <code>rst.js</code> code for ModeRst
	 */
	@Source("/js/mode/rst/rst.js")
	TextResource rst();

	/**
	 * Returns the java script source code for ModeRuby.
	 * 
	 * @return <code>ruby.js</code> code for ModeRuby
	 */
	@Source("/js/mode/ruby/ruby.js")
	TextResource ruby();

	/**
	 * Returns the java script source code for ModeRust.
	 * 
	 * @return <code>rust.js</code> code for ModeRust
	 */
	@Source("/js/mode/rust/rust.js")
	TextResource rust();

	/**
	 * Returns the java script source code for ModeSas.
	 * 
	 * @return <code>sas.js</code> code for ModeSas
	 */
	@Source("/js/mode/sas/sas.js")
	TextResource sas();

	/**
	 * Returns the java script source code for ModeSass.
	 * 
	 * @return <code>sass.js</code> code for ModeSass
	 */
	@Source("/js/mode/sass/sass.js")
	TextResource sass();

	/**
	 * Returns the java script source code for ModeScheme.
	 * 
	 * @return <code>scheme.js</code> code for ModeScheme
	 */
	@Source("/js/mode/scheme/scheme.js")
	TextResource scheme();

	/**
	 * Returns the java script source code for ModeShell.
	 * 
	 * @return <code>shell.js</code> code for ModeShell
	 */
	@Source("/js/mode/shell/shell.js")
	TextResource shell();

	/**
	 * Returns the java script source code for ModeSieve.
	 * 
	 * @return <code>sieve.js</code> code for ModeSieve
	 */
	@Source("/js/mode/sieve/sieve.js")
	TextResource sieve();

	/**
	 * Returns the java script source code for ModeSlim.
	 * 
	 * @return <code>slim.js</code> code for ModeSlim
	 */
	@Source("/js/mode/slim/slim.js")
	TextResource slim();

	/**
	 * Returns the java script source code for ModeSmalltalk.
	 * 
	 * @return <code>smalltalk.js</code> code for ModeSmalltalk
	 */
	@Source("/js/mode/smalltalk/smalltalk.js")
	TextResource smalltalk();

	/**
	 * Returns the java script source code for ModeSmarty.
	 * 
	 * @return <code>smarty.js</code> code for ModeSmarty
	 */
	@Source("/js/mode/smarty/smarty.js")
	TextResource smarty();

	/**
	 * Returns the java script source code for ModeSolr.
	 * 
	 * @return <code>solr.js</code> code for ModeSolr
	 */
	@Source("/js/mode/solr/solr.js")
	TextResource solr();

	/**
	 * Returns the java script source code for ModeSoy.
	 * 
	 * @return <code>soy.js</code> code for ModeSoy
	 */
	@Source("/js/mode/soy/soy.js")
	TextResource soy();

	/**
	 * Returns the java script source code for ModeSparql.
	 * 
	 * @return <code>sparql.js</code> code for ModeSparql
	 */
	@Source("/js/mode/sparql/sparql.js")
	TextResource sparql();

	/**
	 * Returns the java script source code for ModeSpreadsheet.
	 * 
	 * @return <code>spreadsheet.js</code> code for ModeSpreadsheet
	 */
	@Source("/js/mode/spreadsheet/spreadsheet.js")
	TextResource spreadsheet();

	/**
	 * Returns the java script source code for ModeSql.
	 * 
	 * @return <code>sql.js</code> code for ModeSql
	 */
	@Source("/js/mode/sql/sql.js")
	TextResource sql();

	/**
	 * Returns the java script source code for ModeStex.
	 * 
	 * @return <code>stex.js</code> code for ModeStex
	 */
	@Source("/js/mode/stex/stex.js")
	TextResource stex();

	/**
	 * Returns the java script source code for ModeStylus.
	 * 
	 * @return <code>stylus.js</code> code for ModeStylus
	 */
	@Source("/js/mode/stylus/stylus.js")
	TextResource stylus();

	/**
	 * Returns the java script source code for ModeSwift.
	 * 
	 * @return <code>swift.js</code> code for ModeSwift
	 */
	@Source("/js/mode/swift/swift.js")
	TextResource swift();

	/**
	 * Returns the java script source code for ModeTcl.
	 * 
	 * @return <code>tcl.js</code> code for ModeTcl
	 */
	@Source("/js/mode/tcl/tcl.js")
	TextResource tcl();

	/**
	 * Returns the java script source code for ModeTextile.
	 * 
	 * @return <code>textile.js</code> code for ModeTextile
	 */
	@Source("/js/mode/textile/textile.js")
	TextResource textile();

	/**
	 * Returns the java script source code for ModeTiddlywiki.
	 * 
	 * @return <code>tiddlywiki.js</code> code for ModeTiddlywiki
	 */
	@Source("/js/mode/tiddlywiki/tiddlywiki.js")
	TextResource tiddlywiki();

	/**
	 * Returns the java script source code for ModeTiki.
	 * 
	 * @return <code>tiki.js</code> code for ModeTiki
	 */
	@Source("/js/mode/tiki/tiki.js")
	TextResource tiki();

	/**
	 * Returns the java script source code for ModeToml.
	 * 
	 * @return <code>toml.js</code> code for ModeToml
	 */
	@Source("/js/mode/toml/toml.js")
	TextResource toml();

	/**
	 * Returns the java script source code for ModeTornado.
	 * 
	 * @return <code>tornado.js</code> code for ModeTornado
	 */
	@Source("/js/mode/tornado/tornado.js")
	TextResource tornado();

	/**
	 * Returns the java script source code for ModeTroff.
	 * 
	 * @return <code>troff.js</code> code for ModeTroff
	 */
	@Source("/js/mode/troff/troff.js")
	TextResource troff();

	/**
	 * Returns the java script source code for ModeTtcn.
	 * 
	 * @return <code>ttcn.js</code> code for ModeTtcn
	 */
	@Source("/js/mode/ttcn/ttcn.js")
	TextResource ttcn();

	/**
	 * Returns the java script source code for ModeTtcnCfg.
	 * 
	 * @return <code>ttcn-cfg.js</code> code for ModeTtcnCfg
	 */
	@Source("/js/mode/ttcn-cfg/ttcn-cfg.js")
	TextResource ttcnCfg();

	/**
	 * Returns the java script source code for ModeTurtle.
	 * 
	 * @return <code>turtle.js</code> code for ModeTurtle
	 */
	@Source("/js/mode/turtle/turtle.js")
	TextResource turtle();

	/**
	 * Returns the java script source code for ModeTwig.
	 * 
	 * @return <code>twig.js</code> code for ModeTwig
	 */
	@Source("/js/mode/twig/twig.js")
	TextResource twig();

	/**
	 * Returns the java script source code for ModeVb.
	 * 
	 * @return <code>vb.js</code> code for ModeVb
	 */
	@Source("/js/mode/vb/vb.js")
	TextResource vb();

	/**
	 * Returns the java script source code for ModeVbscript.
	 * 
	 * @return <code>vbscript.js</code> code for ModeVbscript
	 */
	@Source("/js/mode/vbscript/vbscript.js")
	TextResource vbscript();

	/**
	 * Returns the java script source code for ModeVelocity.
	 * 
	 * @return <code>velocity.js</code> code for ModeVelocity
	 */
	@Source("/js/mode/velocity/velocity.js")
	TextResource velocity();

	/**
	 * Returns the java script source code for ModeVerilog.
	 * 
	 * @return <code>verilog.js</code> code for ModeVerilog
	 */
	@Source("/js/mode/verilog/verilog.js")
	TextResource verilog();

	/**
	 * Returns the java script source code for ModeVhdl.
	 * 
	 * @return <code>vhdl.js</code> code for ModeVhdl
	 */
	@Source("/js/mode/vhdl/vhdl.js")
	TextResource vhdl();

	/**
	 * Returns the java script source code for ModeVue.
	 * 
	 * @return <code>vue.js</code> code for ModeVue
	 */
	@Source("/js/mode/vue/vue.js")
	TextResource vue();

	/**
	 * Returns the java script source code for ModeWebidl.
	 * 
	 * @return <code>webidl.js</code> code for ModeWebidl
	 */
	@Source("/js/mode/webidl/webidl.js")
	TextResource webidl();

	/**
	 * Returns the java script source code for ModeXml.
	 * 
	 * @return <code>xml.js</code> code for ModeXml
	 */
	@Source("/js/mode/xml/xml.js")
	TextResource xml();

	/**
	 * Returns the java script source code for ModeXquery.
	 * 
	 * @return <code>xquery.js</code> code for ModeXquery
	 */
	@Source("/js/mode/xquery/xquery.js")
	TextResource xquery();

	/**
	 * Returns the java script source code for ModeYacas.
	 * 
	 * @return <code>yacas.js</code> code for ModeYacas
	 */
	@Source("/js/mode/yacas/yacas.js")
	TextResource yacas();

	/**
	 * Returns the java script source code for ModeYaml.
	 * 
	 * @return <code>yaml.js</code> code for ModeYaml
	 */
	@Source("/js/mode/yaml/yaml.js")
	TextResource yaml();

	/**
	 * Returns the java script source code for ModeYamlFrontmatter.
	 * 
	 * @return <code>yaml-frontmatter.js</code> code for ModeYamlFrontmatter
	 */
	@Source("/js/mode/yaml-frontmatter/yaml-frontmatter.js")
	TextResource yamlFrontmatter();

	/**
	 * Returns the java script source code for ModeZ80.
	 * 
	 * @return <code>z80.js</code> code for ModeZ80
	 */
	@Source("/js/mode/z80/z80.js")
	TextResource z80();

	// ----------------------------
	// --- CSS Resources ---
	// ----------------------------

	/**
	 * Returns the CSS source code for ModeTiddlywiki.
	 * 
	 * @return <code>tiddlywiki.css</code> CSS code for ModeTiddlywiki
	 */
	@Source("/js/mode/tiddlywiki/tiddlywiki.css")
	TextResource tiddlywikiCss();

	/**
	 * Returns the CSS source code for ModeTiki.
	 * 
	 * @return <code>tiki.css</code> CSS code for ModeTiki
	 */
	@Source("/js/mode/tiki/tiki.css")
	TextResource tikiCss();
}