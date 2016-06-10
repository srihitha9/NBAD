/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import murach.assign1.Answer;
import murach.assign1.Study;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import murach.assign1.User;
import murach.data.ConnectionPool;
import murach.data.DBUtil;

/**
 *
 * @author sunny
 */
public class StudyDB {

    public ArrayList<Study> studyList;
    private static StudyDB myObj;

    public static StudyDB getInstance() {
        if (myObj == null) {
            myObj = new StudyDB();
        }
        return myObj;
    }

    public StudyDB() {

        this.studyList = new ArrayList<Study>();
        ArrayList<Answer> answerList = new ArrayList<Answer>();

    }

    public Study getStudy(String Scode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = " SELECT * FROM Study " + " WHERE SCode = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Scode);
            rs = ps.executeQuery();

//            for (int i = 0; i < studyList.size(); i++) {
//                if (studyList.get(i).getScode().equals(Scode)) {
//                    return studyList.get(i);
//                }
//            }
            Study study = null;
            while (rs.next()) {
                study = new Study();
                study.setScode(rs.getString("Scode"));
                study.setSname(rs.getString("Sname"));
                study.setEmail(rs.getString("Email"));
                study.setDescription(rs.getString("Description"));
                study.setDateCreated(rs.getDate("DateCreated"));
                study.setActParticipants(rs.getInt("ActParticipants"));
//                study.getImageURL(rs.getString("ImageURL"));
                study.setSstatus(rs.getString("Sstatus"));
                study.setReqParticipants(rs.getInt("ReqParticipants"));
                study.setQuestion(rs.getString("Question"));
            }
            return study;

        } catch (SQLException ex) {
            Logger.getLogger(StudyDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Study> getStudies() {

       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Study> sList = new ArrayList<Study>();
        String query = " SELECT * FROM Study ";
        try {
            ps = connection.prepareStatement(query);
            
            rs = ps.executeQuery();
            Study study;
            while (rs.next()) {
                study = new Study();
                study.setScode(rs.getString("Scode"));
                study.setSname(rs.getString("Sname"));
                study.setEmail(rs.getString("Email"));
                study.setDescription(rs.getString("Description"));
                study.setDateCreated(rs.getDate("DateCreated"));
                study.setActParticipants(rs.getInt("ActParticipants"));
//                study.getImageURL(rs.getString("ImageURL"));
                study.setSstatus(rs.getString("Sstatus"));
                study.setReqParticipants(rs.getInt("ReqParticipants"));
                study.setQuestion(rs.getString("Question"));
                sList.add(study);
            }
            return sList;

        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }


    

    public ArrayList<Study> getStudies(String Email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Study> sList = new ArrayList<Study>();
        String query = " SELECT * FROM Study WHERE Email = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Email);
            rs = ps.executeQuery();
//            for(int i=0; i<studyList.size();i++){
//            if(studyList.get(i).getEmail().equals(email))
//                 sList.add(studyList.get(i));
//            }
//                 return sList;
            Study study;
            while (rs.next()) {
                study = new Study();
                study.setScode(rs.getString("Scode"));
                study.setSname(rs.getString("Sname"));
                study.setEmail(rs.getString("Email"));
                study.setDescription(rs.getString("Description"));
                study.setDateCreated(rs.getDate("DateCreated"));
                study.setActParticipants(rs.getInt("ActParticipants"));
//                study.getImageURL(rs.getString("ImageURL"));
                study.setSstatus(rs.getString("Sstatus"));
                study.setReqParticipants(rs.getInt("ReqParticipants"));
                study.setQuestion(rs.getString("Question"));
                sList.add(study);
            }
            return sList;

        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public ArrayList<Study> getStudiesFor(String Sstatus) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Study> sList = new ArrayList<Study>();
        String query = " SELECT * FROM Study " + " WHERE SStatus = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Sstatus);
            rs = ps.executeQuery();
            Study study;
            while (rs.next()) {
                study = new Study();
                study.setScode(rs.getString("Scode"));
                study.setSname(rs.getString("Sname"));
                study.setEmail(rs.getString("Email"));
                study.setDescription(rs.getString("Description"));
                study.setDateCreated(rs.getDate("DateCreated"));
                study.setActParticipants(rs.getInt("ActParticipants"));
//                study.getImageURL(rs.getString("ImageURL"));
                study.setSstatus(rs.getString("Sstatus"));
                study.setReqParticipants(rs.getInt("ReqParticipants"));
                study.setQuestion(rs.getString("Question"));
                sList.add(study);
            }
            return sList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public Study updateStudy(String Scode, Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = " UPDATE Study SET "
                + " SName = ? ,"
                + " Question = ?,"
                + " ReqParticipants = ?,"
                + " Description = ? "
                + " WHERE SCode = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getSname());
            ps.setString(2, study.getQuestion());
            ps.setInt(3, study.getReqParticipants());
            ps.setString(4, study.getDescription());
            ps.setString(5, study.getScode());
            ps.executeUpdate();
            return study;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public Study addStudy(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = " INSERT INTO Study " + " VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getSname());
            ps.setString(2, study.getScode());
            ps.setString(3, study.getDescription());
            ps.setString(4, study.getEmail());
            ps.setDate(5,(java.sql.Date) study.getDateCreated());
            ps.setString(6, study.getQuestion());
            ps.setString(7, study.getImageURL());
            ps.setInt(8, study.getReqParticipants());
            ps.setInt(9, study.getActParticipants());
            ps.setString(10, study.getSstatus());
            ps.executeUpdate();
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

//    public Study updateStudy(String Scode, Study study) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//
//        String query = "UPDATE Study SET" 
//                + "Sname = ? ,"
//                + "Question = ?,"
////                + "ImageURL = ?,"
//                + "ReqParticipations = ?,"
//                + "Description = ?"
//                + "WHERE Scode=? ";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, study.getSname());
//            ps.setString(2,study.getQuestion());
////            ps.setString(3,study.getImageURL());
//            ps.setInt(3,study.getReqParticipants());
//            ps.setString(4,study.getDescription());
//            ps.setString(5,study.getScode()); 
//            ps.executeUpdate();
//            return study;
//        } catch (SQLException e) {
//            System.out.println("e");
//            return null;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//
//    }
    public void setStatus(Study study) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String insertSQL = " UPDATE Study SET SStatus = ? WHERE SCode = ? ";
        PreparedStatement pstmt = connection.prepareStatement(insertSQL);
        pstmt.setString(1, study.getSstatus());
        pstmt.setString(2, study.getScode());
        pstmt.executeUpdate();
    }

}
