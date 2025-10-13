package com.backend.chickenFarm.farm_status.mapper;

import com.backend.chickenFarm.farm_status.dto.FarmStatusDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FarmStatusMapper {

    // 일일 데이터 조회 (특정 날짜)
    List<FarmStatusDTO> getDailyData(@Param("farmId") Integer farmId, @Param("date") String date);

    // 주간 데이터 조회 (평균값)
    List<FarmStatusDTO> getWeeklyData(@Param("farmId") Integer farmId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // 월간 데이터 조회 (평균값)
    List<FarmStatusDTO> getMonthlyData(@Param("farmId") Integer farmId, @Param("yearMonth") String yearMonth);

    // 최신 데이터 조회
    FarmStatusDTO getLatestData(@Param("farmId") Integer farmId);
}
