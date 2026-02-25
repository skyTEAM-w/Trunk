package com.whut.truck.Dao;

import com.whut.truck.entity.SystemAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DatabaseIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SystemAdminDao systemAdminDao;

    @Test
    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
        }
    }

    @Test
    public void testSystemAdminDao() throws IOException {
        // Create a test user
        String testUsername = "test_user_" + System.currentTimeMillis();
        SystemAdmin newAdmin = new SystemAdmin(testUsername, "password", "test@example.com");

        // Save
        systemAdminDao.save(newAdmin);

        // Find
        SystemAdmin foundAdmin = systemAdminDao.findByUsername(testUsername);
        assertNotNull(foundAdmin);
        assertEquals(testUsername, foundAdmin.getUsername());
        assertEquals("password", foundAdmin.getPassword());
        assertEquals("test@example.com", foundAdmin.getEmail());
    }
}
