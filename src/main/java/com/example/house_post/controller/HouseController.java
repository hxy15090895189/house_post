package com.example.house_post.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.house_post.entity.House;
import com.example.house_post.entity.Search;
import com.example.house_post.entity.Users;
import com.example.house_post.service.HouseService;
import com.example.house_post.until.UploadOSSUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/house")
@Api(description = "房源管理")
@CrossOrigin(allowCredentials ="true")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @GetMapping("/GetAllHuse")
    @ApiOperation("获取所有房源信息")
    public List<House> getAllHouse() {
        List<House> list = houseService.list();
        return list;
    }

    @PostMapping("/AddHouse")
    @ApiOperation("添加房源信息")
    public boolean addHouse(@RequestBody House house) {
        boolean save = houseService.save(house);
        return save;
    }

    @GetMapping("/deleteHouse/{id}")
    @ApiOperation("删除房源")
    public String deleteHouse(
            @ApiParam(name = "id", value = "房源id", required = true)
            @PathVariable String id) {
        boolean b = houseService.removeById(id);
        if (b) {
            return " {\"result\":1}";
        } else {
            return " {\"result\":0}";
        }
    }


    @GetMapping("/SelectPage/{page}/{limit}/{id}")
    @ApiOperation("分页查询")
    public Map<String,Object> getHouseByPage(
            @ApiParam(name = "page", value = "当前页", required = true,defaultValue = "1") @PathVariable Integer page,
            @ApiParam(name = "limit", value = "页容量",defaultValue = "3") @PathVariable Integer limit,
            @ApiParam(name = "id",value = "id",defaultValue = "1006") @PathVariable Integer id
    ) {

        Page<House> page1 = new Page<>(page,limit);
        IPage<House> housePage =houseService.findAllPage(id,page1);
        //返回多块信息:如总页，当前页的记录
        Map<String,Object> map=new HashMap<>();
        map.put("totalPage",housePage.getPages());  //封装总页
        map.put("rows",housePage.getRecords()); //封装当前页的记录
        return map;
    }

    @PostMapping("/uploadFile")
    @ApiOperation("房源信息添加以及文件上传")
    public String upLoadHouse(@RequestParam("file") MultipartFile file, House house, HttpSession session) {
        String id = Long.toString(System.currentTimeMillis());
        String path = UploadOSSUntil.upload(file);
        Users logininfo = (Users)session.getAttribute("logininfo");
        if (logininfo!=null){
            house.setUserId(logininfo.getId());
        }else {
            house.setUserId(2);
        }
        System.out.println(path);
        house.setId(id);
        house.setPath(path);
        boolean save = houseService.save(house);
        if (save){
            return " {\"result\":1}";
        }else {
            return " {\"result\":0}";
        }
    }

    @GetMapping("/selectAndPage/{page}/{limit}")
    @ApiOperation("条件分页查询")
    public List<House> selectAndPage(
            Search search,
            @ApiParam(name = "page", value = "当前页", required = true,defaultValue = "1") @PathVariable Integer page,
            @ApiParam(name = "limit", value = "页容量",defaultValue = "3") @PathVariable Integer limit
            ){

        Page<House> page1 = new Page<>(page,limit);
        QueryWrapper<House> queryWrapper = new QueryWrapper<House>();
         if (search.getTitle()!=null){
             queryWrapper.like("title",search.getTitle());
         }
         if (search.getDid()!=null&&search.getDid()!=0){
             queryWrapper.eq("district.id",search.getDid());
         }
         if (search.getSid()!=null){
             queryWrapper.eq("street.id",search.getDid());
         }
         if (search.getTid()!=null){
             queryWrapper.eq("type_id",search.getTid());
         }
         if (search.getStartPrice()!=null){
             queryWrapper.gt("price",search.getStartPrice());
         }
         if (search.getEndPrice()!=null){
             queryWrapper.lt("price",search.getEndPrice());
         }

        IPage<House> houseIPage = houseService.slecteSearchByPage(page1, queryWrapper);
        return houseIPage.getRecords();
    }
}