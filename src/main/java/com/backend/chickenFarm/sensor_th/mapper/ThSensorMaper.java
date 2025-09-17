package com.backend.chickenFarm.sensor_th.mapper;


import com.backend.chickenFarm.sensor_th.dto.ThSensorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ThSensorMaper {
    public List<ThSensorDTO> getThSensor(String startDate, String endDate );
}
