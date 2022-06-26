package com.hhhlbjshop.backend.controller;

import com.hhhlbjshop.backend.entity.UserInfo;
import com.hhhlbjshop.backend.repository.UserRepository;
import com.hhhlbjshop.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserLogController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public ResultUtil<String> login(String userName, String password) {
        UserInfo userInfo = userRepository.findById(userName).orElse(null);
        if (userInfo == null) {
            return ResultUtil.fail("该用户不存在", null);
        } else if (userName.equals(userInfo.getUsername()) && password.equals(userInfo.getPassword())) {
            return ResultUtil.success("登陆成功", null);
        } else {
            return ResultUtil.fail("密码错误，请重新输入", null);
        }
    }
}
