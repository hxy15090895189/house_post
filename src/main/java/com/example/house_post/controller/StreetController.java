package com.example.house_post.controller;


import com.example.house_post.entity.Street;
import com.example.house_post.service.StreetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/street")
@Api(description = "查询街道")
@CrossOrigin
public class StreetController {

    @Autowired
    private StreetService streetService;

    @GetMapping("/GetAllStreet")
    @ApiOperation("查询所有的街道")
    public List<Street> getAllStreet(){
        List<Street> list = streetService.list();
        return list;
    }

    @GetMapping("/GetStreetById/{id}")
    @ApiOperation("通过区域id查询")
    public List<Street> getStreetById(
            @ApiParam(name = "id",value = "区域id",required = true)
            @PathVariable Integer id
            ){
        List<Street> list =streetService.selecteById(id);
        return list;
    }
}
