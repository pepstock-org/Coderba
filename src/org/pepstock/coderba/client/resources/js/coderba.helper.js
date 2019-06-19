	/*
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
	/*
		JSHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for EDIRBA, because 
		JSINTEROP is not able to address all javascript model.   
	*/
    function CoderbaJsHelper() {};
    /*
	 This method determines whether the passed property of passed object is an Array.
	  
	 @param object the object on which to test the property.
	 @param key the string name of the property to test.
	 @return true if the value is an Array; otherwise, false.
    */
    CoderbaJsHelper.isArray = function(obj, key) {
	    return Array.isArray(obj[key]);
    }  
    /*
	 Returns the java script object type of the object.
	  
	 @param object the object to get type.
	 @return the object type
    */
    CoderbaJsHelper.typeOf = function(obj) {
    	return typeof obj;
    }
   /*
	 Removes a property from a java script object.
	 
	 @param object the object on which to remove the property.
	 @param key the string name of the property to remove.
    */
    CoderbaJsHelper.remove = function(obj, key) {
    	delete obj[key];
    }
    /*
	 Returns the java script object type of a property.
	  
	 @param object the object on which to search the property.
	 @param key the string name of the property to test.
	 @return the object type
    */
    CoderbaJsHelper.type = function(obj, key) {
    	return typeof obj[key];
    }
        /*
	 Returns a property of java script object as string.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return string value
    */
    CoderbaJsHelper.propertyAsString = function(obj, key) {
    	console.log(obj);
    	console.log(key);
    	return obj[key];
    }  
    /*
	 Returns a property of java script object as integer.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return integer value
    */
    CoderbaJsHelper.propertyAsInt = function(obj, key) {
    	return obj[key];
    }  
    /*
	 Returns a property of java script object as boolean.
	  
	 @param object the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return boolean value
    */
    CoderbaJsHelper.propertyAsBoolean = function(obj, key) {
    	return obj[key];
    }
    /*
	 Sets the boolean property to <code>true</code> into a native event.
	  
	 @param object the object on which to remove the property.
	 @param key the string name of the property to remove.
	 */
	CoderbaJsHelper.ignore= function(obj, key) {
    	obj[key] = true;
    }    
    /*
	 Returns an array of strings with element attributes.
	  
	 @param element DOM element to scan
	 @return an array of strings with element attributes
    */
    CoderbaJsHelper.elementAttribute = function(element, key) {
    	// First, let's verify that the paragraph has some attributes    
     	if (element.hasAttributes()) {
           var attrs = element.attributes;
	       for(var i = 0; i < attrs.length; i++) {
	          if (attrs[i].name === key){
	          	 return  attrs[i].value;
	          }
	       }
	    }
    	return null;
    }  
    /*
	 Returns the java script object type of a property.
	  
	 @param object the object on which to search the property.
	 @param key the string name of the property to test.
	 @return the object type
    */
    CoderbaJsHelper.cloneProperty = function(source, target, key) {
    	Object.defineProperty(target, key, {
  			value: source[key],
  			writable: true,
  			configurable: true,
  			enumerable: true
		});
    }
    /*
	 Creates new proxy for callback which will pass "this" environment of java script as first argument of callback
	 method.
	  
	 @return new proxy for callback.
    */
    CoderbaJsHelper.newCallbackProxy = function() {
    	/*
    		Creates an object with 2 properties.
    		CALLBACK: contains user callback implementation which must be called
    		PROXY: contains a function which can call CALLBACk passing "this" 
    	 */
    	var obj = new Object();
		// CALLBACK
    	obj.callback = null;
    	// PROXY
    	obj.proxy = function() {
    		// checks if callback is a function	
        	if (obj.callback != null && typeof obj.callback === 'function'){
        		// creates arguments for callbacks adding the "this" 
				//var args = Array.of(this).concat(Array.prototype.slice.call(arguments));
				var args = Array.prototype.slice.call(arguments);
				// calls CALLBACK
				var result = obj.callback.apply(this, args);
				if (result === null){
					// do nothing console.log("null");
				} else if (result === undefined){
					//console.log("undefined");
				} else {
					//console.log(result);
					return result;
				}
    		} else {
    			//console.log("No caller");
    		}
		};
    	return obj;
    }
 