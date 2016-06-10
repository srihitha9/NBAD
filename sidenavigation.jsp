
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<nav class="vertical">


<ul>
    <li><a href="#">Coins(<c:out value="${sessionScope.theUser.studies+sessionScope.theUser.participation}"></c:out>)</a></li>
    <li><a href="#">Studies(${sessionScope.theUser.studies})</a></li>
    <li><a href="#">Participation(${sessionScope.theUser.participation})</a></li>
    <li><a></a></li>
    <li><a href="user?action=home">Home</a></li>
    <li><a href="study?action=participate">Participate</a></li>
    <li><a href="study?action=studies">My studies</a></li>
    <li><a href="recommend.jsp">Recommend</a></li>
    <li><a href="contact.jsp">Contact</a></li>
</ul>

</nav>



    <a href="sidenavigation.jsp"></a>