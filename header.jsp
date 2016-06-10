 <%-- 
    Document   : header
    Created on : Sep 19, 2015, 4:41:24 PM
    Author     : sunny
--%>
       <!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Researchers Exchange Participation</title>
        <link rel="stylesheet" href="styles/newcss.css" type="text/css"/>
    </head>
    <body>
        <div id="header">
            <ul>
                <li> <strong>Researchers Exchange Participation</strong></li>
           
                     
           
                <li><a href="user?action=about">About us</a></li>
                 
                <li> <a href="user?action=how"> How it works</a></li>
            
                
                   <c:choose>
                <c:when test="${empty sessionScope.name}">
                    <li><a href="user?action=main">Login</a></li>
                </c:when>
                <c:otherwise>
                    <li><c:out value="Hello ${sessionScope.name} !!" /></li>
                </c:otherwise>
                
            </c:choose>  
                   
                
            </ul>
           </div>
        


         
        
        
   
