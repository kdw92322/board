package com.web.app.user;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message = "ID는 필수항목입니다.")
    private String userId;
	
    @NotEmpty(message = "사용자이름은 필수항목입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;
    
	/* 이메일 */
    private String emailId;
    private String emailAddr;
    private String txtEmailAddr;
    
    /* 생년월일 */
    private String year;
    private String month;
    private String day;
    
    /* 휴대전화 */
    private String serial;
    private String phoneNum1;
    private String phoneNum2;
    
    private String userRole;
    private String fileNm;
    
    private Date joinDate;
    private Date uptDt;
    private int lastupdatediff;
}
