package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
