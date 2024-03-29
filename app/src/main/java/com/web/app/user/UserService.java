package com.web.app.user;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;

import com.web.app.file.service.FileService;
import com.web.app.file.service.FileVo;

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
    	user.setId(userform.getUserId());
    	user.setName(userform.getUsername());
    	
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
        
        String phone = userform.getSerial() + userform.getPhoneNum1() + userform.getPhoneNum2();
        user.setPhone(phone);
        
        Date nowDate = new Date();
        user.setJoin_date(nowDate);
        user.setRole(UserRole.GUEST.getValue());
        user.setAuthorities(UserRole.GUEST.getValue());

        this.userRepository.save(user);
        return user;
    }
    
    public Map<String, Object> chkDupId(@RequestBody Map<String, Object> ParamMap) throws Exception {
    	int cnt = usermapper.chkDupUserCnt(ParamMap);
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("CNT", cnt);
		return resultMap;
	}
    
    public List<Map<String,Object>> selectUserList(Map<String,Object> paramMap) throws Exception {
		return usermapper.selectUserList(paramMap);
	}
    
    public UserForm selectUserInfo(Map<String,Object> paramMap) throws Exception {
		return usermapper.selectUserInfo(paramMap);
	}
    
    //UserForm userform
    public void update(UserForm userform) {
    	Optional<UserInfo> _userInfo = this.userRepository.findById(userform.getUserId());
    	
    	_userInfo.ifPresent(selectUser -> {
    		selectUser.setName(userform.getUsername());    
    		
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
    		selectUser.setRole(userform.getUserRole());
    		selectUser.setUptDt(new Date());
    		userRepository.save(selectUser);
    	});
    	
    }
    
    public int delete(Map<String, Object> paramMap) {
    	List<FileVo> fileList = fileservice.selectfilelist(paramMap);
    	if(fileList.size() == 1) {
    		FileVo filevo = fileList.get(0);
    		paramMap.put("uuid", String.valueOf(filevo.getUuid()));  
    	}else {
    		LOG.info("첨부사진 없음");
    	}
    	LOG.info("parameter ==============>{}", paramMap);
    	fileservice.delete(paramMap);
    	return usermapper.deleteUser(paramMap);
    }

    public List<Map<String, Object>> getConnUserLogData(Map<String,Object> paramMap){

    	List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
    	String graphType = String.valueOf(paramMap.get("type"));
    	
    	if(graphType.equals("W")) {
    		resultList = usermapper.getConnUserLogData1(paramMap);
    	}else if(graphType.equals("M")){
    		resultList = usermapper.getConnUserLogData2(paramMap);
    	}
    	
		return resultList;
    }    
}