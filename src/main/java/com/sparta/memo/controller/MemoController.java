package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // @ResponseBody + @Controller
// Html을 반환하는 것이 아닌, Json데이터를 반환할 것이다
@RequestMapping("/api")
public class MemoController {
  private final Map<Long, Memo> memoList = new HashMap<>(); // DB를 아직 배우지 않아, 임시 DB로 사용

  @PostMapping("/memos")
  public MemoResponseDto createMemo(
          @RequestBody
          MemoRequestDto requestDto
  ) {
    // RequestDto -> Entity
    Memo memo = new Memo(requestDto);

    // Memo Max ID Check
    // memoList.keySet(): Map<Long, Memo>의 Long값을 모두 가져온다
    Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
    memo.setId(maxId);

    // DB에 저장
    memoList.put(memo.getId(), memo);

    // Entity -> ResponseDto
    MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

    return memoResponseDto;
  }

  // 모든 메모 보여주기
  @GetMapping("memos")
  public List<MemoResponseDto> readMemo() {
    // Map To List
    // values(): Map<Long, Memo>의 모든 Memo객체를 가져온다
    /* stream(): memoList.values()에서 나온 Memo객체를 for문 처럼 돌려준다.
                 하나씩 나온 Memo객체를 MemoResponseDto객체로 변환해준다 */
    List<MemoResponseDto> responseList = memoList.values().stream()
            .map(MemoResponseDto::new).toList();

    return responseList;
  }




}
