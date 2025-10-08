package com.backend.chickenFarm.chicken_batch.service;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import com.backend.chickenFarm.chicken.mapper.ChickenMapper;
import com.backend.chickenFarm.chicken_batch.dto.ChickenBatchDTO;
import com.backend.chickenFarm.chicken_batch.mapper.ChickenBatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickenBatchService {
  private final ChickenBatchMapper chickenBatchMapper;
  private final ChickenMapper chickenMapper;

  //배치 등록과 동시에 개체 생성(등록)
  @Transactional(rollbackFor = Exception.class)
  public void regBatch(ChickenBatchDTO chickenBatchDTO){
    String batchId = chickenBatchMapper.createBatchId(); //생성한 배치 번호
    chickenBatchDTO.setBatchId(batchId); //생성한 배치 번호를 배치DTO에 넣기
    chickenBatchDTO.setCurrentCount(chickenBatchDTO.getInitialCount()); //입력한 닭의 수(초기 닭의 수) 값을 현재 닭의 수에 넣어주기

    chickenBatchMapper.regBatch(chickenBatchDTO); //배치 등록

    //배치와 겹치는 데이터 개체 빈 값에 넣기
    ChickenDTO chickenDTO = new ChickenDTO();
    chickenDTO.setBatchId(chickenBatchDTO.getBatchId());
    chickenDTO.setFarmNum(chickenBatchDTO.getFarmNum());

    //반복 돌릴 리스트 생성
    List<Integer> chickenList = new ArrayList<>();
    for(int i = 0; i < chickenBatchDTO.getInitialCount(); i++){
      chickenList.add(i);
    }
    chickenDTO.setChickenList(chickenList);

    //개체 등록
    chickenMapper.regChickens(chickenDTO);
  }

  //배치 정보 조회
  public List<ChickenBatchDTO> getBatchInfo(){
    return chickenBatchMapper.getBatchInfo();
  }
}
