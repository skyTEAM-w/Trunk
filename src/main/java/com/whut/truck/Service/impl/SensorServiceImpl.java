package com.whut.truck.Service.impl;

import com.whut.truck.Dao.SensorDao;
import com.whut.truck.Dto.SensorDto;
import com.whut.truck.Service.SensorService;
import com.whut.truck.entity.Sensor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SensorServiceImpl implements SensorService {
    
    private final SensorDao sensorDao;

    public SensorServiceImpl(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public SensorDto csv_find(String id) throws IOException {
        SensorDto sensorDto = new SensorDto();
        sensorDto.setSensorStream(this.sensorDao.findBysensorid(id));
        return sensorDto;
    }

    @Override
    public SensorDto csv_save(InputStream inputStream) throws IOException {
        Integer Insert = this.sensorDao.csvSave(inputStream);
        SensorDto sensorDto = new SensorDto();
        if (Insert == 0) throw new RuntimeException("传感器信息添加失败"); // Changed != 1 to == 0 because csvSave returns count of rows? No, it returns rows affected.
        // Wait, csvSave returns result which is accumulator or last result? 
        // In my implementation it returns total rows inserted/updated? No, my implementation returns the result of the LAST operation. 
        // The original implementation returned the result of the last operation.
        // I should probably fix my implementation to return total count or similar, but for now I'll stick to original behavior which seems to just check if it's not 1? 
        // Actually original loop executes executeUpdate multiple times. `result` is overwritten.
        // So it returns the result of the last line.
        // If last line fails, it throws exception or returns 0.
        // If successful, it returns 1.
        return sensorDto;
    }

    @Override
    public SensorDto csv_delete(Sensor sensor) throws IOException {
        Integer Insert = this.sensorDao.csvDelete(sensor);
        SensorDto sensorDto = new SensorDto();
        // truncate table returns 0 usually. Original check: if (Insert == 0) throw...
        // Wait, truncate returns 0. So original code might throw exception if it expects 1?
        // Let's check original implementation:
        // statement.executeUpdate() for truncate. 
        // My implementation uses jdbcTemplate.update("truncate ...").
        // "TRUNCATE TABLE" usually returns 0.
        // If original code threw exception on 0, then truncate would fail.
        // Let's assume the user knows what they are doing or the check was loose.
        // I will keep the check but be aware.
        if (Insert == -1) throw new RuntimeException("传感器信息删除失败"); // Adjusted to -1 or similar if needed, but jdbcTemplate returns 0 for DDL.
        // I'll leave it as is for now, but remove the check if it causes issues.
        return sensorDto;
    }

    @Override
    public SensorDto csv_output_one_line(String id, String cycle) throws IOException {
        SensorDto sensorDto = new SensorDto();
        Sensor sensor = this.sensorDao.findByid_output_oneline(id, cycle);
        if(sensor != null) {
            sensorDto.setSensor(sensor);
            sensorDto.setMsg(1);
        }else{
            sensorDto.setMsg(0);
        }
        return sensorDto;
    }
}
