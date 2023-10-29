package com.etammag.ieat.service.impl.sign;

import com.alibaba.fastjson.JSON;
import com.etammag.ieat.common.utils.JwtUtil;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Employee;
import com.etammag.ieat.entity.details.LoginEmployee;
import com.etammag.ieat.service.sign.EmployeeSignService;
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
public class EmployeeSignServiceImpl implements EmployeeSignService {

    @Resource(name = "employeeAuthManager")
    private AuthenticationManager employeeAuthManager;

    @Resource
    public StringRedisTemplate stringRedisTemplate;

    @Value("${ieat.redis.prefix.employee-login-details}")
    private String loginKeyPrefix;

    @Value("${ieat.sys.sign-in-duration}")
    private Duration signinDuration;

    /**
     * 使用employeeAuthManager进行认证，认证成功后，将employee信息存入redis中，返回token
     */
    @Override
    public Result<Employee> signIn(Employee unkownEmployee) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                unkownEmployee.getUsername(),
                unkownEmployee.getPassword());
        Authentication authenticate;
        try {
            authenticate = employeeAuthManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            log.trace("A user signin failed", ex);
            return Result.error("账号或密码错误");
        }
        LoginEmployee loginEmployee = (LoginEmployee) authenticate.getPrincipal();
        Employee employee = loginEmployee.getEmployee();
        String key = loginKeyPrefix + employee.getId().toString();
        Map<String, Object> headerParams = new HashMap<>(1);
        headerParams.put("className", LoginEmployee.class.getName());
        String jwt = JwtUtil.createJWT(key, headerParams);
        stringRedisTemplate.opsForValue().set(
                key,
                JSON.toJSONString(loginEmployee),
                signinDuration);
        return Result.success(employee).add("token", jwt);
    }

    @Override
    public Result<String> signOut(String token) {
        String redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        stringRedisTemplate.delete(redisKey);
        return Result.success("注销成功");
    }

}
