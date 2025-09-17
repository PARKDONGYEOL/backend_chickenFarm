package com.backend.chickenFarm.sensor_th.controller;

import com.backend.chickenFarm.sensor_th.dto.ThSensorDTO;
import com.backend.chickenFarm.sensor_th.service.ThSensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/thsensor")
public class ThSensorController {
    private final ThSensorService thSensorService;

    @GetMapping()
    public List<ThSensorDTO> getThSensor(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endData){
        return thSensorService.getThSensor(startDate, endData);
    }
}
