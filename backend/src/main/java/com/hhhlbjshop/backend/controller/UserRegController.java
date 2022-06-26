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
public class UserRegController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/register")
    public ResultUtil<String> reg(String userName, String password, String conPassword) {
        UserInfo userInfo = userRepository.findById(userName).orElse(null);
        UserInfo userInfoAdd = new UserInfo();
        if (userInfo != null) {
            return ResultUtil.fail("该用户已存在", null);
        } else if (!password.equals(conPassword)) {
            return ResultUtil.fail("两次密码不一致", null);
        } else if (userName.equals("") || password.equals("") || conPassword.equals("")) {
            return ResultUtil.fail("填写信息有误，请核对", null);
        } else {
            userInfoAdd.setUsername(userName);
            userInfoAdd.setPassword(password);
            userRepository.save(userInfoAdd);
            return ResultUtil.success("注册成功", null);
        }
    }
}
