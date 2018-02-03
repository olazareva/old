package ua.lazareva.userregister.dao.jdbc.usermapper;

import ua.lazareva.userregister.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<User> map(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            Date dateOfBirth = resultSet.getDate("birth_date");
            if (dateOfBirth != null) {
                user.setDateOfBirth(dateOfBirth.toLocalDate());
            }
            user.setSalary(resultSet.getDouble("salary"));
            list.add(user);
        }
        return list;
    }
}

