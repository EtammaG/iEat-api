package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.Flavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜品口味表 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Mapper
public interface FlavorMapper extends BaseMapper<Flavor> {

    int insertAll(List<Flavor> flavors);

}
