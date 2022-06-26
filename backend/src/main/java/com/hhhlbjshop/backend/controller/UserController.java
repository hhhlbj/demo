package com.hhhlbjshop.backend.controller;

import com.hhhlbjshop.backend.entity.UserTest;
import com.hhhlbjshop.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/login")
    public String login(String userName, String password){
        UserTest user = userRepository.findById(userName).orElse(null);
        if (userName.equals(user.getUsername()) && password.equals(user.getPassword())){
            return "登录成功";
        }else{
            return "登录失败";
        }
    }
}
