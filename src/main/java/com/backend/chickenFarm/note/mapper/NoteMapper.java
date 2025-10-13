package com.backend.chickenFarm.note.mapper;

import com.backend.chickenFarm.note.dto.NoteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteMapper {

    // 전체 일지 조회
    public List<NoteDTO> getAllNotes(String memId);

    // 특정 날짜의 일지 조회
    public List<NoteDTO> getNotesByDate(NoteDTO noteDTO);

    // 일지 추가
    public int insertNote(NoteDTO noteDTO);

    // 일지 수정
    public int updateNote(NoteDTO noteDTO);

    // 일지 삭제
    public int deleteNote(Integer noteNum);

    // 특정 일지 조회
    public NoteDTO getNoteById(Integer noteNum);
}
