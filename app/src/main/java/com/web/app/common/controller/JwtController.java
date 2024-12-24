 package com.web.app.common.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.common.service.JwtService;
import com.web.app.security.jwt.LoginViewModel;
import com.web.app.security.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
public class JwtController {
	
	@Inject
	private JwtService jwtService;
	
	@PostMapping("/createToken")
	/*LoginViewModel*/
	public TokenInfo createToken(@RequestBody LoginViewModel loginInfo, HttpServletResponse response) {
		return jwtService.createToken(loginInfo);
	}
}
