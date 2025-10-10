package com.backend.chickenFarm.inoculation.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InoculationDTO {
    // 배치 정보
    private String batchId;
    private LocalDateTime entryDate;
    private int initialCount;
    private int currentCount;
    private boolean shipmentStatus;
    private int farmNum;

    // 닭 정보
    private int chickenId;
    private int age;
    private double weight;
    private String growthStage;
    private String healthStatus;

    // 예방접종 상태
    private boolean nd;
    private boolean hpai;
    private boolean ibd;
    private boolean ib;

    // 예방접종 실행 정보
    private List<Integer> chickenIds;
    private String vaccineType;
    private String vaccinationMethod;
    private String vaccinatedBy;
    private String notes;
    private LocalDateTime vaccinationDate;

    // 스케줄 정보
    private String vaccine;
    private String name;
    private int day;
    private String method;
    private String color;
}