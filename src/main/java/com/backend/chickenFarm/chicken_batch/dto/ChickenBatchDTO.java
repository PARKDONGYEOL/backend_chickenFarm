package com.backend.chickenFarm.chicken_batch.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChickenBatchDTO {
  private String batchId;
  private LocalDateTime entryDate;
  private int initialCount;
  private int currentCount;
  private boolean shipmentStatus;
  private int farmNum;
}
