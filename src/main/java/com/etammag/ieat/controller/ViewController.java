package com.etammag.ieat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/admin/login")
    public String adminLogin() {
        return "redirect:/super/login.html";
    }

    @GetMapping("/admin/home")
    public String adminLHome() {
        return "redirect:/super/index.html";
    }

    @GetMapping("/employee/login")
    public String backendLogin() {
        return "redirect:/backend/page/login/login.html";
    }

    @GetMapping("/employee/home")
    public String backendHome() {
        return "redirect:/backend/index.html";
    }

    @GetMapping("/user/login")
    public String frontLogin() {
        return "redirect:/front/page/login.html";
    }

    @GetMapping("/user/home")
    public String frontHome() {
        return "redirect:/front/index.html";
    }
}
