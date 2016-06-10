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
import java.util.ArrayList;
import murach.assign1.Answer;
import murach.data.ConnectionPool;
import murach.data.DBUtil;

/**
 *
 * @author sunny
 */
public class AnswerDB {
    
    public Answer AddAnswer(Answer ans){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
                
        String query = "INSERT INTO Answer" + "VALUES(?,?,?,?)";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1,ans.getEmail());
            ps.setInt(2, ans.getChoice());
            ps.setString(3,ans.getScode());
            ps.setDate(4, (Date) ans.getDateSubmitted()); 
            ps.executeUpdate();
            return ans;
        }
        catch(SQLException ex){
            System.out.println("ex");
            return null;
        }
        finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
     }
    public ArrayList<Answer> getAnswersFor(String Scode){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Answer" + " WHERE SCode=?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1,Scode);
            rs = ps.executeQuery();
            return (ArrayList<Answer>) rs;
            
        }
        catch(SQLException ex){
            System.out.println("ex");
            return null;
        }
        finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            DBUtil.closeResultSet(rs);
        }
        
    }
     public ArrayList<Answer> getAnswers(String Email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Answer" + " WHERE Email=?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1,Email);
            rs = ps.executeQuery();
            return (ArrayList<Answer>) rs;
            
        }
        catch(SQLException ex){
            System.out.println("ex");
            return null;
        }
        finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
            DBUtil.closeResultSet(rs);
        }
        
    }
    
}
