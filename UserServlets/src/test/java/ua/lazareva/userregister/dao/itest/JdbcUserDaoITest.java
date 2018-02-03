package ua.lazareva.userregister.dao.itest;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;
import ua.lazareva.userregister.dao.IGenericDao;
import ua.lazareva.userregister.dao.jdbc.JdbcUserDao;
import ua.lazareva.userregister.entity.User;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JdbcUserDaoITest {

    private BasicDataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/web");
        dataSource.setUsername("web_app_user");
        dataSource.setPassword("webapp");
        dataSource.setInitialSize(5);
        return dataSource;
    }

    @Test
    public void testGetAll() {
        //prepare
        IGenericDao<User, Integer> userDao = new JdbcUserDao(getDataSource());
        //when
        List<User> list = userDao.getAll();
        //then
        assertNotNull(list.size());
    }
}
