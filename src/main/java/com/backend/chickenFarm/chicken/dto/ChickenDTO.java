package com.backend.chickenFarm.chicken.dto;

import lombok.Data;

@Data
public class ChickenDTO {
  private int chickenId;
  private float rawWeight;
  private int age;
  private String growthStage;
  private String batchId;
  private int farmNum;
  private String healthStatus;
}
