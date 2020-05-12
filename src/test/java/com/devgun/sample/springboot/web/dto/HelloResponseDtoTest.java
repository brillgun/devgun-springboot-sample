package com.devgun.sample.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void Lombok_text() {

        //given
        String user_name = "test";
        int num = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(user_name, num);

        //then
        assertThat(dto.getName()).isEqualTo(user_name); //1,2
        assertThat(dto.getAmount()).isEqualTo(num);
    }

}
