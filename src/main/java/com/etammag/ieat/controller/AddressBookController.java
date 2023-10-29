package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.entity.AddressBook;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.service.AddressBookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 地址管理 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Resource
    private AddressBookService addressBookService;

    @GetMapping("/{id}")
    public Result<AddressBook> get(@PathVariable Long id) {
        return Result.success(addressBookService.queryById(id));
    }

    @GetMapping("default")
    public Result<AddressBook> getDefault() {
        return Result.success(addressBookService.queryDefault(BaseContext.getCurrentLogin().getId()));
    }

    @GetMapping("/list")
    public Result<List<AddressBook>> list(AddressBook addressBook) {
        return Result.success(addressBookService.queryAllA(addressBook));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADDRESSBOOK')")
    public Result<String> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentLogin().getId());
        addressBookService.save(addressBook);
        return Result.success("添加成功");
    }

    @PutMapping("default")
    public Result<String> setDefault(@RequestParam Long id) {
        addressBookService.setDefault(id);
        return Result.success("成功改变默认地址");
    }

}
