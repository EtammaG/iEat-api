package com.etammag.ieat.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用于解析cookie的工具类
 *
 * @author      Eiji
 */
public class CookieUtil {
    /**
     * 根据name在所给的request中所有cookie中取出对应的cookie的value
     *
     * @param request 请求
     * @param name 需要的cookie对应的name
     * @return 返回对应的cookie的value
     */
    public static String getValue(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
            return null;
        for(Cookie cookie : cookies)
            if(cookie.getName().equals(name))
                return cookie.getValue();
        return null;
    }
}
