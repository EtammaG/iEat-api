package com.etammag.ieat.service;

import com.etammag.ieat.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void add(Employee employee);

    List<Employee> queryAllByName(String name);

    void updateById(Employee employee);

    Employee queryById(Long id);
}
