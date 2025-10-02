package com.backend.chickenFarm.chicken.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChickenWeightHistoryDTO {
  private int historyId;
  private int chickenId;
  private float rawWeight;
  private LocalDateTime recTime;
  private int recAge;
}
