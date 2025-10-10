package com.backend.chickenFarm.danger_notice.controller;


import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import com.backend.chickenFarm.chicken_farm.service.ChickenFarmService;
import com.backend.chickenFarm.danger_notice.dto.DangerNoticeDTO;
import com.backend.chickenFarm.danger_notice.service.DangerNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/danger")
public class DangerNoticeController {
  private final DangerNoticeService dangerNoticeService;

  @PostMapping("/insert")
  public ResponseEntity<?> insertDangerNotice(@RequestBody DangerNoticeDTO dto) {
    try {
      dangerNoticeService.insertDangerNotice(dto);
      return ResponseEntity.ok().body(Map.of("success", true));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(Map.of("success", false, "error", e.getMessage()));
    }
  }
}
