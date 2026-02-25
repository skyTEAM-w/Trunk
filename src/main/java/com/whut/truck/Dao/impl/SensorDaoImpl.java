package com.whut.truck.Dao.impl;

import com.whut.truck.Dao.SensorDao;
import com.whut.truck.entity.Sensor;
import com.whut.truck.utils.CsvUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SensorDaoImpl implements SensorDao {

    private final JdbcTemplate jdbcTemplate;

    public SensorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer csvSave(InputStream inputStream) throws IOException {
        String sql_check = "SELECT * FROM `game`.`传感器数据` where `车辆id` = ? and `运行轮数` = ?";
        // Restore '列数' in INSERT, but we will handle the value manually
        String sql_insert = "INSERT INTO `game`.`传感器数据`(`列数`,`车辆id`, `运行轮数`, `setting1`, `setting2`, `setting3`, `风扇进口总温度`, `低压压气机出口总温度`, `高压压气机出口总温度`, `低压涡轮出口总温度`, `风扇进口压强`, `旁路管道总压强`, `高压压气机出口总压强`, `风扇物理转速`, `核心机物理转速`, `发动机压力比`, `高压压气机出口静压(Ps30)`, `燃油流量与高压压气机出口静压的比率`, `风扇换算转速`, `核心机换算转速`, `涵道比`, `燃烧器油气比`, `抽汽焓`, `需求风扇转速`, `需求风扇换算转速`, `高压涡轮冷气流量`, `低压涡轮冷气流量`) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql_update = "UPDATE `game`.`传感器数据` set `车辆id` = ?, `运行轮数` =?, `setting1`=?, `setting2`=?, `setting3`=?, `风扇进口总温度`=?, `低压压气机出口总温度`=?, `高压压气机出口总温度`=?, `低压涡轮出口总温度`=?, `风扇进口压强`=?, `旁路管道总压强`=?, `高压压气机出口总压强`=?, `风扇物理转速`=?, `核心机物理转速`=?, `发动机压力比`=?, `高压压气机出口静压(Ps30)`=?, `燃油流量与高压压气机出口静压的比率`=?, `风扇换算转速`=?, `核心机换算转速`=?, `涵道比`=?, `燃烧器油气比`=?, `抽汽焓`=?, `需求风扇转速`=?, `需求风扇换算转速`=?, `高压涡轮冷气流量`=?, `低压涡轮冷气流量`=? where `车辆id` = ? and `运行轮数` =?";

        String line;
        String csvSplitBy = " ";
        int counter = 1;
        Integer result = 0;
        
        // Get current max ID to avoid duplicate keys since '列数' is not auto-increment
        Integer maxId = 0;
        try {
            Integer dbMax = jdbcTemplate.queryForObject("SELECT MAX(CAST(`列数` AS UNSIGNED)) FROM `game`.`传感器数据`", Integer.class);
            if (dbMax != null) {
                maxId = dbMax;
            }
        } catch (Exception e) {
            // Table might be empty or column type issue. Start from 0.
            maxId = 0;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                if (counter != 0) {
                    String[] data = line.split(csvSplitBy);
                    // Skip empty lines or lines with insufficient data
                    if (data.length < 26) continue;
                    
                    String id = data[0];
                    String times = data[1];

                    // Check if exists
                    List<String> checkResults = jdbcTemplate.query(sql_check, new RowMapper<String>() {
                        @Override
                        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getString(1);
                        }
                    }, id, times);

                    if (checkResults.isEmpty()) {
                        // Insert
                        Object[] params = new Object[27];
                        maxId++; // Increment ID
                        params[0] = maxId; 
                        
                        // Map data[0] (ID) to params[1] (车辆id)
                        // Map data[1] (Cycle) to params[2] (运行轮数)
                        // ...
                        for (int i = 0; i < 26; i++) {
                            params[i+1] = data[i];
                        }
                        
                        result = jdbcTemplate.update(sql_insert, params);
                    } else {
                        // Update
                        Object[] params = new Object[28]; // 26 params + 2 where clause
                        for (int i = 0; i < 26; i++) {
                            params[i] = data[i];
                        }
                        params[26] = id;
                        params[27] = times;
                        
                        result = jdbcTemplate.update(sql_update, params);
                    }
                }
                // System.out.println("Inserted/Updated row " + counter);
                counter++;
            }
        }
        return result;
    }

    @Override
    public InputStream findBysensorid(String id) throws IOException {
        String sql = "SELECT * FROM `game`.`传感器数据` where `车辆id` =  ? ORDER BY `运行轮数` DESC LIMIT 30";
        return jdbcTemplate.query(sql, new ResultSetExtractor<InputStream>() {
            @Override
            public InputStream extractData(ResultSet rs) throws SQLException, DataAccessException {
                return CsvUtil.resultSetToCSV(rs);
            }
        }, id);
    }

    @Override
    public Integer csvDelete(Sensor sensor) throws IOException {
        String sql = "truncate table `game`.`传感器数据`";
        return jdbcTemplate.update(sql);
    }

    @Override
    public Sensor findByid_output_oneline(String id, String cycle) throws IOException {
        String sql = "SELECT * FROM `game`.`传感器数据` where `车辆id` = ? and `运行轮数` = ?";
        List<Sensor> results = jdbcTemplate.query(sql, new RowMapper<Sensor>() {
            @Override
            public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
                String[] values = new String[27];
                for (int i = 1; i <= 27; i++) {
                    values[i - 1] = rs.getString(i);
                }
                return new Sensor(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15], values[16], values[17], values[18], values[19], values[20], values[21], values[22], values[23], values[24], values[25], values[26]);
            }
        }, id, cycle);
        
        return results.isEmpty() ? null : results.get(0);
    }
}
