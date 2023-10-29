package com.etammag.ieat.controller;

import com.etammag.ieat.entity.dto.PageExample;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.entity.Category;
import com.etammag.ieat.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-09-25
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/page")
    @PreAuthorize("hasAuthority('CATEGORY')")
    public Result<PageInfo<Category>> page(@RequestBody PageExample<Category> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        PageInfo<Category> pageInfo = new PageInfo<>(categoryService.queryAll());
        return Result.success(pageInfo);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('CATEGORY')")
    public Result<List<Category>> list(Category example) {
        return Result.success(categoryService.queryAll(example));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CATEGORY')")
    public Result<String> add(@RequestBody Category category) {
        categoryService.add(category);
        return Result.success("添加成功");
    }

    @PutMapping
    @PreAuthorize("hasAuthority('CATEGORY')")
    public Result<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.success("修改成功");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('CATEGORY')")
    public Result<String> delete(Long id) {
        categoryService.removeById(id);
        return Result.success("删除成功");
    }

}
