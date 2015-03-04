/*!
 * ACCELERATOR.common
 * @version 1.3
 * @author ACCELERATOR - http://www.ACCELERATOR.com/en
 * @requires jQuery Core 1.4.1 - http://www.jquery.com/
*/
/*jslint bitwise: true, eqeqeq: true, passfail: false, nomen: true, plusplus: true, undef: true, evil: true */
/*global window, document, navigator, $, jQuery, */

var ACCELERATOR = window.ACCELERATOR || {};
/**
 * @namespace Common re-used functionality.
 */
ACCELERATOR.common = {	
	version					: 1.2,	
	
	ajaxQueryParam			: 'rc=1',
	webSafeChar				: '<span class="webSafeChar"> </span>',
	printLinkSelector		: 'a.printwin',
	printLinkText			: 'This link will open the Print dialog',
	
	/**	
	 * Returns object of all queries within a string.
	 * <p>
	 * Will return undefined for no query string.
	 * 
	 * @param {Object}	theString
	 * @return {Object}	key:values of query string
	 */
	getStringQueries: function(theString) {
		// returns object of all queries within a string
		if (typeof theString !== 'string') {
			return false;
		}

		if (theString.indexOf('?') > -1) {
			// make sure you get query side of url
			theString = theString.split('?')[1];
		}
		
		var qs = theString, qsKey, qsValue, stringElements = {};
		// make an array of query string items
		qs = qs.split("&");
		// create object
		for (var i = qs.length - 1; i >= 0; i--) {
			qsKey = qs[i].split("=")[0];
			qsValue = qs[i].split("=")[1];
			stringElements[qsKey] = qsValue;
		}
		return stringElements;

	},
	/** 
	 * Splits a string that would not normally wrap (like and email address) with wrapping characters.
	 * <p>
	 * Since wrapping characters are not supported in IE6 without East Asian language support an empty 
	 * styled <span> is used instead. This leads to some accessibility issues.
	 * 
	 * @param (String) myString
	 * @return {String} 
	 */
	webSafeString: function (myString) {
		var stringAsArray = myString.split("");
		var stringLength = stringAsArray.length;
		var newArray = [];
		for (var i=0; i < stringLength; i++ ) {
			newArray.push(stringAsArray[i],ACCELERATOR.common.webSafeChar);
		}
		var newString = newArray.join("");
		return newString;
	},
	
	/**
	 * Strip out and return local page anchor from URL.
	 * 
	 * @param 	{Object} anchor
	 * @return 	Return the anchor portion of the URL (or false if no anchor)
	 */
	urlGetAnchor: function(anchor) {
		if ( anchor.href.indexOf("#") >= 0 ) { //Check for anchorness
			anchor.destination = anchor.href.slice(anchor.href.indexOf("#")+1);
			return anchor.destination;
		} else {
			return false;
		}
	},
	
	/**
	 * Print dialog function.
	 * <p>
	 * Add the window.print function to the onclick event of elements matching a specified selector.
	 */
	getPrintLinks: function() { 
		$(ACCELERATOR.common.printLinkSelector).each(function(){
			$(this).attr('title', $(this).attr('title') + " " + ACCELERATOR.common.printLinkText);
		}).live('click', function(e){
			window.print();
			e.preventDefault();
		});
	},
	
	/**
	 * @namespace Common AJAX functionality.
	 */
	ajax: {		
		bufferFieldID	: 'ajaxVirtualBufferUpdate',		
		/**
		 * Insert form field for buffer update
		 */
		prepareBuffer: function() {
			var objHidden = document.createElement('input');
			
			objHidden.setAttribute('type', 'hidden');
			objHidden.setAttribute('value', '1');
			objHidden.setAttribute('id', ACCELERATOR.common.ajax.bufferFieldID);
			objHidden.setAttribute('name', ACCELERATOR.common.ajax.bufferFieldID);

			document.body.appendChild(objHidden);
		},
		/**
		 * Updates value of hidden form field to nudge screen readers into recognising page state change.
		 * 
		 * @see ACCELERATOR.common.ajax.prepareBuffer()
		 */
		updateBuffer: function() {
			var objHidden = document.getElementById(ACCELERATOR.common.ajax.bufferFieldID);			
			if (objHidden) {
				if (objHidden.getAttribute('value') === '1') {
					objHidden.setAttribute('value', '0');
				} else {
					objHidden.setAttribute('value', '1');
				}
			} else {
				ACCELERATOR.common.ajax.prepareBuffer();
				ACCELERATOR.common.ajax.updateBuffer();
			}		
		},
		/**
		 * Update the page buffer for assistive tech and focus on optional element.
		 *
		 * @param {Object} focusEL
		 * @param {Boolean} timeout
		 */
		pageUpdated: function (focusEL, timeout) {
			// Update page 'buffer'
			ACCELERATOR.common.ajax.updateBuffer();
			// If an element is provided focus it
			if (focusEL) {
				if ($.isArray(focusEL) || (focusEL.length > 0)) {
					// If focusEL is an array object
					focusEL[0].focus();
				} else {
					// If focusEL is a single object
					focusEL.focus();
				}
			}
		},
		/**
		 * Takes a string (URL expected) and appends parameter to identify as AJAX request for backend processing.
		 * 
		 * @param {String}	URL
		 * @return {String}	An AJAXed URL
		 */	
		tagUrl: function(url) {
			// 
			var prefix =(url.indexOf('?') < 0 ) ? "?" : "&" ;
			var ajaxURL = url + prefix + ACCELERATOR.common.ajaxQueryParam;
			return ajaxURL;
		}
	},
	
	
	/**
	 * Parse version string.
	 * <p>
	 * Parse the version strings with a simple function and return them as objects with appropriate properties whose values are integers. 
	 * Once in this form, we can compare them with simple mathematics.
	 * 
	 * @param 	{String} str
	 * @return 	{Object} Return the major, minor and patch numbers
	 */
	parseVersionString: function(str) {
	    if (typeof(str) !== 'string') { return false; }
	    var x = str.split('.');
	    // parse from string or default to 0 if can't parse
	    var maj = parseInt(x[0], 10) || 0;
	    var min = parseInt(x[1], 10) || 0;
	    var pat = parseInt(x[2], 10) || 0;
	    return {
	        major: maj,
	        minor: min,
	        patch: pat
	    };
	},
	
	/**
	 * If within a frame kill the frame and reload at top
	 */
	frameKill: function() {
		if (window.top !== window.self) {
			window.top.location.href = window.self.location.href;
		}
	},
	
	/**
	 * Escapes special characters in DOM ID strings.
	 * <p>Used with jQuery when an ID has special characters. For example, JSF outputs IDs with colons separating namespaces, 
	 * which breaks jQuery's getElementById implementation. This function will escape any such characters in the ID string.
	 * @param {String} id The DOM ID string containing special characters to be escaped.
	 * @returns {String} A DOM ID string with the special characters escaped.
	 */
	escapeSpecialCharsInId: function(id) {
		return '#' + id.replace(/:/g,"\\:").replace(/\./g,"\\.");
	},
	
	/**
	 * Input field text population.
	 * <p>
	 * Takes current value of placeholder attribute and handles clearing and re-populating on focus.
	 * @param {String} CSS selector of the element to be handled
	 * @requires Modernizr 2.0+ - http://www.modernizr.com/
	 */
	dynamicInputText: function(selector) {
		/*
		 * If modernizer tells us we can use the placeholder attribute
		 * then don't go any further
		 */
		if (Modernizr.input.placeholder){
			return false;
		}
		$(selector).each(function (i) {
			var $this = $(this);
			if ($this.attr('placeholder')) {
				$this
					.data('_default', $this.attr('placeholder')) // keep track of the original placeholder attribute value
					.val($this.data('_default'))
					.focus(function(){
						var $this = $(this);
						$this.val($.trim($this.val()));
						if ($this.val() === $this.data('_default')) {
							$this.val('');
						}
					})
					.blur(function(){
						var $this = $(this);
						$this.val($.trim($this.val()));
						if ($this.val() === "") {
							$this.val($this.data('_default'));
						}
					})
					.blur()
			}
		});
	}
	
};/* /ACCELERATOR.common */
