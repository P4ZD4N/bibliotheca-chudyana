package com.p4zd4n.bibliothecachudyana.controllertest;

import com.p4zd4n.bibliothecachudyana.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TopCategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Test for displayTopCategories() method")
    public void testDisplayTopCategories() throws Exception {

        Map<String, Integer> topCategories = new HashMap<>();
        topCategories.put("Rozwój osobisty", 1);
        topCategories.put("Informatyka", 2);

        when(bookService.getTopCategories()).thenReturn(topCategories);

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"))
                .andExpect(model().attribute("topCategories", topCategories));
    }

    @Test
    @DisplayName("Test for displayTopCategories() method access with ADMIN role")
    public void testDisplayTopCategoriesPanelAccessWithAdminRole() throws Exception {

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"));
    }

    @Test
    @DisplayName("Test for displayTopCategories() method access with MANAGER role")
    public void testDisplayTopCategoriesPanelAccessWithManagerRole() throws Exception {

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"));
    }

    @Test
    @DisplayName("Test for displayTopCategories() method access with EMPLOYEE role")
    public void testDisplayTopCategoriesPanelAccessWithEmployeeRole() throws Exception {

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"));
    }

    @Test
    @DisplayName("Test for displayTopCategories() method access with USER role")
    public void testDisplayTopCategoriesPanelAccessWithUserRole() throws Exception {

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"));
    }

    @Test
    @DisplayName("Test for displayTopCategories() method access without roles")
    public void testDisplayTopCategoriesPanelAccessWithoutRoles() throws Exception {

        mockMvc.perform(get("/top-categories"))
                .andExpect(status().isOk())
                .andExpect(view().name("topcategories/top-categories"));
    }
}
