package com.backend.chickenFarm.chicken_batch.controller;

import com.backend.chickenFarm.chicken_batch.service.ChickenBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class ChickenBatchController {
  private final ChickenBatchService chickenBatchService;
}
