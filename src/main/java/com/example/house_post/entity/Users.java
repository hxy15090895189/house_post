package com.example.house_post.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="Users对象", description="")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "用户密码")
    @TableField("PASSWORD")
    private String password;

    @ApiModelProperty(value = "电话")
    @TableField("TELEPHONE")
    private String telephone;

    @ApiModelProperty(value = "是否管理员")
    @TableField("ISADMIN")
    private Integer isadmin;


}
