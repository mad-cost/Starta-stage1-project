package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.service.MemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoController {
  private final MemoService memoService;

  public MemoController(MemoService memoService) {
    this.memoService = memoService;
  }

  @PostMapping("/memos")
  public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
    return memoService.createMemo(requestDto);
  }

  // '/'페이지
  @GetMapping("/memos")
  public List<MemoResponseDto> getMemos() {
    return memoService.getMemos();
  }

  @PutMapping("/memos/{id}")
  public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
    // 받아온 id, 수정할 데이터
    return memoService.updateMemo(id, requestDto);
  }

  @DeleteMapping("/memos/{id}")
  public Long deleteMemo(@PathVariable Long id) {
    return memoService.deleteMemo(id);
  }

  // 2주차 숙제
  // Postman
  @GetMapping("/memos/contents")
  // contents필드에 값으로 호랑이가 있을경우 전부 찾아오기
  // http://localhost:8080/api/memos/contents?keyword=호랑이
  public List<MemoResponseDto> getMemosByKeyword(
          @RequestParam // 어노테이션 생략 가능
          String keyword
  ){
    return memoService.getMemosByKeyword(keyword);
  }

}