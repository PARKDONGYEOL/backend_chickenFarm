package com.backend.chickenFarm.push_token.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpoPushNotificationService {
    private static final String EXPO_PUSH_URL = "https://exp.host/--/api/v2/push/send";
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 단일 푸시 알림 전송
     */
    public boolean sendPushNotification(String expoPushToken, String title, String body, Map<String, Object> data) {
        List<String> tokens = new ArrayList<>();
        tokens.add(expoPushToken);
        return sendPushNotifications(tokens, title, body, data);
    }

    /**
     * 다중 푸시 알림 전송 (여러 디바이스에 동시 전송)
     */
    public boolean sendPushNotifications(List<String> expoPushTokens, String title, String body, Map<String, Object> data) {
        log.info("========== sendPushNotifications ==========");
        log.info("Sending to {} devices", expoPushTokens.size());
        log.info("Title: {}", title);
        log.info("Body: {}", body);

        try {
            // Expo Push 메시지 구성
            List<Map<String, Object>> messages = new ArrayList<>();

            for (String token : expoPushTokens) {
                Map<String, Object> message = new HashMap<>();
                message.put("to", token);
                message.put("sound", "default");
                message.put("title", title);
                message.put("body", body);
                message.put("data", data != null ? data : new HashMap<>());
                message.put("priority", "high");
                message.put("channelId", "default");

                messages.add(message);
            }

            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.set("Accept-Encoding", "gzip, deflate");

            // HTTP 요청 생성
            HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(messages, headers);

            // Expo Push API 호출
            ResponseEntity<Map> response = restTemplate.exchange(
                    EXPO_PUSH_URL,
                    HttpMethod.POST,
                    request,
                    Map.class
            );

            log.info("Response status: {}", response.getStatusCode());
            log.info("Response body: {}", response.getBody());

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody != null && responseBody.containsKey("data")) {
                    List<Map<String, Object>> results = (List<Map<String, Object>>) responseBody.get("data");

                    // 에러 체크
                    for (Map<String, Object> result : results) {
                        if (result.containsKey("status") && "error".equals(result.get("status"))) {
                            log.error("Push notification error: {}", result.get("message"));
                            return false;
                        }
                    }

                    log.info("Push notifications sent successfully");
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            log.error("Error sending push notifications", e);
            return false;
        } finally {
            log.info("===========================================");
        }
    }

    /**
     * 위험 알림 전송 (센서 알림용)
     */
    public boolean sendDangerAlert(List<String> expoPushTokens, String sensorType, String message, String severity) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", "danger_alert");
        data.put("sensorType", sensorType);
        data.put("severity", severity);
        data.put("timestamp", System.currentTimeMillis());

        String title = getSensorEmoji(sensorType) + " " + getSensorTitle(sensorType);

        return sendPushNotifications(expoPushTokens, title, message, data);
    }

    /**
     * 센서별 이모지
     */
    private String getSensorEmoji(String sensorType) {
        switch (sensorType.toLowerCase()) {
            case "temperature":
            case "온도":
                return "🌡️";
            case "humidity":
            case "습도":
                return "💧";
            case "light":
            case "조도":
                return "💡";
            case "ammonia":
            case "암모니아":
                return "⚠️";
            case "co2":
            case "이산화탄소":
                return "☁️";
            case "co":
            case "일산화탄소":
                return "🔴";
            case "no2":
            case "이산화질소":
                return "⚡";
            default:
                return "🔔";
        }
    }

    /**
     * 센서별 제목
     */
    private String getSensorTitle(String sensorType) {
        switch (sensorType.toLowerCase()) {
            case "temperature":
                return "Temperature Alert";
            case "온도":
                return "온도 경고";
            case "humidity":
                return "Humidity Alert";
            case "습도":
                return "습도 경고";
            case "ammonia":
                return "Ammonia Alert";
            case "암모니아":
                return "암모니아 경고";
            default:
                return "Sensor Alert";
        }
    }
}
