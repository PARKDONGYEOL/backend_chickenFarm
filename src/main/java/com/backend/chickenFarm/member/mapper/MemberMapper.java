package com.backend.chickenFarm.member.mapper;

import com.backend.chickenFarm.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    public MemberDTO getMember(MemberDTO memberDTO);
}
