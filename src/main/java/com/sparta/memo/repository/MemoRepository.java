package com.sparta.memo.repository;


import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

  // ModifiedAt을 모두 가져와서 내림차순으로 정렬
  List<Memo> findAllByOrderByModifiedAtDesc();

  // 2주차 숙제
  // 특정 키워드로 찾기: Contents필드에 특정 단어(keyword)가 포함된(Contains) 값을 ModifiedAt역순으로 전분 가져오기
  List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);

}
