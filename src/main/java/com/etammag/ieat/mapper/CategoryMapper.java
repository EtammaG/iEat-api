package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    String selectNameById(Long id);

    Integer catById(Long id);
}
