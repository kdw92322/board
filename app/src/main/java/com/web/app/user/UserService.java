package com.web.app.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.app.file.service.FileService;
import com.web.app.file.service.FileVo;
import com.web.app.security.CustomSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private FileService fileservice;
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
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
        
        Date nowDate = new Date();
        user.setJoinDate(nowDate);
        user.setUserRole(UserRole.GUEST.getValue());
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
    
    public int delete(Map<String, Object> paramMap) {
    	//첨부파일 제거
    	List<FileVo> fileList = fileservice.selectfilelist(paramMap);
    	if(fileList.size() == 1) {
    		FileVo filevo = fileList.get(0);
    		paramMap.put("uuid", String.valueOf(filevo.getUuid()));  
    	}else {
    		LOG.info("첨부사진 없음");
    	}
    	LOG.info("parameter ==============>", paramMap);
    	fileservice.delete(paramMap);
    	return usermapper.deleteUser(paramMap);
    }
    
    public int saveUserLog(Map<String,Object> saveMap) {
		return usermapper.saveUserLog(saveMap);
    }
    
    public List<Map<String, Object>> getConnUserLogData(Map<String,Object> paramMap){
		return usermapper.getConnUserLogData(paramMap);
    }
    
    
}
