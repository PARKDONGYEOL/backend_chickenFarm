package com.backend.chickenFarm.danger_notice.mapper;

import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DangerNoticeMapper {
  public void insertDangerNotice(DangerNoticeDTO dangerNoticeDTO);
}
