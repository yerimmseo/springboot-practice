package com.practice.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
/**
 * Junit4 : RunWith(SpringRunner.class)
 * Junit5 : ExtendWith(SpringExtension.class)
 */
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mockMvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청함
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증 HTTP Header의 Status를 검증 (200, 404, 500 ...)
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증. Controller의 return값과 일치하는지 검증
    }

    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mockMvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메서드
                .andExpect(jsonPath("$.amount", is(amount))); // $를 기준으로 필드명을 명시함
    }
}