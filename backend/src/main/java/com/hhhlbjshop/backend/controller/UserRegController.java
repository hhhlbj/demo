package com.hhhlbjshop.backend.controller;

import com.hhhlbjshop.backend.entity.UserInfo;
import com.hhhlbjshop.backend.repository.UserInfoRepository;
import com.hhhlbjshop.backend.service.UserRegService;
import com.hhhlbjshop.backend.util.MD5Util;
import com.hhhlbjshop.backend.util.ResultUtil;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserRegController {

    @Autowired
    UserRegService userRegService;

    @RequestMapping("/register")
    public ResultUtil<String> UserRegister(
            @RequestParam("userName") @ApiParam(name = "userName", value = "用户名", required = true) String userName,
            @RequestParam("password") @ApiParam(name = "password", value = "密码", required = true) String password,
            @RequestParam("conPassword") @ApiParam(name = "conPassword", value = "确认密码", required = true) String conPassword
    ) throws Exception {
        ResultUtil commonResult = userRegService.doRegister(userName, password, conPassword);
        return commonResult;
    }
}
