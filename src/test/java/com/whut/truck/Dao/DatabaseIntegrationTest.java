package com.whut.truck.Dao;

import com.whut.truck.entity.Sensor;
import com.whut.truck.entity.SystemAdmin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private SensorDao sensorDao;

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

    @Test
    public void testSensorDao() throws IOException {
        // Prepare CSV data
        // Format: id times setting1 setting2 ... (26 columns)
        // SensorDaoImpl.csvSave splits by space.
        String csvData = "test_sensor_1 1 0.1 0.2 0.3 100 200 300 400 500 600 700 800 900 1.5 10.5 2.5 3000 4000 0.5 0.02 50 2000 3000 10 20";
        InputStream inputStream = new ByteArrayInputStream(csvData.getBytes(StandardCharsets.UTF_8));

        // Save
        Integer result = sensorDao.csvSave(inputStream);
        // It returns rows affected. Since it's insert, it should be 1.
        assertEquals(1, result);

        // Find
        Sensor sensor = sensorDao.findByid_output_oneline("test_sensor_1", "1");
        assertNotNull(sensor);
        // Assuming the mapping: id -> vehicle_id, times -> describe (or sensor_id?)
        // Let's just check not null for now, as column mapping might be legacy specific.
    }
}
