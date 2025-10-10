package com.backend.chickenFarm.inoculation.controller;

import com.backend.chickenFarm.inoculation.dto.InoculationDTO;
import com.backend.chickenFarm.inoculation.service.InoculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inoculation")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000"})
public class InoculationController {

    private final InoculationService inoculationService;

    // 테스트 엔드포인트
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("백엔드 서버가 정상적으로 실행 중입니다!");
    }

    // 농장별 배치 목록 조회
    @GetMapping("/batches/{farmId}")
    public ResponseEntity<List<Map<String, Object>>> getBatchesByFarm(@PathVariable int farmId) {
        try {
            List<Map<String, Object>> batches = inoculationService.getBatchesByFarm(farmId);
            return ResponseEntity.ok(batches);
        } catch (Exception e) {
            log.error("배치 목록 조회 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 배치별 닭 목록 조회
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<Map<String, Object>>> getChickensByBatch(@PathVariable String batchId) {
        try {
            List<Map<String, Object>> chickens = inoculationService.getChickensByBatch(batchId);
            return ResponseEntity.ok(chickens);
        } catch (Exception e) {
            log.error("닭 목록 조회 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 예방접종 실행
    @PostMapping("/perform")
    public ResponseEntity<?> performInoculation(@RequestBody InoculationDTO inoculationDTO) {
        try {
            log.info("예방접종 실행 요청: {}", inoculationDTO);
            inoculationService.performInoculation(inoculationDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "예방접종이 성공적으로 완료되었습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("예방접종 처리 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("예방접종 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 예방접종 미완료 처리 (삭제)
    @DeleteMapping("/perform")
    public ResponseEntity<?> deleteInoculation(@RequestBody InoculationDTO inoculationDTO) {
        try {
            log.info("예방접종 미완료 처리 요청: {}", inoculationDTO);
            inoculationService.deleteInoculation(inoculationDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "예방접종 기록이 삭제되었습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("예방접종 삭제 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("예방접종 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 예방접종 스케줄 조회
    @GetMapping("/schedule/{batchId}")
    public ResponseEntity<Map<String, Object>> getInoculationSchedule(@PathVariable String batchId) {
        try {
            Map<String, Object> schedule = inoculationService.getInoculationStats(batchId);
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            log.error("스케줄 조회 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}