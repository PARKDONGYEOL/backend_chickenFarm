package com.backend.chickenFarm.env_settings.dto;

import lombok.Data;

@Data
public class EnvSettingsDTO {
    // LED 조명 기준
    private Double ledThreshold;
    private Boolean autoLedMode;
    private Double manualLedThreshold;
    
    // 위치 정보
    private Double locationLat;
    private Double locationLng;
    
    // 수면 시간 설정
    private Integer sleepStartHour;
    private Integer sleepEndHour;
    private Boolean sleepModeEnabled;
    
    // 서보모터 (문) 기준
    private Double doorOpenTemp;
    private Double doorCloseTemp;
    
    // 팬 기준
    private Double fanHumidityThreshold;
    private Double fanCO2Threshold;
    private Double fanCOThreshold;
    private Double fanSpeed;
    
    // 위험 알림 기준
    private Double tempHighAlert;
    private Double tempLowAlert;
    private Double humidityHighAlert;
    private Double humidityLowAlert;
    private Double co2Alert;
    private Double coAlert;
    private Double nh3Alert;
    
    // 환경 상태 점수 기준
    private Double envStatusGood;
    private Double envStatusFair;
}
