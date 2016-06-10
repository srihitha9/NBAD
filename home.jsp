<%-- 
    Document   : index
    Created on : Sep 19, 2015, 5:04:56 PM
    Author     : sunny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
            <c:when test="${cookie.containsKey('HostName') && cookie.containsKey('PortNumber')}">
            </c:when>
            <c:otherwise>
                <%
                    Cookie ck = new Cookie("HostName", request.getServerName());
                    response.addCookie(ck);
                    ck = new Cookie("PortNumber", request.getServerPort() + "");
                    response.addCookie(ck);
                %>
            </c:otherwise>
        </c:choose>
<!--<!DOCTYPE html>-->
<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment</title>
         <link href="styles/newcss.css" rel="stylesheet" type="text/css">
    </head>
    <body>-->
<jsp:include page="/header.jsp"/>
<div id="leftcontainer"></div><section>   
    <img src="styles/capture.PNG" width="450" height="400" alt="welcome">
</section>


<jsp:include page="/footer.jsp"/>

<!--
</body>-->
<!--</html>-->