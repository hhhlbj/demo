package com.hhhlbjshop.backend.controller;

import com.hhhlbjshop.backend.service.UserLogService;
import com.hhhlbjshop.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserLogController {

    @Autowired
    UserLogService userLogService;

    @RequestMapping("/login")
    public ResultUtil<String> userLogin(
            @RequestParam("userName") @ApiParam(name = "userName", value = "用户名", required = true) String userName,
            @RequestParam("password") @ApiParam(name = "password", value = "密码", required = true) String password
    ) throws Exception {
        ResultUtil commonResult = userLogService.doLogin(userName, password);
        return commonResult;
    }
}
