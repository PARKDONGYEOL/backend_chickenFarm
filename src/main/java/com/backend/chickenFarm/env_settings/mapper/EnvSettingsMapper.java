package com.backend.chickenFarm.env_settings.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EnvSettingsMapper {
    
    // 환경 설정 조회
    Map<String, Object> getEnvSettings();
    
    // 환경 설정 업데이트
    int updateEnvSettings(@Param("settings") Map<String, Object> settings);
    
    // 환경 설정 삽입 (초기 설정)
    int insertEnvSettings(@Param("settings") Map<String, Object> settings);
}
