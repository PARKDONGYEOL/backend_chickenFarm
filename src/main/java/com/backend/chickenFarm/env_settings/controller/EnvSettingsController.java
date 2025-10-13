package com.backend.chickenFarm.env_settings.controller;

import com.backend.chickenFarm.env_settings.dto.EnvSettingsDTO;
import com.backend.chickenFarm.env_settings.service.EnvSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/env-settings")
@CrossOrigin(origins = "http://localhost:5173")
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
            System.out.println("설정 조회 오류: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "설정 조회 실패: " + e.getMessage());
            return response;
        }
    }
    
    @PostMapping
    public Map<String, Object> updateEnvSettings(@RequestBody EnvSettingsDTO settingsDTO) {
        try {
            System.out.println("설정 업데이트 요청: " + settingsDTO);
            boolean success = envSettingsService.updateEnvSettings(settingsDTO);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", success);
            
            if (success) {
                response.put("message", "설정이 저장되었습니다.");
                System.out.println("설정 저장 성공!");
            } else {
                response.put("message", "설정 저장에 실패했습니다.");
                System.out.println("설정 저장 실패!");
            }
            
            return response;
        } catch (Exception e) {
            System.out.println("설정 저장 중 오류: " + e.getMessage());
            e.printStackTrace();
            
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
            
            if (success) {
                response.put("message", "설정이 적용되었습니다.");
            } else {
                response.put("message", "설정 적용에 실패했습니다.");
            }
            
            return response;
        } catch (Exception e) {
            System.out.println("설정 적용 중 오류: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "설정 적용 실패: " + e.getMessage());
            return response;
        }
    }
}