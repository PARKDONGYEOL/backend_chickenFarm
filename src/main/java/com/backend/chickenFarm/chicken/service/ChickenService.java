package com.backend.chickenFarm.chicken.service;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import com.backend.chickenFarm.chicken.dto.ChickenWeightHistoryDTO;
import com.backend.chickenFarm.chicken.mapper.ChickenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickenService {
  private final ChickenMapper chickenMapper;

  //나이 자동 증가, 성장 단계 자동 지정
  @Scheduled(cron = "0 0 0 * * ?") // 초 분 시 일 월 요일 => 0초 0분 0시 *매일 *매월 ?요일 상관없음 ==> 매일 자정 실행
  public void updateAgeAndGrowthStage(){
    chickenMapper.updateAgeAndGrowthStage();
  }

  //몸무게 히스토리 테이블에 기록 + 새로운 몸무게로 치킨 테이블 몸무게 컬럼값 업데이트
  @Transactional(rollbackFor = Exception.class)
  public void insertAndUpdateChickenWeight(ChickenWeightHistoryDTO weightHistoryDTO){
    chickenMapper.insertChickenWeight(weightHistoryDTO);
    chickenMapper.updateChickenWeight(weightHistoryDTO);
  }

  //닭 정보 조회
  public List<ChickenDTO> getChickenInfo(String batchId){
    return chickenMapper.getChickenInfo(batchId);
  }
}
