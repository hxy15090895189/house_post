package com.example.house_post.controller;


import com.example.house_post.entity.Users;
import com.example.house_post.service.UsersService;
import com.example.house_post.until.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yang
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/users")
@Api(description = "用户管理")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping("/userRegiest")
    @ApiOperation("用户注册")
    public String userRegiet(Users users,HttpSession session){
        String s = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(s);
        boolean save = usersService.save(users);
        if (save){
            session.setMaxInactiveInterval(6000);
            session.setAttribute("userId",users.getId());
            return " {\"result\":1}";
        }
        return " {\"result\":0}";
    }

    @PostMapping("/userLogin/{username}/{password}")
    @ApiOperation("用户的登录验证")
    public String userLogin(
            @ApiParam(name = "username",value = "用户名",required = true)
            @PathVariable String username,
            @ApiParam(name = "password",value = "密码",required = true)
            @PathVariable String password,
            HttpSession session
    ){
        String md5Encrypt = MD5Utils.md5Encrypt(password);
        Users users =usersService.selectByUsername(username,md5Encrypt);
        session.setAttribute("logininfo",users);
        session.setMaxInactiveInterval(6000);

        if (users!=null){
            return " {\"result\":1}";
        }else {
            return " {\"result\":0}";
        }
    }

}
