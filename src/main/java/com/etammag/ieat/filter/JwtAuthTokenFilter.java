package com.etammag.ieat.filter;

import com.alibaba.fastjson.JSON;
import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.common.utils.CookieUtil;
import com.etammag.ieat.common.utils.JwtUtil;
import com.etammag.ieat.entity.details.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${ieat.sys.sign-in-duration}")
    private Duration signinDuration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = CookieUtil.getValue(request, "token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails loginUser;
        try {
            Jws<Claims> claimsJws = JwtUtil.parseJWT(token);
            String redisKey = claimsJws.getBody().getSubject();
            String className = (String) claimsJws.getHeader().get("className");
            Class aClass = Class.forName(className);
            loginUser = (UserDetails) JSON.parseObject(stringRedisTemplate.opsForValue().get(redisKey), aClass);
            if (loginUser == null)
                throw new RuntimeException();
            stringRedisTemplate.expire(redisKey, signinDuration);
        } catch (Exception e) {
            log.warn("illegal token");
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        BaseContext.setCurrentLogin((Login) loginUser);
        filterChain.doFilter(request, response);
    }
}
