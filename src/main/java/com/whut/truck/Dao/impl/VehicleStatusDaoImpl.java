package org.whut.trunk.Dao.impl;

import org.whut.trunk.Dao.VehicleStatusDao;
import org.whut.trunk.Util.JDBC_UTL;
import org.whut.trunk.entity.SystemAdmin;
import org.whut.trunk.entity.VehicleStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleStatusDaoImpl implements VehicleStatusDao {
    @Override
    public List<VehicleStatus> list() {
        Connection connection = JDBC_UTL.getconnection();
        String sql = "select * from game.车辆状态";     //用username进行查询
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List <VehicleStatus> list = new ArrayList<>();

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int vehicle_id = resultSet.getInt(1);
                String maintenance_status = resultSet.getString(2);
                String estimated_maintenance_time = resultSet.getString(3);
                String previous_failure_status = resultSet.getString(4);
                list.add(new VehicleStatus(vehicle_id, maintenance_status, estimated_maintenance_time, previous_failure_status));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBC_UTL.release(connection, statement, resultSet);
        }
        return list;
    }
}
