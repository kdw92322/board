package com.web.app.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_info")
@Entity
public class UserInfo {

    @Id
    @Column(unique = true)
    private String userId;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;
    
    @Column
    private String password;

    @Column
    private String birth;
    
    @Column
    private String phone;
    
    @Column
    private Date joinDate;
    
    @Column
    private String userRole;
    
    @Column
    private Date uptDt;
}