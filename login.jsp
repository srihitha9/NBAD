<%-- 
    Document   : login
    Created on : Sep 20, 2015, 2:03:06 PM
    Author     : sunny
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--      including header page     --%>
 <jsp:include page="/header.jsp"/>

<div id="leftcontainer"></div>


 <section>
     
            <p><i>${msg}</i></p>
            <form action="user" method="post">
                <input type="text" name="action" value="login" hidden>
                <label>Email Address*</label>                
                <input type="text" name="email" /><br>
                
          
                <label>Password*</label>
                <input type="password" name="password" 
                       /><br>
                <label>&nbsp;</label>    
                <input type="submit" value="Log In"/><br>
                
                <br> <label>&nbsp;</label>

                <p><a href="signup.jsp">Sign up for a new account</a></p>
            
                
            </form>
        </section>




<%--      including footer page     --%>
<jsp:include page="/footer.jsp"/>

   