package com.etammag.ieat.service.sign;

import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.User;

public interface UserSignService {
    void sendMsg(User user);

    Result<User> signInByPhoneAndCode(String phone, String code);

    Result<String> signOut(String token);
}
