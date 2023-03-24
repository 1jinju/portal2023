package kr.ac.jejunu.userdao.user;

import kr.ac.jejunu.userdao.user.ConnectionMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection 맺고
        return DriverManager.getConnection
                ("jdbc:mysql://192.168.151.175/halla", "jeju", "jejupw");
//        Connection connection = DriverManager.getConnection
//                ("jdbc:mysql://localhost/jeju", "root", "pearl2044");
//        return connection;
    }
}
