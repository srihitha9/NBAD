<%-- 
    Document   : footer
    Created on : Sep 19, 2015, 4:41:46 PM
    Author     : sunny
--%>


   <% Cookie hc=new Cookie("host",request.getServerName());
Cookie pc=new Cookie("port",Integer.toString(request.getServerPort()));
hc.setPath("/");
pc.setPath("/");
response.addCookie(hc);
response.addCookie(pc);
%>
    <div id="footer">
        <h2>NBAD team@Research Exchange Participations</h2>
        <% 


Cookie[] cookies=request.getCookies();
Cookie c=null;
if(cookies!=null){        
for(int i=1;i<cookies.length;i++){
    c=cookies[i];
    out.print(" | " + c.getName() + ":"+ c.getValue() + " | ");

}}else{
        out.print(" | ");
        }
%>
    </div>

</body>
</html>

