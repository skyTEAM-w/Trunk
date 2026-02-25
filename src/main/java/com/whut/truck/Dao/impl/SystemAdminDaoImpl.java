package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.SystemAdminDao;
import com.whut.truck.entity.SystemAdmin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SystemAdminDaoImpl implements SystemAdminDao {

    private final JdbcTemplate jdbcTemplate;

    public SystemAdminDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SystemAdmin findByUsername(String username) throws IOException {
        String sql = "SELECT * FROM `game`.`用户` where `用户名` = ?";
        List<SystemAdmin> results = jdbcTemplate.query(sql, new RowMapper<SystemAdmin>() {
            @Override
            public SystemAdmin mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                return new SystemAdmin(id, username, password, email);
            }
        }, username);

        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Integer save(SystemAdmin systemAdmin) throws IOException {
        String sql = "insert into `game`.`用户`(用户名, 密码, 邮箱)values (?,?,?)";
        return jdbcTemplate.update(sql, systemAdmin.getUsername(), systemAdmin.getPassword(), systemAdmin.getEmail());
    }
}
