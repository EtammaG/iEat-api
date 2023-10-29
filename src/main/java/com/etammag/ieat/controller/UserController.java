package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.CookieUtil;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.User;
import com.etammag.ieat.service.UserService;
import com.etammag.ieat.service.sign.UserSignService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-10-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserSignService userSignService;

    @Resource
    private UserService userService;

    @PostMapping("/sendMsg")
    @PreAuthorize("isAnonymous()")
    public Result<String> sendMsg(@RequestBody User user) {
        userSignService.sendMsg(user);
        return Result.success("发送成功");
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public Result<User> login(@RequestBody Map<String, String> params) {
        return userSignService.signInByPhoneAndCode(
                params.get("phone"),
                params.get("code"));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('USER')")
    public Result<String> logout(HttpServletRequest request) {
        return userSignService.signOut(CookieUtil.getValue(request, "token"));
    }

    @PostMapping("/signup")
    @PreAuthorize("isAnonymous()")
    public Result<User> signup(@RequestBody User user) {
        return userService.signUp(user);
    }

}
