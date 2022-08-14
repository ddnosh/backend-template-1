package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Generated;

@Data
@ApiModel(description="用户实体类")
public class User {
    @ApiModelProperty("用户Id")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer id;

    @ApiModelProperty("用户Name")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;
}