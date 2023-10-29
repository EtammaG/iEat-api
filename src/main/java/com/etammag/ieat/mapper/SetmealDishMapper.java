package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.SetmealDish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 套餐菜品关系 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-10-01
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

    List<Long> selectDishBySetmeal(Long setmealId);

    void insertAll(List<SetmealDish> setmealDishes);

    void deleteBySetmealIds(List<Long> setmealIds);
}
