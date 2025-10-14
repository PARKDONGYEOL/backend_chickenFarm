package com.backend.chickenFarm.danger_notice.service;

import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import com.backend.chickenFarm.danger_notice.mapper.DangerNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
