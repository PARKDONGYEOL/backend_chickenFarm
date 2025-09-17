package com.backend.chickenFarm.sensor_th.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ThSensorDTO {
        private int thSensorId;
        private float tempData;
        private float humData;
        private float luxData;
        private LocalDateTime recTime;

}

