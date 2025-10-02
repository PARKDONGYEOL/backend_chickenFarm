package com.backend.chickenFarm.chicken_batch.mapper;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChickenBatchMapper {
  //배치 번호 생성
  public String createBatchId();

  //배치 등록
  public void regBatch(ChickenBatchDTO chickenBatchDTO);
}
