package com.hhhlbjshop.backend.service;

import com.hhhlbjshop.backend.entity.UserInfo;
import com.hhhlbjshop.backend.repository.UserInfoRepository;
import com.hhhlbjshop.backend.util.BaseUtil;
import com.hhhlbjshop.backend.util.MD5Util;
import com.hhhlbjshop.backend.util.ResultUtil;
import com.hhhlbjshop.backend.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public ResultUtil<String> doRegister(String userName, String password, String conPassword) {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        UserInfo userInfoReg = new UserInfo();
        String passWord = MD5Util.getMD5(password);
        ResultUtil resultUtil = new ResultUtil();
        if (BaseUtil.Base_HasValue(userInfo)) {
            resultUtil = ResultUtil.fail("该用户名已存在");
        } else if (!password.equals(conPassword)) {
            resultUtil = ResultUtil.fail("两次密码不一致");
        } else if (userName.length() < 3 || userName.length() > 11 || password.length() < 6 || password.length() > 16) {
            resultUtil = ResultUtil.fail("填写信息有误，请核对");
        } else {
            userInfoReg.setId(UUIDGenerator.getUUID());
            userInfoReg.setUserName(userName);
            userInfoReg.setPassword(passWord);
            userInfoReg.setUserStatus("normal");
            userInfoReg.setPwdNum(0);
            userInfoRepository.save(userInfoReg);
            resultUtil = ResultUtil.success("注册成功");
        }
        return resultUtil;
    }
}