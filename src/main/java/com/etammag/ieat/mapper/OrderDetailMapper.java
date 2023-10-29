package com.etammag.ieat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.ieat.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 订单明细表 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    void insertAll(List<OrderDetail> orderDetails);
}
