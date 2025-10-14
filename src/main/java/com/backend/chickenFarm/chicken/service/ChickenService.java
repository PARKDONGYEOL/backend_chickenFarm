package com.backend.chickenFarm.chicken.service;

import com.backend.chickenFarm.chicken.dto.ChickenDTO;
import com.backend.chickenFarm.chicken.mapper.ChickenMapper;
import com.backend.chickenFarm.chicken_batch.mapper.ChickenBatchMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickenService {
  private final ChickenMapper chickenMapper;
  private final ChickenBatchMapper chickenBatchMapper;
  private LocalDate lastUpdateDate = null;

  @PostConstruct
  public void init(){
    updateAgeIfNeeded();
  }

  //나이 자동 증가, 성장 단계 자동 지정
  @Scheduled(cron = "0 0 0 * * ?") // 초 분 시 일 월 요일 => 0초 0분 0시 *매일 *매월 ?요일 상관없음 ==> 매일 자정 실행
  public void updateAgeAndGrowthStage(){
    chickenMapper.updateAgeAndGrowthStage();
  }

  //자정에 나이 증가가 되지 않았을 경우 앱 실행 시 나이 증가
  private void updateAgeIfNeeded(){
    LocalDate today = LocalDate.now();

    if(lastUpdateDate != null && lastUpdateDate.equals(today)){
      return;
    }

    chickenMapper.updateAgeAndGrowthStage();
    lastUpdateDate = today;
  }

  //개체 정보 조회
  public List<ChickenDTO> getChickenInfo(String batchId){
    return chickenMapper.getChickenInfo(batchId);
  }

  //체크된 개체 폐사 처리 + 폐사된 만큼 현재 개체 수 줄이기
  @Transactional(rollbackFor = Exception.class)
  public void updateDeadAndCurrentCount(String batchId, List<Integer> chickenIdList){
    chickenMapper.updateDead(chickenIdList); //건강 상태 변경
    chickenBatchMapper.decreaseCurrentCount(batchId, chickenIdList.size()); //현재 개체 수 감소
  }

  //건강 상태 수정
  public void updateHealthStatus(List<Integer> chickenIdList){
    chickenMapper.updateHealthStatus(chickenIdList);
  }
}
