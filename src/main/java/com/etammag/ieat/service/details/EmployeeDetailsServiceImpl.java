package com.etammag.ieat.service.details;

import com.etammag.ieat.entity.Employee;
import com.etammag.ieat.entity.details.LoginEmployee;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("employeeDetailsService")
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Resource
    private com.etammag.ieat.mapper.EmployeeMapper EmployeeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = EmployeeMapper.selectByUsername(username);
        if(employee == null)
            throw new UsernameNotFoundException("user \"" + username + "\" is not found");
        return new LoginEmployee(employee);
    }
}
