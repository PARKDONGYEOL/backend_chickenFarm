package com.backend.chickenFarm.member.mapper;

import com.backend.chickenFarm.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public MemberDTO getMember(MemberDTO memberDTO);

    // 비밀번호 검증용 (현재 비밀번호 확인)
    public String getCurrentPassword(String memId);

    // 비밀번호 변경
    public int updatePassword(MemberDTO memberDTO);

    // 이름 변경
    public int updateName(MemberDTO memberDTO);

    // 회원가입
    public int insertMember(MemberDTO memberDTO);
}