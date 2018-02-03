package ua.lazareva.userregister.dao.jdbc.usermapper;

import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import ua.lazareva.userregister.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserMapperTest {

    @Test
    public void testMapToUser() throws SQLException {
        //prepare
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new User(-1, "Olga", "Lazareva", LocalDate.of(1982, 11, 4), 99.99));
        expectedList.add(new User(-2, "Nastya", "Lazareva", LocalDate.of(2009, 6, 4), 100.01));
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("user_id")).thenReturn(-1).thenReturn(-2);
        when(resultSet.getString("first_name")).thenReturn("Olga").thenReturn("Nastya");
        when(resultSet.getString("last_name")).thenReturn("Lazareva").thenReturn("Lazareva");
        when(resultSet.getDate("birth_date")).thenReturn(Date.valueOf(LocalDate.of(1982, 11, 4))).thenReturn(Date.valueOf(LocalDate.of(2009, 6, 4)));
        when(resultSet.getDouble("salary")).thenReturn(99.99).thenReturn(100.01);
        //when
        List<User> actualList = UserMapper.map(resultSet);
        //then
        assertEquals(expectedList.size(), actualList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i));
        }
    }
}
