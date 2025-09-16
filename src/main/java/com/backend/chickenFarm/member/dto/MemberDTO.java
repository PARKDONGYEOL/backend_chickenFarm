package com.backend.chickenFarm.member.dto;


import lombok.Data;

@Data
public class MemberDTO {
    private String memId;               //아이디
    private String memPw;               //비밀번호
    private String contact;             //연락처
    private String name;                //이름
    private String role;                //권한
}
