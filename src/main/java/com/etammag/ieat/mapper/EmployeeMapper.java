package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.Employee;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Employee selectByUsername(String username);

    Employee selectByPhone(String phone);

    Employee selectByIdNumber(String idNumber);

    List<Employee> selectAllByName(String name);

}