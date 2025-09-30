package com.backend.chickenFarm.chicken.service;

import com.backend.chickenFarm.chicken.mapper.ChickenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickenService {
  private final ChickenMapper chickenMapper;
}
