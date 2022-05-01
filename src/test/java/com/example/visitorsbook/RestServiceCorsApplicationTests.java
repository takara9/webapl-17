package com.example.visitorsbook;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(controllers = User.class)
public class RestServiceCorsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // スタティックなWebページを応答
    @Test
    public void accessing_html_top_page() throws Exception {
	mockMvc.perform(get("/index.html"))
	    .andExpect(content().string(containsString("CICD")));
    }

    // スタティックなWebページを応答
    @Test
    public void accessing_html_list_view() throws Exception {
	mockMvc.perform(get("/list.html"))
	    .andExpect(content().string(containsString("E-Mail")));
    }
 
    
    // スタティックなWebページを応答 
    @Test
    public void accessing_html_input_user_info() throws Exception {
	mockMvc.perform(get("/input.html"))
	    .andExpect(content().string(containsString("ユーザー登録")));
    }

    // テストの状態ではDBへの接続が無いため、エラーが発生することが期待値
    // ユニットテストの範囲外として、統合テストの項目として処理する
    @Test
    public void accessing_REST_API_user_list() throws Exception {
	mockMvc.perform(get("/users"))
		.andExpect(status().is4xxClientError());
    }

    // テストの状態ではDBへの接続が無いため、エラーが発生することが期待値
    // ユニットテストの範囲外として、統合テストの項目として処理する
    @Test
    public void accessing_REST_API_user() throws Exception {
	mockMvc.perform(get("/user"))
	    .andExpect(status().is4xxClientError());
    }
  
}
