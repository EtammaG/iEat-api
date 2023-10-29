package com.etammag.ieat.service;

import java.util.List;

/**
 * <p>
 * 套餐菜品关系 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-01
 */
public interface SetmealDishService {
    List<Long> queryDishBySetmeal(Long setmealId);

    void saveAllDishes(Long setmealId, List<Long> dishIds);

    void deleteBySetmealIds(List<Long> setmealIds);
}
