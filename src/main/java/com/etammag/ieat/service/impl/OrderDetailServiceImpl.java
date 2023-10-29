package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.OrderDetail;
import com.etammag.ieat.mapper.OrderDetailMapper;
import com.etammag.ieat.service.OrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Override
    public void addAllShoppingCart(Long orderId, List<Long> shoppingCartIds) {
        List<OrderDetail> orderDetails = new ArrayList<>(shoppingCartIds.size());
        for (Long shoppingCartId : shoppingCartIds) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(IdWorker.nextId());
            orderDetail.setOrdersId(orderId);
            orderDetail.setShoppingCartId(shoppingCartId);
            orderDetails.add(orderDetail);
        }
        orderDetailMapper.insertAll(orderDetails);
    }
}
