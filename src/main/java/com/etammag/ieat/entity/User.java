package com.etammag.ieat.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author etammag
 * @since 2023-10-02
 */
@Getter
@Setter
@ApiModel(value = "User", description = "用户信息")
public class User {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("状态 0:禁用，1:正常")
    private Integer status;


}
