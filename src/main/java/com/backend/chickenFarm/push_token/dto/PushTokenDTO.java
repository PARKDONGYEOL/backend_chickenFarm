package com.backend.chickenFarm.push_token.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushTokenDTO {
    private Integer tokenId;
    private String expoPushToken;
    private Integer farmNum;
    private String deviceId;
    private String platform;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
