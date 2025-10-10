package com.backend.chickenFarm.danger_notice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DangerNoticeDTO {
  private int noticeNum;
  private String noticeContent;
  private String noticeCategory;
  private int farmNum;
  private LocalDateTime recTime;
}
