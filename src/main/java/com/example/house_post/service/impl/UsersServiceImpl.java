package com.example.house_post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.house_post.entity.Users;
import com.example.house_post.mapper.UsersMapper;
import com.example.house_post.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public Users selectByUsername(String username, String password) {
            QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",username);
            queryWrapper.eq("password",password);
            Users users = baseMapper.selectOne(queryWrapper);
            if (users!=null){
                return users;
            }else {
                return null;
            }

    }
}
