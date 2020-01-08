package net.example;


import net.example.entity.Users;
import net.example.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService service;

    @Test
    public void getUserByEmail() throws Exception {
        Users user = new Users();
        user.setEmail("hai@gmail.com");
        user.setUsername("hai");
        user.setAddress("ha noi");

        given(service.findUserByEmail("hai@gmail.com")).willReturn(user);
        mvc.perform(get("/users/email?key=hai@gmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is(user.getAddress())));
        //$[index].fullname nếu $ là array
    }

}
