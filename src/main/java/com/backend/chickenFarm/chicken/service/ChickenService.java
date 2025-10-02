package com.backend.chickenFarm.chicken.service;

import com.backend.chickenFarm.chicken.mapper.ChickenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickenService {
  private final ChickenMapper chickenMapper;

  //나이 자동 증가, 성장 단계 자동 지정
  @Scheduled(cron = "0 0 0 * * ?") // 초 분 시 일 월 요일 => 0초 0분 0시 *매일 *매월 ?요일 상관없음 ==> 매일 자정 실행
  public void updateAgeAndGrowthStage(){
    chickenMapper.updateAgeAndGrowthStage();
  }
}
