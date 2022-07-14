package com.hhhlbjshop.backend.service;

import com.hhhlbjshop.backend.entity.UserInfo;
import com.hhhlbjshop.backend.repository.UserInfoRepository;
import com.hhhlbjshop.backend.util.BaseUtil;
import com.hhhlbjshop.backend.util.MD5Util;
import com.hhhlbjshop.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class UserLogService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserLockService userLockService;

    public ResultUtil<String> doLogin(String userName, String password) throws ParseException {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        String passWord = MD5Util.getMD5(password);
        ResultUtil resultUtil = new ResultUtil();
        userLockService.changeStatus(userName);
        if (!BaseUtil.Base_HasValue(userInfo)) {
            resultUtil = ResultUtil.fail("未找到该用户");
        } else if (userName.equals(userInfo.getUserName()) && !passWord.equals(userInfo.getPassword()) && userInfo.getUserStatus().equals("error")) {
            resultUtil = ResultUtil.fail("该账户状态异常，请稍后再试");
        } else if (userName.equals(userInfo.getUserName()) && !passWord.equals(userInfo.getPassword()) && userInfo.getUserStatus().equals("normal")) {
            resultUtil = userLockService.userLock(userName);
        } else if (userName.equals(userInfo.getUserName()) && passWord.equals(userInfo.getPassword()) && userInfo.getUserStatus().equals("normal")) {
            userLockService.cleanZero(userName);
            resultUtil = ResultUtil.success("登录成功");
        }
        return resultUtil;
    }
}
