<%-- 
Document   : editstudy
Created on : Sep 22, 2015, 10:47:02 AM
Author     : sunny
--%>
<%@page import="murach.assign1.Study"%>
<%@page import="murach.db.StudyDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



        <jsp:include page="/header.jsp"/>

        <div id="leftcontainer">


            <div id="qwerty"> <b>Editing a new study</b><br>  </div>
            <p><a href="main.jsp"> &lt;&lt; Back to the main page</a></p>
        </div>
        <section>   
            <form action="study" method="post">
                <input type="hidden" name="action" value="update" >
                <input type="hidden" name="studycode"                       
                       value="${studyRecord.getScode()}"/>
                          <label> Study Name*</label>
                <input type="text" name="studyName" required                     
                      value="${studyRecord.getSname()}"/><br>
                <label>Question Text*</label>
                <input type="text" name="questiontext" required                       
                      value="${studyRecord.getQuestion()}"/><br>
                <P><label>Image*</label>
                    <input type="file" ></p><br> 
                <label>#Participants*</label>
                <input type="text" name="participants" required
                        value="${studyRecord.getReqParticipants()}"/><br>
                <label>Description*</label>
                <input type="text" name="description" required
                           value="${studyRecord.getDescription()}"/><br>
                <label>&nbsp;</label>

                <input type="submit" value="Update"/>


            </form>
        </section>




        <jsp:include page="/footer.jsp"/>
    
