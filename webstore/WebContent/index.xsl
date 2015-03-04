<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
		<head>
		
		
	<title>ASDA API Spec Document</title>
	<link rel="stylesheet" href="../js/development-bundle/themes/base/minified/jquery.ui.all.css"/>
	<script src="../js/development-bundle/jquery-1.8.2.js"></script>
	<script src="../js/development-bundle/ui/minified/jquery.ui.core.min.js"></script>
	<script src="../js/development-bundle/ui/minified/jquery.ui.widget.min.js"></script>
	<script src="../js/development-bundle/ui/minified/jquery.ui.position.min.js"></script>
	<script src="../js/development-bundle/ui/minified/jquery.ui.menu.min.js"></script>
	<script src="../js/development-bundle/ui/minified/jquery.ui.accordion.min.js"></script>
	
	
	<style>
	
	body {
	font-size: 75%;
	font-family: "Trebuchet MS", "Arial", "Helvetica", "Verdana", "sans-serif";
}

table {
	font-size: 1em;
}

.demo-description {
	clear: both;
	padding: 12px;
	font-size: 1.3em;
	line-height: 1.4em;
}

.ui-draggable, .ui-droppable {
	background-position: top;
}
	
	
	
	.wmTopNav {
    padding-top: 10px;
}
	.clearfix:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    line-height: 0;
    visibility: hidden;
}
.wmBody {
    position: relative;
}
.newhomepagestyle {
    background: none repeat scroll 0 0 #FFFFFF;
    margin: 0 auto 10px;
    width: 1000px;
}

.wmLeftSide {
    float: left;
    width: 22%;
}
.wmLeftSide {
    display: block;
}

.wmContent {
    float: left;
}
.wmContent {
    width: 78%;
}

	
	table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
	<script>
	$(function() {
		$( "#menu" ).menu({
			select: function( event, ui ) {
			event.preventDefault();
			
				var link = ui.item.children( "a:first" );
				if ( link.attr( "target" ) || event.metaKey || event.shiftKey || event.ctrlKey ) {
					return;
				}
				var url=link.attr( "href" );
				$.ajax({
				url:url,
				type:"GET"
				
				}).done(function(data){
				$("#content").replaceWith(data);
				});
				return false;
			}
		});
	});


	 
	$(function() {
		$( "#accordion" ).accordion();
	});
	function reloadPage(){
	
	location.reload();
	}
	</script>
	<style>
	.ui-menu { width: 200px; margin-bottom: 2em; }
	</style>
		
		</head>
		<body>
		<div id="home" class="newhomepagestyle">
		
		<div class=" wmTopNav clearfix ui-accordion-header  ui-state-default ui-accordion-header-active ui-state-active ui-corner-top ui-accordion-icons">
<div style="text-align:center">
<p>
<h3>ASDA Mobile REST API Specification Wiki</h3></p></div>
</div>
<br></br>
<div class="wmBody clearfix">
<div class="wmLeftSide">
<ul id="menu">
 <li>
<a href="javascript:void(0) ;" onclick="reloadPage();">Home</a>
</li>
<xsl:for-each select="mainpage/topic">

<li>
<a><xsl:attribute name="href"><xsl:value-of select="url"/> </xsl:attribute><xsl:value-of select="title"/> </a>

</li>


</xsl:for-each>

</ul>


</div>

<div id="content" class="wmContent">
 <div id="accordion">
 <h3>Welcome</h3>
<div>

<h5>Introduction</h5>
<p>

The API guide is a reference to all the APIs exposed by the ASDA grocery web services  (ASDA GWS) program including details on syntax, usage, request parameters, and response groups.
</p>
<p>In order to use the ASDA GWS, developers have to register themselves on the ASDA GWS portal. Registration is free on the portal. Once developers are successfully registered, they are provided with an API key and a secret key.</p>
<p>The secret key can be used to create a unique signature. The API key and signature has to be used on every API request. API’s are executed only after the verification of the API key and signature.</p>
<p>Most of the API’s can be invoked only after login API is executed successfully. Any login required API that has not been preceded by successful login will result in an error. The following cookies need to be sent over on each API request:</p>
<p>On a login success, the session id returned in the response that has to be passed the clients as a query parameter to all other API requests. All API’s invoked should pass the session id in a cookie as well. Session id is passed in the query parameter if API consumers cannot send in session cookie as part of the request. </p>
<p>In case of an application level exception, pagination parameters will not be validated.

Parameters can be passed in the request body or in request url for all the API’s whose request method is POST. An exception to this is the parameters apikey and signature, which should always be sent in the request URL itself. The incoming parameters to the API’s should be URL encoded and the Content-Type for the request should be ‘application/x-www-form-urlencoded’. This is to ensure that all the special characters present in the request are safely transferred across to the server.
For all API’s except View Slot API, response will contain either deliveryAddress or cncStoreAddress at a time depending on the current deliveryOption. The other will be an empty array. For ViewSlot, both details will be shown.
</p>


</div>

<h3>Examples</h3>
<div>
<p>
<ul>
<li>	All API operation names in the API request URL, are case in sensitive.</li>
 E.g. : https://groceriesservices.asda.com/api/user/LOGIN?....<br></br>
           https://groceriesservices.asda.com/api/order/VIEW?....
<li> All URL parameter names should be in lower case and values are case in sensitive.</li>
<li> Multiple values to be passed, separated by commas and not to be in brackets-{} implies values passed as an array.</li>
E.g.: itemIds=1112, 2222 instead of itemIds= {1112, 2222}
<li> Quantities will not always be numbers. Hence they are not validated for numbers.</li>
<li> When a response array is null, it will not have any child elements.</li>
</ul>

 <table class="gridtable">
	 
	<tr>
	<th>Parameter Name</th><th>Description</th><th>Mandatory</th><th>Value</th>
	</tr>

	<tr>
	<td>apikey</td>
	<td>The key that was returned when you registered on the ASDA web services portal site.</td>
	<td>Yes</td>
	<td>API Key Value.</td>
	</tr>
	
	<tr>
	<td>sig</td>
	<td>The signature is created by the client by using the api key, private key (shared secret), and time stamp.</td>
	<td>Yes</td>
	<td>Client generated value.</td>
	</tr>
	
	<tr>
	<td>responsegroup</td>
	<td>request header and the minimum information for the operation being invoked You can tailor your response by explicitly asking for extended response group. The request header on the response can be suppressed if the “request” parameter on the query is set to false.</td>
	<td>No</td>
	<td>basic/extended</td>
	</tr>
	
	
	<tr>
	<td>format</td>
	<td>Format of the output response
By default, response representation will be in JSON.</td>
	<td>No</td>
	<td>Default: JSON
Allowed values: JSON/XML
</td>
	</tr>
	
	<tr>
	<td>headerenable</td>
	<td>Enable header response. Header response contains requestUri, operation, opId, apiKey, signature, httpMethod, timestamp, parameters and httpHeader. By default header is disabled, it can be enabled if  the “headerenable” parameter on the query is set to true.</td>
	<td>No</td>
	<td>Default:false
	Allowed values:true/false
</td>
	</tr>
	
	
	
	 </table>
</p>

</div>

</div> 
</div>

</div>
	<br></br>
<div class=" wmTopNav clearfix ui-accordion-header  ui-state-default ui-accordion-header-active ui-state-active ui-corner-top ui-accordion-icons">
<div style="text-align:center">
<p>
© 2013 Wal-Mart Stores, Inc.</p></div>
</div>	
		
		
		</div>
		</body>
		
		</html>
	</xsl:template>
</xsl:stylesheet>