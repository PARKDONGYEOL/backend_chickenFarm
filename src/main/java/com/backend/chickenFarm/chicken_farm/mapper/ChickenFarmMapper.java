package com.backend.chickenFarm.chicken_farm.mapper;

import com.backend.chickenFarm.chicken_farm.dto.ChickenFarmDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChickenFarmMapper {
  //양계장 등록
  public void regFarmName(String farmName);

  //양계장 번호 조회
  public List<ChickenFarmDTO> getFarmInfo();
}
