package com.backend.chickenFarm.chicken_farm.service;

import com.backend.chickenFarm.chicken_farm.mapper.ChickenFarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickenFarmService {
  private final ChickenFarmMapper chickenFarmMapper;

  //양계장 등록
  public void regFarmName(String farmName){
    chickenFarmMapper.regFarmName(farmName);
  }
}
