Coderba - GWT Code editor library based on CodeMirror
=====================================================

Version 0.1 (under development)

What's Coderba
--------

[Google Web toolkit](http://www.gwtproject.org/) doesn't have a code editor available out of the box.

For all these reasons, **Coderba** has been developed, leveraging on [CodeMirror](https://codemirror.net/) capabilities which will be available to GWT developers.

Building
--------

To build **Coderba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Coderba/blob/master/build.xml).

It creates a `coderba-[version.release].jar` file into `dist` folder, ready to be included into your project.

Installation
------------

To install in your GWT project, you must the following configuration into your GWT project module configuration:

```xml
...
    <inherits name="org.pepstock.coderba.Coderba"/>
...
```

License
-------

**Coderba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
