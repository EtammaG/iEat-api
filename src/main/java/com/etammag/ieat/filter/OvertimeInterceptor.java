package com.etammag.ieat.filter;

import com.etammag.ieat.common.utils.IpUtil;
import com.etammag.ieat.exception.bean.OverTimeException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

public class OvertimeInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    private int statedCount;

    private Duration statedTime;

    public OvertimeInterceptor(StringRedisTemplate stringRedisTemplate, int statedCount, Duration statedTime) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.statedCount = statedCount;
        this.statedTime = statedTime;
    }

    /**
     * 根据request的ip地址，使用redis判断该ip是否在
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String key = request.getServletPath().substring(1).replace('/', ':')
                + ":times:"
                + IpUtil.getIpAddr(request);
        String times = stringRedisTemplate.opsForValue().get(key);
        int t = 0;
        if (times == null || (t = Integer.parseInt(times)) < statedCount) {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(t + 1), statedTime);
            return true;
        }
        throw new OverTimeException();
    }

}
