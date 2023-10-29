package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.SetmealDish;
import com.etammag.ieat.mapper.SetmealDishMapper;
import com.etammag.ieat.service.SetmealDishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐菜品关系 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-01
 */
@Service
public class SetmealDishServiceImpl implements SetmealDishService {

    @Resource
    private SetmealDishMapper setmealDishMapper;

    @Override
    public List<Long> queryDishBySetmeal(Long setmealId) {
        return setmealDishMapper.selectDishBySetmeal(setmealId);
    }

    @Override
    public void saveAllDishes(Long setmealId, List<Long> dishIds) {
        List<SetmealDish> setmealDishes = new ArrayList<>(dishIds.size());
        for (Long dishId : dishIds) {
            SetmealDish setmealDish = new SetmealDish();
            setmealDish.setId(IdWorker.nextId());
            setmealDish.setSetmealId(setmealId);
            setmealDish.setDishId(dishId);
            setmealDish.setCopies(1);
            setmealDish.setSort(1);
            Long currentId = BaseContext.getCurrentLogin().getId();
            LocalDateTime now = LocalDateTime.now();
            setmealDish.setCreateTime(now);
            setmealDish.setUpdateTime(now);
            setmealDish.setCreateUser(currentId);
            setmealDish.setUpdateUser(currentId);
            setmealDishes.add(setmealDish);
        }
        setmealDishMapper.insertAll(setmealDishes);
    }

    @Override
    public void deleteBySetmealIds(List<Long> setmealIds) {
//        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper
//                .in(SetmealDish::getSetmealId, setmealIds);
//        setmealDishMapper.delete(queryWrapper);
        setmealDishMapper.deleteBySetmealIds(setmealIds);
    }
}
