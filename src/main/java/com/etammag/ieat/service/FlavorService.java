package com.etammag.ieat.service;

import com.etammag.ieat.entity.Flavor;

import java.util.List;

/**
 * <p>
 * 菜品口味表 服务类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
public interface FlavorService {

    List<Flavor> queryAllByDishId(Long id);

    void saveAll(List<Flavor> flavors);
}
