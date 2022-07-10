package com.hhhlbjshop.backend.repository;

import com.hhhlbjshop.backend.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByUserName(String userName);
}
