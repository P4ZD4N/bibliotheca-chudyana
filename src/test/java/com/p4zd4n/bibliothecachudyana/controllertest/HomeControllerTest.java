package com.p4zd4n.bibliothecachudyana.controllertest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test for displayHomePage() method")
    public void testDisplayHomePage() throws Exception {

        mockMvc.perform(get("/").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));

        mockMvc.perform(get("/").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
