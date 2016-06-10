<%-- 
    Document   : signup
    Created on : Sep 20, 2015, 9:26:53 PM
    Author     : sunny
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>





<%--      including header page     --%>
<jsp:include page="/header.jsp"/>
<div id="leftcontainer"></div>
<section>
    <h1>${msg}</h1>
        <form action="user" method="post">
             <input type="text" name="action" value="create" hidden>
         <label>Name*</label>
         <input type="text" name="name" required
                /><br>

         <label>Email*</label>
         <input type="text" name="email" required
                /><br>
         <label>Password*</label>
         <input type="password" name="password" 
                       /><br>
         
         <label>Confirm Password*</label>
         <input type="password" name="ConfirmPassword" required
                /><br>
         
          <label>&nbsp;</label>
          <input type="submit" value="Create Account"/><br>
          
        </form>
          </section>






<%--      including footer page     --%>
<jsp:include page="/footer.jsp"/>
