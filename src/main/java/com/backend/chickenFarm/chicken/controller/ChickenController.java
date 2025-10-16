package com.backend.chickenFarm.chicken.controller;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import com.backend.chickenFarm.chicken.service.ChickenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chicken")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://192.168.30.*:5173"})
public class ChickenController {
  private final ChickenService chickenService;

  //개체 정보 조회
  @GetMapping("/{batchId}")
  public ResponseEntity<?> getChickenInfo(@PathVariable("batchId") String batchId){
    try {
      List<ChickenDTO> chickenList = chickenService.getChickenInfo(batchId);
      return ResponseEntity.status(HttpStatus.OK).body(chickenList);
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("닭 정보 조회 쿼리 실행 중 오류가 발생했습니다.");
    }
  }

  //체크된 개체 폐사 처리 + 폐사된 만큼 현재 개체 수 줄이기
  @PutMapping("/death")
  public ResponseEntity<?> updateDeadAndCurrentCount(@RequestBody ChickenDTO chickenDTO){
    try {
      chickenService.updateDeadAndCurrentCount(chickenDTO.getBatchId(), chickenDTO.getChickenIdList());
      return ResponseEntity.status(HttpStatus.OK).build();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("개체 폐사 처리 쿼리 실행 중 오류가 발생했습니다.");
    }
  }

  //건강 상태 수정
  @PutMapping("/update-health")
  public ResponseEntity<?> updateHealthStatus(@RequestBody ChickenDTO chickenDTO){
    try {
      chickenService.updateHealthStatus(chickenDTO.getChickenIdList());
      return ResponseEntity.status(HttpStatus.OK).build();
    }catch (Exception e){
      e.printStackTrace();
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("건강 상태 수정 쿼리 실행 중 오류가 발생했습니다.");
    }
  }
}
