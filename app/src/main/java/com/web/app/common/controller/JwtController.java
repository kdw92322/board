package com.web.app.common.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.common.service.JwtService;
import com.web.app.security.jwt.JwtTokenProvider;
import com.web.app.security.jwt.LoginViewModel;
import com.web.app.security.jwt.TokenInfo;
import com.web.app.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginViewModel loginInfo) {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		System.out.println("userid : " + loginInfo.getUserId());
		System.out.println("password : " + loginInfo.getPassword());
		return rtnMap;
	}
}
