package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.Orders;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.mapper.OrdersMapper;
import com.etammag.ieat.service.OrderDetailService;
import com.etammag.ieat.service.OrdersService;
import com.etammag.ieat.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderDetailService orderDetailService;

    @Resource
    private ShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public void submit(Orders orders) {
        Long userId = BaseContext.getCurrentLogin().getId();
        List<Long> shoppingCartIds = shoppingCartService.queryAllIdByUserId(userId);
        if (shoppingCartIds == null || shoppingCartIds.isEmpty()) {
            throw new CustomException("购物车为空，请选择餐品再下单");
        }
        Long orderId = IdWorker.nextId();
        orders.setId(orderId);
        ordersMapper.insert(orders);
        orderDetailService.addAllShoppingCart(orderId, shoppingCartIds);
    }
}
