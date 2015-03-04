<%@page contentType='text/html' pageEncoding='UTF-8'%>


<%

String code= request.getParameter("err");


response.sendRedirect("/lemon/commerce/global/error?err="+code);%>