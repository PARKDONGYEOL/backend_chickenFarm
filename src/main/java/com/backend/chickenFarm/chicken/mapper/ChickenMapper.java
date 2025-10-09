package com.backend.chickenFarm.chicken.mapper;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import com.backend.chickenFarm.chicken.dto.ChickenWeightHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChickenMapper {
  //나이 자동 증가, 성장 단계 자동 지정
  public void updateAgeAndGrowthStage();

  //닭 개체 생성
  public void regChickens(ChickenDTO chickenDTO);

  //몸무게 히스토리 테이블에 기록
  public void insertChickenWeight(ChickenWeightHistoryDTO weightHistoryDTO);

  //닭 몸무게 업데이트
  public void updateChickenWeight(ChickenWeightHistoryDTO weightHistoryDTO);

  //닭 정보 조회
  public List<ChickenDTO> getChickenInfo(String batchId);
}
