package com.backend.chickenFarm.farm_status.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FarmStatusDTO {
    private Integer farmStatusNum;    // PK
    private Double no2Data;           // 이산화질소 데이터
    private Double tempData;          // 온도 데이터
    private Double humData;           // 습도 데이터
    private Double luxData;           // 조도 데이터
    private Double co2Data;           // 이산화탄소 데이터
    private Double nh3Data;           // 암모니아 데이터
    private LocalDateTime recTime;    // 기록 시간
    private Integer farmId;           // 농장 ID
}
