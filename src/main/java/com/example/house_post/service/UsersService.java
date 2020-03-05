package com.example.house_post.service;

import com.example.house_post.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
public interface UsersService extends IService<Users> {

    Users selectByUsername(String username, String password);
}
