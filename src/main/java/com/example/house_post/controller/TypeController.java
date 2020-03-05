package com.example.house_post.controller;


import com.example.house_post.entity.Type;
import com.example.house_post.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/type")
@Api(description = "房源类型")
@CrossOrigin
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/getAllType")
    @ApiOperation("获取所有房源类型信息")
    public List<Type> getAllType(){
        List<Type> list = typeService.list();
        return list;
    }

}
