package com.example.restservicecors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@WebMvcTest(controllers = GreetingController.class)
//@SpringBootTest
//@WebMvcTest(controllers = UserController.class)
public class RestServiceCorsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessing_html_top_page() throws Exception {
	mockMvc.perform(get("/index.html"))
	    .andExpect(content().string(containsString("CICD")));
    }

    @Test
    public void accessing_html_list_view() throws Exception {
	mockMvc.perform(get("/list.html"))
	    .andExpect(content().string(containsString("LIST")));
    }
 
    @Test
    public void accessing_html_input_user_info() throws Exception {
	mockMvc.perform(get("/input.html"))
	    .andExpect(content().string(containsString("FORM")));
    }

    @Test
    public void accessing_REST_API_user_list() throws Exception {
	mockMvc.perform(get("/users"))
		.andExpect(status().is4xxClientError());
    }

    @Test
    public void accessing_REST_API_user() throws Exception {
	mockMvc.perform(get("/user"))
	    .andExpect(content().string(containsString("{")));
    }
   
}
