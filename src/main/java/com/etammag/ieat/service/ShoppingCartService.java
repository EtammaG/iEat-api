package com.etammag.ieat.service;

import com.etammag.ieat.entity.ShoppingCart;

import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-03
 */
public interface ShoppingCartService {

    List<Long> queryAllIdByUserId(Long userId);

    List<ShoppingCart> queryAllA(ShoppingCart shoppingCart);

    void add(ShoppingCart shoppingCart);

    void removeAllA(ShoppingCart shoppingCart);

}
