package com.backend.chickenFarm.chicken_batch.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ChickenBatchDTO {
  private String batchId;
  private LocalDate entryDate;
  private int initialCount;
  private int currentCount;
  private boolean shipmentStatus;
  private int farmNum;

  private List<String> batchIdList;
}
