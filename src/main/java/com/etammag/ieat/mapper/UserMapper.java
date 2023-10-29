package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author etammag
 * @since 2023-10-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
