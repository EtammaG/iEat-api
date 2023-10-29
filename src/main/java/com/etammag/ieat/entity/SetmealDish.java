package com.etammag.ieat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author etammag
 * @since 2023-10-01
 */
@Getter
@Setter
@TableName("setmeal_dish")
@ApiModel(value = "SetmealDish", description = "套餐菜品关系")
public class SetmealDish {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("套餐id ")
    private Long setmealId;

    @ApiModelProperty("菜品id")
    private Long dishId;

    @ApiModelProperty("份数")
    private Integer copies;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("是否删除")
    private Integer isDeleted;


}
