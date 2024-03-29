package com.web.app.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginViewModel {
	private String userId;
	private String password;
}
