package com.etammag.ieat.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.entity.dto.DishDto;
import com.etammag.ieat.entity.Dish;
import com.etammag.ieat.entity.Flavor;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.DishFlavorMapper;
import com.etammag.ieat.mapper.DishMapper;
import com.etammag.ieat.service.CategoryService;
import com.etammag.ieat.service.DishService;
import com.etammag.ieat.service.FlavorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Service
public class DishServiceImpl implements DishService {

    @Resource
    private DishMapper dishMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private FlavorService flavorService;

    @Override
    public boolean catByCategoryId(Long id) {
        return dishMapper.catByCategoryId(id) != null;
    }

    @Override
    public List<Dish> queryByIds(List<Long> ids) {
        return dishMapper.selectBatchIds(ids);
    }

    @Override
    public boolean catById(Long id) {
        return dishMapper.catById(id) != null;
    }

    @Override
    public void saveAll(List<Dish> dishes) {
        if(dishes != null && !dishes.isEmpty()) {
            dishMapper.insertAll(dishes);
        }
    }

    private DishDto toDto(Dish dish) {
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setCategoryName(categoryService.queryNameById(dishDto.getCategoryId()));
        dishDto.setFlavors(flavorService.queryAllByDishId(dishDto.getId()));
        return dishDto;
    }

    @Override
    public DishDto queryDtoById(Long id) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(Dish::getId, id);
        return toDto(dishMapper.selectOne(queryWrapper));
    }

    private List<DishDto> queryAllDto(Wrapper<Dish> queryWrapper) {
        List<Dish> dishes = dishMapper.selectList(queryWrapper);
        List<DishDto> dishDtos = new ArrayList<>(dishes.size());
        for (Dish dish : dishes) {
            dishDtos.add(toDto(dish));
        }
        return dishDtos;
    }

    /**
     * 根据name-like查询，updateTime降序
     *
     * @param example 查询参照对象
     * @return List<DishDto>
     */
    @Override
    public List<DishDto> queryAllDtoA(Dish example) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(example.getName()), Dish::getName, example.getName());
        queryWrapper
                .orderByDesc(Dish::getUpdateTime)
        ;
        return queryAllDto(queryWrapper);
    }

    /**
     * 根据name-like，category，status=1查询，updateTime降序排序
     *
     * @param example 查询参照对象
     * @return List<DishDto>
     */
    @Override
    public List<DishDto> queryAllDtoB(Dish example) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(example.getName()), Dish::getName, example.getName())
                .eq(example.getCategoryId() != null, Dish::getCategoryId, example.getCategoryId())
                .eq(Dish::getStatus, 1)
                .orderByDesc(Dish::getUpdateTime)
                ;
        return queryAllDto(queryWrapper);
    }

    private void updateFlavors(Long dishId, List<Flavor> flavors) {
        List<Flavor> list1 = new ArrayList<>();
        List<Flavor> list2 = new ArrayList<>();
        for(Flavor flavor : flavors) {
            if(flavor.getId() == null) {
                list1.add(flavor);
                list2.add(flavor);
            }else {
                if(dishFlavorMapper.catByDishAndFlavorId(dishId, flavor.getId()) == null) {
                    list2.add(flavor);
                }else {
                    throw new CustomException("菜品ID不存在");
                }
            }
        }
        flavorService.saveAll(list1); //会自动填上的ID
        dishFlavorMapper.insertAllFlavors(
                dishId,
                list2.stream().map(Flavor::getId).toList(),
                BaseContext.getCurrentLogin().getId()
        );
    }

    @Override
    @Transactional
    public void saveDto(DishDto dishDto) {
        if (!categoryService.catById(dishDto.getCategoryId())) {
            throw new CustomException("菜品ID不存在");
        }
        dishMapper.insert(dishDto);
        updateFlavors(dishDto.getId(), dishDto.getFlavors());
    }

    @Override
    @Transactional
    public void updateDtoById(DishDto dishDto) {
        if (!categoryService.catById(dishDto.getCategoryId())) {
            throw new CustomException("菜品ID不存在");
        }
        dishMapper.updateById(dishDto);
        updateFlavors(dishDto.getId(), dishDto.getFlavors());
    }

}
