package com.example.house_post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.house_post.entity.House;
import com.example.house_post.entity.Search;
import com.example.house_post.mapper.HouseMapper;
import com.example.house_post.service.HouseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Override
    public List<House> slecteByPage(Page<House> page1, Search search) {
        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        Page<House> housePage = baseMapper.selectPage(page1, queryWrapper);
        return housePage.getRecords();

    }

    @Override
    public IPage<House> findAllPage(Integer id,Page<House> page1) {
        return baseMapper.selectByid2(id,page1);
    }

    @Override
    public IPage<House> slecteSearchByPage(Page<House> page1, QueryWrapper<House> queryWrapper) {
        return baseMapper.selectQueryAndPage(page1,queryWrapper);
    }
}
