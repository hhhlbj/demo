package com.hhhlbjshop.backend.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserInfo {
    @Id
    private String username;
    private String password;
}
