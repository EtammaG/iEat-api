package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.User;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.mapper.UserMapper;
import com.etammag.ieat.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-02
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Result<User> signUp(User user) {
        user.setId(IdWorker.nextId());
        user.setStatus(1);
        userMapper.insert(user);
        return Result.success(user);
    }
}
