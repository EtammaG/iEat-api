package com.etammag.ieat.service;

import com.etammag.ieat.entity.dto.DishDto;
import com.etammag.ieat.entity.Dish;

import java.util.List;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
public interface DishService {

    boolean catByCategoryId(Long id);

    List<Dish> queryByIds(List<Long> ids);

    boolean catById(Long id);

    void saveAll(List<Dish> dishes);

    List<DishDto> queryAllDtoA(Dish example);

    List<DishDto> queryAllDtoB(Dish example);

    DishDto queryDtoById(Long id);

    void saveDto(DishDto dishDto);

    void updateDtoById(DishDto dishDto);

}
