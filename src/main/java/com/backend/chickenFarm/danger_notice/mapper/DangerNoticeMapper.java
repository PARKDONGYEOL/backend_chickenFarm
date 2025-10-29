package com.backend.chickenFarm.danger_notice.mapper;

import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DangerNoticeMapper {
  public void insertDangerNotice(DangerNoticeDTO dangerNoticeDTO);
  public List<DangerNoticeDTO> getDangerNotices(int farmNum);

  // 기간별 조회 (신규)
  List<DangerNoticeDTO> getDangerNoticesByPeriod(
          @Param("farmNum") int farmNum,
          @Param("period") String period
  );

  // 날짜별 분포 확인
  List<java.util.Map<String, Object>> getDateDistribution(@Param("farmNum") int farmNum);

  // 알림 삭제 (단일)
  int deleteDangerNotice(@Param("noticeNum") int noticeNum);

  // 알림 삭제 (다중)
  int deleteDangerNotices(@Param("list") List<Integer> noticeNums);
}
