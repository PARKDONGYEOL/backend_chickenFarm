package com.backend.chickenFarm.chicken_farm.controller;

import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import com.backend.chickenFarm.chicken_farm.service.ChickenFarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/farm")
public class ChickenFarmController {
  private final ChickenFarmService chickenFarmService;

  //양계장 등록
  @PostMapping("")
  public ResponseEntity<?> regFarmName(@RequestBody ChickenFarmDTO chickenFarmDTO){
    try {
      chickenFarmService.regFarmName(chickenFarmDTO.getFarmName());
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus
              .INTERNAL_SERVER_ERROR)
              .body("양계장 등록 쿼리 실행 중 오류가 발생했습니다.");
    }
  }

  //양계장 번호 조회
  @GetMapping("/num-list")
  public ResponseEntity<?> getFarmInfo(){
    try {
      List<ChickenFarmDTO> numList = chickenFarmService.getFarmInfo();
      return ResponseEntity.status(HttpStatus.OK).body(numList);
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("양계장 번호 조회 쿼리 실행 중 오류가 발생했습니다.");
    }
  }
}
