package com.backend.chickenFarm.chicken_batch.service;

import com.backend.chickenFarm.chicken_batch.mapper.ChickenBatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickenBatchService {
  private final ChickenBatchMapper chickenBatchMapper;
}
