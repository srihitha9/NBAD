<%-- 
    Document   : newstudy
    Created on : Sep 22, 2015, 10:27:41 AM
    Author     : sunny
--%>

<jsp:include page="/header.jsp"/>


  <div id="leftcontainer">
    
      <div id="qwerty">  <b>Adding a study</b><br>  </div>
    <p>    <a href="main.jsp"> &lt;&lt; Back to the main page</a></p>
  </div>
<section>  
    <form action="study">
        <input type="text" name="action" value="add" hidden="">
       
    <label> Study Name*</label>
    <input type="text" name="studyName"/><br>
    <label>Question Text*</label>
    <input type="text" name="questiontext"/><br>
    <P><label>Image*</label>
        <input type="file"></p><br>
    <label>#Participants*</label>
    <input type="text" name="participants"/><br>
    <label>Description*</label>
    <input type="text" name="description"/><br>
    
    <label>&nbsp;</label>
    <input type="submit" value="Submit"/>
    

    </form>
</section>

<jsp:include page="/footer.jsp"/>

