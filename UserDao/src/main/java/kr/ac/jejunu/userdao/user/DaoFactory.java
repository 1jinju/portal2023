package kr.ac.jejunu.userdao.user;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao getUserDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new JejuConnectionMaker();
    }
}
