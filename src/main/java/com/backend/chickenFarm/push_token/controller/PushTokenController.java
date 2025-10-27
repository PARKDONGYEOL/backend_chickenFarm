package com.backend.chickenFarm.push_token.controller;

import com.backend.chickenFarm.push_token.dto.PushTokenDTO;
import com.backend.chickenFarm.push_token.service.ExpoPushNotificationService;
import com.backend.chickenFarm.push_token.service.PushTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/push-token")
public class PushTokenController {
    private final PushTokenService pushTokenService;
    private final ExpoPushNotificationService expoPushNotificationService;

    /**
     * 푸시 토큰 등록
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerPushToken(@RequestBody PushTokenDTO dto) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = pushTokenService.registerPushToken(dto);

            response.put("success", success);
            response.put("message", success ? "푸시 토큰이 등록되었습니다." : "푸시 토큰 등록에 실패했습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * 푸시 토큰 삭제
     */
    @DeleteMapping("/remove/{expoPushToken}")
    public ResponseEntity<Map<String, Object>> removePushToken(@PathVariable String expoPushToken) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = pushTokenService.deletePushToken(expoPushToken);

            response.put("success", success);
            response.put("message", success ? "푸시 토큰이 삭제되었습니다." : "푸시 토큰 삭제에 실패했습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * 테스트 푸시 알림 전송
     */
    @PostMapping("/test")
    public ResponseEntity<Map<String, Object>> sendTestNotification(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String expoPushToken = (String) request.get("expoPushToken");
            String title = (String) request.getOrDefault("title", "테스트 알림");
            String body = (String) request.getOrDefault("body", "이것은 테스트 푸시 알림입니다.");
            Map<String, Object> data = (Map<String, Object>) request.getOrDefault("data", new HashMap<>());

            boolean success = expoPushNotificationService.sendPushNotification(expoPushToken, title, body, data);

            response.put("success", success);
            response.put("message", success ? "테스트 알림이 전송되었습니다." : "알림 전송에 실패했습니다.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * 농장의 모든 디바이스에 푸시 알림 전송
     */
    @PostMapping("/send-to-farm/{farmNum}")
    public ResponseEntity<Map<String, Object>> sendToFarm(
            @PathVariable int farmNum,
            @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 농장의 모든 푸시 토큰 조회
            List<PushTokenDTO> tokens = pushTokenService.getPushTokensByFarmNum(farmNum);

            if (tokens.isEmpty()) {
                response.put("success", false);
                response.put("message", "등록된 디바이스가 없습니다.");
                return ResponseEntity.ok(response);
            }

            // 토큰 리스트 추출
            List<String> expoPushTokens = tokens.stream()
                    .map(PushTokenDTO::getExpoPushToken)
                    .toList();

            String title = (String) request.get("title");
            String body = (String) request.get("body");
            Map<String, Object> data = (Map<String, Object>) request.getOrDefault("data", new HashMap<>());

            boolean success = expoPushNotificationService.sendPushNotifications(expoPushTokens, title, body, data);

            response.put("success", success);
            response.put("message", success ? tokens.size() + "개 디바이스에 알림을 전송했습니다." : "알림 전송에 실패했습니다.");
            response.put("deviceCount", tokens.size());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
