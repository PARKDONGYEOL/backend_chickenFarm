package com.backend.chickenFarm.danger_notice.service;

import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import com.backend.chickenFarm.danger_notice.mapper.DangerNoticeMapper;
import com.backend.chickenFarm.push_token.dto.PushTokenDTO;
import com.backend.chickenFarm.push_token.service.ExpoPushNotificationService;
import com.backend.chickenFarm.push_token.service.PushTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DangerNoticeService {
  private final DangerNoticeMapper dangerNoticeMapper;
  private final PushTokenService pushTokenService;
  private final ExpoPushNotificationService expoPushNotificationService;

  public void insertDangerNotice(DangerNoticeDTO dto) {
    // 위험 알림 DB에 저장
    dangerNoticeMapper.insertDangerNotice(dto);

    // 푸시 알림 전송
    sendPushNotificationForDanger(dto);
  }

  /**
   * 위험 알림 발생 시 해당 농장의 모든 디바이스에 푸시 알림 전송
   */
  private void sendPushNotificationForDanger(DangerNoticeDTO dto) {
    try {
      // 농장의 모든 푸시 토큰 조회
      List<PushTokenDTO> tokens = pushTokenService.getPushTokensByFarmNum(dto.getFarmNum());

      if (tokens.isEmpty()) {
        log.warn("No push tokens found for farm: {}", dto.getFarmNum());
        return;
      }

      // 토큰 리스트 추출
      List<String> expoPushTokens = tokens.stream()
              .map(PushTokenDTO::getExpoPushToken)
              .toList();

      // 심각도 판단
      String severity = determineSeverity(dto.getNoticeContent());

      // 푸시 알림 전송
      boolean success = expoPushNotificationService.sendDangerAlert(
              expoPushTokens,
              dto.getNoticeCategory(),
              dto.getNoticeContent(),
              severity
      );

      if (success) {
        log.info("Push notification sent to {} devices for farm {}", expoPushTokens.size(), dto.getFarmNum());
      } else {
        log.error("Failed to send push notification for farm {}", dto.getFarmNum());
      }
    } catch (Exception e) {
      log.error("Error sending push notification", e);
    }
  }

  /**
   * 알림 내용으로부터 심각도 판단
   */
  private String determineSeverity(String content) {
    String lowerContent = content.toLowerCase();
    if (lowerContent.contains("critical") || lowerContent.contains("위험") || lowerContent.contains("긴급")) {
      return "Critical";
    } else if (lowerContent.contains("warning") || lowerContent.contains("경고") || lowerContent.contains("주의")) {
      return "Warning";
    }
    return "Warning";
  }

  public List<DangerNoticeDTO> getDangerNotices(int farmNum) {
    return dangerNoticeMapper.getDangerNotices(farmNum);
  }

  // 기간별 조회 (신규)
  public List<DangerNoticeDTO> getDangerNoticesByPeriod(int farmNum, String period) {
    log.info("========== getDangerNoticesByPeriod ==========");
    log.info("farmNum: {}, period: '{}'", farmNum, period);
    List<DangerNoticeDTO> result = dangerNoticeMapper.getDangerNoticesByPeriod(farmNum, period);
    log.info("Result count: {}", result.size());
    if (!result.isEmpty()) {
      log.info("First date: {}", result.get(0).getRecTime());
      log.info("Last date: {}", result.get(result.size() - 1).getRecTime());
    }
    log.info("==============================================");
    return result;
  }

  // 날짜별 분포 확인
  public List<java.util.Map<String, Object>> getDateDistribution(int farmNum) {
    return dangerNoticeMapper.getDateDistribution(farmNum);
  }

  // 알림 삭제 (단일)
  public boolean deleteDangerNotice(int noticeNum) {
    log.info("========== deleteDangerNotice ==========");
    log.info("noticeNum: {}", noticeNum);
    
    int result = dangerNoticeMapper.deleteDangerNotice(noticeNum);
    
    log.info("Deleted rows: {}", result);
    log.info("============================================");
    
    return result > 0;
  }

  // 알림 삭제 (다중)
  public boolean deleteDangerNotices(List<Integer> noticeNums) {
    log.info("========== deleteDangerNotices ==========");
    log.info("noticeNums: {}", noticeNums);
    log.info("Count: {}", noticeNums.size());
    
    if (noticeNums == null || noticeNums.isEmpty()) {
      log.warn("noticeNums is empty");
      return false;
    }
    
    int result = dangerNoticeMapper.deleteDangerNotices(noticeNums);
    
    log.info("Deleted rows: {}", result);
    log.info("=============================================");
    
    return result > 0;
  }
}
