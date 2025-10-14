package com.backend.chickenFarm.chicken.mapper;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChickenMapper {
  //나이 자동 증가, 성장 단계 자동 지정
  public void updateAgeAndGrowthStage();

  //닭 개체 생성
  public void regChickens(ChickenDTO chickenDTO);

  //개체 정보 조회
  public List<ChickenDTO> getChickenInfo(String batchId);

  //체크된 개체 폐사 처리
  public void updateDead(List<Integer> chickenIdList);

  //건강 상태 수정
  public void updateHealthStatus(List<Integer> chickenIdList);
}
