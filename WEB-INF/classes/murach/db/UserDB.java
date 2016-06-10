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
import java.util.*;
import murach.assign1.User;
import murach.data.ConnectionPool;
import murach.data.DBUtil;

/**
 *
 * @author sunny
 */
public class UserDB {

    ArrayList<User> userList;
//  public UserDB() {
//        this.userList = new ArrayList<User>();
//         User u1= new User("a1", "a1@g.com","abc", 10, 5,5);
//         User u2= new User("b1", "b1@g.com","abc", 2, 2,2);
//         User u3= new User("c1", "c1@g.com","abc", 1, 1,1);
//         User u4= new User("d1", "d1@g.com","abc", 1, 2,1);
//         User u5= new User("e1", "e1@g.com","abc", 1, 2,1);
//         User u6= new User("f1", "f1@g.com","abc", 1, 2,1);
//         userList.add(u1);
//         userList.add(u2);
//         userList.add(u3);
//         userList.add(u4);
//         userList.add(u5);
//         userList.add(u6);
//    }
    public User getUser(String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = " SELECT * FROM User " + " WHERE Email = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();        
                    
            User user = null;
            if (rs.next()) {
                user = new User();
                System.out.println(rs.getString("Uname"));
                user.setUname(rs.getString("Uname"));
//                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));              
                user.setStudies(rs.getInt("Studies"));
                user.setParticipation(rs.getInt("Participation"));
                user.setCoins(rs.getInt("Coins"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public boolean validateUser(String email, String Password){
        User u = this.getUser(email);
        if(u!=null){
       return u.getPassword().equals(Password);
        }else {
            return false;
        }
    }

//    public boolean validateUser(String Email, String Password) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM User" + " WHERE Email = ?" + "AND Password = ?";
//        try{
//               ps = connection.prepareStatement(query);
//               ps.setString(1, Email);
//               ps.setString(2, Password);
//               rs = ps.executeQuery();
//                 User user = null;  
//               if (rs.next()) {
//                
//                user.setEmail(rs.getString("Email"));
//                user.setPassword(rs.getString("Password"));
//                
//               }
//         }  catch(SQLException e) {
//            System.out.println("e");
//            return false;
//        }
//        return false;
//    }

    public User addUser(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = " INSERT INTO User " + " VALUES(?,?,?,?,?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public User updateParticipations(String Email, Integer Participation) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = " UPDATE User SET Participation = ? AND Email = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, Participation);
            ps.setString(2, Email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return null;
    }

    public User updateStudies(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET" + "Studies= ?," + "WHERE UName=?," + "AND Email=?,";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getStudies());
            ps.setString(2, user.getUname());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("e");
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }

//  public ArrayList<User> getUsers(){
//        
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        String query = "SELECT * FROM User" ;
//        
//        try{
//        
//         return userList;
//        }
////        catch(SQLException e){
////            System.out.println(e);
////            return null;
////        }
//     finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//     }
}
