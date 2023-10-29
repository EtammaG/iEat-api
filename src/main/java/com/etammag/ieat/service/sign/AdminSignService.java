package com.etammag.ieat.service.sign;

import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Admin;

public interface AdminSignService {
    Result<Admin> signIn(String username, String password);

    Result<String> signOut(String token);
}
