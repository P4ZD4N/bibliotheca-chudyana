package com.p4zd4n.bibliothecachudyana.controllertest;

import com.p4zd4n.bibliothecachudyana.entity.Book;
import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class NewReleasesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Test for displayNewReleases() method")
    public void testDisplayNewReleases() throws Exception {

        List<Book> newBooks = List.of(new Book(), new Book());

        when(bookService.getNewReleases()).thenReturn(newBooks);

        mockMvc.perform(get("/new-releases"))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"))
                .andExpect(model().attribute("newBooks", newBooks));
    }

    @Test
    @DisplayName("Test for displayNewReleases() method access")
    public void testDisplayTopCategoriesPanelAccessWithAdminRole() throws Exception {

        mockMvc.perform(get("/new-releases").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"));
        mockMvc.perform(get("/new-releases").with(user("manager").roles("MANAGER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"));
        mockMvc.perform(get("/new-releases").with(user("employee").roles("EMPLOYEE")))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"));
        mockMvc.perform(get("/new-releases").with(user("user").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"));
        mockMvc.perform(get("/new-releases").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(view().name("/newreleases/new-releases"));
    }

}
