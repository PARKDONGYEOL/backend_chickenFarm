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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;

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
      return ResponseEntity.status(500).body(Map.of("success", false, "error", e.getMessage()));
    }
  }

  @GetMapping("/list/{farmNum}")
  public ResponseEntity<?> getDangerNotices(@PathVariable int farmNum) {
    try {
      List<DangerNoticeDTO> notices = dangerNoticeService.getDangerNotices(farmNum);
      return ResponseEntity.ok().body(Map.of("success", true, "data", notices));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("success", false, "error", e.getMessage()));
    }
  }
}
