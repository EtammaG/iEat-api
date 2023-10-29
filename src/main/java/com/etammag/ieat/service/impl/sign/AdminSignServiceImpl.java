package com.etammag.ieat.service.impl.sign;

import com.alibaba.fastjson.JSON;
import com.etammag.ieat.common.utils.JwtUtil;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Admin;
import com.etammag.ieat.entity.details.LoginAdmin;
import com.etammag.ieat.service.sign.AdminSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminSignServiceImpl implements AdminSignService {

    @Resource(name = "adminAuthManager")
    private AuthenticationManager adminAuthManager;

    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @Value("${ieat.redis.prefix.admin-login-details}")
    private String loginKeyPrefix;

    @Value("${ieat.sys.sign-in-duration}")
    private Duration signinDuration;

    @Override
    public Result<Admin> signIn(String username, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate;
        try {
            authenticate = adminAuthManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            log.trace("A user signin failed", ex);
            return Result.error("账号或密码错误");
        }
        LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        String key = loginKeyPrefix + admin.getId().toString();
        Map<String, Object> headerParams = new HashMap<>(1);
        headerParams.put("className", LoginAdmin.class.getName());
        String jwt = JwtUtil.createJWT(key, headerParams);
        stringRedisTemplate.opsForValue().set(
                key,
                JSON.toJSONString(loginAdmin),
                signinDuration);
        return Result.success(admin).add("token", jwt);
    }

    @Override
    public Result<String> signOut(String token) {
        String redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        stringRedisTemplate.delete(redisKey);
        return Result.success("注销成功");
    }

}
