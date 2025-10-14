package com.backend.chickenFarm.env_settings.service;

import com.backend.chickenFarm.env_settings.dto.EnvSettingsDTO;
import com.backend.chickenFarm.env_settings.mapper.EnvSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EnvSettingsService {
    
    @Autowired
    private EnvSettingsMapper envSettingsMapper;
    
    // 환경 설정 조회
    public Map<String, Object> getEnvSettings() {
        Map<String, Object> settings = envSettingsMapper.getEnvSettings();
        
        // 설정이 없으면 기본값 반환
        if (settings == null || settings.isEmpty()) {
            return getDefaultSettings();
        }
        
        return settings;
    }
    
    // 환경 설정 업데이트
    public boolean updateEnvSettings(EnvSettingsDTO settingsDTO) {
        try {
            Map<String, Object> settingsMap = convertToMap(settingsDTO);
            
            // 기존 설정이 있는지 확인
            Map<String, Object> existingSettings = envSettingsMapper.getEnvSettings();
            
            if (existingSettings != null && !existingSettings.isEmpty()) {
                // 기존 설정 업데이트
                int result = envSettingsMapper.updateEnvSettings(settingsMap);
                return result > 0;
            } else {
                // 새 설정 삽입
                int result = envSettingsMapper.insertEnvSettings(settingsMap);
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 설정을 라즈베리파이에 적용
    public boolean applySettings() {
        try {
            // 라즈베리파이에 HTTP 요청을 보내서 설정을 적용
            String raspberryUrl = "http://192.168.30.240:5000/api/settings/apply";
            
            // RestTemplate을 사용하여 POST 요청
            org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            
            org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>("{}", headers);
            
            org.springframework.http.ResponseEntity<String> response = restTemplate.postForEntity(raspberryUrl, request, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("라즈베리파이에 설정 적용 성공: " + response.getBody());
                return true;
            } else {
                System.out.println("라즈베리파이 설정 적용 실패: " + response.getStatusCode());
                return false;
            }
        } catch (Exception e) {
            System.out.println("라즈베리파이 설정 적용 중 오류: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // 기본 설정값 반환
    private Map<String, Object> getDefaultSettings() {
        Map<String, Object> defaultSettings = new HashMap<>();
        
        // LED 조명 기준
        defaultSettings.put("ledThreshold", 300.0);
        defaultSettings.put("autoLedMode", true);
        defaultSettings.put("manualLedThreshold", 300.0);
        
        // 위치 정보
        defaultSettings.put("locationLat", 37.5665);
        defaultSettings.put("locationLng", 126.9780);
        
        // 수면 시간 설정
        defaultSettings.put("sleepStartHour", 22);
        defaultSettings.put("sleepEndHour", 6);
        defaultSettings.put("sleepModeEnabled", true);
        
        // 서보모터 (문) 기준
        defaultSettings.put("doorOpenTemp", 25.0);
        defaultSettings.put("doorCloseTemp", 15.0);
        
        // 팬 기준
        defaultSettings.put("fanHumidityThreshold", 75.0);
        defaultSettings.put("fanCO2Threshold", 1000.0);
        defaultSettings.put("fanCOThreshold", 30.0);
        defaultSettings.put("fanSpeed", 60.0);
        
        // 위험 알림 기준
        defaultSettings.put("tempHighAlert", 35.0);
        defaultSettings.put("tempLowAlert", 10.0);
        defaultSettings.put("humidityHighAlert", 85.0);
        defaultSettings.put("humidityLowAlert", 30.0);
        defaultSettings.put("co2Alert", 2000.0);
        defaultSettings.put("coAlert", 50.0);
        defaultSettings.put("nh3Alert", 25.0);
        
        // 환경 상태 점수 기준
        defaultSettings.put("envStatusGood", 80.0);
        defaultSettings.put("envStatusFair", 60.0);
        
        return defaultSettings;
    }
    
    // DTO를 Map으로 변환
    private Map<String, Object> convertToMap(EnvSettingsDTO dto) {
        Map<String, Object> map = new HashMap<>();
        
        if (dto.getLedThreshold() != null) map.put("ledThreshold", dto.getLedThreshold());
        if (dto.getAutoLedMode() != null) map.put("autoLedMode", dto.getAutoLedMode());
        if (dto.getManualLedThreshold() != null) map.put("manualLedThreshold", dto.getManualLedThreshold());
        if (dto.getLocationLat() != null) map.put("locationLat", dto.getLocationLat());
        if (dto.getLocationLng() != null) map.put("locationLng", dto.getLocationLng());
        if (dto.getSleepStartHour() != null) map.put("sleepStartHour", dto.getSleepStartHour());
        if (dto.getSleepEndHour() != null) map.put("sleepEndHour", dto.getSleepEndHour());
        if (dto.getSleepModeEnabled() != null) map.put("sleepModeEnabled", dto.getSleepModeEnabled());
        if (dto.getDoorOpenTemp() != null) map.put("doorOpenTemp", dto.getDoorOpenTemp());
        if (dto.getDoorCloseTemp() != null) map.put("doorCloseTemp", dto.getDoorCloseTemp());
        if (dto.getFanHumidityThreshold() != null) map.put("fanHumidityThreshold", dto.getFanHumidityThreshold());
        if (dto.getFanCO2Threshold() != null) map.put("fanCO2Threshold", dto.getFanCO2Threshold());
        if (dto.getFanCOThreshold() != null) map.put("fanCOThreshold", dto.getFanCOThreshold());
        if (dto.getFanSpeed() != null) map.put("fanSpeed", dto.getFanSpeed());
        if (dto.getTempHighAlert() != null) map.put("tempHighAlert", dto.getTempHighAlert());
        if (dto.getTempLowAlert() != null) map.put("tempLowAlert", dto.getTempLowAlert());
        if (dto.getHumidityHighAlert() != null) map.put("humidityHighAlert", dto.getHumidityHighAlert());
        if (dto.getHumidityLowAlert() != null) map.put("humidityLowAlert", dto.getHumidityLowAlert());
        if (dto.getCo2Alert() != null) map.put("co2Alert", dto.getCo2Alert());
        if (dto.getCoAlert() != null) map.put("coAlert", dto.getCoAlert());
        if (dto.getNh3Alert() != null) map.put("nh3Alert", dto.getNh3Alert());
        if (dto.getEnvStatusGood() != null) map.put("envStatusGood", dto.getEnvStatusGood());
        if (dto.getEnvStatusFair() != null) map.put("envStatusFair", dto.getEnvStatusFair());
        
        return map;
    }
}
