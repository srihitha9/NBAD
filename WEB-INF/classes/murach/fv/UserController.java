/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.fv;

import murach.assign1.User;
import murach.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.MailHelper;
import murach.db.TempUserDB;
import passwd.PasswordUtil;

/**
 *
 * @author sunny
 */
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In do post");
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        //out.println("<h1>" + message + "</h1>");
        RequestDispatcher requestDispatcher;

        String action = request.getParameter("action");
        //out.println("<h1>" + action + "</h1>");
        String url = "";
        String msg = "error";
        HttpSession s = request.getSession();
        if (action == null) {
            url = "home.jsp";
        } else if (action.equals("login")) {
            // out.println("<h1>" + action + "</h1>");
            User u;
            UserDB userdb1 = new UserDB();
            url = "login.jsp";
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            String hashedPassword;
                    String salt = "";
                    String passwordAndSalt = "";
                    String saltedAndHashedPassword;
                    try {
                        hashedPassword = PasswordUtil.hashPassword(pwd);
                        salt = PasswordUtil.getSalt();
                        passwordAndSalt = hashedPassword + salt;
                        saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(passwordAndSalt);                    

                    } catch (NoSuchAlgorithmException ex) {
                        hashedPassword = ex.getMessage();
                        saltedAndHashedPassword = ex.getMessage();
                    }
            u = userdb1.getUser(email);          
            if (u != null && (u.getPassword().equals(pwd))) {
//                     if(userdb1.validateUser(email, pwd)){
                  // check strength requirements
                    String message;
                    try {
                  //      PasswordUtil.checkPasswordStrength(pwd);
                        message = "";
                    } catch (Exception e) {
                        message = e.getMessage();
                    }
                    request.setAttribute("message", message);        

                    // hash and salt password
                    HttpSession s1 = request.getSession();
                    s1.setAttribute("theUser", u);
                    s1.setAttribute("name", u.getUname());                  
                    
                    request.setAttribute("hashedPassword", hashedPassword);
                    request.setAttribute("salt", salt);
                    request.setAttribute("saltedAndHashedPassword", saltedAndHashedPassword);    
                requestDispatcher = request.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(request, response);
            } else {

                msg = "invalid username and password combination";
                request.setAttribute("msg", msg);
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("create")) {
            User u;

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String pwd = request.getParameter("password");
            String confirmpwd = request.getParameter("ConfirmPassword");
            String salt = "";
             String hashedPassword;
                    String passwordAndSalt = "";
                    String saltedAndHashedPassword;
                    try {
                        hashedPassword = PasswordUtil.hashPassword(pwd);
                        salt = PasswordUtil.getSalt();
                        passwordAndSalt = hashedPassword + salt;
                        saltedAndHashedPassword = PasswordUtil.hashAndSaltPassword(passwordAndSalt);                    

                    } catch (NoSuchAlgorithmException ex) {
                        hashedPassword = ex.getMessage();
                        saltedAndHashedPassword = ex.getMessage();
                    }
            
            UserDB db = new UserDB();
            TempUserDB tdb = new TempUserDB();

            if (pwd.equals(confirmpwd)) {
                u = new User(name, email, saltedAndHashedPassword, 0, 0, 0);
                String token = UUID.randomUUID().toString();
                try {
                    tdb.addTempUser(email, name, saltedAndHashedPassword, token);
                    String tokenMessage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/user?action=activate&token=" + token;
                   // String link = "http://localhost:8935/assi/user?action=activate&token=" + token;
                    String to = email;
                    String from = "nihal.vtz011@gmail.com";
                    String firstName = request.getParameter("name");
                    String subject = "Hi This is a Sample mail";
                    String body = "Dear " + firstName + ",\n\n Click URL to activate your account: "
                            + tokenMessage;

                    boolean isBodyHTML = false;
                    MailHelper mh = new MailHelper();
                    mh.sendMail(firstName, to, body);

                    request.setAttribute("msg", "Check your inbox to activate your account");
                    requestDispatcher = request.getRequestDispatcher("/login.jsp");
                    requestDispatcher.forward(request, response);
                } finally {
                    out.close();
                }
            } else {
                request.setAttribute("msg", "Entered Passwords are not matching");
                requestDispatcher = request.getRequestDispatcher("/signup.jsp");
                requestDispatcher.forward(request, response);

            }
        } else if (action.equals("activate")) {
            TempUserDB tdb = new TempUserDB();
            UserDB db = new UserDB();
            HttpSession session = request.getSession();

            String token = request.getParameter("token");
            try {
                User u = tdb.getUser(token);
                if (token.equals(u.getToken())) {
                    db.addUser(u);
                    String email = u.getEmail();
                    tdb.removeUser(u);
                    session.setAttribute("name", u.getUname()); 
                    session.setAttribute("theUser",db.getUser(u.getEmail()));                    
                    requestDispatcher = request.getRequestDispatcher("/main.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/signup.jsp");
                    request.setAttribute("msg", "Activation Failed !!!");
                    requestDispatcher.forward(request, response);
                }
            } finally {
                out.close();
            }
        } else if (action.equals("how")) {
            out.println("<h1>" + action + "</h1>");
            User u = (User) s.getAttribute("theUser");

            if (u == null) {
                out.println("<h1>" + action + " user object is not null</h1>");
                out.println("<h1>" + u + "</h1>");
                requestDispatcher = request.getRequestDispatcher("/how.jsp");
                requestDispatcher.forward(request, response);
            } else {
                out.println("<h1>" + action + " user object null </h1>");
                requestDispatcher = request.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("about")) {

            User u = (User) s.getAttribute("theUser");
            if (u == null) {
                requestDispatcher = request.getRequestDispatcher("/about.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("/aboutl.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("home")) {

            User u = (User) s.getAttribute("theUser");
            if (u == null) {

                requestDispatcher = request.getRequestDispatcher("/home.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("main")) {

            User u = (User) s.getAttribute("theUser");
            if (u == null) {
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
