package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "memo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
// Auditing기능을 사용하기 위해, Timestamped를 상속받아 createdAt, modifiedAt를 사용
public class Memo extends Timestamped{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement: 자동 증가
  private Long id;
  @Column(name = "username", nullable = false)
  private String username;
  @Column(name = "contents", nullable = false, length = 500)
  private String contents;

  public Memo(MemoRequestDto requestDto) {
    this.username = requestDto.getUsername();
    this.contents = requestDto.getContents();
  }

  public void update(MemoRequestDto requestDto) {
    this.username = requestDto.getUsername();
    this.contents = requestDto.getContents();
  }
}