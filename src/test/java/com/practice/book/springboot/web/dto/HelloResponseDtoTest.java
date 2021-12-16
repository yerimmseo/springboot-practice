package com.practice.book.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat; // junit이 아닌 assertj의 assertThat사용

public class HelloResponseDtoTest {

    @Test
    public void lombok_test() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name); // assertThat : 검증하고 싶은 대상을 메소드 인자로 받음
        assertThat(dto.getAmount()).isEqualTo(amount); // isEqualTo : assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공

        /**
         * junit과 비교하여 assertj의 장점
         * - CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않음
         * - 자동완성이 좀 더 확실하게 지원
         */
    }
}