package com.etammag.ieat.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜品口味表
 * </p>
 *
 * @author etammag
 * @since 2023-09-26
 */
@Getter
@Setter
@ApiModel(value = "Flavor", description = "菜品口味表")
public class Flavor {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("口味名称")
    private String name;

    @ApiModelProperty("口味数据list")
    private String value;

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
    @TableLogic
    private Integer isDeleted;


}
