package com.etammag.ieat.controller;

import com.etammag.ieat.entity.dto.DishDto;
import com.etammag.ieat.entity.dto.PageExample;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Dish;
import com.etammag.ieat.service.DishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    @PostMapping("/page")
    @PreAuthorize("hasAuthority('DISH')")
    public Result<PageInfo<DishDto>> page(@RequestBody PageExample<DishDto> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        List<DishDto> dishDto = dishService.queryAllDtoA(pageExample.getExample());
        return Result.success(PageInfo.of(dishDto));
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('DISH')")
    public Result<List<DishDto>> list(Dish example) {
        return Result.success(dishService.queryAllDtoB(example));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DISH')")
    public Result<DishDto> get(@PathVariable Long id) {
        DishDto dishDto = dishService.queryDtoById(id);
        return Result.success(dishDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('DISH')")
    public Result<String> save(@RequestBody DishDto dishDto) {
        dishService.saveDto(dishDto);
        return Result.success("新增菜品成功");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('DISH')")
    public Result<String> update(@RequestBody DishDto dishDto) {
        dishService.updateDtoById(dishDto);
        return Result.success("修改菜品成功");
    }

}
