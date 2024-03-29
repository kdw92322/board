package com.web.app.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "user_info")
@NoArgsConstructor
@Entity
public class UserInfo {

    @Id
    @Column(unique = true)
    private String id;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true)
    private String name;
    
    @Column
    private Date join_date;
    
    @Column(unique = true)
    private String email;

    @Column
    private String birth;
    
    @Column(unique = true)
    private String phone;
    
    @Column
    private Date uptDt;
    
    @Column
    private String role;
    
    @Column
    private String authorities;
    
}