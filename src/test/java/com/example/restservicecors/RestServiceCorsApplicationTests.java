package com.example.restservicecors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = GreetingController.class)
//@SpringBootTest
public class RestServiceCorsApplicationTests {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void toppage() throws Exception {
	mockMvc.perform(get("/index.html"))
	    .andExpect(content().string(containsString("CICD")));
    }
    
}
