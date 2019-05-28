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
public interface ModeResources extends ClientBundle {

	// static reference of this resource
	public static final ModeResources INSTANCE = GWT.create(ModeResources.class);

	/**
 * Returns the java script source code for Apl.
 * @return <code>apl.js</code> code for Apl
 */
 @Source("/js/mode/apl/apl.js") TextResource apl();

/**
 * Returns the java script source code for Asciiarmor.
 * @return <code>asciiarmor.js</code> code for Asciiarmor
 */
 @Source("/js/mode/asciiarmor/asciiarmor.js") TextResource asciiarmor();

/**
 * Returns the java script source code for Asn1.
 * @return <code>asn.1.js</code> code for Asn1
 */
 @Source("/js/mode/asn.1/asn.1.js") TextResource asn1();

/**
 * Returns the java script source code for Asterisk.
 * @return <code>asterisk.js</code> code for Asterisk
 */
 @Source("/js/mode/asterisk/asterisk.js") TextResource asterisk();

/**
 * Returns the java script source code for Brainfuck.
 * @return <code>brainfuck.js</code> code for Brainfuck
 */
 @Source("/js/mode/brainfuck/brainfuck.js") TextResource brainfuck();

/**
 * Returns the java script source code for Clike.
 * @return <code>clike.js</code> code for Clike
 */
 @Source("/js/mode/clike/clike.js") TextResource clike();

/**
 * Returns the java script source code for Clojure.
 * @return <code>clojure.js</code> code for Clojure
 */
 @Source("/js/mode/clojure/clojure.js") TextResource clojure();

/**
 * Returns the java script source code for Cmake.
 * @return <code>cmake.js</code> code for Cmake
 */
 @Source("/js/mode/cmake/cmake.js") TextResource cmake();

/**
 * Returns the java script source code for Cobol.
 * @return <code>cobol.js</code> code for Cobol
 */
 @Source("/js/mode/cobol/cobol.js") TextResource cobol();

/**
 * Returns the java script source code for Coffeescript.
 * @return <code>coffeescript.js</code> code for Coffeescript
 */
 @Source("/js/mode/coffeescript/coffeescript.js") TextResource coffeescript();

/**
 * Returns the java script source code for Commonlisp.
 * @return <code>commonlisp.js</code> code for Commonlisp
 */
 @Source("/js/mode/commonlisp/commonlisp.js") TextResource commonlisp();

/**
 * Returns the java script source code for Crystal.
 * @return <code>crystal.js</code> code for Crystal
 */
 @Source("/js/mode/crystal/crystal.js") TextResource crystal();

/**
 * Returns the java script source code for Css.
 * @return <code>css.js</code> code for Css
 */
 @Source("/js/mode/css/css.js") TextResource css();

/**
 * Returns the java script source code for Cypher.
 * @return <code>cypher.js</code> code for Cypher
 */
 @Source("/js/mode/cypher/cypher.js") TextResource cypher();

/**
 * Returns the java script source code for D.
 * @return <code>d.js</code> code for D
 */
 @Source("/js/mode/d/d.js") TextResource d();

/**
 * Returns the java script source code for Dart.
 * @return <code>dart.js</code> code for Dart
 */
 @Source("/js/mode/dart/dart.js") TextResource dart();

/**
 * Returns the java script source code for Diff.
 * @return <code>diff.js</code> code for Diff
 */
 @Source("/js/mode/diff/diff.js") TextResource diff();

/**
 * Returns the java script source code for Django.
 * @return <code>django.js</code> code for Django
 */
 @Source("/js/mode/django/django.js") TextResource django();

/**
 * Returns the java script source code for Dockerfile.
 * @return <code>dockerfile.js</code> code for Dockerfile
 */
 @Source("/js/mode/dockerfile/dockerfile.js") TextResource dockerfile();

/**
 * Returns the java script source code for Dtd.
 * @return <code>dtd.js</code> code for Dtd
 */
 @Source("/js/mode/dtd/dtd.js") TextResource dtd();

/**
 * Returns the java script source code for Dylan.
 * @return <code>dylan.js</code> code for Dylan
 */
 @Source("/js/mode/dylan/dylan.js") TextResource dylan();

/**
 * Returns the java script source code for Ebnf.
 * @return <code>ebnf.js</code> code for Ebnf
 */
 @Source("/js/mode/ebnf/ebnf.js") TextResource ebnf();

/**
 * Returns the java script source code for Ecl.
 * @return <code>ecl.js</code> code for Ecl
 */
 @Source("/js/mode/ecl/ecl.js") TextResource ecl();

/**
 * Returns the java script source code for Eiffel.
 * @return <code>eiffel.js</code> code for Eiffel
 */
 @Source("/js/mode/eiffel/eiffel.js") TextResource eiffel();

/**
 * Returns the java script source code for Elm.
 * @return <code>elm.js</code> code for Elm
 */
 @Source("/js/mode/elm/elm.js") TextResource elm();

/**
 * Returns the java script source code for Erlang.
 * @return <code>erlang.js</code> code for Erlang
 */
 @Source("/js/mode/erlang/erlang.js") TextResource erlang();

/**
 * Returns the java script source code for Factor.
 * @return <code>factor.js</code> code for Factor
 */
 @Source("/js/mode/factor/factor.js") TextResource factor();

/**
 * Returns the java script source code for Fcl.
 * @return <code>fcl.js</code> code for Fcl
 */
 @Source("/js/mode/fcl/fcl.js") TextResource fcl();

/**
 * Returns the java script source code for Forth.
 * @return <code>forth.js</code> code for Forth
 */
 @Source("/js/mode/forth/forth.js") TextResource forth();

/**
 * Returns the java script source code for Fortran.
 * @return <code>fortran.js</code> code for Fortran
 */
 @Source("/js/mode/fortran/fortran.js") TextResource fortran();

/**
 * Returns the java script source code for Gas.
 * @return <code>gas.js</code> code for Gas
 */
 @Source("/js/mode/gas/gas.js") TextResource gas();

/**
 * Returns the java script source code for Gfm.
 * @return <code>gfm.js</code> code for Gfm
 */
 @Source("/js/mode/gfm/gfm.js") TextResource gfm();

/**
 * Returns the java script source code for Gherkin.
 * @return <code>gherkin.js</code> code for Gherkin
 */
 @Source("/js/mode/gherkin/gherkin.js") TextResource gherkin();

/**
 * Returns the java script source code for Go.
 * @return <code>go.js</code> code for Go
 */
 @Source("/js/mode/go/go.js") TextResource go();

/**
 * Returns the java script source code for Groovy.
 * @return <code>groovy.js</code> code for Groovy
 */
 @Source("/js/mode/groovy/groovy.js") TextResource groovy();

/**
 * Returns the java script source code for Haml.
 * @return <code>haml.js</code> code for Haml
 */
 @Source("/js/mode/haml/haml.js") TextResource haml();

/**
 * Returns the java script source code for Handlebars.
 * @return <code>handlebars.js</code> code for Handlebars
 */
 @Source("/js/mode/handlebars/handlebars.js") TextResource handlebars();

/**
 * Returns the java script source code for Haskell.
 * @return <code>haskell.js</code> code for Haskell
 */
 @Source("/js/mode/haskell/haskell.js") TextResource haskell();

/**
 * Returns the java script source code for HaskellLiterate.
 * @return <code>haskell-literate.js</code> code for HaskellLiterate
 */
 @Source("/js/mode/haskell-literate/haskell-literate.js") TextResource haskellLiterate();

/**
 * Returns the java script source code for Haxe.
 * @return <code>haxe.js</code> code for Haxe
 */
 @Source("/js/mode/haxe/haxe.js") TextResource haxe();

/**
 * Returns the java script source code for Htmlembedded.
 * @return <code>htmlembedded.js</code> code for Htmlembedded
 */
 @Source("/js/mode/htmlembedded/htmlembedded.js") TextResource htmlembedded();

/**
 * Returns the java script source code for Htmlmixed.
 * @return <code>htmlmixed.js</code> code for Htmlmixed
 */
 @Source("/js/mode/htmlmixed/htmlmixed.js") TextResource htmlmixed();

/**
 * Returns the java script source code for Http.
 * @return <code>http.js</code> code for Http
 */
 @Source("/js/mode/http/http.js") TextResource http();

/**
 * Returns the java script source code for Idl.
 * @return <code>idl.js</code> code for Idl
 */
 @Source("/js/mode/idl/idl.js") TextResource idl();

/**
 * Returns the java script source code for Javascript.
 * @return <code>javascript.js</code> code for Javascript
 */
 @Source("/js/mode/javascript/javascript.js") TextResource javascript();

/**
 * Returns the java script source code for Jinja2.
 * @return <code>jinja2.js</code> code for Jinja2
 */
 @Source("/js/mode/jinja2/jinja2.js") TextResource jinja2();

/**
 * Returns the java script source code for Jsx.
 * @return <code>jsx.js</code> code for Jsx
 */
 @Source("/js/mode/jsx/jsx.js") TextResource jsx();

/**
 * Returns the java script source code for Julia.
 * @return <code>julia.js</code> code for Julia
 */
 @Source("/js/mode/julia/julia.js") TextResource julia();

/**
 * Returns the java script source code for Livescript.
 * @return <code>livescript.js</code> code for Livescript
 */
 @Source("/js/mode/livescript/livescript.js") TextResource livescript();

/**
 * Returns the java script source code for Lua.
 * @return <code>lua.js</code> code for Lua
 */
 @Source("/js/mode/lua/lua.js") TextResource lua();

/**
 * Returns the java script source code for Markdown.
 * @return <code>markdown.js</code> code for Markdown
 */
 @Source("/js/mode/markdown/markdown.js") TextResource markdown();

/**
 * Returns the java script source code for Mathematica.
 * @return <code>mathematica.js</code> code for Mathematica
 */
 @Source("/js/mode/mathematica/mathematica.js") TextResource mathematica();

/**
 * Returns the java script source code for Mbox.
 * @return <code>mbox.js</code> code for Mbox
 */
 @Source("/js/mode/mbox/mbox.js") TextResource mbox();

/**
 * Returns the java script source code for Mirc.
 * @return <code>mirc.js</code> code for Mirc
 */
 @Source("/js/mode/mirc/mirc.js") TextResource mirc();

/**
 * Returns the java script source code for Mllike.
 * @return <code>mllike.js</code> code for Mllike
 */
 @Source("/js/mode/mllike/mllike.js") TextResource mllike();

/**
 * Returns the java script source code for Modelica.
 * @return <code>modelica.js</code> code for Modelica
 */
 @Source("/js/mode/modelica/modelica.js") TextResource modelica();

/**
 * Returns the java script source code for Mscgen.
 * @return <code>mscgen.js</code> code for Mscgen
 */
 @Source("/js/mode/mscgen/mscgen.js") TextResource mscgen();

/**
 * Returns the java script source code for Mumps.
 * @return <code>mumps.js</code> code for Mumps
 */
 @Source("/js/mode/mumps/mumps.js") TextResource mumps();

/**
 * Returns the java script source code for Nginx.
 * @return <code>nginx.js</code> code for Nginx
 */
 @Source("/js/mode/nginx/nginx.js") TextResource nginx();

/**
 * Returns the java script source code for Nsis.
 * @return <code>nsis.js</code> code for Nsis
 */
 @Source("/js/mode/nsis/nsis.js") TextResource nsis();

/**
 * Returns the java script source code for Ntriples.
 * @return <code>ntriples.js</code> code for Ntriples
 */
 @Source("/js/mode/ntriples/ntriples.js") TextResource ntriples();

/**
 * Returns the java script source code for Octave.
 * @return <code>octave.js</code> code for Octave
 */
 @Source("/js/mode/octave/octave.js") TextResource octave();

/**
 * Returns the java script source code for Oz.
 * @return <code>oz.js</code> code for Oz
 */
 @Source("/js/mode/oz/oz.js") TextResource oz();

/**
 * Returns the java script source code for Pascal.
 * @return <code>pascal.js</code> code for Pascal
 */
 @Source("/js/mode/pascal/pascal.js") TextResource pascal();

/**
 * Returns the java script source code for Pegjs.
 * @return <code>pegjs.js</code> code for Pegjs
 */
 @Source("/js/mode/pegjs/pegjs.js") TextResource pegjs();

/**
 * Returns the java script source code for Perl.
 * @return <code>perl.js</code> code for Perl
 */
 @Source("/js/mode/perl/perl.js") TextResource perl();

/**
 * Returns the java script source code for Php.
 * @return <code>php.js</code> code for Php
 */
 @Source("/js/mode/php/php.js") TextResource php();

/**
 * Returns the java script source code for Pig.
 * @return <code>pig.js</code> code for Pig
 */
 @Source("/js/mode/pig/pig.js") TextResource pig();

/**
 * Returns the java script source code for Powershell.
 * @return <code>powershell.js</code> code for Powershell
 */
 @Source("/js/mode/powershell/powershell.js") TextResource powershell();

/**
 * Returns the java script source code for Properties.
 * @return <code>properties.js</code> code for Properties
 */
 @Source("/js/mode/properties/properties.js") TextResource properties();

/**
 * Returns the java script source code for Protobuf.
 * @return <code>protobuf.js</code> code for Protobuf
 */
 @Source("/js/mode/protobuf/protobuf.js") TextResource protobuf();

/**
 * Returns the java script source code for Pug.
 * @return <code>pug.js</code> code for Pug
 */
 @Source("/js/mode/pug/pug.js") TextResource pug();

/**
 * Returns the java script source code for Puppet.
 * @return <code>puppet.js</code> code for Puppet
 */
 @Source("/js/mode/puppet/puppet.js") TextResource puppet();

/**
 * Returns the java script source code for Python.
 * @return <code>python.js</code> code for Python
 */
 @Source("/js/mode/python/python.js") TextResource python();

/**
 * Returns the java script source code for Q.
 * @return <code>q.js</code> code for Q
 */
 @Source("/js/mode/q/q.js") TextResource q();

/**
 * Returns the java script source code for R.
 * @return <code>r.js</code> code for R
 */
 @Source("/js/mode/r/r.js") TextResource r();

/**
 * Returns the java script source code for Rpm.
 * @return <code>rpm.js</code> code for Rpm
 */
 @Source("/js/mode/rpm/rpm.js") TextResource rpm();

/**
 * Returns the java script source code for Rst.
 * @return <code>rst.js</code> code for Rst
 */
 @Source("/js/mode/rst/rst.js") TextResource rst();

/**
 * Returns the java script source code for Ruby.
 * @return <code>ruby.js</code> code for Ruby
 */
 @Source("/js/mode/ruby/ruby.js") TextResource ruby();

/**
 * Returns the java script source code for Rust.
 * @return <code>rust.js</code> code for Rust
 */
 @Source("/js/mode/rust/rust.js") TextResource rust();

/**
 * Returns the java script source code for Sas.
 * @return <code>sas.js</code> code for Sas
 */
 @Source("/js/mode/sas/sas.js") TextResource sas();

/**
 * Returns the java script source code for Sass.
 * @return <code>sass.js</code> code for Sass
 */
 @Source("/js/mode/sass/sass.js") TextResource sass();

/**
 * Returns the java script source code for Scheme.
 * @return <code>scheme.js</code> code for Scheme
 */
 @Source("/js/mode/scheme/scheme.js") TextResource scheme();

/**
 * Returns the java script source code for Shell.
 * @return <code>shell.js</code> code for Shell
 */
 @Source("/js/mode/shell/shell.js") TextResource shell();

/**
 * Returns the java script source code for Sieve.
 * @return <code>sieve.js</code> code for Sieve
 */
 @Source("/js/mode/sieve/sieve.js") TextResource sieve();

/**
 * Returns the java script source code for Slim.
 * @return <code>slim.js</code> code for Slim
 */
 @Source("/js/mode/slim/slim.js") TextResource slim();

/**
 * Returns the java script source code for Smalltalk.
 * @return <code>smalltalk.js</code> code for Smalltalk
 */
 @Source("/js/mode/smalltalk/smalltalk.js") TextResource smalltalk();

/**
 * Returns the java script source code for Smarty.
 * @return <code>smarty.js</code> code for Smarty
 */
 @Source("/js/mode/smarty/smarty.js") TextResource smarty();

/**
 * Returns the java script source code for Solr.
 * @return <code>solr.js</code> code for Solr
 */
 @Source("/js/mode/solr/solr.js") TextResource solr();

/**
 * Returns the java script source code for Soy.
 * @return <code>soy.js</code> code for Soy
 */
 @Source("/js/mode/soy/soy.js") TextResource soy();

/**
 * Returns the java script source code for Sparql.
 * @return <code>sparql.js</code> code for Sparql
 */
 @Source("/js/mode/sparql/sparql.js") TextResource sparql();

/**
 * Returns the java script source code for Spreadsheet.
 * @return <code>spreadsheet.js</code> code for Spreadsheet
 */
 @Source("/js/mode/spreadsheet/spreadsheet.js") TextResource spreadsheet();

/**
 * Returns the java script source code for Sql.
 * @return <code>sql.js</code> code for Sql
 */
 @Source("/js/mode/sql/sql.js") TextResource sql();

/**
 * Returns the java script source code for Stex.
 * @return <code>stex.js</code> code for Stex
 */
 @Source("/js/mode/stex/stex.js") TextResource stex();

/**
 * Returns the java script source code for Stylus.
 * @return <code>stylus.js</code> code for Stylus
 */
 @Source("/js/mode/stylus/stylus.js") TextResource stylus();

/**
 * Returns the java script source code for Swift.
 * @return <code>swift.js</code> code for Swift
 */
 @Source("/js/mode/swift/swift.js") TextResource swift();

/**
 * Returns the java script source code for Tcl.
 * @return <code>tcl.js</code> code for Tcl
 */
 @Source("/js/mode/tcl/tcl.js") TextResource tcl();

/**
 * Returns the java script source code for Textile.
 * @return <code>textile.js</code> code for Textile
 */
 @Source("/js/mode/textile/textile.js") TextResource textile();

/**
 * Returns the CSS source code for Tiddlywiki.
 * @return <code>tiddlywiki.css</code> CSS code for Tiddlywiki
 */
 @Source("/js/mode/tiddlywiki/tiddlywiki.css")
 TextResource tiddlywikiCss();

/**
 * Returns the java script source code for Tiddlywiki.
 * @return <code>tiddlywiki.js</code> code for Tiddlywiki
 */
 @Source("/js/mode/tiddlywiki/tiddlywiki.js") TextResource tiddlywiki();

/**
 * Returns the CSS source code for Tiki.
 * @return <code>tiki.css</code> CSS code for Tiki
 */
 @Source("/js/mode/tiki/tiki.css")
 TextResource tikiCss();

/**
 * Returns the java script source code for Tiki.
 * @return <code>tiki.js</code> code for Tiki
 */
 @Source("/js/mode/tiki/tiki.js") TextResource tiki();

/**
 * Returns the java script source code for Toml.
 * @return <code>toml.js</code> code for Toml
 */
 @Source("/js/mode/toml/toml.js") TextResource toml();

/**
 * Returns the java script source code for Tornado.
 * @return <code>tornado.js</code> code for Tornado
 */
 @Source("/js/mode/tornado/tornado.js") TextResource tornado();

/**
 * Returns the java script source code for Troff.
 * @return <code>troff.js</code> code for Troff
 */
 @Source("/js/mode/troff/troff.js") TextResource troff();

/**
 * Returns the java script source code for Ttcn.
 * @return <code>ttcn.js</code> code for Ttcn
 */
 @Source("/js/mode/ttcn/ttcn.js") TextResource ttcn();

/**
 * Returns the java script source code for TtcnCfg.
 * @return <code>ttcn-cfg.js</code> code for TtcnCfg
 */
 @Source("/js/mode/ttcn-cfg/ttcn-cfg.js") TextResource ttcnCfg();

/**
 * Returns the java script source code for Turtle.
 * @return <code>turtle.js</code> code for Turtle
 */
 @Source("/js/mode/turtle/turtle.js") TextResource turtle();

/**
 * Returns the java script source code for Twig.
 * @return <code>twig.js</code> code for Twig
 */
 @Source("/js/mode/twig/twig.js") TextResource twig();

/**
 * Returns the java script source code for Vb.
 * @return <code>vb.js</code> code for Vb
 */
 @Source("/js/mode/vb/vb.js") TextResource vb();

/**
 * Returns the java script source code for Vbscript.
 * @return <code>vbscript.js</code> code for Vbscript
 */
 @Source("/js/mode/vbscript/vbscript.js") TextResource vbscript();

/**
 * Returns the java script source code for Velocity.
 * @return <code>velocity.js</code> code for Velocity
 */
 @Source("/js/mode/velocity/velocity.js") TextResource velocity();

/**
 * Returns the java script source code for Verilog.
 * @return <code>verilog.js</code> code for Verilog
 */
 @Source("/js/mode/verilog/verilog.js") TextResource verilog();

/**
 * Returns the java script source code for Vhdl.
 * @return <code>vhdl.js</code> code for Vhdl
 */
 @Source("/js/mode/vhdl/vhdl.js") TextResource vhdl();

/**
 * Returns the java script source code for Vue.
 * @return <code>vue.js</code> code for Vue
 */
 @Source("/js/mode/vue/vue.js") TextResource vue();

/**
 * Returns the java script source code for Webidl.
 * @return <code>webidl.js</code> code for Webidl
 */
 @Source("/js/mode/webidl/webidl.js") TextResource webidl();

/**
 * Returns the java script source code for Xml.
 * @return <code>xml.js</code> code for Xml
 */
 @Source("/js/mode/xml/xml.js") TextResource xml();

/**
 * Returns the java script source code for Xquery.
 * @return <code>xquery.js</code> code for Xquery
 */
 @Source("/js/mode/xquery/xquery.js") TextResource xquery();

/**
 * Returns the java script source code for Yacas.
 * @return <code>yacas.js</code> code for Yacas
 */
 @Source("/js/mode/yacas/yacas.js") TextResource yacas();

/**
 * Returns the java script source code for Yaml.
 * @return <code>yaml.js</code> code for Yaml
 */
 @Source("/js/mode/yaml/yaml.js") TextResource yaml();

/**
 * Returns the java script source code for YamlFrontmatter.
 * @return <code>yaml-frontmatter.js</code> code for YamlFrontmatter
 */
 @Source("/js/mode/yaml-frontmatter/yaml-frontmatter.js") TextResource yamlFrontmatter();

/**
 * Returns the java script source code for Z80.
 * @return <code>z80.js</code> code for Z80
 */
 @Source("/js/mode/z80/z80.js") TextResource z80();



}
