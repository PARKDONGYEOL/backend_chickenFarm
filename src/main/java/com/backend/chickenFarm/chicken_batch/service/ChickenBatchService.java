package com.backend.chickenFarm.chicken_batch.service;

import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import com.backend.chickenFarm.chicken_batch.mapper.ChickenBatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChickenBatchService {
  private final ChickenBatchMapper chickenBatchMapper;

  //배치 등록과 동시에 개체 생성(등록)
  @Transactional(rollbackFor = Exception.class)
  public void regBatch(ChickenBatchDTO chickenBatchDTO){
    String batchId = chickenBatchMapper.createBatchId(); //생성한 배치 번호
    chickenBatchDTO.setBatchId(batchId); //생성한 배치 번호를 배치DTO에 넣기
    chickenBatchDTO.setCurrentCount(chickenBatchDTO.getInitialCount()); //입력한 닭의 수(초기 닭의 수) 값을 현재 닭의 수에 넣어주기

    chickenBatchMapper.regBatch(chickenBatchDTO); //배치 등록

    //개체 생성(등록)
  }
}
