package com.example.visitorsbook;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    /*
      登録テスト
    */
    User user = new User(9999, "test_name","test@labo.local","emacs");
    
    @Test
	private void extracted() {
		assertThat(user.getId(), is(9999));
	}

    @Test
    public void user_name() {
        assertThat(user.getName(), is("test_name"));
    }

    @Test
    public void user_email() {
        assertThat(user.getEmail(), is("test@labo.local"));
    }

    @Test
    public void user_preference() {
        assertThat(user.getPreference(), is("emacs"));
    }

}
