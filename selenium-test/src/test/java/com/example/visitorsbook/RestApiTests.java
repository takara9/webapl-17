package com.example.visitorsbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

public class RestApiTests {

	@Test
    public void test() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://webapl-17.test.k8s4.labo.local/users");
        HttpResponse response = client.execute(request);
        
        //assertThat(response, is(notNullValue()));
        //assertThat(response, not(nullValue()));
        assertThat("hoge", is("hoge"));
        /*
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = '';
        while ((line = rd.readLine()) != null) {
           System.out.println(line);
        }
        */
	}
}
