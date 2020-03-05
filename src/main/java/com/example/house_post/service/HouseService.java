package com.example.house_post.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.house_post.entity.House;
import com.example.house_post.entity.Search;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
public interface HouseService extends IService<House> {

    List<House> slecteByPage(Page<House> page1, Search search);

    IPage<House> findAllPage(Integer id ,Page<House> page1);

    IPage<House> slecteSearchByPage(Page<House> page1, QueryWrapper<House> queryWrapper);
}
