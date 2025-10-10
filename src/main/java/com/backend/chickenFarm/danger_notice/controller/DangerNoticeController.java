package com.backend.chickenFarm.danger_notice.controller;


import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import com.backend.chickenFarm.chicken_farm.service.ChickenFarmService;
import com.backend.chickenFarm.danger_notice.service.DangerNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class DangerNoticeController {
  private final DangerNoticeService dangerNoticeService;

}
