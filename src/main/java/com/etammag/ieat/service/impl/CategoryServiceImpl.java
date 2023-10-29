package com.etammag.ieat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.Category;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.CategoryMapper;
import com.etammag.ieat.service.CategoryService;
import com.etammag.ieat.service.DishService;
import com.etammag.ieat.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-09-25
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;

    @Override
    public List<Category> queryAll() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .orderByAsc(Category::getSort)
                .orderByDesc(Category::getUpdateTime);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<Category> queryAll(Category example) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(example.getId() != null, Category::getId, example.getId())
                .eq(example.getType() != null, Category::getType, example.getType())
                .like(StringUtils.hasText(example.getName()), Category::getName, example.getName())
                .eq(example.getSort() != null, Category::getSort, example.getSort())
        ;
        queryWrapper
                .orderByAsc(Category::getSort)
                .orderByDesc(Category::getUpdateTime);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public String queryNameById(Long id) {
        return categoryMapper.selectNameById(id);
    }

    @Override
    public boolean catById(Long id) {
        return categoryMapper.catById(id) != null;
    }

    @Override
    @Transactional
    public void add(Category category) {
        category.setId(IdWorker.nextId());
        categoryMapper.insert(category);
    }

    @Override
    @Transactional()
    public void updateById(Category category) {
        categoryMapper.updateById(category);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        if (dishService.catByCategoryId(id)) {
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        if (setmealService.catByCategoryId(id)) {
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        categoryMapper.deleteById(id);
    }

}
