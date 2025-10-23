package com.backend.chickenFarm.danger_notice.service;

import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import com.backend.chickenFarm.danger_notice.mapper.DangerNoticeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DangerNoticeService {
  private final DangerNoticeMapper dangerNoticeMapper;

  public void insertDangerNotice(DangerNoticeDTO dto) {
    dangerNoticeMapper.insertDangerNotice(dto);
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
