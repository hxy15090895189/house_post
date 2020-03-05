package com.example.house_post.controller;


import com.example.house_post.entity.District;
import com.example.house_post.service.DistrictService;
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
@RequestMapping("/district")
@Api(description = "查询所有的区域")
@CrossOrigin
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping("/getAllDis")
    @ApiOperation("获取所有的区域")
    public List<District> getAllDis(){
        List<District> list = districtService.list();
        return list;
    }
}
