package com.etammag.ieat.service.details;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.entity.User;
import com.etammag.ieat.entity.details.LoginUser;
import com.etammag.ieat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userVerCodeDetailsService")
public class UserVerCodeDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${ieat.redis.prefix.user-login-code}")
    private String codeKeyPrefix;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(User::getPhone, phone);
        return new LoginUser(
                userMapper.selectOne(queryWrapper),
                stringRedisTemplate.opsForValue().get(codeKeyPrefix + phone));
    }
}
