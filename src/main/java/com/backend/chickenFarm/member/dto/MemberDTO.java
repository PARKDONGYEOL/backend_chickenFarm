package com.backend.chickenFarm.member.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String memId;               // 아이디
    private String memPw;               // 비밀번호
    private String contact;             // 연락처
    private String name;                // 이름
    private String role;                // 권한

    // 설정 페이지용 추가 필드
    private String currentPassword;     // 현재 비밀번호 (비밀번호 변경 시)
    private String newPassword;         // 새 비밀번호
    private String newName;             // 새 이름 (이름 변경 시)
}