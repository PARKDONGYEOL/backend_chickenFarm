package com.backend.chickenFarm.sensor_th.service;

import com.backend.chickenFarm.sensor_th.dto.ThSensorDTO;
import com.backend.chickenFarm.sensor_th.mapper.ThSensorMaper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThSensorService {
    private final ThSensorMaper thSensorMaper;

    public List<ThSensorDTO> getThSensor(String startData, String endData){
        return thSensorMaper.getThSensor(startData, endData);
    }

}
