package com.hello.boot.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
/*
*  @WebMvcTest()
*  여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
*  선언할 경우 @Controller, @ControllerAdvice 등 사용 가능
*  단 @Service, @Component, @Repository 등은 사용 불가
*  여기서는 컨트롤러만 사용하기에 선언
* */
class HelloControllerTest {

    @Autowired
    private MockMvc mvc; 
    /*
    *  MockMvc
    *  웹 API 테스트할 때 사용
    *  스프링 MVC 테스트의 시작점
    *  HTTP GET, POST 등에 대한 API 테스트 가능
    */

    @Test
    void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMVC를 통해 /hello 주소를 HTTP GET요청
                .andExpect(status().isOk()) // mvc.perform의 결과 검증, HTTP Header의 Status를 검증 200, 404, 500등 OK는 200인지 아닌 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증 Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        /*
        *  param
        *  - API 테스트할 때 사용될 요청 파라미터를 설정
        *  단 값은 String만 허용 그래서 숫자/날짜 등의 데이터도 등록시 문자열로 변경해야 사용 가능
        *
        *  jsonPath
        *  - JSON 응답값을 필드별로 검증할 수 있는 메소드
        *  $를 기준으로 필드명을 명시
        * */

    }

}