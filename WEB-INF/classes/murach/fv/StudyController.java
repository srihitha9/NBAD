/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.fv;

import murach.assign1.Study;
import murach.assign1.User;
import murach.db.StudyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mail.MailHelper;
import murach.assign1.Answer;
import murach.db.AnswerDB;
import murach.db.UserDB;

/**
 *
 * @author sunny
 */
public class StudyController extends HttpServlet {

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
        ServletContext sc = getServletContext();
        RequestDispatcher requestDispatcher;
        String url = "";
        HttpSession s = request.getSession();
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        // out.println("<h1> action: " + action + "</h1>");
        StudyDB studydb = StudyDB.getInstance();
        if (action == null) {
            User u = (User) s.getAttribute("theUser");
            if (u != null) {
                requestDispatcher = request.getRequestDispatcher("/main.jsp");
                requestDispatcher.forward(request, response);

            } else {
                requestDispatcher = request.getRequestDispatcher("/home.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("participate")) {
            UserDB udb = new UserDB();
            User u = (User) s.getAttribute("theUser");
            if (u != null) {
                String sCode = request.getParameter("StudyCode");
                if (sCode == null) {
                    StudyDB studyd1b = StudyDB.getInstance();
                    ArrayList<Study> sList = studyd1b.getStudies();
                    ArrayList<Study> openList = new ArrayList<Study>();
                    for (int i = 0; i < sList.size(); i++) {
                        if (sList.get(i).getSstatus().equals("start")) {
                            openList.add(sList.get(i));
                        }
                    }
                    int participation = u.getParticipation();
                    udb.updateParticipations(u.getEmail(), participation + 1);
                    u = udb.getUser(u.getEmail());
                  //  u = userdb.getUser(u.getEmail());
                    request.setAttribute("openList", openList);
                    requestDispatcher = request.getRequestDispatcher("/participate.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    String userFlag = (String) request.getParameter("user");
                    out.println(userFlag);
                    if (userFlag != null) {
                        StudyDB studyd1b = StudyDB.getInstance();
                        u.setParticipation(u.getParticipation() + 1);
                        s.setAttribute("theUser", u);
                        ArrayList<Study> sList = studyd1b.getStudies();
                        ArrayList<Study> openList = new ArrayList<Study>();
                        for (int i = 0; i < sList.size(); i++) {
                            if (sList.get(i).getSstatus().equals("start")) {
                                openList.add(sList.get(i));
                            }
                        }
                        out.println(userFlag);
                        request.setAttribute("openList", openList);
                        requestDispatcher = request.getRequestDispatcher("/participate.jsp");
                        requestDispatcher.forward(request, response);
                    } else {
                        out.println(u);
                        out.println("<h1>in else part got study code" + sCode + "</h1>");
                        StudyDB studyd1b = StudyDB.getInstance();
                        String question = studyd1b.getStudy(sCode).getQuestion();
                        request.setAttribute("question", question);
                        request.setAttribute("study", studyd1b.getStudy(sCode));
                        requestDispatcher = request.getRequestDispatcher("/question.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
            } else {
                out.println("<h1>use session exis</h1>");
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);

            }

        } else if (action.equals("edit")) {

            User u = (User) s.getAttribute("theUser");
//             String email = u.getEmail();
            if (u != null) {
                String studyCode = (String) request.getParameter("studyCode");
                //Study study = studydb.getStudy(studyCode);
               //<harsha> StudyDB studydb1 =  new StudyDB();    
                StudyDB studydb1 = StudyDB.getInstance();
                s.setAttribute("StudyCode", studyCode);
                request.setAttribute("studyRecord", studydb1.getStudy(studyCode));
                requestDispatcher = request.getRequestDispatcher("/editstudy.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("update")) {
            User u = (User) s.getAttribute("theUser");
       
            if (u != null) {
                //Study study1 = (Study) request.getAttribute("Study");

                String studyCode = request.getParameter("studycode");
                String studyName = request.getParameter("studyName");
                String questiontext = request.getParameter("questiontext");
   //             String imageurl = request.getParameter("Image");
                String participants = request.getParameter("participants");
                String description = request.getParameter("description");
                
                int p = Integer.parseInt(participants);
                    StudyDB studydb2 = StudyDB.getInstance();

                    Study s3 = null;
                    s3 = studydb2.getStudy(studyCode);

                    System.out.println("in user not equal to null" + studyCode);
                    s3.setScode(studyCode);
                    s3.setSname(studyName);
                    s3.setQuestion(questiontext);

                    if (s3.getReqParticipants() != p && p > 0) {
                        u.setStudies(u.getStudies()- s3.getActParticipants() + p);
                    }
                    s3.setActParticipants(p);
                    s3.setDescription(description);
                    s3.setReqParticipants(Integer.parseInt(participants));
                    studydb2.updateStudy(studyCode, s3);
                    request.setAttribute("userSlist", studydb2.getStudies(u.getEmail()));
                    requestDispatcher = request.getRequestDispatcher("/studies.jsp");
                    requestDispatcher.forward(request, response);
                    
                
//                String Email = u.getEmail(); 
//   //             StudyDB studydb1 = StudyDB.getInstance();
//                int p = Integer.parseInt(participants);
//                
//                StudyDB studydb2 = new StudyDB();
//                Study study = new Study();
//                study.setSname(studyName);
//                study.setQuestion(questiontext);
//   //             study.setImageURL(imageurl);
//                study.setReqParticipants(p);               
//                study.setDescription(description);
//                
//                
//                studydb2.updateStudy(studyCode,study);
//                request.setAttribute("updatestudy", studydb2.getStudies(Email));
//                
//                


               

            } else {
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("add")) {
            out.println("<h1>add page exis</h1>");
            User u1 = (User) s.getAttribute("theUser");
            out.println("<h1>add page exis</h1> sds " + u1);
            if (u1 != null) {

                String studyName = request.getParameter("studyName");
                String questiontext = request.getParameter("questiontext");
                String participants = request.getParameter("participants");
                String description = request.getParameter("description");
                String email = u1.getEmail();
                String status = "start";
                int p = Integer.parseInt(participants);

//                String study = request.getParameter(study);
                Study study = new Study();
                study.setEmail(email);
                study.setSname(studyName);
                study.setScode(action);
                study.setQuestion(questiontext);
                study.setActParticipants(p);
                study.setDescription(description);
                study.setScode(studyName + "_" + System.currentTimeMillis());
                study.setSstatus(status);

//              StudyDB studydb2 = StudyDB.getInstance();
                StudyDB studydb3 = new StudyDB();
                studydb3.addStudy(study);
//                 Study s3 = new Study();              
                out.println(studydb3.studyList.size());
                u1.setStudies(u1.getStudies() + p);
                s.setAttribute("theUser", u1);
                request.setAttribute("userSlist", studydb3.getStudiesFor("start"));
                requestDispatcher = request.getRequestDispatcher("/studies.jsp");
                requestDispatcher.forward(request, response);

            }
        } else if (action.equals("start")) {
            User u1 = (User) s.getAttribute("theUser");
            if (u1 != null) {            
                try {
                    String sCode = request.getParameter("studyCode");
                    StudyDB db = StudyDB.getInstance();
                    Study studyStart = null;
                    studyStart = db.getStudy(sCode);
                    out.println("2 in start and user is not null" + sCode);
                    studyStart.setSstatus("stop");
                    db.setStatus(studyStart);
                    out.println("3 in start and user is not null");
                    
                    ArrayList<Study> sList = db.getStudies(u1.getEmail());
                    
                    //db.setStatus(studyStart);
                    out.println("4 in start and user is not null");
                    request.setAttribute("userSlist", sList);   
                    requestDispatcher = request.getRequestDispatcher("/studies.jsp");
                    requestDispatcher.forward(request, response);
                  
                } catch (SQLException ex) {
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("stop")) {
            User u1 = (User) s.getAttribute("theUser");
            if (u1 != null) {
                try {
                    String sCode = request.getParameter("studyCode");
                    StudyDB db = StudyDB.getInstance();
                    Study studyStart = null;
                    studyStart = db.getStudy(sCode);
                    out.println("2 in start and user is not null" + sCode);
                    studyStart.setSstatus("start");
                    db.setStatus(studyStart);
                    out.println("3 in start and user is not null");
                    
                    ArrayList<Study> sList = db.getStudies(u1.getEmail());
                    
                    //db.setStatus(studyStart);
                    out.println("4 in start and user is not null");
                    request.setAttribute("userSlist", sList);   
                    requestDispatcher = request.getRequestDispatcher("/studies.jsp");
                    requestDispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(StudyController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }

        } else if (action.equals("studies")) {

            User u1 = (User) s.getAttribute("theUser");
            if (u1 != null) {
 //               StudyDB sdb = StudyDB.getInstance();
                StudyDB sdb = new StudyDB();

                ArrayList<Study> userStudyList = sdb.getStudies(u1.getEmail());
                request.setAttribute("userSlist", userStudyList);
                requestDispatcher = request.getRequestDispatcher("/studies.jsp");
                requestDispatcher.forward(request, response);
            } else {

                requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (action.equals("recommend")) {

            String name = request.getParameter("name");
            String Email = request.getParameter("email");
            String Frdemail = request.getParameter("femail");
            String Message = request.getParameter("message");

            MailHelper mh = new MailHelper();
            mh.sendMail(name, Frdemail, Message);
            request.setAttribute("recommend", name);

            requestDispatcher = request.getRequestDispatcher("/confirmr.jsp");
            requestDispatcher.forward(request, response);

        } else if (action.equals("contact")) {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String message = request.getParameter("message");

            MailHelper mh1 = new MailHelper();
            mh1.sendMail(name, email, message);
            request.setAttribute("contact", name);

            requestDispatcher = request.getRequestDispatcher("/confirmc.jsp");
            requestDispatcher.forward(request, response);
        }
//        } else if (action.equals("studies")) {
//            User u1 = (User) request.getAttribute("theUser");
//            if (u1 != null) {
//                StudyDB studydb1 = new StudyDB();
//                String Email = (String) request.getAttribute("Uemail");
//                ArrayList<Study> studies = studydb1.getStudies(Email);
//                request.setAttribute("study", studies);
//                requestDispatcher = request.getRequestDispatcher("/studies.jsp");
//                requestDispatcher.forward(request, response);
//            } else {
//                requestDispatcher = request.getRequestDispatcher("/login.jsp");
//                requestDispatcher.forward(request, response);
//            }
//        } 
            else if (action.equals("answer")) {
            User u1 = (User) s.getAttribute("theUser");
            if (u1 != null) {
                String sCode = request.getParameter("StudyCode");
                String choice = request.getParameter("choice");
                int ch = Integer.parseInt(choice);
                AnswerDB answerDB = new AnswerDB();
                Answer answer = new Answer();
                answer.setChoice(ch);
                answer.setEmail(u1.getEmail());
                answer.setScode(sCode);
                answerDB.AddAnswer(answer);
                request.setAttribute("openList", studydb.getStudiesFor("start"));
                int participants = u1.getParticipation();
                UserDB userDB = new UserDB();
                userDB.updateParticipations(u1.getEmail(), participants+1);
                u1.setParticipation(participants+1);
                HttpSession session = request.getSession();
                session.setAttribute("theUser", u1);
                Study study = studydb.getStudy(sCode);
                study.setActParticipants(study.getActParticipants()+1);
                studydb.updateStudy(sCode, study);
                requestDispatcher = request.getRequestDispatcher("/participate.jsp");
                requestDispatcher.forward(request, response);
              }
        } else {
            sc.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}// </editor-fold>  

