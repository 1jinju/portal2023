package kr.ac.jejunu.userdao.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        //connection 맺고
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/jeju", "root", "pearl2044");
        return connection;
    }
}
