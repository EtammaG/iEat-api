package com.etammag.ieat.common.utils;

import com.etammag.ieat.entity.details.Login;

public class BaseContext {
    private static final ThreadLocal<Login> threadLocal = new ThreadLocal<>();

    public static void setCurrentLogin(Login login){
        threadLocal.set(login);
    }

    public static Login getCurrentLogin(){
        return threadLocal.get();
    }
}