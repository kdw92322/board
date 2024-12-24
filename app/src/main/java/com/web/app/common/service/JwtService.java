package com.web.app.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.web.app.security.jwt.JwtTokenProvider;
import com.web.app.security.jwt.LoginViewModel;
import com.web.app.security.jwt.TokenInfo;
import com.web.app.user.UserSecurityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
    public TokenInfo createToken(LoginViewModel loginInfo) {

    	UserDetails userDetails = userSecurityService.loadUserByUsername(loginInfo.getUserId());
    	TokenInfo tokenInfo = jwtTokenProvider.generateToken(userDetails);
        
    	return tokenInfo;
    }
}
