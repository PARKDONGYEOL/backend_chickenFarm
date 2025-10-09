package com.backend.chickenFarm.chicken.dto;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import lombok.Data;

import java.util.List;

@Data
public class ChickenDTO {
  private int chickenId;
  private float rawWeight;
  private int age;
  private String growthStage;
  private String batchId;
  private int farmNum;
  private String healthStatus;

  private List<Integer> chickenList; //배치 등록 시 입력한 닭의 수 만큼 개체 생성 반복문을 위한 변수
  private ChickenBatchDTO batchDTO;
}
