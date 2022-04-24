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


    //@Test
    //	public void contextLoads() {
    //	}

    /*
    @Test
    public void homePage() throws Exception {
	// N.B. jsoup can be useful for asserting HTML content
	mockMvc.perform(get("/index.html"))
	    .andExpect(content().string(containsString("Get your greeting")));
    }
    */
    
    @Test
    public void greeting() throws Exception {
	mockMvc.perform(get("/greeting"))
	    .andExpect(content().string(containsString("Hello, World!")));
    }

    @Test
    public void greetingWithUser() throws Exception {
	mockMvc.perform(get("/greeting").param("name", "KISHIDA"))
	    .andExpect(content().string(containsString("Hello, KISHIDA!")));
    }
    
}
