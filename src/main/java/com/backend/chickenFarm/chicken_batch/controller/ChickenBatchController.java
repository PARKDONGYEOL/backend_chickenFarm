package com.backend.chickenFarm.chicken_batch.controller;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import com.backend.chickenFarm.chicken_batch.service.ChickenBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class ChickenBatchController {
  private final ChickenBatchService chickenBatchService;

  @PostMapping("")
  public ResponseEntity<?> regBatchAndChickens(@RequestBody ChickenBatchDTO chickenBatchDTO){
    try {
      chickenBatchService.regBatch(chickenBatchDTO);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("배치 & 개체 동시 등록 쿼리 실행 중 오류가 발생했습니다.");
    }

  }
}
