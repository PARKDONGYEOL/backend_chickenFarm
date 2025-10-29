package com.backend.chickenFarm.danger_notice.controller;


import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import com.backend.chickenFarm.chicken_farm.service.ChickenFarmService;
import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import com.backend.chickenFarm.danger_notice.service.DangerNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/danger")
public class DangerNoticeController {
  private final DangerNoticeService dangerNoticeService;

  @PostMapping("/insert")
  public ResponseEntity<?> insertDangerNotice(@RequestBody DangerNoticeDTO dto) {
    try {
      dangerNoticeService.insertDangerNotice(dto);
      return ResponseEntity.ok().body(Map.of("success", true));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("success", false, "error", e.getMessage()));
    }
  }

  @GetMapping("/list/{farmNum}")
  public ResponseEntity<?> getDangerNotices(@PathVariable int farmNum) {
    try {
      List<DangerNoticeDTO> notices = dangerNoticeService.getDangerNotices(farmNum);
      return ResponseEntity.ok().body(Map.of("success", true, "data", notices));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("success", false, "error", e.getMessage()));
    }
  }

  // 기간별 조회 (신규) - 이게 새로 추가된 거예요!
  @GetMapping("/{farmNum}/period")
  public ResponseEntity<List<DangerNoticeDTO>> getDangerNoticesByPeriod(
          @PathVariable int farmNum,
          @RequestParam(defaultValue = "day") String period) {

    List<DangerNoticeDTO> notices = dangerNoticeService.getDangerNoticesByPeriod(farmNum, period);
    return ResponseEntity.ok(notices);
  }

  // 날짜 범위 확인용 테스트 API
  @GetMapping("/{farmNum}/date-range")
  public ResponseEntity<?> getDateRange(@PathVariable int farmNum) {
    List<DangerNoticeDTO> allNotices = dangerNoticeService.getDangerNotices(farmNum);
    if (allNotices.isEmpty()) {
      return ResponseEntity.ok(Map.of("message", "No data"));
    }
    return ResponseEntity.ok(Map.of(
            "total", allNotices.size(),
            "oldest", allNotices.get(allNotices.size() - 1).getRecTime(),
            "newest", allNotices.get(0).getRecTime()
    ));
  }

  // 날짜별 데이터 분포 확인
  @GetMapping("/{farmNum}/date-distribution")
  public ResponseEntity<?> getDateDistribution(@PathVariable int farmNum) {
    return ResponseEntity.ok(dangerNoticeService.getDateDistribution(farmNum));
  }

  // 알림 삭제 (단일)
  @DeleteMapping("/delete/{noticeNum}")
  public ResponseEntity<?> deleteDangerNotice(@PathVariable int noticeNum) {
    try {
      boolean success = dangerNoticeService.deleteDangerNotice(noticeNum);
      return ResponseEntity.ok().body(Map.of(
        "success", success,
        "message", success ? "알림이 삭제되었습니다." : "알림 삭제에 실패했습니다."
      ));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
        "success", false,
        "message", "서버 오류: " + e.getMessage()
      ));
    }
  }

  // 알림 삭제 (다중)
  @DeleteMapping("/delete/batch")
  public ResponseEntity<?> deleteDangerNotices(@RequestBody Map<String, List<Integer>> requestBody) {
    try {
      List<Integer> noticeNums = requestBody.get("noticeNums");
      
      if (noticeNums == null || noticeNums.isEmpty()) {
        return ResponseEntity.badRequest().body(Map.of(
          "success", false,
          "message", "삭제할 알림 번호가 없습니다."
        ));
      }
      
      boolean success = dangerNoticeService.deleteDangerNotices(noticeNums);
      
      return ResponseEntity.ok().body(Map.of(
        "success", success,
        "message", success ? noticeNums.size() + "개의 알림이 삭제되었습니다." : "알림 삭제에 실패했습니다.",
        "deletedCount", noticeNums.size()
      ));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
        "success", false,
        "message", "서버 오류: " + e.getMessage()
      ));
    }
  }
}
