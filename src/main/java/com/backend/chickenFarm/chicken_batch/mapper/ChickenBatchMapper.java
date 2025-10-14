package com.backend.chickenFarm.chicken_batch.mapper;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChickenBatchMapper {
  //배치 번호 생성
  public String createBatchId();

  //배치 등록
  public void regBatch(ChickenBatchDTO chickenBatchDTO);

  //배치 정보 조회
  public List<ChickenBatchDTO> getBatchInfoList();

  //배치 출하
  public void updateShipmentStatus(ChickenBatchDTO chickenBatchDTO);

  //폐사된 만큼 현재 개체 수 줄이기
  public void decreaseCurrentCount(@Param("batchId") String batchId, @Param("deadCount") int deadCount);
}
