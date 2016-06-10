/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import murach.assign1.TempUser;
import murach.assign1.User;
import murach.data.ConnectionPool;
import murach.data.DBUtil;

/**
 *
 * @author sunny
 */
public class TempUserDB {
    private static TempUserDB instance = new TempUserDB();
    
     public static TempUserDB getInstance(){
        return instance;
    } 
 
    public User getUser(String Token){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM tempuser WHERE Token = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1,Token);
            rs = ps.executeQuery();
            User t = null;
              while(rs.next()){
                t = new User();
                t.setUname(rs.getString("Uname"));
                t.setEmail(rs.getString("Email"));
                t.setPassword(rs.getString("Password"));
                t.setDate(rs.getDate("IssueDate"));          
                t.setToken(rs.getString("Token"));
            }
            return t;
        }catch(SQLException e){
        System.out.println("e");
        return null;      
        }
        finally{
            pool.freeConnection(connection);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
        }
    }
        public int addTempUser(String email, String uname, String pass, String token){
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            PreparedStatement ps = null;
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            String query = "INSERT INTO tempuser (Uname, Email, Password, IssueDate, Token) " + " VALUES(?,?,?,?,?)";
            try{
                ps = connection.prepareStatement(query);
                ps.setString(1,uname);
                ps.setString(2,email);
                ps.setString(3,pass);
                ps.setTimestamp(4, date);
                ps.setString(5,token);
                return ps.executeUpdate();
                
            }
            catch(SQLException e){
                System.out.println("e");
                return 0;
            }
            finally{
                pool.freeConnection(connection);
                DBUtil.closePreparedStatement(ps);
            }
            
        }
        
        public int removeUser(User u){
       
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;        
        ResultSet rs = null;       
        
        String query;
        query = "delete from tempuser "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, u.getEmail());
            return ps.executeUpdate();                                                                
        }catch (SQLException e) {
            System.out.println(e);   
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);            
            pool.freeConnection(connection);
        }    
   }
}
