package com.etammag.ieat.service.impl;

import com.etammag.ieat.common.utils.BaseContext;
import com.etammag.ieat.common.utils.IdWorker;
import com.etammag.ieat.entity.Flavor;
import com.etammag.ieat.entity.details.Login;
import com.etammag.ieat.mapper.DishFlavorMapper;
import com.etammag.ieat.mapper.FlavorMapper;
import com.etammag.ieat.service.FlavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 菜品口味表 服务实现类
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Service
public class FlavorServiceImpl implements FlavorService {

    @Resource
    private FlavorMapper flavorMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Override
    public List<Flavor> queryAllByDishId(Long id) {
        return dishFlavorMapper.selectAllFlavorByDishId(id);
    }

    @Override
    public void saveAll(List<Flavor> flavors) {
        Login principal = BaseContext.getCurrentLogin();
        for (Flavor flavor : flavors) {
            flavor.setId(IdWorker.nextId());
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setCreateUser(principal.getId());
            flavor.setUpdateUser(principal.getId());
        }
        flavorMapper.insertAll(flavors);
    }

}
