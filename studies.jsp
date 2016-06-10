<%-- 
    Document   : studies
    Created on : Sep 22, 2015, 10:08:27 AM
    Author     : sunny
--%>
<%@page import="murach.assign1.Study"%>
<%@page import="java.util.ArrayList"%>
<%@page import="murach.db.StudyDB" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp"/>



<span> <b>My Studies</b><br></span>    

<p><a href="newstudy.jsp">Add a new study</a></p>
<p>  <a href="main.jsp"> &lt;&lt; Back to the main page</a></p>
<section>
    <form>
        <table>

            <tr class="d0">
                <th>Study Name</th>
                <th>Requested Participants</th>
                <th>Participations</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="study" items="${userSlist}">
                <c:choose>
                    <c:when test="${study.getSstatus()=='start'}">
                        <c:set var="Sstatus" value="start"/>
                    </c:when>    
                    <c:otherwise>
                        <c:set var="Sstatus" value="stop"/>
                    </c:otherwise>
                </c:choose>  
                <tr class="d0">
                    <td>${study.getSname()}</td>
                    <td>${study.getReqParticipants()}</td>
                    <td>${study.getActParticipants()}</td>

                    <td><input type="button" name="startButton" value="${study.getSstatus()}" onclick="location.href = 'study?action=${Sstatus}&amp;studyCode=${study.getScode()}'"/></td> 
                    <td><input type="button" name="EditButton" value="Edit" onclick="location.href = 'study?action=edit&amp;studyCode=${study.getScode()}'"/></td> 


                </tr>
            </c:forEach> 

        </table>
    </form>
</section>


<jsp:include page="/footer.jsp"/>


