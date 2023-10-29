package com.etammag.ieat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.dto.SetmealDto;
import com.etammag.ieat.entity.Dish;
import com.etammag.ieat.entity.Setmeal;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.SetmealMapper;
import com.etammag.ieat.service.CategoryService;
import com.etammag.ieat.service.DishService;
import com.etammag.ieat.service.SetmealDishService;
import com.etammag.ieat.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Service
public class SetmealServiceImpl implements SetmealService {

    @Resource
    private SetmealMapper setmealMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private DishService dishService;

    @Resource
    private SetmealDishService setmealDishService;

    @Override
    public boolean catByCategoryId(Long id) {
        return setmealMapper.catByCategoryId(id) != null;
    }

    @Override
    public List<Setmeal> queryAll(Wrapper<Setmeal> queryWrapper) {
        return setmealMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void save(Setmeal setmeal) {
        setmeal.setId(IdWorker.nextId());
        setmealMapper.insert(setmeal);
    }

    public void deleteByIds(List<Long> ids) {
        setmealMapper.deleteBatchIds(ids);
    }

    private SetmealDto toDto(Setmeal setmeal) {
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);
        setmealDto.setCategoryName(categoryService.queryNameById(setmeal.getCategoryId()));
        setmealDto.setDishes(dishService.queryByIds(setmealDishService.queryDishBySetmeal(setmeal.getId())));
        return setmealDto;
    }

    private List<SetmealDto> toDtos(List<Setmeal> setmeals) {
        List<SetmealDto> res = new ArrayList<>(setmeals.size());
        for (Setmeal setmeal : setmeals) {
            res.add(toDto(setmeal));
        }
        return res;
    }

    private List<SetmealDto> queryAllDto(Wrapper<Setmeal> queryWrapper) {
        List<Setmeal> setmeals = queryAll(queryWrapper);
        return toDtos(setmeals);
    }

    @Override
    public List<SetmealDto> queryAllDtoA(SetmealDto example) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(example.getName()), Setmeal::getName, example.getName());
        queryWrapper
                .orderByDesc(Setmeal::getUpdateTime);
        return queryAllDto(queryWrapper);
    }

    @Override
    public List<SetmealDto> queryAllDtoB(SetmealDto example) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(example.getName()), Setmeal::getName, example.getName())
                .eq(example.getCategoryId() != null, Setmeal::getCategoryId, example.getCategoryId())
                .eq(example.getStatus() != null, Setmeal::getStatus, example.getStatus())
        ;
        queryWrapper
                .orderByDesc(Setmeal::getUpdateTime);
        return queryAllDto(queryWrapper);
    }

    private void updateDishes(Long id, List<Dish> dishes) {
        List<Dish> toBeSaved = new ArrayList<>();
        for(Dish dish : dishes) {
            if(dish.getId() == null || !dishService.catById(dish.getId())) {
                toBeSaved.add(dish);
            }
        }
        dishService.saveAll(toBeSaved);
        setmealDishService.saveAllDishes(id, dishes.stream().map(Dish::getId).toList());
    }

    @Override
    @Transactional
    public void saveDto(SetmealDto setmealDto) {
        if(!categoryService.catById(setmealDto.getCategoryId())) {
            throw new CustomException("类别id不存在");
        }
        save(setmealDto);
        updateDishes(setmealDto.getId(), setmealDto.getDishes());
    }

    @Override
    @Transactional
    public void deleteDtoByIds(List<Long> ids) {
        if(ids != null && !ids.isEmpty()) {
            setmealDishService.deleteBySetmealIds(ids);
            deleteByIds(ids);
        }
    }
}
