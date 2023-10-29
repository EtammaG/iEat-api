package com.etammag.ieat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.ShoppingCart;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.ShoppingCartMapper;
import com.etammag.ieat.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<Long> queryAllIdByUserId(Long userId) {
        return shoppingCartMapper.selectAllIdByUserId(userId);
    }

    @Override
    public List<ShoppingCart> queryAllA(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(ShoppingCart::getUserId, shoppingCart.getUserId())
        ;
        queryWrapper
                .orderByAsc(ShoppingCart::getCreateTime)
        ;
        return shoppingCartMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void add(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        if (shoppingCart.getDishId() != null) {
            queryWrapper
                    .eq(ShoppingCart::getDishId, shoppingCart.getDishId())
            ;
        } else if (shoppingCart.getSetmealId() != null) {
            queryWrapper
                    .eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId())
            ;
        } else {
            throw new CustomException("必须是菜品或者套餐");
        }
        queryWrapper
                .eq(ShoppingCart::getUserId, shoppingCart.getUserId())
        ;
        ShoppingCart select = shoppingCartMapper.selectOne(queryWrapper);
        if (select == null) {
            shoppingCart.setId(IdWorker.nextId());
            shoppingCart.setNumber(1);
            shoppingCartMapper.insert(shoppingCart);
        } else {
            select.setNumber(select.getNumber() + 1);
            shoppingCartMapper.updateById(select);
        }
    }

    @Override
    @Transactional
    public void removeAllA(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(ShoppingCart::getUserId, shoppingCart.getUserId());
        shoppingCartMapper.delete(queryWrapper);
    }
}
