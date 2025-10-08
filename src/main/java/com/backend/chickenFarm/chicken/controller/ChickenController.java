package com.backend.chickenFarm.chicken.controller;

import com.backend.chickenFarm.chicken.dto.ChickenWeightHistoryDTO;
import com.backend.chickenFarm.chicken.service.ChickenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chicken")
public class ChickenController {
  private final ChickenService chickenService;

  //몸무게 히스토리 테이블에 기록 + 새로운 몸무게로 치킨 테이블 몸무게 컬럼값 업데이트
  @PostMapping("")
  public ResponseEntity<?> insertAndUpdateChickenWeight(@RequestBody ChickenWeightHistoryDTO weightHistoryDTO){
    try {
      chickenService.insertAndUpdateChickenWeight(weightHistoryDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("몸무게 기록 쿼리 실행 중 오류가 발생했습니다.");
    }

  }
}
