<%-- 
    Document   : how
    Created on : Sep 20, 2015, 9:45:07 PM
    Author     : sunny
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--      including header page     --%>
<jsp:include page="/header.jsp"/>
<div id="leftcontainer"></div>


<section>
    <fieldset>
        <p><b>How it works</b></p><br>
        <p>This site was built to help researchers conduct their user studies.</p>
        <p>1 participation=1 coin</p> 
        <p><b>To Participate,</b> go to"Participate" section and choose a study to complete.</p>
        <p><b>To get participants,</b>submit your user study here to start getting Participations. In order to do so,you must have enough coins in Your account.</p>
    </fieldset>
</section>
<form action="user">
    <input type="text" name="action" value="how" hidden="">
</form>

<%--      including footer page     --%>
<jsp:include page="/footer.jsp"/>
    