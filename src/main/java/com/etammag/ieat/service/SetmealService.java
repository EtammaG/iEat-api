package com.etammag.ieat.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.etammag.ieat.entity.dto.SetmealDto;
import com.etammag.ieat.entity.Setmeal;

import java.util.List;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
public interface SetmealService {

    boolean catByCategoryId(Long id);

    List<Setmeal> queryAll(Wrapper<Setmeal> queryWrapper);

    void save(Setmeal setmeal);

    List<SetmealDto> queryAllDtoA(SetmealDto example);

    List<SetmealDto> queryAllDtoB(SetmealDto example);

    void saveDto(SetmealDto setmealDto);

    void deleteDtoByIds(List<Long> ids);
}
