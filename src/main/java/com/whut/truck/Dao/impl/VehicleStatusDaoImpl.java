package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.VehicleStatusDao;
import com.whut.truck.entity.VehicleStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VehicleStatusDaoImpl implements VehicleStatusDao {

    private final JdbcTemplate jdbcTemplate;

    public VehicleStatusDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public VehicleStatus findByVehicle_id(String vehicle_id) throws IOException {
        String sql = "SELECT * FROM `game`.`车辆状态` where `车辆id` = ?";
        List<VehicleStatus> results = jdbcTemplate.query(sql, new RowMapper<VehicleStatus>() {
            @Override
            public VehicleStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
                String id = rs.getString(1);
                String maintenance_status = rs.getString(2);
                Integer estimated_maintenance_time = rs.getInt(3);
                String previous_failure_status = rs.getString(4);
                String Last_Maintenance_date = rs.getString(5);
                String Maintenance_Frequency = rs.getString(6);
                Blob Vehicle_file = rs.getBlob(7);
                return new VehicleStatus(id, maintenance_status, estimated_maintenance_time, previous_failure_status, Last_Maintenance_date, Maintenance_Frequency, Vehicle_file);
            }
        }, vehicle_id);
        
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Integer save(VehicleStatus vehicleStatus) throws IOException {
        String sql = "UPDATE game.车辆状态 " +
                "SET 维护状态=?, 剩余维护时间（分钟）=?, 故障状态=?, 上次维护时间=?, 维护次数=?, 数据文件=? " +
                "WHERE 车辆id=?";
        return jdbcTemplate.update(sql,
                vehicleStatus.getMaintenance_status(),
                vehicleStatus.getEstimated_maintenance_time(),
                vehicleStatus.getPrevious_failure_status(),
                vehicleStatus.getLast_Maintenance_date(),
                vehicleStatus.getMaintenance_Frequency(),
                vehicleStatus.getVehicle_file(),
                vehicleStatus.getVehicle_id());
    }

    @Override
    public Integer Insert_file(String id, InputStream inputStream) throws IOException {
        String sql = "UPDATE `game`.`车辆状态` SET `数据文件` = ? WHERE `车辆id` = ?";
        return jdbcTemplate.update(sql, inputStream, id);
    }
}
