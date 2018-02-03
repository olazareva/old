package ua.lazareva.userregister.dao.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import ua.lazareva.userregister.dao.IGenericDao;
import ua.lazareva.userregister.dao.jdbc.usermapper.UserMapper;
import ua.lazareva.userregister.entity.User;

import java.sql.*;
import java.util.List;

public class JdbcUserDao implements IGenericDao<User, Integer> {
    private static final String PREPARE_SELECT_ALL = "select user_id, first_name, last_name, birth_date, salary from users;";
    private static final String PREPARE_SELECT_BY_ID = "select user_id, first_name, last_name, birth_date, salary from users where user_id = ?;";
    private static final String PREPARE_INSERT = "insert into users(first_name, last_name, birth_date, salary) values(?,?,?,?);";
    private static final String PREPARE_UPDATE = "update users set first_name = ?, last_name = ?, birth_date = ?, salary = ? where user_id = ?;";
    private static final String PREPARE_DELETE = "delete from users where user_id = ?;";
    private BasicDataSource dataSource;

    public JdbcUserDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PREPARE_SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            return UserMapper.map(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getById(Integer userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = getStatementForGetById(connection, userId);
             ResultSet resultSet = statement.executeQuery()) {
            return UserMapper.map(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PREPARE_INSERT)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            if (user.getDateOfBirth() == null) {
                statement.setNull(3, Types.DATE);
            } else {
                statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            }
            if (user.getSalary() == null) {
                statement.setNull(4, Types.FLOAT);
            } else {
                statement.setDouble(4, user.getSalary());
            }
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PREPARE_UPDATE)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            if (user.getDateOfBirth() == null) {
                statement.setNull(3, Types.DATE);
            } else {
                statement.setDate(3, Date.valueOf(user.getDateOfBirth()));
            }
            if (user.getSalary() == null) {
                statement.setNull(4, Types.FLOAT);
            } else {
                statement.setDouble(4, user.getSalary());
            }
            statement.setInt(5, user.getUserId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(PREPARE_DELETE)) {
            statement.setInt(1, userId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement getStatementForGetById(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(PREPARE_SELECT_BY_ID);
        statement.setInt(1, userId);
        return statement;
    }
}
