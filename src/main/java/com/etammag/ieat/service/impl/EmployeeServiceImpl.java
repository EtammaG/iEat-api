package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.Employee;
import com.etammag.ieat.entity.details.Login;
import com.etammag.ieat.exception.DuplicateKeyExceptionParser;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.EmployeeMapper;
import com.etammag.ieat.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee queryById(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> queryAllByName(String name) {
        return employeeMapper.selectAllByName(name);
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        //默认密码身份证号码后六位
        if (!StringUtils.hasText(employee.getPassword()))
            employee.setPassword(
                    passwordEncoder.encode(
                            employee.getIdNumber().substring(employee.getIdNumber().length() - 6)));

        employee.setId(IdWorker.nextId());
        Login current = BaseContext.getCurrentLogin();
        employee.setCreateUser(current.getId());
        employee.setUpdateUser(current.getId());
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setStatus(1);

        try {
            employeeMapper.insert(employee);
        } catch (DuplicateKeyException e) {
            throw new CustomException(DuplicateKeyExceptionParser.parse(e));
        }
    }

    @Override
    @Transactional
    public void updateById(Employee employee) {
        Login current = BaseContext.getCurrentLogin();
        employee.setUpdateUser(current.getId());
        employee.setUpdateTime(LocalDateTime.now());
        try {
            employeeMapper.updateByPrimaryKey(employee);
        } catch (DuplicateKeyException e) {
            throw new CustomException(DuplicateKeyExceptionParser.parse(e));
        }
    }
}
