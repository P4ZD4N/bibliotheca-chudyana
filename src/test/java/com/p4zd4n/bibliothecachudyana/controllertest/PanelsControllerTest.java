package com.p4zd4n.bibliothecachudyana.controllertest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class PanelsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test for displayAdminPanel() method access with ADMIN role")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayAdminPanelAccessWithAdminRole() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/admin"));
    }

    @Test
    @DisplayName("Test for displayAdminPanel() method access with MANAGER role")
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    public void testDisplayAdminPanelAccessWithManagerRole() throws Exception {

        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayAdminPanel() method access with EMPLOYEE role")
    @WithMockUser(username = "employee", roles = {"EMPLOYEE"})
    public void testDisplayAdminPanelAccessWithEmployeeRole() throws Exception {

        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayAdminPanel() method access with USER role")
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDisplayAdminPanelAccessWithUserRole() throws Exception {

        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayAdminPanel() method access without roles")
    public void testDisplayAdminPanelAccessWithoutRoles() throws Exception {

        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @DisplayName("Test for displayMangerPanel() method access with ADMIN role")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayManagerPanelAccessWithAdminRole() throws Exception {

        mockMvc.perform(get("/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/manager"));

    }

    @Test
    @DisplayName("Test for displayManagerPanel() method access with MANAGER role")
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    public void testDisplayManagerPanelAccessWithManagerRole() throws Exception {

        mockMvc.perform(get("/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/manager"));
    }

    @Test
    @DisplayName("Test for displayManagerPanel() method access with EMPLOYEE role")
    @WithMockUser(username = "employee", roles = {"EMPLOYEE"})
    public void testDisplayManagerPanelAccessWithEmployeeRole() throws Exception {

        mockMvc.perform(get("/manager")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayManagerPanel() method access with USER role")
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDisplayManagerPanelAccessWithUserRole() throws Exception {

        mockMvc.perform(get("/manager")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayManagerPanel() method access without roles")
    public void testDisplayManagerPanelAccessWithoutRoles() throws Exception {

        mockMvc.perform(get("/manager"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @DisplayName("Test for displayEmployeePanel() method access with ADMIN role")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDisplayEmployeePanelAccessWithAdminRole() throws Exception {

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/employee"));

    }

    @Test
    @DisplayName("Test for displayEmployeePanel() method access with MANAGER role")
    @WithMockUser(username = "manager", roles = {"MANAGER"})
    public void testDisplayEmployeePanelAccessWithManagerRole() throws Exception {

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/employee"));
    }

    @Test
    @DisplayName("Test for displayEmployeePanel() method access with EMPLOYEE role")
    @WithMockUser(username = "employee", roles = {"EMPLOYEE"})
    public void testDisplayEmployeePanelAccessWithEmployeeRole() throws Exception {

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("panels/employee"));
    }

    @Test
    @DisplayName("Test for displayEmployeePanel() method access with USER role")
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDisplayEmployeePanelAccessWithUserRole() throws Exception {

        mockMvc.perform(get("/employee")).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test for displayEmployeePanel() method access without roles")
    public void testDisplayEmployeePanelAccessWithoutRoles() throws Exception {

        mockMvc.perform(get("/employee"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
}
