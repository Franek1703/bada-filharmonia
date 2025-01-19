package com.bada_project.filharmonia;

import com.bada_project.filharmonia.dao.UserDAO;
import com.bada_project.filharmonia.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private UserDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("SYSTEM");
        datasource.setPassword("SYSTEM");
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        dao = new UserDAO(new JdbcTemplate(datasource));
    }

    @Test
    void testList() {
        List<UserModel> userList = dao.list();
        assertFalse(userList.isEmpty(), "User list should not be empty");
    }

    @Test
    void testSave() {
        UserModel user = new UserModel();
        user.setId(101);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhoneNumber("123456789");

        dao.save(user);

        UserModel savedUser = dao.get(101);
        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("123456789", savedUser.getPhoneNumber());
    }

    @Test
    void testGet() {
        int id = 101;
        UserModel user = dao.get(id);
        assertNotNull(user, "User should not be null");
        assertEquals(id, user.getId());
    }

    @Test
    void testGetByPhoneNumber() {
        String phoneNumber = "987654321";
        UserModel user = dao.getByPhoneNumber(phoneNumber);
        assertNotNull(user, "User should not be null");
        assertEquals(phoneNumber, user.getPhoneNumber());
    }

    @Test
    void testUpdate() {
        UserModel user = dao.get(101);
        user.setPhoneNumber("987654321");

        dao.update(user);

        UserModel updatedUser = dao.get(101);
        assertEquals("987654321", updatedUser.getPhoneNumber(), "Phone number should be updated");
    }

    @Test
    void testDelete() {
        int id = 101;

        dao.delete(id);

        Exception exception = assertThrows(Exception.class, () -> dao.get(id));
        assertNotNull(exception, "Exception should be thrown for non-existent user");
    }
}
