package com.backend.chickenFarm.chicken.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChickenMapper {
  //나이 자동 증가, 성장 단계 자동 지정
  public void updateAgeAndGrowthStage();
}
