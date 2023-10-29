package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    List<Long> selectAllIdByUserId(Long userId);
}
