package com.sparta.memo;

import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransactionTest {

  @PersistenceContext // // EntityManager 주입 받을 때 사용
  EntityManager em;

  @Autowired // MemoRepository를  @Autowired를 사용하여 주입 받는다
  MemoRepository memoRepository;

  @Test
  @Transactional // springframework.transaction
  @Rollback(value = false) // 테스트 코드에서 @Transactional 를 사용하면 테스트가 완료된 후 롤백하기 때문에 false 옵션 추가
  @DisplayName("메모 생성 성공")
  void test1() {
    Memo memo = new Memo();
    memo.setUsername("Robbert");
    memo.setContents("@Transactional 테스트 중!");

    em.persist(memo);  // 영속성 컨텍스트에 데이터 저장 / insert문 사용
  }

  @Test
  // @Transactional을 적용하지 않을 경우 실패
  @Disabled
  @DisplayName("메모 생성 실패")
  void test2() {
    Memo memo = new Memo();
    memo.setUsername("Robbie");
    memo.setContents("@Transactional 테스트 중!");

    // insert, update, delete -> 데이터를 수정할때는 Transaction이 필요하다.
    em.persist(memo);  // 영속성 컨텍스트에 데이터 저장 / insert문 사용
  }

  @Test
  @Disabled // 사용하지 않음
  @Transactional
  @Rollback(value = false)
  @DisplayName("트랜잭션 전파 테스트")
  void test3() {
//    memoRepository.createMemo(em);
    System.out.println("테스트 test3 메서드 종료");
  }
}