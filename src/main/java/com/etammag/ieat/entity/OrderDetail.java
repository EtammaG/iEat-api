package com.etammag.ieat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Getter
@Setter
@TableName("order_detail")
@ApiModel(value = "OrderDetail", description = "订单明细表")
public class OrderDetail {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("订单id")
    private Long ordersId;

    @ApiModelProperty("购物车项id")
    private Long shoppingCartId;


}
