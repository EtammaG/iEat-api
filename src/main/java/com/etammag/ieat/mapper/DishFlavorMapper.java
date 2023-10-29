package com.etammag.ieat.mapper;

import com.etammag.ieat.entity.Flavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    Integer catByDishAndFlavorId(@Param("dishId") Long dishId, @Param("flavorId") Long flavorId);

    List<Flavor> selectAllFlavorByDishId(Long dishId);

    int insertOne(@Param("dishId") Long dishId, @Param("flavorId") Long flavorId, @Param("userId") Long userId);

    int insertAllFlavors(@Param("dishId") Long dishId, @Param("flavorIds") List<Long> flavorIds, @Param("userId") Long userId);

    int insertAllDishes(@Param("dishIds") List<Long> dishId, @Param("flavorIds") Long flavorIds, @Param("userId") Long userId);

}
