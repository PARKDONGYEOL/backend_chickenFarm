package com.backend.chickenFarm.env_settings.controller;

import com.backend.chickenFarm.env_settings.dto.EnvSettingsDTO;
import com.backend.chickenFarm.env_settings.service.EnvSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/env-settings")
public class EnvSettingsController {
    
    @Autowired
    private EnvSettingsService envSettingsService;
    
    @GetMapping
    public Map<String, Object> getEnvSettings() {
        try {
            Map<String, Object> settings = envSettingsService.getEnvSettings();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", settings);
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "설정 조회 실패: " + e.getMessage());
            return response;
        }
    }
    
    @PostMapping
    public Map<String, Object> updateEnvSettings(@RequestBody EnvSettingsDTO settingsDTO) {
        try {
            boolean success = envSettingsService.updateEnvSettings(settingsDTO);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", success);
            response.put("message", success ? "설정이 저장되었습니다." : "설정 저장에 실패했습니다.");
            
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "설정 저장 실패: " + e.getMessage());
            return response;
        }
    }
    
    @PostMapping("/apply")
    public Map<String, Object> applySettings() {
        try {
            boolean success = envSettingsService.applySettings();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", success);
            response.put("message", success ? "설정이 적용되었습니다." : "설정 적용에 실패했습니다.");
            
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "설정 적용 실패: " + e.getMessage());
            return response;
        }
    }
}