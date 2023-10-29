package com.etammag.ieat.controller;

import com.etammag.ieat.entity.dto.PageExample;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.dto.SetmealDto;
import com.etammag.ieat.service.SetmealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Resource
    private SetmealService setmealService;

    @PostMapping("/page")
    @PreAuthorize("hasAuthority('SETMEAL')")
    public Result<PageInfo<SetmealDto>> page(@RequestBody PageExample<SetmealDto> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        List<SetmealDto> setmealDtos = setmealService.queryAllDtoA(pageExample.getExample());
        return Result.success(new PageInfo<>(setmealDtos));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SETMEAL')")
    public Result<List<SetmealDto>> list(SetmealDto example) {
        List<SetmealDto> setmealDtos = setmealService.queryAllDtoB(example);
        return Result.success(setmealDtos);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SETMEAL')")
    public Result<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveDto(setmealDto);
        return Result.success("新增成功");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('SETMEAL')")
    public Result<String> deleteAll(List<Long> ids) {
        setmealService.deleteDtoByIds(ids);
        return Result.success("删除成功");
    }

}
