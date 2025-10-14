package com.backend.chickenFarm.note.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoteDTO {
    private Integer noteNum;        // 일지 번호 (PK, AUTO_INCREMENT)
    private String memId;           // 작성자 ID
    private String content;         // 일지 내용
    private LocalDateTime recTime;  // 기록 시간
}
