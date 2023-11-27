package com.web.app.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
	private UserMapper usermapper;
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserInfo create(UserForm userform) {
    	UserInfo user = new UserInfo();
    	user.setUserId(userform.getUserId());
    	user.setUsername(userform.getUsername());
    	
    	String email = userform.getEmailId();
    	if(userform.getEmailAddr() != null && !"".equals(userform.getEmailAddr())) {
    		email += userform.getEmailAddr();
    	}else {
    		email += userform.getTxtEmailAddr();
    	}
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(userform.getPassword1()));
        
        String birth = userform.getYear() + userform.getMonth() + userform.getDay();
        user.setBirth(birth);
        
        String phone = userform.getSerial() + userform.getPhoneNum1() + userform.getPassword2();
        user.setPhone(phone);
        
        user.setJoinDate(userform.getJoinDate());
        this.userRepository.save(user);
        return user;
    }
    
    public List<Map<String,Object>> selectUserList(Map<String,Object> paramMap) throws Exception {
		return usermapper.selectUserList(paramMap);
	}
    
    public UserForm selectUserInfo(Map<String,Object> paramMap) throws Exception {
		return usermapper.selectUserInfo(paramMap);
	}
    
    public void update(UserForm userform) {
    	Optional<UserInfo> userInfo = this.userRepository.findByUserId(userform.getUserId());
    	userInfo.ifPresent(selectUser -> {
    		selectUser.setUsername(userform.getUsername());
    		
    		String email = userform.getEmailId();
        	if(userform.getEmailAddr() != null && !"".equals(userform.getEmailAddr())) {
        		email += userform.getEmailAddr();
        	}else {
        		email += userform.getTxtEmailAddr();
        	}
    		selectUser.setEmail(email);
    		
    		String birth = userform.getYear() + userform.getMonth() + userform.getDay();
    		selectUser.setBirth(birth);
    		
    		String phone = userform.getSerial() + userform.getPhoneNum1() + userform.getPhoneNum2();
    		selectUser.setPhone(phone);
    		selectUser.setUserRole(userform.getUserRole());
    		selectUser.setUptDt(new Date());
    		userRepository.save(selectUser);
    	});
    }
    
    public int delete(String userId) {
    	System.out.println("userId : " + userId);
		return 0;
    }
    
    public int saveUserConnectLog(Map<String,Object> saveMap) {
		return usermapper.saveLoginUserLog(saveMap);
    }
}
