package com.etammag.ieat.service;

import com.etammag.ieat.entity.Category;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-09-25
 */
public interface CategoryService {

    List<Category> queryAll();

    List<Category> queryAll(Category example);

    String queryNameById(Long id);

    boolean catById(Long id);

    void add(Category category);

    void updateById(Category category);

    void removeById(Long id);

}
