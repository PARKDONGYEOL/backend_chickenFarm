package com.backend.chickenFarm.note.controller;

import com.backend.chickenFarm.note.dto.NoteDTO;
import com.backend.chickenFarm.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/note")
@CrossOrigin(origins = "http://localhost:5173") // CORS 허용 (개발용)
public class NoteController {

    private final NoteService noteService;

    // 전체 일지 조회
    @GetMapping("/all/{memId}")
    public List<NoteDTO> getAllNotes(@PathVariable String memId) {
        return noteService.getAllNotes(memId);
    }

    // 특정 날짜의 일지 조회
    @GetMapping("/date")
    public List<NoteDTO> getNotesByDate(@RequestParam String memId, @RequestParam String date) {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setMemId(memId);
        // date를 LocalDateTime으로 변환하여 설정
        noteDTO.setRecTime(java.time.LocalDateTime.parse(date + "T00:00:00"));
        return noteService.getNotesByDate(noteDTO);
    }

    // 특정 일지 조회
    @GetMapping("/{noteNum}")
    public NoteDTO getNoteById(@PathVariable Integer noteNum) {
        return noteService.getNoteById(noteNum);
    }

    // 일지 추가
    @PostMapping
    public Map<String, Object> insertNote(@RequestBody NoteDTO noteDTO) {
        return noteService.insertNote(noteDTO);
    }

    // 일지 수정
    @PutMapping
    public Map<String, Object> updateNote(@RequestBody NoteDTO noteDTO) {
        return noteService.updateNote(noteDTO);
    }

    // 일지 삭제
    @DeleteMapping("/{noteNum}")
    public Map<String, Object> deleteNote(@PathVariable Integer noteNum) {
        return noteService.deleteNote(noteNum);
    }
}
