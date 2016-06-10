/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murach.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author sunny
 */
public class DBUtil {

    private static DBUtil myObj;
    private  Connection connection;
    
    private DBUtil() throws Exception{
        try{
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://127.11.1.130:3306/rep", "admin7ZQxNvU","Dpmyg8S6pxzp");
             }catch(SQLException ex){
                 System.out.println(ex.getMessage());
             }
    }

    public static DBUtil getInstance() {
        try{if (myObj == null) {
            myObj = new DBUtil();
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return myObj;
    }

//    public DBUtil() {
//
//    }

    public Connection getConnection() {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.driver");
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
   

    public static void closeStatement(Statement s) {
        try {
            if (s != null) {
                s.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
        public static void closePreparedStatement(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

        public static void closeResultSet(ResultSet rs) {
        try{
            if (rs != null) {
            rs.close();
           }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }


}
