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

    public List<ThSensorDTO> getTempData(String date, String startTime, String endTime){
        return thSensorMaper.getTempData(date, startTime, endTime);
    }

    public List<ThSensorDTO> getHumData(String date, String startTime, String endTime){
        return thSensorMaper.getHumData(date, startTime, endTime);
    }

    public List<ThSensorDTO> getLuxData(String date, String startTime, String endTime){
        return thSensorMaper.getLuxData(date, startTime, endTime);
    }

}
