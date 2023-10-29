package com.etammag.ieat.service.sign;

import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Employee;

public interface EmployeeSignService {
    Result<Employee> signIn(Employee employee);

    Result<String> signOut(String token);

}
