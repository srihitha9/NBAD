<%-- 
    Document   : recommend
    Created on : Sep 20, 2015, 9:45:02 PM
    Author     : sunny
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/header.jsp"/>

<div id="leftcontainer">
    <div id="qwerty"><b>Recommend to a friend</b></div>
            <p><a href="main.jsp"> &lt;&lt; Back to main page </a> </p>
</div>
            <section>
        <form action="study" method="post">
            
             <input type="text" name="action" value="recommend" hidden="">
         <label>Name*</label> 
         <input type="text" name="name" required
                value="${recommend.name}"><br>

         <label>Email*</label>
         <input type="text" name="email" required
                value="${recommend.email}"/><br>
         
         <label>Friend's Email*</label>
         <input type="text" name="femail" required
                value="${recommend.femail}"/><br>
         
         <label>Message*</label>
         <input id="msg1" type="text" name="message" required
                value="${recommend.message}"><br>
                  
          <label>&nbsp;</label>
          <input type="submit" value="Submit"/><br>
          <p><strong><i>When your friend logs in and participates in one user study, you'll get 2 coins bonus</i></strong></p>
          
        </form>
          </section>


<jsp:include page="/footer.jsp"/>

