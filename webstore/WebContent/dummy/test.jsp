<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<% 
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
response.setHeader("Pragma","no-cache"); //HTTP 1.0 
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ page buffer="2094kb"%>
<%@ taglib uri="/dspTaglib" prefix="dsp"%>
<%@ taglib uri="/dspCoreTaglib" prefix="core"%>
<%@ taglib uri="/taglibs-i18n" prefix="i18n"%>
<%@ taglib uri="/jstlCore" prefix="c"%>
<dsp:include page="../common/i18nInclude.jsp">
<dsp:param name="bundleName"
value="com.walmart.mtep.asda.StoreMessages" />
</dsp:include>
<dsp:importbean
bean="/com/walmart/mtep/admin/ccm/formhandler/MtepStoreCnCCutOffFormHandler" />
<dsp:importbean
bean="/com/walmart/mtep/admin/ccm/formhandler/MtepRemoteCnCReservingManagementFormHandler" />
<dsp:importbean
bean="/com/walmart/mtep/estore/security/FunctionalSecurityAccessor" />
<dsp:importbean bean="/atg/dynamo/droplet/ErrorMessageForEach" />
<dsp:importbean bean="/atg/dynamo/droplet/Switch" />
<dsp:importbean bean="/atg/dynamo/droplet/IsEmpty" />
<dsp:importbean bean="/atg/dynamo/droplet/ForEach" />
<dsp:importbean
bean="/com/walmart/mtep/util/droplet/TenantConfiguration" />
<dsp:importbean
bean="/com/walmart/mtep/admin/ccm/droplet/MtepStoreCnCLoadTypeDroplet" />
<dsp:importbean
bean="/com/walmart/mtep/admin/ccm/droplet/MtepStoreCnCCutOffDayDroplet" />

<dsp:page>
<head>
<meta http-equiv="x-ua-compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><i18n:message key="cbcm.title.editpage" /></title>
<link type="text/css" href="/storeadmin/css/adminScreens.css"
rel="stylesheet" />
<link type="text/css" href="/storeadmin/css/storeCnCScreens.css"
rel="stylesheet" />
<script type="text/javascript" src="/storeadmin/js/estoreAdmin.js"></script>
</head>
<body onkeypress="escape(event);">
<dsp:getvalueof id="form111" bean="/OriginatingRequest.requestURI"
idtype="java.lang.String">

<dsp:form formid="storecnccreatecutoff" name="storecnccreatecutoff"
action='<%=form111+"?d=12"%>' method="post">
<!-- Green Title -->
<div class="grn-title" style="margin-top:0px">Create cut-off
pattern</div>
<!-- Green Title -->
<!-- Inside Matter -->
<div class="popup_outer"><!-- Pop-UP Content -->
<div class="pop-content"><!-- Message Area -->
<dsp:droplet name="Switch">
<dsp:param bean="MtepRemoteCnCReservingManagementFormHandler.formError"
name="value" />
<dsp:oparam name="true">
<div>
<dsp:droplet name="ForEach">

<dsp:param bean="MtepRemoteCnCReservingManagementFormHandler.formExceptions"
name="array" />
<dsp:oparam name="outputStart">
<div id="errMsg" class="errMsgCB" style="margin-top:5px;margin-bottom:0px;">
</dsp:oparam>
 
<dsp:oparam name="output">
<dsp:droplet name="Switch">
<dsp:param name="value" param="element.errorCode" />
<dsp:oparam name="success">
<div class="successContent"><dsp:valueof
param="element.message" /></div>
</dsp:oparam>
<dsp:oparam name="default">
<div class="errContent" style="color:red"><dsp:valueof
param="element.message" /></div>
</dsp:oparam>
</dsp:droplet></dsp:oparam>
<dsp:oparam name="outputEnd"></dsp:oparam>
</dsp:droplet></div></dsp:oparam> 
<dsp:oparam name="false"></dsp:oparam>
</dsp:droplet>
<!-- Field Content -->
<div class="fld-content" style="margin-top:8px;margin-bottom:8px;">
<table border="0" cellspacing="0" cellpadding="0">
<tr class="fld-grid-row-nw ">
<td class="fld-grid-td-nw" style="width:60px; text-align:left" cellpadding="5"><label
for=""><strong>Reserved Template Name:*</strong></label></td>
<td ><dsp:input
bean="MtepRemoteCnCReservingManagementFormHandler.patternName" type="text"
maxlength="30" style="width:200px"></dsp:input></td>

</tr>
</table>
</div>
 
<div>
<table width="90%" border="1" cellpadding="0" cellspacing="0">
<!--  Values retained in Screen when error occurs -->

<tr>
<td class="cellHeadBoldCenter" width="10%">Cut-off</td>
<td class="cellHeadBoldCenter" width="10%">Load Details</td>
<td class="cellHeadBoldCenter" width="10%">Sat</td>
<td class="cellHeadBoldCenter" width="10%">Sun</td>
<td class="cellHeadBoldCenter" width="10%">Mon</td>
<td class="cellHeadBoldCenter" width="10%">Tue</td>
<td class="cellHeadBoldCenter" width="10%">Wed</td>
<td class="cellHeadBoldCenter" width="10%">Thu</td>
<td class="cellHeadBoldCenter" width="10%">Fri</td>

</tr>
</table>
 
</div></div></div></dsp:form></dsp:getvalueof>
</dsp:page>
