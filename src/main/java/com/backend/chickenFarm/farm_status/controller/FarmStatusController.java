package com.backend.chickenFarm.farm_status.controller;

import com.backend.chickenFarm.farm_status.service.FarmStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm-status")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://192.168.30.*:5173"})
public class FarmStatusController {

    private final FarmStatusService farmStatusService;   /* dddddd */

    // 일일 데이터 조회
    @GetMapping("/daily")
    public Map<String, Object> getDailyData(
            @RequestParam Integer farmId,
            @RequestParam String date) {
        return farmStatusService.getDailyData(farmId, date);
    }

    // 주간 데이터 조회
    @GetMapping("/weekly")
    public Map<String, Object> getWeeklyData(
            @RequestParam Integer farmId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return farmStatusService.getWeeklyData(farmId, startDate, endDate);
    }

    // 월간 데이터 조회
    @GetMapping("/monthly")
    public Map<String, Object> getMonthlyData(
            @RequestParam Integer farmId,
            @RequestParam String yearMonth) {
        return farmStatusService.getMonthlyData(farmId, yearMonth);
    }

    // 최신 데이터 조회
    @GetMapping("/latest")
    public Map<String, Object> getLatestData(@RequestParam Integer farmId) {
        return farmStatusService.getLatestData(farmId);
    }
}
