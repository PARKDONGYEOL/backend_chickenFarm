package com.backend.chickenFarm.member.controller;

import com.backend.chickenFarm.member.dto.MemberDTO;
import com.backend.chickenFarm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@CrossOrigin(origins = "*") // CORS 허용 (개발용)
public class MemberController {
    private final MemberService memberService;

    // 기존 로그인 API
    @GetMapping()
    public MemberDTO getMember(MemberDTO memberDTO){
        return memberService.getMember(memberDTO);
    }

    // 비밀번호 변경 API
    @PutMapping("/password")
    public Map<String, Object> updatePassword(@RequestBody MemberDTO memberDTO) {
        System.out.println("비밀번호 변경 요청 받음: " + memberDTO);
        return memberService.updatePassword(memberDTO);
    }

    // 이름 변경 API
    @PutMapping("/name")
    public Map<String, Object> updateName(@RequestBody MemberDTO memberDTO) {
        System.out.println("이름 변경 요청 받음: " + memberDTO);
        return memberService.updateName(memberDTO);
    }
}