package com.backend.chickenFarm.member.controller;

import com.backend.chickenFarm.member.dto.MemberDTO;
import com.backend.chickenFarm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping()
    public MemberDTO getMember(MemberDTO memberDTO){
        return memberService.getMember(memberDTO);
    }
}
