    <%-- 
    Document   : question
    Created on : Sep 26, 2015, 12:58:31 PM
    Author     : sunny
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp"/>

<jsp:include page="/sidenavigation.jsp"/>

<section>   

    <span><b>Question</b></span><br>




    <img src="styles/tree.jpg" alt="image" width="200" height="200">
    
        <br>

        <form action="study" method="post">
            <input type="hidden" name="action" value="answer"/>
            <input type="hidden" name="StudyCode" value="${study.getScode()}"/>
            <!--<img src="${study.getImageURL()} width=100 height=250 alt=${study.getImageURL()}"/>-->
            <div>${study.getQuestion()}(1 strongly agree - 7 strongly disagree)<br><br>
                <select name ="choice">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
                <input type="submit" value="Submit"/>
             </div>
            </form>


</section>




<jsp:include page="/footer.jsp"/>



