package com.web.app.security.jwt;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.web.app.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
public class CustomSecurityConfig {
	/*
	// 인증되지 않은 사용자 접근에 대한 handler
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserSecurityService userSecurityService;
	private final AuthenticationConfiguration authenticationConfiguration;
	
	// JWT 요청 처리 필터
	//private final JwtRequestFilter jwtRequestFilter;

	/*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return web -> web.ignoring()
        		.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        		.antMatchers("/css/**", "/fontawesome/**", "/image/**", "/js/**");
    }
    */
    
    /*
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	*/
	
	/*
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
    	 return authenticationConfiguration.getAuthenticationManager();
    }
	*/
	
    /*
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		return http
				.httpBasic().disable()
				.formLogin().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilterAt(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "/user/signup", "/jwtLogin").permitAll()
				.antMatchers("/jwt/**").permitAll()
				.and()
				.
				.build();
	}
	*/
}
