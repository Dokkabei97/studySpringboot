package com.hello.boot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication // 프로젝트 최상단에 위치해야함
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
