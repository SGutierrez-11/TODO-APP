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
    @WithMockUser(username="admin", roles={"USER", "ADMIN"})
    public void testGetUsers() throws Exception {
        User user1 = new User();
        user1.setUsername("user1");
        user1.setFirstname("User");
        user1.setLastname("One");
        user1.setRole(UserRole.ADMIN);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setFirstname("User");
        user2.setLastname("Two");
        user2.setRole(UserRole.USER);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].username", is("user1")))
            .andExpect(jsonPath("$[0].firstname", is("User")))
            .andExpect(jsonPath("$[0].lastname", is("One")))
            .andExpect(jsonPath("$[0].role", is("ADMIN")))
            .andExpect(jsonPath("$[1].username", is("user2")))
            .andExpect(jsonPath("$[1].firstname", is("User")))
            .andExpect(jsonPath("$[1].lastname", is("Two")))
            .andExpect(jsonPath("$[1].role", is("USER")));
    }

    @Test
    @WithMockUser(username="user1", roles={"USER"})
    public void testGetUser() throws Exception {
        User user = new User();
        user.setUsername("user1");
        user.setFirstname("User");
        user.setLastname("One");
        user.setRole(UserRole.ADMIN);

        when(userRepository.findOneByUsername("user1")).thenReturn(user);

        mockMvc.perform(get("/users/user1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username", is("user1")))
            .andExpect(jsonPath("$.firstname", is("User")))
            .andExpect(jsonPath("$.lastname", is("One")))
            .andExpect(jsonPath("$.role", is("ADMIN")));
    }

}
