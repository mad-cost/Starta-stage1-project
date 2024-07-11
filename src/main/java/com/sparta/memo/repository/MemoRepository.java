package com.sparta.memo.repository;


import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

  // ModifiedAt을 모두 가져와서 내림차순으로 정렬
  List<Memo> findAllByOrderByModifiedAtDesc();

}
