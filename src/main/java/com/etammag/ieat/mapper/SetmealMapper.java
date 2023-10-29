package com.etammag.ieat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.etammag.ieat.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 套餐 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

    Integer catByCategoryId(Long id);
}
