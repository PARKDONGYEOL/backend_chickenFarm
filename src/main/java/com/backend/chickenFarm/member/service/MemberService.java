package com.backend.chickenFarm.member.service;

import com.backend.chickenFarm.member.dto.MemberDTO;
import com.backend.chickenFarm.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberDTO getMember(MemberDTO memberDTO){
        return memberMapper.getMember(memberDTO);
    }

    // 비밀번호 변경
    public Map<String, Object> updatePassword(MemberDTO memberDTO) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 1. 현재 비밀번호 확인
            String currentPasswordInDB = memberMapper.getCurrentPassword(memberDTO.getMemId());

            if (currentPasswordInDB == null) {
                result.put("success", false);
                result.put("message", "사용자를 찾을 수 없습니다.");
                return result;
            }

            // 2. 현재 비밀번호가 일치하는지 확인
            if (!currentPasswordInDB.equals(memberDTO.getCurrentPassword())) {
                result.put("success", false);
                result.put("message", "현재 비밀번호가 일치하지 않습니다.");
                return result;
            }

            // 3. 비밀번호 업데이트
            int updateResult = memberMapper.updatePassword(memberDTO);

            if (updateResult > 0) {
                result.put("success", true);
                result.put("message", "비밀번호가 변경되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "비밀번호 변경에 실패했습니다.");
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }

    // 이름 변경
    public Map<String, Object> updateName(MemberDTO memberDTO) {
        Map<String, Object> result = new HashMap<>();

        try {
            int updateResult = memberMapper.updateName(memberDTO);

            if (updateResult > 0) {
                result.put("success", true);
                result.put("message", "이름이 변경되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "이름 변경에 실패했습니다.");
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }
}