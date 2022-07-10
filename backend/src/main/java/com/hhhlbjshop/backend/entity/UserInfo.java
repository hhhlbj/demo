package com.hhhlbjshop.backend.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class UserInfo {
    @Id
    private String id;
    private String userName;
    private String password;
    private String userStatus;
    private int pwdNum;
    private Date expireTime;
}
