package com.etammag.ieat.service;

import com.etammag.ieat.entity.User;
import com.etammag.ieat.entity.dto.Result;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-02
 */
public interface UserService {

    Result<User> signUp(User user);
}
