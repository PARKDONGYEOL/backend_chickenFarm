package com.backend.chickenFarm.chicken_farm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChickenFarmMapper {
  //양계장 등록
  public void regFarmName(String farmName);
}
