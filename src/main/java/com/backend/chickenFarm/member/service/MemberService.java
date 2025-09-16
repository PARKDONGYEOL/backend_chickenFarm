package com.backend.chickenFarm.member.service;

import com.backend.chickenFarm.member.dto.MemberDTO;
import com.backend.chickenFarm.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    public MemberDTO getMember(MemberDTO memberDTO){
        return memberMapper.getMember(memberDTO);
    }
}
