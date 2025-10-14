package com.backend.chickenFarm.chicken_farm.service;

import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import com.backend.chickenFarm.chicken_farm.mapper.ChickenFarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickenFarmService {
  private final ChickenFarmMapper chickenFarmMapper;

  //양계장 등록
  public void regFarmName(String farmName){
    chickenFarmMapper.regFarmName(farmName);
  }

  //양계장 번호 조회
  public List<ChickenFarmDTO> getFarmInfo(){
    return chickenFarmMapper.getFarmInfo();
  }
}
