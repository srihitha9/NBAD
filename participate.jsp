<%-- 
    Document   : participate
    Created on : Sep 26, 2015, 11:39:16 AM
    Author     : sunny
--%>
<%@page import="murach.assign1.Study"%>
<%@page import="java.util.ArrayList"%>
<%@page import="murach.db.StudyDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<jsp:include page="/header.jsp"/>

<jsp:include page="/sidenavigation.jsp"/>

<section>
    <span> <b>Studies</b></span><br>
    <table>
        <tr class="d0">
        <th>Study Name</th>
        <th>Image</th>
        <th>Question</th>
        <th>Action</th>
        </tr>
        <c:forEach var="studiesList" items="${openList}"> 
            
            <tr class="d0">
            <td>${studiesList.getSname()}</td>
        
            <td><img src="${studiesList.getImageURL()}" alt="img" width="20" height="20"></td>
            <td>${studiesList.getQuestion()}</td>
            <td><input type="button" value="Participate" onclick="location.href='study?action=participate&amp;StudyCode=${studiesList.getScode()}'   " />
                 </tr> 
            </c:forEach>
        
    </table>
    
    
</section>



<jsp:include page="/footer.jsp"/>

   