package com.etammag.ieat.entity.dto;

import com.etammag.ieat.entity.Dish;
import com.etammag.ieat.entity.Setmeal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "SetmealDto", description = "套餐管理")
public class SetmealDto extends Setmeal {

    @ApiModelProperty("餐品列表")
    private List<Dish> dishes;

    @ApiModelProperty("类型名称")
    private String categoryName;

}
