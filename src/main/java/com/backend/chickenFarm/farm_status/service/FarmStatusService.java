package com.backend.chickenFarm.farm_status.service;

import com.backend.chickenFarm.farm_status.dto.FarmStatusDTO;
import com.backend.chickenFarm.farm_status.mapper.FarmStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FarmStatusService {

    private final FarmStatusMapper farmStatusMapper;

    // 일일 데이터 조회
    public Map<String, Object> getDailyData(Integer farmId, String date) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<FarmStatusDTO> data = farmStatusMapper.getDailyData(farmId, date);
            
            result.put("success", true);
            result.put("data", data);
            result.put("count", data != null ? data.size() : 0);
            result.put("farmId", farmId);
            result.put("date", date);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "일일 데이터 조회 실패: " + e.getMessage());
            result.put("farmId", farmId);
            result.put("date", date);
        }

        return result;
    }

    // 주간 데이터 조회
    public Map<String, Object> getWeeklyData(Integer farmId, String startDate, String endDate) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<FarmStatusDTO> data = farmStatusMapper.getWeeklyData(farmId, startDate, endDate);
            result.put("success", true);
            result.put("data", data);
            result.put("count", data.size());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "주간 데이터 조회 실패");
        }

        return result;
    }

    // 월간 데이터 조회
    public Map<String, Object> getMonthlyData(Integer farmId, String yearMonth) {
        Map<String, Object> result = new HashMap<>();

        try {
            List<FarmStatusDTO> data = farmStatusMapper.getMonthlyData(farmId, yearMonth);
            result.put("success", true);
            result.put("data", data);
            result.put("count", data.size());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "월간 데이터 조회 실패");
        }

        return result;
    }

    // 최신 데이터 조회
    public Map<String, Object> getLatestData(Integer farmId) {
        Map<String, Object> result = new HashMap<>();

        try {
            FarmStatusDTO data = farmStatusMapper.getLatestData(farmId);
            result.put("success", true);
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "최신 데이터 조회 실패");
        }

        return result;
    }
}
