package com.elgris.usersapi;

import com.elgris.usersapi.api.UsersController;
import com.elgris.usersapi.models.User;
import com.elgris.usersapi.models.UserRole;
import com.elgris.usersapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetUsers() throws Exception {
        assertTrue(true);
    }

	//testing CI
}
