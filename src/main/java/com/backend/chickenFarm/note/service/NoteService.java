package com.backend.chickenFarm.note.service;

import com.backend.chickenFarm.note.dto.NoteDTO;
import com.backend.chickenFarm.note.mapper.NoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteMapper noteMapper;

    // 전체 일지 조회
    public List<NoteDTO> getAllNotes(String memId) {
        return noteMapper.getAllNotes(memId);
    }

    // 특정 날짜의 일지 조회
    public List<NoteDTO> getNotesByDate(NoteDTO noteDTO) {
        return noteMapper.getNotesByDate(noteDTO);
    }

    // 일지 추가
    public Map<String, Object> insertNote(NoteDTO noteDTO) {
        Map<String, Object> result = new HashMap<>();

        try {
            int insertResult = noteMapper.insertNote(noteDTO);

            if (insertResult > 0) {
                result.put("success", true);
                result.put("message", "일지가 추가되었습니다.");
                result.put("noteNum", noteDTO.getNoteNum());
            } else {
                result.put("success", false);
                result.put("message", "일지 추가에 실패했습니다.");
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }

    // 일지 수정
    public Map<String, Object> updateNote(NoteDTO noteDTO) {
        Map<String, Object> result = new HashMap<>();

        try {
            int updateResult = noteMapper.updateNote(noteDTO);

            if (updateResult > 0) {
                result.put("success", true);
                result.put("message", "일지가 수정되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "일지 수정에 실패했습니다.");
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }

    // 일지 삭제
    public Map<String, Object> deleteNote(Integer noteNum) {
        Map<String, Object> result = new HashMap<>();

        try {
            int deleteResult = noteMapper.deleteNote(noteNum);

            if (deleteResult > 0) {
                result.put("success", true);
                result.put("message", "일지가 삭제되었습니다.");
            } else {
                result.put("success", false);
                result.put("message", "일지 삭제에 실패했습니다.");
            }

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }

    // 특정 일지 조회
    public NoteDTO getNoteById(Integer noteNum) {
        return noteMapper.getNoteById(noteNum);
    }
}
