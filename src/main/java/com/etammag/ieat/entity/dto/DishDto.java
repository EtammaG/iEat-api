package com.etammag.ieat.entity.dto;

import com.etammag.ieat.entity.Dish;
import com.etammag.ieat.entity.Flavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "DishDto", description = "菜品管理")
public class DishDto extends Dish {

    @ApiModelProperty("口味列表")
    private List<Flavor> flavors;

    @ApiModelProperty("类型名称")
    private String categoryName;

}
