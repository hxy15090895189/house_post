package com.example.house_post.service;

import com.example.house_post.entity.Street;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
public interface StreetService extends IService<Street> {

    List<Street> selecteById(Integer id);
}
