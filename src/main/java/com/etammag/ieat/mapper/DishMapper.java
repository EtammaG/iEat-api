package com.etammag.ieat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.ieat.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    Integer catByCategoryId(Long id);

    Integer catById(Long id);

    void insertAll(List<Dish> dishes);
}
