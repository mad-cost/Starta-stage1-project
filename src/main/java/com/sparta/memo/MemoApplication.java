package com.sparta.memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Auditing기능을 사용
@SpringBootApplication
public class MemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MemoApplication.class, args);
  }

}
