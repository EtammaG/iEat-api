package com.etammag.ieat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author etammag
 * @since 2023-10-04
 */
@Getter
@Setter
@TableName("shopping_cart")
@ApiModel(value = "ShoppingCart", description = "购物车")
public class ShoppingCart {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("主键")
    private Long userId;

    @ApiModelProperty("菜品id")
    private Long dishId;

    @ApiModelProperty("套餐id")
    private Long setmealId;

    @ApiModelProperty("选定口味")
    private String dishFlavor;

    @ApiModelProperty("数量")
    private Integer number;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
