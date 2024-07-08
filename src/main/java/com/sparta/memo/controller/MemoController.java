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

  // 업데이트 API
  @PutMapping("/memos/{id}")
  public Long updateMemo(
          @PathVariable("id")
          Long id,
          @RequestBody
          MemoRequestDto requestDto
  ) {
    // 수정하려는 해당 메모가 DB에 존재하는지 확인
    // containsKey: Map<Long, Memo>의 Long에 해당하는 값이 있는지 확인한다
    if (memoList.containsKey(id)) {
      // 해당 메모 가져오기
      // memoList.get(id): Map<Long, Memo>에서 id(Long)에 해당하는 Memo객체를 가져온다
      Memo memo = memoList.get(id);

      // memo 수정
      memo.update(requestDto);
      return memo.getId();

    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다");
    }
  }

  // 삭제하기 API
  @DeleteMapping("/memos/{id}")
  public Long deleteMemo(
          @PathVariable("id")
          Long id
  ) {
    // 해당 메모가 존재하는지 확인
    if (memoList.containsKey(id)) {
      // 해당 메모를 삭제
      // id에 해당하는 key와 value값 모두 삭제
      memoList.remove(id);
      return id;
    } else {
      throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다");
    }
  }


}
