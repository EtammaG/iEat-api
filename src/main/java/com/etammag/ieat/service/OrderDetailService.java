package com.etammag.ieat.service;

import java.util.List;

/**
 * <p>
 * 订单明细表 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
public interface OrderDetailService {

    void addAllShoppingCart(Long orderId, List<Long> shoppingCartIds);
}
