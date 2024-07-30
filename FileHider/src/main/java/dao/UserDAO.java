package dao;

import db.MyConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isExists(String email)throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String e = rs.getString(1);
            if(e.equals(email)){
                return true;
            }

        }
        return false;
    }

    public static int saveUser(User user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into users values(default, ? , ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        return ps.executeUpdate();

    }
}

//package dao;
//
//import db.MyConnection;
//import models.User;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserDAO {
//    public static boolean isExists(String email) throws SQLException {
//        Connection connection = MyConnection.getConnection();
//        if (connection == null) {
//            throw new SQLException("Failed to establish a connection to the database");
//        }
//        PreparedStatement ps = connection.prepareStatement("select email from users where email =?");
//        ps.setString(1, email);
//        ResultSet rs = ps.executeQuery();
//        return rs.next();
//    }
//
//    public static int saveUser(User user) throws SQLException {
//        Connection connection = MyConnection.getConnection();
//        if (connection == null) {
//            throw new SQLException("Failed to establish a connection to the database");
//        }
//        PreparedStatement ps = connection.prepareStatement("insert into users values(default,?,?)");
//        ps.setString(1, user.getName());
//        ps.setString(2, user.getEmail());
//        return ps.executeUpdate();
//    }
//}
