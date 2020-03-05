package com.example.house_post.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="House对象", description="")
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID")
    private String id;

    @ApiModelProperty(value = "用户编号")
    @TableField("USER_ID")
    private Integer userId;

    @ApiModelProperty(value = "类型编号")
    @TableField("TYPE_ID")
    private Integer typeId;

    @ApiModelProperty(value = "标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "出租价")
    @TableField("PRICE")
    private BigDecimal price;

    @ApiModelProperty(value = "发布日期")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate pubdate;

    @ApiModelProperty(value = "面积")
    @TableField("FLOORAGE")
    private Integer floorage;

    @ApiModelProperty(value = "联系人")
    @TableField("CONTACT")
    private String contact;

    @ApiModelProperty(value = "街道编号")
    @TableField("STREET_ID")
    private Integer streetId;

    @ApiModelProperty(value = "是否审核通过")
    private Integer ispass;

    @ApiModelProperty(value = "是否删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isdel;

    @ApiModelProperty(value = "图片路径")
    private String path;

    private  String tname; //类型名称

    private String sname; //街道名称

    private String dname; //区域名称
}
