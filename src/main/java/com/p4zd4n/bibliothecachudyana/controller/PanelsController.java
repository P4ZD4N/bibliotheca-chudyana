package com.p4zd4n.bibliothecachudyana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PanelsController {

    @GetMapping("/admin")
    public String displayAdminPanel() {
        return "/admin/admin";
    }
}
