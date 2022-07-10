package com.hhhlbjshop.backend.service;

import com.hhhlbjshop.backend.entity.UserInfo;
import com.hhhlbjshop.backend.repository.UserInfoRepository;
import com.hhhlbjshop.backend.util.BaseUtil;
import com.hhhlbjshop.backend.util.GetSeconds2Tomorrow;
import com.hhhlbjshop.backend.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserLockService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public ResultUtil<String> userLock(String userName) throws ParseException {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        userInfo.setPwdNum(userInfo.getPwdNum()+1);
        userInfoRepository.save(userInfo);
        ResultUtil resultUtil = new ResultUtil();
        resultUtil = ResultUtil.fail("密码错误，请重新输入");
        if (userInfo.getPwdNum() == 4){
            long fiveMin = 5 * 60 * 1000;
            doLockTime(userName, fiveMin);
            resultUtil = ResultUtil.fail("密码错误次数过多，请5分钟后重试");
        }else if (userInfo.getPwdNum() == 5) {
            long fiveMin = 30 * 60 * 1000;
            doLockTime(userName, fiveMin);
            resultUtil = ResultUtil.fail("密码错误次数过多，请30分钟后重试");
        }else if (userInfo.getPwdNum() == 6) {
            long fiveMin = 60 * 60 * 1000;
            doLockTime(userName, fiveMin);
            resultUtil = ResultUtil.fail("密码错误次数过多，请60分钟后重试");
        }else if (userInfo.getPwdNum() > 6) {
            long oneDay = GetSeconds2Tomorrow.getSeconds2Tomorrow() * 1000;
            doLockTime(userName, oneDay);
            resultUtil = ResultUtil.fail("今天密码错误次数过多，请明天再来");
        }
       return resultUtil;
    }
    //锁定时间函数
    private void doLockTime(String userName, long addTimes) throws ParseException {
        Date now = new Date();
        long times = now.getTime();//时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String lock_Time = simpleDateFormat.format(new Date(times + addTimes));
        Date lockTime = simpleDateFormat.parse(lock_Time);
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        userInfo.setUserStatus("error");
        userInfo.setExpireTime(lockTime);
        userInfoRepository.save(userInfo);
    }

    public void changeStatus(String userName) {
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        Date now = new Date();
        //判断过期时间是否过期
        if (BaseUtil.Base_HasValue(userInfo) && BaseUtil.Base_HasValue(userInfo.getExpireTime())) {
            //时间过期，状态变为正常
            if (now.after(userInfo.getExpireTime())) {
                userInfo.setUserStatus("normal");
                userInfoRepository.save(userInfo);
            }
        }
    }

    public void cleanZero(String userName){
        UserInfo userInfo = userInfoRepository.findByUserName(userName);
        userInfo.setPwdNum(0);
        userInfo.setUserStatus("normal");
        userInfoRepository.save(userInfo);
    }
}
