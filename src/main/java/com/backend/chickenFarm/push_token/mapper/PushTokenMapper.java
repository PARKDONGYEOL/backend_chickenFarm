package com.backend.chickenFarm.push_token.mapper;

import com.backend.chickenFarm.push_token.dto.PushTokenDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PushTokenMapper {
    // 푸시 토큰 등록
    void insertPushToken(PushTokenDTO dto);

    // 푸시 토큰 업데이트 (기존 토큰이 있는 경우)
    int updatePushToken(PushTokenDTO dto);

    // 푸시 토큰 조회 (토큰으로)
    PushTokenDTO getPushTokenByToken(String expoPushToken);

    // 농장별 모든 푸시 토큰 조회
    List<PushTokenDTO> getPushTokensByFarmNum(int farmNum);

    // 푸시 토큰 삭제
    int deletePushToken(String expoPushToken);

    // 디바이스 ID로 조회
    PushTokenDTO getPushTokenByDeviceId(String deviceId);
}
