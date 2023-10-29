package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.CookieUtil;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Admin;
import com.etammag.ieat.service.sign.AdminSignService;
import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminSignService adminSignService;

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public Result<Admin> login(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        return adminSignService.signIn(username, password);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<String> logout(HttpServletRequest request){
        return adminSignService.signOut(CookieUtil.getValue(request, "token"));
    }

}
