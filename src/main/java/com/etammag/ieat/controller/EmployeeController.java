package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.CookieUtil;
import com.etammag.ieat.entity.dto.PageExample;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Employee;
import com.etammag.ieat.service.EmployeeService;
import com.etammag.ieat.service.sign.EmployeeSignService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeSignService employeeSignService;

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public Result<Employee> login(@RequestBody Employee employee) {
        return employeeSignService.signIn(employee);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Result<String> logout(HttpServletRequest request) {
        return employeeSignService.signOut(CookieUtil.getValue(request, "token"));
    }

    @PostMapping("/page")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<PageInfo<Employee>> page(@RequestBody PageExample<Employee> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        List<Employee> employees = employeeService.queryAllByName(pageExample.getExample().getName());
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employees);
        return Result.success(employeePageInfo);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Employee> getById(@PathVariable("id") Long id) {
        return Result.success(employeeService.queryById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<String> add(@RequestBody Employee employee) {
        employeeService.add(employee);
        return Result.success("添加成功");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<String> update(@RequestBody Employee employee) {
        employeeService.updateById(employee);
        return Result.success("修改成功");
    }

}
