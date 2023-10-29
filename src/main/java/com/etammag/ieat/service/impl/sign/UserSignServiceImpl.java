package com.etammag.ieat.service.impl.sign;

import com.alibaba.fastjson.JSON;
import com.etammag.ieat.common.utils.JwtUtil;
import com.etammag.ieat.common.utils.RandomUtil;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.User;
import com.etammag.ieat.entity.details.LoginUser;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.service.sign.UserSignService;
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
public class UserSignServiceImpl implements UserSignService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager userVerCodeAuthManager;

    @Value("${ieat.redis.prefix.user-login-code}")
    private String codeKeyPrefix;

    @Value("${ieat.sys.ver-code-duration}")
    private Duration verCodeDuration;

    @Value("${ieat.redis.prefix.user-login-details}")
    private String loginKeyPrefix;

    @Value("${ieat.sys.sign-in-duration}")
    private Duration signinDuration;

    @Override
    public void sendMsg(User user) {
        String code = RandomUtil.nextNum(4);
        // SMSUtils.sendMsg("iEat", phone, code)
        stringRedisTemplate.opsForValue().set(codeKeyPrefix + user.getPhone(), code, verCodeDuration);
    }

    @Override
    public Result<User> signInByPhoneAndCode(String phone, String code) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                phone, code
        );
        Authentication authenticate;
        try {
            authenticate = userVerCodeAuthManager.authenticate(token);
        } catch (BadCredentialsException ex) {
            log.trace("A user signin failed", ex);
            throw new CustomException("手机或验证码有误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String key = loginKeyPrefix + user.getId().toString();
        Map<String, Object> headerParams = new HashMap<>(1);
        headerParams.put("className", LoginUser.class.getName());
        String jwt = JwtUtil.createJWT(key, headerParams);
        stringRedisTemplate.opsForValue().set(
                key,
                JSON.toJSONString(loginUser),
                signinDuration
        );
        return Result.success(user).add("token", jwt);
    }

    @Override
    public Result<String> signOut(String token) {
        String redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        stringRedisTemplate.delete(redisKey);
        return Result.success("注销成功");
    }
}
