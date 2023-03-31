package kr.ac.jejunu.userdao.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    // new -> 의존성 있음
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        User user;
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("select id, name, password from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            //쿼리 실행하고
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            //결과를 사용자 정보에 매핑하고
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        //결과 리턴
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null; // 키 값 가져옴
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("insert  into userinfo (name, password) values ( ?, ? )"
                            , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());

            //쿼리 실행하고
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // refactoring, 중복 코드 extract
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        //connection 맺고
        return dataSource.getConnection();
    }
}
