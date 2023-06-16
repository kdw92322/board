package com.web.app.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public UserInfo create(String userId, String username, String email, String password, String birth, String phone, Date joinDate) {
    	UserInfo user = new UserInfo();
    	user.setUserId(userId);
    	user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setBirth(birth);
        user.setPhone(phone);
        user.setJoinDate(joinDate);
        this.userRepository.save(user);
        return user;
    }
    
    public List<Map<String,Object>> selectUserList(Map<String,Object> paramMap) throws Exception {
		return usermapper.selectUserList(paramMap);
	}
}
