package com.backend.chickenFarm.push_token.service;

import com.backend.chickenFarm.push_token.dto.PushTokenDTO;
import com.backend.chickenFarm.push_token.mapper.PushTokenMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushTokenService {
    private final PushTokenMapper pushTokenMapper;

    /**
     * 푸시 토큰 등록 또는 업데이트
     */
    public boolean registerPushToken(PushTokenDTO dto) {
        log.info("========== registerPushToken ==========");
        log.info("Token: {}", dto.getExpoPushToken());
        log.info("FarmNum: {}, DeviceId: {}, Platform: {}", dto.getFarmNum(), dto.getDeviceId(), dto.getPlatform());

        try {
            // 기존 토큰 확인
            PushTokenDTO existingToken = pushTokenMapper.getPushTokenByToken(dto.getExpoPushToken());

            if (existingToken != null) {
                // 업데이트
                int updated = pushTokenMapper.updatePushToken(dto);
                log.info("Updated existing token: {}", updated > 0);
                return updated > 0;
            } else {
                // 신규 등록
                pushTokenMapper.insertPushToken(dto);
                log.info("Inserted new token");
                return true;
            }
        } catch (Exception e) {
            log.error("Error registering push token", e);
            return false;
        } finally {
            log.info("=========================================");
        }
    }

    /**
     * 농장별 모든 푸시 토큰 조회
     */
    public List<PushTokenDTO> getPushTokensByFarmNum(int farmNum) {
        return pushTokenMapper.getPushTokensByFarmNum(farmNum);
    }

    /**
     * 푸시 토큰 삭제
     */
    public boolean deletePushToken(String expoPushToken) {
        log.info("========== deletePushToken ==========");
        log.info("Token: {}", expoPushToken);

        int result = pushTokenMapper.deletePushToken(expoPushToken);

        log.info("Deleted: {}", result > 0);
        log.info("=====================================");

        return result > 0;
    }
}
