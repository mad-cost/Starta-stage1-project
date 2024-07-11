package com.sparta.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 해당 클래스(Timestamped)를 상속받는 클래스는 해당 클래스의 필드를 갖게 된다
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing기능을 포함시켜 준다 즉, 자동으로 시간을 추가해준다
// Auditing기능을 사용할 때는 MemoApplication에 @EnableJpaAuditing을 추가해서 spring에 알려줘야 한다
public abstract class Timestamped {

  @CreatedDate // Entity객체가 생성되어서 저장이 될 때, 시간 값이 자동으로 저장된다
  @Column(updatable = false) // 최초 생성 시간값만 저장이되고, 그 이후에는 시간이 업데이트 되지 않도록 막아준다
  @Temporal(TemporalType.TIMESTAMP) // 자바의 Date, Calender와 같은 날짜 데이터를 매핑할 때 사용
  private LocalDateTime createdAt;

  @LastModifiedDate // 조회하는 Entity의 값을 변경할 때, 변경된 시간이 자동으로 기입된다
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime modifiedAt;
}