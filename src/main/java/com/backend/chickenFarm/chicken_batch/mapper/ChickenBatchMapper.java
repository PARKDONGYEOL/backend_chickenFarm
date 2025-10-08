package com.backend.chickenFarm.chicken_batch.mapper;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChickenBatchMapper {
  //배치 번호 생성
  public String createBatchId();

  //배치 등록
  public void regBatch(ChickenBatchDTO chickenBatchDTO);

  //배치 정보 조회
  public List<ChickenBatchDTO> getBatchInfo();
}
