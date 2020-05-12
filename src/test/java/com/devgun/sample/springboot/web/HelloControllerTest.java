package com.devgun.sample.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) //1.
@WebMvcTest(controllers = HelloController.class) //2.
public class HelloControllerTest {

    @Autowired //3.
    private MockMvc mvc; //4.

    @Test
    public void hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //5.
            .andExpect(status().isOk()) //6.
            .andExpect(content().string(hello));
    }

    @Test
    public void helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) //1.
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //2.
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
