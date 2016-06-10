<%@page contentType="text/html" pageEncoding="UTF-8"%>
     

<jsp:include page="/header.jsp"/>

<div id="leftcontainer">
    <span><b>Contact</b></span>
  
    <p><a href="main.jsp"> &lt;&lt;Back to main page; </a> </p></div>
    
   
    
<section>
        <form action="study" method="post">
             <input type="text" name="action" value="contact" hidden="">
         <label>Name*</label>
         <input type="text" name="name" required
                       value="${contact.name}"/><br>

         <label>Email*</label>
         <input type="text" name="email" required                
                value="${contact.Email}"/><br>
         
         <label>Message*</label>
         <input id="msg" type="text" name="message" required                
                value="${contact.message}"/><br>
                  
          <label>&nbsp;</label>
          <input type="submit" value="Submit"/><br>
          
        </form>
          </section>

                
                
                <jsp:include page="/footer.jsp"/>
