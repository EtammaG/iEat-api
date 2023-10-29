package com.etammag.ieat.controller;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.entity.Orders;
import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.service.OrdersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@RestController
@RequestMapping("/order")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @PostMapping("/submit")
    public Result<String> submit(@RequestBody Orders orders) {
        orders.setUserId(BaseContext.getCurrentLogin().getId());
        ordersService.submit(orders);
        return Result.success("下单成功");
    }

}
