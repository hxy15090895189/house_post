package com.example.house_post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.house_post.entity.Street;
import com.example.house_post.mapper.StreetMapper;
import com.example.house_post.service.StreetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StreetServiceImpl extends ServiceImpl<StreetMapper, Street> implements StreetService {

    @Override
    public List<Street> selecteById(Integer id) {
        QueryWrapper<Street> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DISTRICT_ID",id);
        List<Street> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
