package com.backend.chickenFarm.member.controller;

import com.backend.chickenFarm.member.dto.MemberDTO;
import com.backend.chickenFarm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://192.168.30.*:5173"})
public class MemberController {
    private final MemberService memberService;

    // 로그인 API
    @PostMapping()
    public MemberDTO getMember(@RequestBody MemberDTO memberDTO){
        return memberService.getMember(memberDTO);
    }

    // 비밀번호 변경 API
    @PutMapping("/password")
    public Map<String, Object> updatePassword(@RequestBody MemberDTO memberDTO) {
        return memberService.updatePassword(memberDTO);
    }

    // 이름 변경 API
    @PutMapping("/name")
    public Map<String, Object> updateName(@RequestBody MemberDTO memberDTO) {
        return memberService.updateName(memberDTO);
    }

    // 회원가입 API
    @PostMapping("/signup")
    public Map<String, Object> signup(@RequestBody MemberDTO memberDTO) {
        return memberService.signup(memberDTO);
    }
}