package com.etammag.ieat.service.details;

import com.etammag.ieat.entity.Admin;
import com.etammag.ieat.entity.details.LoginAdmin;
import com.etammag.ieat.mapper.AdminMapper;
import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("adminDetailsService")
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectByUsername(username);
        if(admin == null)
            return null;
        return new LoginAdmin(admin);
    }
}
