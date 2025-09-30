package com.backend.chickenFarm.chicken.controller;

import com.backend.chickenFarm.chicken.service.ChickenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chicken")
public class ChickenController {
  private final ChickenService chickenService;
}
