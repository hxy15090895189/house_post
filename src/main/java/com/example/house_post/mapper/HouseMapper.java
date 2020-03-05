package com.example.house_post.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.house_post.entity.House;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
public interface HouseMapper extends BaseMapper<House> {


    IPage<House> selectByid2( @Param("id") Integer id,Page<House> page);

    IPage<House> selectQueryAndPage(Page<House> page1,  @Param(Constants.WRAPPER) QueryWrapper<House> queryWrapper);
}
