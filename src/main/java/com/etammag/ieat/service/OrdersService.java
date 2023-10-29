package com.etammag.ieat.service;

import com.etammag.ieat.entity.Orders;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
public interface OrdersService {

    void submit(Orders orders);
}
